package ru.barsopen.plsqlconverter.ast.typed;
public class projection_list implements query_block_projection {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<selected_element> selected_elements = new java.util.ArrayList<selected_element>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECT_LIST);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_LIST");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (selected_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < selected_elements.size(); ++i) {
      _result.addChild(selected_elements.get(i).unparse());
    }


    return _result;
  }

}
