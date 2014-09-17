package ru.barsopen.plsqlconverter.ast.typed;
public class indexed_for_bounds implements _baseNode {
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
  public expression b1 = null;
  public expression get_b1() { return this.b1; }
  public void set_b1(expression value) {
    if (this.b1 != null) { this.b1._setParent(null); }
    this.b1 = value;
    if (this.b1 != null) { this.b1._setParent(this); }
  }
  public expression b2 = null;
  public expression get_b2() { return this.b2; }
  public void set_b2(expression value) {
    if (this.b2 != null) { this.b2._setParent(null); }
    this.b2 = value;
    if (this.b2 != null) { this.b2._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.b1 != null) {
      this.b1._walk(visitor);
    }
    if (this.b2 != null) {
      this.b2._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SIMPLE_BOUND);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SIMPLE_BOUND");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (b1 == null) { throw new RuntimeException(); }
    _result.addChild(b1.unparse());


    if (b2 == null) { throw new RuntimeException(); }
    _result.addChild(b2.unparse());


    return _result;
  }

}
