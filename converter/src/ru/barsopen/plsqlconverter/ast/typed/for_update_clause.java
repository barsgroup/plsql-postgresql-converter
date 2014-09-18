package ru.barsopen.plsqlconverter.ast.typed;
public class for_update_clause implements _baseNode {
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
  public for_update_of_part for_update_of_part = null;
  public for_update_of_part get_for_update_of_part() { return this.for_update_of_part; }
  public void set_for_update_of_part(for_update_of_part value) {
    if (this.for_update_of_part != null) { this.for_update_of_part._setParent(null); }
    this.for_update_of_part = value;
    if (this.for_update_of_part != null) { this.for_update_of_part._setParent(this); }
  }
  public boolean is_for_update_of_part() { return this.for_update_of_part != null; }
  public for_update_options for_update_options = null;
  public for_update_options get_for_update_options() { return this.for_update_options; }
  public void set_for_update_options(for_update_options value) {
    if (this.for_update_options != null) { this.for_update_options._setParent(null); }
    this.for_update_options = value;
    if (this.for_update_options != null) { this.for_update_options._setParent(this); }
  }
  public boolean is_for_update_options() { return this.for_update_options != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.for_update_of_part != null) {
      this.for_update_of_part._walk(visitor);
    }
    if (this.for_update_options != null) {
      this.for_update_options._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (for_update_of_part != null) {
      _result.addChild(for_update_of_part.unparse());
    }


    if (for_update_options != null) {
      _result.addChild(for_update_options.unparse());
    }


    return _result;
  }

}
