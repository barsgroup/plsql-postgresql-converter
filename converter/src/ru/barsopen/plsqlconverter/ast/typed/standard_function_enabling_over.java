package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_enabling_over implements standard_function, _baseNode {
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
  public String name = null;
  public String get_name() { return this.name; }
  public void set_name(String value) {
    this.name = value;
  }
  public function_argument function_argument = null;
  public function_argument get_function_argument() { return this.function_argument; }
  public void set_function_argument(function_argument value) {
    if (this.function_argument != null) { this.function_argument._setParent(null); }
    this.function_argument = value;
    if (this.function_argument != null) { this.function_argument._setParent(this); }
  }
  public over_clause over_clause = null;
  public over_clause get_over_clause() { return this.over_clause; }
  public void set_over_clause(over_clause value) {
    if (this.over_clause != null) { this.over_clause._setParent(null); }
    this.over_clause = value;
    if (this.over_clause != null) { this.over_clause._setParent(this); }
  }
  public boolean is_over_clause() { return this.over_clause != null; }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.FUNCTION_ENABLING_OVER);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText(name);
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);

    if (function_argument == null) { throw new RuntimeException(); }
    _result.addChild(function_argument.unparse());


    if (over_clause != null) {
      _result.addChild(over_clause.unparse());
    }


    return _result;
  }

}
