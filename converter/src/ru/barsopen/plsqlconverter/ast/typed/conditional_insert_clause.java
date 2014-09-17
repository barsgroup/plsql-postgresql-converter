package ru.barsopen.plsqlconverter.ast.typed;
public class conditional_insert_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL; }
  public void set_SQL92_RESERVED_ALL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_ALL = value;
  }
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public org.antlr.runtime.tree.Tree FIRST_VK = null;
  public org.antlr.runtime.tree.Tree get_FIRST_VK() { return this.FIRST_VK; }
  public void set_FIRST_VK(org.antlr.runtime.tree.Tree value) {
    this.FIRST_VK = value;
  }
  public boolean is_FIRST_VK() { return this.FIRST_VK != null; }
  public java.util.List<conditional_insert_when_part> conditional_insert_when_parts = new java.util.ArrayList<conditional_insert_when_part>();
  public java.util.List<conditional_insert_when_part> get_conditional_insert_when_parts() { return this.conditional_insert_when_parts; }
  public void add_conditional_insert_when_parts(conditional_insert_when_part value) {
    insert_conditional_insert_when_parts(conditional_insert_when_parts.size(), value);
  }
  public void insert_conditional_insert_when_parts(int pos, conditional_insert_when_part value) {
    this.conditional_insert_when_parts.add(pos, value);
    value._setParent(this);
  }
  public void remove_conditional_insert_when_parts(int pos) {
    this.conditional_insert_when_parts.get(pos)._setParent(null);
    this.conditional_insert_when_parts.remove(pos);
  }
  public void remove_conditional_insert_when_parts(conditional_insert_when_part value) {
    this.remove_conditional_insert_when_parts(this.conditional_insert_when_parts.indexOf(value));
  }
  public conditional_insert_else_part conditional_insert_else_part = null;
  public conditional_insert_else_part get_conditional_insert_else_part() { return this.conditional_insert_else_part; }
  public void set_conditional_insert_else_part(conditional_insert_else_part value) {
    if (this.conditional_insert_else_part != null) { this.conditional_insert_else_part._setParent(null); }
    this.conditional_insert_else_part = value;
    if (this.conditional_insert_else_part != null) { this.conditional_insert_else_part._setParent(this); }
  }
  public boolean is_conditional_insert_else_part() { return this.conditional_insert_else_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.SQL92_RESERVED_ALL != null) {
      visitor.visit(this.SQL92_RESERVED_ALL);
    }
    if (this.FIRST_VK != null) {
      visitor.visit(this.FIRST_VK);
    }
    for (conditional_insert_when_part _value: this.conditional_insert_when_parts) {
      _value._walk(visitor);
    }
    if (this.conditional_insert_else_part != null) {
      this.conditional_insert_else_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CONDITIONAL_INSERT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CONDITIONAL_INSERT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    if (FIRST_VK != null) {
      _result.addChild(FIRST_VK);
    }


    if (conditional_insert_when_parts.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < conditional_insert_when_parts.size(); ++i) {
      _result.addChild(conditional_insert_when_parts.get(i).unparse());
    }


    if (conditional_insert_else_part != null) {
      _result.addChild(conditional_insert_else_part.unparse());
    }


    return _result;
  }

}
