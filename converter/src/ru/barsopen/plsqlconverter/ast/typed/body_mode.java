package ru.barsopen.plsqlconverter.ast.typed;
public class body_mode implements function_impl, create_procedure_body_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public block block = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.BODY_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("BODY_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (block == null) { throw new RuntimeException(); }
    _result.addChild(block.unparse());


    return _result;
  }

}
