package ru.barsopen.plsqlconverter.ast.typed;
public class merge_update_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<merge_element> merge_elements = new java.util.ArrayList<merge_element>();
  public where_clause where_clause = null;
  public boolean is_where_clause() { return this.where_clause != null; }
  public merge_update_delete_part merge_update_delete_part = null;
  public boolean is_merge_update_delete_part() { return this.merge_update_delete_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MERGE_UPDATE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MERGE_UPDATE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (merge_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < merge_elements.size(); ++i) {
      _result.addChild(merge_elements.get(i).unparse());
    }


    if (where_clause != null) {
      _result.addChild(where_clause.unparse());
    }


    if (merge_update_delete_part != null) {
      _result.addChild(merge_update_delete_part.unparse());
    }


    return _result;
  }

}
