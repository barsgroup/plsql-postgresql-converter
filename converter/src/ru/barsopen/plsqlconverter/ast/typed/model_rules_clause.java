package ru.barsopen.plsqlconverter.ast.typed;
public class model_rules_clause implements _baseNode {
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
  public java.util.List<model_rules_element> model_rules_elements = new java.util.ArrayList<model_rules_element>();
  public java.util.List<model_rules_element> get_model_rules_elements() { return this.model_rules_elements; }
  public void add_model_rules_elements(model_rules_element value) {
    insert_model_rules_elements(model_rules_elements.size(), value);
  }
  public void insert_model_rules_elements(int pos, model_rules_element value) {
    this.model_rules_elements.add(pos, value);
    value._setParent(this);
  }
  public void remove_model_rules_elements(int pos) {
    this.model_rules_elements.get(pos)._setParent(null);
    this.model_rules_elements.remove(pos);
  }
  public void remove_model_rules_elements(model_rules_element value) {
    this.remove_model_rules_elements(this.model_rules_elements.indexOf(value));
  }
  public model_rules_part model_rules_part = null;
  public model_rules_part get_model_rules_part() { return this.model_rules_part; }
  public void set_model_rules_part(model_rules_part value) {
    if (this.model_rules_part != null) { this.model_rules_part._setParent(null); }
    this.model_rules_part = value;
    if (this.model_rules_part != null) { this.model_rules_part._setParent(this); }
  }
  public boolean is_model_rules_part() { return this.model_rules_part != null; }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    for (model_rules_element _value: this.model_rules_elements) {
      _value._walk(visitor);
    }
    if (this.model_rules_part != null) {
      this.model_rules_part._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_RULES);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("MODEL_RULES");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (model_rules_elements.size() == 0) { throw new RuntimeException(); }
    for (int i = 0; i < model_rules_elements.size(); ++i) {
      _result.addChild(model_rules_elements.get(i).unparse());
    }


    if (model_rules_part != null) {
      _result.addChild(model_rules_part.unparse());
    }


    return _result;
  }

}
