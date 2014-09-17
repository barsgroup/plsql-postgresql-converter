package ru.barsopen.plsqlconverter.ast.typed;
public class xml_table_column {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public xml_column_name xml_column_name = null;
  public org.antlr.runtime.tree.Tree ORDINALITY_VK = null;
  public boolean is_ORDINALITY_VK() { return this.ORDINALITY_VK != null; }
  public type_spec type_spec = null;
  public boolean is_type_spec() { return this.type_spec != null; }
  public expression expression = null;
  public boolean is_expression() { return this.expression != null; }
  public xml_general_default_part xml_general_default_part = null;
  public boolean is_xml_general_default_part() { return this.xml_general_default_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XML_COLUMN);
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
