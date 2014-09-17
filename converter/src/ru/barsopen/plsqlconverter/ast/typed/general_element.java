package ru.barsopen.plsqlconverter.ast.typed;
public class general_element implements statement, assignment_target, table_expression_element, expression_element, _baseNode {
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
  public java.util.List<general_element_item> general_element_items = new java.util.ArrayList<general_element_item>();
  public java.util.List<general_element_item> get_general_element_items() { return this.general_element_items; }
  public void add_general_element_items(general_element_item value) {
    insert_general_element_items(general_element_items.size(), value);
  }
  public void insert_general_element_items(int pos, general_element_item value) {
    this.general_element_items.add(pos, value);
    value._setParent(this);
  }
  public void remove_general_element_items(int pos) {
    this.general_element_items.get(pos)._setParent(null);
    this.general_element_items.remove(pos);
  }
  public void remove_general_element_items(general_element_item value) {
    this.remove_general_element_items(this.general_element_items.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (general_element_item _value: this.general_element_items) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CASCATED_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CASCATED_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_element_items.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < general_element_items.size(); ++i) {
      _result.addChild(general_element_items.get(i).unparse());
    }


    return _result;
  }

}
