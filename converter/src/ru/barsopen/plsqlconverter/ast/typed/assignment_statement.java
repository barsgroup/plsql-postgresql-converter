package ru.barsopen.plsqlconverter.ast.typed;
public class assignment_statement implements statement, _baseNode {
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
  public assignment_target assignment_target = null;
  public assignment_target get_assignment_target() { return this.assignment_target; }
  public void set_assignment_target(assignment_target value) {
    if (this.assignment_target != null) { this.assignment_target._setParent(null); }
    this.assignment_target = value;
    if (this.assignment_target != null) { this.assignment_target._setParent(this); }
  }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.assignment_target != null) {
      this.assignment_target._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.assignment_target == child) {
      this.set_assignment_target((ru.barsopen.plsqlconverter.ast.typed.assignment_target)replacement);
      return;
    }
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.ASSIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ASSIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (assignment_target == null) { throw new RuntimeException(); }
    _result.addChild(assignment_target.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
