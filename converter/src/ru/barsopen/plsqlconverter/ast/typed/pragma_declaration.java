package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration implements package_obj_spec, declare_spec, _baseNode {
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
  public pragma_declaration_impl pragma_declaration_impl = null;
  public pragma_declaration_impl get_pragma_declaration_impl() { return this.pragma_declaration_impl; }
  public void set_pragma_declaration_impl(pragma_declaration_impl value) {
    if (this.pragma_declaration_impl != null) { this.pragma_declaration_impl._setParent(null); }
    this.pragma_declaration_impl = value;
    if (this.pragma_declaration_impl != null) { this.pragma_declaration_impl._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.pragma_declaration_impl != null) {
      this.pragma_declaration_impl._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PRAGMA_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PRAGMA_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (pragma_declaration_impl == null) { throw new RuntimeException(); }
    _result.addChild(pragma_declaration_impl.unparse());


    return _result;
  }

}
