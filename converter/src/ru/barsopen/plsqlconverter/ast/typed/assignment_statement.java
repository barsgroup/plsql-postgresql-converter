package ru.barsopen.plsqlconverter.ast.typed;
public class assignment_statement implements statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public assignment_target assignment_target = null;
  public expression expression = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ASSIGN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ASSIGN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (assignment_target == null) { throw new RuntimeException(); }
    _result.addChild(assignment_target.unparse());


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    return _result;
  }

}
