package ru.barsopen.plsqlconverter.ast.typed;
public class xmlroot_param_standalone_part {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree YES_VK = null;
  public boolean is_YES_VK() { return this.YES_VK != null; }
  public org.antlr.runtime.tree.Tree NO_VK = null;
  public boolean is_NO_VK() { return this.NO_VK != null; }
  public org.antlr.runtime.tree.Tree VALUE_VK = null;
  public boolean is_VALUE_VK() { return this.VALUE_VK != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.STANDALONE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("STANDALONE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (YES_VK != null) {
      _result.addChild(YES_VK);
    }


    if (NO_VK != null) {
      _result.addChild(NO_VK);
    }


    if (VALUE_VK != null) {
      _result.addChild(VALUE_VK);
    }


    return _result;
  }

}
