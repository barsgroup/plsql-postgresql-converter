package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_outer_join_sign implements expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element expr = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.OUTER_JOIN_SIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("OUTER_JOIN_SIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expr == null) { throw new RuntimeException(); }
    _result.addChild(expr.unparse());


    return _result;
  }

}
