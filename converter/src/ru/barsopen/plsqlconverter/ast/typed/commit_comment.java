package ru.barsopen.plsqlconverter.ast.typed;
public class commit_comment implements commit_statement_additional, _baseNode {
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
  public expression comment_expr = null;
  public expression get_comment_expr() { return this.comment_expr; }
  public void set_comment_expr(expression value) {
    if (this.comment_expr != null) { this.comment_expr._setParent(null); }
    this.comment_expr = value;
    if (this.comment_expr != null) { this.comment_expr._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.COMMENT_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("COMMENT_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (comment_expr == null) { throw new RuntimeException(); }
    _result.addChild(comment_expr.unparse());


    return _result;
  }

}
