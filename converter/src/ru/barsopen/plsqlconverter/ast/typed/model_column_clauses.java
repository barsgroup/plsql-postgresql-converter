package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_clauses {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public model_column_clauses_dimension model_column_clauses_dimension = null;
  public model_column_clauses_measures model_column_clauses_measures = null;
  public model_column_partition_part model_column_partition_part = null;
  public boolean is_model_column_partition_part() { return this.model_column_partition_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MODEL_COLUMN);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_COLUMN");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_column_clauses_dimension == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses_dimension.unparse());


    if (model_column_clauses_measures == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses_measures.unparse());


    if (model_column_partition_part != null) {
      _result.addChild(model_column_partition_part.unparse());
    }


    return _result;
  }

}
