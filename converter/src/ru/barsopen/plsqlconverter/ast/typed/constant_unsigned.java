package ru.barsopen.plsqlconverter.ast.typed;
public class constant_unsigned implements constant {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public String value = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.UNSIGNED_INTEGER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText(value);
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);

    return _result;
  }

}
