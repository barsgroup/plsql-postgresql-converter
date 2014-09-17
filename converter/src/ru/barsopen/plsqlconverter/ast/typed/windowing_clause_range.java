package ru.barsopen.plsqlconverter.ast.typed;
public class windowing_clause_range implements windowing_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public windowing_clause_spec windowing_clause_spec = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RANGE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RANGE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (windowing_clause_spec == null) { throw new RuntimeException(); }
    _result.addChild(windowing_clause_spec.unparse());


    return _result;
  }

}
