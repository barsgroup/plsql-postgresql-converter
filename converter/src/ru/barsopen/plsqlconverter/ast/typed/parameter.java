package ru.barsopen.plsqlconverter.ast.typed;
public class parameter implements _baseNode {
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
  public parameter_name parameter_name = null;
  public parameter_name get_parameter_name() { return this.parameter_name; }
  public void set_parameter_name(parameter_name value) {
    if (this.parameter_name != null) { this.parameter_name._setParent(null); }
    this.parameter_name = value;
    if (this.parameter_name != null) { this.parameter_name._setParent(this); }
  }
  public java.util.List<parameter_dir_spec> parameter_dir_specs = new java.util.ArrayList<parameter_dir_spec>();
  public java.util.List<parameter_dir_spec> get_parameter_dir_specs() { return this.parameter_dir_specs; }
  public void add_parameter_dir_specs(parameter_dir_spec value) {
    insert_parameter_dir_specs(parameter_dir_specs.size(), value);
  }
  public void insert_parameter_dir_specs(int pos, parameter_dir_spec value) {
    this.parameter_dir_specs.add(pos, value);
    value._setParent(this);
  }
  public void remove_parameter_dir_specs(int pos) {
    this.parameter_dir_specs.get(pos)._setParent(null);
    this.parameter_dir_specs.remove(pos);
  }
  public void remove_parameter_dir_specs(parameter_dir_spec value) {
    this.remove_parameter_dir_specs(this.parameter_dir_specs.indexOf(value));
  }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }
  public boolean is_type_spec() { return this.type_spec != null; }
  public default_value_part default_value_part = null;
  public default_value_part get_default_value_part() { return this.default_value_part; }
  public void set_default_value_part(default_value_part value) {
    if (this.default_value_part != null) { this.default_value_part._setParent(null); }
    this.default_value_part = value;
    if (this.default_value_part != null) { this.default_value_part._setParent(this); }
  }
  public boolean is_default_value_part() { return this.default_value_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARAMETER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARAMETER");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (parameter_name == null) { throw new RuntimeException(); }
    _result.addChild(parameter_name.unparse());


    for (int i = 0; i < parameter_dir_specs.size(); ++i) {
      _result.addChild(parameter_dir_specs.get(i).unparse());
    }


    if (type_spec != null) {
      _result.addChild(type_spec.unparse());
    }


    if (default_value_part != null) {
      _result.addChild(default_value_part.unparse());
    }


    return _result;
  }

}
