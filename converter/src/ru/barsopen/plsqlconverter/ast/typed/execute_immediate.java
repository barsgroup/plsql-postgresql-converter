package ru.barsopen.plsqlconverter.ast.typed;
public class execute_immediate implements sql_statement, _baseNode {
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
  public into_clause into_clause = null;
  public into_clause get_into_clause() { return this.into_clause; }
  public void set_into_clause(into_clause value) {
    if (this.into_clause != null) { this.into_clause._setParent(null); }
    this.into_clause = value;
    if (this.into_clause != null) { this.into_clause._setParent(this); }
  }
  public boolean is_into_clause() { return this.into_clause != null; }
  public using_clause using_clause = null;
  public using_clause get_using_clause() { return this.using_clause; }
  public void set_using_clause(using_clause value) {
    if (this.using_clause != null) { this.using_clause._setParent(null); }
    this.using_clause = value;
    if (this.using_clause != null) { this.using_clause._setParent(this); }
  }
  public boolean is_using_clause() { return this.using_clause != null; }
  public dynamic_returning_clause dynamic_returning_clause = null;
  public dynamic_returning_clause get_dynamic_returning_clause() { return this.dynamic_returning_clause; }
  public void set_dynamic_returning_clause(dynamic_returning_clause value) {
    if (this.dynamic_returning_clause != null) { this.dynamic_returning_clause._setParent(null); }
    this.dynamic_returning_clause = value;
    if (this.dynamic_returning_clause != null) { this.dynamic_returning_clause._setParent(this); }
  }
  public boolean is_dynamic_returning_clause() { return this.dynamic_returning_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.into_clause != null) {
      this.into_clause._walk(visitor);
    }
    if (this.using_clause != null) {
      this.using_clause._walk(visitor);
    }
    if (this.dynamic_returning_clause != null) {
      this.dynamic_returning_clause._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.into_clause == child) {
      this.set_into_clause((ru.barsopen.plsqlconverter.ast.typed.into_clause)replacement);
      return;
    }
    if (this.using_clause == child) {
      this.set_using_clause((ru.barsopen.plsqlconverter.ast.typed.using_clause)replacement);
      return;
    }
    if (this.dynamic_returning_clause == child) {
      this.set_dynamic_returning_clause((ru.barsopen.plsqlconverter.ast.typed.dynamic_returning_clause)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.EXECUTE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXECUTE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (into_clause != null) {
      _result.addChild(into_clause.unparse());
    }


    if (using_clause != null) {
      _result.addChild(using_clause.unparse());
    }


    if (dynamic_returning_clause != null) {
      _result.addChild(dynamic_returning_clause.unparse());
    }


    return _result;
  }

}
