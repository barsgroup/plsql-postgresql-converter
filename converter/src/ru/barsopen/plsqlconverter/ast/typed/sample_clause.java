package ru.barsopen.plsqlconverter.ast.typed;
public class sample_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree BLOCK_VK = null;
  public org.antlr.runtime.tree.Tree get_BLOCK_VK() { return this.BLOCK_VK; }
  public void set_BLOCK_VK(org.antlr.runtime.tree.Tree value) {
    this.BLOCK_VK = value;
  }
  public boolean is_BLOCK_VK() { return this.BLOCK_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public seed_part seed_part = null;
  public seed_part get_seed_part() { return this.seed_part; }
  public void set_seed_part(seed_part value) {
    if (this.seed_part != null) { this.seed_part._setParent(null); }
    this.seed_part = value;
    if (this.seed_part != null) { this.seed_part._setParent(this); }
  }
  public boolean is_seed_part() { return this.seed_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.BLOCK_VK != null) {
      visitor.visit(this.BLOCK_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.seed_part != null) {
      this.seed_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SAMPLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SAMPLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (BLOCK_VK != null) {
      _result.addChild(BLOCK_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (seed_part != null) {
      _result.addChild(seed_part.unparse());
    }


    return _result;
  }

}
