package ru.barsopen.plsqlconverter.ast.typed;
public class group_by_clause implements _baseNode {
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
  public java.util.List<group_by_element> group_by_elements = new java.util.ArrayList<group_by_element>();
  public java.util.List<group_by_element> get_group_by_elements() { return this.group_by_elements; }
  public void add_group_by_elements(group_by_element value) {
    insert_group_by_elements(group_by_elements.size(), value);
  }
  public void insert_group_by_elements(int pos, group_by_element value) {
    this.group_by_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_group_by_elements(int pos) {
    this.group_by_elements.get(pos)._setParent(null);
    this.group_by_elements.remove(pos);
  }
  public void remove_group_by_elements(group_by_element value) {
    this.remove_group_by_elements(this.group_by_elements.indexOf(value));
  }
  public having_clause having_clause = null;
  public having_clause get_having_clause() { return this.having_clause; }
  public void set_having_clause(having_clause value) {
    if (this.having_clause != null) { this.having_clause._setParent(null); }
    this.having_clause = value;
    if (this.having_clause != null) { this.having_clause._setParent(this); }
  }
  public boolean is_having_clause() { return this.having_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (group_by_element _value: this.group_by_elements) {
      _value._walk(visitor);
    }
    if (this.having_clause != null) {
      this.having_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_GROUP);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_GROUP");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (group_by_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < group_by_elements.size(); ++i) {
      _result.addChild(group_by_elements.get(i).unparse());
    }


    if (having_clause != null) {
      _result.addChild(having_clause.unparse());
    }


    return _result;
  }

}
