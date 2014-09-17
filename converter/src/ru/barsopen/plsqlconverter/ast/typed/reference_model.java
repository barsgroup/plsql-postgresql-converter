package ru.barsopen.plsqlconverter.ast.typed;
public class reference_model {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public reference_model_name reference_model_name = null;
  public subquery subquery = null;
  public model_column_clauses model_column_clauses = null;
  public java.util.List<cell_reference_options> cell_reference_optionss = new java.util.ArrayList<cell_reference_options>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.REFERENCE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("REFERENCE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (reference_model_name == null) { throw new RuntimeException(); }
    _result.addChild(reference_model_name.unparse());


    if (subquery == null) { throw new RuntimeException(); }
    _result.addChild(subquery.unparse());


    if (model_column_clauses == null) { throw new RuntimeException(); }
    _result.addChild(model_column_clauses.unparse());


    for (int i = 0; i < cell_reference_optionss.size(); ++i) {
      _result.addChild(cell_reference_optionss.get(i).unparse());
    }


    return _result;
  }

}
