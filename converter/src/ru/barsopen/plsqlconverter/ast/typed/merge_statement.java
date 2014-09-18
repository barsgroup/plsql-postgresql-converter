package ru.barsopen.plsqlconverter.ast.typed;
public class merge_statement implements data_manipulation_language_statements, _baseNode {
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
  public alias alias = null;
  public alias get_alias() { return this.alias; }
  public void set_alias(alias value) {
    if (this.alias != null) { this.alias._setParent(null); }
    this.alias = value;
    if (this.alias != null) { this.alias._setParent(this); }
  }
  public boolean is_alias() { return this.alias != null; }
  public tableview_name tableview_name = null;
  public tableview_name get_tableview_name() { return this.tableview_name; }
  public void set_tableview_name(tableview_name value) {
    if (this.tableview_name != null) { this.tableview_name._setParent(null); }
    this.tableview_name = value;
    if (this.tableview_name != null) { this.tableview_name._setParent(this); }
  }
  public merge_using_clause merge_using_clause = null;
  public merge_using_clause get_merge_using_clause() { return this.merge_using_clause; }
  public void set_merge_using_clause(merge_using_clause value) {
    if (this.merge_using_clause != null) { this.merge_using_clause._setParent(null); }
    this.merge_using_clause = value;
    if (this.merge_using_clause != null) { this.merge_using_clause._setParent(this); }
  }
  public merge_update_clause merge_update_clause = null;
  public merge_update_clause get_merge_update_clause() { return this.merge_update_clause; }
  public void set_merge_update_clause(merge_update_clause value) {
    if (this.merge_update_clause != null) { this.merge_update_clause._setParent(null); }
    this.merge_update_clause = value;
    if (this.merge_update_clause != null) { this.merge_update_clause._setParent(this); }
  }
  public boolean is_merge_update_clause() { return this.merge_update_clause != null; }
  public merge_insert_clause merge_insert_clause = null;
  public merge_insert_clause get_merge_insert_clause() { return this.merge_insert_clause; }
  public void set_merge_insert_clause(merge_insert_clause value) {
    if (this.merge_insert_clause != null) { this.merge_insert_clause._setParent(null); }
    this.merge_insert_clause = value;
    if (this.merge_insert_clause != null) { this.merge_insert_clause._setParent(this); }
  }
  public boolean is_merge_insert_clause() { return this.merge_insert_clause != null; }
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
    if (this.alias != null) {
      this.alias._walk(visitor);
    }
    if (this.tableview_name != null) {
      this.tableview_name._walk(visitor);
    }
    if (this.merge_using_clause != null) {
      this.merge_using_clause._walk(visitor);
    }
    if (this.merge_update_clause != null) {
      this.merge_update_clause._walk(visitor);
    }
    if (this.merge_insert_clause != null) {
      this.merge_insert_clause._walk(visitor);
    }
    if (this.error_logging_clause != null) {
      this.error_logging_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.MERGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MERGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (tableview_name == null) { throw new RuntimeException(); }
    _result.addChild(tableview_name.unparse());


    if (merge_using_clause == null) { throw new RuntimeException(); }
    _result.addChild(merge_using_clause.unparse());


    if (merge_update_clause != null) {
      _result.addChild(merge_update_clause.unparse());
    }


    if (merge_insert_clause != null) {
      _result.addChild(merge_insert_clause.unparse());
    }


    if (error_logging_clause != null) {
      _result.addChild(error_logging_clause.unparse());
    }


    return _result;
  }

}
