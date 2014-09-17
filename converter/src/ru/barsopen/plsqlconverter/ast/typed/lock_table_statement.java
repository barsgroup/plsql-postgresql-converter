package ru.barsopen.plsqlconverter.ast.typed;
public class lock_table_statement implements data_manipulation_language_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<lock_table_element> lock_table_elements = new java.util.ArrayList<lock_table_element>();
  public lock_mode lock_mode = null;
  public wait_nowait_part wait_nowait_part = null;
  public boolean is_wait_nowait_part() { return this.wait_nowait_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PLSQL_RESERVED_LOCK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_RESERVED_LOCK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (lock_table_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < lock_table_elements.size(); ++i) {
      _result.addChild(lock_table_elements.get(i).unparse());
    }


    if (lock_mode == null) { throw new RuntimeException(); }
    _result.addChild(lock_mode.unparse());


    if (wait_nowait_part != null) {
      _result.addChild(wait_nowait_part.unparse());
    }


    return _result;
  }

}
