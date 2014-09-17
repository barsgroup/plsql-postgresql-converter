package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_elements_list implements _baseNode {
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
  public java.util.List<order_by_elements> elements = new java.util.ArrayList<order_by_elements>();
  public java.util.List<order_by_elements> get_elements() { return this.elements; }
  public void add_elements(order_by_elements value) {
    insert_elements(elements.size(), value);
  }
  public void insert_elements(int pos, order_by_elements value) {
    this.elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_elements(int pos) {
    this.elements.get(pos)._setParent(null);
    this.elements.remove(pos);
  }
  public void remove_elements(order_by_elements value) {
    this.remove_elements(this.elements.indexOf(value));
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ORDER_BY_ELEMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ORDER_BY_ELEMENTS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < elements.size(); ++i) {
      _result.addChild(elements.get(i).unparse());
    }


    return _result;
  }

}
