package ru.barsopen.plsqlconverter.ast.typed;
public class dynamic_returning_clause implements _baseNode {
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
  public into_clause into_clause = null;
  public into_clause get_into_clause() { return this.into_clause; }
  public void set_into_clause(into_clause value) {
    if (this.into_clause != null) { this.into_clause._setParent(null); }
    this.into_clause = value;
    if (this.into_clause != null) { this.into_clause._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.into_clause != null) {
      this.into_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.DYNAMIC_RETURN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DYNAMIC_RETURN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (into_clause == null) { throw new RuntimeException(); }
    _result.addChild(into_clause.unparse());


    return _result;
  }

}
