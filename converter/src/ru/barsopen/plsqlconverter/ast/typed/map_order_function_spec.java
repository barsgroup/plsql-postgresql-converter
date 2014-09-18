package ru.barsopen.plsqlconverter.ast.typed;
public class map_order_function_spec implements _baseNode {
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
  public java.util.List<org.antlr.runtime.tree.Tree> contents = new java.util.ArrayList<org.antlr.runtime.tree.Tree>();
  public java.util.List<org.antlr.runtime.tree.Tree> get_contents() { return this.contents; }
  public void add_contents(org.antlr.runtime.tree.Tree value) {
    insert_contents(contents.size(), value);
  }
  public void insert_contents(int pos, org.antlr.runtime.tree.Tree value) {
    this.contents.add(pos, value);
  }
  public void remove_contents(int pos) {
    this.contents.remove(pos);
  }
  public void remove_contents(org.antlr.runtime.tree.Tree value) {
    this.remove_contents(this.contents.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (org.antlr.runtime.tree.Tree _value: this.contents) {
      visitor.visit(_value);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("NON_DML");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < contents.size(); ++i) {
      _result.addChild(contents.get(i));
    }


    return _result;
  }

}
