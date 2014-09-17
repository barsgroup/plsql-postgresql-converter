package ru.barsopen.plsqlconverter.ast.typed;
public class cursor_based_for implements cursor_loop_param {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public record_name record_name = null;
  public general_element general_element = null;

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.CURSOR_BASED_FOR);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("CURSOR_BASED_FOR");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (record_name == null) { throw new RuntimeException(); }
    _result.addChild(record_name.unparse());


    if (general_element == null) { throw new RuntimeException(); }
    _result.addChild(general_element.unparse());


    return _result;
  }

}
