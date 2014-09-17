package ru.barsopen.plsqlconverter.ast.typed;
public class raise_statement implements statement, _baseNode {
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
  public exception_name exception_name = null;
  public exception_name get_exception_name() { return this.exception_name; }
  public void set_exception_name(exception_name value) {
    if (this.exception_name != null) { this.exception_name._setParent(null); }
    this.exception_name = value;
    if (this.exception_name != null) { this.exception_name._setParent(this); }
  }
  public boolean is_exception_name() { return this.exception_name != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RAISE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RAISE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_name != null) {
      _result.addChild(exception_name.unparse());
    }


    return _result;
  }

}
