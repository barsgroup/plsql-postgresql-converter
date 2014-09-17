package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_stanrd implements expression_element, _baseNode {
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
  public standard_function standard_function = null;
  public standard_function get_standard_function() { return this.standard_function; }
  public void set_standard_function(standard_function value) {
    if (this.standard_function != null) { this.standard_function._setParent(null); }
    this.standard_function = value;
    if (this.standard_function != null) { this.standard_function._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.standard_function != null) {
      this.standard_function._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.STANDARD_FUNCTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("STANDARD_FUNCTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (standard_function == null) { throw new RuntimeException(); }
    _result.addChild(standard_function.unparse());


    return _result;
  }

}
