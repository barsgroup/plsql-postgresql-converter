package ru.barsopen.plsqlconverter.ast.typed;
public class constant_negated implements constant, _baseNode {
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
  public constant_unsigned constant_unsigned = null;
  public constant_unsigned get_constant_unsigned() { return this.constant_unsigned; }
  public void set_constant_unsigned(constant_unsigned value) {
    if (this.constant_unsigned != null) { this.constant_unsigned._setParent(null); }
    this.constant_unsigned = value;
    if (this.constant_unsigned != null) { this.constant_unsigned._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.constant_unsigned != null) {
      this.constant_unsigned._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.CONSTANT_NEGATED);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CONSTANT_NEGATED");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (constant_unsigned == null) { throw new RuntimeException(); }
    _result.addChild(constant_unsigned.unparse());


    return _result;
  }

}
