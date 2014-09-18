package ru.barsopen.plsqlconverter.ast.typed;
public class error_logging_reject_part implements _baseNode {
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
  public org.antlr.runtime.tree.Tree UNLIMITED_VK = null;
  public org.antlr.runtime.tree.Tree get_UNLIMITED_VK() { return this.UNLIMITED_VK; }
  public void set_UNLIMITED_VK(org.antlr.runtime.tree.Tree value) {
    this.UNLIMITED_VK = value;
  }
  public boolean is_UNLIMITED_VK() { return this.UNLIMITED_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public boolean is_expression() { return this.expression != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.UNLIMITED_VK != null) {
      visitor.visit(this.UNLIMITED_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.REJECT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("REJECT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (UNLIMITED_VK != null) {
      _result.addChild(UNLIMITED_VK);
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    return _result;
  }

}
