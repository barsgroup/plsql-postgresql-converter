package ru.barsopen.plsqlconverter.ast.typed;
public class main_model {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public main_model_name main_model_name = null;
  public boolean is_main_model_name() { return this.main_model_name != null; }
  public model_column_clauses model_column_clauses = null;
  public model_rules_clause model_rules_clause = null;
  public java.util.List<cell_reference_options> cell_reference_optionss = new java.util.ArrayList<cell_reference_options>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MAIN_MODEL);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MAIN_MODEL");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (main_model_name != null) {
      _result.addChild(main_model_name.unparse());
    }


    if (model_column_clauses == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses.unparse());


    if (model_rules_clause == null) { throw new RuntimeException(); }
    _result.addChild(model_rules_clause.unparse());


    for (int i = 0; i < cell_reference_optionss.size(); ++i) {
      _result.addChild(cell_reference_optionss.get(i).unparse());
    }


    return _result;
  }

}
