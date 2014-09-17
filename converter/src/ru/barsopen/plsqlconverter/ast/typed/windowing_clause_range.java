package ru.barsopen.plsqlconverter.ast.typed;
public class windowing_clause_range implements windowing_clause, _baseNode {
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
  public windowing_clause_spec windowing_clause_spec = null;
  public windowing_clause_spec get_windowing_clause_spec() { return this.windowing_clause_spec; }
  public void set_windowing_clause_spec(windowing_clause_spec value) {
    if (this.windowing_clause_spec != null) { this.windowing_clause_spec._setParent(null); }
    this.windowing_clause_spec = value;
    if (this.windowing_clause_spec != null) { this.windowing_clause_spec._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.windowing_clause_spec != null) {
      this.windowing_clause_spec._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RANGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RANGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (windowing_clause_spec == null) { throw new RuntimeException(); }
    _result.addChild(windowing_clause_spec.unparse());


    return _result;
  }

}
