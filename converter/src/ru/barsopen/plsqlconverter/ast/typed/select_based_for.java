package ru.barsopen.plsqlconverter.ast.typed;
public class select_based_for implements cursor_loop_param, _baseNode {
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
  public record_name record_name = null;
  public record_name get_record_name() { return this.record_name; }
  public void set_record_name(record_name value) {
    if (this.record_name != null) { this.record_name._setParent(null); }
    this.record_name = value;
    if (this.record_name != null) { this.record_name._setParent(this); }
  }
  public select_statement select_statement = null;
  public select_statement get_select_statement() { return this.select_statement; }
  public void set_select_statement(select_statement value) {
    if (this.select_statement != null) { this.select_statement._setParent(null); }
    this.select_statement = value;
    if (this.select_statement != null) { this.select_statement._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.record_name != null) {
      this.record_name._walk(visitor);
    }
    if (this.select_statement != null) {
      this.select_statement._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.record_name == child) {
      this.set_record_name((ru.barsopen.plsqlconverter.ast.typed.record_name)replacement);
      return;
    }
    if (this.select_statement == child) {
      this.set_select_statement((ru.barsopen.plsqlconverter.ast.typed.select_statement)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_BASED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_BASED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (record_name == null) { throw new RuntimeException(); }
    _result.addChild(record_name.unparse());


    if (select_statement == null) { throw new RuntimeException(); }
    _result.addChild(select_statement.unparse());


    return _result;
  }

}
