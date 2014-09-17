package ru.barsopen.plsqlconverter.ast.typed;
public class error_logging_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public error_logging_into_part error_logging_into_part = null;
  public boolean is_error_logging_into_part() { return this.error_logging_into_part != null; }
  public expression expression = null;
  public boolean is_expression() { return this.expression != null; }
  public error_logging_reject_part error_logging_reject_part = null;
  public boolean is_error_logging_reject_part() { return this.error_logging_reject_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LOG_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LOG_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (error_logging_into_part != null) {
      _result.addChild(error_logging_into_part.unparse());
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    if (error_logging_reject_part != null) {
      _result.addChild(error_logging_reject_part.unparse());
    }


    return _result;
  }

}
