package ru.barsopen.plsqlconverter.ast.typed;
public class compilation_unit implements _baseNode {
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
  public java.util.List<unit_statement> unit_statements = new java.util.ArrayList<unit_statement>();
  public java.util.List<unit_statement> get_unit_statements() { return this.unit_statements; }
  public void add_unit_statements(unit_statement value) {
    insert_unit_statements(unit_statements.size(), value);
  }
  public void insert_unit_statements(int pos, unit_statement value) {
    this.unit_statements.add(pos, value);
    value._setParent(this);
  }
  public void remove_unit_statements(int pos) {
    this.unit_statements.get(pos)._setParent(null);
    this.unit_statements.remove(pos);
  }
  public void remove_unit_statements(unit_statement value) {
    this.remove_unit_statements(this.unit_statements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (unit_statement _value: this.unit_statements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COMPILATION_UNIT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COMPILATION_UNIT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < unit_statements.size(); ++i) {
      _result.addChild(unit_statements.get(i).unparse());
    }


    return _result;
  }

}
