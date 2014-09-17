package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_partition_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public model_column_list model_column_list = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PARTITION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARTITION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_column_list == null) { throw new RuntimeException(); }
    _result.addChild(model_column_list.unparse());


    return _result;
  }

}
