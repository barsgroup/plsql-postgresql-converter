package ru.barsopen.plsqlconverter.ast.typed;
public class commit_comment implements commit_statement_additional {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression comment_expr = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COMMENT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COMMENT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (comment_expr == null) { throw new RuntimeException(); }
    _result.addChild(comment_expr.unparse());


    return _result;
  }

}
