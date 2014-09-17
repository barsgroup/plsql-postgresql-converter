package ru.barsopen.plsqlconverter.ast.typed;
public class update_statement implements data_manipulation_language_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public general_table_ref general_table_ref = null;
  public update_statement_set update_statement_set = null;
  public where_clause where_clause = null;
  public boolean is_where_clause() { return this.where_clause != null; }
  public static_returning_clause static_returning_clause = null;
  public boolean is_static_returning_clause() { return this.static_returning_clause != null; }
  public error_logging_clause error_logging_clause = null;
  public boolean is_error_logging_clause() { return this.error_logging_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_UPDATE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_UPDATE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_table_ref == null) { throw new RuntimeException(); }
    _result.addChild(general_table_ref.unparse());


    if (update_statement_set == null) { throw new RuntimeException(); }
    _result.addChild(update_statement_set.unparse());


    if (where_clause != null) {
      _result.addChild(where_clause.unparse());
    }


    if (static_returning_clause != null) {
      _result.addChild(static_returning_clause.unparse());
    }


    if (error_logging_clause != null) {
      _result.addChild(error_logging_clause.unparse());
    }


    return _result;
  }

}
