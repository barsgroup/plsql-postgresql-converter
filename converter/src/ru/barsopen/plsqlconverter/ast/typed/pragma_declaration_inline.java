package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration_inline implements pragma_declaration_impl, _baseNode {
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
  public id id = null;
  public id get_id() { return this.id; }
  public void set_id(id value) {
    if (this.id != null) { this.id._setParent(null); }
    this.id = value;
    if (this.id != null) { this.id._setParent(this); }
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
    if (this.id != null) {
      this.id._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INLINE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INLINE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (id == null) { throw new RuntimeException(); }
    _result.addChild(id.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
