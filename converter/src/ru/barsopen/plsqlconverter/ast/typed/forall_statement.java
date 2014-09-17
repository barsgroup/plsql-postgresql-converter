package ru.barsopen.plsqlconverter.ast.typed;
public class forall_statement implements statement, _baseNode {
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
  public index_name index_name = null;
  public index_name get_index_name() { return this.index_name; }
  public void set_index_name(index_name value) {
    if (this.index_name != null) { this.index_name._setParent(null); }
    this.index_name = value;
    if (this.index_name != null) { this.index_name._setParent(this); }
  }
  public bounds_clause bounds_clause = null;
  public bounds_clause get_bounds_clause() { return this.bounds_clause; }
  public void set_bounds_clause(bounds_clause value) {
    if (this.bounds_clause != null) { this.bounds_clause._setParent(null); }
    this.bounds_clause = value;
    if (this.bounds_clause != null) { this.bounds_clause._setParent(this); }
  }
  public sql_statement sql_statement = null;
  public sql_statement get_sql_statement() { return this.sql_statement; }
  public void set_sql_statement(sql_statement value) {
    if (this.sql_statement != null) { this.sql_statement._setParent(null); }
    this.sql_statement = value;
    if (this.sql_statement != null) { this.sql_statement._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree EXCEPTIONS_VK = null;
  public org.antlr.runtime.tree.Tree get_EXCEPTIONS_VK() { return this.EXCEPTIONS_VK; }
  public void set_EXCEPTIONS_VK(org.antlr.runtime.tree.Tree value) {
    this.EXCEPTIONS_VK = value;
  }
  public boolean is_EXCEPTIONS_VK() { return this.EXCEPTIONS_VK != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.index_name != null) {
      this.index_name._walk(visitor);
    }
    if (this.bounds_clause != null) {
      this.bounds_clause._walk(visitor);
    }
    if (this.sql_statement != null) {
      this.sql_statement._walk(visitor);
    }
    if (this.EXCEPTIONS_VK != null) {
      visitor.visit(this.EXCEPTIONS_VK);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FORALL_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FORALL_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (index_name == null) { throw new RuntimeException(); }
    _result.addChild(index_name.unparse());


    if (bounds_clause == null) { throw new RuntimeException(); }
    _result.addChild(bounds_clause.unparse());


    if (sql_statement == null) { throw new RuntimeException(); }
    _result.addChild(sql_statement.unparse());


    if (EXCEPTIONS_VK != null) {
      _result.addChild(EXCEPTIONS_VK);
    }


    return _result;
  }

}
