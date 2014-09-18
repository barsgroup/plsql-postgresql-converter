package ru.barsopen.plsqlconverter.ast.typed;
public class using_clause implements _baseNode {
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
  public java.util.List<using_element> using_elements = new java.util.ArrayList<using_element>();
  public java.util.List<using_element> get_using_elements() { return this.using_elements; }
  public void add_using_elements(using_element value) {
    insert_using_elements(using_elements.size(), value);
  }
  public void insert_using_elements(int pos, using_element value) {
    this.using_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_using_elements(int pos) {
    this.using_elements.get(pos)._setParent(null);
    this.using_elements.remove(pos);
  }
  public void remove_using_elements(using_element value) {
    this.remove_using_elements(this.using_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (using_element _value: this.using_elements) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.using_elements.size(); ++_i) {
      if (this.using_elements.get(_i) == child) {
        this.remove_using_elements(_i);
        this.insert_using_elements(_i, (ru.barsopen.plsqlconverter.ast.typed.using_element)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_USING);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_NON_RESERVED_USING");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (using_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < using_elements.size(); ++i) {
      _result.addChild(using_elements.get(i).unparse());
    }


    return _result;
  }

}
