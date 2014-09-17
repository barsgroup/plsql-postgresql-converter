package ru.barsopen.plsqlconverter.ast.typed;
public interface cursor_loop_param {
  // implemented by: indexed_for, cursor_based_for, select_based_for
  org.antlr.runtime.tree.Tree unparse();
}
