package ru.barsopen.plsqlconverter.ast.typed;
public class return_rows_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree UPDATED_VK = null;
  public boolean is_UPDATED_VK() { return this.UPDATED_VK != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RETURN_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RETURN_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (UPDATED_VK != null) {
      _result.addChild(UPDATED_VK);
    }


    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    return _result;
  }

}
