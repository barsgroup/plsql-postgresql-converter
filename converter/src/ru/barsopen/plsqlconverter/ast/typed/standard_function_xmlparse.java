package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_xmlparse implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public xmlparse_document_or_content xmlparse_document_or_content = null;
  public expression expression = null;
  public org.antlr.runtime.tree.Tree WELLFORMED_VK = null;
  public boolean is_WELLFORMED_VK() { return this.WELLFORMED_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLPARSE_VK);
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
