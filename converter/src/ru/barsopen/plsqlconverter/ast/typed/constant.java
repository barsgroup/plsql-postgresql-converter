package ru.barsopen.plsqlconverter.ast.typed;
public interface constant extends expression_element, _baseNode {
  // implemented by: constant_unsigned, constant_negated, constant_exact_num, constant_approx_num, constant_char_string, constant_null, constant_true, constant_false, constant_dbtimezone, constant_sessiontimezone, constant_minvalue, constant_maxvalue, constant_default
}
