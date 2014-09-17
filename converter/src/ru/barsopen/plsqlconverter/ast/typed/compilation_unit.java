package ru.barsopen.plsqlconverter.ast.typed;
public class compilation_unit {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<unit_statement> unit_statements = new java.util.ArrayList<unit_statement>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COMPILATION_UNIT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COMPILATION_UNIT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < unit_statements.size(); ++i) {
      _result.addChild(unit_statements.get(i).unparse());
    }


    return _result;
  }

}
