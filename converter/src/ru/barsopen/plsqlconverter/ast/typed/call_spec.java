package ru.barsopen.plsqlconverter.ast.typed;
public class call_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public call_spec_decl call_spec_decl = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LANGUAGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LANGUAGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (call_spec_decl == null) { throw new RuntimeException(); }
    _result.addChild(call_spec_decl.unparse());


    return _result;
  }

}
