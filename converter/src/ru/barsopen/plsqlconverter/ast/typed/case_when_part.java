package ru.barsopen.plsqlconverter.ast.typed;
public class case_when_part implements _baseNode {
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
  public expression condition = null;
  public expression get_condition() { return this.condition; }
  public void set_condition(expression value) {
    if (this.condition != null) { this.condition._setParent(null); }
    this.condition = value;
    if (this.condition != null) { this.condition._setParent(this); }
  }
  public expression_or_seq_of_statements expression_or_seq_of_statements = null;
  public expression_or_seq_of_statements get_expression_or_seq_of_statements() { return this.expression_or_seq_of_statements; }
  public void set_expression_or_seq_of_statements(expression_or_seq_of_statements value) {
    if (this.expression_or_seq_of_statements != null) { this.expression_or_seq_of_statements._setParent(null); }
    this.expression_or_seq_of_statements = value;
    if (this.expression_or_seq_of_statements != null) { this.expression_or_seq_of_statements._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.condition != null) {
      this.condition._walk(visitor);
    }
    if (this.expression_or_seq_of_statements != null) {
      this.expression_or_seq_of_statements._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.condition == child) {
      this.set_condition((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    if (this.expression_or_seq_of_statements == child) {
      this.set_expression_or_seq_of_statements((ru.barsopen.plsqlconverter.ast.typed.expression_or_seq_of_statements)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WHEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (condition == null) { throw new RuntimeException(); }
    _result.addChild(condition.unparse());


    if (expression_or_seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_seq_of_statements.unparse());


    return _result;
  }

}
