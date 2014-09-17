package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_count implements standard_function {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT = null;
  public boolean is_SQL92_RESERVED_DISTINCT() { return this.SQL92_RESERVED_DISTINCT != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE = null;
  public boolean is_SQL92_RESERVED_UNIQUE() { return this.SQL92_RESERVED_UNIQUE != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }
  public org.antlr.runtime.tree.Tree ASTERISK = null;
  public boolean is_ASTERISK() { return this.ASTERISK != null; }
  public expression expression = null;
  public boolean is_expression() { return this.expression != null; }
  public over_clause over_clause = null;
  public boolean is_over_clause() { return this.over_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COUNT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COUNT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_DISTINCT != null) {
      _result.addChild(SQL92_RESERVED_DISTINCT);
    }


    if (SQL92_RESERVED_UNIQUE != null) {
      _result.addChild(SQL92_RESERVED_UNIQUE);
    }


    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    if (ASTERISK != null) {
      _result.addChild(ASTERISK);
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    if (over_clause != null) {
      _result.addChild(over_clause.unparse());
    }


    return _result;
  }

}
