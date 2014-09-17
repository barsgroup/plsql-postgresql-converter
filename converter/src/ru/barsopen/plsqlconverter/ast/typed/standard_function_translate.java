package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_translate implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expr = null;
  public expression expr_from = null;
  public expression expr_to = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TRANSLATE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TRANSLATE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expr == null) { throw new RuntimeException(); }
    _result.addChild(expr.unparse());


    if (expr_from == null) { throw new RuntimeException(); }
    _result.addChild(expr_from.unparse());


    if (expr_to == null) { throw new RuntimeException(); }
    _result.addChild(expr_to.unparse());


    return _result;
  }

}
