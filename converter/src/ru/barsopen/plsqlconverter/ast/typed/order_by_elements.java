package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_elements implements _baseNode {
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
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ASC = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_ASC() { return this.SQL92_RESERVED_ASC; }
  public void set_SQL92_RESERVED_ASC(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_ASC = value;
  }
  public boolean is_SQL92_RESERVED_ASC() { return this.SQL92_RESERVED_ASC != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DESC = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_DESC() { return this.SQL92_RESERVED_DESC; }
  public void set_SQL92_RESERVED_DESC(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_DESC = value;
  }
  public boolean is_SQL92_RESERVED_DESC() { return this.SQL92_RESERVED_DESC != null; }
  public org.antlr.runtime.tree.Tree NULLS_VK = null;
  public org.antlr.runtime.tree.Tree get_NULLS_VK() { return this.NULLS_VK; }
  public void set_NULLS_VK(org.antlr.runtime.tree.Tree value) {
    this.NULLS_VK = value;
  }
  public boolean is_NULLS_VK() { return this.NULLS_VK != null; }
  public org.antlr.runtime.tree.Tree FIRST_VK = null;
  public org.antlr.runtime.tree.Tree get_FIRST_VK() { return this.FIRST_VK; }
  public void set_FIRST_VK(org.antlr.runtime.tree.Tree value) {
    this.FIRST_VK = value;
  }
  public boolean is_FIRST_VK() { return this.FIRST_VK != null; }
  public org.antlr.runtime.tree.Tree LAST_VK = null;
  public org.antlr.runtime.tree.Tree get_LAST_VK() { return this.LAST_VK; }
  public void set_LAST_VK(org.antlr.runtime.tree.Tree value) {
    this.LAST_VK = value;
  }
  public boolean is_LAST_VK() { return this.LAST_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ORDER_BY_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ORDER_BY_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (SQL92_RESERVED_ASC != null) {
      _result.addChild(SQL92_RESERVED_ASC);
    }


    if (SQL92_RESERVED_DESC != null) {
      _result.addChild(SQL92_RESERVED_DESC);
    }


    if (NULLS_VK != null) {
      _result.addChild(NULLS_VK);
    }


    if (FIRST_VK != null) {
      _result.addChild(FIRST_VK);
    }


    if (LAST_VK != null) {
      _result.addChild(LAST_VK);
    }


    return _result;
  }

}
