package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_xmlagg implements standard_function, _baseNode {
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
  public order_by_clause order_by_clause = null;
  public order_by_clause get_order_by_clause() { return this.order_by_clause; }
  public void set_order_by_clause(order_by_clause value) {
    if (this.order_by_clause != null) { this.order_by_clause._setParent(null); }
    this.order_by_clause = value;
    if (this.order_by_clause != null) { this.order_by_clause._setParent(this); }
  }
  public boolean is_order_by_clause() { return this.order_by_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.order_by_clause != null) {
      this.order_by_clause._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.order_by_clause == child) {
      this.set_order_by_clause((ru.barsopen.plsqlconverter.ast.typed.order_by_clause)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XMLAGG_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLAGG_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (order_by_clause != null) {
      _result.addChild(order_by_clause.unparse());
    }


    return _result;
  }

}
