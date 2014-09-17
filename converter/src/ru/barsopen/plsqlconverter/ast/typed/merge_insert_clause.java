package ru.barsopen.plsqlconverter.ast.typed;
public class merge_insert_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public insert_into_clause_columns insert_into_clause_columns = null;
  public expression_list expression_list = null;
  public where_clause where_clause = null;
  public boolean is_where_clause() { return this.where_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MERGE_INSERT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MERGE_INSERT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (insert_into_clause_columns == null) { throw new RuntimeException(); }
    _result.addChild(insert_into_clause_columns.unparse());


    if (expression_list == null) { throw new RuntimeException(); }
    _result.addChild(expression_list.unparse());


    if (where_clause != null) {
      _result.addChild(where_clause.unparse());
    }


    return _result;
  }

}
