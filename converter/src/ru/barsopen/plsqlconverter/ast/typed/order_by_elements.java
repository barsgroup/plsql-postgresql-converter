package ru.barsopen.plsqlconverter.ast.typed;
public class order_by_elements {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression expression = null;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ASC = null;
  public boolean is_SQL92_RESERVED_ASC() { return this.SQL92_RESERVED_ASC != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DESC = null;
  public boolean is_SQL92_RESERVED_DESC() { return this.SQL92_RESERVED_DESC != null; }
  public org.antlr.runtime.tree.Tree NULLS_VK = null;
  public boolean is_NULLS_VK() { return this.NULLS_VK != null; }
  public org.antlr.runtime.tree.Tree FIRST_VK = null;
  public boolean is_FIRST_VK() { return this.FIRST_VK != null; }
  public org.antlr.runtime.tree.Tree LAST_VK = null;
  public boolean is_LAST_VK() { return this.LAST_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.ORDER_BY_ELEMENT);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ORDER_BY_ELEMENT");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (SQL92_RESERVED_ASC != null) {
      _result.addChild(SQL92_RESERVED_ASC);
    }


    if (SQL92_RESERVED_DESC != null) {
      _result.addChild(SQL92_RESERVED_DESC);
    }


    if (NULLS_VK != null) {
      _result.addChild(NULLS_VK);
    }


    if (FIRST_VK != null) {
      _result.addChild(FIRST_VK);
    }


    if (LAST_VK != null) {
      _result.addChild(LAST_VK);
    }


    return _result;
  }

}
