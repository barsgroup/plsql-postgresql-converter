package ru.barsopen.plsqlconverter.ast.typed;
public interface subquery_operation_part {
  // implemented by: subquery_operation_union, subquery_operation_intersect, subquery_operation_minus
  org.antlr.runtime.tree.Tree unparse();
}
