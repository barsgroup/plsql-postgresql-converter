package ru.barsopen.plsqlconverter.ast.typed;
public class column_name {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public char_set_name char_set_name = null;
  public boolean is_char_set_name() { return this.char_set_name != null; }
  public java.util.List<id> ids = new java.util.ArrayList<id>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COLUMN_NAME);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COLUMN_NAME");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (char_set_name != null) {
      _result.addChild(char_set_name.unparse());
    }


    if (ids.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < ids.size(); ++i) {
      _result.addChild(ids.get(i).unparse());
    }


    return _result;
  }

}
