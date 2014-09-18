package ru.barsopen.plsqlconverter.ast.typed;
public class query_partition_clause_spec_expressions implements query_partition_clause_spec, _baseNode {
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
  public java.util.List<expression> expressions = new java.util.ArrayList<expression>();
  public java.util.List<expression> get_expressions() { return this.expressions; }
  public void add_expressions(expression value) {
    insert_expressions(expressions.size(), value);
  }
  public void insert_expressions(int pos, expression value) {
    this.expressions.add(pos, value);
    value._setParent(this);
  }
  public void remove_expressions(int pos) {
    this.expressions.get(pos)._setParent(null);
    this.expressions.remove(pos);
  }
  public void remove_expressions(expression value) {
    this.remove_expressions(this.expressions.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (expression _value: this.expressions) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.expressions.size(); ++_i) {
      if (this.expressions.get(_i) == child) {
        this.remove_expressions(_i);
        this.insert_expressions(_i, (ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.QUERY_PARTITION_CLAUSE_SPEC_EXPRESSIONS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("QUERY_PARTITION_CLAUSE_SPEC_EXPRESSIONS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expressions.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < expressions.size(); ++i) {
      _result.addChild(expressions.get(i).unparse());
    }


    return _result;
  }

}
