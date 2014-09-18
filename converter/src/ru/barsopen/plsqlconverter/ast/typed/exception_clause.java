package ru.barsopen.plsqlconverter.ast.typed;
public class exception_clause implements _baseNode {
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
  public java.util.List<exception_handler> exception_handlers = new java.util.ArrayList<exception_handler>();
  public java.util.List<exception_handler> get_exception_handlers() { return this.exception_handlers; }
  public void add_exception_handlers(exception_handler value) {
    insert_exception_handlers(exception_handlers.size(), value);
  }
  public void insert_exception_handlers(int pos, exception_handler value) {
    this.exception_handlers.add(pos, value);
    value._setParent(this);
  }
  public void remove_exception_handlers(int pos) {
    this.exception_handlers.get(pos)._setParent(null);
    this.exception_handlers.remove(pos);
  }
  public void remove_exception_handlers(exception_handler value) {
    this.remove_exception_handlers(this.exception_handlers.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (exception_handler _value: this.exception_handlers) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.exception_handlers.size(); ++_i) {
      if (this.exception_handlers.get(_i) == child) {
        this.remove_exception_handlers(_i);
        this.insert_exception_handlers(_i, (ru.barsopen.plsqlconverter.ast.typed.exception_handler)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_EXCEPTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_EXCEPTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_handlers.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < exception_handlers.size(); ++i) {
      _result.addChild(exception_handlers.get(i).unparse());
    }


    return _result;
  }

}
