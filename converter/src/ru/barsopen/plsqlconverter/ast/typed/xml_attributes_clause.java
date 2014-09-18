package ru.barsopen.plsqlconverter.ast.typed;
public class xml_attributes_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree SCHEMACHECK_VK = null;
  public org.antlr.runtime.tree.Tree get_SCHEMACHECK_VK() { return this.SCHEMACHECK_VK; }
  public void set_SCHEMACHECK_VK(org.antlr.runtime.tree.Tree value) {
    this.SCHEMACHECK_VK = value;
  }
  public boolean is_SCHEMACHECK_VK() { return this.SCHEMACHECK_VK != null; }
  public org.antlr.runtime.tree.Tree NOSCHEMACHECK_VK = null;
  public org.antlr.runtime.tree.Tree get_NOSCHEMACHECK_VK() { return this.NOSCHEMACHECK_VK; }
  public void set_NOSCHEMACHECK_VK(org.antlr.runtime.tree.Tree value) {
    this.NOSCHEMACHECK_VK = value;
  }
  public boolean is_NOSCHEMACHECK_VK() { return this.NOSCHEMACHECK_VK != null; }
  public java.util.List<xml_multiuse_expression_element> xml_multiuse_expression_elements = new java.util.ArrayList<xml_multiuse_expression_element>();
  public java.util.List<xml_multiuse_expression_element> get_xml_multiuse_expression_elements() { return this.xml_multiuse_expression_elements; }
  public void add_xml_multiuse_expression_elements(xml_multiuse_expression_element value) {
    insert_xml_multiuse_expression_elements(xml_multiuse_expression_elements.size(), value);
  }
  public void insert_xml_multiuse_expression_elements(int pos, xml_multiuse_expression_element value) {
    this.xml_multiuse_expression_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_xml_multiuse_expression_elements(int pos) {
    this.xml_multiuse_expression_elements.get(pos)._setParent(null);
    this.xml_multiuse_expression_elements.remove(pos);
  }
  public void remove_xml_multiuse_expression_elements(xml_multiuse_expression_element value) {
    this.remove_xml_multiuse_expression_elements(this.xml_multiuse_expression_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.ENTITYESCAPING_VK != null) {
      visitor.visit(this.ENTITYESCAPING_VK);
    }
    if (this.NOENTITYESCAPING_VK != null) {
      visitor.visit(this.NOENTITYESCAPING_VK);
    }
    if (this.SCHEMACHECK_VK != null) {
      visitor.visit(this.SCHEMACHECK_VK);
    }
    if (this.NOSCHEMACHECK_VK != null) {
      visitor.visit(this.NOSCHEMACHECK_VK);
    }
    for (xml_multiuse_expression_element _value: this.xml_multiuse_expression_elements) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.xml_multiuse_expression_elements.size(); ++_i) {
      if (this.xml_multiuse_expression_elements.get(_i) == child) {
        this.remove_xml_multiuse_expression_elements(_i);
        this.insert_xml_multiuse_expression_elements(_i, (ru.barsopen.plsqlconverter.ast.typed.xml_multiuse_expression_element)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.XMLATTRIBUTES_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLATTRIBUTES_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (ENTITYESCAPING_VK != null) {
      _result.addChild(ENTITYESCAPING_VK);
    }


    if (NOENTITYESCAPING_VK != null) {
      _result.addChild(NOENTITYESCAPING_VK);
    }


    if (SCHEMACHECK_VK != null) {
      _result.addChild(SCHEMACHECK_VK);
    }


    if (NOSCHEMACHECK_VK != null) {
      _result.addChild(NOSCHEMACHECK_VK);
    }


    if (xml_multiuse_expression_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < xml_multiuse_expression_elements.size(); ++i) {
      _result.addChild(xml_multiuse_expression_elements.get(i).unparse());
    }


    return _result;
  }

}
