package ru.barsopen.plsqlconverter.ast.typed;
public class subtype_range implements _baseNode {
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
  public expression e1 = null;
  public expression get_e1() { return this.e1; }
  public void set_e1(expression value) {
    if (this.e1 != null) { this.e1._setParent(null); }
    this.e1 = value;
    if (this.e1 != null) { this.e1._setParent(this); }
  }
  public expression e2 = null;
  public expression get_e2() { return this.e2; }
  public void set_e2(expression value) {
    if (this.e2 != null) { this.e2._setParent(null); }
    this.e2 = value;
    if (this.e2 != null) { this.e2._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RANGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RANGE_VK");
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
