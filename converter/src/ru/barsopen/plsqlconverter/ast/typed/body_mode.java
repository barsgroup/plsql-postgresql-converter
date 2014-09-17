package ru.barsopen.plsqlconverter.ast.typed;
public class body_mode implements function_impl, create_procedure_body_impl, _baseNode {
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
  public block block = null;
  public block get_block() { return this.block; }
  public void set_block(block value) {
    if (this.block != null) { this.block._setParent(null); }
    this.block = value;
    if (this.block != null) { this.block._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.block != null) {
      this.block._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.BODY_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("BODY_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (block == null) { throw new RuntimeException(); }
    _result.addChild(block.unparse());


    return _result;
  }

}
