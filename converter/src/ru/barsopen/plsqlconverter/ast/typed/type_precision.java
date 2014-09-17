package ru.barsopen.plsqlconverter.ast.typed;
public class type_precision {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public constant size1 = null;
  public constant size2 = null;
  public org.antlr.runtime.tree.Tree CHAR_VK = null;
  public boolean is_CHAR_VK() { return this.CHAR_VK != null; }
  public org.antlr.runtime.tree.Tree BYTE_VK = null;
  public boolean is_BYTE_VK() { return this.BYTE_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.PRECISION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PRECISION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (size1 == null) { throw new RuntimeException(); }
    _result.addChild(size1.unparse());


    if (size2 != null) {
      _result.addChild(size2.unparse());
    }


    if (CHAR_VK != null) {
      _result.addChild(CHAR_VK);
    }


    if (BYTE_VK != null) {
      _result.addChild(BYTE_VK);
    }


    return _result;
  }

}
