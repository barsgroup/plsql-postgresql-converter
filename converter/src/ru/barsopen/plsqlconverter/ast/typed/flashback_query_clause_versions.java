package ru.barsopen.plsqlconverter.ast.typed;
public class flashback_query_clause_versions implements flashback_query_clause, _baseNode {
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
  public org.antlr.runtime.tree.Tree SCN_VK = null;
  public org.antlr.runtime.tree.Tree get_SCN_VK() { return this.SCN_VK; }
  public void set_SCN_VK(org.antlr.runtime.tree.Tree value) {
    this.SCN_VK = value;
  }
  public boolean is_SCN_VK() { return this.SCN_VK != null; }
  public org.antlr.runtime.tree.Tree TIMESTAMP_VK = null;
  public org.antlr.runtime.tree.Tree get_TIMESTAMP_VK() { return this.TIMESTAMP_VK; }
  public void set_TIMESTAMP_VK(org.antlr.runtime.tree.Tree value) {
    this.TIMESTAMP_VK = value;
  }
  public boolean is_TIMESTAMP_VK() { return this.TIMESTAMP_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.SCN_VK != null) {
      visitor.visit(this.SCN_VK);
    }
    if (this.TIMESTAMP_VK != null) {
      visitor.visit(this.TIMESTAMP_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.VERSIONS_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("VERSIONS_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SCN_VK != null) {
      _result.addChild(SCN_VK);
    }


    if (TIMESTAMP_VK != null) {
      _result.addChild(TIMESTAMP_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
