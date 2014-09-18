package ru.barsopen.plsqlconverter.ast.typed;
public class query_partition_clause implements _baseNode {
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
  public query_partition_clause_spec query_partition_clause_spec = null;
  public query_partition_clause_spec get_query_partition_clause_spec() { return this.query_partition_clause_spec; }
  public void set_query_partition_clause_spec(query_partition_clause_spec value) {
    if (this.query_partition_clause_spec != null) { this.query_partition_clause_spec._setParent(null); }
    this.query_partition_clause_spec = value;
    if (this.query_partition_clause_spec != null) { this.query_partition_clause_spec._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.query_partition_clause_spec != null) {
      this.query_partition_clause_spec._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.query_partition_clause_spec == child) {
      this.set_query_partition_clause_spec((ru.barsopen.plsqlconverter.ast.typed.query_partition_clause_spec)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PARTITION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARTITION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_partition_clause_spec == null) { throw new RuntimeException(); }
    _result.addChild(query_partition_clause_spec.unparse());


    return _result;
  }

}
