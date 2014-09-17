package ru.barsopen.plsqlconverter.ast.typed;
public class create_function_body implements unit_statement, package_obj_body, declare_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_CREATE = null;
  public boolean is_SQL92_RESERVED_CREATE() { return this.SQL92_RESERVED_CREATE != null; }
  public org.antlr.runtime.tree.Tree REPLACE_VK = null;
  public boolean is_REPLACE_VK() { return this.REPLACE_VK != null; }
  public function_name function_name = null;
  public type_spec type_spec = null;
  public parameters parameters = null;
  public java.util.List<invoker_rights_clause> invoker_rights_clauses = new java.util.ArrayList<invoker_rights_clause>();
  public java.util.List<parallel_enable_clause> parallel_enable_clauses = new java.util.ArrayList<parallel_enable_clause>();
  public java.util.List<result_cache_clause> result_cache_clauses = new java.util.ArrayList<result_cache_clause>();
  public java.util.List<org.antlr.runtime.tree.Tree> DETERMINISTIC_VKs = new java.util.ArrayList<org.antlr.runtime.tree.Tree>();
  public org.antlr.runtime.tree.Tree PIPELINED_VK = null;
  public boolean is_PIPELINED_VK() { return this.PIPELINED_VK != null; }
  public function_impl function_impl = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CREATE_FUNCTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CREATE_FUNCTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_CREATE != null) {
      _result.addChild(SQL92_RESERVED_CREATE);
    }


    if (REPLACE_VK != null) {
      _result.addChild(REPLACE_VK);
    }


    if (function_name == null) { throw new RuntimeException(); }
    _result.addChild(function_name.unparse());


    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    if (parameters == null) { throw new RuntimeException(); }
    _result.addChild(parameters.unparse());


    for (int i = 0; i < invoker_rights_clauses.size(); ++i) {
      _result.addChild(invoker_rights_clauses.get(i).unparse());
    }


    for (int i = 0; i < parallel_enable_clauses.size(); ++i) {
      _result.addChild(parallel_enable_clauses.get(i).unparse());
    }


    for (int i = 0; i < result_cache_clauses.size(); ++i) {
      _result.addChild(result_cache_clauses.get(i).unparse());
    }


    for (int i = 0; i < DETERMINISTIC_VKs.size(); ++i) {
      _result.addChild(DETERMINISTIC_VKs.get(i));
    }


    if (PIPELINED_VK != null) {
      _result.addChild(PIPELINED_VK);
    }


    if (function_impl == null) { throw new RuntimeException(); }
    _result.addChild(function_impl.unparse());


    return _result;
  }

}
