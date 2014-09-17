package ru.barsopen.plsqlconverter.ast.typed;
public class query_block implements subquery_basic_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public from_clause from_clause = null;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT = null;
  public boolean is_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE = null;
  public boolean is_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public query_block_projection query_block_projection = null;
  public into_clause into_clause = null;
  public boolean is_into_clause() { return this.into_clause != null; }
  public where_clause where_clause = null;
  public boolean is_where_clause() { return this.where_clause != null; }
  public hierarchical_query_clause hierarchical_query_clause = null;
  public boolean is_hierarchical_query_clause() { return this.hierarchical_query_clause != null; }
  public group_by_clause group_by_clause = null;
  public boolean is_group_by_clause() { return this.group_by_clause != null; }
  public model_clause model_clause = null;
  public boolean is_model_clause() { return this.model_clause != null; }

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
