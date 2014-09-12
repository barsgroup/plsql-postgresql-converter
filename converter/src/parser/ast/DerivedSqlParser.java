package parser.ast;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

import br.com.porcelli.parser.plsql.PLSQLParser;

public class DerivedSqlParser extends PLSQLParser {

	public DerivedSqlParser(TokenStream input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
	
	public List<RecognitionException> errors = new ArrayList<RecognitionException>();
	
	@Override
	public void reportError(RecognitionException e) {
		super.reportError(e);
		
		errors.add(e);
	}

}
