package ru.barsopen.plsqlconverter.ast.typed;
public class lock_table_element implements _baseNode {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public _baseNode _parent = null;
  public _baseNode _getParent() { return _parent; }
  public void _setParent(_baseNode value) { _parent = value; }
  public void _setBaseNode(_baseNode value) { this._parent = value; }
  public int _getLine() { return _line; }
  public int _getCol() { return _col; }
  public int _getTokenStartIndex() { return _tokenStartIndex; }
  public int _getTokenStopIndex() { return _tokenStopIndex; }
  public tableview_name tableview_name = null;
  public tableview_name get_tableview_name() { return this.tableview_name; }
  public void set_tableview_name(tableview_name value) {
    if (this.tableview_name != null) { this.tableview_name._setParent(null); }
    this.tableview_name = value;
    if (this.tableview_name != null) { this.tableview_name._setParent(this); }
  }
  public partition_extension_clause partition_extension_clause = null;
  public partition_extension_clause get_partition_extension_clause() { return this.partition_extension_clause; }
  public void set_partition_extension_clause(partition_extension_clause value) {
    if (this.partition_extension_clause != null) { this.partition_extension_clause._setParent(null); }
    this.partition_extension_clause = value;
    if (this.partition_extension_clause != null) { this.partition_extension_clause._setParent(this); }
  }
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
