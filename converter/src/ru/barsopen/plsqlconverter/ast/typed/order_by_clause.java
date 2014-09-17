package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SIBLINGS_VK = null;
  public boolean is_SIBLINGS_VK() { return this.SIBLINGS_VK != null; }
  public order_by_elements_list order_by_elements_list = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_ORDER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_ORDER");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SIBLINGS_VK != null) {
      _result.addChild(SIBLINGS_VK);
    }


    if (order_by_elements_list == null) { throw new RuntimeException(); }
    _result.addChild(order_by_elements_list.unparse());


    return _result;
  }

}
