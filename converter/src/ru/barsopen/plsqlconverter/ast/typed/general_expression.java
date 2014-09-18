package ru.barsopen.plsqlconverter.ast.typed;
public class general_expression implements expression, _baseNode {
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
  public expression_element expression_element = null;
  public expression_element get_expression_element() { return this.expression_element; }
  public void set_expression_element(expression_element value) {
    if (this.expression_element != null) { this.expression_element._setParent(null); }
    this.expression_element = value;
    if (this.expression_element != null) { this.expression_element._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression_element != null) {
      this.expression_element._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.EXPR);
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
