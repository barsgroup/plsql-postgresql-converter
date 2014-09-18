package ru.barsopen.plsqlconverter.ast.typed;
public class return_rows_clause implements _baseNode {
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
  public org.antlr.runtime.tree.Tree UPDATED_VK = null;
  public org.antlr.runtime.tree.Tree get_UPDATED_VK() { return this.UPDATED_VK; }
  public void set_UPDATED_VK(org.antlr.runtime.tree.Tree value) {
    this.UPDATED_VK = value;
  }
  public boolean is_UPDATED_VK() { return this.UPDATED_VK != null; }
  public org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL = null;
  public org.antlr.runtime.tree.Tree get_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL; }
  public void set_SQL92_RESERVED_ALL(org.antlr.runtime.tree.Tree value) {
    this.SQL92_RESERVED_ALL = value;
  }
  public boolean is_SQL92_RESERVED_ALL() { return this.SQL92_RESERVED_ALL != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.UPDATED_VK != null) {
      visitor.visit(this.UPDATED_VK);
    }
    if (this.SQL92_RESERVED_ALL != null) {
      visitor.visit(this.SQL92_RESERVED_ALL);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.RETURN_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("RETURN_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (UPDATED_VK != null) {
      _result.addChild(UPDATED_VK);
    }


    if (SQL92_RESERVED_ALL != null) {
      _result.addChild(SQL92_RESERVED_ALL);
    }


    return _result;
  }

}
