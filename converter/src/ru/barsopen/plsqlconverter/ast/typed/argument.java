package ru.barsopen.plsqlconverter.ast.typed;
public class argument implements _baseNode {
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
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public parameter_name parameter_name = null;
  public parameter_name get_parameter_name() { return this.parameter_name; }
  public void set_parameter_name(parameter_name value) {
    if (this.parameter_name != null) { this.parameter_name._setParent(null); }
    this.parameter_name = value;
    if (this.parameter_name != null) { this.parameter_name._setParent(this); }
  }
  public boolean is_parameter_name() { return this.parameter_name != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.parameter_name != null) {
      this.parameter_name._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.parameter_name == child) {
      this.set_parameter_name((ru.barsopen.plsqlconverter.ast.typed.parameter_name)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.ARGUMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ARGUMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (parameter_name != null) {
      _result.addChild(parameter_name.unparse());
    }


    return _result;
  }

}
