package ru.barsopen.plsqlconverter.ast.typed;
public class model_column_list implements _baseNode {
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
  public java.util.List<model_column> model_columns = new java.util.ArrayList<model_column>();
  public java.util.List<model_column> get_model_columns() { return this.model_columns; }
  public void add_model_columns(model_column value) {
    insert_model_columns(model_columns.size(), value);
  }
  public void insert_model_columns(int pos, model_column value) {
    this.model_columns.add(pos, value);
    value._setParent(this);
  }
  public void remove_model_columns(int pos) {
    this.model_columns.get(pos)._setParent(null);
    this.model_columns.remove(pos);
  }
  public void remove_model_columns(model_column value) {
    this.remove_model_columns(this.model_columns.indexOf(value));
  }

  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.MODEL_COLUMNS);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_COLUMNS");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_columns.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < model_columns.size(); ++i) {
      _result.addChild(model_columns.get(i).unparse());
    }


    return _result;
  }

}
