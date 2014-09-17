package ru.barsopen.plsqlconverter.ast.typed;
public class savepoint_statement implements transaction_control_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public savepoint_name savepoint_name = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SAVEPOINT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SAVEPOINT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (savepoint_name == null) { throw new RuntimeException(); }
    _result.addChild(savepoint_name.unparse());


    return _result;
  }

}
