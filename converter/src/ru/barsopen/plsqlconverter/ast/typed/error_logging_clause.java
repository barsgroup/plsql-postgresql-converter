package ru.barsopen.plsqlconverter.ast.typed;
public class error_logging_clause implements _baseNode {
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
  public error_logging_into_part error_logging_into_part = null;
  public error_logging_into_part get_error_logging_into_part() { return this.error_logging_into_part; }
  public void set_error_logging_into_part(error_logging_into_part value) {
    if (this.error_logging_into_part != null) { this.error_logging_into_part._setParent(null); }
    this.error_logging_into_part = value;
    if (this.error_logging_into_part != null) { this.error_logging_into_part._setParent(this); }
  }
  public boolean is_error_logging_into_part() { return this.error_logging_into_part != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public boolean is_expression() { return this.expression != null; }
  public error_logging_reject_part error_logging_reject_part = null;
  public error_logging_reject_part get_error_logging_reject_part() { return this.error_logging_reject_part; }
  public void set_error_logging_reject_part(error_logging_reject_part value) {
    if (this.error_logging_reject_part != null) { this.error_logging_reject_part._setParent(null); }
    this.error_logging_reject_part = value;
    if (this.error_logging_reject_part != null) { this.error_logging_reject_part._setParent(this); }
  }
  public boolean is_error_logging_reject_part() { return this.error_logging_reject_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.error_logging_into_part != null) {
      this.error_logging_into_part._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.error_logging_reject_part != null) {
      this.error_logging_reject_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.LOG_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("LOG_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (error_logging_into_part != null) {
      _result.addChild(error_logging_into_part.unparse());
    }


    if (expression != null) {
      _result.addChild(expression.unparse());
    }


    if (error_logging_reject_part != null) {
      _result.addChild(error_logging_reject_part.unparse());
    }


    return _result;
  }

}
