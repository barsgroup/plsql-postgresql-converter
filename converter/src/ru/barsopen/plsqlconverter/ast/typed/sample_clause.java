package ru.barsopen.plsqlconverter.ast.typed;
public class sample_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree BLOCK_VK = null;
  public boolean is_BLOCK_VK() { return this.BLOCK_VK != null; }
  public expression expression = null;
  public seed_part seed_part = null;
  public boolean is_seed_part() { return this.seed_part != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.SAMPLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("SAMPLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (BLOCK_VK != null) {
      _result.addChild(BLOCK_VK);
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (seed_part != null) {
      _result.addChild(seed_part.unparse());
    }


    return _result;
  }

}
