package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration_inline implements pragma_declaration_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public id id = null;
  public expression expression = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INLINE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INLINE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (id == null) { throw new RuntimeException(); }
    _result.addChild(id.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
