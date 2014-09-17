package ru.barsopen.plsqlconverter.ast.typed;
public class exit_statement implements statement {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public label_name label_name = null;
  public boolean is_label_name() { return this.label_name != null; }
  public general_when general_when = null;
  public boolean is_general_when() { return this.general_when != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.EXIT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("EXIT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (label_name != null) {
      _result.addChild(label_name.unparse());
    }


    if (general_when != null) {
      _result.addChild(general_when.unparse());
    }


    return _result;
  }

}
