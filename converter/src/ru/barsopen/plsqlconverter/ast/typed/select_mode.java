package ru.barsopen.plsqlconverter.ast.typed;
public class select_mode implements table_expression_element, _baseNode {
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
  public select_statement select_statement = null;
  public select_statement get_select_statement() { return this.select_statement; }
  public void set_select_statement(select_statement value) {
    if (this.select_statement != null) { this.select_statement._setParent(null); }
    this.select_statement = value;
    if (this.select_statement != null) { this.select_statement._setParent(this); }
  }
  public subquery_restriction_clause subquery_restriction_clause = null;
  public subquery_restriction_clause get_subquery_restriction_clause() { return this.subquery_restriction_clause; }
  public void set_subquery_restriction_clause(subquery_restriction_clause value) {
    if (this.subquery_restriction_clause != null) { this.subquery_restriction_clause._setParent(null); }
    this.subquery_restriction_clause = value;
    if (this.subquery_restriction_clause != null) { this.subquery_restriction_clause._setParent(this); }
  }
  public boolean is_subquery_restriction_clause() { return this.subquery_restriction_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.select_statement != null) {
      this.select_statement._walk(visitor);
    }
    if (this.subquery_restriction_clause != null) {
      this.subquery_restriction_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (select_statement == null) { throw new RuntimeException(); }
    _result.addChild(select_statement.unparse());


    if (subquery_restriction_clause != null) {
      _result.addChild(subquery_restriction_clause.unparse());
    }


    return _result;
  }

}
