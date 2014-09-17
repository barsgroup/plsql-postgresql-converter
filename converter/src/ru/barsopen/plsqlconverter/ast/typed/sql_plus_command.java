package ru.barsopen.plsqlconverter.ast.typed;
public interface sql_plus_command extends sql_script_item {
  // implemented by: whenever_command, exit_command, prompt_command, set_command
  org.antlr.runtime.tree.Tree unparse();
}
