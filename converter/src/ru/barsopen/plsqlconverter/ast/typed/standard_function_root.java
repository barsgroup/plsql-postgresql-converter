package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_root implements standard_function, _baseNode {
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
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public xml_param_version_part xml_param_version_part = null;
  public xml_param_version_part get_xml_param_version_part() { return this.xml_param_version_part; }
  public void set_xml_param_version_part(xml_param_version_part value) {
    if (this.xml_param_version_part != null) { this.xml_param_version_part._setParent(null); }
    this.xml_param_version_part = value;
    if (this.xml_param_version_part != null) { this.xml_param_version_part._setParent(this); }
  }
  public xmlroot_param_standalone_part xmlroot_param_standalone_part = null;
  public xmlroot_param_standalone_part get_xmlroot_param_standalone_part() { return this.xmlroot_param_standalone_part; }
  public void set_xmlroot_param_standalone_part(xmlroot_param_standalone_part value) {
    if (this.xmlroot_param_standalone_part != null) { this.xmlroot_param_standalone_part._setParent(null); }
    this.xmlroot_param_standalone_part = value;
    if (this.xmlroot_param_standalone_part != null) { this.xmlroot_param_standalone_part._setParent(this); }
  }
  public boolean is_xmlroot_param_standalone_part() { return this.xmlroot_param_standalone_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.xml_param_version_part != null) {
      this.xml_param_version_part._walk(visitor);
    }
    if (this.xmlroot_param_standalone_part != null) {
      this.xmlroot_param_standalone_part._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.xml_param_version_part == child) {
      this.set_xml_param_version_part((ru.barsopen.plsqlconverter.ast.typed.xml_param_version_part)replacement);
      return;
    }
    if (this.xmlroot_param_standalone_part == child) {
      this.set_xmlroot_param_standalone_part((ru.barsopen.plsqlconverter.ast.typed.xmlroot_param_standalone_part)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XMLROOT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLROOT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (xml_param_version_part == null) { throw new RuntimeException(); }
    _result.addChild(xml_param_version_part.unparse());


    if (xmlroot_param_standalone_part != null) {
      _result.addChild(xmlroot_param_standalone_part.unparse());
    }


    return _result;
  }

}
