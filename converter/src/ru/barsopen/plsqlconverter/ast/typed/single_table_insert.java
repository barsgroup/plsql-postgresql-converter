package ru.barsopen.plsqlconverter.ast.typed;
public class single_table_insert implements insert_statement_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public insert_into_clause insert_into_clause = null;
  public values_clause values_clause = null;
  public boolean is_values_clause() { return this.values_clause != null; }
  public static_returning_clause static_returning_clause = null;
  public boolean is_static_returning_clause() { return this.static_returning_clause != null; }
  public select_statement select_statement = null;
  public boolean is_select_statement() { return this.select_statement != null; }
  public error_logging_clause error_logging_clause = null;
  public boolean is_error_logging_clause() { return this.error_logging_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SINGLE_TABLE_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SINGLE_TABLE_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (insert_into_clause == null) { throw new RuntimeException(); }
    _result.addChild(insert_into_clause.unparse());


    if (values_clause != null) {
      _result.addChild(values_clause.unparse());
    }


    if (static_returning_clause != null) {
      _result.addChild(static_returning_clause.unparse());
    }


    if (select_statement != null) {
      _result.addChild(select_statement.unparse());
    }


    if (error_logging_clause != null) {
      _result.addChild(error_logging_clause.unparse());
    }


    return _result;
  }

}
