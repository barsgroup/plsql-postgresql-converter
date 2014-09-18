package ru.barsopen.plsqlconverter.ast.typed;
public class for_loop implements loop_statement, _baseNode {
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
  public cursor_loop_param cursor_loop_param = null;
  public cursor_loop_param get_cursor_loop_param() { return this.cursor_loop_param; }
  public void set_cursor_loop_param(cursor_loop_param value) {
    if (this.cursor_loop_param != null) { this.cursor_loop_param._setParent(null); }
    this.cursor_loop_param = value;
    if (this.cursor_loop_param != null) { this.cursor_loop_param._setParent(this); }
  }
  public seq_of_statements seq_of_statements = null;
  public seq_of_statements get_seq_of_statements() { return this.seq_of_statements; }
  public void set_seq_of_statements(seq_of_statements value) {
    if (this.seq_of_statements != null) { this.seq_of_statements._setParent(null); }
    this.seq_of_statements = value;
    if (this.seq_of_statements != null) { this.seq_of_statements._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.cursor_loop_param != null) {
      this.cursor_loop_param._walk(visitor);
    }
    if (this.seq_of_statements != null) {
      this.seq_of_statements._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.cursor_loop_param == child) {
      this.set_cursor_loop_param((ru.barsopen.plsqlconverter.ast.typed.cursor_loop_param)replacement);
      return;
    }
    if (this.seq_of_statements == child) {
      this.set_seq_of_statements((ru.barsopen.plsqlconverter.ast.typed.seq_of_statements)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.FOR_LOOP);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FOR_LOOP");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (cursor_loop_param == null) { throw new RuntimeException(); }
    _result.addChild(cursor_loop_param.unparse());


    if (seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(seq_of_statements.unparse());


    return _result;
  }

}
