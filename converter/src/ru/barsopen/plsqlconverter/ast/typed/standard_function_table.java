package ru.barsopen.plsqlconverter.ast.typed;
public class standard_function_table implements standard_function, _baseNode {
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
  public xml_namespaces_clause xml_namespaces_clause = null;
  public xml_namespaces_clause get_xml_namespaces_clause() { return this.xml_namespaces_clause; }
  public void set_xml_namespaces_clause(xml_namespaces_clause value) {
    if (this.xml_namespaces_clause != null) { this.xml_namespaces_clause._setParent(null); }
    this.xml_namespaces_clause = value;
    if (this.xml_namespaces_clause != null) { this.xml_namespaces_clause._setParent(this); }
  }
  public boolean is_xml_namespaces_clause() { return this.xml_namespaces_clause != null; }
  public expression expression = null;
  public expression get_expression() { return this.expression; }
  public void set_expression(expression value) {
    if (this.expression != null) { this.expression._setParent(null); }
    this.expression = value;
    if (this.expression != null) { this.expression._setParent(this); }
  }
  public xml_passing_clause xml_passing_clause = null;
  public xml_passing_clause get_xml_passing_clause() { return this.xml_passing_clause; }
  public void set_xml_passing_clause(xml_passing_clause value) {
    if (this.xml_passing_clause != null) { this.xml_passing_clause._setParent(null); }
    this.xml_passing_clause = value;
    if (this.xml_passing_clause != null) { this.xml_passing_clause._setParent(this); }
  }
  public boolean is_xml_passing_clause() { return this.xml_passing_clause != null; }
  public java.util.List<xml_table_column> xml_table_columns = new java.util.ArrayList<xml_table_column>();
  public java.util.List<xml_table_column> get_xml_table_columns() { return this.xml_table_columns; }
  public void add_xml_table_columns(xml_table_column value) {
    insert_xml_table_columns(xml_table_columns.size(), value);
  }
  public void insert_xml_table_columns(int pos, xml_table_column value) {
    this.xml_table_columns.add(pos, value);
    value._setParent(this);
  }
  public void remove_xml_table_columns(int pos) {
    this.xml_table_columns.get(pos)._setParent(null);
    this.xml_table_columns.remove(pos);
  }
  public void remove_xml_table_columns(xml_table_column value) {
    this.remove_xml_table_columns(this.xml_table_columns.indexOf(value));
  }

  public void _walk(_visitor visitor) {
    visitor.visit(this);
    if (this.xml_namespaces_clause != null) {
      this.xml_namespaces_clause._walk(visitor);
    }
    if (this.expression != null) {
      this.expression._walk(visitor);
    }
    if (this.xml_passing_clause != null) {
      this.xml_passing_clause._walk(visitor);
    }
    for (xml_table_column _value: this.xml_table_columns) {
      _value._walk(visitor);
    }
  }
  public org.antlr.runtime.tree.Tree unparse() {
    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(br.com.porcelli.parser.plsql.PLSQLParser.XMLTABLE_VK);
    _token.setLine(_line);
    _token.setCharPositionInLine(_col);
    _token.setText("XMLTABLE_VK");
    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);
    _result.setTokenStartIndex(_tokenStartIndex);
    _result.setTokenStopIndex(_tokenStopIndex);
    if (xml_namespaces_clause != null) {
      _result.addChild(xml_namespaces_clause.unparse());
    }


    if (expression == null) { throw new RuntimeException(); }
    _result.addChild(expression.unparse());


    if (xml_passing_clause != null) {
      _result.addChild(xml_passing_clause.unparse());
    }


    for (int i = 0; i < xml_table_columns.size(); ++i) {
      _result.addChild(xml_table_columns.get(i).unparse());
    }


    return _result;
  }

}
