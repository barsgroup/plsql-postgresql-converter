package ru.barsopen.plsqlconverter.ast.typed;
public class function_spec implements package_obj_spec, package_obj_body {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public function_name function_name = null;
  public type_spec type_spec = null;
  public parameters parameters = null;
  public org.antlr.runtime.tree.Tree PIPELINED_VK = null;
  public boolean is_PIPELINED_VK() { return this.PIPELINED_VK != null; }
  public org.antlr.runtime.tree.Tree RESULT_CACHE_VK = null;
  public boolean is_RESULT_CACHE_VK() { return this.RESULT_CACHE_VK != null; }
  public org.antlr.runtime.tree.Tree DETERMINISTIC_VK = null;
  public boolean is_DETERMINISTIC_VK() { return this.DETERMINISTIC_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FUNCTION_SPEC);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FUNCTION_SPEC");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (function_name == null) { throw new RuntimeException(); }
    _result.addChild(function_name.unparse());


    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    if (parameters == null) { throw new RuntimeException(); }
    _result.addChild(parameters.unparse());


    if (PIPELINED_VK != null) {
      _result.addChild(PIPELINED_VK);
    }


    if (RESULT_CACHE_VK != null) {
      _result.addChild(RESULT_CACHE_VK);
    }


    if (DETERMINISTIC_VK != null) {
      _result.addChild(DETERMINISTIC_VK);
    }


    return _result;
  }

}
