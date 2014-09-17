package ru.barsopen.plsqlconverter.ast.typed;
public class streaming_clause {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<org.antlr.runtime.tree.Tree> contents = new java.util.ArrayList<org.antlr.runtime.tree.Tree>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.STREAMING_CLAUSE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("STREAMING_CLAUSE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < contents.size(); ++i) {
      _result.addChild(contents.get(i));
    }


    return _result;
  }

}
