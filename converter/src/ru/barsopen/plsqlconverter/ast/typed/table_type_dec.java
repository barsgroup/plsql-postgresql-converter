package ru.barsopen.plsqlconverter.ast.typed;
public class table_type_dec implements table_declaration, _baseNode {
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
  public type_name type_name = null;
  public type_name get_type_name() { return this.type_name; }
  public void set_type_name(type_name value) {
    if (this.type_name != null) { this.type_name._setParent(null); }
    this.type_name = value;
    if (this.type_name != null) { this.type_name._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_NULL() { return this.SQL92_RESERVED_NULL; }
  public void set_SQL92_RESERVED_NULL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_NULL = value;
  }
  public boolean is_SQL92_RESERVED_NULL() { return this.SQL92_RESERVED_NULL != null; }
  public table_type_dec_impl table_type_dec_impl = null;
  public table_type_dec_impl get_table_type_dec_impl() { return this.table_type_dec_impl; }
  public void set_table_type_dec_impl(table_type_dec_impl value) {
    if (this.table_type_dec_impl != null) { this.table_type_dec_impl._setParent(null); }
    this.table_type_dec_impl = value;
    if (this.table_type_dec_impl != null) { this.table_type_dec_impl._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.type_name != null) {
      this.type_name._walk(visitor);
    }
    if (this.SQL92_RESERVED_NULL != null) {
      visitor.visit(this.SQL92_RESERVED_NULL);
    }
    if (this.table_type_dec_impl != null) {
      this.table_type_dec_impl._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.type_name == child) {
      this.set_type_name((ru.barsopen.plsqlconverter.ast.typed.type_name)replacement);
      return;
    }
    if (this.table_type_dec_impl == child) {
      this.set_table_type_dec_impl((ru.barsopen.plsqlconverter.ast.typed.table_type_dec_impl)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_TYPE_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_TYPE_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (type_name == null) { throw new RuntimeException(); }
    _result.addChild(type_name.unparse());


    if (SQL92_RESERVED_NULL != null) {
      _result.addChild(SQL92_RESERVED_NULL);
    }


    if (table_type_dec_impl == null) { throw new RuntimeException(); }
    _result.addChild(table_type_dec_impl.unparse());


    return _result;
  }

}
