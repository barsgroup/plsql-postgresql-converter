package ru.barsopen.plsqlconverter.ast.typed;
public class relies_on_part implements _baseNode {
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
  public java.util.List<tableview_name> tableview_names = new java.util.ArrayList<tableview_name>();
  public java.util.List<tableview_name> get_tableview_names() { return this.tableview_names; }
  public void add_tableview_names(tableview_name value) {
    insert_tableview_names(tableview_names.size(), value);
  }
  public void insert_tableview_names(int pos, tableview_name value) {
    this.tableview_names.add(pos, value);
    value._setParent(this);
  }
  public void remove_tableview_names(int pos) {
    this.tableview_names.get(pos)._setParent(null);
    this.tableview_names.remove(pos);
  }
  public void remove_tableview_names(tableview_name value) {
    this.remove_tableview_names(this.tableview_names.indexOf(value));
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RELIES_ON_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RELIES_ON_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (tableview_names.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < tableview_names.size(); ++i) {
      _result.addChild(tableview_names.get(i).unparse());
    }


    return _result;
  }

}
