package ru.barsopen.plsqlconverter.ast.typed;
public class create_package_body implements create_package {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree REPLACE_VK = null;
  public boolean is_REPLACE_VK() { return this.REPLACE_VK != null; }
  public package_name package_name = null;
  public java.util.List<package_obj_body> package_obj_bodys = new java.util.ArrayList<package_obj_body>();
  public seq_of_statements seq_of_statements = null;
  public boolean is_seq_of_statements() { return this.seq_of_statements != null; }
  public exception_clause exception_clause = null;
  public boolean is_exception_clause() { return this.exception_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CREATE_PACKAGE_BODY);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CREATE_PACKAGE_BODY");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (REPLACE_VK != null) {
      _result.addChild(REPLACE_VK);
    }


    if (package_name == null) { throw new RuntimeException(); }
    _result.addChild(package_name.unparse());


    for (int i = 0; i < package_obj_bodys.size(); ++i) {
      _result.addChild(package_obj_bodys.get(i).unparse());
    }


    if (seq_of_statements != null) {
      _result.addChild(seq_of_statements.unparse());
    }


    if (exception_clause != null) {
      _result.addChild(exception_clause.unparse());
    }


    return _result;
  }

}
