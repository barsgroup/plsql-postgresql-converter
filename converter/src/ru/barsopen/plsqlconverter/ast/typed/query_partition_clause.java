package ru.barsopen.plsqlconverter.ast.typed;
public class query_partition_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public query_partition_clause_impl query_partition_clause_impl = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARTITION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARTITION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (query_partition_clause_impl == null) { throw new RuntimeException(); }
    _result.addChild(query_partition_clause_impl.unparse());


    return _result;
  }

}
