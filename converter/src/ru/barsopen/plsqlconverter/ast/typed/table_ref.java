package ru.barsopen.plsqlconverter.ast.typed;
public class table_ref implements dml_table_expression_clause, _baseNode {
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
  public table_ref_aux table_ref_aux = null;
  public table_ref_aux get_table_ref_aux() { return this.table_ref_aux; }
  public void set_table_ref_aux(table_ref_aux value) {
    if (this.table_ref_aux != null) { this.table_ref_aux._setParent(null); }
    this.table_ref_aux = value;
    if (this.table_ref_aux != null) { this.table_ref_aux._setParent(this); }
  }
  public java.util.List<join_clause> join_clauses = new java.util.ArrayList<join_clause>();
  public java.util.List<join_clause> get_join_clauses() { return this.join_clauses; }
  public void add_join_clauses(join_clause value) {
    insert_join_clauses(join_clauses.size(), value);
  }
  public void insert_join_clauses(int pos, join_clause value) {
    this.join_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_join_clauses(int pos) {
    this.join_clauses.get(pos)._setParent(null);
    this.join_clauses.remove(pos);
  }
  public void remove_join_clauses(join_clause value) {
    this.remove_join_clauses(this.join_clauses.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.table_ref_aux != null) {
      this.table_ref_aux._walk(visitor);
    }
    for (join_clause _value: this.join_clauses) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_REF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_REF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_ref_aux == null) { throw new RuntimeException(); }
    _result.addChild(table_ref_aux.unparse());


    for (int i = 0; i < join_clauses.size(); ++i) {
      _result.addChild(join_clauses.get(i).unparse());
    }


    return _result;
  }

}
