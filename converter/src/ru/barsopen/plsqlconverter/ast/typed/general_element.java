package ru.barsopen.plsqlconverter.ast.typed;
public class general_element implements statement, assignment_target, table_expression_element, expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<general_element_item> general_element_items = new java.util.ArrayList<general_element_item>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CASCATED_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CASCATED_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (general_element_items.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < general_element_items.size(); ++i) {
      _result.addChild(general_element_items.get(i).unparse());
    }


    return _result;
  }

}
