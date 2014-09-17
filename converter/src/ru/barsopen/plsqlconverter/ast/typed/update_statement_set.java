package ru.barsopen.plsqlconverter.ast.typed;
public class update_statement_set {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<update_set_elements> update_set_elementss = new java.util.ArrayList<update_set_elements>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SET_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SET_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (update_set_elementss.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < update_set_elementss.size(); ++i) {
      _result.addChild(update_set_elementss.get(i).unparse());
    }


    return _result;
  }

}
