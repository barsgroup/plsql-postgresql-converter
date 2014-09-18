package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_clauses_dimension implements _baseNode {
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
  public model_column_list model_column_list = null;
  public model_column_list get_model_column_list() { return this.model_column_list; }
  public void set_model_column_list(model_column_list value) {
    if (this.model_column_list != null) { this.model_column_list._setParent(null); }
    this.model_column_list = value;
    if (this.model_column_list != null) { this.model_column_list._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.model_column_list != null) {
      this.model_column_list._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.model_column_list == child) {
      this.set_model_column_list((ru.barsopen.plsqlconverter.ast.typed.model_column_list)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.DIMENSION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DIMENSION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_column_list == null) { throw new RuntimeException(); }
    _result.addChild(model_column_list.unparse());


    return _result;
  }

}
