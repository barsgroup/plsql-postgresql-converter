package ru.barsopen.plsqlconverter.ast.typed;
public class alter_function implements unit_statement, _baseNode {
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
  public function_name function_name = null;
  public function_name get_function_name() { return this.function_name; }
  public void set_function_name(function_name value) {
    if (this.function_name != null) { this.function_name._setParent(null); }
    this.function_name = value;
    if (this.function_name != null) { this.function_name._setParent(this); }
  }
  public org.antlr.runtime.tree.Tree DEBUG_VK = null;
  public org.antlr.runtime.tree.Tree get_DEBUG_VK() { return this.DEBUG_VK; }
  public void set_DEBUG_VK(org.antlr.runtime.tree.Tree value) {
    this.DEBUG_VK = value;
  }
  public boolean is_DEBUG_VK() { return this.DEBUG_VK != null; }
  public org.antlr.runtime.tree.Tree REUSE_VK = null;
  public org.antlr.runtime.tree.Tree get_REUSE_VK() { return this.REUSE_VK; }
  public void set_REUSE_VK(org.antlr.runtime.tree.Tree value) {
    this.REUSE_VK = value;
  }
  public boolean is_REUSE_VK() { return this.REUSE_VK != null; }
  public java.util.List<compiler_parameters_clause> compiler_parameters_clauses = new java.util.ArrayList<compiler_parameters_clause>();
  public java.util.List<compiler_parameters_clause> get_compiler_parameters_clauses() { return this.compiler_parameters_clauses; }
  public void add_compiler_parameters_clauses(compiler_parameters_clause value) {
    insert_compiler_parameters_clauses(compiler_parameters_clauses.size(), value);
  }
  public void insert_compiler_parameters_clauses(int pos, compiler_parameters_clause value) {
    this.compiler_parameters_clauses.add(pos, value);
    value._setParent(this);
  }
  public void remove_compiler_parameters_clauses(int pos) {
    this.compiler_parameters_clauses.get(pos)._setParent(null);
    this.compiler_parameters_clauses.remove(pos);
  }
  public void remove_compiler_parameters_clauses(compiler_parameters_clause value) {
    this.remove_compiler_parameters_clauses(this.compiler_parameters_clauses.indexOf(value));
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ALTER_FUNCTION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ALTER_FUNCTION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (function_name == null) { throw new RuntimeException(); }
    _result.addChild(function_name.unparse());


    if (DEBUG_VK != null) {
      _result.addChild(DEBUG_VK);
    }


    if (REUSE_VK != null) {
      _result.addChild(REUSE_VK);
    }


    for (int i = 0; i < compiler_parameters_clauses.size(); ++i) {
      _result.addChild(compiler_parameters_clauses.get(i).unparse());
    }


    return _result;
  }

}
