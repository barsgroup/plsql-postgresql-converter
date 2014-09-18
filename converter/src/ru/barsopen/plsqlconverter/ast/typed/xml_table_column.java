package ru.barsopen.plsqlconverter.ast.typed;
public class xml_table_column implements _baseNode {
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
  public xml_column_name xml_column_name = null;
  public xml_column_name get_xml_column_name() { return this.xml_column_name; }
  public void set_xml_column_name(xml_column_name value) {
    if (this.xml_column_name != null) { this.xml_column_name._setParent(null); }
    this.xml_column_name = value;
    if (this.xml_column_name != null) { this.xml_column_name._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree ORDINALITY_VK = null;
  public org.antlr.runtime.tree.Tree get_ORDINALITY_VK() { return this.ORDINALITY_VK; }
  public void set_ORDINALITY_VK(org.antlr.runtime.tree.Tree value) {
    this.ORDINALITY_VK = value;
  }
  public boolean is_ORDINALITY_VK() { return this.ORDINALITY_VK != null; }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }
  public boolean is_type_spec() { return this.type_spec != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public boolean is_expression() { return this.expression != null; }
  public xml_general_default_part xml_general_default_part = null;
  public xml_general_default_part get_xml_general_default_part() { return this.xml_general_default_part; }
  public void set_xml_general_default_part(xml_general_default_part value) {
    if (this.xml_general_default_part != null) { this.xml_general_default_part._setParent(null); }
    this.xml_general_default_part = value;
    if (this.xml_general_default_part != null) { this.xml_general_default_part._setParent(this); }
  }
  public boolean is_xml_general_default_part() { return this.xml_general_default_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.xml_column_name != null) {
      this.xml_column_name._walk(visitor);
    }
    if (this.ORDINALITY_VK != null) {
      visitor.visit(this.ORDINALITY_VK);
    }
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.xml_general_default_part != null) {
      this.xml_general_default_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XML_COLUMN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XML_COLUMN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (xml_column_name == null) { throw new RuntimeException(); }
    _result.addChild(xml_column_name.unparse());


    if (ORDINALITY_VK != null) {
      _result.addChild(ORDINALITY_VK);
    }


    if (type_spec != null) {
      _result.addChild(type_spec.unparse());
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    if (xml_general_default_part != null) {
      _result.addChild(xml_general_default_part.unparse());
    }


    return _result;
  }

}
