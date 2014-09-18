package ru.barsopen.plsqlconverter.ast.typed;
public class java_spec implements call_spec_decl, _baseNode {
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
  public org.antlr.runtime.tree.Tree CHAR_STRING = null;
  public org.antlr.runtime.tree.Tree get_CHAR_STRING() { return this.CHAR_STRING; }
  public void set_CHAR_STRING(org.antlr.runtime.tree.Tree value) {
    this.CHAR_STRING = value;
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.CHAR_STRING != null) {
      visitor.visit(this.CHAR_STRING);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.JAVA_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("JAVA_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (CHAR_STRING == null) { throw new RuntimeException(); }
    _result.addChild(CHAR_STRING);


    return _result;
  }

}
