package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_table implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public xml_namespaces_clause xml_namespaces_clause = null;
  public boolean is_xml_namespaces_clause() { return this.xml_namespaces_clause != null; }
  public expression expression = null;
  public xml_passing_clause xml_passing_clause = null;
  public boolean is_xml_passing_clause() { return this.xml_passing_clause != null; }
  public java.util.List<xml_table_column> xml_table_columns = new java.util.ArrayList<xml_table_column>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLTABLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLTABLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (xml_namespaces_clause != null) {
      _result.addChild(xml_namespaces_clause.unparse());
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (xml_passing_clause != null) {
      _result.addChild(xml_passing_clause.unparse());
    }


    for (int i = 0; i < xml_table_columns.size(); ++i) {
      _result.addChild(xml_table_columns.get(i).unparse());
    }


    return _result;
  }

}
