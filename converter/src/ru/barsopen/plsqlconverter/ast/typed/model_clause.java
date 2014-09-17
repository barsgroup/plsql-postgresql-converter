package ru.barsopen.plsqlconverter.ast.typed;
public class model_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public main_model main_model = null;
  public java.util.List<cell_reference_options> cell_reference_optionss = new java.util.ArrayList<cell_reference_options>();
  public return_rows_clause return_rows_clause = null;
  public boolean is_return_rows_clause() { return this.return_rows_clause != null; }
  public java.util.List<reference_model> reference_models = new java.util.ArrayList<reference_model>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PLSQL_NON_RESERVED_MODEL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_NON_RESERVED_MODEL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (main_model == null) { throw new RuntimeException(); }
    _result.addChild(main_model.unparse());


    for (int i = 0; i < cell_reference_optionss.size(); ++i) {
      _result.addChild(cell_reference_optionss.get(i).unparse());
    }


    if (return_rows_clause != null) {
      _result.addChild(return_rows_clause.unparse());
    }


    for (int i = 0; i < reference_models.size(); ++i) {
      _result.addChild(reference_models.get(i).unparse());
    }


    return _result;
  }

}
