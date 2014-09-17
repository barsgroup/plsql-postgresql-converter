package ru.barsopen.plsqlconverter.ast.typed;
public class record_var_dec implements record_declaration {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public record_name record_name = null;
  public type_name type_name = null;
  public percent_type_or_rowtype percent_type_or_rowtype = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RECORD_VAR_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RECORD_VAR_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (record_name == null) { throw new RuntimeException(); }
    _result.addChild(record_name.unparse());


    if (type_name == null) { throw new RuntimeException(); }
    _result.addChild(type_name.unparse());


    if (percent_type_or_rowtype == null) { throw new RuntimeException(); }
    _result.addChild(percent_type_or_rowtype.unparse());


    return _result;
  }

}
