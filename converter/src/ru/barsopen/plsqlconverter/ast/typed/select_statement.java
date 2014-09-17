package ru.barsopen.plsqlconverter.ast.typed;
public class select_statement implements data_manipulation_language_statements, selected_tableview_src, expression_or_select_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public subquery_factoring_clause subquery_factoring_clause = null;
  public boolean is_subquery_factoring_clause() { return this.subquery_factoring_clause != null; }
  public subquery subquery = null;
  public order_by_clause order_by_clause = null;
  public boolean is_order_by_clause() { return this.order_by_clause != null; }
  public for_update_clause for_update_clause = null;
  public boolean is_for_update_clause() { return this.for_update_clause != null; }

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
