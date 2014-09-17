package ru.barsopen.plsqlconverter.ast.typed;
public interface expression extends grouping_element, expression_or_subquery, expression_or_expression_list, table_collection_expression, expression_or_select_statement, commit_force_exprs, in_elements, expression_or_seq_of_statements, xmlelement_value_expr, xml_passing_clause_expr, _baseNode {
  // implemented by: logic_expression, general_expression
}
