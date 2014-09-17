package ru.barsopen.plsqlconverter.ast.typed;
public class interval_type_spec_second_second implements interval_type_spec_second {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public boolean is_expression() { return this.expression != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SECOND_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SECOND_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    return _result;
  }

}
