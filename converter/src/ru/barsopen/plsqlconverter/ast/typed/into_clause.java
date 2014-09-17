package ru.barsopen.plsqlconverter.ast.typed;
public interface into_clause {
  // implemented by: into_clause_normal, into_clause_bulk
  org.antlr.runtime.tree.Tree unparse();
}
