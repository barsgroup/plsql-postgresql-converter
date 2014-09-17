package ru.barsopen.plsqlconverter.ast.typed;
public class open_for_statement implements cursor_manipulation_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public variable_name variable_name = null;
  public expression_or_select_statement expression_or_select_statement = null;
  public using_clause using_clause = null;
  public boolean is_using_clause() { return this.using_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.OPEN_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("OPEN_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (variable_name == null) { throw new RuntimeException(); }
    _result.addChild(variable_name.unparse());


    if (expression_or_select_statement == null) { throw new RuntimeException(); }
    _result.addChild(expression_or_select_statement.unparse());


    if (using_clause != null) {
      _result.addChild(using_clause.unparse());
    }


    return _result;
  }

}
