package ru.barsopen.plsqlconverter.ast.typed;
public class drop_trigger implements unit_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public trigger_name trigger_name = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.DROP_TRIGGER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DROP_TRIGGER");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (trigger_name == null) { throw new RuntimeException(); }
    _result.addChild(trigger_name.unparse());


    return _result;
  }

}
