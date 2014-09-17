package ru.barsopen.plsqlconverter.ast.typed;
public class write_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree WAIT_VK = null;
  public boolean is_WAIT_VK() { return this.WAIT_VK != null; }
  public org.antlr.runtime.tree.Tree PLSQL_RESERVED_NOWAIT = null;
  public boolean is_PLSQL_RESERVED_NOWAIT() { return this.PLSQL_RESERVED_NOWAIT != null; }
  public org.antlr.runtime.tree.Tree IMMEDIATE_VK = null;
  public boolean is_IMMEDIATE_VK() { return this.IMMEDIATE_VK != null; }
  public org.antlr.runtime.tree.Tree BATCH_VK = null;
  public boolean is_BATCH_VK() { return this.BATCH_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.WRITE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("WRITE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (WAIT_VK != null) {
      _result.addChild(WAIT_VK);
    }


    if (PLSQL_RESERVED_NOWAIT != null) {
      _result.addChild(PLSQL_RESERVED_NOWAIT);
    }


    if (IMMEDIATE_VK != null) {
      _result.addChild(IMMEDIATE_VK);
    }


    if (BATCH_VK != null) {
      _result.addChild(BATCH_VK);
    }


    return _result;
  }

}
