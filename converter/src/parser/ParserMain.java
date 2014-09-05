package parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BufferedTokenStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

import br.com.porcelli.parser.plsql.PLSQLLexer;
import br.com.porcelli.parser.plsql.PLSQLParser;
import br.com.porcelli.parser.plsql.PLSQLParser.sql_script_return;
import br.com.porcelli.parser.plsql.PLSQLParser_PLSQL_DMLParser.*;
import br.com.porcelli.parser.plsql.PLSQLParser_PLSQLCommons.*;
import br.com.porcelli.parser.plsql.PLSQLParser.*;

public class ParserMain {
	public static void main(String[] args) throws Exception {
		if (true) {
			parseByParts();
			return;
		}
		
		ANTLRStringStream input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/ora2pg/wb/easy.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/ora2pg/wb/medm.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/ora2pg/wb/hard.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/ora2pg/wb/tables.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/types.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/packages-excerpt.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/packages-excerpt-2.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/packages-excerpt-3.sql");
		input = new ANTLRFileStream("failure1.txt");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/packages-all-mod1.sql");
		//input = new ANTLRFileStream("/home/dvk/bars/misc/2014.09.03/package-broker.sql");
		//input = new ANTLRStringStream("c%ROWCOUNT");
		//input = new ANTLRStringStream("f(1) := 1;");
		//input = new ANTLRStringStream("paARR.Delete");
		//input = new ANTLRStringStream("CREATE OR RePLaCE TYPE \"DEV\".\"DPI_CL_REP_PRESCS\" as table of DPI_TP_REP_PRESCS;");
		PLSQLLexer l = new PLSQLLexer(input);
		CommonTokenStream cts = new CommonTokenStream(l);
		
		if (false)
		{
			cts.fill();
			List<? extends Token> tokens = cts.getTokens();
			PLSQLParser p = new PLSQLParser(cts);
			String[] tokenNames = p.getTokenNames();
			for (Token t: tokens) {
				int type = t.getType();
				if (type != Token.EOF) {
					String s = tokenNames[type];
					String tokenText = t.getText();
					System.out.printf("%s %s\n", s, tokenText);
				}
			}
			return;
		}
		PLSQLParser p = new PLSQLParser(cts);
		//expression_return r = p.expression();
		//general_element_return r = p.general_element();
		//create_package_return r = p.create_package();
		//seq_of_statements_return r = p.seq_of_statements();
		//statement_return r = p.statement();
		//body_return r = p.body();
		//sql_statement_return r = p.sql_statement();
		//loop_statement_return r = p.loop_statement();
		//standard_function_return r = p.standard_function();
		//pipe_row_statement_return r = p.pipe_row_statement();
		//create_type_return r = p.create_type();
		//create_procedure_body_return r = p.create_procedure_body();
		//query_block_return r = p.query_block();
		//sql_script_return r = p.sql_script();
		//Object tree = p.pragma_declaration().getTree();
		//Object tree = p.create_procedure_body().getTree();
		Object tree = p.sql_script().getTree();
	
		org.antlr.runtime.tree.Tree theTree = (org.antlr.runtime.tree.Tree)tree;
		String str;
		str = (theTree).toStringTree();
		str = prettyPrint(theTree);

		System.out.println(str.length() > 400 ? str.substring(0, 400) + "..." : str);
		try (PrintStream out = new PrintStream(new FileOutputStream("output.txt"))) {
		    out.print(str);
		}
	}
	
	private static void parseByParts() throws Exception {
		byte[] contentBytes = Files.readAllBytes(Paths.get("/home/dvk/bars/misc/2014.09.03/packages-all-mod1.sql"));
		String contentString = new String(contentBytes, Charset.forName("UTF-8"));
		List<String> parts = splitContent(contentString);
		List<String> failures = new ArrayList<String>();
		List<String> failureBodies = new ArrayList<String>();
		List<String> successes = new ArrayList<String>();
		for (String part : parts) {
			String header = part.substring(0, part.indexOf('\n'));
			System.out.println(header);

			ANTLRStringStream input = new ANTLRStringStream(part);
			PLSQLLexer l = new PLSQLLexer(input);
			CommonTokenStream cts = new CommonTokenStream(l);
			DerivedSqlParser p = new DerivedSqlParser(cts);
			sql_script_return r = p.sql_script();
			if (p.errors.size() > 0) {
				System.out.println("FAIL");
				failures.add(header);
				failureBodies.add(part);
				/*for (RecognitionException ex: p.errors) {
					System.out.println(ex.toString());
					System.out.println(p.getErrorHeader(ex) + ":" + p.getErrorMessage(ex, new String[0]));
				}*/
			} else {
				successes.add(header);
				
				org.antlr.runtime.tree.Tree tree = (org.antlr.runtime.tree.Tree)r.getTree();
				String str;
				str = prettyPrint(tree);

				try (PrintStream out = new PrintStream(new FileOutputStream(String.format("parsetrees/success%d.txt", successes.size() - 1)))) {
				    out.print(str);
				}
			}
		}
		
		System.out.printf("%d succeeded, %d failed\n", successes.size(), failures.size());
		System.out.println("Failures:");
		for (int i = 0; i < failures.size(); ++i) {
			System.out.printf("%d %s\n", i, failures.get(i));

			try (PrintStream out = new PrintStream(new FileOutputStream(String.format("failure%d.txt", i)))) {
			    out.print(failureBodies.get(i));
			}
		}
		
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
		if (true) {
			sb.append(tree.getText());
		} else {
			int ttype = tree.getType();
			String tokenName = ttype >= 0 && ttype < PLSQLParser.tokenNames.length ? PLSQLParser.tokenNames[ttype] : "";
			int ntype = Arrays.asList(PLSQLParser.tokenNames).indexOf(tree.getText());
			sb.append(String.format("%d %d %s[%s]", ttype, ntype, tokenName, tree.getText()));
		}
		if (tree.getChildCount() == 1 && tree.getChild(0).getChildCount() == 0) {
			sb.append(" ");
			org.antlr.runtime.tree.Tree childNode = tree.getChild(0);
			sb.append(childNode.getText());
		} else {
			for (int i = 0; i < tree.getChildCount(); ++i) {
				org.antlr.runtime.tree.Tree childNode = tree.getChild(i);
				sb.append("\n");
				printIndent(sb, indent + 1);
				if (childNode.getChildCount() == 0) {
					sb.append(childNode.getText());
				} else {
					prettyPrint(childNode, sb, indent + 1);
				}
			}
		}
		sb.append(")");
	}
	
	String sql = "";
}
