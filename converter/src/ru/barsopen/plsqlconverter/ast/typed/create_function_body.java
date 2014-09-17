package ru.barsopen.plsqlconverter.ast.typed;
public class create_function_body implements unit_statement, package_obj_body, declare_spec, _baseNode {
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
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_CREATE = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_CREATE() { return this.SQL92_RESERVED_CREATE; }
  public void set_SQL92_RESERVED_CREATE(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_CREATE = value;
  }
  public boolean is_SQL92_RESERVED_CREATE() { return this.SQL92_RESERVED_CREATE != null; }
  public org.antlr.runtime.tree.Tree REPLACE_VK = null;
  public org.antlr.runtime.tree.Tree get_REPLACE_VK() { return this.REPLACE_VK; }
  public void set_REPLACE_VK(org.antlr.runtime.tree.Tree value) {
    this.REPLACE_VK = value;
  }
  public boolean is_REPLACE_VK() { return this.REPLACE_VK != null; }
  public function_name function_name = null;
  public function_name get_function_name() { return this.function_name; }
  public void set_function_name(function_name value) {
    if (this.function_name != null) { this.function_name._setParent(null); }
    this.function_name = value;
    if (this.function_name != null) { this.function_name._setParent(this); }
  }
  public type_spec type_spec = null;
  public type_spec get_type_spec() { return this.type_spec; }
  public void set_type_spec(type_spec value) {
    if (this.type_spec != null) { this.type_spec._setParent(null); }
    this.type_spec = value;
    if (this.type_spec != null) { this.type_spec._setParent(this); }
  }
  public parameters parameters = null;
  public parameters get_parameters() { return this.parameters; }
  public void set_parameters(parameters value) {
    if (this.parameters != null) { this.parameters._setParent(null); }
    this.parameters = value;
    if (this.parameters != null) { this.parameters._setParent(this); }
  }
  public java.util.List<invoker_rights_clause> invoker_rights_clauses = new java.util.ArrayList<invoker_rights_clause>();
  public java.util.List<invoker_rights_clause> get_invoker_rights_clauses() { return this.invoker_rights_clauses; }
  public void add_invoker_rights_clauses(invoker_rights_clause value) {
    insert_invoker_rights_clauses(invoker_rights_clauses.size(), value);
  }
  public void insert_invoker_rights_clauses(int pos, invoker_rights_clause value) {
    this.invoker_rights_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_invoker_rights_clauses(int pos) {
    this.invoker_rights_clauses.get(pos)._setParent(null);
    this.invoker_rights_clauses.remove(pos);
  }
  public void remove_invoker_rights_clauses(invoker_rights_clause value) {
    this.remove_invoker_rights_clauses(this.invoker_rights_clauses.indexOf(value));
  }
  public java.util.List<parallel_enable_clause> parallel_enable_clauses = new java.util.ArrayList<parallel_enable_clause>();
  public java.util.List<parallel_enable_clause> get_parallel_enable_clauses() { return this.parallel_enable_clauses; }
  public void add_parallel_enable_clauses(parallel_enable_clause value) {
    insert_parallel_enable_clauses(parallel_enable_clauses.size(), value);
  }
  public void insert_parallel_enable_clauses(int pos, parallel_enable_clause value) {
    this.parallel_enable_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_parallel_enable_clauses(int pos) {
    this.parallel_enable_clauses.get(pos)._setParent(null);
    this.parallel_enable_clauses.remove(pos);
  }
  public void remove_parallel_enable_clauses(parallel_enable_clause value) {
    this.remove_parallel_enable_clauses(this.parallel_enable_clauses.indexOf(value));
  }
  public java.util.List<result_cache_clause> result_cache_clauses = new java.util.ArrayList<result_cache_clause>();
  public java.util.List<result_cache_clause> get_result_cache_clauses() { return this.result_cache_clauses; }
  public void add_result_cache_clauses(result_cache_clause value) {
    insert_result_cache_clauses(result_cache_clauses.size(), value);
  }
  public void insert_result_cache_clauses(int pos, result_cache_clause value) {
    this.result_cache_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_result_cache_clauses(int pos) {
    this.result_cache_clauses.get(pos)._setParent(null);
    this.result_cache_clauses.remove(pos);
  }
  public void remove_result_cache_clauses(result_cache_clause value) {
    this.remove_result_cache_clauses(this.result_cache_clauses.indexOf(value));
  }
  public java.util.List<org.antlr.runtime.tree.Tree> DETERMINISTIC_VKs = new java.util.ArrayList<org.antlr.runtime.tree.Tree>();
  public java.util.List<org.antlr.runtime.tree.Tree> get_DETERMINISTIC_VKs() { return this.DETERMINISTIC_VKs; }
  public void add_DETERMINISTIC_VKs(org.antlr.runtime.tree.Tree value) {
    insert_DETERMINISTIC_VKs(DETERMINISTIC_VKs.size(), value);
  }
  public void insert_DETERMINISTIC_VKs(int pos, org.antlr.runtime.tree.Tree value) {
    this.DETERMINISTIC_VKs.add(pos, value);
  }
  public void remove_DETERMINISTIC_VKs(int pos) {
    this.DETERMINISTIC_VKs.remove(pos);
  }
  public void remove_DETERMINISTIC_VKs(org.antlr.runtime.tree.Tree value) {
    this.remove_DETERMINISTIC_VKs(this.DETERMINISTIC_VKs.indexOf(value));
  }
  public org.antlr.runtime.tree.Tree PIPELINED_VK = null;
  public org.antlr.runtime.tree.Tree get_PIPELINED_VK() { return this.PIPELINED_VK; }
  public void set_PIPELINED_VK(org.antlr.runtime.tree.Tree value) {
    this.PIPELINED_VK = value;
  }
  public boolean is_PIPELINED_VK() { return this.PIPELINED_VK != null; }
  public function_impl function_impl = null;
  public function_impl get_function_impl() { return this.function_impl; }
  public void set_function_impl(function_impl value) {
    if (this.function_impl != null) { this.function_impl._setParent(null); }
    this.function_impl = value;
    if (this.function_impl != null) { this.function_impl._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.SQL92_RESERVED_CREATE != null) {
      visitor.visit(this.SQL92_RESERVED_CREATE);
    }
    if (this.REPLACE_VK != null) {
      visitor.visit(this.REPLACE_VK);
    }
    if (this.function_name != null) {
      this.function_name._walk(visitor);
    }
    if (this.type_spec != null) {
      this.type_spec._walk(visitor);
    }
    if (this.parameters != null) {
      this.parameters._walk(visitor);
    }
    for (invoker_rights_clause _value: this.invoker_rights_clauses) {
      _value._walk(visitor);
    }
    for (parallel_enable_clause _value: this.parallel_enable_clauses) {
      _value._walk(visitor);
    }
    for (result_cache_clause _value: this.result_cache_clauses) {
      _value._walk(visitor);
    }
    for (org.antlr.runtime.tree.Tree _value: this.DETERMINISTIC_VKs) {
      visitor.visit(_value);
    }
    if (this.PIPELINED_VK != null) {
      visitor.visit(this.PIPELINED_VK);
    }
    if (this.function_impl != null) {
      this.function_impl._walk(visitor);
    }
  }
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
