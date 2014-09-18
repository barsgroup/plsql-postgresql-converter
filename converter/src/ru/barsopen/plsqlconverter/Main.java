package ru.barsopen.plsqlconverter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

import ru.barsopen.plsqlconverter.ast.DerivedSqlPrinter;
import ru.barsopen.plsqlconverter.ast.transforms.AstParser;
import ru.barsopen.plsqlconverter.ast.transforms.AstPrinter;
import ru.barsopen.plsqlconverter.ast.transforms.AstSerializer;
import ru.barsopen.plsqlconverter.ast.transforms.AstUtil;
import ru.barsopen.plsqlconverter.ast.transforms.AstXml;
import ru.barsopen.plsqlconverter.ast.transforms.DatatypeConversionTransformer;
import ru.barsopen.plsqlconverter.ast.transforms.OracleOuterJoinTransformer;
import ru.barsopen.plsqlconverter.ast.transforms.PackageConversionTransformer;
import ru.barsopen.plsqlconverter.ast.transforms.ParseResult;
import ru.barsopen.plsqlconverter.ast.transforms.PrintResult;
import ru.barsopen.plsqlconverter.ast.transforms.ProcedurePerformConversionTransformer;
import ru.barsopen.plsqlconverter.ast.transforms.ProcedureToFunctionConversionTransformer;
import ru.barsopen.plsqlconverter.ast.typed.*;
import ru.barsopen.plsqlconverter.util.TokenCounter;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		CliOptions options = CliOptions.parseCliOptions(args);
		if (options.help) {
			CliOptions.printUsage(System.out);
			return;
		}
		if (options.allPackages) {
			parseByParts(options.inputSqlPath, options.validateReparse, options.limitAllPackages);
			return;
		}
		
		ParseResult parseResult = null;
		long parseStartTime = System.currentTimeMillis();
		
		if (options.inputSqlPath != null) {
			String inputContent = new String(Files.readAllBytes(Paths.get(options.inputSqlPath)), Charset.forName("UTF-8"));
			parseResult = AstParser.parseTreeFromString(inputContent, false, options.tree_type);
		} else if (options.inputXmlPath != null) {
			String inputContent = new String(Files.readAllBytes(Paths.get(options.inputXmlPath)), Charset.forName("UTF-8"));
			parseResult = AstXml.xmlToAst(AstXml.stringToXml(inputContent));
		} else if (options.inputSerialiedPath != null) {
			try (FileInputStream stream = new FileInputStream(options.inputSerialiedPath)) {
				parseResult = AstSerializer.deserializeAst(stream);
			}
		} else {
			System.err.println("No --input-sql or --input-xml or --input-serialized specified");
		}
		System.err.printf("Parse took %f seconds\n", (System.currentTimeMillis() - parseStartTime) / 1000.0);
		
		if (parseResult.lexerErrors.size() > 0 || parseResult.parserErrors.size() > 0) {
			System.exit(1);
		}
		
		org.antlr.runtime.tree.Tree theTree = parseResult.tree;
		
		if (options.validateReparse) {
			String errorMessage = validatePrintedTreeMatchesParsedTree(theTree, options.validateReparseOutputAstPath, options.tree_type);
			
			if (errorMessage != null) {
				System.err.printf("Error comparing after print: %s\n", errorMessage);
				System.exit(1);
			}
		}
		
		if (options.convert) {
			OracleOuterJoinTransformer.isDebugEnabled = options.debug;
			OracleOuterJoinTransformer.transformAllQueries(theTree);
			PackageConversionTransformer.transformAllPackages(theTree);
			DatatypeConversionTransformer.transformAll(theTree);
			ProcedureToFunctionConversionTransformer.transformAll(theTree);
			ProcedurePerformConversionTransformer.transformAll(theTree);
		}
		
		if (!options.splitLargeScript) {
			if (options.outputAstPath != null) {
				String str = AstPrinter.prettyPrint(theTree);
	
				if (options.outputAstPath.equals("-")) {
					System.out.println(str);
				} else {
					try (PrintStream out = new PrintStream(new FileOutputStream(options.outputAstPath))) {
					    out.println(str);
					}
				}
			}
			
			if (options.outputSqlPath != null) {
				PrintResult printResult;
				if (options.usePgSql) {
					printResult = AstPrinter.printTreeToPostgresqlString(theTree, options.tree_type);
				} else {
					printResult = AstPrinter.printTreeToOracleString(theTree, options.tree_type);
				}
				
				if (options.outputSqlPath.equals("-")) {
					System.out.println(printResult.text);
				} else {
					try (PrintStream out = new PrintStream(new FileOutputStream(options.outputSqlPath))) {
					    out.println(printResult.text);
					}
				}
			}
			
			if (options.outputXmlPath != null) {
				String astXml = AstXml.xmlToString(AstXml.astToXml(parseResult.tokens, theTree));
				
				if (options.outputXmlPath.equals("-")) {
					System.out.println(astXml);
				} else {
					try (PrintStream out = new PrintStream(new FileOutputStream(options.outputXmlPath))) {
					    out.println(astXml);
					}
				}
			}
			
			if (options.outputSerializedPath != null) {
				try (FileOutputStream stream = new FileOutputStream(options.outputSerializedPath)) {
					AstSerializer.serialiaseAst(stream, parseResult.tokens, theTree);
				}
			}
		} else {
			if (theTree.getType() != PLSQLParser.SQL_SCRIPT) {
				System.err.println("Parsed tree is not a SQL script");
			}
			
			sql_script script = parser.parsesql_script(theTree);
			List<unit_statement> statements = new ArrayList<unit_statement>();
			for (sql_script_item item: script.sql_script_items) {
				if (item instanceof unit_statement) {
					unit_statement statement = (unit_statement)item;
					statements.add(statement);
					if (options.limitAllPackages != null && statements.size() == options.limitAllPackages) {
						break;
					}
				}
			}
			
			int idx = 0;
			for (unit_statement statement: statements) {
				++idx;
				//alter_function, alter_package, alter_procedure, alter_sequence, alter_trigger,
				// alter_type, create_function_body, create_procedure_body, create_package, create_sequence,
				// create_trigger, create_type, drop_function, drop_package, drop_procedure,
				// drop_sequence, drop_trigger, drop_type
				
				String name = getStatementName(statement);
				
				Tree newScript = new org.antlr.runtime.tree.CommonTree(new org.antlr.runtime.CommonToken(PLSQLParser.SQL_SCRIPT));
				newScript.addChild(statement.unparse());
				
				if (options.splitLargeScriptOutputAst) {
					String str = AstPrinter.prettyPrint(newScript);

					String path = Paths.get(options.splitLargeScriptOutputDir, String.format("%s_%d.ast.txt", name, idx)).toString();
					try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
					    out.println(str);
					}
				}
				
				if (options.splitLargeScriptOutputSql) {
					PrintResult printResult = AstPrinter.printTreeToOracleString(newScript, options.tree_type);

					String path = Paths.get(options.splitLargeScriptOutputDir, String.format("%s_%d.sql", name, idx)).toString();
					try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
					    out.println(printResult.text);
					}
				}
				
				if (options.splitLargeScriptOutputSerialied) {
					PrintResult printResult = AstPrinter.printTreeToOracleString(newScript, options.tree_type);

					String path = Paths.get(options.splitLargeScriptOutputDir, String.format("%s_%d.bin", name, idx)).toString();
					try (FileOutputStream out = new FileOutputStream(path)) {
						AstSerializer.serialiaseAst(out, new ArrayList<Token>(), newScript);
					}
				}
				
				/*if (options.outputXmlPath != null) {
					String astXml = AstXml.xmlToString(AstXml.astToXml(parseResult.tokens, theTree));

					String path = Paths.get(options.splitLargeScriptOutputDir, String.format("%d.ast.xml", idx)).toString();
					try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
					    out.println(astXml);
					}
				}*/
				
				++idx;
			}
		}
	}

	private static String getStatementName(unit_statement statement) {
		String result;
		if (statement instanceof create_function_body) {
			create_function_body b = (create_function_body)statement;
			result = b.function_name.ids.get(b.function_name.ids.size() - 1).value;
		} else if (statement instanceof create_procedure_body) {
			create_procedure_body b = (create_procedure_body)statement;
			result = b.procedure_name.ids.get(b.procedure_name.ids.size() - 1).value;
		} else if (statement instanceof create_package_spec) {
			create_package_spec p = (create_package_spec)statement;
			result = p.package_name.ids.get(p.package_name.ids.size() - 1).value;
		} else if (statement instanceof create_package_body) {
			create_package_body p = (create_package_body)statement;
			result = p.package_name.ids.get(p.package_name.ids.size() - 1).value;
		} else if (statement instanceof create_sequence) {
			result = "seq";
		} else if (statement instanceof create_trigger) {
			result = "trigger";
		} else if (statement instanceof create_type) {
			result = "type";
		} else {
			return "other";
		}
		result = AstUtil.normalizeId(result).toLowerCase();
		return result;
	}

	private static void parseByParts(String path, boolean validateReparse, Integer limit) throws Exception {
		byte[] contentBytes = Files.readAllBytes(Paths.get(path));
		String contentString = new String(contentBytes, Charset.forName("UTF-8"));
		List<String> parts = splitContent(contentString);
		List<String> parseFailures = new ArrayList<String>();
		List<String> parseFailureBodies = new ArrayList<String>();
		List<String> printFailures = new ArrayList<String>();
		List<String> printFailureBodies = new ArrayList<String>();
		List<String> reparseFailures = new ArrayList<String>();
		List<String> reparseFailureBodies = new ArrayList<String>();
		List<String> successes = new ArrayList<String>();
		TokenCounter ctr = new TokenCounter();
		long ms_start_all = System.currentTimeMillis();
		int partIdx = 0;
		for (String part : parts) {
			if (limit != null && partIdx >= limit) {
				break;
			}
			String header = part.substring(0, part.indexOf('\n'));
			System.out.print(header);

			long ms_start_1 = System.currentTimeMillis();
			ParseResult parseResult = AstParser.parseTreeFromString(part, false, "sql_script");
			String printedTree = "";
			long ms_end_1 = System.currentTimeMillis();
			System.out.printf(" %f s\n", (ms_end_1 - ms_start_1) / 1000.0);
			boolean failure = parseResult.lexerErrors.size() > 0 || parseResult.parserErrors.size() > 0;
			if (failure) {
				System.out.println("PARSE FAIL");
				parseFailures.add(header);
				parseFailureBodies.add(part);
			} else {
				ctr.addTree(parseResult.tree);
				
				boolean is_tree_walked;
				try {
					PrintResult printResult = AstPrinter.printTreeToOracleString(parseResult.tree, "sql_script");
					if (printResult.printErrors.size() > 0) {
						is_tree_walked = false;
					} else {
						printedTree = printResult.text;
						is_tree_walked = true;
					}
				} catch (Exception ex) {
					is_tree_walked = false;
					ex.printStackTrace();
				}
				if (is_tree_walked) {
					String compareResult = null;
					if (validateReparse) {
						compareResult = validatePrintedTreeMatchesParsedTree(parseResult.tree, "sql_script");
					}
					if (compareResult != null) {
						System.out.printf("Print&Reparse failed: %s\n", compareResult);
						reparseFailures.add(header);
						reparseFailureBodies.add(part);
					} else {
						successes.add(header);
					}
				} else {
					System.out.println("PRINT FAIL");
					printFailures.add(header);
					printFailureBodies.add(part);
				}
			}
			
			org.antlr.runtime.tree.Tree tree = parseResult.tree;
			String str;
			str = AstPrinter.prettyPrint(tree);
			
			String name = tryGuessPackageName(tree);
			if (name == null) {
				name = "unguessed";
			}

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/parsetrees/%d_%s_input.txt", partIdx, name)))) {
			    out.print(part);
			}

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/parsetrees/%d_%s_%s.txt", partIdx, name, failure ? "failure" : "success")))) {
			    out.print(str);
			}

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/parsetrees/%d_%s_printed.txt", partIdx, name)))) {
			    out.print(printedTree);
			}
			
			++partIdx;
		}
		long ms_end_all = System.currentTimeMillis();
		System.out.printf("Total time: %f s\n", (ms_end_all - ms_start_all) / 1000.0);
		
		System.out.printf("%d succeeded, %d parse failed, %d print failed, %d reparse failed\n", successes.size(), parseFailures.size(), printFailures.size(), reparseFailures.size());
		int idx = 0;
		System.out.println("Parse failures:");
		for (int i = 0; i < parseFailures.size(); ++i) {
			System.out.printf("%d %s\n", idx, parseFailures.get(i));

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/failure%d.txt", idx)))) {
			    out.print(parseFailureBodies.get(i));
			}
			++idx;
		}
		System.out.println("Print failures:");
		for (int i = 0; i < printFailures.size(); ++i) {
			System.out.printf("%d %s\n", idx, printFailures.get(i));

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/failure%d.txt", idx)))) {
			    out.print(printFailureBodies.get(i));
			}
			++idx;
		}
		System.out.println("Reparse failures:");
		for (int i = 0; i < reparseFailures.size(); ++i) {
			System.out.printf("%d %s\n", idx, reparseFailures.get(i));

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("workdir/failure%d.txt", idx)))) {
			    out.print(reparseFailureBodies.get(i));
			}
			++idx;
		}
		if (limit == null) {
			try (PrintStream out = new PrintStream(new FileOutputStream("workdir/token_stats.txt"))) {
				printTokenStats(ctr.getOccurences(), out);
			}
		}
	}

	private static String tryGuessPackageName(Tree tree) {
		if (tree.getType() == PLSQLParser.SQL_SCRIPT) {
			tree = tree.getChild(0);
		}
		if (tree.getType() == PLSQLParser.CREATE_PACKAGE_SPEC || tree.getType() == PLSQLParser.CREATE_PACKAGE_BODY) {
			for (int i = 0; i < tree.getChildCount(); ++i) {
				Tree childNode = tree.getChild(i);
				if (childNode.getType() == PLSQLParser.PACKAGE_NAME) {
					Tree nameNode = childNode.getChild(childNode.getChildCount() - 1);
					if (nameNode.getType() == PLSQLParser.ID) {
						String maybeQuotedName = nameNode.getText();
						if (maybeQuotedName.charAt(0) == '\"') {
							maybeQuotedName = maybeQuotedName.substring(1, maybeQuotedName.length() - 1);
						}
						if (tree.getType() == PLSQLParser.CREATE_PACKAGE_SPEC) {
							maybeQuotedName += "_spec";
						}
						return maybeQuotedName;
					}
				}
			}
		}
		return null;
	}

	private static List<String> splitContent(String contentString) {
		ArrayList<String> result = new ArrayList<String>();
		String pattern = "CREATE OR REPLACE";
		int idx = contentString.indexOf(pattern);
		while (idx < contentString.length() && idx >= 0) {
			int next = contentString.indexOf(pattern, idx + pattern.length());
			if (next == -1) {
				next = contentString.length();
			}
			String item = contentString.substring(idx, next);
			result.add(item);
			idx = next;
		}
		return result;
	}
	
	private static void printTokenStats(final Map<Integer, Integer> occurences, PrintStream out) {
		List<Integer> keys = new ArrayList<Integer>(occurences.keySet());
		Collections.sort(keys, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return occurences.get(o2) - occurences.get(o1);
			}
		});
		for (int key : keys) {
			out.printf("%s -> %d\n", AstUtil.tokenNames[key], occurences.get(key));
		}
	}
	
	private static String validatePrintedTreeMatchesParsedTree(Tree tree, String treeType) throws Exception {
		return validatePrintedTreeMatchesParsedTree(tree, null, treeType);
	}
	
	private static String validatePrintedTreeMatchesParsedTree(Tree tree, String reprintedTreeDestination, String treeType) throws Exception {
		PrintResult printResult = AstPrinter.printTreeToOracleString(tree, treeType);
		if (printResult.printErrors.size() > 0) {
			return "Printer errors";
		}
		if (printResult.text.contains("not implemented: ")) {
			return "Printed text contains 'not implemented: '";
		}
		ParseResult reparseResult = AstParser.parseTreeFromString(printResult.text, false, treeType);
		if (reparseResult.lexerErrors.size() > 0) {
			return "Lexer errors (on printed tree)";
		}
		if (reparseResult.parserErrors.size() > 0) {
			return "Parser errors (on printed tree)";
		}
		PrintResult reprintResult = AstPrinter.printTreeToOracleString(reparseResult.tree, treeType);
		if (reprintedTreeDestination != null) {
			if (reprintedTreeDestination.equals("-")) {
				System.out.println(reprintResult.text);
			} else {
				try (PrintStream out = new PrintStream(new FileOutputStream(reprintedTreeDestination))) {
				    out.println(reprintResult.text);
				}
			}
		}
		if (reprintResult.printErrors.size() > 0) {
			return "Printer errors (on printed tree)";
		}
		/*
		String text1 = printResult.text.replace("\r\n", "\n").replace("\r", "\n");
		String text2 = reprintResult.text.replace("\r\n", "\n").replace("\r", "\n");
		if (!text1.equals(text2)) {
			TextPos mismatchPos = getStringMismatchIndex(text1, text2);
			Character c1 = mismatchPos.index < text1.length() ? text1.charAt(mismatchPos.index) : null;
			Character c2 = mismatchPos.index < text2.length() ? text2.charAt(mismatchPos.index) : null;
			return String.format("Texts mismatch at %d:%d '%s' <> '%s'", mismatchPos.line, mismatchPos.col, c1, c2);
		}*/
		Tree[] mismatchedTrees = getMismatchedTreeNodes(tree, reparseResult.tree);
		if (mismatchedTrees == null) {
			return null;
		}

		String token1Description = getTreeNodeDescription(mismatchedTrees[0]);
		String token2Description = getTreeNodeDescription(mismatchedTrees[1]);
		
		String result = String.format("Mismatch: %s <> %s", token1Description, token2Description);

		return result;
	}
	
	static class TextPos {
		public int line;
		public int col;
		public int index;
	}
	
	private static TextPos getStringMismatchIndex(String s1, String s2) {
		int n = Math.max(s1.length(), s2.length());
		int line = 1;
		int col = 1;
		for (int i = 0; i < n; ++i) {
			if (i >= s1.length() || i >= s2.length() || s1.charAt(i) != s2.charAt(i)) {
				TextPos result = new TextPos();
				result.line = line;
				result.col = col;
				result.index = i;
				return result;
			}
			if (s1.charAt(i) == '\n') {
				++line;
				col = 1;
			} else {
				++col;
			}
		}
		return null;
	}

	private static String getTreeNodeDescription(Tree tree) {
		if (tree == null) {
			return "<missing>";
		}
		String result = String.format("%s '%s' at %d:%d", AstUtil.tokenNames[tree.getType()], tree.getText(), tree.getLine(), tree.getCharPositionInLine());
		return result;
	}

	private static Tree[] getMismatchedTreeNodes(Tree t1, Tree t2) {
		if (t1.getType() != t2.getType()) {
			return new Tree[] { t1, t2 };
		}
		if (t1.getType() == PLSQLParser.ID) {
			if (!t1.getText().equals(t2.getText())) {
				return new Tree[] { t1, t2 };
			}
		}
		int n = Math.max(t1.getChildCount(), t2.getChildCount());
		for (int i = 0; i < n; ++i) {
			Tree child1 = i < t1.getChildCount() ? t1.getChild(i) : null;
			Tree child2 = i < t2.getChildCount() ? t2.getChild(i) : null;
			if (child1 == null || child2 == null) {
				return new Tree[] { child1, child2 };
			}
			Tree[] childResult = getMismatchedTreeNodes(child1, child2);
			if (childResult != null) {
				return childResult;
			}
		}
		return null;
	}
}
