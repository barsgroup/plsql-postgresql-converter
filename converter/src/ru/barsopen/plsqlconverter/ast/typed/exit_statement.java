package ru.barsopen.plsqlconverter.ast.typed;
public class exit_statement implements statement, _baseNode {
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
  public label_name label_name = null;
  public label_name get_label_name() { return this.label_name; }
  public void set_label_name(label_name value) {
    if (this.label_name != null) { this.label_name._setParent(null); }
    this.label_name = value;
    if (this.label_name != null) { this.label_name._setParent(this); }
  }
  public boolean is_label_name() { return this.label_name != null; }
  public general_when general_when = null;
  public general_when get_general_when() { return this.general_when; }
  public void set_general_when(general_when value) {
    if (this.general_when != null) { this.general_when._setParent(null); }
    this.general_when = value;
    if (this.general_when != null) { this.general_when._setParent(this); }
  }
  public boolean is_general_when() { return this.general_when != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.label_name != null) {
      this.label_name._walk(visitor);
    }
    if (this.general_when != null) {
      this.general_when._walk(visitor);
    }
  }
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
