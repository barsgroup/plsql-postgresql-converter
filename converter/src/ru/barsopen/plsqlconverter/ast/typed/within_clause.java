package ru.barsopen.plsqlconverter.ast.typed;
public class within_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public order_by_clause order_by_clause = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.WITHIN_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("WITHIN_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (order_by_clause == null) { throw new RuntimeException(); }
    _result.addChild(order_by_clause.unparse());


    return _result;
  }

}
