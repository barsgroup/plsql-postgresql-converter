package ru.barsopen.plsqlconverter.ast.typed;
public class function_impl_using implements function_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree AGGREGATE_VK = null;
  public boolean is_AGGREGATE_VK() { return this.AGGREGATE_VK != null; }
  public implementation_type_name implementation_type_name = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.USING_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("USING_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (AGGREGATE_VK != null) {
      _result.addChild(AGGREGATE_VK);
    }


    if (implementation_type_name == null) { throw new RuntimeException(); }
    _result.addChild(implementation_type_name.unparse());


    return _result;
  }

}
