package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_all implements expression_element, _baseNode {
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

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression_or_subquery != null) {
      this.expression_or_subquery._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression_or_subquery == child) {
      this.set_expression_or_subquery((ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_ALL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression_or_subquery == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_subquery.unparse());


    return _result;
  }

}
