package ru.barsopen.plsqlconverter.ast.typed;
public class over_clause implements _baseNode {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public _baseNode _parent = null;
  public _baseNode _getParent() { return _parent; }
  public void _setParent(_baseNode value) { _parent = value; }
  public void _setBaseNode(_baseNode value) { this._parent = value; }
  public int _getLine() { return _line; }
  public int _getCol() { return _col; }
  public int _getTokenStartIndex() { return _tokenStartIndex; }
  public int _getTokenStopIndex() { return _tokenStopIndex; }
  public query_partition_clause query_partition_clause = null;
  public query_partition_clause get_query_partition_clause() { return this.query_partition_clause; }
  public void set_query_partition_clause(query_partition_clause value) {
    if (this.query_partition_clause != null) { this.query_partition_clause._setParent(null); }
    this.query_partition_clause = value;
    if (this.query_partition_clause != null) { this.query_partition_clause._setParent(this); }
  }
  public boolean is_query_partition_clause() { return this.query_partition_clause != null; }
  public order_by_clause order_by_clause = null;
  public order_by_clause get_order_by_clause() { return this.order_by_clause; }
  public void set_order_by_clause(order_by_clause value) {
    if (this.order_by_clause != null) { this.order_by_clause._setParent(null); }
    this.order_by_clause = value;
    if (this.order_by_clause != null) { this.order_by_clause._setParent(this); }
  }
  public boolean is_order_by_clause() { return this.order_by_clause != null; }
  public windowing_clause windowing_clause = null;
  public windowing_clause get_windowing_clause() { return this.windowing_clause; }
  public void set_windowing_clause(windowing_clause value) {
    if (this.windowing_clause != null) { this.windowing_clause._setParent(null); }
    this.windowing_clause = value;
    if (this.windowing_clause != null) { this.windowing_clause._setParent(this); }
  }
  public boolean is_windowing_clause() { return this.windowing_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.query_partition_clause != null) {
      this.query_partition_clause._walk(visitor);
    }
    if (this.order_by_clause != null) {
      this.order_by_clause._walk(visitor);
    }
    if (this.windowing_clause != null) {
      this.windowing_clause._walk(visitor);
    }
  }
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
