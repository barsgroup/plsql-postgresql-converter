package ru.barsopen.plsqlconverter.ast.typed;
public class argument {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public parameter_name parameter_name = null;
  public boolean is_parameter_name() { return this.parameter_name != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ARGUMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ARGUMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (parameter_name != null) {
      _result.addChild(parameter_name.unparse());
    }


    return _result;
  }

}
