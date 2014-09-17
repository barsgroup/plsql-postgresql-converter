package ru.barsopen.plsqlconverter.ast.typed;
public class table_ref implements dml_table_expression_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public table_ref_aux table_ref_aux = null;
  public java.util.List<join_clause> join_clauses = new java.util.ArrayList<join_clause>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TABLE_REF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_REF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_ref_aux == null) { throw new RuntimeException(); }
    _result.addChild(table_ref_aux.unparse());


    for (int i = 0; i < join_clauses.size(); ++i) {
      _result.addChild(join_clauses.get(i).unparse());
    }


    return _result;
  }

}
