package ru.barsopen.plsqlconverter.ast.typed;
public class from_clause implements _baseNode {
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
  public java.util.List<table_ref> table_refs = new java.util.ArrayList<table_ref>();
  public java.util.List<table_ref> get_table_refs() { return this.table_refs; }
  public void add_table_refs(table_ref value) {
    insert_table_refs(table_refs.size(), value);
  }
  public void insert_table_refs(int pos, table_ref value) {
    this.table_refs.add(pos, value);
    value._setParent(this);
  }
  public void remove_table_refs(int pos) {
    this.table_refs.get(pos)._setParent(null);
    this.table_refs.remove(pos);
  }
  public void remove_table_refs(table_ref value) {
    this.remove_table_refs(this.table_refs.indexOf(value));
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_FROM);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_FROM");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_refs.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < table_refs.size(); ++i) {
      _result.addChild(table_refs.get(i).unparse());
    }


    return _result;
  }

}
