package ru.barsopen.plsqlconverter.ast.typed;
public class pragma_declaration_restrict_references implements pragma_declaration_impl {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_DEFAULT = null;
  public boolean is_SQL92_RESERVED_DEFAULT() { return this.SQL92_RESERVED_DEFAULT != null; }
  public java.util.List<id> ids = new java.util.ArrayList<id>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.RESTRICT_REFERENCES_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RESTRICT_REFERENCES_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (SQL92_RESERVED_DEFAULT != null) {
      _result.addChild(SQL92_RESERVED_DEFAULT);
    }


    if (ids.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < ids.size(); ++i) {
      _result.addChild(ids.get(i).unparse());
    }


    return _result;
  }

}
