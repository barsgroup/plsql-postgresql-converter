package ru.barsopen.plsqlconverter.ast.typed;
public class alter_procedure implements unit_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public procedure_name procedure_name = null;
  public org.antlr.runtime.tree.Tree DEBUG_VK = null;
  public boolean is_DEBUG_VK() { return this.DEBUG_VK != null; }
  public org.antlr.runtime.tree.Tree REUSE_VK = null;
  public boolean is_REUSE_VK() { return this.REUSE_VK != null; }
  public java.util.List<compiler_parameters_clause> compiler_parameters_clauses = new java.util.ArrayList<compiler_parameters_clause>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ALTER_PROCEDURE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ALTER_PROCEDURE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (procedure_name == null) { throw new RuntimeException(); }
    _result.addChild(procedure_name.unparse());


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
