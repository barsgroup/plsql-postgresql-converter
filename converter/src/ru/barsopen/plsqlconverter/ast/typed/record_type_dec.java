package ru.barsopen.plsqlconverter.ast.typed;
public interface record_type_dec extends record_declaration {
  // implemented by: record_type_dec_record, record_type_dec_refcursor
  org.antlr.runtime.tree.Tree unparse();
}
