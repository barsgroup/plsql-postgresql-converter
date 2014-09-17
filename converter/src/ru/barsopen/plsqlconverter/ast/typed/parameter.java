package ru.barsopen.plsqlconverter.ast.typed;
public class parameter {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public parameter_name parameter_name = null;
  public java.util.List<parameter_dir_spec> parameter_dir_specs = new java.util.ArrayList<parameter_dir_spec>();
  public type_spec type_spec = null;
  public boolean is_type_spec() { return this.type_spec != null; }
  public default_value_part default_value_part = null;
  public boolean is_default_value_part() { return this.default_value_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARAMETER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARAMETER");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (parameter_name == null) { throw new RuntimeException(); }
    _result.addChild(parameter_name.unparse());


    for (int i = 0; i < parameter_dir_specs.size(); ++i) {
      _result.addChild(parameter_dir_specs.get(i).unparse());
    }


    if (type_spec != null) {
      _result.addChild(type_spec.unparse());
    }


    if (default_value_part != null) {
      _result.addChild(default_value_part.unparse());
    }


    return _result;
  }

}
