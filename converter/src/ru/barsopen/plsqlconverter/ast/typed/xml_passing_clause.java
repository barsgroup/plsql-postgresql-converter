package ru.barsopen.plsqlconverter.ast.typed;
public class xml_passing_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree VALUE_VK = null;
  public boolean is_VALUE_VK() { return this.VALUE_VK != null; }
  public xml_passing_clause_expr xml_passing_clause_expr = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PASSING_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PASSING_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (VALUE_VK != null) {
      _result.addChild(VALUE_VK);
    }


    if (xml_passing_clause_expr == null) { throw new RuntimeException(); }
    _result.addChild(xml_passing_clause_expr.unparse());


    return _result;
  }

}
