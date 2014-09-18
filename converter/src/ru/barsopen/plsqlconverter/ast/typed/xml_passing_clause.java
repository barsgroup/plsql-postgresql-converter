package ru.barsopen.plsqlconverter.ast.typed;
public class xml_passing_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree VALUE_VK = null;
  public org.antlr.runtime.tree.Tree get_VALUE_VK() { return this.VALUE_VK; }
  public void set_VALUE_VK(org.antlr.runtime.tree.Tree value) {
    this.VALUE_VK = value;
  }
  public boolean is_VALUE_VK() { return this.VALUE_VK != null; }
  public xml_passing_clause_expr xml_passing_clause_expr = null;
  public xml_passing_clause_expr get_xml_passing_clause_expr() { return this.xml_passing_clause_expr; }
  public void set_xml_passing_clause_expr(xml_passing_clause_expr value) {
    if (this.xml_passing_clause_expr != null) { this.xml_passing_clause_expr._setParent(null); }
    this.xml_passing_clause_expr = value;
    if (this.xml_passing_clause_expr != null) { this.xml_passing_clause_expr._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.VALUE_VK != null) {
      visitor.visit(this.VALUE_VK);
    }
    if (this.xml_passing_clause_expr != null) {
      this.xml_passing_clause_expr._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.xml_passing_clause_expr == child) {
      this.set_xml_passing_clause_expr((ru.barsopen.plsqlconverter.ast.typed.xml_passing_clause_expr)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PASSING_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PASSING_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (VALUE_VK != null) {
      _result.addChild(VALUE_VK);
    }


    if (xml_passing_clause_expr == null) { throw new RuntimeException(); }
    _result.addChild(xml_passing_clause_expr.unparse());


    return _result;
  }

}
