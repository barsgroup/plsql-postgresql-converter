package ru.barsopen.plsqlconverter.ast.typed;
public interface declare_spec {
  // implemented by: variable_declaration, subtype_declaration, cursor_declaration, exception_declaration, pragma_declaration, record_declaration, table_declaration, create_procedure_body, create_function_body, create_type
  org.antlr.runtime.tree.Tree unparse();
}
