package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_trim implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element text_expr = null;
  public expression_element trim_char_expr = null;
  public org.antlr.runtime.tree.Tree LEADING_VK = null;
  public boolean is_LEADING_VK() { return this.LEADING_VK != null; }
  public org.antlr.runtime.tree.Tree TRAILING_VK = null;
  public boolean is_TRAILING_VK() { return this.TRAILING_VK != null; }
  public org.antlr.runtime.tree.Tree BOTH_VK = null;
  public boolean is_BOTH_VK() { return this.BOTH_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TRIM_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TRIM_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (text_expr == null) { throw new RuntimeException(); }
    _result.addChild(text_expr.unparse());


    if (trim_char_expr != null) {
      _result.addChild(trim_char_expr.unparse());
    }


    if (LEADING_VK != null) {
      _result.addChild(LEADING_VK);
    }


    if (TRAILING_VK != null) {
      _result.addChild(TRAILING_VK);
    }


    if (BOTH_VK != null) {
      _result.addChild(BOTH_VK);
    }


    return _result;
  }

}
