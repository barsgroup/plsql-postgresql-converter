package ru.barsopen.plsqlconverter.ast.typed;
public class single_table_insert implements insert_statement_spec, _baseNode {
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
  public insert_into_clause insert_into_clause = null;
  public insert_into_clause get_insert_into_clause() { return this.insert_into_clause; }
  public void set_insert_into_clause(insert_into_clause value) {
    if (this.insert_into_clause != null) { this.insert_into_clause._setParent(null); }
    this.insert_into_clause = value;
    if (this.insert_into_clause != null) { this.insert_into_clause._setParent(this); }
  }
  public values_clause values_clause = null;
  public values_clause get_values_clause() { return this.values_clause; }
  public void set_values_clause(values_clause value) {
    if (this.values_clause != null) { this.values_clause._setParent(null); }
    this.values_clause = value;
    if (this.values_clause != null) { this.values_clause._setParent(this); }
  }
  public boolean is_values_clause() { return this.values_clause != null; }
  public static_returning_clause static_returning_clause = null;
  public static_returning_clause get_static_returning_clause() { return this.static_returning_clause; }
  public void set_static_returning_clause(static_returning_clause value) {
    if (this.static_returning_clause != null) { this.static_returning_clause._setParent(null); }
    this.static_returning_clause = value;
    if (this.static_returning_clause != null) { this.static_returning_clause._setParent(this); }
  }
  public boolean is_static_returning_clause() { return this.static_returning_clause != null; }
  public select_statement select_statement = null;
  public select_statement get_select_statement() { return this.select_statement; }
  public void set_select_statement(select_statement value) {
    if (this.select_statement != null) { this.select_statement._setParent(null); }
    this.select_statement = value;
    if (this.select_statement != null) { this.select_statement._setParent(this); }
  }
  public boolean is_select_statement() { return this.select_statement != null; }
  public error_logging_clause error_logging_clause = null;
  public error_logging_clause get_error_logging_clause() { return this.error_logging_clause; }
  public void set_error_logging_clause(error_logging_clause value) {
    if (this.error_logging_clause != null) { this.error_logging_clause._setParent(null); }
    this.error_logging_clause = value;
    if (this.error_logging_clause != null) { this.error_logging_clause._setParent(this); }
  }
  public boolean is_error_logging_clause() { return this.error_logging_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.insert_into_clause != null) {
      this.insert_into_clause._walk(visitor);
    }
    if (this.values_clause != null) {
      this.values_clause._walk(visitor);
    }
    if (this.static_returning_clause != null) {
      this.static_returning_clause._walk(visitor);
    }
    if (this.select_statement != null) {
      this.select_statement._walk(visitor);
    }
    if (this.error_logging_clause != null) {
      this.error_logging_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SINGLE_TABLE_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SINGLE_TABLE_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (insert_into_clause == null) { throw new RuntimeException(); }
    _result.addChild(insert_into_clause.unparse());


    if (values_clause != null) {
      _result.addChild(values_clause.unparse());
    }


    if (static_returning_clause != null) {
      _result.addChild(static_returning_clause.unparse());
    }


    if (select_statement != null) {
      _result.addChild(select_statement.unparse());
    }


    if (error_logging_clause != null) {
      _result.addChild(error_logging_clause.unparse());
    }


    return _result;
  }

}
