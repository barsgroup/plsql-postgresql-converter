package ru.barsopen.plsqlconverter.ast.typed;
public class selected_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public alias alias = null;
  public boolean is_alias() { return this.alias != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SELECT_ITEM);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SELECT_ITEM");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    return _result;
  }

}
