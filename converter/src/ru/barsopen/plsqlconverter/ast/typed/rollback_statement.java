package ru.barsopen.plsqlconverter.ast.typed;
public class rollback_statement implements transaction_control_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree WORK_VK = null;
  public boolean is_WORK_VK() { return this.WORK_VK != null; }
  public rollback_statement_additional rollback_statement_additional = null;
  public boolean is_rollback_statement_additional() { return this.rollback_statement_additional != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ROLLBACK_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ROLLBACK_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (WORK_VK != null) {
      _result.addChild(WORK_VK);
    }


    if (rollback_statement_additional != null) {
      _result.addChild(rollback_statement_additional.unparse());
    }


    return _result;
  }

}
