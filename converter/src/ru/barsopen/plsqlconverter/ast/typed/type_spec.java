package ru.barsopen.plsqlconverter.ast.typed;
public interface type_spec {
  // implemented by: type_spec_custom, native_datatype_spec, type_spec_interval
  org.antlr.runtime.tree.Tree unparse();
}
