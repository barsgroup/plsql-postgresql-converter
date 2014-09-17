package ru.barsopen.plsqlconverter.ast.typed;
public interface data_manipulation_language_statements extends sql_statement {
  // implemented by: merge_statement, lock_table_statement, select_statement, update_statement, delete_statement, insert_statement
  org.antlr.runtime.tree.Tree unparse();
}
