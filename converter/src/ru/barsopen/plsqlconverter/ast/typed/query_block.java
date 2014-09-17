package ru.barsopen.plsqlconverter.ast.typed;
public class query_block implements subquery_basic_elements, _baseNode {
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
  public from_clause from_clause = null;
  public from_clause get_from_clause() { return this.from_clause; }
  public void set_from_clause(from_clause value) {
    if (this.from_clause != null) { this.from_clause._setParent(null); }
    this.from_clause = value;
    if (this.from_clause != null) { this.from_clause._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT; }
  public void set_SQL92_RESERVED_DISTINCT(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_DISTINCT = value;
  }
  public boolean is_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE; }
  public void set_SQL92_RESERVED_UNIQUE(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_UNIQUE = value;
  }
  public boolean is_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL; }
  public void set_SQL92_RESERVED_ALL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_ALL = value;
  }
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public query_block_projection query_block_projection = null;
  public query_block_projection get_query_block_projection() { return this.query_block_projection; }
  public void set_query_block_projection(query_block_projection value) {
    if (this.query_block_projection != null) { this.query_block_projection._setParent(null); }
    this.query_block_projection = value;
    if (this.query_block_projection != null) { this.query_block_projection._setParent(this); }
  }
  public into_clause into_clause = null;
  public into_clause get_into_clause() { return this.into_clause; }
  public void set_into_clause(into_clause value) {
    if (this.into_clause != null) { this.into_clause._setParent(null); }
    this.into_clause = value;
    if (this.into_clause != null) { this.into_clause._setParent(this); }
  }
  public boolean is_into_clause() { return this.into_clause != null; }
  public where_clause where_clause = null;
  public where_clause get_where_clause() { return this.where_clause; }
  public void set_where_clause(where_clause value) {
    if (this.where_clause != null) { this.where_clause._setParent(null); }
    this.where_clause = value;
    if (this.where_clause != null) { this.where_clause._setParent(this); }
  }
  public boolean is_where_clause() { return this.where_clause != null; }
  public hierarchical_query_clause hierarchical_query_clause = null;
  public hierarchical_query_clause get_hierarchical_query_clause() { return this.hierarchical_query_clause; }
  public void set_hierarchical_query_clause(hierarchical_query_clause value) {
    if (this.hierarchical_query_clause != null) { this.hierarchical_query_clause._setParent(null); }
    this.hierarchical_query_clause = value;
    if (this.hierarchical_query_clause != null) { this.hierarchical_query_clause._setParent(this); }
  }
  public boolean is_hierarchical_query_clause() { return this.hierarchical_query_clause != null; }
  public group_by_clause group_by_clause = null;
  public group_by_clause get_group_by_clause() { return this.group_by_clause; }
  public void set_group_by_clause(group_by_clause value) {
    if (this.group_by_clause != null) { this.group_by_clause._setParent(null); }
    this.group_by_clause = value;
    if (this.group_by_clause != null) { this.group_by_clause._setParent(this); }
  }
  public boolean is_group_by_clause() { return this.group_by_clause != null; }
  public model_clause model_clause = null;
  public model_clause get_model_clause() { return this.model_clause; }
  public void set_model_clause(model_clause value) {
    if (this.model_clause != null) { this.model_clause._setParent(null); }
    this.model_clause = value;
    if (this.model_clause != null) { this.model_clause._setParent(this); }
  }
  public boolean is_model_clause() { return this.model_clause != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.from_clause != null) {
      this.from_clause._walk(visitor);
    }
    if (this.SQL92_RESERVED_DISTINCT != null) {
      visitor.visit(this.SQL92_RESERVED_DISTINCT);
    }
    if (this.SQL92_RESERVED_UNIQUE != null) {
      visitor.visit(this.SQL92_RESERVED_UNIQUE);
    }
    if (this.SQL92_RESERVED_ALL != null) {
      visitor.visit(this.SQL92_RESERVED_ALL);
    }
    if (this.query_block_projection != null) {
      this.query_block_projection._walk(visitor);
    }
    if (this.into_clause != null) {
      this.into_clause._walk(visitor);
    }
    if (this.where_clause != null) {
      this.where_clause._walk(visitor);
    }
    if (this.hierarchical_query_clause != null) {
      this.hierarchical_query_clause._walk(visitor);
    }
    if (this.group_by_clause != null) {
      this.group_by_clause._walk(visitor);
    }
    if (this.model_clause != null) {
      this.model_clause._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_SELECT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_SELECT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (from_clause == null) { throw new RuntimeException(); }
    _result.addChild(from_clause.unparse());


    if (SQL92_RESERVED_DISTINCT != null) {
      _result.addChild(SQL92_RESERVED_DISTINCT);
    }


    if (SQL92_RESERVED_UNIQUE != null) {
      _result.addChild(SQL92_RESERVED_UNIQUE);
    }


    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    if (query_block_projection == null) { throw new RuntimeException(); }
    _result.addChild(query_block_projection.unparse());


    if (into_clause != null) {
      _result.addChild(into_clause.unparse());
    }


    if (where_clause != null) {
      _result.addChild(where_clause.unparse());
    }


    if (hierarchical_query_clause != null) {
      _result.addChild(hierarchical_query_clause.unparse());
    }


    if (group_by_clause != null) {
      _result.addChild(group_by_clause.unparse());
    }


    if (model_clause != null) {
      _result.addChild(model_clause.unparse());
    }


    return _result;
  }

}
