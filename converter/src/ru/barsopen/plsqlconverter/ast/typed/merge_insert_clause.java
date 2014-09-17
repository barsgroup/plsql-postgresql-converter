package ru.barsopen.plsqlconverter.ast.typed;
public class merge_insert_clause implements _baseNode {
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
  public insert_into_clause_columns insert_into_clause_columns = null;
  public insert_into_clause_columns get_insert_into_clause_columns() { return this.insert_into_clause_columns; }
  public void set_insert_into_clause_columns(insert_into_clause_columns value) {
    if (this.insert_into_clause_columns != null) { this.insert_into_clause_columns._setParent(null); }
    this.insert_into_clause_columns = value;
    if (this.insert_into_clause_columns != null) { this.insert_into_clause_columns._setParent(this); }
  }
  public expression_list expression_list = null;
  public expression_list get_expression_list() { return this.expression_list; }
  public void set_expression_list(expression_list value) {
    if (this.expression_list != null) { this.expression_list._setParent(null); }
    this.expression_list = value;
    if (this.expression_list != null) { this.expression_list._setParent(this); }
  }
  public where_clause where_clause = null;
  public where_clause get_where_clause() { return this.where_clause; }
  public void set_where_clause(where_clause value) {
    if (this.where_clause != null) { this.where_clause._setParent(null); }
    this.where_clause = value;
    if (this.where_clause != null) { this.where_clause._setParent(this); }
  }
  public boolean is_where_clause() { return this.where_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MERGE_INSERT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MERGE_INSERT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (insert_into_clause_columns == null) { throw new RuntimeException(); }
    _result.addChild(insert_into_clause_columns.unparse());


    if (expression_list == null) { throw new RuntimeException(); }
    _result.addChild(expression_list.unparse());


    if (where_clause != null) {
      _result.addChild(where_clause.unparse());
    }


    return _result;
  }

}
