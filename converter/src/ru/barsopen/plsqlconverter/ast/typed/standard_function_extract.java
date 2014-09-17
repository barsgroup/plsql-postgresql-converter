package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_extract implements standard_function, _baseNode {
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
  public extract_part extract_part = null;
  public extract_part get_extract_part() { return this.extract_part; }
  public void set_extract_part(extract_part value) {
    if (this.extract_part != null) { this.extract_part._setParent(null); }
    this.extract_part = value;
    if (this.extract_part != null) { this.extract_part._setParent(this); }
  }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.extract_part != null) {
      this.extract_part._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXTRACT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXTRACT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (extract_part == null) { throw new RuntimeException(); }
    _result.addChild(extract_part.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
