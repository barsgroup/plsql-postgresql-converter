package ru.barsopen.plsqlconverter.ast.typed;
public class field_specs {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public java.util.List<field_spec> field_specs = new java.util.ArrayList<field_spec>();

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FIELDS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FIELDS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < field_specs.size(); ++i) {
      _result.addChild(field_specs.get(i).unparse());
    }


    return _result;
  }

}
