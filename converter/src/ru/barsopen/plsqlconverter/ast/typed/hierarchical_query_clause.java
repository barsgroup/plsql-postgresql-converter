package ru.barsopen.plsqlconverter.ast.typed;
public class hierarchical_query_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public start_part start_part = null;
  public boolean is_start_part() { return this.start_part != null; }
  public hierarchical_query_clause_connect hierarchical_query_clause_connect = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.HIERARCHICAL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("HIERARCHICAL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (start_part != null) {
      _result.addChild(start_part.unparse());
    }


    if (hierarchical_query_clause_connect == null) { throw new RuntimeException(); }
    _result.addChild(hierarchical_query_clause_connect.unparse());


    return _result;
  }

}
