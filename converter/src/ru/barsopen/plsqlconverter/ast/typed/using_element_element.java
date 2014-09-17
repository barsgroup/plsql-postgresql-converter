package ru.barsopen.plsqlconverter.ast.typed;
public class using_element_element implements using_element, _baseNode {
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
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_IN = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_IN() { return this.SQL92_RESERVED_IN; }
  public void set_SQL92_RESERVED_IN(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_IN = value;
  }
  public boolean is_SQL92_RESERVED_IN() { return this.SQL92_RESERVED_IN != null; }
  public org.antlr.runtime.tree.Tree OUT_VK = null;
  public org.antlr.runtime.tree.Tree get_OUT_VK() { return this.OUT_VK; }
  public void set_OUT_VK(org.antlr.runtime.tree.Tree value) {
    this.OUT_VK = value;
  }
  public boolean is_OUT_VK() { return this.OUT_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public alias alias = null;
  public alias get_alias() { return this.alias; }
  public void set_alias(alias value) {
    if (this.alias != null) { this.alias._setParent(null); }
    this.alias = value;
    if (this.alias != null) { this.alias._setParent(this); }
  }
  public boolean is_alias() { return this.alias != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.SQL92_RESERVED_IN != null) {
      visitor.visit(this.SQL92_RESERVED_IN);
    }
    if (this.OUT_VK != null) {
      visitor.visit(this.OUT_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.alias != null) {
      this.alias._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_IN != null) {
      _result.addChild(SQL92_RESERVED_IN);
    }


    if (OUT_VK != null) {
      _result.addChild(OUT_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    return _result;
  }

}
