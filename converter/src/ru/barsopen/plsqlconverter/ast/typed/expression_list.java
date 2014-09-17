package ru.barsopen.plsqlconverter.ast.typed;
public class expression_list implements query_partition_clause_impl, grouping_element, expression_or_expression_list, in_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<expression> expressions = new java.util.ArrayList<expression>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXPR_LIST);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXPR_LIST");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < expressions.size(); ++i) {
      _result.addChild(expressions.get(i).unparse());
    }


    return _result;
  }

}
