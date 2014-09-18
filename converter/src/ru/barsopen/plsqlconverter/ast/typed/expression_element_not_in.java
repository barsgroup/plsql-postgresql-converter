package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_not_in implements expression_element, _baseNode {
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
  public expression_element arg = null;
  public expression_element get_arg() { return this.arg; }
  public void set_arg(expression_element value) {
    if (this.arg != null) { this.arg._setParent(null); }
    this.arg = value;
    if (this.arg != null) { this.arg._setParent(this); }
  }
  public in_elements in_elements = null;
  public in_elements get_in_elements() { return this.in_elements; }
  public void set_in_elements(in_elements value) {
    if (this.in_elements != null) { this.in_elements._setParent(null); }
    this.in_elements = value;
    if (this.in_elements != null) { this.in_elements._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.arg != null) {
      this.arg._walk(visitor);
    }
    if (this.in_elements != null) {
      this.in_elements._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.arg == child) {
      this.set_arg((ru.barsopen.plsqlconverter.ast.typed.expression_element)replacement);
      return;
    }
    if (this.in_elements == child) {
      this.set_in_elements((ru.barsopen.plsqlconverter.ast.typed.in_elements)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_IN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("NOT_IN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (arg == null) { throw new RuntimeException(); }
    _result.addChild(arg.unparse());


    if (in_elements == null) { throw new RuntimeException(); }
    _result.addChild(in_elements.unparse());


    return _result;
  }

}
