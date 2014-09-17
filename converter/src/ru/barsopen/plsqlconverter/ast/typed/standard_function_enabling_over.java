package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_enabling_over implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public String name = null;
  public function_argument function_argument = null;
  public over_clause over_clause = null;
  public boolean is_over_clause() { return this.over_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FUNCTION_ENABLING_OVER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText(name);
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);

    if (function_argument == null) { throw new RuntimeException(); }
    _result.addChild(function_argument.unparse());


    if (over_clause != null) {
      _result.addChild(over_clause.unparse());
    }


    return _result;
  }

}
