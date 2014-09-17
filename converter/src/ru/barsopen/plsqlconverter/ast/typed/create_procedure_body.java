package ru.barsopen.plsqlconverter.ast.typed;
public class create_procedure_body implements unit_statement, package_obj_body, declare_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_CREATE = null;
  public boolean is_SQL92_RESERVED_CREATE() { return this.SQL92_RESERVED_CREATE != null; }
  public org.antlr.runtime.tree.Tree REPLACE_VK = null;
  public boolean is_REPLACE_VK() { return this.REPLACE_VK != null; }
  public procedure_name procedure_name = null;
  public parameters parameters = null;
  public invoker_rights_clause invoker_rights_clause = null;
  public boolean is_invoker_rights_clause() { return this.invoker_rights_clause != null; }
  public create_procedure_body_impl create_procedure_body_impl = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CREATE_PROCEDURE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CREATE_PROCEDURE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_CREATE != null) {
      _result.addChild(SQL92_RESERVED_CREATE);
    }


    if (REPLACE_VK != null) {
      _result.addChild(REPLACE_VK);
    }


    if (procedure_name == null) { throw new RuntimeException(); }
    _result.addChild(procedure_name.unparse());


    if (parameters == null) { throw new RuntimeException(); }
    _result.addChild(parameters.unparse());


    if (invoker_rights_clause != null) {
      _result.addChild(invoker_rights_clause.unparse());
    }


    if (create_procedure_body_impl == null) { throw new RuntimeException(); }
    _result.addChild(create_procedure_body_impl.unparse());


    return _result;
  }

}
