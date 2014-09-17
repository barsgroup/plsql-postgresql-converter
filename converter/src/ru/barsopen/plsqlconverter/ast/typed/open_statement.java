package ru.barsopen.plsqlconverter.ast.typed;
public class open_statement implements cursor_manipulation_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public cursor_name cursor_name = null;
  public expression_list expression_list = null;
  public boolean is_expression_list() { return this.expression_list != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.OPEN_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("OPEN_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (cursor_name == null) { throw new RuntimeException(); }
    _result.addChild(cursor_name.unparse());


    if (expression_list != null) {
      _result.addChild(expression_list.unparse());
    }


    return _result;
  }

}
