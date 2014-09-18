package ru.barsopen.plsqlconverter.ast.typed;
public class xml_param_version_part implements _baseNode {
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
  public org.antlr.runtime.tree.Tree NO_VK = null;
  public org.antlr.runtime.tree.Tree get_NO_VK() { return this.NO_VK; }
  public void set_NO_VK(org.antlr.runtime.tree.Tree value) {
    this.NO_VK = value;
  }
  public boolean is_NO_VK() { return this.NO_VK != null; }
  public org.antlr.runtime.tree.Tree VALUE_VK = null;
  public org.antlr.runtime.tree.Tree get_VALUE_VK() { return this.VALUE_VK; }
  public void set_VALUE_VK(org.antlr.runtime.tree.Tree value) {
    this.VALUE_VK = value;
  }
  public boolean is_VALUE_VK() { return this.VALUE_VK != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public boolean is_expression() { return this.expression != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.NO_VK != null) {
      visitor.visit(this.NO_VK);
    }
    if (this.VALUE_VK != null) {
      visitor.visit(this.VALUE_VK);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    if (this.expression == child) {
      this.set_expression((ru.barsopen.plsqlconverter.ast.typed.expression)replacement);
      return;
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.VERSION_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("VERSION_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (NO_VK != null) {
      _result.addChild(NO_VK);
    }


    if (VALUE_VK != null) {
      _result.addChild(VALUE_VK);
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    return _result;
  }

}
