package ru.barsopen.plsqlconverter.ast.typed;
public class xml_attributes_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree ENTITYESCAPING_VK = null;
  public boolean is_ENTITYESCAPING_VK() { return this.ENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree NOENTITYESCAPING_VK = null;
  public boolean is_NOENTITYESCAPING_VK() { return this.NOENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree SCHEMACHECK_VK = null;
  public boolean is_SCHEMACHECK_VK() { return this.SCHEMACHECK_VK != null; }
  public org.antlr.runtime.tree.Tree NOSCHEMACHECK_VK = null;
  public boolean is_NOSCHEMACHECK_VK() { return this.NOSCHEMACHECK_VK != null; }
  public java.util.List<xml_multiuse_expression_element> xml_multiuse_expression_elements = new java.util.ArrayList<xml_multiuse_expression_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLATTRIBUTES_VK);
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
