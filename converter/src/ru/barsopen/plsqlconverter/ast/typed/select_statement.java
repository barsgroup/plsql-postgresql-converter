package ru.barsopen.plsqlconverter.ast.typed;
public class select_statement implements data_manipulation_language_statements, selected_tableview_src, expression_or_select_statement, _baseNode {
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
  public subquery_factoring_clause subquery_factoring_clause = null;
  public subquery_factoring_clause get_subquery_factoring_clause() { return this.subquery_factoring_clause; }
  public void set_subquery_factoring_clause(subquery_factoring_clause value) {
    if (this.subquery_factoring_clause != null) { this.subquery_factoring_clause._setParent(null); }
    this.subquery_factoring_clause = value;
    if (this.subquery_factoring_clause != null) { this.subquery_factoring_clause._setParent(this); }
  }
  public boolean is_subquery_factoring_clause() { return this.subquery_factoring_clause != null; }
  public subquery subquery = null;
  public subquery get_subquery() { return this.subquery; }
  public void set_subquery(subquery value) {
    if (this.subquery != null) { this.subquery._setParent(null); }
    this.subquery = value;
    if (this.subquery != null) { this.subquery._setParent(this); }
  }
  public order_by_clause order_by_clause = null;
  public order_by_clause get_order_by_clause() { return this.order_by_clause; }
  public void set_order_by_clause(order_by_clause value) {
    if (this.order_by_clause != null) { this.order_by_clause._setParent(null); }
    this.order_by_clause = value;
    if (this.order_by_clause != null) { this.order_by_clause._setParent(this); }
  }
  public boolean is_order_by_clause() { return this.order_by_clause != null; }
  public for_update_clause for_update_clause = null;
  public for_update_clause get_for_update_clause() { return this.for_update_clause; }
  public void set_for_update_clause(for_update_clause value) {
    if (this.for_update_clause != null) { this.for_update_clause._setParent(null); }
    this.for_update_clause = value;
    if (this.for_update_clause != null) { this.for_update_clause._setParent(this); }
  }
  public boolean is_for_update_clause() { return this.for_update_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.subquery_factoring_clause != null) {
      this.subquery_factoring_clause._walk(visitor);
    }
    if (this.subquery != null) {
      this.subquery._walk(visitor);
    }
    if (this.order_by_clause != null) {
      this.order_by_clause._walk(visitor);
    }
    if (this.for_update_clause != null) {
      this.for_update_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECT_STATEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_STATEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (subquery_factoring_clause != null) {
      _result.addChild(subquery_factoring_clause.unparse());
    }


    if (subquery == null) { throw new RuntimeException(); }
    _result.addChild(subquery.unparse());


    if (order_by_clause != null) {
      _result.addChild(order_by_clause.unparse());
    }


    if (for_update_clause != null) {
      _result.addChild(for_update_clause.unparse());
    }


    return _result;
  }

}
