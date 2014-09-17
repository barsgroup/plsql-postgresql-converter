package ru.barsopen.plsqlconverter.ast.typed;
public class for_update_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public for_update_of_part for_update_of_part = null;
  public boolean is_for_update_of_part() { return this.for_update_of_part != null; }
  public for_update_options for_update_options = null;
  public boolean is_for_update_options() { return this.for_update_options != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (for_update_of_part != null) {
      _result.addChild(for_update_of_part.unparse());
    }


    if (for_update_options != null) {
      _result.addChild(for_update_options.unparse());
    }


    return _result;
  }

}
