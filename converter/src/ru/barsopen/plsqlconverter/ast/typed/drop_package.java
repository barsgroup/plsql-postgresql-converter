package ru.barsopen.plsqlconverter.ast.typed;
public class drop_package implements unit_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public package_name package_name = null;
  public org.antlr.runtime.tree.Tree BODY_VK = null;
  public boolean is_BODY_VK() { return this.BODY_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.DROP_PACKAGE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DROP_PACKAGE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (package_name == null) { throw new RuntimeException(); }
    _result.addChild(package_name.unparse());


    if (BODY_VK != null) {
      _result.addChild(BODY_VK);
    }


    return _result;
  }

}
