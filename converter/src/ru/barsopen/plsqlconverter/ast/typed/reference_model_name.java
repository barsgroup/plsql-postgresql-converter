package ru.barsopen.plsqlconverter.ast.typed;
public class reference_model_name implements _baseNode {
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
  public char_set_name char_set_name = null;
  public char_set_name get_char_set_name() { return this.char_set_name; }
  public void set_char_set_name(char_set_name value) {
    if (this.char_set_name != null) { this.char_set_name._setParent(null); }
    this.char_set_name = value;
    if (this.char_set_name != null) { this.char_set_name._setParent(this); }
  }
  public boolean is_char_set_name() { return this.char_set_name != null; }
  public id id = null;
  public id get_id() { return this.id; }
  public void set_id(id value) {
    if (this.id != null) { this.id._setParent(null); }
    this.id = value;
    if (this.id != null) { this.id._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.REFERENCE_MODEL_NAME);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("REFERENCE_MODEL_NAME");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (char_set_name != null) {
      _result.addChild(char_set_name.unparse());
    }


    if (id == null) { throw new RuntimeException(); }
    _result.addChild(id.unparse());


    return _result;
  }

}
