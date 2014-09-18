package ru.barsopen.plsqlconverter.ast.typed;
public class drop_procedure implements unit_statement, _baseNode {
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

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.procedure_name != null) {
      this.procedure_name._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_PROCEDURE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DROP_PROCEDURE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (procedure_name == null) { throw new RuntimeException(); }
    _result.addChild(procedure_name.unparse());


    return _result;
  }

}
