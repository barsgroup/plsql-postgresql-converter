package ru.barsopen.plsqlconverter.ast.typed;
public class exception_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<exception_handler> exception_handlers = new java.util.ArrayList<exception_handler>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_EXCEPTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_EXCEPTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_handlers.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < exception_handlers.size(); ++i) {
      _result.addChild(exception_handlers.get(i).unparse());
    }


    return _result;
  }

}
