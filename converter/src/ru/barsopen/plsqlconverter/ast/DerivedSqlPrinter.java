package ru.barsopen.plsqlconverter.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.TreeNodeStream;

import ru.barsopen.plsqlconverter.PLSQLPrinter;

public class DerivedSqlPrinter extends PLSQLPrinter {

	public DerivedSqlPrinter(TreeNodeStream input) {
		super(input);
	}
	
	public List<RecognitionException> errors = new ArrayList<RecognitionException>();
	
	@Override
	public void reportError(RecognitionException e) {
		super.reportError(e);
		
		errors.add(e);
	}
	
}
