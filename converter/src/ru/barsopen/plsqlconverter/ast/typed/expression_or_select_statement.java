package ru.barsopen.plsqlconverter.ast.typed;
public interface expression_or_select_statement {
  // implemented by: expression, select_statement
  org.antlr.runtime.tree.Tree unparse();
}
