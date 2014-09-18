package ru.barsopen.plsqlconverter.ast.typed;
public class drop_function implements unit_statement, _baseNode {
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
  public function_name function_name = null;
  public function_name get_function_name() { return this.function_name; }
  public void set_function_name(function_name value) {
    if (this.function_name != null) { this.function_name._setParent(null); }
    this.function_name = value;
    if (this.function_name != null) { this.function_name._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.function_name != null) {
      this.function_name._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.function_name == child) {
      this.set_function_name((ru.barsopen.plsqlconverter.ast.typed.function_name)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_FUNCTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DROP_FUNCTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (function_name == null) { throw new RuntimeException(); }
    _result.addChild(function_name.unparse());


    return _result;
  }

}
