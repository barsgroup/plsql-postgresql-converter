package ru.barsopen.plsqlconverter.ast.typed;
public class query_name {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public char_set_name char_set_name = null;
  public boolean is_char_set_name() { return this.char_set_name != null; }
  public id id = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.QUERY_NAME);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("QUERY_NAME");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (char_set_name != null) {
      _result.addChild(char_set_name.unparse());
    }


    if (id == null) { throw new RuntimeException(); }
    _result.addChild(id.unparse());


    return _result;
  }

}
