package ru.barsopen.plsqlconverter.ast.typed;
public class case_statement_simple implements case_statement, _baseNode {
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
  public java.util.List<case_when_part> case_when_parts = new java.util.ArrayList<case_when_part>();
  public java.util.List<case_when_part> get_case_when_parts() { return this.case_when_parts; }
  public void add_case_when_parts(case_when_part value) {
    insert_case_when_parts(case_when_parts.size(), value);
  }
  public void insert_case_when_parts(int pos, case_when_part value) {
    this.case_when_parts.add(pos, value);
    value._setParent(this);
  }
  public void remove_case_when_parts(int pos) {
    this.case_when_parts.get(pos)._setParent(null);
    this.case_when_parts.remove(pos);
  }
  public void remove_case_when_parts(case_when_part value) {
    this.remove_case_when_parts(this.case_when_parts.indexOf(value));
  }
  public case_else_part case_else_part = null;
  public case_else_part get_case_else_part() { return this.case_else_part; }
  public void set_case_else_part(case_else_part value) {
    if (this.case_else_part != null) { this.case_else_part._setParent(null); }
    this.case_else_part = value;
    if (this.case_else_part != null) { this.case_else_part._setParent(this); }
  }
  public boolean is_case_else_part() { return this.case_else_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    for (case_when_part _value: this.case_when_parts) {
      _value._walk(visitor);
    }
    if (this.case_else_part != null) {
      this.case_else_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SIMPLE_CASE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SIMPLE_CASE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (case_when_parts.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < case_when_parts.size(); ++i) {
      _result.addChild(case_when_parts.get(i).unparse());
    }


    if (case_else_part != null) {
      _result.addChild(case_else_part.unparse());
    }


    return _result;
  }

}
