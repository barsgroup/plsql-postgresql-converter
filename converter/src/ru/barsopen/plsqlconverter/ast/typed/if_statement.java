package ru.barsopen.plsqlconverter.ast.typed;
public class if_statement implements statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public seq_of_statements seq_of_statements = null;
  public java.util.List<elsif_part> elsif_parts = new java.util.ArrayList<elsif_part>();
  public else_part else_part = null;
  public boolean is_else_part() { return this.else_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PLSQL_RESERVED_IF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PLSQL_RESERVED_IF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (seq_of_statements == null) { throw new RuntimeException(); }
    _result.addChild(seq_of_statements.unparse());


    for (int i = 0; i < elsif_parts.size(); ++i) {
      _result.addChild(elsif_parts.get(i).unparse());
    }


    if (else_part != null) {
      _result.addChild(else_part.unparse());
    }


    return _result;
  }

}
