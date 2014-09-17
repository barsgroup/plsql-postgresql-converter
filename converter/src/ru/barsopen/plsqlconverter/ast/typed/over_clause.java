package ru.barsopen.plsqlconverter.ast.typed;
public class over_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public query_partition_clause query_partition_clause = null;
  public boolean is_query_partition_clause() { return this.query_partition_clause != null; }
  public order_by_clause order_by_clause = null;
  public boolean is_order_by_clause() { return this.order_by_clause != null; }
  public windowing_clause windowing_clause = null;
  public boolean is_windowing_clause() { return this.windowing_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.OVER_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("OVER_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_partition_clause != null) {
      _result.addChild(query_partition_clause.unparse());
    }


    if (order_by_clause != null) {
      _result.addChild(order_by_clause.unparse());
    }


    if (windowing_clause != null) {
      _result.addChild(windowing_clause.unparse());
    }


    return _result;
  }

}
