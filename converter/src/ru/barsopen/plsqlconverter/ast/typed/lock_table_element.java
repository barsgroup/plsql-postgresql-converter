package ru.barsopen.plsqlconverter.ast.typed;
public class lock_table_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public tableview_name tableview_name = null;
  public partition_extension_clause partition_extension_clause = null;
  public boolean is_partition_extension_clause() { return this.partition_extension_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LOCK_TABLE_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LOCK_TABLE_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (tableview_name == null) { throw new RuntimeException(); }
    _result.addChild(tableview_name.unparse());


    if (partition_extension_clause != null) {
      _result.addChild(partition_extension_clause.unparse());
    }


    return _result;
  }

}
