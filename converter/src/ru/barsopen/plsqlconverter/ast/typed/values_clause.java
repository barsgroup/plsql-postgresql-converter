package ru.barsopen.plsqlconverter.ast.typed;
public class values_clause implements _baseNode {
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
  public expression_or_expression_list expression_or_expression_list = null;
  public expression_or_expression_list get_expression_or_expression_list() { return this.expression_or_expression_list; }
  public void set_expression_or_expression_list(expression_or_expression_list value) {
    if (this.expression_or_expression_list != null) { this.expression_or_expression_list._setParent(null); }
    this.expression_or_expression_list = value;
    if (this.expression_or_expression_list != null) { this.expression_or_expression_list._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression_or_expression_list != null) {
      this.expression_or_expression_list._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression_or_expression_list == child) {
      this.set_expression_or_expression_list((ru.barsopen.plsqlconverter.ast.typed.expression_or_expression_list)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_VALUES);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_VALUES");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression_or_expression_list == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_expression_list.unparse());


    return _result;
  }

}
