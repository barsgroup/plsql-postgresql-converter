package ru.barsopen.plsqlconverter.ast.typed;
public class model_clause implements _baseNode {
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
  public main_model main_model = null;
  public main_model get_main_model() { return this.main_model; }
  public void set_main_model(main_model value) {
    if (this.main_model != null) { this.main_model._setParent(null); }
    this.main_model = value;
    if (this.main_model != null) { this.main_model._setParent(this); }
  }
  public java.util.List<cell_reference_options> cell_reference_optionss = new java.util.ArrayList<cell_reference_options>();
  public java.util.List<cell_reference_options> get_cell_reference_optionss() { return this.cell_reference_optionss; }
  public void add_cell_reference_optionss(cell_reference_options value) {
    insert_cell_reference_optionss(cell_reference_optionss.size(), value);
  }
  public void insert_cell_reference_optionss(int pos, cell_reference_options value) {
    this.cell_reference_optionss.add(pos, value);
    value._setParent(this);
  }
  public void remove_cell_reference_optionss(int pos) {
    this.cell_reference_optionss.get(pos)._setParent(null);
    this.cell_reference_optionss.remove(pos);
  }
  public void remove_cell_reference_optionss(cell_reference_options value) {
    this.remove_cell_reference_optionss(this.cell_reference_optionss.indexOf(value));
  }
  public return_rows_clause return_rows_clause = null;
  public return_rows_clause get_return_rows_clause() { return this.return_rows_clause; }
  public void set_return_rows_clause(return_rows_clause value) {
    if (this.return_rows_clause != null) { this.return_rows_clause._setParent(null); }
    this.return_rows_clause = value;
    if (this.return_rows_clause != null) { this.return_rows_clause._setParent(this); }
  }
  public boolean is_return_rows_clause() { return this.return_rows_clause != null; }
  public java.util.List<reference_model> reference_models = new java.util.ArrayList<reference_model>();
  public java.util.List<reference_model> get_reference_models() { return this.reference_models; }
  public void add_reference_models(reference_model value) {
    insert_reference_models(reference_models.size(), value);
  }
  public void insert_reference_models(int pos, reference_model value) {
    this.reference_models.add(pos, value);
    value._setParent(this);
  }
  public void remove_reference_models(int pos) {
    this.reference_models.get(pos)._setParent(null);
    this.reference_models.remove(pos);
  }
  public void remove_reference_models(reference_model value) {
    this.remove_reference_models(this.reference_models.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.main_model != null) {
      this.main_model._walk(visitor);
    }
    for (cell_reference_options _value: this.cell_reference_optionss) {
      _value._walk(visitor);
    }
    if (this.return_rows_clause != null) {
      this.return_rows_clause._walk(visitor);
    }
    for (reference_model _value: this.reference_models) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PLSQL_NON_RESERVED_MODEL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_NON_RESERVED_MODEL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (main_model == null) { throw new RuntimeException(); }
    _result.addChild(main_model.unparse());


    for (int i = 0; i < cell_reference_optionss.size(); ++i) {
      _result.addChild(cell_reference_optionss.get(i).unparse());
    }


    if (return_rows_clause != null) {
      _result.addChild(return_rows_clause.unparse());
    }


    for (int i = 0; i < reference_models.size(); ++i) {
      _result.addChild(reference_models.get(i).unparse());
    }


    return _result;
  }

}
