package ru.barsopen.plsqlconverter.ast.typed;
public class variable_declaration implements package_obj_spec, package_obj_body, declare_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public variable_name variable_name = null;
  public type_spec type_spec = null;
  public org.antlr.runtime.tree.Tree CONSTANT_VK = null;
  public boolean is_CONSTANT_VK() { return this.CONSTANT_VK != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL = null;
  public boolean is_SQL92_RESERVED_NULL() { return this.SQL92_RESERVED_NULL != null; }
  public default_value_part default_value_part = null;
  public boolean is_default_value_part() { return this.default_value_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.VARIABLE_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("VARIABLE_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (variable_name == null) { throw new RuntimeException(); }
    _result.addChild(variable_name.unparse());


    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    if (CONSTANT_VK != null) {
      _result.addChild(CONSTANT_VK);
    }


    if (SQL92_RESERVED_NULL != null) {
      _result.addChild(SQL92_RESERVED_NULL);
    }


    if (default_value_part != null) {
      _result.addChild(default_value_part.unparse());
    }


    return _result;
  }

}
