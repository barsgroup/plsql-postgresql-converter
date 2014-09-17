package ru.barsopen.plsqlconverter.ast.typed;
public class block implements statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<declare_spec> declare_specs = new java.util.ArrayList<declare_spec>();
  public body body = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.BLOCK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("BLOCK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < declare_specs.size(); ++i) {
      _result.addChild(declare_specs.get(i).unparse());
    }


    if (body == null) { throw new RuntimeException(); }
    _result.addChild(body.unparse());


    return _result;
  }

}
