package ru.barsopen.plsqlconverter.ast.typed;
public class groupin_set {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public grouping_element grouping_element = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.GROUPIN_SET);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("GROUPIN_SET");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (grouping_element == null) { throw new RuntimeException(); }
    _result.addChild(grouping_element.unparse());


    return _result;
  }

}
