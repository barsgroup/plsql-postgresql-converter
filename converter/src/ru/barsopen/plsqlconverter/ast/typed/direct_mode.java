package ru.barsopen.plsqlconverter.ast.typed;
public class direct_mode implements table_expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public tableview_name tableview_name = null;
  public sample_clause sample_clause = null;
  public boolean is_sample_clause() { return this.sample_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.DIRECT_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("DIRECT_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (tableview_name == null) { throw new RuntimeException(); }
    _result.addChild(tableview_name.unparse());


    if (sample_clause != null) {
      _result.addChild(sample_clause.unparse());
    }


    return _result;
  }

}
