package ru.barsopen.plsqlconverter.ast.typed;
public interface record_declaration extends package_obj_spec, package_obj_body, declare_spec {
  // implemented by: record_type_dec, record_var_dec
  org.antlr.runtime.tree.Tree unparse();
}
