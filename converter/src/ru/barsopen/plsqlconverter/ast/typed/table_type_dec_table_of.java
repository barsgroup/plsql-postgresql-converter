package ru.barsopen.plsqlconverter.ast.typed;
public class table_type_dec_table_of implements table_type_dec_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public type_spec type_spec = null;
  public table_indexed_by_part table_indexed_by_part = null;
  public boolean is_table_indexed_by_part() { return this.table_indexed_by_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_TABLE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_TABLE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (type_spec == null) { throw new RuntimeException(); }
    _result.addChild(type_spec.unparse());


    if (table_indexed_by_part != null) {
      _result.addChild(table_indexed_by_part.unparse());
    }


    return _result;
  }

}
