package ru.barsopen.plsqlconverter.ast.typed;
public class selected_tableview implements _baseNode {
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
  public alias alias = null;
  public alias get_alias() { return this.alias; }
  public void set_alias(alias value) {
    if (this.alias != null) { this.alias._setParent(null); }
    this.alias = value;
    if (this.alias != null) { this.alias._setParent(this); }
  }
  public boolean is_alias() { return this.alias != null; }
  public selected_tableview_src selected_tableview_src = null;
  public selected_tableview_src get_selected_tableview_src() { return this.selected_tableview_src; }
  public void set_selected_tableview_src(selected_tableview_src value) {
    if (this.selected_tableview_src != null) { this.selected_tableview_src._setParent(null); }
    this.selected_tableview_src = value;
    if (this.selected_tableview_src != null) { this.selected_tableview_src._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.alias != null) {
      this.alias._walk(visitor);
    }
    if (this.selected_tableview_src != null) {
      this.selected_tableview_src._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.alias == child) {
      this.set_alias((ru.barsopen.plsqlconverter.ast.typed.alias)replacement);
      return;
    }
    if (this.selected_tableview_src == child) {
      this.set_selected_tableview_src((ru.barsopen.plsqlconverter.ast.typed.selected_tableview_src)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SELECTED_TABLEVIEW);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECTED_TABLEVIEW");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (selected_tableview_src == null) { throw new RuntimeException(); }
    _result.addChild(selected_tableview_src.unparse());


    return _result;
  }

}
