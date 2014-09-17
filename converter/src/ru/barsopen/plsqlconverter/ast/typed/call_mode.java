package ru.barsopen.plsqlconverter.ast.typed;
public class call_mode implements function_impl, create_procedure_body_impl, _baseNode {
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
  public call_spec call_spec = null;
  public call_spec get_call_spec() { return this.call_spec; }
  public void set_call_spec(call_spec value) {
    if (this.call_spec != null) { this.call_spec._setParent(null); }
    this.call_spec = value;
    if (this.call_spec != null) { this.call_spec._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CALL_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CALL_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (call_spec == null) { throw new RuntimeException(); }
    _result.addChild(call_spec.unparse());


    return _result;
  }

}
