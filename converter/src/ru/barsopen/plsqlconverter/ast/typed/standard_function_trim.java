package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_trim implements standard_function, _baseNode {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public _baseNode _parent = null;
  public _baseNode _getParent() { return _parent; }
  public void _setParent(_baseNode value) { _parent = value; }
  public void _setBaseNode(_baseNode value) { this._parent = value; }
  public int _getLine() { return _line; }
  public int _getCol() { return _col; }
  public int _getTokenStartIndex() { return _tokenStartIndex; }
  public int _getTokenStopIndex() { return _tokenStopIndex; }
  public expression_element text_expr = null;
  public expression_element get_text_expr() { return this.text_expr; }
  public void set_text_expr(expression_element value) {
    if (this.text_expr != null) { this.text_expr._setParent(null); }
    this.text_expr = value;
    if (this.text_expr != null) { this.text_expr._setParent(this); }
  }
  public expression_element trim_char_expr = null;
  public expression_element get_trim_char_expr() { return this.trim_char_expr; }
  public void set_trim_char_expr(expression_element value) {
    if (this.trim_char_expr != null) { this.trim_char_expr._setParent(null); }
    this.trim_char_expr = value;
    if (this.trim_char_expr != null) { this.trim_char_expr._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree LEADING_VK = null;
  public org.antlr.runtime.tree.Tree get_LEADING_VK() { return this.LEADING_VK; }
  public void set_LEADING_VK(org.antlr.runtime.tree.Tree value) {
    this.LEADING_VK = value;
  }
  public boolean is_LEADING_VK() { return this.LEADING_VK != null; }
  public org.antlr.runtime.tree.Tree TRAILING_VK = null;
  public org.antlr.runtime.tree.Tree get_TRAILING_VK() { return this.TRAILING_VK; }
  public void set_TRAILING_VK(org.antlr.runtime.tree.Tree value) {
    this.TRAILING_VK = value;
  }
  public boolean is_TRAILING_VK() { return this.TRAILING_VK != null; }
  public org.antlr.runtime.tree.Tree BOTH_VK = null;
  public org.antlr.runtime.tree.Tree get_BOTH_VK() { return this.BOTH_VK; }
  public void set_BOTH_VK(org.antlr.runtime.tree.Tree value) {
    this.BOTH_VK = value;
  }
  public boolean is_BOTH_VK() { return this.BOTH_VK != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.text_expr != null) {
      this.text_expr._walk(visitor);
    }
    if (this.trim_char_expr != null) {
      this.trim_char_expr._walk(visitor);
    }
    if (this.LEADING_VK != null) {
      visitor.visit(this.LEADING_VK);
    }
    if (this.TRAILING_VK != null) {
      visitor.visit(this.TRAILING_VK);
    }
    if (this.BOTH_VK != null) {
      visitor.visit(this.BOTH_VK);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TRIM_VK);
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
