package ru.barsopen.plsqlconverter.ast.typed;
public class type_spec_custom implements type_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public type_name type_name = null;
  public org.antlr.runtime.tree.Tree REF_VK = null;
  public boolean is_REF_VK() { return this.REF_VK != null; }
  public percent_type_or_rowtype percent_type_or_rowtype = null;
  public boolean is_percent_type_or_rowtype() { return this.percent_type_or_rowtype != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CUSTOM_TYPE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CUSTOM_TYPE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (type_name == null) { throw new RuntimeException(); }
    _result.addChild(type_name.unparse());


    if (REF_VK != null) {
      _result.addChild(REF_VK);
    }


    if (percent_type_or_rowtype != null) {
      _result.addChild(percent_type_or_rowtype.unparse());
    }


    return _result;
  }

}
