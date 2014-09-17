package ru.barsopen.plsqlconverter.ast.typed;
public class seq_of_statements implements expression_or_seq_of_statements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<stat_or_label> stat_or_labels = new java.util.ArrayList<stat_or_label>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.STATEMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("STATEMENTS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (stat_or_labels.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < stat_or_labels.size(); ++i) {
      _result.addChild(stat_or_labels.get(i).unparse());
    }


    return _result;
  }

}
