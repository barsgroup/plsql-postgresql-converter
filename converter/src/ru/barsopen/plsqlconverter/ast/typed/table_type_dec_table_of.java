package ru.barsopen.plsqlconverter.ast.typed;
public class table_type_dec_table_of implements table_type_dec_impl, _baseNode {
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
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }
  public table_indexed_by_part table_indexed_by_part = null;
  public table_indexed_by_part get_table_indexed_by_part() { return this.table_indexed_by_part; }
  public void set_table_indexed_by_part(table_indexed_by_part value) {
    if (this.table_indexed_by_part != null) { this.table_indexed_by_part._setParent(null); }
    this.table_indexed_by_part = value;
    if (this.table_indexed_by_part != null) { this.table_indexed_by_part._setParent(this); }
  }
  public boolean is_table_indexed_by_part() { return this.table_indexed_by_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
    if (this.table_indexed_by_part != null) {
      this.table_indexed_by_part._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.type_spec == child) {
      this.set_type_spec((ru.barsopen.plsqlconverter.ast.typed.type_spec)replacement);
      return;
    }
    if (this.table_indexed_by_part == child) {
      this.set_table_indexed_by_part((ru.barsopen.plsqlconverter.ast.typed.table_indexed_by_part)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_TABLE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_TABLE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    if (table_indexed_by_part != null) {
      _result.addChild(table_indexed_by_part.unparse());
    }


    return _result;
  }

}
