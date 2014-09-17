package ru.barsopen.plsqlconverter.ast.typed;
public class commit_force_xid_all implements commit_force_content {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CORRUPT_XID_ALL_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CORRUPT_XID_ALL_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    return _result;
  }

}
