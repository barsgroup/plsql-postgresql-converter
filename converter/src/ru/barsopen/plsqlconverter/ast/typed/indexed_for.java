package ru.barsopen.plsqlconverter.ast.typed;
public class indexed_for implements cursor_loop_param {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public index_name index_name = null;
  public org.antlr.runtime.tree.Tree REVERSE_VK = null;
  public boolean is_REVERSE_VK() { return this.REVERSE_VK != null; }
  public indexed_for_bounds indexed_for_bounds = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.INDEXED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("INDEXED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (index_name == null) { throw new RuntimeException(); }
    _result.addChild(index_name.unparse());


    if (REVERSE_VK != null) {
      _result.addChild(REVERSE_VK);
    }


    if (indexed_for_bounds == null) { throw new RuntimeException(); }
    _result.addChild(indexed_for_bounds.unparse());


    return _result;
  }

}
