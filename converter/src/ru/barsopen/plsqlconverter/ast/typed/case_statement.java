package ru.barsopen.plsqlconverter.ast.typed;
public interface case_statement extends statement, expression_element {
  // implemented by: case_statement_simple, case_statement_searched
  org.antlr.runtime.tree.Tree unparse();
}
