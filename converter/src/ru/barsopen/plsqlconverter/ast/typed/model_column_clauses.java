package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_clauses implements _baseNode {
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
  public model_column_clauses_dimension model_column_clauses_dimension = null;
  public model_column_clauses_dimension get_model_column_clauses_dimension() { return this.model_column_clauses_dimension; }
  public void set_model_column_clauses_dimension(model_column_clauses_dimension value) {
    if (this.model_column_clauses_dimension != null) { this.model_column_clauses_dimension._setParent(null); }
    this.model_column_clauses_dimension = value;
    if (this.model_column_clauses_dimension != null) { this.model_column_clauses_dimension._setParent(this); }
  }
  public model_column_clauses_measures model_column_clauses_measures = null;
  public model_column_clauses_measures get_model_column_clauses_measures() { return this.model_column_clauses_measures; }
  public void set_model_column_clauses_measures(model_column_clauses_measures value) {
    if (this.model_column_clauses_measures != null) { this.model_column_clauses_measures._setParent(null); }
    this.model_column_clauses_measures = value;
    if (this.model_column_clauses_measures != null) { this.model_column_clauses_measures._setParent(this); }
  }
  public model_column_partition_part model_column_partition_part = null;
  public model_column_partition_part get_model_column_partition_part() { return this.model_column_partition_part; }
  public void set_model_column_partition_part(model_column_partition_part value) {
    if (this.model_column_partition_part != null) { this.model_column_partition_part._setParent(null); }
    this.model_column_partition_part = value;
    if (this.model_column_partition_part != null) { this.model_column_partition_part._setParent(this); }
  }
  public boolean is_model_column_partition_part() { return this.model_column_partition_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.model_column_clauses_dimension != null) {
      this.model_column_clauses_dimension._walk(visitor);
    }
    if (this.model_column_clauses_measures != null) {
      this.model_column_clauses_measures._walk(visitor);
    }
    if (this.model_column_partition_part != null) {
      this.model_column_partition_part._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.model_column_clauses_dimension == child) {
      this.set_model_column_clauses_dimension((ru.barsopen.plsqlconverter.ast.typed.model_column_clauses_dimension)replacement);
      return;
    }
    if (this.model_column_clauses_measures == child) {
      this.set_model_column_clauses_measures((ru.barsopen.plsqlconverter.ast.typed.model_column_clauses_measures)replacement);
      return;
    }
    if (this.model_column_partition_part == child) {
      this.set_model_column_partition_part((ru.barsopen.plsqlconverter.ast.typed.model_column_partition_part)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_COLUMN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_COLUMN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_column_clauses_dimension == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses_dimension.unparse());


    if (model_column_clauses_measures == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses_measures.unparse());


    if (model_column_partition_part != null) {
      _result.addChild(model_column_partition_part.unparse());
    }


    return _result;
  }

}
