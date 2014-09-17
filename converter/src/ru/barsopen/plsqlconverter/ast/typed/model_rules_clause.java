package ru.barsopen.plsqlconverter.ast.typed;
public class model_rules_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<model_rules_element> model_rules_elements = new java.util.ArrayList<model_rules_element>();
  public model_rules_part model_rules_part = null;
  public boolean is_model_rules_part() { return this.model_rules_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MODEL_RULES);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_RULES");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_rules_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < model_rules_elements.size(); ++i) {
      _result.addChild(model_rules_elements.get(i).unparse());
    }


    if (model_rules_part != null) {
      _result.addChild(model_rules_part.unparse());
    }


    return _result;
  }

}
