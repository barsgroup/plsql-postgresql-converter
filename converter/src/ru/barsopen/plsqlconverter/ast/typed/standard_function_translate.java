package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_translate implements standard_function, _baseNode {
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
  public expression expr = null;
  public expression get_expr() { return this.expr; }
  public void set_expr(expression value) {
    if (this.expr != null) { this.expr._setParent(null); }
    this.expr = value;
    if (this.expr != null) { this.expr._setParent(this); }
  }
  public expression expr_from = null;
  public expression get_expr_from() { return this.expr_from; }
  public void set_expr_from(expression value) {
    if (this.expr_from != null) { this.expr_from._setParent(null); }
    this.expr_from = value;
    if (this.expr_from != null) { this.expr_from._setParent(this); }
  }
  public expression expr_to = null;
  public expression get_expr_to() { return this.expr_to; }
  public void set_expr_to(expression value) {
    if (this.expr_to != null) { this.expr_to._setParent(null); }
    this.expr_to = value;
    if (this.expr_to != null) { this.expr_to._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expr != null) {
      this.expr._walk(visitor);
    }
    if (this.expr_from != null) {
      this.expr_from._walk(visitor);
    }
    if (this.expr_to != null) {
      this.expr_to._walk(visitor);
    }
  }
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
