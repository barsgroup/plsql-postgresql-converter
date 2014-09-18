package ru.barsopen.plsqlconverter.ast.typed;
public class hosted_variable_name implements assignment_target, expression_element, _baseNode {
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
  public org.antlr.runtime.tree.Tree BINDVAR = null;
  public org.antlr.runtime.tree.Tree get_BINDVAR() { return this.BINDVAR; }
  public void set_BINDVAR(org.antlr.runtime.tree.Tree value) {
    this.BINDVAR = value;
  }
  public boolean is_BINDVAR() { return this.BINDVAR != null; }
  public org.antlr.runtime.tree.Tree UNSIGNED_INTEGER = null;
  public org.antlr.runtime.tree.Tree get_UNSIGNED_INTEGER() { return this.UNSIGNED_INTEGER; }
  public void set_UNSIGNED_INTEGER(org.antlr.runtime.tree.Tree value) {
    this.UNSIGNED_INTEGER = value;
  }
  public boolean is_UNSIGNED_INTEGER() { return this.UNSIGNED_INTEGER != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.BINDVAR != null) {
      visitor.visit(this.BINDVAR);
    }
    if (this.UNSIGNED_INTEGER != null) {
      visitor.visit(this.UNSIGNED_INTEGER);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.HOSTED_VARIABLE_NAME);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("HOSTED_VARIABLE_NAME");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (BINDVAR != null) {
      _result.addChild(BINDVAR);
    }


    if (UNSIGNED_INTEGER != null) {
      _result.addChild(UNSIGNED_INTEGER);
    }


    return _result;
  }

}
