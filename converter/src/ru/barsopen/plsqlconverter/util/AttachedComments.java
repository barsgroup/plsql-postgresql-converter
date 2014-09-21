package ru.barsopen.plsqlconverter.util;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;

public class AttachedComments {
	public List<Token> before = new ArrayList<Token>();
	public List<Token> after = new ArrayList<Token>();
	public List<Token> inside = new ArrayList<Token>();
	
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
