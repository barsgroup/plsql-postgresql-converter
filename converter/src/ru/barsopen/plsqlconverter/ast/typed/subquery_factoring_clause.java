package ru.barsopen.plsqlconverter.ast.typed;
public class subquery_factoring_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree RECURSIVE_VK = null;
  public boolean is_RECURSIVE_VK() { return this.RECURSIVE_VK != null; }
  public java.util.List<factoring_element> factoring_elements = new java.util.ArrayList<factoring_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_WITH);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_WITH");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (RECURSIVE_VK != null) {
      _result.addChild(RECURSIVE_VK);
    }


    if (factoring_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < factoring_elements.size(); ++i) {
      _result.addChild(factoring_elements.get(i).unparse());
    }


    return _result;
  }

}
