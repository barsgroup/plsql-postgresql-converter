package ru.barsopen.plsqlconverter.ast.typed;
public interface transaction_control_statements extends sql_statement {
  // implemented by: set_transaction_command, set_constraint_command, commit_statement, rollback_statement, savepoint_statement
  org.antlr.runtime.tree.Tree unparse();
}
