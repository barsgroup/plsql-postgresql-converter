package ru.barsopen.plsqlconverter.ast.typed;
public class open_for_statement implements cursor_manipulation_statements, _baseNode {
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
  public variable_name variable_name = null;
  public variable_name get_variable_name() { return this.variable_name; }
  public void set_variable_name(variable_name value) {
    if (this.variable_name != null) { this.variable_name._setParent(null); }
    this.variable_name = value;
    if (this.variable_name != null) { this.variable_name._setParent(this); }
  }
  public expression_or_select_statement expression_or_select_statement = null;
  public expression_or_select_statement get_expression_or_select_statement() { return this.expression_or_select_statement; }
  public void set_expression_or_select_statement(expression_or_select_statement value) {
    if (this.expression_or_select_statement != null) { this.expression_or_select_statement._setParent(null); }
    this.expression_or_select_statement = value;
    if (this.expression_or_select_statement != null) { this.expression_or_select_statement._setParent(this); }
  }
  public using_clause using_clause = null;
  public using_clause get_using_clause() { return this.using_clause; }
  public void set_using_clause(using_clause value) {
    if (this.using_clause != null) { this.using_clause._setParent(null); }
    this.using_clause = value;
    if (this.using_clause != null) { this.using_clause._setParent(this); }
  }
  public boolean is_using_clause() { return this.using_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.variable_name != null) {
      this.variable_name._walk(visitor);
    }
    if (this.expression_or_select_statement != null) {
      this.expression_or_select_statement._walk(visitor);
    }
    if (this.using_clause != null) {
      this.using_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.OPEN_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("OPEN_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (variable_name == null) { throw new RuntimeException(); }
    _result.addChild(variable_name.unparse());


    if (expression_or_select_statement == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_select_statement.unparse());


    if (using_clause != null) {
      _result.addChild(using_clause.unparse());
    }


    return _result;
  }

}
