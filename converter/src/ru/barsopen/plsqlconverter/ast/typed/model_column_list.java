package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_list {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<model_column> model_columns = new java.util.ArrayList<model_column>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MODEL_COLUMNS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_COLUMNS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_columns.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < model_columns.size(); ++i) {
      _result.addChild(model_columns.get(i).unparse());
    }


    return _result;
  }

}
