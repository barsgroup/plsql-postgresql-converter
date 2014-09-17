package ru.barsopen.plsqlconverter.ast.typed;
public class forall_statement implements statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public index_name index_name = null;
  public bounds_clause bounds_clause = null;
  public sql_statement sql_statement = null;
  public org.antlr.runtime.tree.Tree EXCEPTIONS_VK = null;
  public boolean is_EXCEPTIONS_VK() { return this.EXCEPTIONS_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FORALL_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FORALL_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (index_name == null) { throw new RuntimeException(); }
    _result.addChild(index_name.unparse());


    if (bounds_clause == null) { throw new RuntimeException(); }
    _result.addChild(bounds_clause.unparse());


    if (sql_statement == null) { throw new RuntimeException(); }
    _result.addChild(sql_statement.unparse());


    if (EXCEPTIONS_VK != null) {
      _result.addChild(EXCEPTIONS_VK);
    }


    return _result;
  }

}
