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
  public query_partition_clause_impl query_partition_clause_impl = null;
  public query_partition_clause_impl get_query_partition_clause_impl() { return this.query_partition_clause_impl; }
  public void set_query_partition_clause_impl(query_partition_clause_impl value) {
    if (this.query_partition_clause_impl != null) { this.query_partition_clause_impl._setParent(null); }
    this.query_partition_clause_impl = value;
    if (this.query_partition_clause_impl != null) { this.query_partition_clause_impl._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.query_partition_clause_impl != null) {
      this.query_partition_clause_impl._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARTITION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARTITION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_partition_clause_impl == null) { throw new RuntimeException(); }
    _result.addChild(query_partition_clause_impl.unparse());


    return _result;
  }

}
