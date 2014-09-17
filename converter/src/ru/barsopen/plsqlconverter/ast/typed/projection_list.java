package ru.barsopen.plsqlconverter.ast.typed;
public class projection_list implements query_block_projection, _baseNode {
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
  public java.util.List<selected_element> selected_elements = new java.util.ArrayList<selected_element>();
  public java.util.List<selected_element> get_selected_elements() { return this.selected_elements; }
  public void add_selected_elements(selected_element value) {
    insert_selected_elements(selected_elements.size(), value);
  }
  public void insert_selected_elements(int pos, selected_element value) {
    this.selected_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_selected_elements(int pos) {
    this.selected_elements.get(pos)._setParent(null);
    this.selected_elements.remove(pos);
  }
  public void remove_selected_elements(selected_element value) {
    this.remove_selected_elements(this.selected_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (selected_element _value: this.selected_elements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECT_LIST);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_LIST");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (selected_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < selected_elements.size(); ++i) {
      _result.addChild(selected_elements.get(i).unparse());
    }


    return _result;
  }

}
