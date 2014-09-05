package parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;

import br.com.porcelli.parser.plsql.PLSQLLexer;

public class DerivedSqlLexer extends PLSQLLexer {

	public DerivedSqlLexer(CharStream input) {
		super(input);
	}
	
	public List<RecognitionException> errors = new ArrayList<RecognitionException>();
	
	@Override
	public void reportError(RecognitionException e) {
		super.reportError(e);
		
		errors.add(e);
	}
}
