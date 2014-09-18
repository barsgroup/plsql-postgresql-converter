package ru.barsopen.plsqlconverter.ast.typed;
public class table_ref_aux implements _baseNode {
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
  public pivot_clause pivot_clause = null;
  public pivot_clause get_pivot_clause() { return this.pivot_clause; }
  public void set_pivot_clause(pivot_clause value) {
    if (this.pivot_clause != null) { this.pivot_clause._setParent(null); }
    this.pivot_clause = value;
    if (this.pivot_clause != null) { this.pivot_clause._setParent(this); }
  }
  public boolean is_pivot_clause() { return this.pivot_clause != null; }
  public unpivot_clause unpivot_clause = null;
  public unpivot_clause get_unpivot_clause() { return this.unpivot_clause; }
  public void set_unpivot_clause(unpivot_clause value) {
    if (this.unpivot_clause != null) { this.unpivot_clause._setParent(null); }
    this.unpivot_clause = value;
    if (this.unpivot_clause != null) { this.unpivot_clause._setParent(this); }
  }
  public boolean is_unpivot_clause() { return this.unpivot_clause != null; }
  public java.util.List<flashback_query_clause> flashback_query_clauses = new java.util.ArrayList<flashback_query_clause>();
  public java.util.List<flashback_query_clause> get_flashback_query_clauses() { return this.flashback_query_clauses; }
  public void add_flashback_query_clauses(flashback_query_clause value) {
    insert_flashback_query_clauses(flashback_query_clauses.size(), value);
  }
  public void insert_flashback_query_clauses(int pos, flashback_query_clause value) {
    this.flashback_query_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_flashback_query_clauses(int pos) {
    this.flashback_query_clauses.get(pos)._setParent(null);
    this.flashback_query_clauses.remove(pos);
  }
  public void remove_flashback_query_clauses(flashback_query_clause value) {
    this.remove_flashback_query_clauses(this.flashback_query_clauses.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.alias != null) {
      this.alias._walk(visitor);
    }
    if (this.dml_table_expression_clause != null) {
      this.dml_table_expression_clause._walk(visitor);
    }
    if (this.ONLY_VK != null) {
      visitor.visit(this.ONLY_VK);
    }
    if (this.pivot_clause != null) {
      this.pivot_clause._walk(visitor);
    }
    if (this.unpivot_clause != null) {
      this.unpivot_clause._walk(visitor);
    }
    for (flashback_query_clause _value: this.flashback_query_clauses) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.alias == child) {
      this.set_alias((ru.barsopen.plsqlconverter.ast.typed.alias)replacement);
      return;
    }
    if (this.dml_table_expression_clause == child) {
      this.set_dml_table_expression_clause((ru.barsopen.plsqlconverter.ast.typed.dml_table_expression_clause)replacement);
      return;
    }
    if (this.pivot_clause == child) {
      this.set_pivot_clause((ru.barsopen.plsqlconverter.ast.typed.pivot_clause)replacement);
      return;
    }
    if (this.unpivot_clause == child) {
      this.set_unpivot_clause((ru.barsopen.plsqlconverter.ast.typed.unpivot_clause)replacement);
      return;
    }
    for (int _i = 0; _i < this.flashback_query_clauses.size(); ++_i) {
      if (this.flashback_query_clauses.get(_i) == child) {
        this.remove_flashback_query_clauses(_i);
        this.insert_flashback_query_clauses(_i, (ru.barsopen.plsqlconverter.ast.typed.flashback_query_clause)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_REF_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("TABLE_REF_ELEMENT");
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


    if (pivot_clause != null) {
      _result.addChild(pivot_clause.unparse());
    }


    if (unpivot_clause != null) {
      _result.addChild(unpivot_clause.unparse());
    }


    for (int i = 0; i < flashback_query_clauses.size(); ++i) {
      _result.addChild(flashback_query_clauses.get(i).unparse());
    }


    return _result;
  }

}
