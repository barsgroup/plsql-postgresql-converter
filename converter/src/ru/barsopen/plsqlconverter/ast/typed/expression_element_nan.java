package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_nan implements expression_element, _baseNode {
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
  public expression_element arg = null;
  public expression_element get_arg() { return this.arg; }
  public void set_arg(expression_element value) {
    if (this.arg != null) { this.arg._setParent(null); }
    this.arg = value;
    if (this.arg != null) { this.arg._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.arg != null) {
      this.arg._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.IS_NAN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("IS_NAN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (arg == null) { throw new RuntimeException(); }
    _result.addChild(arg.unparse());


    return _result;
  }

}
