package ru.barsopen.plsqlconverter.ast.typed;
public class conditional_insert_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public org.antlr.runtime.tree.Tree FIRST_VK = null;
  public boolean is_FIRST_VK() { return this.FIRST_VK != null; }
  public java.util.List<conditional_insert_when_part> conditional_insert_when_parts = new java.util.ArrayList<conditional_insert_when_part>();
  public conditional_insert_else_part conditional_insert_else_part = null;
  public boolean is_conditional_insert_else_part() { return this.conditional_insert_else_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CONDITIONAL_INSERT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CONDITIONAL_INSERT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    if (FIRST_VK != null) {
      _result.addChild(FIRST_VK);
    }


    if (conditional_insert_when_parts.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < conditional_insert_when_parts.size(); ++i) {
      _result.addChild(conditional_insert_when_parts.get(i).unparse());
    }


    if (conditional_insert_else_part != null) {
      _result.addChild(conditional_insert_else_part.unparse());
    }


    return _result;
  }

}
