package ru.barsopen.plsqlconverter.ast.typed;
public class conditional_insert_else_part implements _baseNode {
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
  public java.util.List<multi_table_element> multi_table_elements = new java.util.ArrayList<multi_table_element>();
  public java.util.List<multi_table_element> get_multi_table_elements() { return this.multi_table_elements; }
  public void add_multi_table_elements(multi_table_element value) {
    insert_multi_table_elements(multi_table_elements.size(), value);
  }
  public void insert_multi_table_elements(int pos, multi_table_element value) {
    this.multi_table_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_multi_table_elements(int pos) {
    this.multi_table_elements.get(pos)._setParent(null);
    this.multi_table_elements.remove(pos);
  }
  public void remove_multi_table_elements(multi_table_element value) {
    this.remove_multi_table_elements(this.multi_table_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (multi_table_element _value: this.multi_table_elements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_ELSE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_ELSE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (multi_table_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < multi_table_elements.size(); ++i) {
      _result.addChild(multi_table_elements.get(i).unparse());
    }


    return _result;
  }

}
