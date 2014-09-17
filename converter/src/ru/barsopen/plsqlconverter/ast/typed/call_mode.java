package ru.barsopen.plsqlconverter.ast.typed;
public class call_mode implements function_impl, create_procedure_body_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public call_spec call_spec = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CALL_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CALL_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (call_spec == null) { throw new RuntimeException(); }
    _result.addChild(call_spec.unparse());


    return _result;
  }

}
