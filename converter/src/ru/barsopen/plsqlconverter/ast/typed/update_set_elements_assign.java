package ru.barsopen.plsqlconverter.ast.typed;
public class update_set_elements_assign implements update_set_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<column_name> column_names = new java.util.ArrayList<column_name>();
  public expression_or_subquery expression_or_subquery = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ASSIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ASSIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (column_names.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < column_names.size(); ++i) {
      _result.addChild(column_names.get(i).unparse());
    }


    if (expression_or_subquery == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_subquery.unparse());


    return _result;
  }

}
