package ru.barsopen.plsqlconverter.ast.typed;
public class conditional_insert_when_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public java.util.List<multi_table_element> multi_table_elements = new java.util.ArrayList<multi_table_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_WHEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WHEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (multi_table_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < multi_table_elements.size(); ++i) {
      _result.addChild(multi_table_elements.get(i).unparse());
    }


    return _result;
  }

}
