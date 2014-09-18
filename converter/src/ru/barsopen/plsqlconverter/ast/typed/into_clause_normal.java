package ru.barsopen.plsqlconverter.ast.typed;
public class into_clause_normal implements into_clause, _baseNode {
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
  public java.util.List<general_element> general_elements = new java.util.ArrayList<general_element>();
  public java.util.List<general_element> get_general_elements() { return this.general_elements; }
  public void add_general_elements(general_element value) {
    insert_general_elements(general_elements.size(), value);
  }
  public void insert_general_elements(int pos, general_element value) {
    this.general_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_general_elements(int pos) {
    this.general_elements.get(pos)._setParent(null);
    this.general_elements.remove(pos);
  }
  public void remove_general_elements(general_element value) {
    this.remove_general_elements(this.general_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (general_element _value: this.general_elements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTO);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_INTO");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < general_elements.size(); ++i) {
      _result.addChild(general_elements.get(i).unparse());
    }


    return _result;
  }

}
