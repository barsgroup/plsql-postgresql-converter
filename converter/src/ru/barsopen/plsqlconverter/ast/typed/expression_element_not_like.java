package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_not_like implements expression_element, _baseNode {
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
  public expression_element text = null;
  public expression_element get_text() { return this.text; }
  public void set_text(expression_element value) {
    if (this.text != null) { this.text._setParent(null); }
    this.text = value;
    if (this.text != null) { this.text._setParent(this); }
  }
  public expression_element pattern = null;
  public expression_element get_pattern() { return this.pattern; }
  public void set_pattern(expression_element value) {
    if (this.pattern != null) { this.pattern._setParent(null); }
    this.pattern = value;
    if (this.pattern != null) { this.pattern._setParent(this); }
  }
  public expression_element escape = null;
  public expression_element get_escape() { return this.escape; }
  public void set_escape(expression_element value) {
    if (this.escape != null) { this.escape._setParent(null); }
    this.escape = value;
    if (this.escape != null) { this.escape._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.text != null) {
      this.text._walk(visitor);
    }
    if (this.pattern != null) {
      this.pattern._walk(visitor);
    }
    if (this.escape != null) {
      this.escape._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_LIKE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("NOT_LIKE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (text == null) { throw new RuntimeException(); }
    _result.addChild(text.unparse());


    if (pattern == null) { throw new RuntimeException(); }
    _result.addChild(pattern.unparse());


    if (escape != null) {
      _result.addChild(escape.unparse());
    }


    return _result;
  }

}
