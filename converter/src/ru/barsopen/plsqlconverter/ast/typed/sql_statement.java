package ru.barsopen.plsqlconverter.ast.typed;
public interface sql_statement extends statement {
  // implemented by: execute_immediate, data_manipulation_language_statements, cursor_manipulation_statements, transaction_control_statements
  org.antlr.runtime.tree.Tree unparse();
}
