package ru.barsopen.plsqlconverter.ast.typed;
public interface alias {
  // implemented by: column_alias, table_alias
  org.antlr.runtime.tree.Tree unparse();
}
