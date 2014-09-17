package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_count implements standard_function, _baseNode {
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
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT; }
  public void set_SQL92_RESERVED_DISTINCT(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_DISTINCT = value;
  }
  public boolean is_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE; }
  public void set_SQL92_RESERVED_UNIQUE(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_UNIQUE = value;
  }
  public boolean is_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL; }
  public void set_SQL92_RESERVED_ALL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_ALL = value;
  }
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public org.antlr.runtime.tree.Tree ASTERISK = null;
  public org.antlr.runtime.tree.Tree get_ASTERISK() { return this.ASTERISK; }
  public void set_ASTERISK(org.antlr.runtime.tree.Tree value) {
    this.ASTERISK = value;
  }
  public boolean is_ASTERISK() { return this.ASTERISK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public boolean is_expression() { return this.expression != null; }
  public over_clause over_clause = null;
  public over_clause get_over_clause() { return this.over_clause; }
  public void set_over_clause(over_clause value) {
    if (this.over_clause != null) { this.over_clause._setParent(null); }
    this.over_clause = value;
    if (this.over_clause != null) { this.over_clause._setParent(this); }
  }
  public boolean is_over_clause() { return this.over_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COUNT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COUNT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_DISTINCT != null) {
      _result.addChild(SQL92_RESERVED_DISTINCT);
    }


    if (SQL92_RESERVED_UNIQUE != null) {
      _result.addChild(SQL92_RESERVED_UNIQUE);
    }


    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    if (ASTERISK != null) {
      _result.addChild(ASTERISK);
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    if (over_clause != null) {
      _result.addChild(over_clause.unparse());
    }


    return _result;
  }

}
