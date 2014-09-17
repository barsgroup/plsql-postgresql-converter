package ru.barsopen.plsqlconverter.ast.typed;
public class error_logging_reject_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree UNLIMITED_VK = null;
  public boolean is_UNLIMITED_VK() { return this.UNLIMITED_VK != null; }
  public expression expression = null;
  public boolean is_expression() { return this.expression != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.REJECT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("REJECT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (UNLIMITED_VK != null) {
      _result.addChild(UNLIMITED_VK);
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    return _result;
  }

}
