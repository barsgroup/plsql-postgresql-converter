package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_xmlelement implements standard_function, _baseNode {
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
  public org.antlr.runtime.tree.Tree ENTITYESCAPING_VK = null;
  public org.antlr.runtime.tree.Tree get_ENTITYESCAPING_VK() { return this.ENTITYESCAPING_VK; }
  public void set_ENTITYESCAPING_VK(org.antlr.runtime.tree.Tree value) {
    this.ENTITYESCAPING_VK = value;
  }
  public boolean is_ENTITYESCAPING_VK() { return this.ENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree NOENTITYESCAPING_VK = null;
  public org.antlr.runtime.tree.Tree get_NOENTITYESCAPING_VK() { return this.NOENTITYESCAPING_VK; }
  public void set_NOENTITYESCAPING_VK(org.antlr.runtime.tree.Tree value) {
    this.NOENTITYESCAPING_VK = value;
  }
  public boolean is_NOENTITYESCAPING_VK() { return this.NOENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree NAME_VK = null;
  public org.antlr.runtime.tree.Tree get_NAME_VK() { return this.NAME_VK; }
  public void set_NAME_VK(org.antlr.runtime.tree.Tree value) {
    this.NAME_VK = value;
  }
  public boolean is_NAME_VK() { return this.NAME_VK != null; }
  public org.antlr.runtime.tree.Tree EVALNAME_VK = null;
  public org.antlr.runtime.tree.Tree get_EVALNAME_VK() { return this.EVALNAME_VK; }
  public void set_EVALNAME_VK(org.antlr.runtime.tree.Tree value) {
    this.EVALNAME_VK = value;
  }
  public boolean is_EVALNAME_VK() { return this.EVALNAME_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public xml_attributes_clause xml_attributes_clause = null;
  public xml_attributes_clause get_xml_attributes_clause() { return this.xml_attributes_clause; }
  public void set_xml_attributes_clause(xml_attributes_clause value) {
    if (this.xml_attributes_clause != null) { this.xml_attributes_clause._setParent(null); }
    this.xml_attributes_clause = value;
    if (this.xml_attributes_clause != null) { this.xml_attributes_clause._setParent(this); }
  }
  public boolean is_xml_attributes_clause() { return this.xml_attributes_clause != null; }
  public java.util.List<xmlelement_value_expr> xmlelement_value_exprs = new java.util.ArrayList<xmlelement_value_expr>();
  public java.util.List<xmlelement_value_expr> get_xmlelement_value_exprs() { return this.xmlelement_value_exprs; }
  public void add_xmlelement_value_exprs(xmlelement_value_expr value) {
    insert_xmlelement_value_exprs(xmlelement_value_exprs.size(), value);
  }
  public void insert_xmlelement_value_exprs(int pos, xmlelement_value_expr value) {
    this.xmlelement_value_exprs.add(pos, value);
    value._setParent(this);
  }
  public void remove_xmlelement_value_exprs(int pos) {
    this.xmlelement_value_exprs.get(pos)._setParent(null);
    this.xmlelement_value_exprs.remove(pos);
  }
  public void remove_xmlelement_value_exprs(xmlelement_value_expr value) {
    this.remove_xmlelement_value_exprs(this.xmlelement_value_exprs.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.ENTITYESCAPING_VK != null) {
      visitor.visit(this.ENTITYESCAPING_VK);
    }
    if (this.NOENTITYESCAPING_VK != null) {
      visitor.visit(this.NOENTITYESCAPING_VK);
    }
    if (this.NAME_VK != null) {
      visitor.visit(this.NAME_VK);
    }
    if (this.EVALNAME_VK != null) {
      visitor.visit(this.EVALNAME_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.xml_attributes_clause != null) {
      this.xml_attributes_clause._walk(visitor);
    }
    for (xmlelement_value_expr _value: this.xmlelement_value_exprs) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.xml_attributes_clause == child) {
      this.set_xml_attributes_clause((ru.barsopen.plsqlconverter.ast.typed.xml_attributes_clause)replacement);
      return;
    }
    for (int _i = 0; _i < this.xmlelement_value_exprs.size(); ++_i) {
      if (this.xmlelement_value_exprs.get(_i) == child) {
        this.remove_xmlelement_value_exprs(_i);
        this.insert_xmlelement_value_exprs(_i, (ru.barsopen.plsqlconverter.ast.typed.xmlelement_value_expr)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XMLELEMENT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLELEMENT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (ENTITYESCAPING_VK != null) {
      _result.addChild(ENTITYESCAPING_VK);
    }


    if (NOENTITYESCAPING_VK != null) {
      _result.addChild(NOENTITYESCAPING_VK);
    }


    if (NAME_VK != null) {
      _result.addChild(NAME_VK);
    }


    if (EVALNAME_VK != null) {
      _result.addChild(EVALNAME_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (xml_attributes_clause != null) {
      _result.addChild(xml_attributes_clause.unparse());
    }


    for (int i = 0; i < xmlelement_value_exprs.size(); ++i) {
      _result.addChild(xmlelement_value_exprs.get(i).unparse());
    }


    return _result;
  }

}
