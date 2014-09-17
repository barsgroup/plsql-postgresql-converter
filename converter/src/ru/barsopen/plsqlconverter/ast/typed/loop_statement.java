package ru.barsopen.plsqlconverter.ast.typed;
public interface loop_statement extends statement {
  // implemented by: while_loop, for_loop, simple_loop
  org.antlr.runtime.tree.Tree unparse();
}
