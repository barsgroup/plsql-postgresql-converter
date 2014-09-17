package ru.barsopen.plsqlconverter.ast.typed;
public class parallel_enable_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public partition_by_clause partition_by_clause = null;
  public boolean is_partition_by_clause() { return this.partition_by_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARALLEL_ENABLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARALLEL_ENABLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (partition_by_clause != null) {
      _result.addChild(partition_by_clause.unparse());
    }


    return _result;
  }

}
