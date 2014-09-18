package ru.barsopen.plsqlconverter.ast.typed;
public class update_set_elements_assign implements update_set_elements, _baseNode {
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
  public java.util.List<column_name> column_names = new java.util.ArrayList<column_name>();
  public java.util.List<column_name> get_column_names() { return this.column_names; }
  public void add_column_names(column_name value) {
    insert_column_names(column_names.size(), value);
  }
  public void insert_column_names(int pos, column_name value) {
    this.column_names.add(pos, value);
    value._setParent(this);
  }
  public void remove_column_names(int pos) {
    this.column_names.get(pos)._setParent(null);
    this.column_names.remove(pos);
  }
  public void remove_column_names(column_name value) {
    this.remove_column_names(this.column_names.indexOf(value));
  }
  public expression_or_subquery expression_or_subquery = null;
  public expression_or_subquery get_expression_or_subquery() { return this.expression_or_subquery; }
  public void set_expression_or_subquery(expression_or_subquery value) {
    if (this.expression_or_subquery != null) { this.expression_or_subquery._setParent(null); }
    this.expression_or_subquery = value;
    if (this.expression_or_subquery != null) { this.expression_or_subquery._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (column_name _value: this.column_names) {
      _value._walk(visitor);
    }
    if (this.expression_or_subquery != null) {
      this.expression_or_subquery._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.column_names.size(); ++_i) {
      if (this.column_names.get(_i) == child) {
        this.remove_column_names(_i);
        this.insert_column_names(_i, (ru.barsopen.plsqlconverter.ast.typed.column_name)replacement);
        return;
      }
    }
    if (this.expression_or_subquery == child) {
      this.set_expression_or_subquery((ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.ASSIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ASSIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (column_names.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < column_names.size(); ++i) {
      _result.addChild(column_names.get(i).unparse());
    }


    if (expression_or_subquery == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_subquery.unparse());


    return _result;
  }

}
