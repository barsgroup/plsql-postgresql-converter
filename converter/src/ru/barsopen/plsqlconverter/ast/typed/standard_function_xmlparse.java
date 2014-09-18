package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_xmlparse implements standard_function, _baseNode {
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
  public xmlparse_document_or_content xmlparse_document_or_content = null;
  public xmlparse_document_or_content get_xmlparse_document_or_content() { return this.xmlparse_document_or_content; }
  public void set_xmlparse_document_or_content(xmlparse_document_or_content value) {
    if (this.xmlparse_document_or_content != null) { this.xmlparse_document_or_content._setParent(null); }
    this.xmlparse_document_or_content = value;
    if (this.xmlparse_document_or_content != null) { this.xmlparse_document_or_content._setParent(this); }
  }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree WELLFORMED_VK = null;
  public org.antlr.runtime.tree.Tree get_WELLFORMED_VK() { return this.WELLFORMED_VK; }
  public void set_WELLFORMED_VK(org.antlr.runtime.tree.Tree value) {
    this.WELLFORMED_VK = value;
  }
  public boolean is_WELLFORMED_VK() { return this.WELLFORMED_VK != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.xmlparse_document_or_content != null) {
      this.xmlparse_document_or_content._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.WELLFORMED_VK != null) {
      visitor.visit(this.WELLFORMED_VK);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XMLPARSE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLPARSE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (xmlparse_document_or_content == null) { throw new RuntimeException(); }
    _result.addChild(xmlparse_document_or_content.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (WELLFORMED_VK != null) {
      _result.addChild(WELLFORMED_VK);
    }


    return _result;
  }

}
