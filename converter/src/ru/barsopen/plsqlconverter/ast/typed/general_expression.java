package ru.barsopen.plsqlconverter.ast.typed;
public class general_expression implements expression {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element expression_element = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXPR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXPR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression_element == null) { throw new RuntimeException(); }
    _result.addChild(expression_element.unparse());


    return _result;
  }

}
