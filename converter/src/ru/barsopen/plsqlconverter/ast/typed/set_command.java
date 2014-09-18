package ru.barsopen.plsqlconverter.ast.typed;
public class set_command implements sql_plus_command, _baseNode {
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
  public org.antlr.runtime.tree.Tree content = null;
  public org.antlr.runtime.tree.Tree get_content() { return this.content; }
  public void set_content(org.antlr.runtime.tree.Tree value) {
    this.content = value;
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.content != null) {
      visitor.visit(this.content);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SET_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SET_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (content == null) { throw new RuntimeException(); }
    _result.addChild(content);


    return _result;
  }

}
