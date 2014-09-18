package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_case implements standard_function, _baseNode {
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
  public expression_or_subquery expression_or_subquery = null;
  public expression_or_subquery get_expression_or_subquery() { return this.expression_or_subquery; }
  public void set_expression_or_subquery(expression_or_subquery value) {
    if (this.expression_or_subquery != null) { this.expression_or_subquery._setParent(null); }
    this.expression_or_subquery = value;
    if (this.expression_or_subquery != null) { this.expression_or_subquery._setParent(this); }
  }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression_or_subquery != null) {
      this.expression_or_subquery._walk(visitor);
    }
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.CAST_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CAST_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression_or_subquery == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_subquery.unparse());


    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    return _result;
  }

}
