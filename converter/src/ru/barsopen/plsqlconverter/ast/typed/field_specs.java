package ru.barsopen.plsqlconverter.ast.typed;
public class field_specs implements _baseNode {
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
  public java.util.List<field_spec> field_specs = new java.util.ArrayList<field_spec>();
  public java.util.List<field_spec> get_field_specs() { return this.field_specs; }
  public void add_field_specs(field_spec value) {
    insert_field_specs(field_specs.size(), value);
  }
  public void insert_field_specs(int pos, field_spec value) {
    this.field_specs.add(pos, value);
    value._setParent(this);
  }
  public void remove_field_specs(int pos) {
    this.field_specs.get(pos)._setParent(null);
    this.field_specs.remove(pos);
  }
  public void remove_field_specs(field_spec value) {
    this.remove_field_specs(this.field_specs.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (field_spec _value: this.field_specs) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FIELDS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FIELDS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < field_specs.size(); ++i) {
      _result.addChild(field_specs.get(i).unparse());
    }


    return _result;
  }

}
