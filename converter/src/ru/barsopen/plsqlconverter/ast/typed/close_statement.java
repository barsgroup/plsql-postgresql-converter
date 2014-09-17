package ru.barsopen.plsqlconverter.ast.typed;
public class close_statement implements cursor_manipulation_statements, _baseNode {
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
  public cursor_name cursor_name = null;
  public cursor_name get_cursor_name() { return this.cursor_name; }
  public void set_cursor_name(cursor_name value) {
    if (this.cursor_name != null) { this.cursor_name._setParent(null); }
    this.cursor_name = value;
    if (this.cursor_name != null) { this.cursor_name._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CLOSE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CLOSE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (cursor_name == null) { throw new RuntimeException(); }
    _result.addChild(cursor_name.unparse());


    return _result;
  }

}
