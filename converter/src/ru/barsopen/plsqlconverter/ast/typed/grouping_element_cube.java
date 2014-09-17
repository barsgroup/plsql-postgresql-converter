package ru.barsopen.plsqlconverter.ast.typed;
public class grouping_element_cube implements grouping_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<grouping_element> grouping_elements = new java.util.ArrayList<grouping_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CUBE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CUBE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (grouping_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < grouping_elements.size(); ++i) {
      _result.addChild(grouping_elements.get(i).unparse());
    }


    return _result;
  }

}
