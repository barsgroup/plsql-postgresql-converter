package ru.barsopen.plsqlconverter.ast.typed;
public class if_statement implements statement, _baseNode {
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
  public seq_of_statements seq_of_statements = null;
  public seq_of_statements get_seq_of_statements() { return this.seq_of_statements; }
  public void set_seq_of_statements(seq_of_statements value) {
    if (this.seq_of_statements != null) { this.seq_of_statements._setParent(null); }
    this.seq_of_statements = value;
    if (this.seq_of_statements != null) { this.seq_of_statements._setParent(this); }
  }
  public java.util.List<elsif_part> elsif_parts = new java.util.ArrayList<elsif_part>();
  public java.util.List<elsif_part> get_elsif_parts() { return this.elsif_parts; }
  public void add_elsif_parts(elsif_part value) {
    insert_elsif_parts(elsif_parts.size(), value);
  }
  public void insert_elsif_parts(int pos, elsif_part value) {
    this.elsif_parts.add(pos, value);
    value._setParent(this);
  }
  public void remove_elsif_parts(int pos) {
    this.elsif_parts.get(pos)._setParent(null);
    this.elsif_parts.remove(pos);
  }
  public void remove_elsif_parts(elsif_part value) {
    this.remove_elsif_parts(this.elsif_parts.indexOf(value));
  }
  public else_part else_part = null;
  public else_part get_else_part() { return this.else_part; }
  public void set_else_part(else_part value) {
    if (this.else_part != null) { this.else_part._setParent(null); }
    this.else_part = value;
    if (this.else_part != null) { this.else_part._setParent(this); }
  }
  public boolean is_else_part() { return this.else_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PLSQL_RESERVED_IF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_RESERVED_IF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(seq_of_statements.unparse());


    for (int i = 0; i < elsif_parts.size(); ++i) {
      _result.addChild(elsif_parts.get(i).unparse());
    }


    if (else_part != null) {
      _result.addChild(else_part.unparse());
    }


    return _result;
  }

}
