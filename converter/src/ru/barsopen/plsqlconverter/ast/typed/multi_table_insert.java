package ru.barsopen.plsqlconverter.ast.typed;
public class multi_table_insert implements insert_statement_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public select_statement select_statement = null;
  public conditional_insert_clause conditional_insert_clause = null;
  public boolean is_conditional_insert_clause() { return this.conditional_insert_clause != null; }
  public java.util.List<multi_table_element> multi_table_elements = new java.util.ArrayList<multi_table_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MULTI_TABLE_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MULTI_TABLE_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (select_statement == null) { throw new RuntimeException(); }
    _result.addChild(select_statement.unparse());


    if (conditional_insert_clause != null) {
      _result.addChild(conditional_insert_clause.unparse());
    }


    for (int i = 0; i < multi_table_elements.size(); ++i) {
      _result.addChild(multi_table_elements.get(i).unparse());
    }


    return _result;
  }

}
