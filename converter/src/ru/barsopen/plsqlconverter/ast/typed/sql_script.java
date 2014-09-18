package ru.barsopen.plsqlconverter.ast.typed;
public class sql_script implements _baseNode {
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
  public sql_script_item sql_script_item = null;
  public sql_script_item get_sql_script_item() { return this.sql_script_item; }
  public void set_sql_script_item(sql_script_item value) {
    if (this.sql_script_item != null) { this.sql_script_item._setParent(null); }
    this.sql_script_item = value;
    if (this.sql_script_item != null) { this.sql_script_item._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.sql_script_item != null) {
      this.sql_script_item._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL_SCRIPT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL_SCRIPT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (sql_script_item == null) { throw new RuntimeException(); }
    _result.addChild(sql_script_item.unparse());


    return _result;
  }

}
