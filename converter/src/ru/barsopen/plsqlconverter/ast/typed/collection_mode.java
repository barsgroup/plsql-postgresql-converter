package ru.barsopen.plsqlconverter.ast.typed;
public class collection_mode implements table_expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public table_collection_expression table_collection_expression = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COLLECTION_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COLLECTION_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (table_collection_expression == null) { throw new RuntimeException(); }
    _result.addChild(table_collection_expression.unparse());


    return _result;
  }

}
