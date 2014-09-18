package ru.barsopen.plsqlconverter.ast.typed;
public class perform_statement implements statement, _baseNode {
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
  public general_element_or_dml_statement inner = null;
  public general_element_or_dml_statement get_inner() { return this.inner; }
  public void set_inner(general_element_or_dml_statement value) {
    if (this.inner != null) { this.inner._setParent(null); }
    this.inner = value;
    if (this.inner != null) { this.inner._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.inner != null) {
      this.inner._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.inner == child) {
      this.set_inner((ru.barsopen.plsqlconverter.ast.typed.general_element_or_dml_statement)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PGSQL_PERFORM);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PGSQL_PERFORM");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (inner == null) { throw new RuntimeException(); }
    _result.addChild(inner.unparse());


    return _result;
  }

}
