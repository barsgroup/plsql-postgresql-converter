package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration_exception_init implements pragma_declaration_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public exception_name exception_name = null;
  public constant constant = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXCEPTION_INIT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXCEPTION_INIT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (exception_name == null) { throw new RuntimeException(); }
    _result.addChild(exception_name.unparse());


    if (constant == null) { throw new RuntimeException(); }
    _result.addChild(constant.unparse());


    return _result;
  }

}
