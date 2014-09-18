package ru.barsopen.plsqlconverter.ast.typed;
public class hierarchical_query_clause implements _baseNode {
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
  public start_part start_part = null;
  public start_part get_start_part() { return this.start_part; }
  public void set_start_part(start_part value) {
    if (this.start_part != null) { this.start_part._setParent(null); }
    this.start_part = value;
    if (this.start_part != null) { this.start_part._setParent(this); }
  }
  public boolean is_start_part() { return this.start_part != null; }
  public hierarchical_query_clause_connect hierarchical_query_clause_connect = null;
  public hierarchical_query_clause_connect get_hierarchical_query_clause_connect() { return this.hierarchical_query_clause_connect; }
  public void set_hierarchical_query_clause_connect(hierarchical_query_clause_connect value) {
    if (this.hierarchical_query_clause_connect != null) { this.hierarchical_query_clause_connect._setParent(null); }
    this.hierarchical_query_clause_connect = value;
    if (this.hierarchical_query_clause_connect != null) { this.hierarchical_query_clause_connect._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.start_part != null) {
      this.start_part._walk(visitor);
    }
    if (this.hierarchical_query_clause_connect != null) {
      this.hierarchical_query_clause_connect._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.HIERARCHICAL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("HIERARCHICAL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (start_part != null) {
      _result.addChild(start_part.unparse());
    }


    if (hierarchical_query_clause_connect == null) { throw new RuntimeException(); }
    _result.addChild(hierarchical_query_clause_connect.unparse());


    return _result;
  }

}
