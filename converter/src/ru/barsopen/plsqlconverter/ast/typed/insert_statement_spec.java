package ru.barsopen.plsqlconverter.ast.typed;
public interface insert_statement_spec {
  // implemented by: single_table_insert, multi_table_insert
  org.antlr.runtime.tree.Tree unparse();
}
