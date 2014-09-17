package ru.barsopen.plsqlconverter.ast.typed;
public class commit_force implements commit_statement_additional, _baseNode {
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
  public commit_force_content commit_force_content = null;
  public commit_force_content get_commit_force_content() { return this.commit_force_content; }
  public void set_commit_force_content(commit_force_content value) {
    if (this.commit_force_content != null) { this.commit_force_content._setParent(null); }
    this.commit_force_content = value;
    if (this.commit_force_content != null) { this.commit_force_content._setParent(this); }
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.commit_force_content != null) {
      this.commit_force_content._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FORCE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("FORCE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (commit_force_content == null) { throw new RuntimeException(); }
    _result.addChild(commit_force_content.unparse());


    return _result;
  }

}
