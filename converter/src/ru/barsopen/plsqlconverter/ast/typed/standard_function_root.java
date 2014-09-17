package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_root implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public xml_param_version_part xml_param_version_part = null;
  public xmlroot_param_standalone_part xmlroot_param_standalone_part = null;
  public boolean is_xmlroot_param_standalone_part() { return this.xmlroot_param_standalone_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLROOT_VK);
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
