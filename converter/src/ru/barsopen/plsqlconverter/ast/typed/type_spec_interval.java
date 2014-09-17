package ru.barsopen.plsqlconverter.ast.typed;
public class type_spec_interval implements type_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public interval_type_spec_first interval_type_spec_first = null;
  public interval_type_spec_second interval_type_spec_second = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INTERVAL_DATATYPE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INTERVAL_DATATYPE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (interval_type_spec_first == null) { throw new RuntimeException(); }
    _result.addChild(interval_type_spec_first.unparse());


    if (interval_type_spec_second == null) { throw new RuntimeException(); }
    _result.addChild(interval_type_spec_second.unparse());


    return _result;
  }

}
