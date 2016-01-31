package ru.barsopen.plsqlconverter.util;

import org.antlr.runtime.tree.Tree;

public class TreeWalker {
	public Tree visitNode(Tree node) {
		visitChildren(node);
		return node;
	}
	
	protected void visitChildren(Tree node) {
		Tree[] result = new Tree[node.getChildCount()];
		for (int i = 0; i < node.getChildCount(); ++i) {
			result[i] = visitNode(node.getChild(i));
		}
		for (int i = 0; i < node.getChildCount(); ++i) {
			if (result[i] != node.getChild(i)) {
				node.replaceChildren(i, i, result[i]);
			}
		}
	}
}
