package ru.barsopen.plsqlconverter.ast.typed;
public class table_ref_aux {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public alias alias = null;
  public boolean is_alias() { return this.alias != null; }
  public dml_table_expression_clause dml_table_expression_clause = null;
  public org.antlr.runtime.tree.Tree ONLY_VK = null;
  public boolean is_ONLY_VK() { return this.ONLY_VK != null; }
  public pivot_clause pivot_clause = null;
  public boolean is_pivot_clause() { return this.pivot_clause != null; }
  public unpivot_clause unpivot_clause = null;
  public boolean is_unpivot_clause() { return this.unpivot_clause != null; }
  public java.util.List<flashback_query_clause> flashback_query_clauses = new java.util.ArrayList<flashback_query_clause>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TABLE_REF_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_REF_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (dml_table_expression_clause == null) { throw new RuntimeException(); }
    _result.addChild(dml_table_expression_clause.unparse());


    if (ONLY_VK != null) {
      _result.addChild(ONLY_VK);
    }


    if (pivot_clause != null) {
      _result.addChild(pivot_clause.unparse());
    }


    if (unpivot_clause != null) {
      _result.addChild(unpivot_clause.unparse());
    }


    for (int i = 0; i < flashback_query_clauses.size(); ++i) {
      _result.addChild(flashback_query_clauses.get(i).unparse());
    }


    return _result;
  }

}
