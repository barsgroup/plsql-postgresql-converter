package ru.barsopen.plsqlconverter.ast.typed;
public class selected_tableview {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public alias alias = null;
  public boolean is_alias() { return this.alias != null; }
  public selected_tableview_src selected_tableview_src = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECTED_TABLEVIEW);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECTED_TABLEVIEW");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    if (selected_tableview_src == null) { throw new RuntimeException(); }
    _result.addChild(selected_tableview_src.unparse());


    return _result;
  }

}
