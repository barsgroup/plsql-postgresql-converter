package ru.barsopen.plsqlconverter.ast.typed;
public class groupin_set implements _baseNode {
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
  public grouping_element grouping_element = null;
  public grouping_element get_grouping_element() { return this.grouping_element; }
  public void set_grouping_element(grouping_element value) {
    if (this.grouping_element != null) { this.grouping_element._setParent(null); }
    this.grouping_element = value;
    if (this.grouping_element != null) { this.grouping_element._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.grouping_element != null) {
      this.grouping_element._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.GROUPIN_SET);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("GROUPIN_SET");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (grouping_element == null) { throw new RuntimeException(); }
    _result.addChild(grouping_element.unparse());


    return _result;
  }

}
