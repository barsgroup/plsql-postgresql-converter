package ru.barsopen.plsqlconverter.ast.typed;
public class insert_into_clause implements _baseNode {
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
  public general_table_ref general_table_ref = null;
  public general_table_ref get_general_table_ref() { return this.general_table_ref; }
  public void set_general_table_ref(general_table_ref value) {
    if (this.general_table_ref != null) { this.general_table_ref._setParent(null); }
    this.general_table_ref = value;
    if (this.general_table_ref != null) { this.general_table_ref._setParent(this); }
  }
  public insert_into_clause_columns insert_into_clause_columns = null;
  public insert_into_clause_columns get_insert_into_clause_columns() { return this.insert_into_clause_columns; }
  public void set_insert_into_clause_columns(insert_into_clause_columns value) {
    if (this.insert_into_clause_columns != null) { this.insert_into_clause_columns._setParent(null); }
    this.insert_into_clause_columns = value;
    if (this.insert_into_clause_columns != null) { this.insert_into_clause_columns._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.general_table_ref != null) {
      this.general_table_ref._walk(visitor);
    }
    if (this.insert_into_clause_columns != null) {
      this.insert_into_clause_columns._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTO);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_INTO");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_table_ref == null) { throw new RuntimeException(); }
    _result.addChild(general_table_ref.unparse());


    if (insert_into_clause_columns == null) { throw new RuntimeException(); }
    _result.addChild(insert_into_clause_columns.unparse());


    return _result;
  }

}
