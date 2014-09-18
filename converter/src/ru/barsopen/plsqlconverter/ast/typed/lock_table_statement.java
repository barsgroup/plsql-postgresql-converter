package ru.barsopen.plsqlconverter.ast.typed;
public class lock_table_statement implements data_manipulation_language_statements, _baseNode {
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
  public java.util.List<lock_table_element> lock_table_elements = new java.util.ArrayList<lock_table_element>();
  public java.util.List<lock_table_element> get_lock_table_elements() { return this.lock_table_elements; }
  public void add_lock_table_elements(lock_table_element value) {
    insert_lock_table_elements(lock_table_elements.size(), value);
  }
  public void insert_lock_table_elements(int pos, lock_table_element value) {
    this.lock_table_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_lock_table_elements(int pos) {
    this.lock_table_elements.get(pos)._setParent(null);
    this.lock_table_elements.remove(pos);
  }
  public void remove_lock_table_elements(lock_table_element value) {
    this.remove_lock_table_elements(this.lock_table_elements.indexOf(value));
  }
  public lock_mode lock_mode = null;
  public lock_mode get_lock_mode() { return this.lock_mode; }
  public void set_lock_mode(lock_mode value) {
    if (this.lock_mode != null) { this.lock_mode._setParent(null); }
    this.lock_mode = value;
    if (this.lock_mode != null) { this.lock_mode._setParent(this); }
  }
  public wait_nowait_part wait_nowait_part = null;
  public wait_nowait_part get_wait_nowait_part() { return this.wait_nowait_part; }
  public void set_wait_nowait_part(wait_nowait_part value) {
    if (this.wait_nowait_part != null) { this.wait_nowait_part._setParent(null); }
    this.wait_nowait_part = value;
    if (this.wait_nowait_part != null) { this.wait_nowait_part._setParent(this); }
  }
  public boolean is_wait_nowait_part() { return this.wait_nowait_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (lock_table_element _value: this.lock_table_elements) {
      _value._walk(visitor);
    }
    if (this.lock_mode != null) {
      this.lock_mode._walk(visitor);
    }
    if (this.wait_nowait_part != null) {
      this.wait_nowait_part._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.lock_table_elements.size(); ++_i) {
      if (this.lock_table_elements.get(_i) == child) {
        this.remove_lock_table_elements(_i);
        this.insert_lock_table_elements(_i, (ru.barsopen.plsqlconverter.ast.typed.lock_table_element)replacement);
        return;
      }
    }
    if (this.lock_mode == child) {
      this.set_lock_mode((ru.barsopen.plsqlconverter.ast.typed.lock_mode)replacement);
      return;
    }
    if (this.wait_nowait_part == child) {
      this.set_wait_nowait_part((ru.barsopen.plsqlconverter.ast.typed.wait_nowait_part)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_LOCK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_RESERVED_LOCK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (lock_table_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < lock_table_elements.size(); ++i) {
      _result.addChild(lock_table_elements.get(i).unparse());
    }


    if (lock_mode == null) { throw new RuntimeException(); }
    _result.addChild(lock_mode.unparse());


    if (wait_nowait_part != null) {
      _result.addChild(wait_nowait_part.unparse());
    }


    return _result;
  }

}
