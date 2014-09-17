package ru.barsopen.plsqlconverter.ast.typed;
public class general_table_ref implements _baseNode {
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
  public alias alias = null;
  public alias get_alias() { return this.alias; }
  public void set_alias(alias value) {
    if (this.alias != null) { this.alias._setParent(null); }
    this.alias = value;
    if (this.alias != null) { this.alias._setParent(this); }
  }
  public boolean is_alias() { return this.alias != null; }
  public dml_table_expression_clause dml_table_expression_clause = null;
  public dml_table_expression_clause get_dml_table_expression_clause() { return this.dml_table_expression_clause; }
  public void set_dml_table_expression_clause(dml_table_expression_clause value) {
    if (this.dml_table_expression_clause != null) { this.dml_table_expression_clause._setParent(null); }
    this.dml_table_expression_clause = value;
    if (this.dml_table_expression_clause != null) { this.dml_table_expression_clause._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree ONLY_VK = null;
  public org.antlr.runtime.tree.Tree get_ONLY_VK() { return this.ONLY_VK; }
  public void set_ONLY_VK(org.antlr.runtime.tree.Tree value) {
    this.ONLY_VK = value;
  }
  public boolean is_ONLY_VK() { return this.ONLY_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.TABLE_REF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_REF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (dml_table_expression_clause == null) { throw new RuntimeException(); }
    _result.addChild(dml_table_expression_clause.unparse());


    if (ONLY_VK != null) {
      _result.addChild(ONLY_VK);
    }


    return _result;
  }

}
