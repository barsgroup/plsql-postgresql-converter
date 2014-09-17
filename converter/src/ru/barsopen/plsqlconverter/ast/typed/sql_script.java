package ru.barsopen.plsqlconverter.ast.typed;
public class sql_script {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public sql_script_item sql_script_item = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL_SCRIPT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL_SCRIPT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (sql_script_item == null) { throw new RuntimeException(); }
    _result.addChild(sql_script_item.unparse());


    return _result;
  }

}
