package ru.barsopen.plsqlconverter.ast.typed;
public interface table_expression_element {
  // implemented by: collection_mode, select_mode, direct_mode, general_element, standard_function
  org.antlr.runtime.tree.Tree unparse();
}
