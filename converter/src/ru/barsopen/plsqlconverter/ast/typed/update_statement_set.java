package ru.barsopen.plsqlconverter.ast.typed;
public class update_statement_set implements _baseNode {
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
  public java.util.List<update_set_elements> update_set_elementss = new java.util.ArrayList<update_set_elements>();
  public java.util.List<update_set_elements> get_update_set_elementss() { return this.update_set_elementss; }
  public void add_update_set_elementss(update_set_elements value) {
    insert_update_set_elementss(update_set_elementss.size(), value);
  }
  public void insert_update_set_elementss(int pos, update_set_elements value) {
    this.update_set_elementss.add(pos, value);
    value._setParent(this);
  }
  public void remove_update_set_elementss(int pos) {
    this.update_set_elementss.get(pos)._setParent(null);
    this.update_set_elementss.remove(pos);
  }
  public void remove_update_set_elementss(update_set_elements value) {
    this.remove_update_set_elementss(this.update_set_elementss.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (update_set_elements _value: this.update_set_elementss) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SET_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SET_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (update_set_elementss.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < update_set_elementss.size(); ++i) {
      _result.addChild(update_set_elementss.get(i).unparse());
    }


    return _result;
  }

}
