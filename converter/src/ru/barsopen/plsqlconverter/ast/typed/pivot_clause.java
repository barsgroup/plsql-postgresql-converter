package ru.barsopen.plsqlconverter.ast.typed;
public class pivot_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree XML_VK = null;
  public org.antlr.runtime.tree.Tree get_XML_VK() { return this.XML_VK; }
  public void set_XML_VK(org.antlr.runtime.tree.Tree value) {
    this.XML_VK = value;
  }
  public boolean is_XML_VK() { return this.XML_VK != null; }
  public java.util.List<pivot_element> pivot_elements = new java.util.ArrayList<pivot_element>();
  public java.util.List<pivot_element> get_pivot_elements() { return this.pivot_elements; }
  public void add_pivot_elements(pivot_element value) {
    insert_pivot_elements(pivot_elements.size(), value);
  }
  public void insert_pivot_elements(int pos, pivot_element value) {
    this.pivot_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_pivot_elements(int pos) {
    this.pivot_elements.get(pos)._setParent(null);
    this.pivot_elements.remove(pos);
  }
  public void remove_pivot_elements(pivot_element value) {
    this.remove_pivot_elements(this.pivot_elements.indexOf(value));
  }
  public pivot_for_clause pivot_for_clause = null;
  public pivot_for_clause get_pivot_for_clause() { return this.pivot_for_clause; }
  public void set_pivot_for_clause(pivot_for_clause value) {
    if (this.pivot_for_clause != null) { this.pivot_for_clause._setParent(null); }
    this.pivot_for_clause = value;
    if (this.pivot_for_clause != null) { this.pivot_for_clause._setParent(this); }
  }
  public pivot_in_clause pivot_in_clause = null;
  public pivot_in_clause get_pivot_in_clause() { return this.pivot_in_clause; }
  public void set_pivot_in_clause(pivot_in_clause value) {
    if (this.pivot_in_clause != null) { this.pivot_in_clause._setParent(null); }
    this.pivot_in_clause = value;
    if (this.pivot_in_clause != null) { this.pivot_in_clause._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PIVOT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PIVOT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (XML_VK != null) {
      _result.addChild(XML_VK);
    }


    if (pivot_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < pivot_elements.size(); ++i) {
      _result.addChild(pivot_elements.get(i).unparse());
    }


    if (pivot_for_clause == null) { throw new RuntimeException(); }
    _result.addChild(pivot_for_clause.unparse());


    if (pivot_in_clause == null) { throw new RuntimeException(); }
    _result.addChild(pivot_in_clause.unparse());


    return _result;
  }

}
