package ru.barsopen.plsqlconverter.ast.typed;
public class cursor_based_for implements cursor_loop_param, _baseNode {
  public int _line = -1;
  public int _col = -1;
  public int _tokenStartIndex = -1;
  public int _tokenStopIndex = -1;
  public _baseNode _parent = null;
  public _baseNode _getParent() { return _parent; }
  public void _setParent(_baseNode value) { _parent = value; }
  public void _setBaseNode(_baseNode value) { this._parent = value; }
  public int _getLine() { return _line; }
  public int _getCol() { return _col; }
  public int _getTokenStartIndex() { return _tokenStartIndex; }
  public int _getTokenStopIndex() { return _tokenStopIndex; }
  public record_name record_name = null;
  public record_name get_record_name() { return this.record_name; }
  public void set_record_name(record_name value) {
    if (this.record_name != null) { this.record_name._setParent(null); }
    this.record_name = value;
    if (this.record_name != null) { this.record_name._setParent(this); }
  }
  public general_element general_element = null;
  public general_element get_general_element() { return this.general_element; }
  public void set_general_element(general_element value) {
    if (this.general_element != null) { this.general_element._setParent(null); }
    this.general_element = value;
    if (this.general_element != null) { this.general_element._setParent(this); }
  }

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
