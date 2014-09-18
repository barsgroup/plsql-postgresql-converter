package ru.barsopen.plsqlconverter.ast.typed;
public class rollback_statement implements transaction_control_statements, _baseNode {
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
  public org.antlr.runtime.tree.Tree WORK_VK = null;
  public org.antlr.runtime.tree.Tree get_WORK_VK() { return this.WORK_VK; }
  public void set_WORK_VK(org.antlr.runtime.tree.Tree value) {
    this.WORK_VK = value;
  }
  public boolean is_WORK_VK() { return this.WORK_VK != null; }
  public rollback_statement_additional rollback_statement_additional = null;
  public rollback_statement_additional get_rollback_statement_additional() { return this.rollback_statement_additional; }
  public void set_rollback_statement_additional(rollback_statement_additional value) {
    if (this.rollback_statement_additional != null) { this.rollback_statement_additional._setParent(null); }
    this.rollback_statement_additional = value;
    if (this.rollback_statement_additional != null) { this.rollback_statement_additional._setParent(this); }
  }
  public boolean is_rollback_statement_additional() { return this.rollback_statement_additional != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.WORK_VK != null) {
      visitor.visit(this.WORK_VK);
    }
    if (this.rollback_statement_additional != null) {
      this.rollback_statement_additional._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.ROLLBACK_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ROLLBACK_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (WORK_VK != null) {
      _result.addChild(WORK_VK);
    }


    if (rollback_statement_additional != null) {
      _result.addChild(rollback_statement_additional.unparse());
    }


    return _result;
  }

}
