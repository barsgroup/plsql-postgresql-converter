package ru.barsopen.plsqlconverter.ast.typed;
public class table_var_dec implements table_declaration, _baseNode {
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
  public table_var_name table_var_name = null;
  public table_var_name get_table_var_name() { return this.table_var_name; }
  public void set_table_var_name(table_var_name value) {
    if (this.table_var_name != null) { this.table_var_name._setParent(null); }
    this.table_var_name = value;
    if (this.table_var_name != null) { this.table_var_name._setParent(this); }
  }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.table_var_name != null) {
      this.table_var_name._walk(visitor);
    }
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.table_var_name == child) {
      this.set_table_var_name((ru.barsopen.plsqlconverter.ast.typed.table_var_name)replacement);
      return;
    }
    if (this.type_spec == child) {
      this.set_type_spec((ru.barsopen.plsqlconverter.ast.typed.type_spec)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_VAR_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_VAR_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_var_name == null) { throw new RuntimeException(); }
    _result.addChild(table_var_name.unparse());


    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    return _result;
  }

}
