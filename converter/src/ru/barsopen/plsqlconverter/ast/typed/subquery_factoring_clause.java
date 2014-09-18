package ru.barsopen.plsqlconverter.ast.typed;
public class subquery_factoring_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree RECURSIVE_VK = null;
  public org.antlr.runtime.tree.Tree get_RECURSIVE_VK() { return this.RECURSIVE_VK; }
  public void set_RECURSIVE_VK(org.antlr.runtime.tree.Tree value) {
    this.RECURSIVE_VK = value;
  }
  public boolean is_RECURSIVE_VK() { return this.RECURSIVE_VK != null; }
  public java.util.List<factoring_element> factoring_elements = new java.util.ArrayList<factoring_element>();
  public java.util.List<factoring_element> get_factoring_elements() { return this.factoring_elements; }
  public void add_factoring_elements(factoring_element value) {
    insert_factoring_elements(factoring_elements.size(), value);
  }
  public void insert_factoring_elements(int pos, factoring_element value) {
    this.factoring_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_factoring_elements(int pos) {
    this.factoring_elements.get(pos)._setParent(null);
    this.factoring_elements.remove(pos);
  }
  public void remove_factoring_elements(factoring_element value) {
    this.remove_factoring_elements(this.factoring_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.RECURSIVE_VK != null) {
      visitor.visit(this.RECURSIVE_VK);
    }
    for (factoring_element _value: this.factoring_elements) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.factoring_elements.size(); ++_i) {
      if (this.factoring_elements.get(_i) == child) {
        this.remove_factoring_elements(_i);
        this.insert_factoring_elements(_i, (ru.barsopen.plsqlconverter.ast.typed.factoring_element)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WITH);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WITH");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (RECURSIVE_VK != null) {
      _result.addChild(RECURSIVE_VK);
    }


    if (factoring_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < factoring_elements.size(); ++i) {
      _result.addChild(factoring_elements.get(i).unparse());
    }


    return _result;
  }

}
