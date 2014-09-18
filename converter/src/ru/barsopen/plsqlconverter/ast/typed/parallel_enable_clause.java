package ru.barsopen.plsqlconverter.ast.typed;
public class parallel_enable_clause implements _baseNode {
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
  public partition_by_clause partition_by_clause = null;
  public partition_by_clause get_partition_by_clause() { return this.partition_by_clause; }
  public void set_partition_by_clause(partition_by_clause value) {
    if (this.partition_by_clause != null) { this.partition_by_clause._setParent(null); }
    this.partition_by_clause = value;
    if (this.partition_by_clause != null) { this.partition_by_clause._setParent(this); }
  }
  public boolean is_partition_by_clause() { return this.partition_by_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.partition_by_clause != null) {
      this.partition_by_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PARALLEL_ENABLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARALLEL_ENABLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (partition_by_clause != null) {
      _result.addChild(partition_by_clause.unparse());
    }


    return _result;
  }

}
