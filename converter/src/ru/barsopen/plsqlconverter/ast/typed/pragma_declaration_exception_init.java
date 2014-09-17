package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration_exception_init implements pragma_declaration_impl, _baseNode {
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
  public exception_name exception_name = null;
  public exception_name get_exception_name() { return this.exception_name; }
  public void set_exception_name(exception_name value) {
    if (this.exception_name != null) { this.exception_name._setParent(null); }
    this.exception_name = value;
    if (this.exception_name != null) { this.exception_name._setParent(this); }
  }
  public constant constant = null;
  public constant get_constant() { return this.constant; }
  public void set_constant(constant value) {
    if (this.constant != null) { this.constant._setParent(null); }
    this.constant = value;
    if (this.constant != null) { this.constant._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXCEPTION_INIT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXCEPTION_INIT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_name == null) { throw new RuntimeException(); }
    _result.addChild(exception_name.unparse());


    if (constant == null) { throw new RuntimeException(); }
    _result.addChild(constant.unparse());


    return _result;
  }

}
