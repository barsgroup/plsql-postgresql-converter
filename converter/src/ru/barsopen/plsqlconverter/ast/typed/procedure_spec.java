package ru.barsopen.plsqlconverter.ast.typed;
public class procedure_spec implements package_obj_spec, package_obj_body, _baseNode {
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
  public procedure_name procedure_name = null;
  public procedure_name get_procedure_name() { return this.procedure_name; }
  public void set_procedure_name(procedure_name value) {
    if (this.procedure_name != null) { this.procedure_name._setParent(null); }
    this.procedure_name = value;
    if (this.procedure_name != null) { this.procedure_name._setParent(this); }
  }
  public parameters parameters = null;
  public parameters get_parameters() { return this.parameters; }
  public void set_parameters(parameters value) {
    if (this.parameters != null) { this.parameters._setParent(null); }
    this.parameters = value;
    if (this.parameters != null) { this.parameters._setParent(this); }
  }
  public call_mode call_mode = null;
  public call_mode get_call_mode() { return this.call_mode; }
  public void set_call_mode(call_mode value) {
    if (this.call_mode != null) { this.call_mode._setParent(null); }
    this.call_mode = value;
    if (this.call_mode != null) { this.call_mode._setParent(this); }
  }
  public boolean is_call_mode() { return this.call_mode != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.procedure_name != null) {
      this.procedure_name._walk(visitor);
    }
    if (this.parameters != null) {
      this.parameters._walk(visitor);
    }
    if (this.call_mode != null) {
      this.call_mode._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.procedure_name == child) {
      this.set_procedure_name((ru.barsopen.plsqlconverter.ast.typed.procedure_name)replacement);
      return;
    }
    if (this.parameters == child) {
      this.set_parameters((ru.barsopen.plsqlconverter.ast.typed.parameters)replacement);
      return;
    }
    if (this.call_mode == child) {
      this.set_call_mode((ru.barsopen.plsqlconverter.ast.typed.call_mode)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PROCEDURE_SPEC);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PROCEDURE_SPEC");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (procedure_name == null) { throw new RuntimeException(); }
    _result.addChild(procedure_name.unparse());


    if (parameters == null) { throw new RuntimeException(); }
    _result.addChild(parameters.unparse());


    if (call_mode != null) {
      _result.addChild(call_mode.unparse());
    }


    return _result;
  }

}
