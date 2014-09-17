package ru.barsopen.plsqlconverter.ast.typed;
public interface table_declaration extends package_obj_spec, package_obj_body, declare_spec {
  // implemented by: table_type_dec, table_var_dec
  org.antlr.runtime.tree.Tree unparse();
}
