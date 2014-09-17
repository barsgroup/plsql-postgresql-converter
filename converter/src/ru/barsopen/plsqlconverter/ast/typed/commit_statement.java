package ru.barsopen.plsqlconverter.ast.typed;
public class commit_statement implements transaction_control_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree WORK_VK = null;
  public boolean is_WORK_VK() { return this.WORK_VK != null; }
  public commit_statement_additional commit_statement_additional = null;
  public boolean is_commit_statement_additional() { return this.commit_statement_additional != null; }
  public write_clause write_clause = null;
  public boolean is_write_clause() { return this.write_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COMMIT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COMMIT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (WORK_VK != null) {
      _result.addChild(WORK_VK);
    }


    if (commit_statement_additional != null) {
      _result.addChild(commit_statement_additional.unparse());
    }


    if (write_clause != null) {
      _result.addChild(write_clause.unparse());
    }


    return _result;
  }

}
