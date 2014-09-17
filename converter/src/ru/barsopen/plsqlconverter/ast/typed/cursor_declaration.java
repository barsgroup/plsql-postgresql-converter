package ru.barsopen.plsqlconverter.ast.typed;
public class cursor_declaration implements package_obj_spec, package_obj_body, declare_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public cursor_name cursor_name = null;
  public type_spec type_spec = null;
  public boolean is_type_spec() { return this.type_spec != null; }
  public select_statement select_statement = null;
  public boolean is_select_statement() { return this.select_statement != null; }
  public parameters parameters = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CURSOR_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CURSOR_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (cursor_name == null) { throw new RuntimeException(); }
    _result.addChild(cursor_name.unparse());


    if (type_spec != null) {
      _result.addChild(type_spec.unparse());
    }


    if (select_statement != null) {
      _result.addChild(select_statement.unparse());
    }


    if (parameters == null) { throw new RuntimeException(); }
    _result.addChild(parameters.unparse());


    return _result;
  }

}
