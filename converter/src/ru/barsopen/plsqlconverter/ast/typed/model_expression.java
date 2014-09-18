package ru.barsopen.plsqlconverter.ast.typed;
public class model_expression implements _baseNode {
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
  public expression_element expression_element = null;
  public expression_element get_expression_element() { return this.expression_element; }
  public void set_expression_element(expression_element value) {
    if (this.expression_element != null) { this.expression_element._setParent(null); }
    this.expression_element = value;
    if (this.expression_element != null) { this.expression_element._setParent(this); }
  }
  public java.util.List<model_expression_element> model_expression_elements = new java.util.ArrayList<model_expression_element>();
  public java.util.List<model_expression_element> get_model_expression_elements() { return this.model_expression_elements; }
  public void add_model_expression_elements(model_expression_element value) {
    insert_model_expression_elements(model_expression_elements.size(), value);
  }
  public void insert_model_expression_elements(int pos, model_expression_element value) {
    this.model_expression_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_model_expression_elements(int pos) {
    this.model_expression_elements.get(pos)._setParent(null);
    this.model_expression_elements.remove(pos);
  }
  public void remove_model_expression_elements(model_expression_element value) {
    this.remove_model_expression_elements(this.model_expression_elements.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.expression_element != null) {
      this.expression_element._walk(visitor);
    }
    for (model_expression_element _value: this.model_expression_elements) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_EXPRESSION);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_EXPRESSION");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (expression_element == null) { throw new RuntimeException(); }
    _result.addChild(expression_element.unparse());


    if (model_expression_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < model_expression_elements.size(); ++i) {
      _result.addChild(model_expression_elements.get(i).unparse());
    }


    return _result;
  }

}
