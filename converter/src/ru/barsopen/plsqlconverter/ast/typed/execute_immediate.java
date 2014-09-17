package ru.barsopen.plsqlconverter.ast.typed;
public class execute_immediate implements sql_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public into_clause into_clause = null;
  public boolean is_into_clause() { return this.into_clause != null; }
  public using_clause using_clause = null;
  public boolean is_using_clause() { return this.using_clause != null; }
  public dynamic_returning_clause dynamic_returning_clause = null;
  public boolean is_dynamic_returning_clause() { return this.dynamic_returning_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXECUTE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXECUTE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (into_clause != null) {
      _result.addChild(into_clause.unparse());
    }


    if (using_clause != null) {
      _result.addChild(using_clause.unparse());
    }


    if (dynamic_returning_clause != null) {
      _result.addChild(dynamic_returning_clause.unparse());
    }


    return _result;
  }

}
