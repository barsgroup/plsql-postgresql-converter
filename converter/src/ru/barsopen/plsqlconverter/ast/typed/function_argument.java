package ru.barsopen.plsqlconverter.ast.typed;
public class function_argument implements general_element_item {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<argument> arguments = new java.util.ArrayList<argument>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ARGUMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ARGUMENTS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < arguments.size(); ++i) {
      _result.addChild(arguments.get(i).unparse());
    }


    return _result;
  }

}
