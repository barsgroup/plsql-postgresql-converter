package ru.barsopen.plsqlconverter.ast.typed;
public class create_package_spec implements create_package {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree REPLACE_VK = null;
  public boolean is_REPLACE_VK() { return this.REPLACE_VK != null; }
  public package_name package_name = null;
  public invoker_rights_clause invoker_rights_clause = null;
  public boolean is_invoker_rights_clause() { return this.invoker_rights_clause != null; }
  public java.util.List<package_obj_spec> package_obj_specs = new java.util.ArrayList<package_obj_spec>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CREATE_PACKAGE_SPEC);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CREATE_PACKAGE_SPEC");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (REPLACE_VK != null) {
      _result.addChild(REPLACE_VK);
    }


    if (package_name == null) { throw new RuntimeException(); }
    _result.addChild(package_name.unparse());


    if (invoker_rights_clause != null) {
      _result.addChild(invoker_rights_clause.unparse());
    }


    for (int i = 0; i < package_obj_specs.size(); ++i) {
      _result.addChild(package_obj_specs.get(i).unparse());
    }


    return _result;
  }

}
