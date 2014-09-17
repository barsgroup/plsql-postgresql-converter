package ru.barsopen.plsqlconverter.ast.typed;
public class table_expression implements dml_table_expression_clause, _baseNode {
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
  public table_expression_element table_expression_element = null;
  public table_expression_element get_table_expression_element() { return this.table_expression_element; }
  public void set_table_expression_element(table_expression_element value) {
    if (this.table_expression_element != null) { this.table_expression_element._setParent(null); }
    this.table_expression_element = value;
    if (this.table_expression_element != null) { this.table_expression_element._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.table_expression_element != null) {
      this.table_expression_element._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TABLE_EXPRESSION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_EXPRESSION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_expression_element == null) { throw new RuntimeException(); }
    _result.addChild(table_expression_element.unparse());


    return _result;
  }

}
