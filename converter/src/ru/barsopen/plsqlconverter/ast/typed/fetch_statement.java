package ru.barsopen.plsqlconverter.ast.typed;
public class fetch_statement implements cursor_manipulation_statements, _baseNode {
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
  public cursor_name cursor_name = null;
  public cursor_name get_cursor_name() { return this.cursor_name; }
  public void set_cursor_name(cursor_name value) {
    if (this.cursor_name != null) { this.cursor_name._setParent(null); }
    this.cursor_name = value;
    if (this.cursor_name != null) { this.cursor_name._setParent(this); }
  }
  public into_clause into_clause = null;
  public into_clause get_into_clause() { return this.into_clause; }
  public void set_into_clause(into_clause value) {
    if (this.into_clause != null) { this.into_clause._setParent(null); }
    this.into_clause = value;
    if (this.into_clause != null) { this.into_clause._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.cursor_name != null) {
      this.cursor_name._walk(visitor);
    }
    if (this.into_clause != null) {
      this.into_clause._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.cursor_name == child) {
      this.set_cursor_name((ru.barsopen.plsqlconverter.ast.typed.cursor_name)replacement);
      return;
    }
    if (this.into_clause == child) {
      this.set_into_clause((ru.barsopen.plsqlconverter.ast.typed.into_clause)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FETCH);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_FETCH");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (cursor_name == null) { throw new RuntimeException(); }
    _result.addChild(cursor_name.unparse());


    if (into_clause == null) { throw new RuntimeException(); }
    _result.addChild(into_clause.unparse());


    return _result;
  }

}
