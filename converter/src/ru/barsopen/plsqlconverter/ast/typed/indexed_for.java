package ru.barsopen.plsqlconverter.ast.typed;
public class indexed_for implements cursor_loop_param, _baseNode {
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
  public index_name index_name = null;
  public index_name get_index_name() { return this.index_name; }
  public void set_index_name(index_name value) {
    if (this.index_name != null) { this.index_name._setParent(null); }
    this.index_name = value;
    if (this.index_name != null) { this.index_name._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree REVERSE_VK = null;
  public org.antlr.runtime.tree.Tree get_REVERSE_VK() { return this.REVERSE_VK; }
  public void set_REVERSE_VK(org.antlr.runtime.tree.Tree value) {
    this.REVERSE_VK = value;
  }
  public boolean is_REVERSE_VK() { return this.REVERSE_VK != null; }
  public indexed_for_bounds indexed_for_bounds = null;
  public indexed_for_bounds get_indexed_for_bounds() { return this.indexed_for_bounds; }
  public void set_indexed_for_bounds(indexed_for_bounds value) {
    if (this.indexed_for_bounds != null) { this.indexed_for_bounds._setParent(null); }
    this.indexed_for_bounds = value;
    if (this.indexed_for_bounds != null) { this.indexed_for_bounds._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.index_name != null) {
      this.index_name._walk(visitor);
    }
    if (this.REVERSE_VK != null) {
      visitor.visit(this.REVERSE_VK);
    }
    if (this.indexed_for_bounds != null) {
      this.indexed_for_bounds._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INDEXED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INDEXED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (index_name == null) { throw new RuntimeException(); }
    _result.addChild(index_name.unparse());


    if (REVERSE_VK != null) {
      _result.addChild(REVERSE_VK);
    }


    if (indexed_for_bounds == null) { throw new RuntimeException(); }
    _result.addChild(indexed_for_bounds.unparse());


    return _result;
  }

}
