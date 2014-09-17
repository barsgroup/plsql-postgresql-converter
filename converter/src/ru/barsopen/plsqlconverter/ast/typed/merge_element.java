package ru.barsopen.plsqlconverter.ast.typed;
public class merge_element implements _baseNode {
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
  public column_name column_name = null;
  public column_name get_column_name() { return this.column_name; }
  public void set_column_name(column_name value) {
    if (this.column_name != null) { this.column_name._setParent(null); }
    this.column_name = value;
    if (this.column_name != null) { this.column_name._setParent(this); }
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
    if (this.column_name != null) {
      this.column_name._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ASSIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ASSIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (column_name == null) { throw new RuntimeException(); }
    _result.addChild(column_name.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
