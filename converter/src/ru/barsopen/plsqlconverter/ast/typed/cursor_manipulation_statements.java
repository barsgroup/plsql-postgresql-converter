package ru.barsopen.plsqlconverter.ast.typed;
public interface cursor_manipulation_statements extends sql_statement {
  // implemented by: close_statement, open_statement, fetch_statement, open_for_statement
  org.antlr.runtime.tree.Tree unparse();
}
