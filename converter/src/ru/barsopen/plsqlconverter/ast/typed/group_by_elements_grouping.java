package ru.barsopen.plsqlconverter.ast.typed;
public class group_by_elements_grouping implements group_by_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<groupin_set> groupin_sets = new java.util.ArrayList<groupin_set>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.GROUPING_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("GROUPING_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (groupin_sets.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < groupin_sets.size(); ++i) {
      _result.addChild(groupin_sets.get(i).unparse());
    }


    return _result;
  }

}
