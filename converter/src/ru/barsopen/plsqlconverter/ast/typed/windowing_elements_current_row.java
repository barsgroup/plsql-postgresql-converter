package ru.barsopen.plsqlconverter.ast.typed;
public class windowing_elements_current_row implements windowing_elements, _baseNode {
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
  public org.antlr.runtime.tree.Tree ROW_VK = null;
  public org.antlr.runtime.tree.Tree get_ROW_VK() { return this.ROW_VK; }
  public void set_ROW_VK(org.antlr.runtime.tree.Tree value) {
    this.ROW_VK = value;
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CURRENT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CURRENT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (ROW_VK == null) { throw new RuntimeException(); }
    _result.addChild(ROW_VK);


    return _result;
  }

}
