package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_gte implements expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element lhs = null;
  public expression_element rhs = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.GREATER_THAN_OR_EQUALS_OP);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("GREATER_THAN_OR_EQUALS_OP");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (lhs == null) { throw new RuntimeException(); }
    _result.addChild(lhs.unparse());


    if (rhs == null) { throw new RuntimeException(); }
    _result.addChild(rhs.unparse());


    return _result;
  }

}
