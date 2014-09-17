package ru.barsopen.plsqlconverter.ast.typed;
public class join_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public query_partition_clause qpc1 = null;
  public org.antlr.runtime.tree.Tree CROSS_VK = null;
  public boolean is_CROSS_VK() { return this.CROSS_VK != null; }
  public org.antlr.runtime.tree.Tree NATURAL_VK = null;
  public boolean is_NATURAL_VK() { return this.NATURAL_VK != null; }
  public org.antlr.runtime.tree.Tree INNER_VK = null;
  public boolean is_INNER_VK() { return this.INNER_VK != null; }
  public org.antlr.runtime.tree.Tree FULL_VK = null;
  public boolean is_FULL_VK() { return this.FULL_VK != null; }
  public org.antlr.runtime.tree.Tree LEFT_VK = null;
  public boolean is_LEFT_VK() { return this.LEFT_VK != null; }
  public org.antlr.runtime.tree.Tree RIGHT_VK = null;
  public boolean is_RIGHT_VK() { return this.RIGHT_VK != null; }
  public table_ref_aux table_ref_aux = null;
  public query_partition_clause qpc2 = null;
  public join_on_part join_on_part = null;
  public boolean is_join_on_part() { return this.join_on_part != null; }
  public join_using_part join_using_part = null;
  public boolean is_join_using_part() { return this.join_using_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.JOIN_DEF);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("JOIN_DEF");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (qpc1 != null) {
      _result.addChild(qpc1.unparse());
    }


    if (CROSS_VK != null) {
      _result.addChild(CROSS_VK);
    }


    if (NATURAL_VK != null) {
      _result.addChild(NATURAL_VK);
    }


    if (INNER_VK != null) {
      _result.addChild(INNER_VK);
    }


    if (FULL_VK != null) {
      _result.addChild(FULL_VK);
    }


    if (LEFT_VK != null) {
      _result.addChild(LEFT_VK);
    }


    if (RIGHT_VK != null) {
      _result.addChild(RIGHT_VK);
    }


    if (table_ref_aux == null) { throw new RuntimeException(); }
    _result.addChild(table_ref_aux.unparse());


    if (qpc2 != null) {
      _result.addChild(qpc2.unparse());
    }


    if (join_on_part != null) {
      _result.addChild(join_on_part.unparse());
    }


    if (join_using_part != null) {
      _result.addChild(join_using_part.unparse());
    }


    return _result;
  }

}
