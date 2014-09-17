package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_xmlelement implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree ENTITYESCAPING_VK = null;
  public boolean is_ENTITYESCAPING_VK() { return this.ENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree NOENTITYESCAPING_VK = null;
  public boolean is_NOENTITYESCAPING_VK() { return this.NOENTITYESCAPING_VK != null; }
  public org.antlr.runtime.tree.Tree NAME_VK = null;
  public boolean is_NAME_VK() { return this.NAME_VK != null; }
  public org.antlr.runtime.tree.Tree EVALNAME_VK = null;
  public boolean is_EVALNAME_VK() { return this.EVALNAME_VK != null; }
  public expression expression = null;
  public xml_attributes_clause xml_attributes_clause = null;
  public boolean is_xml_attributes_clause() { return this.xml_attributes_clause != null; }
  public java.util.List<xmlelement_value_expr> xmlelement_value_exprs = new java.util.ArrayList<xmlelement_value_expr>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLELEMENT_VK);
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
