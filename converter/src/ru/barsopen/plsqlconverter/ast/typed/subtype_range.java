package ru.barsopen.plsqlconverter.ast.typed;
public class subtype_range {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression e1 = null;
  public expression e2 = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RANGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RANGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (e1 == null) { throw new RuntimeException(); }
    _result.addChild(e1.unparse());


    if (e2 == null) { throw new RuntimeException(); }
    _result.addChild(e2.unparse());


    return _result;
  }

}
