package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration implements package_obj_spec, declare_spec {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public pragma_declaration_impl pragma_declaration_impl = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PRAGMA_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PRAGMA_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (pragma_declaration_impl == null) { throw new RuntimeException(); }
    _result.addChild(pragma_declaration_impl.unparse());


    return _result;
  }

}
