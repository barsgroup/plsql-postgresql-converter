package ru.barsopen.plsqlconverter.ast.typed;
public class pivot_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree XML_VK = null;
  public boolean is_XML_VK() { return this.XML_VK != null; }
  public java.util.List<pivot_element> pivot_elements = new java.util.ArrayList<pivot_element>();
  public pivot_for_clause pivot_for_clause = null;
  public pivot_in_clause pivot_in_clause = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PIVOT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PIVOT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (XML_VK != null) {
      _result.addChild(XML_VK);
    }


    if (pivot_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < pivot_elements.size(); ++i) {
      _result.addChild(pivot_elements.get(i).unparse());
    }


    if (pivot_for_clause == null) { throw new RuntimeException(); }
    _result.addChild(pivot_for_clause.unparse());


    if (pivot_in_clause == null) { throw new RuntimeException(); }
    _result.addChild(pivot_in_clause.unparse());


    return _result;
  }

}
