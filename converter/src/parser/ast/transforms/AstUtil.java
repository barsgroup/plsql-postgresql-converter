package parser.ast.transforms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import br.com.porcelli.parser.plsql.PLSQLParser;

public class AstUtil {
	
	public static String[] tokenNames = getTokenNames();
	
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

	public static Tree getChildOfType(Tree node, int type) {
		for (int i = 0; i < node.getChildCount(); ++i) {
			if (node.getChild(i).getType() == type) {
				return node.getChild(i);
			}
		}
		return null;
	}
	
	public static List<Tree> getChildrenOfType(Tree node, int type) {
		List<Tree> result = new ArrayList<Tree>();
		for (int i = 0; i < node.getChildCount(); ++i) {
			if (node.getChild(i).getType() == type) {
				result.add(node.getChild(i));
			}
		}
		return result;
	}
	
	public static List<Tree> getDescendantsOfType(Tree node, int type) {
		List<Tree> result = new ArrayList<Tree>();
		getDescendantsOfType(node, type, result);
		return result;
	}
	
	private static void getDescendantsOfType(Tree node, int type, List<Tree> result) {
		if (node.getType() == type) {
			result.add(node);
		}
		for (int i = 0; i < node.getChildCount(); ++i) {
			getDescendantsOfType(node.getChild(i), type, result);
		}
	}

	public static void assertNodeType(Tree tree, int type) {
		if (tree.getType() != type) {
			String expectedTokenType = tokenNames[type];
			String actualTokenType = tokenNames[tree.getType()];
			String message = String.format("Unexpected node type: expected '%s' got '%s'", expectedTokenType, actualTokenType);
			throw new RuntimeException(message);
		}
	}
	
	public static void assertThat(boolean condition) {
		assertThat(condition, "Assertion failed");
	}
	
	public static void assertThat(boolean condition, String messageIfFailed) {
		if (!condition) {
			throw new RuntimeException(messageIfFailed);
		}
	}

	public static String normalizeId(String idString) {
		if (idString.startsWith("\"")) {
			idString = idString.substring(1, idString.length() - 1);
		}
		idString = idString.toUpperCase();
		return idString;
	}

	public static Tree createAstNode(int type, Tree... children) {
		 Tree tree = new CommonTree(new CommonToken(type, tokenNames[type]));
		 for (Tree child: children) {
			 tree.addChild(child);
		 }
	 	return tree;
	}

	public static Tree createAstNode(int type, List<Tree> children) {
		 Tree tree = new CommonTree(new CommonToken(type, tokenNames[type]));
		 for (Tree child: children) {
			 tree.addChild(child);
		 }
	 	return tree;
	}

}
