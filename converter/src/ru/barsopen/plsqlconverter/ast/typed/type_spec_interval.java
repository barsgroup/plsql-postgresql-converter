package ru.barsopen.plsqlconverter.ast.typed;
public class type_spec_interval implements type_spec, _baseNode {
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
  public interval_type_spec_first interval_type_spec_first = null;
  public interval_type_spec_first get_interval_type_spec_first() { return this.interval_type_spec_first; }
  public void set_interval_type_spec_first(interval_type_spec_first value) {
    if (this.interval_type_spec_first != null) { this.interval_type_spec_first._setParent(null); }
    this.interval_type_spec_first = value;
    if (this.interval_type_spec_first != null) { this.interval_type_spec_first._setParent(this); }
  }
  public interval_type_spec_second interval_type_spec_second = null;
  public interval_type_spec_second get_interval_type_spec_second() { return this.interval_type_spec_second; }
  public void set_interval_type_spec_second(interval_type_spec_second value) {
    if (this.interval_type_spec_second != null) { this.interval_type_spec_second._setParent(null); }
    this.interval_type_spec_second = value;
    if (this.interval_type_spec_second != null) { this.interval_type_spec_second._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.interval_type_spec_first != null) {
      this.interval_type_spec_first._walk(visitor);
    }
    if (this.interval_type_spec_second != null) {
      this.interval_type_spec_second._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.INTERVAL_DATATYPE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INTERVAL_DATATYPE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (interval_type_spec_first == null) { throw new RuntimeException(); }
    _result.addChild(interval_type_spec_first.unparse());


    if (interval_type_spec_second == null) { throw new RuntimeException(); }
    _result.addChild(interval_type_spec_second.unparse());


    return _result;
  }

}
