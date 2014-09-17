package ru.barsopen.plsqlconverter.ast.typed;
public class insert_into_clause_columns implements _baseNode {
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

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (column_name _value: this.column_names) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COLUMNS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COLUMNS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < column_names.size(); ++i) {
      _result.addChild(column_names.get(i).unparse());
    }


    return _result;
  }

}
