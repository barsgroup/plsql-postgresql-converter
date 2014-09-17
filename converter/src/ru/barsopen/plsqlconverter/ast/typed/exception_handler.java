package ru.barsopen.plsqlconverter.ast.typed;
public class exception_handler implements _baseNode {
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
  public java.util.List<exception_name> exception_names = new java.util.ArrayList<exception_name>();
  public java.util.List<exception_name> get_exception_names() { return this.exception_names; }
  public void add_exception_names(exception_name value) {
    insert_exception_names(exception_names.size(), value);
  }
  public void insert_exception_names(int pos, exception_name value) {
    this.exception_names.add(pos, value);
    value._setParent(this);
  }
  public void remove_exception_names(int pos) {
    this.exception_names.get(pos)._setParent(null);
    this.exception_names.remove(pos);
  }
  public void remove_exception_names(exception_name value) {
    this.remove_exception_names(this.exception_names.indexOf(value));
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
    for (exception_name _value: this.exception_names) {
      _value._walk(visitor);
    }
    if (this.seq_of_statements != null) {
      this.seq_of_statements._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_WHEN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WHEN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_names.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < exception_names.size(); ++i) {
      _result.addChild(exception_names.get(i).unparse());
    }


    if (seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(seq_of_statements.unparse());


    return _result;
  }

}
