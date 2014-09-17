package ru.barsopen.plsqlconverter.ast.typed;
public class result_cache_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public relies_on_part relies_on_part = null;
  public boolean is_relies_on_part() { return this.relies_on_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RESULT_CACHE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RESULT_CACHE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (relies_on_part != null) {
      _result.addChild(relies_on_part.unparse());
    }


    return _result;
  }

}
