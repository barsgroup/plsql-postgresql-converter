package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_elements_list {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<order_by_elements> elements = new java.util.ArrayList<order_by_elements>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ORDER_BY_ELEMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ORDER_BY_ELEMENTS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < elements.size(); ++i) {
      _result.addChild(elements.get(i).unparse());
    }


    return _result;
  }

}
