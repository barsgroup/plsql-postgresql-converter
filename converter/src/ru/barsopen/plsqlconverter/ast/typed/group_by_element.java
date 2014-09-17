package ru.barsopen.plsqlconverter.ast.typed;
public class group_by_element implements _baseNode {
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
  public group_by_elements group_by_elements = null;
  public group_by_elements get_group_by_elements() { return this.group_by_elements; }
  public void set_group_by_elements(group_by_elements value) {
    if (this.group_by_elements != null) { this.group_by_elements._setParent(null); }
    this.group_by_elements = value;
    if (this.group_by_elements != null) { this.group_by_elements._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.GROUP_BY_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("GROUP_BY_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (group_by_elements == null) { throw new RuntimeException(); }
    _result.addChild(group_by_elements.unparse());


    return _result;
  }

}
