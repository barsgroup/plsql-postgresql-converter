package ru.barsopen.plsqlconverter.ast.typed;
public class tabe_expression implements dml_table_expression_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public table_expression_element table_expression_element = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TABLE_EXPRESSION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_EXPRESSION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_expression_element == null) { throw new RuntimeException(); }
    _result.addChild(table_expression_element.unparse());


    return _result;
  }

}
