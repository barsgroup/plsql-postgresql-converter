package ru.barsopen.plsqlconverter.ast.typed;
public class indexed_for_bounds {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression b1 = null;
  public expression b2 = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SIMPLE_BOUND);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SIMPLE_BOUND");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (b1 == null) { throw new RuntimeException(); }
    _result.addChild(b1.unparse());


    if (b2 == null) { throw new RuntimeException(); }
    _result.addChild(b2.unparse());


    return _result;
  }

}
