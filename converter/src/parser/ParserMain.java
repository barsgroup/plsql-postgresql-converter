package parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

import br.com.porcelli.parser.plsql.PLSQLParser;

public class ParserMain {
	
	
	public static void main(String[] args) throws Exception {
		CliOptions options = parseCliOptions(args); 
		if (options.allPackages) {
			parseByParts(options.path, options.validateReparse, options.limitAllPackages);
			return;
		}
		
		String inputContent = new String(Files.readAllBytes(Paths.get(options.path)), Charset.forName("UTF-8"));
		ParseResult parseResult = parseTreeFromString(inputContent, false);
		org.antlr.runtime.tree.Tree theTree = parseResult.tree;
		String str;
		str = (theTree).toStringTree();
		str = prettyPrint(theTree);

		System.out.println(str.length() > 400 ? str.substring(0, 400) + "..." : str);
		try (PrintStream out = new PrintStream(new FileOutputStream("workdir/output.txt"))) {
		    out.print(str);
		}
		
		/*
		TokenCounter ctr = new TokenCounter();
		ctr.addTree(theTree);
		printTokenStats(ctr.getOccurences());
		*/
		
		//StringBuilder sb = new StringBuilder();
		//SqlPrinter printer = new SqlPrinter(sb);
		//printer.visitNode(theTree);
		//System.out.println(sb);
		
		PrintResult printResult = printTreeToString(theTree);
		String printed = printResult.text;

		System.out.println(printed.length() > 400 ? printed.substring(0, 400) + "..." : printed);
		try (PrintStream out = new PrintStream(new FileOutputStream("workdir/output_printed.txt"))) {
		    out.print(printed);
		}
		
		String errorMessage = validatePrintedTreeMatchesParsedTree(inputContent, "workdir/output_reprinted.txt");
		
		if (errorMessage != null) {
			System.out.printf("Error comparing after print: %s\n", errorMessage);
		} else {
			System.out.println("Tree matches reparsed tree");
		}
	}
	
	static class CliOptions {
		boolean allPackages;
		boolean validateReparse;
		Integer limitAllPackages;
		String path;
	}
	
	private static CliOptions parseCliOptions(String[] args) {
		CliOptions result = new CliOptions();
		int i = 0;
		while (i < args.length) {
			String arg = args[i];
			++i;
			switch (arg) {
			case "--all-packages": result.allPackages = true; break;
			case "--validate-reparse": result.validateReparse = true; break;
			case "--no-validate-reparse": result.validateReparse = false; break;
			case "--limit-all-packages": result.limitAllPackages = Integer.valueOf(args[i]); ++i; break;
			default: result.path = arg;
			}
		}
		return result;
	}

	static class PrintResult {
		public List<RecognitionException> printErrors;
		public String text;
	}

	private static PrintResult printTreeToString(org.antlr.runtime.tree.Tree theTree)
			throws IOException, RecognitionException {
		DerivedSqlPrinter printer = new DerivedSqlPrinter(new CommonTreeNodeStream(theTree));
		
		try (InputStream templateInputStream = ParserMain.class.getClassLoader().getResourceAsStream("parser/PLSQLPrinterTemplates.stg")) {
			StringTemplateGroup templateGroup = new StringTemplateGroup(new InputStreamReader(templateInputStream, Charset.forName("UTF-8")), AngleBracketTemplateLexer.class);
			printer.setTemplateLib(templateGroup);
		}
		String printed = printer.sql_script().st.toString();
		PrintResult result = new PrintResult();
		result.printErrors = printer.errors;
		result.text = printed;
		return result;
	}
	
	static class ParseResult {
		public List<RecognitionException> lexerErrors;
		public List<RecognitionException> parserErrors;
		public Tree tree;
	}

	private static ParseResult parseTreeFromString(String inputContent, boolean printTokens) throws RecognitionException {
		ANTLRStringStream input = new ANTLRStringStream(inputContent);
		DerivedSqlLexer l = new DerivedSqlLexer(input);
		CommonTokenStream cts = new CommonTokenStream(l);
		
		if (printTokens)
		{
			cts.fill();
			List<? extends Token> tokens = cts.getTokens();
			DerivedSqlParser p = new DerivedSqlParser(cts);
			String[] tokenNames = p.getTokenNames();
			for (Token t: tokens) {
				int type = t.getType();
				if (type != Token.EOF && t.getChannel() != Token.HIDDEN_CHANNEL) {
					String s = tokenNames[type];
					String tokenText = t.getText();
					System.out.printf("%s '%s' %d\n", s, tokenText, t.getChannel());
				}
			}
			System.exit(0);
		}
		DerivedSqlParser p = new DerivedSqlParser(cts);
		Object tree = p.sql_script().getTree();
	
		org.antlr.runtime.tree.Tree theTree = (org.antlr.runtime.tree.Tree)tree;
		ParseResult result = new ParseResult();
		result.tree = theTree;
		result.lexerErrors = l.errors;
		result.parserErrors = p.errors;
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
		//int times = 0;
		int partIdx = 0;
		for (String part : parts) {
			if (limit != null && partIdx >= limit) {
				break;
			}
			//if (times > 100) {
			//	break;
			//}
			//++times;
			String header = part.substring(0, part.indexOf('\n'));
			System.out.print(header);

			long ms_start_1 = System.currentTimeMillis();
			ParseResult parseResult = parseTreeFromString(part, false);
			String printedTree = "";
			long ms_end_1 = System.currentTimeMillis();
			System.out.printf(" %f s\n", (ms_end_1 - ms_start_1) / 1000.0);
			boolean failure = parseResult.lexerErrors.size() > 0 || parseResult.parserErrors.size() > 0;
			if (failure) {
				System.out.println("PARSE FAIL");
				parseFailures.add(header);
				parseFailureBodies.add(part);
				/*for (RecognitionException ex: p.errors) {
					System.out.println(ex.toString());
					System.out.println(p.getErrorHeader(ex) + ":" + p.getErrorMessage(ex, new String[0]));
				}*/
			} else {
				ctr.addTree(parseResult.tree);
				
				boolean is_tree_walked;
				try {
					DerivedSqlPrinter printer = new DerivedSqlPrinter(new CommonTreeNodeStream(parseResult.tree));
					try (InputStream templateInputStream = ParserMain.class.getClassLoader().getResourceAsStream("parser/PLSQLPrinterTemplates.stg")) {
						StringTemplateGroup templateGroup = new StringTemplateGroup(new InputStreamReader(templateInputStream, Charset.forName("UTF-8")), AngleBracketTemplateLexer.class);
						printer.setTemplateLib(templateGroup);
					}
					StringTemplate st = printer.sql_script().st;
					if (printer.errors.size() > 0) {
						is_tree_walked = false;
					} else {
						printedTree = st.toString();
						is_tree_walked = true;
					}
				} catch (Exception ex) {
					is_tree_walked = false;
					ex.printStackTrace();
				}
				if (is_tree_walked) {
					String compareResult = null;
					if (validateReparse) {
						compareResult = validatePrintedTreeMatchesParsedTree(parseResult.tree);
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
			str = prettyPrint(tree);
			
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

	static String prettyPrint(org.antlr.runtime.tree.Tree tree) {
		StringBuilder sb = new StringBuilder();
		prettyPrint(tree, sb, 0);
		return sb.toString();
	}

	static void printIndent(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; ++i) {
			sb.append("  ");
		}
	}
	
	private static void prettyPrint(org.antlr.runtime.tree.Tree tree, StringBuilder sb, int indent) {
		sb.append('(');
		String nodeText = prettyPrintNodeTag(tree);
		sb.append(nodeText);
		if (tree.getChildCount() == 1 && tree.getChild(0).getChildCount() == 0) {
			sb.append(" ");
			org.antlr.runtime.tree.Tree childNode = tree.getChild(0);
			String childNodeText = prettyPrintNodeTag(childNode);
			sb.append(childNodeText);
		} else {
			for (int i = 0; i < tree.getChildCount(); ++i) {
				org.antlr.runtime.tree.Tree childNode = tree.getChild(i);
				sb.append("\n");
				printIndent(sb, indent + 1);
				if (childNode.getChildCount() == 0) {
					String childNodeText = prettyPrintNodeTag(childNode);
					sb.append(childNodeText);
				} else {
					prettyPrint(childNode, sb, indent + 1);
				}
			}
		}
		sb.append(")");
	}
	
	private static String prettyPrintNodeTag(Tree tree) {
		int ttype = tree.getType();
		String tokenName = ttype >= 0 && ttype < tokenNames.length ? tokenNames[ttype] : "";
		String text = tree.getText();
		if (tokenName.equals(text)) {
			return tokenName;
		} else {
			//int ntype = Arrays.asList(PLSQLParser.tokenNames).indexOf(tree.getText());
			return String.format("%s[%s]", tokenName, text);
		}
	}

	String sql = "";
	
	static String[] tokenNames = getTokenNames();
	
	static String[] getTokenNames() {
		Field[] fields = PLSQLParser.class.getDeclaredFields();
		Map<Integer, String> tokenNamesMap = new HashMap<Integer, String>();
		int maxTokenValue = 0;
		for (Field field: fields) {
			int mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isFinal(mod) && field.getType() == int.class) {
				String name = field.getName();
				int value;
				try {
					value = field.getInt(null);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				tokenNamesMap.put(value, name);
				maxTokenValue = Math.max(maxTokenValue, value);
			}
		}
		String[] result = new String[maxTokenValue + 1];
		for (int i = 0; i < maxTokenValue; ++i) {
			if (tokenNamesMap.containsKey(i)) {
				result[i] = tokenNamesMap.get(i);
			} else {
				result[i] = "<none>";
			}
		}
		return result;
	}
	
	private static void printTokenStats(final Map<Integer, Integer> occurences) {
		printTokenStats(occurences, System.out);
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
			out.printf("%s -> %d\n", tokenNames[key], occurences.get(key));
		}
	}
	
	private static String validatePrintedTreeMatchesParsedTree(Tree tree) throws Exception {
		return validatePrintedTreeMatchesParsedTree(tree, null);
	}
	
	private static String validatePrintedTreeMatchesParsedTree(Tree tree, String reprintedTreeDestination) throws Exception {
		PrintResult printResult = printTreeToString(tree);
		if (printResult.printErrors.size() > 0) {
			return "Printer errors";
		}
		if (printResult.text.contains("not implemented: ")) {
			return "Printed text contains 'not implemented: '";
		}
		ParseResult reparseResult = parseTreeFromString(printResult.text, false);
		if (reparseResult.lexerErrors.size() > 0) {
			return "Lexer errors (on printed tree)";
		}
		if (reparseResult.parserErrors.size() > 0) {
			return "Parser errors (on printed tree)";
		}
		PrintResult reprintResult = printTreeToString(reparseResult.tree);
		if (reprintedTreeDestination != null) {
			try (PrintStream out = new PrintStream(new FileOutputStream(reprintedTreeDestination))) {
			    out.print(reprintResult.text);
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
	
	private static String validatePrintedTreeMatchesParsedTree(String inputContent) throws Exception {
		return validatePrintedTreeMatchesParsedTree(inputContent, null);
	}
	
	private static String validatePrintedTreeMatchesParsedTree(String inputContent, String reprintedTreeDestination) throws Exception {
		ParseResult parseResult = parseTreeFromString(inputContent, false);
		if (parseResult.lexerErrors.size() > 0) {
			return "Lexer errors";
		}
		if (parseResult.parserErrors.size() > 0) {
			return "Parser errors";
		}
		return validatePrintedTreeMatchesParsedTree(parseResult.tree, reprintedTreeDestination);
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
		String result = String.format("%s '%s' at %d:%d", tokenNames[tree.getType()], tree.getText(), tree.getLine(), tree.getCharPositionInLine());
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
