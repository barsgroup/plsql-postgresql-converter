package ru.barsopen.plsqlconverter.ast.typed;
public class windowing_elements_unbounded_preceding implements windowing_elements, _baseNode {
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
  public org.antlr.runtime.tree.Tree PRECEDING_VK = null;
  public org.antlr.runtime.tree.Tree get_PRECEDING_VK() { return this.PRECEDING_VK; }
  public void set_PRECEDING_VK(org.antlr.runtime.tree.Tree value) {
    this.PRECEDING_VK = value;
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.UNBOUNDED_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("UNBOUNDED_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (PRECEDING_VK == null) { throw new RuntimeException(); }
    _result.addChild(PRECEDING_VK);


    return _result;
  }

}
