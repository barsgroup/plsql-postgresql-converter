package ru.barsopen.plsqlconverter.ast.typed;
public class drop_package implements unit_statement, _baseNode {
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
  public package_name package_name = null;
  public package_name get_package_name() { return this.package_name; }
  public void set_package_name(package_name value) {
    if (this.package_name != null) { this.package_name._setParent(null); }
    this.package_name = value;
    if (this.package_name != null) { this.package_name._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree BODY_VK = null;
  public org.antlr.runtime.tree.Tree get_BODY_VK() { return this.BODY_VK; }
  public void set_BODY_VK(org.antlr.runtime.tree.Tree value) {
    this.BODY_VK = value;
  }
  public boolean is_BODY_VK() { return this.BODY_VK != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.package_name != null) {
      this.package_name._walk(visitor);
    }
    if (this.BODY_VK != null) {
      visitor.visit(this.BODY_VK);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.DROP_PACKAGE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DROP_PACKAGE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (package_name == null) { throw new RuntimeException(); }
    _result.addChild(package_name.unparse());


    if (BODY_VK != null) {
      _result.addChild(BODY_VK);
    }


    return _result;
  }

}
