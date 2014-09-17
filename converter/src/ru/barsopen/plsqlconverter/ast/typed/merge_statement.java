package ru.barsopen.plsqlconverter.ast.typed;
public class merge_statement implements data_manipulation_language_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public alias alias = null;
  public boolean is_alias() { return this.alias != null; }
  public tableview_name tableview_name = null;
  public merge_using_clause merge_using_clause = null;
  public merge_update_clause merge_update_clause = null;
  public boolean is_merge_update_clause() { return this.merge_update_clause != null; }
  public merge_insert_clause merge_insert_clause = null;
  public boolean is_merge_insert_clause() { return this.merge_insert_clause != null; }
  public error_logging_clause error_logging_clause = null;
  public boolean is_error_logging_clause() { return this.error_logging_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MERGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MERGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (tableview_name == null) { throw new RuntimeException(); }
    _result.addChild(tableview_name.unparse());


    if (merge_using_clause == null) { throw new RuntimeException(); }
    _result.addChild(merge_using_clause.unparse());


    if (merge_update_clause != null) {
      _result.addChild(merge_update_clause.unparse());
    }


    if (merge_insert_clause != null) {
      _result.addChild(merge_insert_clause.unparse());
    }


    if (error_logging_clause != null) {
      _result.addChild(error_logging_clause.unparse());
    }


    return _result;
  }

}
