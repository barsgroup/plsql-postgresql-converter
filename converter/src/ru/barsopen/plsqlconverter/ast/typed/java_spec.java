package ru.barsopen.plsqlconverter.ast.typed;
public class java_spec implements call_spec_decl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree CHAR_STRING = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.JAVA_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("JAVA_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (CHAR_STRING == null) { throw new RuntimeException(); }
    _result.addChild(CHAR_STRING);


    return _result;
  }

}
