package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_not_between implements expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element arg = null;
  public expression_element expr_low = null;
  public expression_element expr_high = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.NOT_BETWEEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("NOT_BETWEEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (arg == null) { throw new RuntimeException(); }
    _result.addChild(arg.unparse());


    if (expr_low == null) { throw new RuntimeException(); }
    _result.addChild(expr_low.unparse());


    if (expr_high == null) { throw new RuntimeException(); }
    _result.addChild(expr_high.unparse());


    return _result;
  }

}
