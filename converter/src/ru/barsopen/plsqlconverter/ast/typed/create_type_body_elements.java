package ru.barsopen.plsqlconverter.ast.typed;
public class create_type_body_elements implements _baseNode {
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
  public java.util.List<type_body_elements> elements = new java.util.ArrayList<type_body_elements>();
  public java.util.List<type_body_elements> get_elements() { return this.elements; }
  public void add_elements(type_body_elements value) {
    insert_elements(elements.size(), value);
  }
  public void insert_elements(int pos, type_body_elements value) {
    this.elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_elements(int pos) {
    this.elements.get(pos)._setParent(null);
    this.elements.remove(pos);
  }
  public void remove_elements(type_body_elements value) {
    this.remove_elements(this.elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (type_body_elements _value: this.elements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TYPE_BODY_ELEMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TYPE_BODY_ELEMENTS");
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
