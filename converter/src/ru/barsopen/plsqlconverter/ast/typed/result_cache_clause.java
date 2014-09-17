package ru.barsopen.plsqlconverter.ast.typed;
public class result_cache_clause implements _baseNode {
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
  public relies_on_part relies_on_part = null;
  public relies_on_part get_relies_on_part() { return this.relies_on_part; }
  public void set_relies_on_part(relies_on_part value) {
    if (this.relies_on_part != null) { this.relies_on_part._setParent(null); }
    this.relies_on_part = value;
    if (this.relies_on_part != null) { this.relies_on_part._setParent(this); }
  }
  public boolean is_relies_on_part() { return this.relies_on_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.relies_on_part != null) {
      this.relies_on_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RESULT_CACHE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RESULT_CACHE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (relies_on_part != null) {
      _result.addChild(relies_on_part.unparse());
    }


    return _result;
  }

}
