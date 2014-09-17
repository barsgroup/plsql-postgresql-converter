package ru.barsopen.plsqlconverter.ast.typed;
public class native_datatype_spec implements type_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree name = null;
  public type_precision type_precision = null;
  public boolean is_type_precision() { return this.type_precision != null; }
  public org.antlr.runtime.tree.Tree TIME_VK = null;
  public boolean is_TIME_VK() { return this.TIME_VK != null; }
  public org.antlr.runtime.tree.Tree LOCAL_VK = null;
  public boolean is_LOCAL_VK() { return this.LOCAL_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.NATIVE_DATATYPE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("NATIVE_DATATYPE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (name == null) { throw new RuntimeException(); }
    _result.addChild(name);


    if (type_precision != null) {
      _result.addChild(type_precision.unparse());
    }


    if (TIME_VK != null) {
      _result.addChild(TIME_VK);
    }


    if (LOCAL_VK != null) {
      _result.addChild(LOCAL_VK);
    }


    return _result;
  }

}
