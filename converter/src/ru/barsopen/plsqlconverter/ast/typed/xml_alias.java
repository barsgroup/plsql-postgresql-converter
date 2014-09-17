package ru.barsopen.plsqlconverter.ast.typed;
public class xml_alias {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public id_or_evalname id_or_evalname = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XML_ALIAS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XML_ALIAS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (id_or_evalname == null) { throw new RuntimeException(); }
    _result.addChild(id_or_evalname.unparse());


    return _result;
  }

}
