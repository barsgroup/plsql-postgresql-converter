package ru.barsopen.plsqlconverter.ast.typed;
public class hosted_variable_name implements assignment_target, expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree BINDVAR = null;
  public boolean is_BINDVAR() { return this.BINDVAR != null; }
  public org.antlr.runtime.tree.Tree UNSIGNED_INTEGER = null;
  public boolean is_UNSIGNED_INTEGER() { return this.UNSIGNED_INTEGER != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.HOSTED_VARIABLE_NAME);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("HOSTED_VARIABLE_NAME");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (BINDVAR != null) {
      _result.addChild(BINDVAR);
    }


    if (UNSIGNED_INTEGER != null) {
      _result.addChild(UNSIGNED_INTEGER);
    }


    return _result;
  }

}
