package ru.barsopen.plsqlconverter.ast.typed;
public class factoring_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public query_name query_name = null;
  public java.util.List<column_name> column_names = new java.util.ArrayList<column_name>();
  public subquery subquery = null;
  public order_by_clause order_by_clause = null;
  public boolean is_order_by_clause() { return this.order_by_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FACTORING);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FACTORING");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_name == null) { throw new RuntimeException(); }
    _result.addChild(query_name.unparse());


    for (int i = 0; i < column_names.size(); ++i) {
      _result.addChild(column_names.get(i).unparse());
    }


    if (subquery == null) { throw new RuntimeException(); }
    _result.addChild(subquery.unparse());


    if (order_by_clause != null) {
      _result.addChild(order_by_clause.unparse());
    }


    return _result;
  }

}
