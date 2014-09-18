package ru.barsopen.plsqlconverter.ast.typed;
public class hierarchical_query_clause_connect implements _baseNode {
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
  public org.antlr.runtime.tree.Tree NOCYCLE_VK = null;
  public org.antlr.runtime.tree.Tree get_NOCYCLE_VK() { return this.NOCYCLE_VK; }
  public void set_NOCYCLE_VK(org.antlr.runtime.tree.Tree value) {
    this.NOCYCLE_VK = value;
  }
  public boolean is_NOCYCLE_VK() { return this.NOCYCLE_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.NOCYCLE_VK != null) {
      visitor.visit(this.NOCYCLE_VK);
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
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_CONNECT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_CONNECT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (NOCYCLE_VK != null) {
      _result.addChild(NOCYCLE_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
