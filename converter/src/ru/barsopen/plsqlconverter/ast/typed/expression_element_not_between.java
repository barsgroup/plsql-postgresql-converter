package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_not_between implements expression_element, _baseNode {
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
  public expression_element expr_low = null;
  public expression_element get_expr_low() { return this.expr_low; }
  public void set_expr_low(expression_element value) {
    if (this.expr_low != null) { this.expr_low._setParent(null); }
    this.expr_low = value;
    if (this.expr_low != null) { this.expr_low._setParent(this); }
  }
  public expression_element expr_high = null;
  public expression_element get_expr_high() { return this.expr_high; }
  public void set_expr_high(expression_element value) {
    if (this.expr_high != null) { this.expr_high._setParent(null); }
    this.expr_high = value;
    if (this.expr_high != null) { this.expr_high._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.arg != null) {
      this.arg._walk(visitor);
    }
    if (this.expr_low != null) {
      this.expr_low._walk(visitor);
    }
    if (this.expr_high != null) {
      this.expr_high._walk(visitor);
    }
  }
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
