package ru.barsopen.plsqlconverter.ast.typed;
public class labeled_statement implements stat_or_label {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public label_name label_name = null;
  public statement statement = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LABEL_DECLARE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LABEL_DECLARE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (label_name == null) { throw new RuntimeException(); }
    _result.addChild(label_name.unparse());


    if (statement == null) { throw new RuntimeException(); }
    _result.addChild(statement.unparse());


    return _result;
  }

}
