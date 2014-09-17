package ru.barsopen.plsqlconverter.ast.typed;
public class case_statement_searched implements case_statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<case_when_part> case_when_parts = new java.util.ArrayList<case_when_part>();
  public case_else_part case_else_part = null;
  public boolean is_case_else_part() { return this.case_else_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SEARCHED_CASE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SEARCHED_CASE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (case_when_parts.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < case_when_parts.size(); ++i) {
      _result.addChild(case_when_parts.get(i).unparse());
    }


    if (case_else_part != null) {
      _result.addChild(case_else_part.unparse());
    }


    return _result;
  }

}
