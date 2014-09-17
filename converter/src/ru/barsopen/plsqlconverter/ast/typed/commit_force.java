package ru.barsopen.plsqlconverter.ast.typed;
public class commit_force implements commit_statement_additional {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public commit_force_content commit_force_content = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FORCE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FORCE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (commit_force_content == null) { throw new RuntimeException(); }
    _result.addChild(commit_force_content.unparse());


    return _result;
  }

}
