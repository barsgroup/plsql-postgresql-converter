package ru.barsopen.plsqlconverter.ast.typed;
public class relies_on_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<tableview_name> tableview_names = new java.util.ArrayList<tableview_name>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RELIES_ON_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RELIES_ON_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (tableview_names.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < tableview_names.size(); ++i) {
      _result.addChild(tableview_names.get(i).unparse());
    }


    return _result;
  }

}
