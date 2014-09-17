package ru.barsopen.plsqlconverter.ast.typed;
public class using_element_element implements using_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_IN = null;
  public boolean is_SQL92_RESERVED_IN() { return this.SQL92_RESERVED_IN != null; }
  public org.antlr.runtime.tree.Tree OUT_VK = null;
  public boolean is_OUT_VK() { return this.OUT_VK != null; }
  public expression expression = null;
  public alias alias = null;
  public boolean is_alias() { return this.alias != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_IN != null) {
      _result.addChild(SQL92_RESERVED_IN);
    }


    if (OUT_VK != null) {
      _result.addChild(OUT_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (alias != null) {
      _result.addChild(alias.unparse());
    }


    return _result;
  }

}
