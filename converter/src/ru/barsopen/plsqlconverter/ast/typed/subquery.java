package ru.barsopen.plsqlconverter.ast.typed;
public class subquery implements subquery_basic_elements, query_partition_clause_impl, expression_or_subquery, table_collection_expression, expression_element, in_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public subquery_basic_elements subquery_basic_elements = null;
  public java.util.List<subquery_operation_part> subquery_operation_parts = new java.util.ArrayList<subquery_operation_part>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SUBQUERY);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SUBQUERY");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (subquery_basic_elements == null) { throw new RuntimeException(); }
    _result.addChild(subquery_basic_elements.unparse());


    for (int i = 0; i < subquery_operation_parts.size(); ++i) {
      _result.addChild(subquery_operation_parts.get(i).unparse());
    }


    return _result;
  }

}
