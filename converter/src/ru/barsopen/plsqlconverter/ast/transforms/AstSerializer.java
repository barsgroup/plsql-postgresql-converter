package ru.barsopen.plsqlconverter.ast.transforms;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class AstSerializer {
	public static void serialiaseAst(OutputStream stream, List<Token> tokens, org.antlr.runtime.tree.Tree tree) throws Exception {
		try (ObjectOutputStream out = new ObjectOutputStream(stream)) {
			out.writeObject(tokens);
			serializeTree(out, tree);
			out.flush();
		}
	}
	
	private static void serializeTree(ObjectOutputStream out, Tree tree) throws Exception {
		CommonToken t = new CommonToken(tree.getType(), tree.getText());
		out.writeObject(t);
		out.writeInt(tree.getTokenStartIndex());
		out.writeInt(tree.getTokenStopIndex());
		out.writeInt(tree.getChildCount());
		for (int i = 0; i < tree.getChildCount(); ++i) {
			serializeTree(out, tree.getChild(i));
		}
	}

	@SuppressWarnings("unchecked")
	public static ParseResult deserializeAst(InputStream stream) throws Exception {
		ParseResult result = new ParseResult();
		try (ObjectInputStream in = new ObjectInputStream(stream)) {
			result.tokens = (List<Token>)in.readObject();
			result.tree = deserializeTree(in);
		}
		result.lexerErrors = new ArrayList<RecognitionException>();
		result.parserErrors = new ArrayList<RecognitionException>();
		return result;
	}

	private static Tree deserializeTree(ObjectInputStream in) throws Exception {
		CommonToken t = (CommonToken)in.readObject();
		CommonTree result = new CommonTree(t);
		result.setTokenStartIndex(in.readInt());
		result.setTokenStopIndex(in.readInt());
		int count = in.readInt();
		for (int i = 0; i < count; ++i) {
			result.addChild(deserializeTree(in));
		}
		return result;
	}
}
