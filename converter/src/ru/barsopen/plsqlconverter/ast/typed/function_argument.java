package ru.barsopen.plsqlconverter.ast.typed;
public class function_argument implements general_element_item, _baseNode {
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
  public java.util.List<argument> arguments = new java.util.ArrayList<argument>();
  public java.util.List<argument> get_arguments() { return this.arguments; }
  public void add_arguments(argument value) {
    insert_arguments(arguments.size(), value);
  }
  public void insert_arguments(int pos, argument value) {
    this.arguments.add(pos, value);
    value._setParent(this);
  }
  public void remove_arguments(int pos) {
    this.arguments.get(pos)._setParent(null);
    this.arguments.remove(pos);
  }
  public void remove_arguments(argument value) {
    this.remove_arguments(this.arguments.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (argument _value: this.arguments) {
      _value._walk(visitor);
    }
  }

  public void _replace(_baseNode child, _baseNode replacement) {
    for (int _i = 0; _i < this.arguments.size(); ++_i) {
      if (this.arguments.get(_i) == child) {
        this.remove_arguments(_i);
        this.insert_arguments(_i, (ru.barsopen.plsqlconverter.ast.typed.argument)replacement);
        return;
      }
    }
    throw new RuntimeException("Failed to replace node: no such node");
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.ARGUMENTS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("ARGUMENTS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    for (int i = 0; i < arguments.size(); ++i) {
      _result.addChild(arguments.get(i).unparse());
    }


    return _result;
  }

}
