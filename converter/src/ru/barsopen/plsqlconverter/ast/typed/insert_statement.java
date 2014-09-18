package ru.barsopen.plsqlconverter.ast.typed;
public class insert_statement implements data_manipulation_language_statements, _baseNode {
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
  public insert_statement_spec insert_statement_spec = null;
  public insert_statement_spec get_insert_statement_spec() { return this.insert_statement_spec; }
  public void set_insert_statement_spec(insert_statement_spec value) {
    if (this.insert_statement_spec != null) { this.insert_statement_spec._setParent(null); }
    this.insert_statement_spec = value;
    if (this.insert_statement_spec != null) { this.insert_statement_spec._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.insert_statement_spec != null) {
      this.insert_statement_spec._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INSERT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_INSERT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (insert_statement_spec == null) { throw new RuntimeException(); }
    _result.addChild(insert_statement_spec.unparse());


    return _result;
  }

}
