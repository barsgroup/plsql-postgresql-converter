package ru.barsopen.plsqlconverter.ast.typed;
public class parameters implements _baseNode {
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
  public java.util.List<parameter> parameters = new java.util.ArrayList<parameter>();
  public java.util.List<parameter> get_parameters() { return this.parameters; }
  public void add_parameters(parameter value) {
    insert_parameters(parameters.size(), value);
  }
  public void insert_parameters(int pos, parameter value) {
    this.parameters.add(pos, value);
    value._setParent(this);
  }
  public void remove_parameters(int pos) {
    this.parameters.get(pos)._setParent(null);
    this.parameters.remove(pos);
  }
  public void remove_parameters(parameter value) {
    this.remove_parameters(this.parameters.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (parameter _value: this.parameters) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.PARAMETERS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("PARAMETERS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < parameters.size(); ++i) {
      _result.addChild(parameters.get(i).unparse());
    }


    return _result;
  }

}
