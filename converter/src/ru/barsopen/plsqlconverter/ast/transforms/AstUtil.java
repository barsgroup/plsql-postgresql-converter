package ru.barsopen.plsqlconverter.ast.transforms;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed._baseNode;
import ru.barsopen.plsqlconverter.ast.typed._visitor;
import ru.barsopen.plsqlconverter.ast.typed.id;

public class AstUtil {
	
	public static String[] tokenNames;
	public static Map<Integer, String> tokenNumber2NameMap;
	public static Map<String, Integer> tokenName2NumberMap;
	
	static
	{
		Field[] fields = PLSQLPrinter.class.getDeclaredFields();
		tokenNumber2NameMap = new HashMap<Integer, String>();
		tokenName2NumberMap = new HashMap<String, Integer>();
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
				tokenNumber2NameMap.put(value, name);
				tokenName2NumberMap.put(name, value);
				maxTokenValue = Math.max(maxTokenValue, value);
			}
		}
		tokenNames = new String[maxTokenValue + 1];
		for (int i = 0; i <= maxTokenValue; ++i) {
			if (tokenNumber2NameMap.containsKey(i)) {
				tokenNames[i] = tokenNumber2NameMap.get(i);
			} else {
				tokenNames[i] = "<none>";
			}
		}
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
	
	public static List<Tree> getChildren(Tree node) {
		List<Tree> result = new ArrayList<Tree>();
		for (int i = 0; i < node.getChildCount(); ++i) {
			result.add(node.getChild(i));
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

	public static Tree createAstNode(int type, String text, Tree... children) {
		 Tree tree = new CommonTree(new CommonToken(type, text));
		 for (Tree child: children) {
			 tree.addChild(child);
		 }
	 	return tree;
	}

	public static Tree createAstNode(int type, String text, List<Tree> children) {
		 Tree tree = new CommonTree(new CommonToken(type, text));
		 for (Tree child: children) {
			 tree.addChild(child);
		 }
	 	return tree;
	}

	public static void replaceNode(Tree node, Tree replacement) {
		Tree parent = node.getParent();
		int idx = node.getChildIndex();
		parent.replaceChildren(idx, idx, replacement);
	}

	public static void replaceNode(Tree node, List<Tree> replacement) {
		BaseTree parent = (BaseTree)node.getParent();
		int idx = node.getChildIndex();
		parent.deleteChild(idx);
		for (Tree replacementNode: replacement) {
			parent.insertChild(idx, replacementNode);
			++idx;
		}
	}

	public static void replaceNode(Tree node, Tree... replacement) {
		BaseTree parent = (BaseTree)node.getParent();
		int idx = node.getChildIndex();
		parent.deleteChild(idx);
		for (Tree replacementNode: replacement) {
			parent.insertChild(idx, replacementNode);
			++idx;
		}
	}
	
	static CommonTreeAdaptor adaptor = new CommonTreeAdaptor();
	
	public static Tree cloneTree(Tree node) {
		return (Tree)adaptor.dupTree(node);
	}

	public static <T> List<T> getDescendantsOfType(
			_baseNode node, final Class<T> klass) {

		final List<T> elts = new ArrayList<T>();
		node._walk(new _visitor() {
			public void visit(Tree nonNode) {
			}
			@SuppressWarnings("unchecked")
			@Override
			public boolean enter(_baseNode node) {
				if (klass.isAssignableFrom(node.getClass())) {
					elts.add((T)node);
				}
				return true;
			}
			@Override
			public void leave(_baseNode node) {
			}
		});
		return elts;
	}
	
	public static String stringJoin(String separator, List<String> parts) {
		StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < parts.size(); ++i) {
	    	if (i > 0) {
    			sb.append(separator);
	    	}
	    	sb.append(parts.get(i));
	    }
	    return sb.toString();
	}

	public static String getLastIdString(List<id> ids) {
		id lastId = ids.get(ids.size() - 1);
		String lastIdString = normalizeId(lastId.value);
		return lastIdString;
	}

}
