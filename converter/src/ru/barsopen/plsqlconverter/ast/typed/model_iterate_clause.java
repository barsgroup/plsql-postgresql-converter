package ru.barsopen.plsqlconverter.ast.typed;
public class model_iterate_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public until_part until_part = null;
  public boolean is_until_part() { return this.until_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ITERATE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ITERATE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (until_part != null) {
      _result.addChild(until_part.unparse());
    }


    return _result;
  }

}
