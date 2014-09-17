package ru.barsopen.plsqlconverter.ast.typed;
public class constant_minus_unsigned implements constant {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public constant_unsigned constant_unsigned = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MINUS_SIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MINUS_SIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (constant_unsigned == null) { throw new RuntimeException(); }
    _result.addChild(constant_unsigned.unparse());


    return _result;
  }

}
