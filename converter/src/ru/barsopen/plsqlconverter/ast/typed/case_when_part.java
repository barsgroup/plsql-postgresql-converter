package ru.barsopen.plsqlconverter.ast.typed;
public class case_when_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression condition = null;
  public expression_or_seq_of_statements expression_or_seq_of_statements = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_WHEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WHEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (condition == null) { throw new RuntimeException(); }
    _result.addChild(condition.unparse());


    if (expression_or_seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_seq_of_statements.unparse());


    return _result;
  }

}
