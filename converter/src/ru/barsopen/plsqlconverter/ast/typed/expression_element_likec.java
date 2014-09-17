package ru.barsopen.plsqlconverter.ast.typed;
public class expression_element_likec implements expression_element {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public expression_element text = null;
  public expression_element pattern = null;
  public expression_element escape = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LIKEC_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LIKEC_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (text == null) { throw new RuntimeException(); }
    _result.addChild(text.unparse());


    if (pattern == null) { throw new RuntimeException(); }
    _result.addChild(pattern.unparse());


    if (escape != null) {
      _result.addChild(escape.unparse());
    }


    return _result;
  }

}
