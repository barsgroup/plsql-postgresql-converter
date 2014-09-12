package parser.ast.transforms;

import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;

import parser.ast.DerivedSqlLexer;
import parser.ast.DerivedSqlParser;
import parser.util.ReflectionUtil;

public class AstParser {

	public static ParseResult parseTreeFromString(String inputContent, boolean printTokens, String treeType) throws RecognitionException {
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
		Object tree = ReflectionUtil.callMethod(ReflectionUtil.callMethod(p, treeType), "getTree");
	
		org.antlr.runtime.tree.Tree theTree = (org.antlr.runtime.tree.Tree)tree;
		ParseResult result = new ParseResult();
		result.tree = theTree;
		result.lexerErrors = l.errors;
		result.parserErrors = p.errors;
		return result;
	}
}
