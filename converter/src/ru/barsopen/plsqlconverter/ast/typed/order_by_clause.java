package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree SIBLINGS_VK = null;
  public org.antlr.runtime.tree.Tree get_SIBLINGS_VK() { return this.SIBLINGS_VK; }
  public void set_SIBLINGS_VK(org.antlr.runtime.tree.Tree value) {
    this.SIBLINGS_VK = value;
  }
  public boolean is_SIBLINGS_VK() { return this.SIBLINGS_VK != null; }
  public order_by_elements_list order_by_elements_list = null;
  public order_by_elements_list get_order_by_elements_list() { return this.order_by_elements_list; }
  public void set_order_by_elements_list(order_by_elements_list value) {
    if (this.order_by_elements_list != null) { this.order_by_elements_list._setParent(null); }
    this.order_by_elements_list = value;
    if (this.order_by_elements_list != null) { this.order_by_elements_list._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.SIBLINGS_VK != null) {
      visitor.visit(this.SIBLINGS_VK);
    }
    if (this.order_by_elements_list != null) {
      this.order_by_elements_list._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.order_by_elements_list == child) {
      this.set_order_by_elements_list((ru.barsopen.plsqlconverter.ast.typed.order_by_elements_list)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ORDER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_ORDER");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SIBLINGS_VK != null) {
      _result.addChild(SIBLINGS_VK);
    }


    if (order_by_elements_list == null) { throw new RuntimeException(); }
    _result.addChild(order_by_elements_list.unparse());


    return _result;
  }

}
