package ru.barsopen.plsqlconverter.ast.typed;
public interface sql_script_item {
  // implemented by: unit_statement, sql_plus_command
  org.antlr.runtime.tree.Tree unparse();
}
