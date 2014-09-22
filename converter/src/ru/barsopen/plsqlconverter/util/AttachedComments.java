package ru.barsopen.plsqlconverter.util;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.PLSQLPrinter;

public class AttachedComments {
	public List<Token> before = new ArrayList<Token>();
	public List<Token> after = new ArrayList<Token>();
	public List<Token> inside = new ArrayList<Token>();
	
	public static AttachedComments fromTree(Tree commentsTree) {
		AttachedComments result = new AttachedComments();
		Tree cbefore = commentsTree.getChild(0);
		Tree cinside = commentsTree.getChild(1);
		Tree cafter = commentsTree.getChild(2);
		if (cbefore.getText() != null && cbefore.getText().length() > 0) {
			result.before.add(new CommonToken(PLSQLPrinter.COMMENT, cbefore.getText()));
		}
		if (cinside.getText() != null && cinside.getText().length() > 0) {
			result.inside.add(new CommonToken(PLSQLPrinter.COMMENT, cinside.getText()));
		}
		if (cafter.getText() != null && cafter.getText().length() > 0) {
			result.after.add(new CommonToken(PLSQLPrinter.COMMENT, cafter.getText()));
		}
		return result;
	}
	
	public void add(AttachedComments other) {
		if (other != null) {
			before.addAll(other.before);
			inside.addAll(other.inside);
			after.addAll(other.after);
		}
	}
	
	public String getBefore() {
		StringBuilder sb = new StringBuilder();
		
		for (Token t: before) {
			sb.append(t.getText());
		}
		
		return sb.toString();
	}
	
	public String getAfter() {
		StringBuilder sb = new StringBuilder();
		
		for (Token t: after) {
			sb.append(t.getText());
		}
		
		return sb.toString();
	}
	
	public String getInside() {
		StringBuilder sb = new StringBuilder();
		
		for (Token t: inside) {
			sb.append(t.getText());
		}
		
		return sb.toString();
	}
}
