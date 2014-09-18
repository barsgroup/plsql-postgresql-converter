package ru.barsopen.plsqlconverter.ast.typed;
public class windowing_clause_between implements windowing_clause_spec, _baseNode {
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
  public windowing_elements e1 = null;
  public windowing_elements get_e1() { return this.e1; }
  public void set_e1(windowing_elements value) {
    if (this.e1 != null) { this.e1._setParent(null); }
    this.e1 = value;
    if (this.e1 != null) { this.e1._setParent(this); }
  }
  public windowing_elements e2 = null;
  public windowing_elements get_e2() { return this.e2; }
  public void set_e2(windowing_elements value) {
    if (this.e2 != null) { this.e2._setParent(null); }
    this.e2 = value;
    if (this.e2 != null) { this.e2._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.e1 != null) {
      this.e1._walk(visitor);
    }
    if (this.e2 != null) {
      this.e2._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.e1 == child) {
      this.set_e1((ru.barsopen.plsqlconverter.ast.typed.windowing_elements)replacement);
      return;
    }
    if (this.e2 == child) {
      this.set_e2((ru.barsopen.plsqlconverter.ast.typed.windowing_elements)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_BETWEEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_BETWEEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (e1 == null) { throw new RuntimeException(); }
    _result.addChild(e1.unparse());


    if (e2 == null) { throw new RuntimeException(); }
    _result.addChild(e2.unparse());


    return _result;
  }

}
