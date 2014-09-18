package ru.barsopen.plsqlconverter.ast.typed;
public class factoring_element implements _baseNode {
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
  public query_name query_name = null;
  public query_name get_query_name() { return this.query_name; }
  public void set_query_name(query_name value) {
    if (this.query_name != null) { this.query_name._setParent(null); }
    this.query_name = value;
    if (this.query_name != null) { this.query_name._setParent(this); }
  }
  public java.util.List<column_name> column_names = new java.util.ArrayList<column_name>();
  public java.util.List<column_name> get_column_names() { return this.column_names; }
  public void add_column_names(column_name value) {
    insert_column_names(column_names.size(), value);
  }
  public void insert_column_names(int pos, column_name value) {
    this.column_names.add(pos, value);
    value._setParent(this);
  }
  public void remove_column_names(int pos) {
    this.column_names.get(pos)._setParent(null);
    this.column_names.remove(pos);
  }
  public void remove_column_names(column_name value) {
    this.remove_column_names(this.column_names.indexOf(value));
  }
  public subquery subquery = null;
  public subquery get_subquery() { return this.subquery; }
  public void set_subquery(subquery value) {
    if (this.subquery != null) { this.subquery._setParent(null); }
    this.subquery = value;
    if (this.subquery != null) { this.subquery._setParent(this); }
  }
  public order_by_clause order_by_clause = null;
  public order_by_clause get_order_by_clause() { return this.order_by_clause; }
  public void set_order_by_clause(order_by_clause value) {
    if (this.order_by_clause != null) { this.order_by_clause._setParent(null); }
    this.order_by_clause = value;
    if (this.order_by_clause != null) { this.order_by_clause._setParent(this); }
  }
  public boolean is_order_by_clause() { return this.order_by_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.query_name != null) {
      this.query_name._walk(visitor);
    }
    for (column_name _value: this.column_names) {
      _value._walk(visitor);
    }
    if (this.subquery != null) {
      this.subquery._walk(visitor);
    }
    if (this.order_by_clause != null) {
      this.order_by_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.FACTORING);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FACTORING");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_name == null) { throw new RuntimeException(); }
    _result.addChild(query_name.unparse());


    for (int i = 0; i < column_names.size(); ++i) {
      _result.addChild(column_names.get(i).unparse());
    }


    if (subquery == null) { throw new RuntimeException(); }
    _result.addChild(subquery.unparse());


    if (order_by_clause != null) {
      _result.addChild(order_by_clause.unparse());
    }


    return _result;
  }

}
