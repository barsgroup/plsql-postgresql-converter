package ru.barsopen.plsqlconverter.ast.typed;
public class table_indexed_by_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public type_spec type_spec = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INDEXED_BY);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INDEXED_BY");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    return _result;
  }

}
