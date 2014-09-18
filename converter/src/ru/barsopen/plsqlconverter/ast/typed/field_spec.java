package ru.barsopen.plsqlconverter.ast.typed;
public class field_spec implements _baseNode {
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
  public column_name column_name = null;
  public column_name get_column_name() { return this.column_name; }
  public void set_column_name(column_name value) {
    if (this.column_name != null) { this.column_name._setParent(null); }
    this.column_name = value;
    if (this.column_name != null) { this.column_name._setParent(this); }
  }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }
  public boolean is_type_spec() { return this.type_spec != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_NULL() { return this.SQL92_RESERVED_NULL; }
  public void set_SQL92_RESERVED_NULL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_NULL = value;
  }
  public boolean is_SQL92_RESERVED_NULL() { return this.SQL92_RESERVED_NULL != null; }
  public default_value_part default_value_part = null;
  public default_value_part get_default_value_part() { return this.default_value_part; }
  public void set_default_value_part(default_value_part value) {
    if (this.default_value_part != null) { this.default_value_part._setParent(null); }
    this.default_value_part = value;
    if (this.default_value_part != null) { this.default_value_part._setParent(this); }
  }
  public boolean is_default_value_part() { return this.default_value_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.column_name != null) {
      this.column_name._walk(visitor);
    }
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
    if (this.SQL92_RESERVED_NULL != null) {
      visitor.visit(this.SQL92_RESERVED_NULL);
    }
    if (this.default_value_part != null) {
      this.default_value_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.FIELD_SPEC);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FIELD_SPEC");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (column_name == null) { throw new RuntimeException(); }
    _result.addChild(column_name.unparse());


    if (type_spec != null) {
      _result.addChild(type_spec.unparse());
    }


    if (SQL92_RESERVED_NULL != null) {
      _result.addChild(SQL92_RESERVED_NULL);
    }


    if (default_value_part != null) {
      _result.addChild(default_value_part.unparse());
    }


    return _result;
  }

}
