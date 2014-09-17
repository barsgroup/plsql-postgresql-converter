package ru.barsopen.plsqlconverter.ast.typed;
public class flashback_query_clause_versions implements flashback_query_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SCN_VK = null;
  public boolean is_SCN_VK() { return this.SCN_VK != null; }
  public org.antlr.runtime.tree.Tree TIMESTAMP_VK = null;
  public boolean is_TIMESTAMP_VK() { return this.TIMESTAMP_VK != null; }
  public expression expression = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.VERSIONS_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("VERSIONS_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SCN_VK != null) {
      _result.addChild(SCN_VK);
    }


    if (TIMESTAMP_VK != null) {
      _result.addChild(TIMESTAMP_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
