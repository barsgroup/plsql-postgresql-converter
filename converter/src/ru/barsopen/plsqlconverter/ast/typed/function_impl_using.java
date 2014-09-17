package ru.barsopen.plsqlconverter.ast.typed;
public class function_impl_using implements function_impl, _baseNode {
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
  public org.antlr.runtime.tree.Tree AGGREGATE_VK = null;
  public org.antlr.runtime.tree.Tree get_AGGREGATE_VK() { return this.AGGREGATE_VK; }
  public void set_AGGREGATE_VK(org.antlr.runtime.tree.Tree value) {
    this.AGGREGATE_VK = value;
  }
  public boolean is_AGGREGATE_VK() { return this.AGGREGATE_VK != null; }
  public implementation_type_name implementation_type_name = null;
  public implementation_type_name get_implementation_type_name() { return this.implementation_type_name; }
  public void set_implementation_type_name(implementation_type_name value) {
    if (this.implementation_type_name != null) { this.implementation_type_name._setParent(null); }
    this.implementation_type_name = value;
    if (this.implementation_type_name != null) { this.implementation_type_name._setParent(this); }
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.USING_MODE);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("USING_MODE");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (AGGREGATE_VK != null) {
      _result.addChild(AGGREGATE_VK);
    }


    if (implementation_type_name == null) { throw new RuntimeException(); }
    _result.addChild(implementation_type_name.unparse());


    return _result;
  }

}
