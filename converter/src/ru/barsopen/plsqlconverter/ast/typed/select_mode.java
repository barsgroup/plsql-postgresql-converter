package ru.barsopen.plsqlconverter.ast.typed;
public class select_mode implements table_expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public select_statement select_statement = null;
  public subquery_restriction_clause subquery_restriction_clause = null;
  public boolean is_subquery_restriction_clause() { return this.subquery_restriction_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECT_MODE);
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
