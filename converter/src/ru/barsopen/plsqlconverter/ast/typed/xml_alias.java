package ru.barsopen.plsqlconverter.ast.typed;
public class xml_alias implements _baseNode {
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
  public id_or_evalname id_or_evalname = null;
  public id_or_evalname get_id_or_evalname() { return this.id_or_evalname; }
  public void set_id_or_evalname(id_or_evalname value) {
    if (this.id_or_evalname != null) { this.id_or_evalname._setParent(null); }
    this.id_or_evalname = value;
    if (this.id_or_evalname != null) { this.id_or_evalname._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.id_or_evalname != null) {
      this.id_or_evalname._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.id_or_evalname == child) {
      this.set_id_or_evalname((ru.barsopen.plsqlconverter.ast.typed.id_or_evalname)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XML_ALIAS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XML_ALIAS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (id_or_evalname == null) { throw new RuntimeException(); }
    _result.addChild(id_or_evalname.unparse());


    return _result;
  }

}
