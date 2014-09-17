package ru.barsopen.plsqlconverter.ast.typed;
public class into_clause_normal implements into_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<general_element> general_elements = new java.util.ArrayList<general_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_INTO);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_INTO");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < general_elements.size(); ++i) {
      _result.addChild(general_elements.get(i).unparse());
    }


    return _result;
  }

}
