package ru.barsopen.plsqlconverter.ast.typed;
public class group_by_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<group_by_element> group_by_elements = new java.util.ArrayList<group_by_element>();
  public having_clause having_clause = null;
  public boolean is_having_clause() { return this.having_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SQL92_RESERVED_GROUP);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SQL92_RESERVED_GROUP");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (group_by_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < group_by_elements.size(); ++i) {
      _result.addChild(group_by_elements.get(i).unparse());
    }


    if (having_clause != null) {
      _result.addChild(having_clause.unparse());
    }


    return _result;
  }

}
