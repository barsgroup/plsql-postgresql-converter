package ru.barsopen.plsqlconverter.ast.typed;
public class from_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<table_ref> table_refs = new java.util.ArrayList<table_ref>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_FROM);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_FROM");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_refs.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < table_refs.size(); ++i) {
      _result.addChild(table_refs.get(i).unparse());
    }


    return _result;
  }

}
