package ru.barsopen.plsqlconverter.ast.typed;
public class parser {
  public static boolean canParsecompilation_unit(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COMPILATION_UNIT;
  }

  public static compilation_unit parsecompilation_unit(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecompilation_unit(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    compilation_unit _result = new compilation_unit();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParseunit_statement(tree.getChild(_i)))) {
      _result.add_unit_statements(parseunit_statement(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static compilation_unit make_compilation_unit(java.util.List<ru.barsopen.plsqlconverter.ast.typed.unit_statement> unit_statements) {
    compilation_unit _result = new compilation_unit();
    if (unit_statements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.unit_statement _value: unit_statements) { _result.add_unit_statements(_value); }
    }
    return _result;
  }

  public static boolean canParsesql_script(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL_SCRIPT;
  }

  public static sql_script parsesql_script(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesql_script(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sql_script _result = new sql_script();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsesql_script_item(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_sql_script_item(parsesql_script_item(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sql_script make_sql_script(ru.barsopen.plsqlconverter.ast.typed.sql_script_item sql_script_item) {
    sql_script _result = new sql_script();
    _result.set_sql_script_item(sql_script_item);
    return _result;
  }

  public static boolean canParsesql_script_item(org.antlr.runtime.tree.Tree tree) {
    return canParseunit_statement(tree) || canParsesql_plus_command(tree);
  }

  public static sql_script_item parsesql_script_item(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesql_script_item(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseunit_statement(tree)) return parseunit_statement(tree);
    if (canParsesql_plus_command(tree)) return parsesql_plus_command(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsesql_plus_command(org.antlr.runtime.tree.Tree tree) {
    return canParsewhenever_command(tree) || canParseexit_command(tree) || canParseprompt_command(tree) || canParseset_command(tree);
  }

  public static sql_plus_command parsesql_plus_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesql_plus_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsewhenever_command(tree)) return parsewhenever_command(tree);
    if (canParseexit_command(tree)) return parseexit_command(tree);
    if (canParseprompt_command(tree)) return parseprompt_command(tree);
    if (canParseset_command(tree)) return parseset_command(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewhenever_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WHENEVER_VK;
  }

  public static whenever_command parsewhenever_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewhenever_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    whenever_command _result = new whenever_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (true))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static whenever_command make_whenever_command(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    whenever_command _result = new whenever_command();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseset_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SET_VK;
  }

  public static set_command parseset_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParseset_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    set_command _result = new set_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (true))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_content(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static set_command make_set_command(org.antlr.runtime.tree.Tree content) {
    set_command _result = new set_command();
    _result.set_content(content);
    return _result;
  }

  public static boolean canParseexit_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXIT_VK;
  }

  public static exit_command parseexit_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexit_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exit_command _result = new exit_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exit_command make_exit_command() {
    exit_command _result = new exit_command();
    return _result;
  }

  public static boolean canParseprompt_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PROMPT;
  }

  public static prompt_command parseprompt_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParseprompt_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    prompt_command _result = new prompt_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static prompt_command make_prompt_command() {
    prompt_command _result = new prompt_command();
    return _result;
  }

  public static boolean canParseunit_statement(org.antlr.runtime.tree.Tree tree) {
    return canParsealter_function(tree) || canParsealter_package(tree) || canParsealter_procedure(tree) || canParsealter_sequence(tree) || canParsealter_trigger(tree) || canParsealter_type(tree) || canParsecreate_function_body(tree) || canParsecreate_procedure_body(tree) || canParsecreate_package(tree) || canParsecreate_sequence(tree) || canParsecreate_trigger(tree) || canParsecreate_type(tree) || canParsedrop_function(tree) || canParsedrop_package(tree) || canParsedrop_procedure(tree) || canParsedrop_sequence(tree) || canParsedrop_trigger(tree) || canParsedrop_type(tree);
  }

  public static unit_statement parseunit_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseunit_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsealter_function(tree)) return parsealter_function(tree);
    if (canParsealter_package(tree)) return parsealter_package(tree);
    if (canParsealter_procedure(tree)) return parsealter_procedure(tree);
    if (canParsealter_sequence(tree)) return parsealter_sequence(tree);
    if (canParsealter_trigger(tree)) return parsealter_trigger(tree);
    if (canParsealter_type(tree)) return parsealter_type(tree);
    if (canParsecreate_function_body(tree)) return parsecreate_function_body(tree);
    if (canParsecreate_procedure_body(tree)) return parsecreate_procedure_body(tree);
    if (canParsecreate_package(tree)) return parsecreate_package(tree);
    if (canParsecreate_sequence(tree)) return parsecreate_sequence(tree);
    if (canParsecreate_trigger(tree)) return parsecreate_trigger(tree);
    if (canParsecreate_type(tree)) return parsecreate_type(tree);
    if (canParsedrop_function(tree)) return parsedrop_function(tree);
    if (canParsedrop_package(tree)) return parsedrop_package(tree);
    if (canParsedrop_procedure(tree)) return parsedrop_procedure(tree);
    if (canParsedrop_sequence(tree)) return parsedrop_sequence(tree);
    if (canParsedrop_trigger(tree)) return parsedrop_trigger(tree);
    if (canParsedrop_type(tree)) return parsedrop_type(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsedrop_function(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_FUNCTION;
  }

  public static drop_function parsedrop_function(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_function(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_function _result = new drop_function();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsefunction_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_name(parsefunction_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_function make_drop_function(ru.barsopen.plsqlconverter.ast.typed.function_name function_name) {
    drop_function _result = new drop_function();
    _result.set_function_name(function_name);
    return _result;
  }

  public static boolean canParsealter_function(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ALTER_FUNCTION;
  }

  public static alter_function parsealter_function(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_function(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_function _result = new alter_function();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsefunction_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_name(parsefunction_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DEBUG_VK)) {
      _result.set_DEBUG_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REUSE_VK)) {
      _result.set_REUSE_VK(tree.getChild(_i));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsecompiler_parameters_clause(tree.getChild(_i)))) {
      _result.add_compiler_parameters_clauses(parsecompiler_parameters_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_function make_alter_function(ru.barsopen.plsqlconverter.ast.typed.function_name function_name,
      org.antlr.runtime.tree.Tree DEBUG_VK,
      org.antlr.runtime.tree.Tree REUSE_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.compiler_parameters_clause> compiler_parameters_clauses) {
    alter_function _result = new alter_function();
    _result.set_function_name(function_name);
    _result.set_DEBUG_VK(DEBUG_VK);
    _result.set_REUSE_VK(REUSE_VK);
    if (compiler_parameters_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.compiler_parameters_clause _value: compiler_parameters_clauses) { _result.add_compiler_parameters_clauses(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_function_body(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CREATE_FUNCTION;
  }

  public static create_function_body parsecreate_function_body(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_function_body(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_function_body _result = new create_function_body();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_CREATE)) {
      _result.set_SQL92_RESERVED_CREATE(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REPLACE_VK)) {
      _result.set_REPLACE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsefunction_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_name(parsefunction_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseparameters(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameters(parseparameters(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParseinvoker_rights_clause(tree.getChild(_i)))) {
      _result.add_invoker_rights_clauses(parseinvoker_rights_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParseparallel_enable_clause(tree.getChild(_i)))) {
      _result.add_parallel_enable_clauses(parseparallel_enable_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParseresult_cache_clause(tree.getChild(_i)))) {
      _result.add_result_cache_clauses(parseresult_cache_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DETERMINISTIC_VK)) {
      _result.add_DETERMINISTIC_VKs(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIPELINED_VK)) {
      _result.set_PIPELINED_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsefunction_impl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_impl(parsefunction_impl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_function_body make_create_function_body(org.antlr.runtime.tree.Tree SQL92_RESERVED_CREATE,
      org.antlr.runtime.tree.Tree REPLACE_VK,
      ru.barsopen.plsqlconverter.ast.typed.function_name function_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.parameters parameters,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.invoker_rights_clause> invoker_rights_clauses,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.parallel_enable_clause> parallel_enable_clauses,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.result_cache_clause> result_cache_clauses,
      java.util.List<org.antlr.runtime.tree.Tree> DETERMINISTIC_VKs,
      org.antlr.runtime.tree.Tree PIPELINED_VK,
      ru.barsopen.plsqlconverter.ast.typed.function_impl function_impl) {
    create_function_body _result = new create_function_body();
    _result.set_SQL92_RESERVED_CREATE(SQL92_RESERVED_CREATE);
    _result.set_REPLACE_VK(REPLACE_VK);
    _result.set_function_name(function_name);
    _result.set_type_spec(type_spec);
    _result.set_parameters(parameters);
    if (invoker_rights_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.invoker_rights_clause _value: invoker_rights_clauses) { _result.add_invoker_rights_clauses(_value); }
    }
    if (parallel_enable_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.parallel_enable_clause _value: parallel_enable_clauses) { _result.add_parallel_enable_clauses(_value); }
    }
    if (result_cache_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.result_cache_clause _value: result_cache_clauses) { _result.add_result_cache_clauses(_value); }
    }
    if (DETERMINISTIC_VKs != null) {
      for (org.antlr.runtime.tree.Tree _value: DETERMINISTIC_VKs) { _result.add_DETERMINISTIC_VKs(_value); }
    }
    _result.set_PIPELINED_VK(PIPELINED_VK);
    _result.set_function_impl(function_impl);
    return _result;
  }

  public static boolean canParseparameters(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARAMETERS;
  }

  public static parameters parseparameters(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameters(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameters _result = new parameters();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParseparameter(tree.getChild(_i)))) {
      _result.add_parameters(parseparameter(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameters make_parameters(java.util.List<ru.barsopen.plsqlconverter.ast.typed.parameter> parameters) {
    parameters _result = new parameters();
    if (parameters != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.parameter _value: parameters) { _result.add_parameters(_value); }
    }
    return _result;
  }

  public static boolean canParsefunction_impl(org.antlr.runtime.tree.Tree tree) {
    return canParsefunction_impl_using(tree) || canParsecall_mode(tree) || canParsebody_mode(tree);
  }

  public static function_impl parsefunction_impl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefunction_impl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsefunction_impl_using(tree)) return parsefunction_impl_using(tree);
    if (canParsecall_mode(tree)) return parsecall_mode(tree);
    if (canParsebody_mode(tree)) return parsebody_mode(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsefunction_impl_using(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.USING_MODE;
  }

  public static function_impl_using parsefunction_impl_using(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefunction_impl_using(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    function_impl_using _result = new function_impl_using();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.AGGREGATE_VK)) {
      _result.set_AGGREGATE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseimplementation_type_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_implementation_type_name(parseimplementation_type_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static function_impl_using make_function_impl_using(org.antlr.runtime.tree.Tree AGGREGATE_VK,
      ru.barsopen.plsqlconverter.ast.typed.implementation_type_name implementation_type_name) {
    function_impl_using _result = new function_impl_using();
    _result.set_AGGREGATE_VK(AGGREGATE_VK);
    _result.set_implementation_type_name(implementation_type_name);
    return _result;
  }

  public static boolean canParsecall_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CALL_MODE;
  }

  public static call_mode parsecall_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecall_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    call_mode _result = new call_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecall_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_call_spec(parsecall_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static call_mode make_call_mode(ru.barsopen.plsqlconverter.ast.typed.call_spec call_spec) {
    call_mode _result = new call_mode();
    _result.set_call_spec(call_spec);
    return _result;
  }

  public static boolean canParsebody_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BODY_MODE;
  }

  public static body_mode parsebody_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParsebody_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    body_mode _result = new body_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseblock(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_block(parseblock(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static body_mode make_body_mode(ru.barsopen.plsqlconverter.ast.typed.block block) {
    body_mode _result = new body_mode();
    _result.set_block(block);
    return _result;
  }

  public static boolean canParseparallel_enable_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARALLEL_ENABLE_VK;
  }

  public static parallel_enable_clause parseparallel_enable_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparallel_enable_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parallel_enable_clause _result = new parallel_enable_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsepartition_by_clause(tree.getChild(_i)))) {
      _result.set_partition_by_clause(parsepartition_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parallel_enable_clause make_parallel_enable_clause(ru.barsopen.plsqlconverter.ast.typed.partition_by_clause partition_by_clause) {
    parallel_enable_clause _result = new parallel_enable_clause();
    _result.set_partition_by_clause(partition_by_clause);
    return _result;
  }

  public static boolean canParsepartition_by_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARTITION_VK;
  }

  public static partition_by_clause parsepartition_by_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepartition_by_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    partition_by_clause _result = new partition_by_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static partition_by_clause make_partition_by_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    partition_by_clause _result = new partition_by_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseresult_cache_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RESULT_CACHE_VK;
  }

  public static result_cache_clause parseresult_cache_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseresult_cache_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    result_cache_clause _result = new result_cache_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParserelies_on_part(tree.getChild(_i)))) {
      _result.set_relies_on_part(parserelies_on_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static result_cache_clause make_result_cache_clause(ru.barsopen.plsqlconverter.ast.typed.relies_on_part relies_on_part) {
    result_cache_clause _result = new result_cache_clause();
    _result.set_relies_on_part(relies_on_part);
    return _result;
  }

  public static boolean canParserelies_on_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RELIES_ON_VK;
  }

  public static relies_on_part parserelies_on_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParserelies_on_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    relies_on_part _result = new relies_on_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i)))) {
      _result.add_tableview_names(parsetableview_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static relies_on_part make_relies_on_part(java.util.List<ru.barsopen.plsqlconverter.ast.typed.tableview_name> tableview_names) {
    relies_on_part _result = new relies_on_part();
    if (tableview_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.tableview_name _value: tableview_names) { _result.add_tableview_names(_value); }
    }
    return _result;
  }

  public static boolean canParsestreaming_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.STREAMING_CLAUSE;
  }

  public static streaming_clause parsestreaming_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestreaming_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    streaming_clause _result = new streaming_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static streaming_clause make_streaming_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    streaming_clause _result = new streaming_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedrop_package(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_PACKAGE;
  }

  public static drop_package parsedrop_package(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_package(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_package _result = new drop_package();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsepackage_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_package_name(parsepackage_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BODY_VK)) {
      _result.set_BODY_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_package make_drop_package(ru.barsopen.plsqlconverter.ast.typed.package_name package_name,
      org.antlr.runtime.tree.Tree BODY_VK) {
    drop_package _result = new drop_package();
    _result.set_package_name(package_name);
    _result.set_BODY_VK(BODY_VK);
    return _result;
  }

  public static boolean canParsealter_package(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ALTER_PACKAGE;
  }

  public static alter_package parsealter_package(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_package(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_package _result = new alter_package();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_package make_alter_package(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_package _result = new alter_package();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_package(org.antlr.runtime.tree.Tree tree) {
    return canParsecreate_package_spec(tree) || canParsecreate_package_body(tree);
  }

  public static create_package parsecreate_package(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_package(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecreate_package_spec(tree)) return parsecreate_package_spec(tree);
    if (canParsecreate_package_body(tree)) return parsecreate_package_body(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecreate_package_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CREATE_PACKAGE_SPEC;
  }

  public static create_package_spec parsecreate_package_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_package_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_package_spec _result = new create_package_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REPLACE_VK)) {
      _result.set_REPLACE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsepackage_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_package_name(parsepackage_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseinvoker_rights_clause(tree.getChild(_i)))) {
      _result.set_invoker_rights_clause(parseinvoker_rights_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsepackage_obj_spec(tree.getChild(_i)))) {
      _result.add_package_obj_specs(parsepackage_obj_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_package_spec make_create_package_spec(org.antlr.runtime.tree.Tree REPLACE_VK,
      ru.barsopen.plsqlconverter.ast.typed.package_name package_name,
      ru.barsopen.plsqlconverter.ast.typed.invoker_rights_clause invoker_rights_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.package_obj_spec> package_obj_specs) {
    create_package_spec _result = new create_package_spec();
    _result.set_REPLACE_VK(REPLACE_VK);
    _result.set_package_name(package_name);
    _result.set_invoker_rights_clause(invoker_rights_clause);
    if (package_obj_specs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.package_obj_spec _value: package_obj_specs) { _result.add_package_obj_specs(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_package_body(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CREATE_PACKAGE_BODY;
  }

  public static create_package_body parsecreate_package_body(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_package_body(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_package_body _result = new create_package_body();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REPLACE_VK)) {
      _result.set_REPLACE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsepackage_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_package_name(parsepackage_name(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsepackage_obj_body(tree.getChild(_i)))) {
      _result.add_package_obj_bodys(parsepackage_obj_body(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i)))) {
      _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexception_clause(tree.getChild(_i)))) {
      _result.set_exception_clause(parseexception_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_package_body make_create_package_body(org.antlr.runtime.tree.Tree REPLACE_VK,
      ru.barsopen.plsqlconverter.ast.typed.package_name package_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.package_obj_body> package_obj_bodys,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements,
      ru.barsopen.plsqlconverter.ast.typed.exception_clause exception_clause) {
    create_package_body _result = new create_package_body();
    _result.set_REPLACE_VK(REPLACE_VK);
    _result.set_package_name(package_name);
    if (package_obj_bodys != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.package_obj_body _value: package_obj_bodys) { _result.add_package_obj_bodys(_value); }
    }
    _result.set_seq_of_statements(seq_of_statements);
    _result.set_exception_clause(exception_clause);
    return _result;
  }

  public static boolean canParsepackage_obj_spec(org.antlr.runtime.tree.Tree tree) {
    return canParsevariable_declaration(tree) || canParsesubtype_declaration(tree) || canParsecursor_declaration(tree) || canParseexception_declaration(tree) || canParserecord_declaration(tree) || canParsetable_declaration(tree) || canParseprocedure_spec(tree) || canParsefunction_spec(tree) || canParsepragma_declaration(tree);
  }

  public static package_obj_spec parsepackage_obj_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepackage_obj_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsevariable_declaration(tree)) return parsevariable_declaration(tree);
    if (canParsesubtype_declaration(tree)) return parsesubtype_declaration(tree);
    if (canParsecursor_declaration(tree)) return parsecursor_declaration(tree);
    if (canParseexception_declaration(tree)) return parseexception_declaration(tree);
    if (canParserecord_declaration(tree)) return parserecord_declaration(tree);
    if (canParsetable_declaration(tree)) return parsetable_declaration(tree);
    if (canParseprocedure_spec(tree)) return parseprocedure_spec(tree);
    if (canParsefunction_spec(tree)) return parsefunction_spec(tree);
    if (canParsepragma_declaration(tree)) return parsepragma_declaration(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseprocedure_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PROCEDURE_SPEC;
  }

  public static procedure_spec parseprocedure_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseprocedure_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    procedure_spec _result = new procedure_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseprocedure_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_procedure_name(parseprocedure_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseparameters(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameters(parseparameters(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsecall_mode(tree.getChild(_i)))) {
      _result.set_call_mode(parsecall_mode(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static procedure_spec make_procedure_spec(ru.barsopen.plsqlconverter.ast.typed.procedure_name procedure_name,
      ru.barsopen.plsqlconverter.ast.typed.parameters parameters,
      ru.barsopen.plsqlconverter.ast.typed.call_mode call_mode) {
    procedure_spec _result = new procedure_spec();
    _result.set_procedure_name(procedure_name);
    _result.set_parameters(parameters);
    _result.set_call_mode(call_mode);
    return _result;
  }

  public static boolean canParsefunction_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FUNCTION_SPEC;
  }

  public static function_spec parsefunction_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefunction_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    function_spec _result = new function_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsefunction_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_name(parsefunction_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseparameters(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameters(parseparameters(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIPELINED_VK)) {
      _result.set_PIPELINED_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RESULT_CACHE_VK)) {
      _result.set_RESULT_CACHE_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DETERMINISTIC_VK)) {
      _result.set_DETERMINISTIC_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static function_spec make_function_spec(ru.barsopen.plsqlconverter.ast.typed.function_name function_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.parameters parameters,
      org.antlr.runtime.tree.Tree PIPELINED_VK,
      org.antlr.runtime.tree.Tree RESULT_CACHE_VK,
      org.antlr.runtime.tree.Tree DETERMINISTIC_VK) {
    function_spec _result = new function_spec();
    _result.set_function_name(function_name);
    _result.set_type_spec(type_spec);
    _result.set_parameters(parameters);
    _result.set_PIPELINED_VK(PIPELINED_VK);
    _result.set_RESULT_CACHE_VK(RESULT_CACHE_VK);
    _result.set_DETERMINISTIC_VK(DETERMINISTIC_VK);
    return _result;
  }

  public static boolean canParsepackage_obj_body(org.antlr.runtime.tree.Tree tree) {
    return canParseprocedure_spec(tree) || canParsefunction_spec(tree) || canParsevariable_declaration(tree) || canParsesubtype_declaration(tree) || canParsecursor_declaration(tree) || canParseexception_declaration(tree) || canParserecord_declaration(tree) || canParsetable_declaration(tree) || canParsecreate_procedure_body(tree) || canParsecreate_function_body(tree) || canParsecreate_type(tree);
  }

  public static package_obj_body parsepackage_obj_body(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepackage_obj_body(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseprocedure_spec(tree)) return parseprocedure_spec(tree);
    if (canParsefunction_spec(tree)) return parsefunction_spec(tree);
    if (canParsevariable_declaration(tree)) return parsevariable_declaration(tree);
    if (canParsesubtype_declaration(tree)) return parsesubtype_declaration(tree);
    if (canParsecursor_declaration(tree)) return parsecursor_declaration(tree);
    if (canParseexception_declaration(tree)) return parseexception_declaration(tree);
    if (canParserecord_declaration(tree)) return parserecord_declaration(tree);
    if (canParsetable_declaration(tree)) return parsetable_declaration(tree);
    if (canParsecreate_procedure_body(tree)) return parsecreate_procedure_body(tree);
    if (canParsecreate_function_body(tree)) return parsecreate_function_body(tree);
    if (canParsecreate_type(tree)) return parsecreate_type(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsedrop_procedure(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_PROCEDURE;
  }

  public static drop_procedure parsedrop_procedure(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_procedure(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_procedure _result = new drop_procedure();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseprocedure_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_procedure_name(parseprocedure_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_procedure make_drop_procedure(ru.barsopen.plsqlconverter.ast.typed.procedure_name procedure_name) {
    drop_procedure _result = new drop_procedure();
    _result.set_procedure_name(procedure_name);
    return _result;
  }

  public static boolean canParsealter_procedure(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ALTER_PROCEDURE;
  }

  public static alter_procedure parsealter_procedure(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_procedure(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_procedure _result = new alter_procedure();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseprocedure_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_procedure_name(parseprocedure_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DEBUG_VK)) {
      _result.set_DEBUG_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REUSE_VK)) {
      _result.set_REUSE_VK(tree.getChild(_i));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsecompiler_parameters_clause(tree.getChild(_i)))) {
      _result.add_compiler_parameters_clauses(parsecompiler_parameters_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_procedure make_alter_procedure(ru.barsopen.plsqlconverter.ast.typed.procedure_name procedure_name,
      org.antlr.runtime.tree.Tree DEBUG_VK,
      org.antlr.runtime.tree.Tree REUSE_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.compiler_parameters_clause> compiler_parameters_clauses) {
    alter_procedure _result = new alter_procedure();
    _result.set_procedure_name(procedure_name);
    _result.set_DEBUG_VK(DEBUG_VK);
    _result.set_REUSE_VK(REUSE_VK);
    if (compiler_parameters_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.compiler_parameters_clause _value: compiler_parameters_clauses) { _result.add_compiler_parameters_clauses(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_procedure_body(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CREATE_PROCEDURE;
  }

  public static create_procedure_body parsecreate_procedure_body(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_procedure_body(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_procedure_body _result = new create_procedure_body();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_CREATE)) {
      _result.set_SQL92_RESERVED_CREATE(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REPLACE_VK)) {
      _result.set_REPLACE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseprocedure_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_procedure_name(parseprocedure_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseparameters(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameters(parseparameters(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseinvoker_rights_clause(tree.getChild(_i)))) {
      _result.set_invoker_rights_clause(parseinvoker_rights_clause(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsecreate_procedure_body_impl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_create_procedure_body_impl(parsecreate_procedure_body_impl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_procedure_body make_create_procedure_body(org.antlr.runtime.tree.Tree SQL92_RESERVED_CREATE,
      org.antlr.runtime.tree.Tree REPLACE_VK,
      ru.barsopen.plsqlconverter.ast.typed.procedure_name procedure_name,
      ru.barsopen.plsqlconverter.ast.typed.parameters parameters,
      ru.barsopen.plsqlconverter.ast.typed.invoker_rights_clause invoker_rights_clause,
      ru.barsopen.plsqlconverter.ast.typed.create_procedure_body_impl create_procedure_body_impl) {
    create_procedure_body _result = new create_procedure_body();
    _result.set_SQL92_RESERVED_CREATE(SQL92_RESERVED_CREATE);
    _result.set_REPLACE_VK(REPLACE_VK);
    _result.set_procedure_name(procedure_name);
    _result.set_parameters(parameters);
    _result.set_invoker_rights_clause(invoker_rights_clause);
    _result.set_create_procedure_body_impl(create_procedure_body_impl);
    return _result;
  }

  public static boolean canParsecreate_procedure_body_impl(org.antlr.runtime.tree.Tree tree) {
    return canParsecreate_procedure_body_impl_external(tree) || canParsecall_mode(tree) || canParsebody_mode(tree);
  }

  public static create_procedure_body_impl parsecreate_procedure_body_impl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_procedure_body_impl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecreate_procedure_body_impl_external(tree)) return parsecreate_procedure_body_impl_external(tree);
    if (canParsecall_mode(tree)) return parsecall_mode(tree);
    if (canParsebody_mode(tree)) return parsebody_mode(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecreate_procedure_body_impl_external(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXTERNAL_VK;
  }

  public static create_procedure_body_impl_external parsecreate_procedure_body_impl_external(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_procedure_body_impl_external(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_procedure_body_impl_external _result = new create_procedure_body_impl_external();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_procedure_body_impl_external make_create_procedure_body_impl_external() {
    create_procedure_body_impl_external _result = new create_procedure_body_impl_external();
    return _result;
  }

  public static boolean canParsedrop_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DROP_TRIGGER;
  }

  public static drop_trigger parsedrop_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_trigger _result = new drop_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetrigger_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_trigger_name(parsetrigger_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_trigger make_drop_trigger(ru.barsopen.plsqlconverter.ast.typed.trigger_name trigger_name) {
    drop_trigger _result = new drop_trigger();
    _result.set_trigger_name(trigger_name);
    return _result;
  }

  public static boolean canParsealter_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ALTER_TRIGGER;
  }

  public static alter_trigger parsealter_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_trigger _result = new alter_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_trigger make_alter_trigger(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_trigger _result = new alter_trigger();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CREATE_TRIGGER;
  }

  public static create_trigger parsecreate_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_trigger _result = new create_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_trigger make_create_trigger(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    create_trigger _result = new create_trigger();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetrigger_follows_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FOLLOWS_VK;
  }

  public static trigger_follows_clause parsetrigger_follows_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetrigger_follows_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    trigger_follows_clause _result = new trigger_follows_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static trigger_follows_clause make_trigger_follows_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    trigger_follows_clause _result = new trigger_follows_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetrigger_when_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN;
  }

  public static trigger_when_clause parsetrigger_when_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetrigger_when_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    trigger_when_clause _result = new trigger_when_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static trigger_when_clause make_trigger_when_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    trigger_when_clause _result = new trigger_when_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesimple_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SIMPLE_DML;
  }

  public static simple_dml_trigger parsesimple_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesimple_dml_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    simple_dml_trigger _result = new simple_dml_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static simple_dml_trigger make_simple_dml_trigger() {
    simple_dml_trigger _result = new simple_dml_trigger();
    return _result;
  }

  public static boolean canParsecompound_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COMPOUND_DML;
  }

  public static compound_dml_trigger parsecompound_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecompound_dml_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    compound_dml_trigger _result = new compound_dml_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static compound_dml_trigger make_compound_dml_trigger() {
    compound_dml_trigger _result = new compound_dml_trigger();
    return _result;
  }

  public static boolean canParsenon_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static non_dml_trigger parsenon_dml_trigger(org.antlr.runtime.tree.Tree tree) {
    if (!canParsenon_dml_trigger(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    non_dml_trigger _result = new non_dml_trigger();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static non_dml_trigger make_non_dml_trigger(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    non_dml_trigger _result = new non_dml_trigger();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetrigger_body(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static trigger_body parsetrigger_body(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetrigger_body(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    trigger_body _result = new trigger_body();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static trigger_body make_trigger_body(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    trigger_body _result = new trigger_body();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetiming_point_section(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static timing_point_section parsetiming_point_section(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetiming_point_section(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    timing_point_section _result = new timing_point_section();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static timing_point_section make_timing_point_section(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    timing_point_section _result = new timing_point_section();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsenon_dml_event(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static non_dml_event parsenon_dml_event(org.antlr.runtime.tree.Tree tree) {
    if (!canParsenon_dml_event(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    non_dml_event _result = new non_dml_event();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static non_dml_event make_non_dml_event(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    non_dml_event _result = new non_dml_event();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedml_event_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static dml_event_clause parsedml_event_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedml_event_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dml_event_clause _result = new dml_event_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dml_event_clause make_dml_event_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    dml_event_clause _result = new dml_event_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedml_event_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static dml_event_element parsedml_event_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedml_event_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dml_event_element _result = new dml_event_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dml_event_element make_dml_event_element(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    dml_event_element _result = new dml_event_element();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedml_event_nested_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static dml_event_nested_clause parsedml_event_nested_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedml_event_nested_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dml_event_nested_clause _result = new dml_event_nested_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dml_event_nested_clause make_dml_event_nested_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    dml_event_nested_clause _result = new dml_event_nested_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsereferencing_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static referencing_clause parsereferencing_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereferencing_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    referencing_clause _result = new referencing_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static referencing_clause make_referencing_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    referencing_clause _result = new referencing_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsereferencing_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static referencing_element parsereferencing_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereferencing_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    referencing_element _result = new referencing_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static referencing_element make_referencing_element(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    referencing_element _result = new referencing_element();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedrop_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static drop_type parsedrop_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_type _result = new drop_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_type make_drop_type(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    drop_type _result = new drop_type();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsealter_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static alter_type parsealter_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_type _result = new alter_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_type make_alter_type(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_type _result = new alter_type();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsealter_method_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static alter_method_element parsealter_method_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_method_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_method_element _result = new alter_method_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_method_element make_alter_method_element(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_method_element _result = new alter_method_element();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseattribute_definition(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static attribute_definition parseattribute_definition(org.antlr.runtime.tree.Tree tree) {
    if (!canParseattribute_definition(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    attribute_definition _result = new attribute_definition();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static attribute_definition make_attribute_definition(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    attribute_definition _result = new attribute_definition();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsealter_collection_clauses(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static alter_collection_clauses parsealter_collection_clauses(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_collection_clauses(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_collection_clauses _result = new alter_collection_clauses();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_collection_clauses make_alter_collection_clauses(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_collection_clauses _result = new alter_collection_clauses();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedependent_handling_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static dependent_handling_clause parsedependent_handling_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedependent_handling_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dependent_handling_clause _result = new dependent_handling_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dependent_handling_clause make_dependent_handling_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    dependent_handling_clause _result = new dependent_handling_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedependent_exceptions_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static dependent_exceptions_part parsedependent_exceptions_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedependent_exceptions_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dependent_exceptions_part _result = new dependent_exceptions_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dependent_exceptions_part make_dependent_exceptions_part(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    dependent_exceptions_part _result = new dependent_exceptions_part();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static create_type parsecreate_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_type _result = new create_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_type make_create_type(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    create_type _result = new create_type();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseobject_type_def(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static object_type_def parseobject_type_def(org.antlr.runtime.tree.Tree tree) {
    if (!canParseobject_type_def(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    object_type_def _result = new object_type_def();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static object_type_def make_object_type_def(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    object_type_def _result = new object_type_def();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseobject_as_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static object_as_part parseobject_as_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseobject_as_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    object_as_part _result = new object_as_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static object_as_part make_object_as_part(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    object_as_part _result = new object_as_part();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseobject_under_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static object_under_part parseobject_under_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseobject_under_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    object_under_part _result = new object_under_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static object_under_part make_object_under_part(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    object_under_part _result = new object_under_part();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsenested_table_type_def(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static nested_table_type_def parsenested_table_type_def(org.antlr.runtime.tree.Tree tree) {
    if (!canParsenested_table_type_def(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    nested_table_type_def _result = new nested_table_type_def();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static nested_table_type_def make_nested_table_type_def(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    nested_table_type_def _result = new nested_table_type_def();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesqlj_object_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static sqlj_object_type parsesqlj_object_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesqlj_object_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sqlj_object_type _result = new sqlj_object_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sqlj_object_type make_sqlj_object_type(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    sqlj_object_type _result = new sqlj_object_type();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetype_body_elements(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static type_body_elements parsetype_body_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_body_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_body_elements _result = new type_body_elements();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_body_elements make_type_body_elements(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    type_body_elements _result = new type_body_elements();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsemap_order_func_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static map_order_func_declaration parsemap_order_func_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemap_order_func_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    map_order_func_declaration _result = new map_order_func_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static map_order_func_declaration make_map_order_func_declaration(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    map_order_func_declaration _result = new map_order_func_declaration();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesubprog_decl_in_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static subprog_decl_in_type parsesubprog_decl_in_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubprog_decl_in_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subprog_decl_in_type _result = new subprog_decl_in_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subprog_decl_in_type make_subprog_decl_in_type(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    subprog_decl_in_type _result = new subprog_decl_in_type();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseconstructor_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static constructor_declaration parseconstructor_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstructor_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constructor_declaration _result = new constructor_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constructor_declaration make_constructor_declaration(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    constructor_declaration _result = new constructor_declaration();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsemodifier_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static modifier_clause parsemodifier_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodifier_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    modifier_clause _result = new modifier_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static modifier_clause make_modifier_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    modifier_clause _result = new modifier_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesqlj_object_type_attr(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static sqlj_object_type_attr parsesqlj_object_type_attr(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesqlj_object_type_attr(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sqlj_object_type_attr _result = new sqlj_object_type_attr();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sqlj_object_type_attr make_sqlj_object_type_attr(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    sqlj_object_type_attr _result = new sqlj_object_type_attr();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseelement_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static element_spec parseelement_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseelement_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    element_spec _result = new element_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static element_spec make_element_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    element_spec _result = new element_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseelement_spec_options(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static element_spec_options parseelement_spec_options(org.antlr.runtime.tree.Tree tree) {
    if (!canParseelement_spec_options(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    element_spec_options _result = new element_spec_options();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static element_spec_options make_element_spec_options(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    element_spec_options _result = new element_spec_options();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesubprogram_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static subprogram_spec parsesubprogram_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubprogram_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subprogram_spec _result = new subprogram_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subprogram_spec make_subprogram_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    subprogram_spec _result = new subprogram_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseconstructor_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static constructor_spec parseconstructor_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstructor_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constructor_spec _result = new constructor_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constructor_spec make_constructor_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    constructor_spec _result = new constructor_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseconstructor_call_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static constructor_call_mode parseconstructor_call_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstructor_call_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constructor_call_mode _result = new constructor_call_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constructor_call_mode make_constructor_call_mode(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    constructor_call_mode _result = new constructor_call_mode();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsemap_order_function_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static map_order_function_spec parsemap_order_function_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemap_order_function_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    map_order_function_spec _result = new map_order_function_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static map_order_function_spec make_map_order_function_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    map_order_function_spec _result = new map_order_function_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsepragma_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static pragma_clause parsepragma_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_clause _result = new pragma_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_clause make_pragma_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    pragma_clause _result = new pragma_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsepragma_elements(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static pragma_elements parsepragma_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_elements _result = new pragma_elements();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_elements make_pragma_elements(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    pragma_elements _result = new pragma_elements();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsetype_elements_parameter(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static type_elements_parameter parsetype_elements_parameter(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_elements_parameter(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_elements_parameter _result = new type_elements_parameter();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_elements_parameter make_type_elements_parameter(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    type_elements_parameter _result = new type_elements_parameter();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsedrop_sequence(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static drop_sequence parsedrop_sequence(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedrop_sequence(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    drop_sequence _result = new drop_sequence();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static drop_sequence make_drop_sequence(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    drop_sequence _result = new drop_sequence();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsealter_sequence(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static alter_sequence parsealter_sequence(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealter_sequence(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    alter_sequence _result = new alter_sequence();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static alter_sequence make_alter_sequence(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    alter_sequence _result = new alter_sequence();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecreate_sequence(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static create_sequence parsecreate_sequence(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecreate_sequence(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    create_sequence _result = new create_sequence();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static create_sequence make_create_sequence(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    create_sequence _result = new create_sequence();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsesequence_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static sequence_spec parsesequence_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesequence_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sequence_spec _result = new sequence_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sequence_spec make_sequence_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    sequence_spec _result = new sequence_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParseinvoker_rights_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static invoker_rights_clause parseinvoker_rights_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinvoker_rights_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    invoker_rights_clause _result = new invoker_rights_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static invoker_rights_clause make_invoker_rights_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    invoker_rights_clause _result = new invoker_rights_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecompiler_parameters_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NON_DML;
  }

  public static compiler_parameters_clause parsecompiler_parameters_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecompiler_parameters_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    compiler_parameters_clause _result = new compiler_parameters_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static compiler_parameters_clause make_compiler_parameters_clause(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    compiler_parameters_clause _result = new compiler_parameters_clause();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsecall_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LANGUAGE_VK;
  }

  public static call_spec parsecall_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecall_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    call_spec _result = new call_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecall_spec_decl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_call_spec_decl(parsecall_spec_decl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static call_spec make_call_spec(ru.barsopen.plsqlconverter.ast.typed.call_spec_decl call_spec_decl) {
    call_spec _result = new call_spec();
    _result.set_call_spec_decl(call_spec_decl);
    return _result;
  }

  public static boolean canParsecall_spec_decl(org.antlr.runtime.tree.Tree tree) {
    return canParsejava_spec(tree) || canParsec_spec(tree);
  }

  public static call_spec_decl parsecall_spec_decl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecall_spec_decl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsejava_spec(tree)) return parsejava_spec(tree);
    if (canParsec_spec(tree)) return parsec_spec(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsejava_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.JAVA_VK;
  }

  public static java_spec parsejava_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsejava_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    java_spec _result = new java_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CHAR_STRING))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_CHAR_STRING(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static java_spec make_java_spec(org.antlr.runtime.tree.Tree CHAR_STRING) {
    java_spec _result = new java_spec();
    _result.set_CHAR_STRING(CHAR_STRING);
    return _result;
  }

  public static boolean canParsec_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.C_VK;
  }

  public static c_spec parsec_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsec_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    c_spec _result = new c_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (true)) {
      _result.add_contents(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static c_spec make_c_spec(java.util.List<org.antlr.runtime.tree.Tree> contents) {
    c_spec _result = new c_spec();
    if (contents != null) {
      for (org.antlr.runtime.tree.Tree _value: contents) { _result.add_contents(_value); }
    }
    return _result;
  }

  public static boolean canParsec_agent_in_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.AGENT_VK;
  }

  public static c_agent_in_clause parsec_agent_in_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsec_agent_in_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    c_agent_in_clause _result = new c_agent_in_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static c_agent_in_clause make_c_agent_in_clause() {
    c_agent_in_clause _result = new c_agent_in_clause();
    return _result;
  }

  public static boolean canParsec_parameters_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARAMETERS_VK;
  }

  public static c_parameters_clause parsec_parameters_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsec_parameters_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    c_parameters_clause _result = new c_parameters_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static c_parameters_clause make_c_parameters_clause() {
    c_parameters_clause _result = new c_parameters_clause();
    return _result;
  }

  public static boolean canParseparameter(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARAMETER;
  }

  public static parameter parseparameter(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameter _result = new parameter();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseparameter_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameter_name(parseparameter_name(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParseparameter_dir_spec(tree.getChild(_i)))) {
      _result.add_parameter_dir_specs(parseparameter_dir_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i)))) {
      _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsedefault_value_part(tree.getChild(_i)))) {
      _result.set_default_value_part(parsedefault_value_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameter make_parameter(ru.barsopen.plsqlconverter.ast.typed.parameter_name parameter_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.parameter_dir_spec> parameter_dir_specs,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.default_value_part default_value_part) {
    parameter _result = new parameter();
    _result.set_parameter_name(parameter_name);
    if (parameter_dir_specs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.parameter_dir_spec _value: parameter_dir_specs) { _result.add_parameter_dir_specs(_value); }
    }
    _result.set_type_spec(type_spec);
    _result.set_default_value_part(default_value_part);
    return _result;
  }

  public static boolean canParseparameter_dir_spec(org.antlr.runtime.tree.Tree tree) {
    return canParseparameter_in(tree) || canParseparameter_out(tree) || canParseparameter_inout(tree);
  }

  public static parameter_dir_spec parseparameter_dir_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter_dir_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseparameter_in(tree)) return parseparameter_in(tree);
    if (canParseparameter_out(tree)) return parseparameter_out(tree);
    if (canParseparameter_inout(tree)) return parseparameter_inout(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseparameter_in(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_IN;
  }

  public static parameter_in parseparameter_in(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter_in(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameter_in _result = new parameter_in();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameter_in make_parameter_in() {
    parameter_in _result = new parameter_in();
    return _result;
  }

  public static boolean canParseparameter_out(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OUT_VK;
  }

  public static parameter_out parseparameter_out(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter_out(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameter_out _result = new parameter_out();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameter_out make_parameter_out() {
    parameter_out _result = new parameter_out();
    return _result;
  }

  public static boolean canParseparameter_inout(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INOUT_VK;
  }

  public static parameter_inout parseparameter_inout(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter_inout(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameter_inout _result = new parameter_inout();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameter_inout make_parameter_inout() {
    parameter_inout _result = new parameter_inout();
    return _result;
  }

  public static boolean canParsedefault_value_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DEFAULT_VALUE;
  }

  public static default_value_part parsedefault_value_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedefault_value_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    default_value_part _result = new default_value_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static default_value_part make_default_value_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    default_value_part _result = new default_value_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsedeclare_spec(org.antlr.runtime.tree.Tree tree) {
    return canParsevariable_declaration(tree) || canParsesubtype_declaration(tree) || canParsecursor_declaration(tree) || canParseexception_declaration(tree) || canParsepragma_declaration(tree) || canParserecord_declaration(tree) || canParsetable_declaration(tree) || canParsecreate_procedure_body(tree) || canParsecreate_function_body(tree) || canParsecreate_type(tree);
  }

  public static declare_spec parsedeclare_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedeclare_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsevariable_declaration(tree)) return parsevariable_declaration(tree);
    if (canParsesubtype_declaration(tree)) return parsesubtype_declaration(tree);
    if (canParsecursor_declaration(tree)) return parsecursor_declaration(tree);
    if (canParseexception_declaration(tree)) return parseexception_declaration(tree);
    if (canParsepragma_declaration(tree)) return parsepragma_declaration(tree);
    if (canParserecord_declaration(tree)) return parserecord_declaration(tree);
    if (canParsetable_declaration(tree)) return parsetable_declaration(tree);
    if (canParsecreate_procedure_body(tree)) return parsecreate_procedure_body(tree);
    if (canParsecreate_function_body(tree)) return parsecreate_function_body(tree);
    if (canParsecreate_type(tree)) return parsecreate_type(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsevariable_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VARIABLE_DECLARE;
  }

  public static variable_declaration parsevariable_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsevariable_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    variable_declaration _result = new variable_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsevariable_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_variable_name(parsevariable_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONSTANT_VK)) {
      _result.set_CONSTANT_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL)) {
      _result.set_SQL92_RESERVED_NULL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsedefault_value_part(tree.getChild(_i)))) {
      _result.set_default_value_part(parsedefault_value_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static variable_declaration make_variable_declaration(ru.barsopen.plsqlconverter.ast.typed.variable_name variable_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      org.antlr.runtime.tree.Tree CONSTANT_VK,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL,
      ru.barsopen.plsqlconverter.ast.typed.default_value_part default_value_part) {
    variable_declaration _result = new variable_declaration();
    _result.set_variable_name(variable_name);
    _result.set_type_spec(type_spec);
    _result.set_CONSTANT_VK(CONSTANT_VK);
    _result.set_SQL92_RESERVED_NULL(SQL92_RESERVED_NULL);
    _result.set_default_value_part(default_value_part);
    return _result;
  }

  public static boolean canParsesubtype_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SUBTYPE_DECLARE;
  }

  public static subtype_declaration parsesubtype_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubtype_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subtype_declaration _result = new subtype_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL)) {
      _result.set_SQL92_RESERVED_NULL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsesubtype_range(tree.getChild(_i)))) {
      _result.set_subtype_range(parsesubtype_range(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subtype_declaration make_subtype_declaration(ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL,
      ru.barsopen.plsqlconverter.ast.typed.subtype_range subtype_range) {
    subtype_declaration _result = new subtype_declaration();
    _result.set_type_name(type_name);
    _result.set_type_spec(type_spec);
    _result.set_SQL92_RESERVED_NULL(SQL92_RESERVED_NULL);
    _result.set_subtype_range(subtype_range);
    return _result;
  }

  public static boolean canParsesubtype_range(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RANGE_VK;
  }

  public static subtype_range parsesubtype_range(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubtype_range(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subtype_range _result = new subtype_range();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_e1(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_e2(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subtype_range make_subtype_range(ru.barsopen.plsqlconverter.ast.typed.expression e1,
      ru.barsopen.plsqlconverter.ast.typed.expression e2) {
    subtype_range _result = new subtype_range();
    _result.set_e1(e1);
    _result.set_e2(e2);
    return _result;
  }

  public static boolean canParsecursor_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CURSOR_DECLARE;
  }

  public static cursor_declaration parsecursor_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecursor_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cursor_declaration _result = new cursor_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i)))) {
      _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseselect_statement(tree.getChild(_i)))) {
      _result.set_select_statement(parseselect_statement(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseparameters(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_parameters(parseparameters(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cursor_declaration make_cursor_declaration(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.select_statement select_statement,
      ru.barsopen.plsqlconverter.ast.typed.parameters parameters) {
    cursor_declaration _result = new cursor_declaration();
    _result.set_cursor_name(cursor_name);
    _result.set_type_spec(type_spec);
    _result.set_select_statement(select_statement);
    _result.set_parameters(parameters);
    return _result;
  }

  public static boolean canParseexception_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXCEPTION_DECLARE;
  }

  public static exception_declaration parseexception_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexception_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exception_declaration _result = new exception_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexception_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_exception_name(parseexception_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exception_declaration make_exception_declaration(ru.barsopen.plsqlconverter.ast.typed.exception_name exception_name) {
    exception_declaration _result = new exception_declaration();
    _result.set_exception_name(exception_name);
    return _result;
  }

  public static boolean canParsepragma_declaration(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PRAGMA_DECLARE;
  }

  public static pragma_declaration parsepragma_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration _result = new pragma_declaration();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsepragma_declaration_impl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pragma_declaration_impl(parsepragma_declaration_impl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration make_pragma_declaration(ru.barsopen.plsqlconverter.ast.typed.pragma_declaration_impl pragma_declaration_impl) {
    pragma_declaration _result = new pragma_declaration();
    _result.set_pragma_declaration_impl(pragma_declaration_impl);
    return _result;
  }

  public static boolean canParsepragma_declaration_impl(org.antlr.runtime.tree.Tree tree) {
    return canParsepragma_declaration_serially_reusable(tree) || canParsepragma_declaration_autonomous_transaction(tree) || canParsepragma_declaration_exception_init(tree) || canParsepragma_declaration_inline(tree) || canParsepragma_declaration_restrict_references(tree);
  }

  public static pragma_declaration_impl parsepragma_declaration_impl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_impl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsepragma_declaration_serially_reusable(tree)) return parsepragma_declaration_serially_reusable(tree);
    if (canParsepragma_declaration_autonomous_transaction(tree)) return parsepragma_declaration_autonomous_transaction(tree);
    if (canParsepragma_declaration_exception_init(tree)) return parsepragma_declaration_exception_init(tree);
    if (canParsepragma_declaration_inline(tree)) return parsepragma_declaration_inline(tree);
    if (canParsepragma_declaration_restrict_references(tree)) return parsepragma_declaration_restrict_references(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsepragma_declaration_serially_reusable(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SERIALLY_REUSABLE_VK;
  }

  public static pragma_declaration_serially_reusable parsepragma_declaration_serially_reusable(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_serially_reusable(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration_serially_reusable _result = new pragma_declaration_serially_reusable();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration_serially_reusable make_pragma_declaration_serially_reusable() {
    pragma_declaration_serially_reusable _result = new pragma_declaration_serially_reusable();
    return _result;
  }

  public static boolean canParsepragma_declaration_autonomous_transaction(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.AUTONOMOUS_TRANSACTION_VK;
  }

  public static pragma_declaration_autonomous_transaction parsepragma_declaration_autonomous_transaction(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_autonomous_transaction(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration_autonomous_transaction _result = new pragma_declaration_autonomous_transaction();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration_autonomous_transaction make_pragma_declaration_autonomous_transaction() {
    pragma_declaration_autonomous_transaction _result = new pragma_declaration_autonomous_transaction();
    return _result;
  }

  public static boolean canParsepragma_declaration_exception_init(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXCEPTION_INIT_VK;
  }

  public static pragma_declaration_exception_init parsepragma_declaration_exception_init(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_exception_init(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration_exception_init _result = new pragma_declaration_exception_init();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexception_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_exception_name(parseexception_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseconstant(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_constant(parseconstant(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration_exception_init make_pragma_declaration_exception_init(ru.barsopen.plsqlconverter.ast.typed.exception_name exception_name,
      ru.barsopen.plsqlconverter.ast.typed.constant constant) {
    pragma_declaration_exception_init _result = new pragma_declaration_exception_init();
    _result.set_exception_name(exception_name);
    _result.set_constant(constant);
    return _result;
  }

  public static boolean canParsepragma_declaration_inline(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INLINE_VK;
  }

  public static pragma_declaration_inline parsepragma_declaration_inline(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_inline(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration_inline _result = new pragma_declaration_inline();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration_inline make_pragma_declaration_inline(ru.barsopen.plsqlconverter.ast.typed.id id,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    pragma_declaration_inline _result = new pragma_declaration_inline();
    _result.set_id(id);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsepragma_declaration_restrict_references(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RESTRICT_REFERENCES_VK;
  }

  public static pragma_declaration_restrict_references parsepragma_declaration_restrict_references(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepragma_declaration_restrict_references(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pragma_declaration_restrict_references _result = new pragma_declaration_restrict_references();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DEFAULT)) {
      _result.set_SQL92_RESERVED_DEFAULT(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pragma_declaration_restrict_references make_pragma_declaration_restrict_references(org.antlr.runtime.tree.Tree SQL92_RESERVED_DEFAULT,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    pragma_declaration_restrict_references _result = new pragma_declaration_restrict_references();
    _result.set_SQL92_RESERVED_DEFAULT(SQL92_RESERVED_DEFAULT);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParserecord_declaration(org.antlr.runtime.tree.Tree tree) {
    return canParserecord_type_dec(tree) || canParserecord_var_dec(tree);
  }

  public static record_declaration parserecord_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParserecord_type_dec(tree)) return parserecord_type_dec(tree);
    if (canParserecord_var_dec(tree)) return parserecord_var_dec(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParserecord_type_dec(org.antlr.runtime.tree.Tree tree) {
    return canParserecord_type_dec_record(tree) || canParserecord_type_dec_refcursor(tree);
  }

  public static record_type_dec parserecord_type_dec(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_type_dec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParserecord_type_dec_record(tree)) return parserecord_type_dec_record(tree);
    if (canParserecord_type_dec_refcursor(tree)) return parserecord_type_dec_refcursor(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParserecord_type_dec_record(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RECORD_TYPE_DECLARE;
  }

  public static record_type_dec_record parserecord_type_dec_record(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_type_dec_record(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    record_type_dec_record _result = new record_type_dec_record();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsefield_specs(tree.getChild(_i)))) {
      _result.set_field_specs(parsefield_specs(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static record_type_dec_record make_record_type_dec_record(ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      ru.barsopen.plsqlconverter.ast.typed.field_specs field_specs) {
    record_type_dec_record _result = new record_type_dec_record();
    _result.set_type_name(type_name);
    _result.set_field_specs(field_specs);
    return _result;
  }

  public static boolean canParsefield_specs(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FIELDS;
  }

  public static field_specs parsefield_specs(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefield_specs(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    field_specs _result = new field_specs();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParsefield_spec(tree.getChild(_i)))) {
      _result.add_field_specs(parsefield_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static field_specs make_field_specs(java.util.List<ru.barsopen.plsqlconverter.ast.typed.field_spec> field_specs) {
    field_specs _result = new field_specs();
    if (field_specs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.field_spec _value: field_specs) { _result.add_field_specs(_value); }
    }
    return _result;
  }

  public static boolean canParserecord_type_dec_refcursor(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RECORD_TYPE_DECLARE;
  }

  public static record_type_dec_refcursor parserecord_type_dec_refcursor(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_type_dec_refcursor(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    record_type_dec_refcursor _result = new record_type_dec_refcursor();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REF_VK))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_REF_VK(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i)))) {
      _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static record_type_dec_refcursor make_record_type_dec_refcursor(ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      org.antlr.runtime.tree.Tree REF_VK,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    record_type_dec_refcursor _result = new record_type_dec_refcursor();
    _result.set_type_name(type_name);
    _result.set_REF_VK(REF_VK);
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParsefield_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FIELD_SPEC;
  }

  public static field_spec parsefield_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefield_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    field_spec _result = new field_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_column_name(parsecolumn_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i)))) {
      _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL)) {
      _result.set_SQL92_RESERVED_NULL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsedefault_value_part(tree.getChild(_i)))) {
      _result.set_default_value_part(parsedefault_value_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static field_spec make_field_spec(ru.barsopen.plsqlconverter.ast.typed.column_name column_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL,
      ru.barsopen.plsqlconverter.ast.typed.default_value_part default_value_part) {
    field_spec _result = new field_spec();
    _result.set_column_name(column_name);
    _result.set_type_spec(type_spec);
    _result.set_SQL92_RESERVED_NULL(SQL92_RESERVED_NULL);
    _result.set_default_value_part(default_value_part);
    return _result;
  }

  public static boolean canParserecord_var_dec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RECORD_VAR_DECLARE;
  }

  public static record_var_dec parserecord_var_dec(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_var_dec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    record_var_dec _result = new record_var_dec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParserecord_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_record_name(parserecord_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsepercent_type_or_rowtype(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_percent_type_or_rowtype(parsepercent_type_or_rowtype(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static record_var_dec make_record_var_dec(ru.barsopen.plsqlconverter.ast.typed.record_name record_name,
      ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      ru.barsopen.plsqlconverter.ast.typed.percent_type_or_rowtype percent_type_or_rowtype) {
    record_var_dec _result = new record_var_dec();
    _result.set_record_name(record_name);
    _result.set_type_name(type_name);
    _result.set_percent_type_or_rowtype(percent_type_or_rowtype);
    return _result;
  }

  public static boolean canParsepercent_type_or_rowtype(org.antlr.runtime.tree.Tree tree) {
    return canParsepercent_type(tree) || canParsepercent_rowtype(tree);
  }

  public static percent_type_or_rowtype parsepercent_type_or_rowtype(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepercent_type_or_rowtype(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsepercent_type(tree)) return parsepercent_type(tree);
    if (canParsepercent_rowtype(tree)) return parsepercent_rowtype(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsepercent_type(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_TYPE_VK;
  }

  public static percent_type parsepercent_type(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepercent_type(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    percent_type _result = new percent_type();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static percent_type make_percent_type() {
    percent_type _result = new percent_type();
    return _result;
  }

  public static boolean canParsepercent_rowtype(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_ROWTYPE_VK;
  }

  public static percent_rowtype parsepercent_rowtype(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepercent_rowtype(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    percent_rowtype _result = new percent_rowtype();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static percent_rowtype make_percent_rowtype() {
    percent_rowtype _result = new percent_rowtype();
    return _result;
  }

  public static boolean canParsetable_declaration(org.antlr.runtime.tree.Tree tree) {
    return canParsetable_type_dec(tree) || canParsetable_var_dec(tree);
  }

  public static table_declaration parsetable_declaration(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_declaration(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsetable_type_dec(tree)) return parsetable_type_dec(tree);
    if (canParsetable_var_dec(tree)) return parsetable_var_dec(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsetable_type_dec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_TYPE_DECLARE;
  }

  public static table_type_dec parsetable_type_dec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_type_dec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_type_dec _result = new table_type_dec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL)) {
      _result.set_SQL92_RESERVED_NULL(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsetable_type_dec_impl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_type_dec_impl(parsetable_type_dec_impl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_type_dec make_table_type_dec(ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_NULL,
      ru.barsopen.plsqlconverter.ast.typed.table_type_dec_impl table_type_dec_impl) {
    table_type_dec _result = new table_type_dec();
    _result.set_type_name(type_name);
    _result.set_SQL92_RESERVED_NULL(SQL92_RESERVED_NULL);
    _result.set_table_type_dec_impl(table_type_dec_impl);
    return _result;
  }

  public static boolean canParsetable_type_dec_impl(org.antlr.runtime.tree.Tree tree) {
    return canParsetable_type_dec_table_of(tree) || canParsevarray_type_def(tree);
  }

  public static table_type_dec_impl parsetable_type_dec_impl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_type_dec_impl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsetable_type_dec_table_of(tree)) return parsetable_type_dec_table_of(tree);
    if (canParsevarray_type_def(tree)) return parsevarray_type_def(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsetable_type_dec_table_of(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_TABLE;
  }

  public static table_type_dec_table_of parsetable_type_dec_table_of(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_type_dec_table_of(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_type_dec_table_of _result = new table_type_dec_table_of();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsetable_indexed_by_part(tree.getChild(_i)))) {
      _result.set_table_indexed_by_part(parsetable_indexed_by_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_type_dec_table_of make_table_type_dec_table_of(ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.table_indexed_by_part table_indexed_by_part) {
    table_type_dec_table_of _result = new table_type_dec_table_of();
    _result.set_type_spec(type_spec);
    _result.set_table_indexed_by_part(table_indexed_by_part);
    return _result;
  }

  public static boolean canParsetable_indexed_by_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INDEXED_BY;
  }

  public static table_indexed_by_part parsetable_indexed_by_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_indexed_by_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_indexed_by_part _result = new table_indexed_by_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_indexed_by_part make_table_indexed_by_part(ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    table_indexed_by_part _result = new table_indexed_by_part();
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParsevarray_type_def(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VARR_ARRAY_DEF;
  }

  public static varray_type_def parsevarray_type_def(org.antlr.runtime.tree.Tree tree) {
    if (!canParsevarray_type_def(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    varray_type_def _result = new varray_type_def();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static varray_type_def make_varray_type_def(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    varray_type_def _result = new varray_type_def();
    _result.set_expression(expression);
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParsetable_var_dec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_VAR_DECLARE;
  }

  public static table_var_dec parsetable_var_dec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_var_dec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_var_dec _result = new table_var_dec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetable_var_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_var_name(parsetable_var_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_var_dec make_table_var_dec(ru.barsopen.plsqlconverter.ast.typed.table_var_name table_var_name,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    table_var_dec _result = new table_var_dec();
    _result.set_table_var_name(table_var_name);
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParseseq_of_statements(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.STATEMENTS;
  }

  public static seq_of_statements parseseq_of_statements(org.antlr.runtime.tree.Tree tree) {
    if (!canParseseq_of_statements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    seq_of_statements _result = new seq_of_statements();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsestat_or_label(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsestat_or_label(tree.getChild(_i)))) {
      _result.add_stat_or_labels(parsestat_or_label(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static seq_of_statements make_seq_of_statements(java.util.List<ru.barsopen.plsqlconverter.ast.typed.stat_or_label> stat_or_labels) {
    seq_of_statements _result = new seq_of_statements();
    if (stat_or_labels != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.stat_or_label _value: stat_or_labels) { _result.add_stat_or_labels(_value); }
    }
    return _result;
  }

  public static boolean canParsestat_or_label(org.antlr.runtime.tree.Tree tree) {
    return canParsestatement(tree) || canParselabeled_statement(tree);
  }

  public static stat_or_label parsestat_or_label(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestat_or_label(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsestatement(tree)) return parsestatement(tree);
    if (canParselabeled_statement(tree)) return parselabeled_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParselabeled_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LABEL_DECLARE;
  }

  public static labeled_statement parselabeled_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParselabeled_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    labeled_statement _result = new labeled_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParselabel_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_label_name(parselabel_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsestatement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_statement(parsestatement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static labeled_statement make_labeled_statement(ru.barsopen.plsqlconverter.ast.typed.label_name label_name,
      ru.barsopen.plsqlconverter.ast.typed.statement statement) {
    labeled_statement _result = new labeled_statement();
    _result.set_label_name(label_name);
    _result.set_statement(statement);
    return _result;
  }

  public static boolean canParsestatement(org.antlr.runtime.tree.Tree tree) {
    return canParseassignment_statement(tree) || canParsecontinue_statement(tree) || canParseexit_statement(tree) || canParsegoto_statement(tree) || canParseif_statement(tree) || canParseloop_statement(tree) || canParseforall_statement(tree) || canParsenull_statement(tree) || canParseraise_statement(tree) || canParsereturn_statement(tree) || canParsepipe_row_statement(tree) || canParsecase_statement(tree) || canParsesql_statement(tree) || canParseperform_statement(tree) || canParsegeneral_element(tree) || canParsebody(tree) || canParseblock(tree);
  }

  public static statement parsestatement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestatement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseassignment_statement(tree)) return parseassignment_statement(tree);
    if (canParsecontinue_statement(tree)) return parsecontinue_statement(tree);
    if (canParseexit_statement(tree)) return parseexit_statement(tree);
    if (canParsegoto_statement(tree)) return parsegoto_statement(tree);
    if (canParseif_statement(tree)) return parseif_statement(tree);
    if (canParseloop_statement(tree)) return parseloop_statement(tree);
    if (canParseforall_statement(tree)) return parseforall_statement(tree);
    if (canParsenull_statement(tree)) return parsenull_statement(tree);
    if (canParseraise_statement(tree)) return parseraise_statement(tree);
    if (canParsereturn_statement(tree)) return parsereturn_statement(tree);
    if (canParsepipe_row_statement(tree)) return parsepipe_row_statement(tree);
    if (canParsecase_statement(tree)) return parsecase_statement(tree);
    if (canParsesql_statement(tree)) return parsesql_statement(tree);
    if (canParseperform_statement(tree)) return parseperform_statement(tree);
    if (canParsegeneral_element(tree)) return parsegeneral_element(tree);
    if (canParsebody(tree)) return parsebody(tree);
    if (canParseblock(tree)) return parseblock(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseassignment_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASSIGN;
  }

  public static assignment_statement parseassignment_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseassignment_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    assignment_statement _result = new assignment_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseassignment_target(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_assignment_target(parseassignment_target(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static assignment_statement make_assignment_statement(ru.barsopen.plsqlconverter.ast.typed.assignment_target assignment_target,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    assignment_statement _result = new assignment_statement();
    _result.set_assignment_target(assignment_target);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseassignment_target(org.antlr.runtime.tree.Tree tree) {
    return canParsegeneral_element(tree) || canParsehosted_variable_name(tree);
  }

  public static assignment_target parseassignment_target(org.antlr.runtime.tree.Tree tree) {
    if (!canParseassignment_target(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsegeneral_element(tree)) return parsegeneral_element(tree);
    if (canParsehosted_variable_name(tree)) return parsehosted_variable_name(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecontinue_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONTINUE_VK;
  }

  public static continue_statement parsecontinue_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecontinue_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    continue_statement _result = new continue_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParselabel_name(tree.getChild(_i)))) {
      _result.set_label_name(parselabel_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsegeneral_when(tree.getChild(_i)))) {
      _result.set_general_when(parsegeneral_when(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static continue_statement make_continue_statement(ru.barsopen.plsqlconverter.ast.typed.label_name label_name,
      ru.barsopen.plsqlconverter.ast.typed.general_when general_when) {
    continue_statement _result = new continue_statement();
    _result.set_label_name(label_name);
    _result.set_general_when(general_when);
    return _result;
  }

  public static boolean canParsegeneral_when(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN;
  }

  public static general_when parsegeneral_when(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_when(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    general_when _result = new general_when();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static general_when make_general_when(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    general_when _result = new general_when();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseexit_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXIT_VK;
  }

  public static exit_statement parseexit_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexit_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exit_statement _result = new exit_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParselabel_name(tree.getChild(_i)))) {
      _result.set_label_name(parselabel_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsegeneral_when(tree.getChild(_i)))) {
      _result.set_general_when(parsegeneral_when(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exit_statement make_exit_statement(ru.barsopen.plsqlconverter.ast.typed.label_name label_name,
      ru.barsopen.plsqlconverter.ast.typed.general_when general_when) {
    exit_statement _result = new exit_statement();
    _result.set_label_name(label_name);
    _result.set_general_when(general_when);
    return _result;
  }

  public static boolean canParsegoto_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_GOTO;
  }

  public static goto_statement parsegoto_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegoto_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    goto_statement _result = new goto_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParselabel_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_label_name(parselabel_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static goto_statement make_goto_statement(ru.barsopen.plsqlconverter.ast.typed.label_name label_name) {
    goto_statement _result = new goto_statement();
    _result.set_label_name(label_name);
    return _result;
  }

  public static boolean canParseif_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_IF;
  }

  public static if_statement parseif_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseif_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if_statement _result = new if_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParseelsif_part(tree.getChild(_i)))) {
      _result.add_elsif_parts(parseelsif_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseelse_part(tree.getChild(_i)))) {
      _result.set_else_part(parseelse_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static if_statement make_if_statement(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.elsif_part> elsif_parts,
      ru.barsopen.plsqlconverter.ast.typed.else_part else_part) {
    if_statement _result = new if_statement();
    _result.set_expression(expression);
    _result.set_seq_of_statements(seq_of_statements);
    if (elsif_parts != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.elsif_part _value: elsif_parts) { _result.add_elsif_parts(_value); }
    }
    _result.set_else_part(else_part);
    return _result;
  }

  public static boolean canParseelsif_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_ELSIF;
  }

  public static elsif_part parseelsif_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseelsif_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    elsif_part _result = new elsif_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static elsif_part make_elsif_part(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    elsif_part _result = new elsif_part();
    _result.set_expression(expression);
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParseelse_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ELSE;
  }

  public static else_part parseelse_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseelse_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    else_part _result = new else_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static else_part make_else_part(ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    else_part _result = new else_part();
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParseloop_statement(org.antlr.runtime.tree.Tree tree) {
    return canParsewhile_loop(tree) || canParsefor_loop(tree) || canParsesimple_loop(tree);
  }

  public static loop_statement parseloop_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseloop_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsewhile_loop(tree)) return parsewhile_loop(tree);
    if (canParsefor_loop(tree)) return parsefor_loop(tree);
    if (canParsesimple_loop(tree)) return parsesimple_loop(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewhile_loop(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WHILE_LOOP;
  }

  public static while_loop parsewhile_loop(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewhile_loop(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    while_loop _result = new while_loop();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static while_loop make_while_loop(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    while_loop _result = new while_loop();
    _result.set_expression(expression);
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParsefor_loop(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FOR_LOOP;
  }

  public static for_loop parsefor_loop(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_loop(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_loop _result = new for_loop();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_loop_param(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_loop_param(parsecursor_loop_param(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_loop make_for_loop(ru.barsopen.plsqlconverter.ast.typed.cursor_loop_param cursor_loop_param,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    for_loop _result = new for_loop();
    _result.set_cursor_loop_param(cursor_loop_param);
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParsesimple_loop(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LOOP_VK;
  }

  public static simple_loop parsesimple_loop(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesimple_loop(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    simple_loop _result = new simple_loop();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static simple_loop make_simple_loop(ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    simple_loop _result = new simple_loop();
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParsecursor_loop_param(org.antlr.runtime.tree.Tree tree) {
    return canParseindexed_for(tree) || canParsecursor_based_for(tree) || canParseselect_based_for(tree);
  }

  public static cursor_loop_param parsecursor_loop_param(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecursor_loop_param(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseindexed_for(tree)) return parseindexed_for(tree);
    if (canParsecursor_based_for(tree)) return parsecursor_based_for(tree);
    if (canParseselect_based_for(tree)) return parseselect_based_for(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseindexed_for(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INDEXED_FOR;
  }

  public static indexed_for parseindexed_for(org.antlr.runtime.tree.Tree tree) {
    if (!canParseindexed_for(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    indexed_for _result = new indexed_for();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseindex_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_index_name(parseindex_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REVERSE_VK)) {
      _result.set_REVERSE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseindexed_for_bounds(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_indexed_for_bounds(parseindexed_for_bounds(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static indexed_for make_indexed_for(ru.barsopen.plsqlconverter.ast.typed.index_name index_name,
      org.antlr.runtime.tree.Tree REVERSE_VK,
      ru.barsopen.plsqlconverter.ast.typed.indexed_for_bounds indexed_for_bounds) {
    indexed_for _result = new indexed_for();
    _result.set_index_name(index_name);
    _result.set_REVERSE_VK(REVERSE_VK);
    _result.set_indexed_for_bounds(indexed_for_bounds);
    return _result;
  }

  public static boolean canParsecursor_based_for(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CURSOR_BASED_FOR;
  }

  public static cursor_based_for parsecursor_based_for(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecursor_based_for(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cursor_based_for _result = new cursor_based_for();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParserecord_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_record_name(parserecord_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsegeneral_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_general_element(parsegeneral_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cursor_based_for make_cursor_based_for(ru.barsopen.plsqlconverter.ast.typed.record_name record_name,
      ru.barsopen.plsqlconverter.ast.typed.general_element general_element) {
    cursor_based_for _result = new cursor_based_for();
    _result.set_record_name(record_name);
    _result.set_general_element(general_element);
    return _result;
  }

  public static boolean canParseselect_based_for(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_BASED_FOR;
  }

  public static select_based_for parseselect_based_for(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselect_based_for(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    select_based_for _result = new select_based_for();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParserecord_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_record_name(parserecord_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseselect_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_select_statement(parseselect_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static select_based_for make_select_based_for(ru.barsopen.plsqlconverter.ast.typed.record_name record_name,
      ru.barsopen.plsqlconverter.ast.typed.select_statement select_statement) {
    select_based_for _result = new select_based_for();
    _result.set_record_name(record_name);
    _result.set_select_statement(select_statement);
    return _result;
  }

  public static boolean canParseindexed_for_bounds(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SIMPLE_BOUND;
  }

  public static indexed_for_bounds parseindexed_for_bounds(org.antlr.runtime.tree.Tree tree) {
    if (!canParseindexed_for_bounds(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    indexed_for_bounds _result = new indexed_for_bounds();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_b1(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_b2(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static indexed_for_bounds make_indexed_for_bounds(ru.barsopen.plsqlconverter.ast.typed.expression b1,
      ru.barsopen.plsqlconverter.ast.typed.expression b2) {
    indexed_for_bounds _result = new indexed_for_bounds();
    _result.set_b1(b1);
    _result.set_b2(b2);
    return _result;
  }

  public static boolean canParseforall_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FORALL_VK;
  }

  public static forall_statement parseforall_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseforall_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    forall_statement _result = new forall_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseindex_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_index_name(parseindex_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsebounds_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_bounds_clause(parsebounds_clause(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsesql_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_sql_statement(parsesql_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXCEPTIONS_VK)) {
      _result.set_EXCEPTIONS_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static forall_statement make_forall_statement(ru.barsopen.plsqlconverter.ast.typed.index_name index_name,
      ru.barsopen.plsqlconverter.ast.typed.bounds_clause bounds_clause,
      ru.barsopen.plsqlconverter.ast.typed.sql_statement sql_statement,
      org.antlr.runtime.tree.Tree EXCEPTIONS_VK) {
    forall_statement _result = new forall_statement();
    _result.set_index_name(index_name);
    _result.set_bounds_clause(bounds_clause);
    _result.set_sql_statement(sql_statement);
    _result.set_EXCEPTIONS_VK(EXCEPTIONS_VK);
    return _result;
  }

  public static boolean canParsebounds_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SIMPLE_BOUND;
  }

  public static bounds_clause parsebounds_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsebounds_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    bounds_clause _result = new bounds_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static bounds_clause make_bounds_clause() {
    bounds_clause _result = new bounds_clause();
    return _result;
  }

  public static boolean canParsebetween_bound(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_BETWEEN;
  }

  public static between_bound parsebetween_bound(org.antlr.runtime.tree.Tree tree) {
    if (!canParsebetween_bound(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    between_bound _result = new between_bound();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static between_bound make_between_bound() {
    between_bound _result = new between_bound();
    return _result;
  }

  public static boolean canParsenull_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL;
  }

  public static null_statement parsenull_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsenull_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    null_statement _result = new null_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static null_statement make_null_statement() {
    null_statement _result = new null_statement();
    return _result;
  }

  public static boolean canParseraise_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RAISE_VK;
  }

  public static raise_statement parseraise_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseraise_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    raise_statement _result = new raise_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexception_name(tree.getChild(_i)))) {
      _result.set_exception_name(parseexception_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static raise_statement make_raise_statement(ru.barsopen.plsqlconverter.ast.typed.exception_name exception_name) {
    raise_statement _result = new raise_statement();
    _result.set_exception_name(exception_name);
    return _result;
  }

  public static boolean canParsereturn_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RETURN_VK;
  }

  public static return_statement parsereturn_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereturn_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    return_statement _result = new return_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static return_statement make_return_statement(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    return_statement _result = new return_statement();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsebody(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BODY;
  }

  public static body parsebody(org.antlr.runtime.tree.Tree tree) {
    if (!canParsebody(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    body _result = new body();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParselabel_name(tree.getChild(_i)))) {
      _result.set_label_name(parselabel_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexception_clause(tree.getChild(_i)))) {
      _result.set_exception_clause(parseexception_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static body make_body(ru.barsopen.plsqlconverter.ast.typed.label_name label_name,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements,
      ru.barsopen.plsqlconverter.ast.typed.exception_clause exception_clause) {
    body _result = new body();
    _result.set_label_name(label_name);
    _result.set_seq_of_statements(seq_of_statements);
    _result.set_exception_clause(exception_clause);
    return _result;
  }

  public static boolean canParseexception_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_EXCEPTION;
  }

  public static exception_clause parseexception_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexception_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exception_clause _result = new exception_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexception_handler(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseexception_handler(tree.getChild(_i)))) {
      _result.add_exception_handlers(parseexception_handler(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exception_clause make_exception_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.exception_handler> exception_handlers) {
    exception_clause _result = new exception_clause();
    if (exception_handlers != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.exception_handler _value: exception_handlers) { _result.add_exception_handlers(_value); }
    }
    return _result;
  }

  public static boolean canParseexception_handler(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN;
  }

  public static exception_handler parseexception_handler(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexception_handler(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exception_handler _result = new exception_handler();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexception_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseexception_name(tree.getChild(_i)))) {
      _result.add_exception_names(parseexception_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseseq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_seq_of_statements(parseseq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exception_handler make_exception_handler(java.util.List<ru.barsopen.plsqlconverter.ast.typed.exception_name> exception_names,
      ru.barsopen.plsqlconverter.ast.typed.seq_of_statements seq_of_statements) {
    exception_handler _result = new exception_handler();
    if (exception_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.exception_name _value: exception_names) { _result.add_exception_names(_value); }
    }
    _result.set_seq_of_statements(seq_of_statements);
    return _result;
  }

  public static boolean canParseblock(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BLOCK;
  }

  public static block parseblock(org.antlr.runtime.tree.Tree tree) {
    if (!canParseblock(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    block _result = new block();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParsedeclare_spec(tree.getChild(_i)))) {
      _result.add_declare_specs(parsedeclare_spec(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsebody(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_body(parsebody(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static block make_block(java.util.List<ru.barsopen.plsqlconverter.ast.typed.declare_spec> declare_specs,
      ru.barsopen.plsqlconverter.ast.typed.body body) {
    block _result = new block();
    if (declare_specs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.declare_spec _value: declare_specs) { _result.add_declare_specs(_value); }
    }
    _result.set_body(body);
    return _result;
  }

  public static boolean canParseperform_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PGSQL_PERFORM;
  }

  public static perform_statement parseperform_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseperform_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    perform_statement _result = new perform_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_element_or_dml_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_inner(parsegeneral_element_or_dml_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static perform_statement make_perform_statement(ru.barsopen.plsqlconverter.ast.typed.general_element_or_dml_statement inner) {
    perform_statement _result = new perform_statement();
    _result.set_inner(inner);
    return _result;
  }

  public static boolean canParsegeneral_element_or_dml_statement(org.antlr.runtime.tree.Tree tree) {
    return canParsegeneral_element(tree) || canParsedata_manipulation_language_statements(tree);
  }

  public static general_element_or_dml_statement parsegeneral_element_or_dml_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_element_or_dml_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsegeneral_element(tree)) return parsegeneral_element(tree);
    if (canParsedata_manipulation_language_statements(tree)) return parsedata_manipulation_language_statements(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsesql_statement(org.antlr.runtime.tree.Tree tree) {
    return canParseexecute_immediate(tree) || canParsedata_manipulation_language_statements(tree) || canParsecursor_manipulation_statements(tree) || canParsetransaction_control_statements(tree);
  }

  public static sql_statement parsesql_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesql_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexecute_immediate(tree)) return parseexecute_immediate(tree);
    if (canParsedata_manipulation_language_statements(tree)) return parsedata_manipulation_language_statements(tree);
    if (canParsecursor_manipulation_statements(tree)) return parsecursor_manipulation_statements(tree);
    if (canParsetransaction_control_statements(tree)) return parsetransaction_control_statements(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseexecute_immediate(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXECUTE_VK;
  }

  public static execute_immediate parseexecute_immediate(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexecute_immediate(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    execute_immediate _result = new execute_immediate();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseinto_clause(tree.getChild(_i)))) {
      _result.set_into_clause(parseinto_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseusing_clause(tree.getChild(_i)))) {
      _result.set_using_clause(parseusing_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsedynamic_returning_clause(tree.getChild(_i)))) {
      _result.set_dynamic_returning_clause(parsedynamic_returning_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static execute_immediate make_execute_immediate(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.into_clause into_clause,
      ru.barsopen.plsqlconverter.ast.typed.using_clause using_clause,
      ru.barsopen.plsqlconverter.ast.typed.dynamic_returning_clause dynamic_returning_clause) {
    execute_immediate _result = new execute_immediate();
    _result.set_expression(expression);
    _result.set_into_clause(into_clause);
    _result.set_using_clause(using_clause);
    _result.set_dynamic_returning_clause(dynamic_returning_clause);
    return _result;
  }

  public static boolean canParsedynamic_returning_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DYNAMIC_RETURN;
  }

  public static dynamic_returning_clause parsedynamic_returning_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedynamic_returning_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    dynamic_returning_clause _result = new dynamic_returning_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinto_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_into_clause(parseinto_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static dynamic_returning_clause make_dynamic_returning_clause(ru.barsopen.plsqlconverter.ast.typed.into_clause into_clause) {
    dynamic_returning_clause _result = new dynamic_returning_clause();
    _result.set_into_clause(into_clause);
    return _result;
  }

  public static boolean canParsedata_manipulation_language_statements(org.antlr.runtime.tree.Tree tree) {
    return canParsemerge_statement(tree) || canParselock_table_statement(tree) || canParseselect_statement(tree) || canParseupdate_statement(tree) || canParsedelete_statement(tree) || canParseinsert_statement(tree);
  }

  public static data_manipulation_language_statements parsedata_manipulation_language_statements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedata_manipulation_language_statements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsemerge_statement(tree)) return parsemerge_statement(tree);
    if (canParselock_table_statement(tree)) return parselock_table_statement(tree);
    if (canParseselect_statement(tree)) return parseselect_statement(tree);
    if (canParseupdate_statement(tree)) return parseupdate_statement(tree);
    if (canParsedelete_statement(tree)) return parsedelete_statement(tree);
    if (canParseinsert_statement(tree)) return parseinsert_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseselect_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_STATEMENT;
  }

  public static select_statement parseselect_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselect_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    select_statement _result = new select_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsesubquery_factoring_clause(tree.getChild(_i)))) {
      _result.set_subquery_factoring_clause(parsesubquery_factoring_clause(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsesubquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery(parsesubquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseorder_by_clause(tree.getChild(_i)))) {
      _result.set_order_by_clause(parseorder_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsefor_update_clause(tree.getChild(_i)))) {
      _result.set_for_update_clause(parsefor_update_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static select_statement make_select_statement(ru.barsopen.plsqlconverter.ast.typed.subquery_factoring_clause subquery_factoring_clause,
      ru.barsopen.plsqlconverter.ast.typed.subquery subquery,
      ru.barsopen.plsqlconverter.ast.typed.order_by_clause order_by_clause,
      ru.barsopen.plsqlconverter.ast.typed.for_update_clause for_update_clause) {
    select_statement _result = new select_statement();
    _result.set_subquery_factoring_clause(subquery_factoring_clause);
    _result.set_subquery(subquery);
    _result.set_order_by_clause(order_by_clause);
    _result.set_for_update_clause(for_update_clause);
    return _result;
  }

  public static boolean canParsesubquery_factoring_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WITH;
  }

  public static subquery_factoring_clause parsesubquery_factoring_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_factoring_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery_factoring_clause _result = new subquery_factoring_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RECURSIVE_VK)) {
      _result.set_RECURSIVE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsefactoring_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsefactoring_element(tree.getChild(_i)))) {
      _result.add_factoring_elements(parsefactoring_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery_factoring_clause make_subquery_factoring_clause(org.antlr.runtime.tree.Tree RECURSIVE_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.factoring_element> factoring_elements) {
    subquery_factoring_clause _result = new subquery_factoring_clause();
    _result.set_RECURSIVE_VK(RECURSIVE_VK);
    if (factoring_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.factoring_element _value: factoring_elements) { _result.add_factoring_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsefactoring_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FACTORING;
  }

  public static factoring_element parsefactoring_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefactoring_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    factoring_element _result = new factoring_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsequery_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_query_name(parsequery_name(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsesubquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery(parsesubquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseorder_by_clause(tree.getChild(_i)))) {
      _result.set_order_by_clause(parseorder_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static factoring_element make_factoring_element(ru.barsopen.plsqlconverter.ast.typed.query_name query_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names,
      ru.barsopen.plsqlconverter.ast.typed.subquery subquery,
      ru.barsopen.plsqlconverter.ast.typed.order_by_clause order_by_clause) {
    factoring_element _result = new factoring_element();
    _result.set_query_name(query_name);
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    _result.set_subquery(subquery);
    _result.set_order_by_clause(order_by_clause);
    return _result;
  }

  public static boolean canParsesubquery(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SUBQUERY;
  }

  public static subquery parsesubquery(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery _result = new subquery();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsesubquery_basic_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery_basic_elements(parsesubquery_basic_elements(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsesubquery_operation_part(tree.getChild(_i)))) {
      _result.add_subquery_operation_parts(parsesubquery_operation_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery make_subquery(ru.barsopen.plsqlconverter.ast.typed.subquery_basic_elements subquery_basic_elements,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.subquery_operation_part> subquery_operation_parts) {
    subquery _result = new subquery();
    _result.set_subquery_basic_elements(subquery_basic_elements);
    if (subquery_operation_parts != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.subquery_operation_part _value: subquery_operation_parts) { _result.add_subquery_operation_parts(_value); }
    }
    return _result;
  }

  public static boolean canParsesubquery_operation_part(org.antlr.runtime.tree.Tree tree) {
    return canParsesubquery_operation_union(tree) || canParsesubquery_operation_intersect(tree) || canParsesubquery_operation_minus(tree);
  }

  public static subquery_operation_part parsesubquery_operation_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_operation_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsesubquery_operation_union(tree)) return parsesubquery_operation_union(tree);
    if (canParsesubquery_operation_intersect(tree)) return parsesubquery_operation_intersect(tree);
    if (canParsesubquery_operation_minus(tree)) return parsesubquery_operation_minus(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsesubquery_operation_union(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_UNION;
  }

  public static subquery_operation_union parsesubquery_operation_union(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_operation_union(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery_operation_union _result = new subquery_operation_union();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsesubquery_basic_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery_basic_elements(parsesubquery_basic_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery_operation_union make_subquery_operation_union(org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      ru.barsopen.plsqlconverter.ast.typed.subquery_basic_elements subquery_basic_elements) {
    subquery_operation_union _result = new subquery_operation_union();
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_subquery_basic_elements(subquery_basic_elements);
    return _result;
  }

  public static boolean canParsesubquery_operation_intersect(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTERSECT;
  }

  public static subquery_operation_intersect parsesubquery_operation_intersect(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_operation_intersect(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery_operation_intersect _result = new subquery_operation_intersect();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsesubquery_basic_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery_basic_elements(parsesubquery_basic_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery_operation_intersect make_subquery_operation_intersect(org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      ru.barsopen.plsqlconverter.ast.typed.subquery_basic_elements subquery_basic_elements) {
    subquery_operation_intersect _result = new subquery_operation_intersect();
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_subquery_basic_elements(subquery_basic_elements);
    return _result;
  }

  public static boolean canParsesubquery_operation_minus(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_MINUS;
  }

  public static subquery_operation_minus parsesubquery_operation_minus(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_operation_minus(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery_operation_minus _result = new subquery_operation_minus();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsesubquery_basic_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery_basic_elements(parsesubquery_basic_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery_operation_minus make_subquery_operation_minus(org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      ru.barsopen.plsqlconverter.ast.typed.subquery_basic_elements subquery_basic_elements) {
    subquery_operation_minus _result = new subquery_operation_minus();
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_subquery_basic_elements(subquery_basic_elements);
    return _result;
  }

  public static boolean canParsesubquery_basic_elements(org.antlr.runtime.tree.Tree tree) {
    return canParsequery_block(tree) || canParsesubquery(tree);
  }

  public static subquery_basic_elements parsesubquery_basic_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_basic_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsequery_block(tree)) return parsequery_block(tree);
    if (canParsesubquery(tree)) return parsesubquery(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsequery_block(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_SELECT;
  }

  public static query_block parsequery_block(org.antlr.runtime.tree.Tree tree) {
    if (!canParsequery_block(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    query_block _result = new query_block();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsefrom_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_from_clause(parsefrom_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DISTINCT)) {
      _result.set_SQL92_RESERVED_DISTINCT(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_UNIQUE)) {
      _result.set_SQL92_RESERVED_UNIQUE(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsequery_block_projection(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_query_block_projection(parsequery_block_projection(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseinto_clause(tree.getChild(_i)))) {
      _result.set_into_clause(parseinto_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i)))) {
      _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsehierarchical_query_clause(tree.getChild(_i)))) {
      _result.set_hierarchical_query_clause(parsehierarchical_query_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsegroup_by_clause(tree.getChild(_i)))) {
      _result.set_group_by_clause(parsegroup_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsemodel_clause(tree.getChild(_i)))) {
      _result.set_model_clause(parsemodel_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static query_block make_query_block(ru.barsopen.plsqlconverter.ast.typed.from_clause from_clause,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      ru.barsopen.plsqlconverter.ast.typed.query_block_projection query_block_projection,
      ru.barsopen.plsqlconverter.ast.typed.into_clause into_clause,
      ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause,
      ru.barsopen.plsqlconverter.ast.typed.hierarchical_query_clause hierarchical_query_clause,
      ru.barsopen.plsqlconverter.ast.typed.group_by_clause group_by_clause,
      ru.barsopen.plsqlconverter.ast.typed.model_clause model_clause) {
    query_block _result = new query_block();
    _result.set_from_clause(from_clause);
    _result.set_SQL92_RESERVED_DISTINCT(SQL92_RESERVED_DISTINCT);
    _result.set_SQL92_RESERVED_UNIQUE(SQL92_RESERVED_UNIQUE);
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_query_block_projection(query_block_projection);
    _result.set_into_clause(into_clause);
    _result.set_where_clause(where_clause);
    _result.set_hierarchical_query_clause(hierarchical_query_clause);
    _result.set_group_by_clause(group_by_clause);
    _result.set_model_clause(model_clause);
    return _result;
  }

  public static boolean canParsequery_block_projection(org.antlr.runtime.tree.Tree tree) {
    return canParseprojection_asterisk(tree) || canParseprojection_list(tree);
  }

  public static query_block_projection parsequery_block_projection(org.antlr.runtime.tree.Tree tree) {
    if (!canParsequery_block_projection(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseprojection_asterisk(tree)) return parseprojection_asterisk(tree);
    if (canParseprojection_list(tree)) return parseprojection_list(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseprojection_asterisk(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASTERISK;
  }

  public static projection_asterisk parseprojection_asterisk(org.antlr.runtime.tree.Tree tree) {
    if (!canParseprojection_asterisk(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    projection_asterisk _result = new projection_asterisk();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static projection_asterisk make_projection_asterisk() {
    projection_asterisk _result = new projection_asterisk();
    return _result;
  }

  public static boolean canParseprojection_list(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_LIST;
  }

  public static projection_list parseprojection_list(org.antlr.runtime.tree.Tree tree) {
    if (!canParseprojection_list(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    projection_list _result = new projection_list();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseselected_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseselected_element(tree.getChild(_i)))) {
      _result.add_selected_elements(parseselected_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static projection_list make_projection_list(java.util.List<ru.barsopen.plsqlconverter.ast.typed.selected_element> selected_elements) {
    projection_list _result = new projection_list();
    if (selected_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.selected_element _value: selected_elements) { _result.add_selected_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseselected_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_ITEM;
  }

  public static selected_element parseselected_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselected_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    selected_element _result = new selected_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static selected_element make_selected_element(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.alias alias) {
    selected_element _result = new selected_element();
    _result.set_expression(expression);
    _result.set_alias(alias);
    return _result;
  }

  public static boolean canParsefrom_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FROM;
  }

  public static from_clause parsefrom_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefrom_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    from_clause _result = new from_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetable_ref(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsetable_ref(tree.getChild(_i)))) {
      _result.add_table_refs(parsetable_ref(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static from_clause make_from_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.table_ref> table_refs) {
    from_clause _result = new from_clause();
    if (table_refs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.table_ref _value: table_refs) { _result.add_table_refs(_value); }
    }
    return _result;
  }

  public static boolean canParsetable_ref(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_REF;
  }

  public static table_ref parsetable_ref(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_ref(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_ref _result = new table_ref();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetable_ref_aux(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_ref_aux(parsetable_ref_aux(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsejoin_clause(tree.getChild(_i)))) {
      _result.add_join_clauses(parsejoin_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_ref make_table_ref(ru.barsopen.plsqlconverter.ast.typed.table_ref_aux table_ref_aux,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.join_clause> join_clauses) {
    table_ref _result = new table_ref();
    _result.set_table_ref_aux(table_ref_aux);
    if (join_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.join_clause _value: join_clauses) { _result.add_join_clauses(_value); }
    }
    return _result;
  }

  public static boolean canParsetable_ref_aux(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_REF_ELEMENT;
  }

  public static table_ref_aux parsetable_ref_aux(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_ref_aux(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_ref_aux _result = new table_ref_aux();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsedml_table_expression_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_dml_table_expression_clause(parsedml_table_expression_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ONLY_VK)) {
      _result.set_ONLY_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsepivot_clause(tree.getChild(_i)))) {
      _result.set_pivot_clause(parsepivot_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseunpivot_clause(tree.getChild(_i)))) {
      _result.set_unpivot_clause(parseunpivot_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParseflashback_query_clause(tree.getChild(_i)))) {
      _result.add_flashback_query_clauses(parseflashback_query_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_ref_aux make_table_ref_aux(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.dml_table_expression_clause dml_table_expression_clause,
      org.antlr.runtime.tree.Tree ONLY_VK,
      ru.barsopen.plsqlconverter.ast.typed.pivot_clause pivot_clause,
      ru.barsopen.plsqlconverter.ast.typed.unpivot_clause unpivot_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.flashback_query_clause> flashback_query_clauses) {
    table_ref_aux _result = new table_ref_aux();
    _result.set_alias(alias);
    _result.set_dml_table_expression_clause(dml_table_expression_clause);
    _result.set_ONLY_VK(ONLY_VK);
    _result.set_pivot_clause(pivot_clause);
    _result.set_unpivot_clause(unpivot_clause);
    if (flashback_query_clauses != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.flashback_query_clause _value: flashback_query_clauses) { _result.add_flashback_query_clauses(_value); }
    }
    return _result;
  }

  public static boolean canParsejoin_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.JOIN_DEF;
  }

  public static join_clause parsejoin_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsejoin_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    join_clause _result = new join_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsequery_partition_clause(tree.getChild(_i)))) {
      _result.set_qpc1(parsequery_partition_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CROSS_VK)) {
      _result.set_CROSS_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NATURAL_VK)) {
      _result.set_NATURAL_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INNER_VK)) {
      _result.set_INNER_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FULL_VK)) {
      _result.set_FULL_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LEFT_VK)) {
      _result.set_LEFT_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RIGHT_VK)) {
      _result.set_RIGHT_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsetable_ref_aux(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_ref_aux(parsetable_ref_aux(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsequery_partition_clause(tree.getChild(_i)))) {
      _result.set_qpc2(parsequery_partition_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsejoin_on_part(tree.getChild(_i)))) {
      _result.set_join_on_part(parsejoin_on_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsejoin_using_part(tree.getChild(_i)))) {
      _result.set_join_using_part(parsejoin_using_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static join_clause make_join_clause(ru.barsopen.plsqlconverter.ast.typed.query_partition_clause qpc1,
      org.antlr.runtime.tree.Tree CROSS_VK,
      org.antlr.runtime.tree.Tree NATURAL_VK,
      org.antlr.runtime.tree.Tree INNER_VK,
      org.antlr.runtime.tree.Tree FULL_VK,
      org.antlr.runtime.tree.Tree LEFT_VK,
      org.antlr.runtime.tree.Tree RIGHT_VK,
      ru.barsopen.plsqlconverter.ast.typed.table_ref_aux table_ref_aux,
      ru.barsopen.plsqlconverter.ast.typed.query_partition_clause qpc2,
      ru.barsopen.plsqlconverter.ast.typed.join_on_part join_on_part,
      ru.barsopen.plsqlconverter.ast.typed.join_using_part join_using_part) {
    join_clause _result = new join_clause();
    _result.set_qpc1(qpc1);
    _result.set_CROSS_VK(CROSS_VK);
    _result.set_NATURAL_VK(NATURAL_VK);
    _result.set_INNER_VK(INNER_VK);
    _result.set_FULL_VK(FULL_VK);
    _result.set_LEFT_VK(LEFT_VK);
    _result.set_RIGHT_VK(RIGHT_VK);
    _result.set_table_ref_aux(table_ref_aux);
    _result.set_qpc2(qpc2);
    _result.set_join_on_part(join_on_part);
    _result.set_join_using_part(join_using_part);
    return _result;
  }

  public static boolean canParsejoin_on_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ON;
  }

  public static join_on_part parsejoin_on_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsejoin_on_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    join_on_part _result = new join_on_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static join_on_part make_join_on_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    join_on_part _result = new join_on_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsejoin_using_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_USING;
  }

  public static join_using_part parsejoin_using_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsejoin_using_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    join_using_part _result = new join_using_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static join_using_part make_join_using_part(java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names) {
    join_using_part _result = new join_using_part();
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    return _result;
  }

  public static boolean canParsequery_partition_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARTITION_VK;
  }

  public static query_partition_clause parsequery_partition_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsequery_partition_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    query_partition_clause _result = new query_partition_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsequery_partition_clause_impl(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_query_partition_clause_impl(parsequery_partition_clause_impl(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static query_partition_clause make_query_partition_clause(ru.barsopen.plsqlconverter.ast.typed.query_partition_clause_impl query_partition_clause_impl) {
    query_partition_clause _result = new query_partition_clause();
    _result.set_query_partition_clause_impl(query_partition_clause_impl);
    return _result;
  }

  public static boolean canParsequery_partition_clause_impl(org.antlr.runtime.tree.Tree tree) {
    return canParsesubquery(tree) || canParseexpression_list(tree);
  }

  public static query_partition_clause_impl parsequery_partition_clause_impl(org.antlr.runtime.tree.Tree tree) {
    if (!canParsequery_partition_clause_impl(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsesubquery(tree)) return parsesubquery(tree);
    if (canParseexpression_list(tree)) return parseexpression_list(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseflashback_query_clause(org.antlr.runtime.tree.Tree tree) {
    return canParseflashback_query_clause_versions(tree) || canParseflashback_query_clause_as(tree);
  }

  public static flashback_query_clause parseflashback_query_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseflashback_query_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseflashback_query_clause_versions(tree)) return parseflashback_query_clause_versions(tree);
    if (canParseflashback_query_clause_as(tree)) return parseflashback_query_clause_as(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseflashback_query_clause_versions(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VERSIONS_VK;
  }

  public static flashback_query_clause_versions parseflashback_query_clause_versions(org.antlr.runtime.tree.Tree tree) {
    if (!canParseflashback_query_clause_versions(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    flashback_query_clause_versions _result = new flashback_query_clause_versions();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SCN_VK)) {
      _result.set_SCN_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMESTAMP_VK)) {
      _result.set_TIMESTAMP_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static flashback_query_clause_versions make_flashback_query_clause_versions(org.antlr.runtime.tree.Tree SCN_VK,
      org.antlr.runtime.tree.Tree TIMESTAMP_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    flashback_query_clause_versions _result = new flashback_query_clause_versions();
    _result.set_SCN_VK(SCN_VK);
    _result.set_TIMESTAMP_VK(TIMESTAMP_VK);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseflashback_query_clause_as(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_AS;
  }

  public static flashback_query_clause_as parseflashback_query_clause_as(org.antlr.runtime.tree.Tree tree) {
    if (!canParseflashback_query_clause_as(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    flashback_query_clause_as _result = new flashback_query_clause_as();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SCN_VK)) {
      _result.set_SCN_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMESTAMP_VK)) {
      _result.set_TIMESTAMP_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static flashback_query_clause_as make_flashback_query_clause_as(org.antlr.runtime.tree.Tree SCN_VK,
      org.antlr.runtime.tree.Tree TIMESTAMP_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    flashback_query_clause_as _result = new flashback_query_clause_as();
    _result.set_SCN_VK(SCN_VK);
    _result.set_TIMESTAMP_VK(TIMESTAMP_VK);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsepivot_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIVOT_VK;
  }

  public static pivot_clause parsepivot_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepivot_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pivot_clause _result = new pivot_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XML_VK)) {
      _result.set_XML_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsepivot_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsepivot_element(tree.getChild(_i)))) {
      _result.add_pivot_elements(parsepivot_element(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsepivot_for_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pivot_for_clause(parsepivot_for_clause(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsepivot_in_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pivot_in_clause(parsepivot_in_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pivot_clause make_pivot_clause(org.antlr.runtime.tree.Tree XML_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.pivot_element> pivot_elements,
      ru.barsopen.plsqlconverter.ast.typed.pivot_for_clause pivot_for_clause,
      ru.barsopen.plsqlconverter.ast.typed.pivot_in_clause pivot_in_clause) {
    pivot_clause _result = new pivot_clause();
    _result.set_XML_VK(XML_VK);
    if (pivot_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.pivot_element _value: pivot_elements) { _result.add_pivot_elements(_value); }
    }
    _result.set_pivot_for_clause(pivot_for_clause);
    _result.set_pivot_in_clause(pivot_in_clause);
    return _result;
  }

  public static boolean canParsepivot_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIVOT_ELEMENT;
  }

  public static pivot_element parsepivot_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepivot_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pivot_element _result = new pivot_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pivot_element make_pivot_element(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    pivot_element _result = new pivot_element();
    _result.set_alias(alias);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsepivot_for_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FOR;
  }

  public static pivot_for_clause parsepivot_for_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepivot_for_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pivot_for_clause _result = new pivot_for_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pivot_for_clause make_pivot_for_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names) {
    pivot_for_clause _result = new pivot_for_clause();
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    return _result;
  }

  public static boolean canParsepivot_in_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_IN;
  }

  public static pivot_in_clause parsepivot_in_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepivot_in_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pivot_in_clause _result = new pivot_in_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pivot_in_clause make_pivot_in_clause() {
    pivot_in_clause _result = new pivot_in_clause();
    return _result;
  }

  public static boolean canParsepivot_in_clause_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIVOT_IN_ELEMENT;
  }

  public static pivot_in_clause_element parsepivot_in_clause_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepivot_in_clause_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pivot_in_clause_element _result = new pivot_in_clause_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pivot_in_clause_element make_pivot_in_clause_element() {
    pivot_in_clause_element _result = new pivot_in_clause_element();
    return _result;
  }

  public static boolean canParseunpivot_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNPIVOT_VK;
  }

  public static unpivot_clause parseunpivot_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseunpivot_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    unpivot_clause _result = new unpivot_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static unpivot_clause make_unpivot_clause() {
    unpivot_clause _result = new unpivot_clause();
    return _result;
  }

  public static boolean canParseunpivot_in_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNPIVOT_VK;
  }

  public static unpivot_in_clause parseunpivot_in_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseunpivot_in_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    unpivot_in_clause _result = new unpivot_in_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static unpivot_in_clause make_unpivot_in_clause() {
    unpivot_in_clause _result = new unpivot_in_clause();
    return _result;
  }

  public static boolean canParseunpivot_in_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNPIVOT_VK;
  }

  public static unpivot_in_element parseunpivot_in_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParseunpivot_in_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    unpivot_in_element _result = new unpivot_in_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static unpivot_in_element make_unpivot_in_element() {
    unpivot_in_element _result = new unpivot_in_element();
    return _result;
  }

  public static boolean canParsehierarchical_query_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.HIERARCHICAL;
  }

  public static hierarchical_query_clause parsehierarchical_query_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsehierarchical_query_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    hierarchical_query_clause _result = new hierarchical_query_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsestart_part(tree.getChild(_i)))) {
      _result.set_start_part(parsestart_part(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsehierarchical_query_clause_connect(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_hierarchical_query_clause_connect(parsehierarchical_query_clause_connect(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static hierarchical_query_clause make_hierarchical_query_clause(ru.barsopen.plsqlconverter.ast.typed.start_part start_part,
      ru.barsopen.plsqlconverter.ast.typed.hierarchical_query_clause_connect hierarchical_query_clause_connect) {
    hierarchical_query_clause _result = new hierarchical_query_clause();
    _result.set_start_part(start_part);
    _result.set_hierarchical_query_clause_connect(hierarchical_query_clause_connect);
    return _result;
  }

  public static boolean canParsehierarchical_query_clause_connect(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_CONNECT;
  }

  public static hierarchical_query_clause_connect parsehierarchical_query_clause_connect(org.antlr.runtime.tree.Tree tree) {
    if (!canParsehierarchical_query_clause_connect(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    hierarchical_query_clause_connect _result = new hierarchical_query_clause_connect();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOCYCLE_VK)) {
      _result.set_NOCYCLE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static hierarchical_query_clause_connect make_hierarchical_query_clause_connect(org.antlr.runtime.tree.Tree NOCYCLE_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    hierarchical_query_clause_connect _result = new hierarchical_query_clause_connect();
    _result.set_NOCYCLE_VK(NOCYCLE_VK);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsestart_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_START;
  }

  public static start_part parsestart_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestart_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    start_part _result = new start_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static start_part make_start_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    start_part _result = new start_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsegroup_by_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_GROUP;
  }

  public static group_by_clause parsegroup_by_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegroup_by_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    group_by_clause _result = new group_by_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegroup_by_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegroup_by_element(tree.getChild(_i)))) {
      _result.add_group_by_elements(parsegroup_by_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsehaving_clause(tree.getChild(_i)))) {
      _result.set_having_clause(parsehaving_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static group_by_clause make_group_by_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.group_by_element> group_by_elements,
      ru.barsopen.plsqlconverter.ast.typed.having_clause having_clause) {
    group_by_clause _result = new group_by_clause();
    if (group_by_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.group_by_element _value: group_by_elements) { _result.add_group_by_elements(_value); }
    }
    _result.set_having_clause(having_clause);
    return _result;
  }

  public static boolean canParsegroup_by_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.GROUP_BY_ELEMENT;
  }

  public static group_by_element parsegroup_by_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegroup_by_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    group_by_element _result = new group_by_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegroup_by_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_group_by_elements(parsegroup_by_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static group_by_element make_group_by_element(ru.barsopen.plsqlconverter.ast.typed.group_by_elements group_by_elements) {
    group_by_element _result = new group_by_element();
    _result.set_group_by_elements(group_by_elements);
    return _result;
  }

  public static boolean canParsegroup_by_elements(org.antlr.runtime.tree.Tree tree) {
    return canParsegroup_by_elements_grouping(tree) || canParsegrouping_element(tree);
  }

  public static group_by_elements parsegroup_by_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegroup_by_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsegroup_by_elements_grouping(tree)) return parsegroup_by_elements_grouping(tree);
    if (canParsegrouping_element(tree)) return parsegrouping_element(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsegroup_by_elements_grouping(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.GROUPING_VK;
  }

  public static group_by_elements_grouping parsegroup_by_elements_grouping(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegroup_by_elements_grouping(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    group_by_elements_grouping _result = new group_by_elements_grouping();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegroupin_set(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegroupin_set(tree.getChild(_i)))) {
      _result.add_groupin_sets(parsegroupin_set(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static group_by_elements_grouping make_group_by_elements_grouping(java.util.List<ru.barsopen.plsqlconverter.ast.typed.groupin_set> groupin_sets) {
    group_by_elements_grouping _result = new group_by_elements_grouping();
    if (groupin_sets != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.groupin_set _value: groupin_sets) { _result.add_groupin_sets(_value); }
    }
    return _result;
  }

  public static boolean canParsegroupin_set(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.GROUPIN_SET;
  }

  public static groupin_set parsegroupin_set(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegroupin_set(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    groupin_set _result = new groupin_set();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegrouping_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_grouping_element(parsegrouping_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static groupin_set make_groupin_set(ru.barsopen.plsqlconverter.ast.typed.grouping_element grouping_element) {
    groupin_set _result = new groupin_set();
    _result.set_grouping_element(grouping_element);
    return _result;
  }

  public static boolean canParsegrouping_element(org.antlr.runtime.tree.Tree tree) {
    return canParsegrouping_element_rollup(tree) || canParsegrouping_element_cube(tree) || canParseexpression_list(tree) || canParseexpression(tree);
  }

  public static grouping_element parsegrouping_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegrouping_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsegrouping_element_rollup(tree)) return parsegrouping_element_rollup(tree);
    if (canParsegrouping_element_cube(tree)) return parsegrouping_element_cube(tree);
    if (canParseexpression_list(tree)) return parseexpression_list(tree);
    if (canParseexpression(tree)) return parseexpression(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsegrouping_element_rollup(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROLLUP_VK;
  }

  public static grouping_element_rollup parsegrouping_element_rollup(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegrouping_element_rollup(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    grouping_element_rollup _result = new grouping_element_rollup();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegrouping_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegrouping_element(tree.getChild(_i)))) {
      _result.add_grouping_elements(parsegrouping_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static grouping_element_rollup make_grouping_element_rollup(java.util.List<ru.barsopen.plsqlconverter.ast.typed.grouping_element> grouping_elements) {
    grouping_element_rollup _result = new grouping_element_rollup();
    if (grouping_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.grouping_element _value: grouping_elements) { _result.add_grouping_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsegrouping_element_cube(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CUBE_VK;
  }

  public static grouping_element_cube parsegrouping_element_cube(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegrouping_element_cube(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    grouping_element_cube _result = new grouping_element_cube();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegrouping_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegrouping_element(tree.getChild(_i)))) {
      _result.add_grouping_elements(parsegrouping_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static grouping_element_cube make_grouping_element_cube(java.util.List<ru.barsopen.plsqlconverter.ast.typed.grouping_element> grouping_elements) {
    grouping_element_cube _result = new grouping_element_cube();
    if (grouping_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.grouping_element _value: grouping_elements) { _result.add_grouping_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsehaving_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_HAVING;
  }

  public static having_clause parsehaving_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsehaving_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    having_clause _result = new having_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static having_clause make_having_clause(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    having_clause _result = new having_clause();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsemodel_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_MODEL;
  }

  public static model_clause parsemodel_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_clause _result = new model_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemain_model(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_main_model(parsemain_model(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsecell_reference_options(tree.getChild(_i)))) {
      _result.add_cell_reference_optionss(parsecell_reference_options(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsereturn_rows_clause(tree.getChild(_i)))) {
      _result.set_return_rows_clause(parsereturn_rows_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsereference_model(tree.getChild(_i)))) {
      _result.add_reference_models(parsereference_model(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_clause make_model_clause(ru.barsopen.plsqlconverter.ast.typed.main_model main_model,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.cell_reference_options> cell_reference_optionss,
      ru.barsopen.plsqlconverter.ast.typed.return_rows_clause return_rows_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.reference_model> reference_models) {
    model_clause _result = new model_clause();
    _result.set_main_model(main_model);
    if (cell_reference_optionss != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.cell_reference_options _value: cell_reference_optionss) { _result.add_cell_reference_optionss(_value); }
    }
    _result.set_return_rows_clause(return_rows_clause);
    if (reference_models != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.reference_model _value: reference_models) { _result.add_reference_models(_value); }
    }
    return _result;
  }

  public static boolean canParsecell_reference_options(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IGNORE_VK;
  }

  public static cell_reference_options parsecell_reference_options(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecell_reference_options(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cell_reference_options _result = new cell_reference_options();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cell_reference_options make_cell_reference_options() {
    cell_reference_options _result = new cell_reference_options();
    return _result;
  }

  public static boolean canParsereturn_rows_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RETURN_VK;
  }

  public static return_rows_clause parsereturn_rows_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereturn_rows_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    return_rows_clause _result = new return_rows_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UPDATED_VK)) {
      _result.set_UPDATED_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static return_rows_clause make_return_rows_clause(org.antlr.runtime.tree.Tree UPDATED_VK,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL) {
    return_rows_clause _result = new return_rows_clause();
    _result.set_UPDATED_VK(UPDATED_VK);
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    return _result;
  }

  public static boolean canParsereference_model(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REFERENCE_VK;
  }

  public static reference_model parsereference_model(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereference_model(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    reference_model _result = new reference_model();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsereference_model_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_reference_model_name(parsereference_model_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsesubquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_subquery(parsesubquery(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_clauses(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_clauses(parsemodel_column_clauses(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsecell_reference_options(tree.getChild(_i)))) {
      _result.add_cell_reference_optionss(parsecell_reference_options(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static reference_model make_reference_model(ru.barsopen.plsqlconverter.ast.typed.reference_model_name reference_model_name,
      ru.barsopen.plsqlconverter.ast.typed.subquery subquery,
      ru.barsopen.plsqlconverter.ast.typed.model_column_clauses model_column_clauses,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.cell_reference_options> cell_reference_optionss) {
    reference_model _result = new reference_model();
    _result.set_reference_model_name(reference_model_name);
    _result.set_subquery(subquery);
    _result.set_model_column_clauses(model_column_clauses);
    if (cell_reference_optionss != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.cell_reference_options _value: cell_reference_optionss) { _result.add_cell_reference_optionss(_value); }
    }
    return _result;
  }

  public static boolean canParsemain_model(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MAIN_MODEL;
  }

  public static main_model parsemain_model(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemain_model(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    main_model _result = new main_model();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsemain_model_name(tree.getChild(_i)))) {
      _result.set_main_model_name(parsemain_model_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsemodel_column_clauses(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_clauses(parsemodel_column_clauses(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemodel_rules_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_rules_clause(parsemodel_rules_clause(tree.getChild(_i)));
    ++_i;

    while (_i < tree.getChildCount() && (canParsecell_reference_options(tree.getChild(_i)))) {
      _result.add_cell_reference_optionss(parsecell_reference_options(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static main_model make_main_model(ru.barsopen.plsqlconverter.ast.typed.main_model_name main_model_name,
      ru.barsopen.plsqlconverter.ast.typed.model_column_clauses model_column_clauses,
      ru.barsopen.plsqlconverter.ast.typed.model_rules_clause model_rules_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.cell_reference_options> cell_reference_optionss) {
    main_model _result = new main_model();
    _result.set_main_model_name(main_model_name);
    _result.set_model_column_clauses(model_column_clauses);
    _result.set_model_rules_clause(model_rules_clause);
    if (cell_reference_optionss != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.cell_reference_options _value: cell_reference_optionss) { _result.add_cell_reference_optionss(_value); }
    }
    return _result;
  }

  public static boolean canParsemodel_column_clauses(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_COLUMN;
  }

  public static model_column_clauses parsemodel_column_clauses(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column_clauses(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column_clauses _result = new model_column_clauses();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_clauses_dimension(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_clauses_dimension(parsemodel_column_clauses_dimension(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_clauses_measures(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_clauses_measures(parsemodel_column_clauses_measures(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsemodel_column_partition_part(tree.getChild(_i)))) {
      _result.set_model_column_partition_part(parsemodel_column_partition_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column_clauses make_model_column_clauses(ru.barsopen.plsqlconverter.ast.typed.model_column_clauses_dimension model_column_clauses_dimension,
      ru.barsopen.plsqlconverter.ast.typed.model_column_clauses_measures model_column_clauses_measures,
      ru.barsopen.plsqlconverter.ast.typed.model_column_partition_part model_column_partition_part) {
    model_column_clauses _result = new model_column_clauses();
    _result.set_model_column_clauses_dimension(model_column_clauses_dimension);
    _result.set_model_column_clauses_measures(model_column_clauses_measures);
    _result.set_model_column_partition_part(model_column_partition_part);
    return _result;
  }

  public static boolean canParsemodel_column_clauses_dimension(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DIMENSION_VK;
  }

  public static model_column_clauses_dimension parsemodel_column_clauses_dimension(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column_clauses_dimension(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column_clauses_dimension _result = new model_column_clauses_dimension();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_list(parsemodel_column_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column_clauses_dimension make_model_column_clauses_dimension(ru.barsopen.plsqlconverter.ast.typed.model_column_list model_column_list) {
    model_column_clauses_dimension _result = new model_column_clauses_dimension();
    _result.set_model_column_list(model_column_list);
    return _result;
  }

  public static boolean canParsemodel_column_clauses_measures(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MEASURES_VK;
  }

  public static model_column_clauses_measures parsemodel_column_clauses_measures(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column_clauses_measures(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column_clauses_measures _result = new model_column_clauses_measures();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_list(parsemodel_column_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column_clauses_measures make_model_column_clauses_measures(ru.barsopen.plsqlconverter.ast.typed.model_column_list model_column_list) {
    model_column_clauses_measures _result = new model_column_clauses_measures();
    _result.set_model_column_list(model_column_list);
    return _result;
  }

  public static boolean canParsemodel_column_partition_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARTITION_VK;
  }

  public static model_column_partition_part parsemodel_column_partition_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column_partition_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column_partition_part _result = new model_column_partition_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_column_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_model_column_list(parsemodel_column_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column_partition_part make_model_column_partition_part(ru.barsopen.plsqlconverter.ast.typed.model_column_list model_column_list) {
    model_column_partition_part _result = new model_column_partition_part();
    _result.set_model_column_list(model_column_list);
    return _result;
  }

  public static boolean canParsemodel_column_list(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_COLUMNS;
  }

  public static model_column_list parsemodel_column_list(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column_list(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column_list _result = new model_column_list();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_column(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemodel_column(tree.getChild(_i)))) {
      _result.add_model_columns(parsemodel_column(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column_list make_model_column_list(java.util.List<ru.barsopen.plsqlconverter.ast.typed.model_column> model_columns) {
    model_column_list _result = new model_column_list();
    if (model_columns != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.model_column _value: model_columns) { _result.add_model_columns(_value); }
    }
    return _result;
  }

  public static boolean canParsemodel_column(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_COLUMN;
  }

  public static model_column parsemodel_column(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_column(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_column _result = new model_column();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_column make_model_column(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    model_column _result = new model_column();
    _result.set_alias(alias);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsemodel_rules_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_RULES;
  }

  public static model_rules_clause parsemodel_rules_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_rules_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_rules_clause _result = new model_rules_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemodel_rules_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemodel_rules_element(tree.getChild(_i)))) {
      _result.add_model_rules_elements(parsemodel_rules_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsemodel_rules_part(tree.getChild(_i)))) {
      _result.set_model_rules_part(parsemodel_rules_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_rules_clause make_model_rules_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.model_rules_element> model_rules_elements,
      ru.barsopen.plsqlconverter.ast.typed.model_rules_part model_rules_part) {
    model_rules_clause _result = new model_rules_clause();
    if (model_rules_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.model_rules_element _value: model_rules_elements) { _result.add_model_rules_elements(_value); }
    }
    _result.set_model_rules_part(model_rules_part);
    return _result;
  }

  public static boolean canParsemodel_rules_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RULES_VK;
  }

  public static model_rules_part parsemodel_rules_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_rules_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_rules_part _result = new model_rules_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_rules_part make_model_rules_part() {
    model_rules_part _result = new model_rules_part();
    return _result;
  }

  public static boolean canParsemodel_rules_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_RULE;
  }

  public static model_rules_element parsemodel_rules_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_rules_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_rules_element _result = new model_rules_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_rules_element make_model_rules_element() {
    model_rules_element _result = new model_rules_element();
    return _result;
  }

  public static boolean canParsemodel_iterate_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ITERATE_VK;
  }

  public static model_iterate_clause parsemodel_iterate_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_iterate_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_iterate_clause _result = new model_iterate_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseuntil_part(tree.getChild(_i)))) {
      _result.set_until_part(parseuntil_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_iterate_clause make_model_iterate_clause(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.until_part until_part) {
    model_iterate_clause _result = new model_iterate_clause();
    _result.set_expression(expression);
    _result.set_until_part(until_part);
    return _result;
  }

  public static boolean canParseuntil_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNTIL_VK;
  }

  public static until_part parseuntil_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseuntil_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    until_part _result = new until_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static until_part make_until_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    until_part _result = new until_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseorder_by_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ORDER;
  }

  public static order_by_clause parseorder_by_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseorder_by_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    order_by_clause _result = new order_by_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SIBLINGS_VK)) {
      _result.set_SIBLINGS_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseorder_by_elements_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_order_by_elements_list(parseorder_by_elements_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static order_by_clause make_order_by_clause(org.antlr.runtime.tree.Tree SIBLINGS_VK,
      ru.barsopen.plsqlconverter.ast.typed.order_by_elements_list order_by_elements_list) {
    order_by_clause _result = new order_by_clause();
    _result.set_SIBLINGS_VK(SIBLINGS_VK);
    _result.set_order_by_elements_list(order_by_elements_list);
    return _result;
  }

  public static boolean canParseorder_by_elements_list(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ORDER_BY_ELEMENTS;
  }

  public static order_by_elements_list parseorder_by_elements_list(org.antlr.runtime.tree.Tree tree) {
    if (!canParseorder_by_elements_list(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    order_by_elements_list _result = new order_by_elements_list();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseorder_by_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseorder_by_elements(tree.getChild(_i)))) {
      _result.add_elements(parseorder_by_elements(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static order_by_elements_list make_order_by_elements_list(java.util.List<ru.barsopen.plsqlconverter.ast.typed.order_by_elements> elements) {
    order_by_elements_list _result = new order_by_elements_list();
    if (elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.order_by_elements _value: elements) { _result.add_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseorder_by_elements(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ORDER_BY_ELEMENT;
  }

  public static order_by_elements parseorder_by_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParseorder_by_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    order_by_elements _result = new order_by_elements();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ASC)) {
      _result.set_SQL92_RESERVED_ASC(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DESC)) {
      _result.set_SQL92_RESERVED_DESC(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NULLS_VK)) {
      _result.set_NULLS_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FIRST_VK)) {
      _result.set_FIRST_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LAST_VK)) {
      _result.set_LAST_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static order_by_elements make_order_by_elements(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_ASC,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_DESC,
      org.antlr.runtime.tree.Tree NULLS_VK,
      org.antlr.runtime.tree.Tree FIRST_VK,
      org.antlr.runtime.tree.Tree LAST_VK) {
    order_by_elements _result = new order_by_elements();
    _result.set_expression(expression);
    _result.set_SQL92_RESERVED_ASC(SQL92_RESERVED_ASC);
    _result.set_SQL92_RESERVED_DESC(SQL92_RESERVED_DESC);
    _result.set_NULLS_VK(NULLS_VK);
    _result.set_FIRST_VK(FIRST_VK);
    _result.set_LAST_VK(LAST_VK);
    return _result;
  }

  public static boolean canParsefor_update_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FOR;
  }

  public static for_update_clause parsefor_update_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_update_clause _result = new for_update_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsefor_update_of_part(tree.getChild(_i)))) {
      _result.set_for_update_of_part(parsefor_update_of_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsefor_update_options(tree.getChild(_i)))) {
      _result.set_for_update_options(parsefor_update_options(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_update_clause make_for_update_clause(ru.barsopen.plsqlconverter.ast.typed.for_update_of_part for_update_of_part,
      ru.barsopen.plsqlconverter.ast.typed.for_update_options for_update_options) {
    for_update_clause _result = new for_update_clause();
    _result.set_for_update_of_part(for_update_of_part);
    _result.set_for_update_options(for_update_options);
    return _result;
  }

  public static boolean canParsefor_update_of_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_OF;
  }

  public static for_update_of_part parsefor_update_of_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_of_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_update_of_part _result = new for_update_of_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_update_of_part make_for_update_of_part(java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names) {
    for_update_of_part _result = new for_update_of_part();
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    return _result;
  }

  public static boolean canParsefor_update_options(org.antlr.runtime.tree.Tree tree) {
    return canParsefor_update_options_skip(tree) || canParsefor_update_options_nowait(tree) || canParsefor_update_options_wait(tree);
  }

  public static for_update_options parsefor_update_options(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_options(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsefor_update_options_skip(tree)) return parsefor_update_options_skip(tree);
    if (canParsefor_update_options_nowait(tree)) return parsefor_update_options_nowait(tree);
    if (canParsefor_update_options_wait(tree)) return parsefor_update_options_wait(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsefor_update_options_skip(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SKIP_VK;
  }

  public static for_update_options_skip parsefor_update_options_skip(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_options_skip(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_update_options_skip _result = new for_update_options_skip();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_update_options_skip make_for_update_options_skip() {
    for_update_options_skip _result = new for_update_options_skip();
    return _result;
  }

  public static boolean canParsefor_update_options_nowait(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_NOWAIT;
  }

  public static for_update_options_nowait parsefor_update_options_nowait(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_options_nowait(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_update_options_nowait _result = new for_update_options_nowait();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_update_options_nowait make_for_update_options_nowait() {
    for_update_options_nowait _result = new for_update_options_nowait();
    return _result;
  }

  public static boolean canParsefor_update_options_wait(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WAIT_VK;
  }

  public static for_update_options_wait parsefor_update_options_wait(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_update_options_wait(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_update_options_wait _result = new for_update_options_wait();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_update_options_wait make_for_update_options_wait(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    for_update_options_wait _result = new for_update_options_wait();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseupdate_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_UPDATE;
  }

  public static update_statement parseupdate_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseupdate_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    update_statement _result = new update_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_table_ref(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_general_table_ref(parsegeneral_table_ref(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseupdate_statement_set(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_update_statement_set(parseupdate_statement_set(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i)))) {
      _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsestatic_returning_clause(tree.getChild(_i)))) {
      _result.set_static_returning_clause(parsestatic_returning_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_clause(tree.getChild(_i)))) {
      _result.set_error_logging_clause(parseerror_logging_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static update_statement make_update_statement(ru.barsopen.plsqlconverter.ast.typed.general_table_ref general_table_ref,
      ru.barsopen.plsqlconverter.ast.typed.update_statement_set update_statement_set,
      ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause,
      ru.barsopen.plsqlconverter.ast.typed.static_returning_clause static_returning_clause,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_clause error_logging_clause) {
    update_statement _result = new update_statement();
    _result.set_general_table_ref(general_table_ref);
    _result.set_update_statement_set(update_statement_set);
    _result.set_where_clause(where_clause);
    _result.set_static_returning_clause(static_returning_clause);
    _result.set_error_logging_clause(error_logging_clause);
    return _result;
  }

  public static boolean canParseupdate_statement_set(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SET_VK;
  }

  public static update_statement_set parseupdate_statement_set(org.antlr.runtime.tree.Tree tree) {
    if (!canParseupdate_statement_set(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    update_statement_set _result = new update_statement_set();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseupdate_set_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseupdate_set_elements(tree.getChild(_i)))) {
      _result.add_update_set_elementss(parseupdate_set_elements(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static update_statement_set make_update_statement_set(java.util.List<ru.barsopen.plsqlconverter.ast.typed.update_set_elements> update_set_elementss) {
    update_statement_set _result = new update_statement_set();
    if (update_set_elementss != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.update_set_elements _value: update_set_elementss) { _result.add_update_set_elementss(_value); }
    }
    return _result;
  }

  public static boolean canParseupdate_set_elements(org.antlr.runtime.tree.Tree tree) {
    return canParseupdate_set_elements_assign(tree) || canParseupdate_set_elements_value(tree);
  }

  public static update_set_elements parseupdate_set_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParseupdate_set_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseupdate_set_elements_assign(tree)) return parseupdate_set_elements_assign(tree);
    if (canParseupdate_set_elements_value(tree)) return parseupdate_set_elements_value(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseupdate_set_elements_assign(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASSIGN;
  }

  public static update_set_elements_assign parseupdate_set_elements_assign(org.antlr.runtime.tree.Tree tree) {
    if (!canParseupdate_set_elements_assign(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    update_set_elements_assign _result = new update_set_elements_assign();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static update_set_elements_assign make_update_set_elements_assign(java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names,
      ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    update_set_elements_assign _result = new update_set_elements_assign();
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParseexpression_or_subquery(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree) || canParsesubquery(tree);
  }

  public static expression_or_subquery parseexpression_or_subquery(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_or_subquery(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParsesubquery(tree)) return parsesubquery(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseupdate_set_elements_value(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VALUE_VK;
  }

  public static update_set_elements_value parseupdate_set_elements_value(org.antlr.runtime.tree.Tree tree) {
    if (!canParseupdate_set_elements_value(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    update_set_elements_value _result = new update_set_elements_value();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static update_set_elements_value make_update_set_elements_value(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    update_set_elements_value _result = new update_set_elements_value();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsedelete_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DELETE;
  }

  public static delete_statement parsedelete_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedelete_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    delete_statement _result = new delete_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_table_ref(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_general_table_ref(parsegeneral_table_ref(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i)))) {
      _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsestatic_returning_clause(tree.getChild(_i)))) {
      _result.set_static_returning_clause(parsestatic_returning_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_clause(tree.getChild(_i)))) {
      _result.set_error_logging_clause(parseerror_logging_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static delete_statement make_delete_statement(ru.barsopen.plsqlconverter.ast.typed.general_table_ref general_table_ref,
      ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause,
      ru.barsopen.plsqlconverter.ast.typed.static_returning_clause static_returning_clause,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_clause error_logging_clause) {
    delete_statement _result = new delete_statement();
    _result.set_general_table_ref(general_table_ref);
    _result.set_where_clause(where_clause);
    _result.set_static_returning_clause(static_returning_clause);
    _result.set_error_logging_clause(error_logging_clause);
    return _result;
  }

  public static boolean canParseinsert_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INSERT;
  }

  public static insert_statement parseinsert_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinsert_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    insert_statement _result = new insert_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinsert_statement_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_insert_statement_spec(parseinsert_statement_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static insert_statement make_insert_statement(ru.barsopen.plsqlconverter.ast.typed.insert_statement_spec insert_statement_spec) {
    insert_statement _result = new insert_statement();
    _result.set_insert_statement_spec(insert_statement_spec);
    return _result;
  }

  public static boolean canParseinsert_statement_spec(org.antlr.runtime.tree.Tree tree) {
    return canParsesingle_table_insert(tree) || canParsemulti_table_insert(tree);
  }

  public static insert_statement_spec parseinsert_statement_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinsert_statement_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsesingle_table_insert(tree)) return parsesingle_table_insert(tree);
    if (canParsemulti_table_insert(tree)) return parsemulti_table_insert(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsesingle_table_insert(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SINGLE_TABLE_MODE;
  }

  public static single_table_insert parsesingle_table_insert(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesingle_table_insert(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    single_table_insert _result = new single_table_insert();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinsert_into_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_insert_into_clause(parseinsert_into_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsevalues_clause(tree.getChild(_i)))) {
      _result.set_values_clause(parsevalues_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsestatic_returning_clause(tree.getChild(_i)))) {
      _result.set_static_returning_clause(parsestatic_returning_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseselect_statement(tree.getChild(_i)))) {
      _result.set_select_statement(parseselect_statement(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_clause(tree.getChild(_i)))) {
      _result.set_error_logging_clause(parseerror_logging_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static single_table_insert make_single_table_insert(ru.barsopen.plsqlconverter.ast.typed.insert_into_clause insert_into_clause,
      ru.barsopen.plsqlconverter.ast.typed.values_clause values_clause,
      ru.barsopen.plsqlconverter.ast.typed.static_returning_clause static_returning_clause,
      ru.barsopen.plsqlconverter.ast.typed.select_statement select_statement,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_clause error_logging_clause) {
    single_table_insert _result = new single_table_insert();
    _result.set_insert_into_clause(insert_into_clause);
    _result.set_values_clause(values_clause);
    _result.set_static_returning_clause(static_returning_clause);
    _result.set_select_statement(select_statement);
    _result.set_error_logging_clause(error_logging_clause);
    return _result;
  }

  public static boolean canParsemulti_table_insert(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MULTI_TABLE_MODE;
  }

  public static multi_table_insert parsemulti_table_insert(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemulti_table_insert(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    multi_table_insert _result = new multi_table_insert();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseselect_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_select_statement(parseselect_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseconditional_insert_clause(tree.getChild(_i)))) {
      _result.set_conditional_insert_clause(parseconditional_insert_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsemulti_table_element(tree.getChild(_i)))) {
      _result.add_multi_table_elements(parsemulti_table_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static multi_table_insert make_multi_table_insert(ru.barsopen.plsqlconverter.ast.typed.select_statement select_statement,
      ru.barsopen.plsqlconverter.ast.typed.conditional_insert_clause conditional_insert_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.multi_table_element> multi_table_elements) {
    multi_table_insert _result = new multi_table_insert();
    _result.set_select_statement(select_statement);
    _result.set_conditional_insert_clause(conditional_insert_clause);
    if (multi_table_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.multi_table_element _value: multi_table_elements) { _result.add_multi_table_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsemulti_table_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_ELEMENT;
  }

  public static multi_table_element parsemulti_table_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemulti_table_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    multi_table_element _result = new multi_table_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinsert_into_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_insert_into_clause(parseinsert_into_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsevalues_clause(tree.getChild(_i)))) {
      _result.set_values_clause(parsevalues_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_clause(tree.getChild(_i)))) {
      _result.set_error_logging_clause(parseerror_logging_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static multi_table_element make_multi_table_element(ru.barsopen.plsqlconverter.ast.typed.insert_into_clause insert_into_clause,
      ru.barsopen.plsqlconverter.ast.typed.values_clause values_clause,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_clause error_logging_clause) {
    multi_table_element _result = new multi_table_element();
    _result.set_insert_into_clause(insert_into_clause);
    _result.set_values_clause(values_clause);
    _result.set_error_logging_clause(error_logging_clause);
    return _result;
  }

  public static boolean canParseconditional_insert_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONDITIONAL_INSERT;
  }

  public static conditional_insert_clause parseconditional_insert_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconditional_insert_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    conditional_insert_clause _result = new conditional_insert_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FIRST_VK)) {
      _result.set_FIRST_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseconditional_insert_when_part(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseconditional_insert_when_part(tree.getChild(_i)))) {
      _result.add_conditional_insert_when_parts(parseconditional_insert_when_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseconditional_insert_else_part(tree.getChild(_i)))) {
      _result.set_conditional_insert_else_part(parseconditional_insert_else_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static conditional_insert_clause make_conditional_insert_clause(org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      org.antlr.runtime.tree.Tree FIRST_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.conditional_insert_when_part> conditional_insert_when_parts,
      ru.barsopen.plsqlconverter.ast.typed.conditional_insert_else_part conditional_insert_else_part) {
    conditional_insert_clause _result = new conditional_insert_clause();
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_FIRST_VK(FIRST_VK);
    if (conditional_insert_when_parts != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.conditional_insert_when_part _value: conditional_insert_when_parts) { _result.add_conditional_insert_when_parts(_value); }
    }
    _result.set_conditional_insert_else_part(conditional_insert_else_part);
    return _result;
  }

  public static boolean canParseconditional_insert_when_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN;
  }

  public static conditional_insert_when_part parseconditional_insert_when_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconditional_insert_when_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    conditional_insert_when_part _result = new conditional_insert_when_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemulti_table_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemulti_table_element(tree.getChild(_i)))) {
      _result.add_multi_table_elements(parsemulti_table_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static conditional_insert_when_part make_conditional_insert_when_part(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.multi_table_element> multi_table_elements) {
    conditional_insert_when_part _result = new conditional_insert_when_part();
    _result.set_expression(expression);
    if (multi_table_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.multi_table_element _value: multi_table_elements) { _result.add_multi_table_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseconditional_insert_else_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ELSE;
  }

  public static conditional_insert_else_part parseconditional_insert_else_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconditional_insert_else_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    conditional_insert_else_part _result = new conditional_insert_else_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemulti_table_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemulti_table_element(tree.getChild(_i)))) {
      _result.add_multi_table_elements(parsemulti_table_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static conditional_insert_else_part make_conditional_insert_else_part(java.util.List<ru.barsopen.plsqlconverter.ast.typed.multi_table_element> multi_table_elements) {
    conditional_insert_else_part _result = new conditional_insert_else_part();
    if (multi_table_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.multi_table_element _value: multi_table_elements) { _result.add_multi_table_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseinsert_into_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTO;
  }

  public static insert_into_clause parseinsert_into_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinsert_into_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    insert_into_clause _result = new insert_into_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_table_ref(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_general_table_ref(parsegeneral_table_ref(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseinsert_into_clause_columns(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_insert_into_clause_columns(parseinsert_into_clause_columns(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static insert_into_clause make_insert_into_clause(ru.barsopen.plsqlconverter.ast.typed.general_table_ref general_table_ref,
      ru.barsopen.plsqlconverter.ast.typed.insert_into_clause_columns insert_into_clause_columns) {
    insert_into_clause _result = new insert_into_clause();
    _result.set_general_table_ref(general_table_ref);
    _result.set_insert_into_clause_columns(insert_into_clause_columns);
    return _result;
  }

  public static boolean canParseinsert_into_clause_columns(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COLUMNS;
  }

  public static insert_into_clause_columns parseinsert_into_clause_columns(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinsert_into_clause_columns(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    insert_into_clause_columns _result = new insert_into_clause_columns();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i)))) {
      _result.add_column_names(parsecolumn_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static insert_into_clause_columns make_insert_into_clause_columns(java.util.List<ru.barsopen.plsqlconverter.ast.typed.column_name> column_names) {
    insert_into_clause_columns _result = new insert_into_clause_columns();
    if (column_names != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.column_name _value: column_names) { _result.add_column_names(_value); }
    }
    return _result;
  }

  public static boolean canParsevalues_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_VALUES;
  }

  public static values_clause parsevalues_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsevalues_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    values_clause _result = new values_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_expression_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_expression_list(parseexpression_or_expression_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static values_clause make_values_clause(ru.barsopen.plsqlconverter.ast.typed.expression_or_expression_list expression_or_expression_list) {
    values_clause _result = new values_clause();
    _result.set_expression_or_expression_list(expression_or_expression_list);
    return _result;
  }

  public static boolean canParseexpression_or_expression_list(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree) || canParseexpression_list(tree);
  }

  public static expression_or_expression_list parseexpression_or_expression_list(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_or_expression_list(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParseexpression_list(tree)) return parseexpression_list(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsemerge_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MERGE_VK;
  }

  public static merge_statement parsemerge_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_statement _result = new merge_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_tableview_name(parsetableview_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemerge_using_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_merge_using_clause(parsemerge_using_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsemerge_update_clause(tree.getChild(_i)))) {
      _result.set_merge_update_clause(parsemerge_update_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsemerge_insert_clause(tree.getChild(_i)))) {
      _result.set_merge_insert_clause(parsemerge_insert_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_clause(tree.getChild(_i)))) {
      _result.set_error_logging_clause(parseerror_logging_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_statement make_merge_statement(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.tableview_name tableview_name,
      ru.barsopen.plsqlconverter.ast.typed.merge_using_clause merge_using_clause,
      ru.barsopen.plsqlconverter.ast.typed.merge_update_clause merge_update_clause,
      ru.barsopen.plsqlconverter.ast.typed.merge_insert_clause merge_insert_clause,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_clause error_logging_clause) {
    merge_statement _result = new merge_statement();
    _result.set_alias(alias);
    _result.set_tableview_name(tableview_name);
    _result.set_merge_using_clause(merge_using_clause);
    _result.set_merge_update_clause(merge_update_clause);
    _result.set_merge_insert_clause(merge_insert_clause);
    _result.set_error_logging_clause(error_logging_clause);
    return _result;
  }

  public static boolean canParsemerge_using_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_USING;
  }

  public static merge_using_clause parsemerge_using_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_using_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_using_clause _result = new merge_using_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseselected_tableview(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_selected_tableview(parseselected_tableview(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_using_clause make_merge_using_clause(ru.barsopen.plsqlconverter.ast.typed.selected_tableview selected_tableview,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    merge_using_clause _result = new merge_using_clause();
    _result.set_selected_tableview(selected_tableview);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsemerge_update_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MERGE_UPDATE;
  }

  public static merge_update_clause parsemerge_update_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_update_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_update_clause _result = new merge_update_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsemerge_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemerge_element(tree.getChild(_i)))) {
      _result.add_merge_elements(parsemerge_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i)))) {
      _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsemerge_update_delete_part(tree.getChild(_i)))) {
      _result.set_merge_update_delete_part(parsemerge_update_delete_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_update_clause make_merge_update_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.merge_element> merge_elements,
      ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause,
      ru.barsopen.plsqlconverter.ast.typed.merge_update_delete_part merge_update_delete_part) {
    merge_update_clause _result = new merge_update_clause();
    if (merge_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.merge_element _value: merge_elements) { _result.add_merge_elements(_value); }
    }
    _result.set_where_clause(where_clause);
    _result.set_merge_update_delete_part(merge_update_delete_part);
    return _result;
  }

  public static boolean canParsemerge_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASSIGN;
  }

  public static merge_element parsemerge_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_element _result = new merge_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecolumn_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_column_name(parsecolumn_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_element make_merge_element(ru.barsopen.plsqlconverter.ast.typed.column_name column_name,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    merge_element _result = new merge_element();
    _result.set_column_name(column_name);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsemerge_update_delete_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DELETE;
  }

  public static merge_update_delete_part parsemerge_update_delete_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_update_delete_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_update_delete_part _result = new merge_update_delete_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_update_delete_part make_merge_update_delete_part(ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause) {
    merge_update_delete_part _result = new merge_update_delete_part();
    _result.set_where_clause(where_clause);
    return _result;
  }

  public static boolean canParsemerge_insert_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MERGE_INSERT;
  }

  public static merge_insert_clause parsemerge_insert_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemerge_insert_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    merge_insert_clause _result = new merge_insert_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinsert_into_clause_columns(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_insert_into_clause_columns(parseinsert_into_clause_columns(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_list(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_list(parseexpression_list(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsewhere_clause(tree.getChild(_i)))) {
      _result.set_where_clause(parsewhere_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static merge_insert_clause make_merge_insert_clause(ru.barsopen.plsqlconverter.ast.typed.insert_into_clause_columns insert_into_clause_columns,
      ru.barsopen.plsqlconverter.ast.typed.expression_list expression_list,
      ru.barsopen.plsqlconverter.ast.typed.where_clause where_clause) {
    merge_insert_clause _result = new merge_insert_clause();
    _result.set_insert_into_clause_columns(insert_into_clause_columns);
    _result.set_expression_list(expression_list);
    _result.set_where_clause(where_clause);
    return _result;
  }

  public static boolean canParseselected_tableview(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECTED_TABLEVIEW;
  }

  public static selected_tableview parseselected_tableview(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselected_tableview(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    selected_tableview _result = new selected_tableview();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseselected_tableview_src(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_selected_tableview_src(parseselected_tableview_src(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static selected_tableview make_selected_tableview(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.selected_tableview_src selected_tableview_src) {
    selected_tableview _result = new selected_tableview();
    _result.set_alias(alias);
    _result.set_selected_tableview_src(selected_tableview_src);
    return _result;
  }

  public static boolean canParseselected_tableview_src(org.antlr.runtime.tree.Tree tree) {
    return canParsetableview_name(tree) || canParseselect_statement(tree);
  }

  public static selected_tableview_src parseselected_tableview_src(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselected_tableview_src(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsetableview_name(tree)) return parsetableview_name(tree);
    if (canParseselect_statement(tree)) return parseselect_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParselock_table_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_LOCK;
  }

  public static lock_table_statement parselock_table_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParselock_table_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    lock_table_statement _result = new lock_table_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParselock_table_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParselock_table_element(tree.getChild(_i)))) {
      _result.add_lock_table_elements(parselock_table_element(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParselock_mode(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lock_mode(parselock_mode(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsewait_nowait_part(tree.getChild(_i)))) {
      _result.set_wait_nowait_part(parsewait_nowait_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static lock_table_statement make_lock_table_statement(java.util.List<ru.barsopen.plsqlconverter.ast.typed.lock_table_element> lock_table_elements,
      ru.barsopen.plsqlconverter.ast.typed.lock_mode lock_mode,
      ru.barsopen.plsqlconverter.ast.typed.wait_nowait_part wait_nowait_part) {
    lock_table_statement _result = new lock_table_statement();
    if (lock_table_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.lock_table_element _value: lock_table_elements) { _result.add_lock_table_elements(_value); }
    }
    _result.set_lock_mode(lock_mode);
    _result.set_wait_nowait_part(wait_nowait_part);
    return _result;
  }

  public static boolean canParsewait_nowait_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WAIT_VK;
  }

  public static wait_nowait_part parsewait_nowait_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewait_nowait_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    wait_nowait_part _result = new wait_nowait_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static wait_nowait_part make_wait_nowait_part() {
    wait_nowait_part _result = new wait_nowait_part();
    return _result;
  }

  public static boolean canParselock_table_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LOCK_TABLE_ELEMENT;
  }

  public static lock_table_element parselock_table_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParselock_table_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    lock_table_element _result = new lock_table_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_tableview_name(parsetableview_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsepartition_extension_clause(tree.getChild(_i)))) {
      _result.set_partition_extension_clause(parsepartition_extension_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static lock_table_element make_lock_table_element(ru.barsopen.plsqlconverter.ast.typed.tableview_name tableview_name,
      ru.barsopen.plsqlconverter.ast.typed.partition_extension_clause partition_extension_clause) {
    lock_table_element _result = new lock_table_element();
    _result.set_tableview_name(tableview_name);
    _result.set_partition_extension_clause(partition_extension_clause);
    return _result;
  }

  public static boolean canParselock_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROW_VK;
  }

  public static lock_mode parselock_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParselock_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    lock_mode _result = new lock_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static lock_mode make_lock_mode() {
    lock_mode _result = new lock_mode();
    return _result;
  }

  public static boolean canParsegeneral_table_ref(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_REF;
  }

  public static general_table_ref parsegeneral_table_ref(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_table_ref(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    general_table_ref _result = new general_table_ref();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsedml_table_expression_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_dml_table_expression_clause(parsedml_table_expression_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ONLY_VK)) {
      _result.set_ONLY_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static general_table_ref make_general_table_ref(ru.barsopen.plsqlconverter.ast.typed.alias alias,
      ru.barsopen.plsqlconverter.ast.typed.dml_table_expression_clause dml_table_expression_clause,
      org.antlr.runtime.tree.Tree ONLY_VK) {
    general_table_ref _result = new general_table_ref();
    _result.set_alias(alias);
    _result.set_dml_table_expression_clause(dml_table_expression_clause);
    _result.set_ONLY_VK(ONLY_VK);
    return _result;
  }

  public static boolean canParsestatic_returning_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.STATIC_RETURNING;
  }

  public static static_returning_clause parsestatic_returning_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestatic_returning_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    static_returning_clause _result = new static_returning_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.add_expressions(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseinto_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_into_clause(parseinto_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static static_returning_clause make_static_returning_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.expression> expressions,
      ru.barsopen.plsqlconverter.ast.typed.into_clause into_clause) {
    static_returning_clause _result = new static_returning_clause();
    if (expressions != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.expression _value: expressions) { _result.add_expressions(_value); }
    }
    _result.set_into_clause(into_clause);
    return _result;
  }

  public static boolean canParseerror_logging_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LOG_VK;
  }

  public static error_logging_clause parseerror_logging_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseerror_logging_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    error_logging_clause _result = new error_logging_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseerror_logging_into_part(tree.getChild(_i)))) {
      _result.set_error_logging_into_part(parseerror_logging_into_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseerror_logging_reject_part(tree.getChild(_i)))) {
      _result.set_error_logging_reject_part(parseerror_logging_reject_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static error_logging_clause make_error_logging_clause(ru.barsopen.plsqlconverter.ast.typed.error_logging_into_part error_logging_into_part,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.error_logging_reject_part error_logging_reject_part) {
    error_logging_clause _result = new error_logging_clause();
    _result.set_error_logging_into_part(error_logging_into_part);
    _result.set_expression(expression);
    _result.set_error_logging_reject_part(error_logging_reject_part);
    return _result;
  }

  public static boolean canParseerror_logging_into_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTO;
  }

  public static error_logging_into_part parseerror_logging_into_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseerror_logging_into_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    error_logging_into_part _result = new error_logging_into_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_tableview_name(parsetableview_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static error_logging_into_part make_error_logging_into_part(ru.barsopen.plsqlconverter.ast.typed.tableview_name tableview_name) {
    error_logging_into_part _result = new error_logging_into_part();
    _result.set_tableview_name(tableview_name);
    return _result;
  }

  public static boolean canParseerror_logging_reject_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REJECT_VK;
  }

  public static error_logging_reject_part parseerror_logging_reject_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseerror_logging_reject_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    error_logging_reject_part _result = new error_logging_reject_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNLIMITED_VK)) {
      _result.set_UNLIMITED_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static error_logging_reject_part make_error_logging_reject_part(org.antlr.runtime.tree.Tree UNLIMITED_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    error_logging_reject_part _result = new error_logging_reject_part();
    _result.set_UNLIMITED_VK(UNLIMITED_VK);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsedml_table_expression_clause(org.antlr.runtime.tree.Tree tree) {
    return canParsetable_expression(tree) || canParsetable_ref(tree);
  }

  public static dml_table_expression_clause parsedml_table_expression_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedml_table_expression_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsetable_expression(tree)) return parsetable_expression(tree);
    if (canParsetable_ref(tree)) return parsetable_ref(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsetable_expression(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_EXPRESSION;
  }

  public static table_expression parsetable_expression(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_expression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_expression _result = new table_expression();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetable_expression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_expression_element(parsetable_expression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_expression make_table_expression(ru.barsopen.plsqlconverter.ast.typed.table_expression_element table_expression_element) {
    table_expression _result = new table_expression();
    _result.set_table_expression_element(table_expression_element);
    return _result;
  }

  public static boolean canParsetable_expression_element(org.antlr.runtime.tree.Tree tree) {
    return canParsecollection_mode(tree) || canParseselect_mode(tree) || canParsedirect_mode(tree) || canParsegeneral_element(tree) || canParsestandard_function(tree);
  }

  public static table_expression_element parsetable_expression_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_expression_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecollection_mode(tree)) return parsecollection_mode(tree);
    if (canParseselect_mode(tree)) return parseselect_mode(tree);
    if (canParsedirect_mode(tree)) return parsedirect_mode(tree);
    if (canParsegeneral_element(tree)) return parsegeneral_element(tree);
    if (canParsestandard_function(tree)) return parsestandard_function(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecollection_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COLLECTION_MODE;
  }

  public static collection_mode parsecollection_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecollection_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    collection_mode _result = new collection_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetable_collection_expression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_table_collection_expression(parsetable_collection_expression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static collection_mode make_collection_mode(ru.barsopen.plsqlconverter.ast.typed.table_collection_expression table_collection_expression) {
    collection_mode _result = new collection_mode();
    _result.set_table_collection_expression(table_collection_expression);
    return _result;
  }

  public static boolean canParseselect_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SELECT_MODE;
  }

  public static select_mode parseselect_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParseselect_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    select_mode _result = new select_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseselect_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_select_statement(parseselect_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsesubquery_restriction_clause(tree.getChild(_i)))) {
      _result.set_subquery_restriction_clause(parsesubquery_restriction_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static select_mode make_select_mode(ru.barsopen.plsqlconverter.ast.typed.select_statement select_statement,
      ru.barsopen.plsqlconverter.ast.typed.subquery_restriction_clause subquery_restriction_clause) {
    select_mode _result = new select_mode();
    _result.set_select_statement(select_statement);
    _result.set_subquery_restriction_clause(subquery_restriction_clause);
    return _result;
  }

  public static boolean canParsedirect_mode(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DIRECT_MODE;
  }

  public static direct_mode parsedirect_mode(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedirect_mode(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    direct_mode _result = new direct_mode();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_tableview_name(parsetableview_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsesample_clause(tree.getChild(_i)))) {
      _result.set_sample_clause(parsesample_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static direct_mode make_direct_mode(ru.barsopen.plsqlconverter.ast.typed.tableview_name tableview_name,
      ru.barsopen.plsqlconverter.ast.typed.sample_clause sample_clause) {
    direct_mode _result = new direct_mode();
    _result.set_tableview_name(tableview_name);
    _result.set_sample_clause(sample_clause);
    return _result;
  }

  public static boolean canParsetable_collection_expression(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree) || canParsesubquery(tree) || canParseouter_join_sign_table_expr(tree);
  }

  public static table_collection_expression parsetable_collection_expression(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_collection_expression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParsesubquery(tree)) return parsesubquery(tree);
    if (canParseouter_join_sign_table_expr(tree)) return parseouter_join_sign_table_expr(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseouter_join_sign_table_expr(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OUTER_JOIN_SIGN;
  }

  public static outer_join_sign_table_expr parseouter_join_sign_table_expr(org.antlr.runtime.tree.Tree tree) {
    if (!canParseouter_join_sign_table_expr(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    outer_join_sign_table_expr _result = new outer_join_sign_table_expr();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static outer_join_sign_table_expr make_outer_join_sign_table_expr(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    outer_join_sign_table_expr _result = new outer_join_sign_table_expr();
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParsesubquery_restriction_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WITH;
  }

  public static subquery_restriction_clause parsesubquery_restriction_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesubquery_restriction_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    subquery_restriction_clause _result = new subquery_restriction_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static subquery_restriction_clause make_subquery_restriction_clause() {
    subquery_restriction_clause _result = new subquery_restriction_clause();
    return _result;
  }

  public static boolean canParsesample_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SAMPLE_VK;
  }

  public static sample_clause parsesample_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesample_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sample_clause _result = new sample_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BLOCK_VK)) {
      _result.set_BLOCK_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseseed_part(tree.getChild(_i)))) {
      _result.set_seed_part(parseseed_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sample_clause make_sample_clause(org.antlr.runtime.tree.Tree BLOCK_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.seed_part seed_part) {
    sample_clause _result = new sample_clause();
    _result.set_BLOCK_VK(BLOCK_VK);
    _result.set_expression(expression);
    _result.set_seed_part(seed_part);
    return _result;
  }

  public static boolean canParseseed_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SEED_VK;
  }

  public static seed_part parseseed_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseseed_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    seed_part _result = new seed_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static seed_part make_seed_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    seed_part _result = new seed_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsecursor_manipulation_statements(org.antlr.runtime.tree.Tree tree) {
    return canParseclose_statement(tree) || canParseopen_statement(tree) || canParsefetch_statement(tree) || canParseopen_for_statement(tree);
  }

  public static cursor_manipulation_statements parsecursor_manipulation_statements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecursor_manipulation_statements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseclose_statement(tree)) return parseclose_statement(tree);
    if (canParseopen_statement(tree)) return parseopen_statement(tree);
    if (canParsefetch_statement(tree)) return parsefetch_statement(tree);
    if (canParseopen_for_statement(tree)) return parseopen_for_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseclose_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CLOSE_VK;
  }

  public static close_statement parseclose_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseclose_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    close_statement _result = new close_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static close_statement make_close_statement(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name) {
    close_statement _result = new close_statement();
    _result.set_cursor_name(cursor_name);
    return _result;
  }

  public static boolean canParseopen_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OPEN_VK;
  }

  public static open_statement parseopen_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseopen_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    open_statement _result = new open_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_list(tree.getChild(_i)))) {
      _result.set_expression_list(parseexpression_list(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static open_statement make_open_statement(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name,
      ru.barsopen.plsqlconverter.ast.typed.expression_list expression_list) {
    open_statement _result = new open_statement();
    _result.set_cursor_name(cursor_name);
    _result.set_expression_list(expression_list);
    return _result;
  }

  public static boolean canParsefetch_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FETCH;
  }

  public static fetch_statement parsefetch_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefetch_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    fetch_statement _result = new fetch_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseinto_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_into_clause(parseinto_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static fetch_statement make_fetch_statement(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name,
      ru.barsopen.plsqlconverter.ast.typed.into_clause into_clause) {
    fetch_statement _result = new fetch_statement();
    _result.set_cursor_name(cursor_name);
    _result.set_into_clause(into_clause);
    return _result;
  }

  public static boolean canParseopen_for_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OPEN_VK;
  }

  public static open_for_statement parseopen_for_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseopen_for_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    open_for_statement _result = new open_for_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsevariable_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_variable_name(parsevariable_name(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_select_statement(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_select_statement(parseexpression_or_select_statement(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseusing_clause(tree.getChild(_i)))) {
      _result.set_using_clause(parseusing_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static open_for_statement make_open_for_statement(ru.barsopen.plsqlconverter.ast.typed.variable_name variable_name,
      ru.barsopen.plsqlconverter.ast.typed.expression_or_select_statement expression_or_select_statement,
      ru.barsopen.plsqlconverter.ast.typed.using_clause using_clause) {
    open_for_statement _result = new open_for_statement();
    _result.set_variable_name(variable_name);
    _result.set_expression_or_select_statement(expression_or_select_statement);
    _result.set_using_clause(using_clause);
    return _result;
  }

  public static boolean canParseexpression_or_select_statement(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree) || canParseselect_statement(tree);
  }

  public static expression_or_select_statement parseexpression_or_select_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_or_select_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParseselect_statement(tree)) return parseselect_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsetransaction_control_statements(org.antlr.runtime.tree.Tree tree) {
    return canParseset_transaction_command(tree) || canParseset_constraint_command(tree) || canParsecommit_statement(tree) || canParserollback_statement(tree) || canParsesavepoint_statement(tree);
  }

  public static transaction_control_statements parsetransaction_control_statements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetransaction_control_statements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseset_transaction_command(tree)) return parseset_transaction_command(tree);
    if (canParseset_constraint_command(tree)) return parseset_constraint_command(tree);
    if (canParsecommit_statement(tree)) return parsecommit_statement(tree);
    if (canParserollback_statement(tree)) return parserollback_statement(tree);
    if (canParsesavepoint_statement(tree)) return parsesavepoint_statement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseset_transaction_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SET_TRANSACTION;
  }

  public static set_transaction_command parseset_transaction_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParseset_transaction_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    set_transaction_command _result = new set_transaction_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static set_transaction_command make_set_transaction_command() {
    set_transaction_command _result = new set_transaction_command();
    return _result;
  }

  public static boolean canParseset_constraint_command(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SET_CONSTRAINT;
  }

  public static set_constraint_command parseset_constraint_command(org.antlr.runtime.tree.Tree tree) {
    if (!canParseset_constraint_command(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    set_constraint_command _result = new set_constraint_command();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static set_constraint_command make_set_constraint_command() {
    set_constraint_command _result = new set_constraint_command();
    return _result;
  }

  public static boolean canParsecommit_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COMMIT_VK;
  }

  public static commit_statement parsecommit_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    commit_statement _result = new commit_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WORK_VK)) {
      _result.set_WORK_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsecommit_statement_additional(tree.getChild(_i)))) {
      _result.set_commit_statement_additional(parsecommit_statement_additional(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsewrite_clause(tree.getChild(_i)))) {
      _result.set_write_clause(parsewrite_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static commit_statement make_commit_statement(org.antlr.runtime.tree.Tree WORK_VK,
      ru.barsopen.plsqlconverter.ast.typed.commit_statement_additional commit_statement_additional,
      ru.barsopen.plsqlconverter.ast.typed.write_clause write_clause) {
    commit_statement _result = new commit_statement();
    _result.set_WORK_VK(WORK_VK);
    _result.set_commit_statement_additional(commit_statement_additional);
    _result.set_write_clause(write_clause);
    return _result;
  }

  public static boolean canParsecommit_statement_additional(org.antlr.runtime.tree.Tree tree) {
    return canParsecommit_comment(tree) || canParsecommit_force(tree);
  }

  public static commit_statement_additional parsecommit_statement_additional(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_statement_additional(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecommit_comment(tree)) return parsecommit_comment(tree);
    if (canParsecommit_force(tree)) return parsecommit_force(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecommit_comment(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COMMENT_VK;
  }

  public static commit_comment parsecommit_comment(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_comment(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    commit_comment _result = new commit_comment();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_comment_expr(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static commit_comment make_commit_comment(ru.barsopen.plsqlconverter.ast.typed.expression comment_expr) {
    commit_comment _result = new commit_comment();
    _result.set_comment_expr(comment_expr);
    return _result;
  }

  public static boolean canParsecommit_force(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FORCE_VK;
  }

  public static commit_force parsecommit_force(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_force(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    commit_force _result = new commit_force();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecommit_force_content(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_commit_force_content(parsecommit_force_content(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static commit_force make_commit_force(ru.barsopen.plsqlconverter.ast.typed.commit_force_content commit_force_content) {
    commit_force _result = new commit_force();
    _result.set_commit_force_content(commit_force_content);
    return _result;
  }

  public static boolean canParsecommit_force_content(org.antlr.runtime.tree.Tree tree) {
    return canParsecommit_force_xid(tree) || canParsecommit_force_xid_all(tree) || canParsecommit_force_exprs(tree);
  }

  public static commit_force_content parsecommit_force_content(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_force_content(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecommit_force_xid(tree)) return parsecommit_force_xid(tree);
    if (canParsecommit_force_xid_all(tree)) return parsecommit_force_xid_all(tree);
    if (canParsecommit_force_exprs(tree)) return parsecommit_force_exprs(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecommit_force_xid(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CORRUPT_XID_VK;
  }

  public static commit_force_xid parsecommit_force_xid(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_force_xid(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    commit_force_xid _result = new commit_force_xid();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static commit_force_xid make_commit_force_xid(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    commit_force_xid _result = new commit_force_xid();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsecommit_force_xid_all(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CORRUPT_XID_ALL_VK;
  }

  public static commit_force_xid_all parsecommit_force_xid_all(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_force_xid_all(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    commit_force_xid_all _result = new commit_force_xid_all();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static commit_force_xid_all make_commit_force_xid_all() {
    commit_force_xid_all _result = new commit_force_xid_all();
    return _result;
  }

  public static boolean canParsecommit_force_exprs(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree);
  }

  public static commit_force_exprs parsecommit_force_exprs(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecommit_force_exprs(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewrite_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WRITE_VK;
  }

  public static write_clause parsewrite_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewrite_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    write_clause _result = new write_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WAIT_VK)) {
      _result.set_WAIT_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_RESERVED_NOWAIT)) {
      _result.set_PLSQL_RESERVED_NOWAIT(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IMMEDIATE_VK)) {
      _result.set_IMMEDIATE_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BATCH_VK)) {
      _result.set_BATCH_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static write_clause make_write_clause(org.antlr.runtime.tree.Tree WAIT_VK,
      org.antlr.runtime.tree.Tree PLSQL_RESERVED_NOWAIT,
      org.antlr.runtime.tree.Tree IMMEDIATE_VK,
      org.antlr.runtime.tree.Tree BATCH_VK) {
    write_clause _result = new write_clause();
    _result.set_WAIT_VK(WAIT_VK);
    _result.set_PLSQL_RESERVED_NOWAIT(PLSQL_RESERVED_NOWAIT);
    _result.set_IMMEDIATE_VK(IMMEDIATE_VK);
    _result.set_BATCH_VK(BATCH_VK);
    return _result;
  }

  public static boolean canParserollback_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROLLBACK_VK;
  }

  public static rollback_statement parserollback_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParserollback_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    rollback_statement _result = new rollback_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WORK_VK)) {
      _result.set_WORK_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParserollback_statement_additional(tree.getChild(_i)))) {
      _result.set_rollback_statement_additional(parserollback_statement_additional(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static rollback_statement make_rollback_statement(org.antlr.runtime.tree.Tree WORK_VK,
      ru.barsopen.plsqlconverter.ast.typed.rollback_statement_additional rollback_statement_additional) {
    rollback_statement _result = new rollback_statement();
    _result.set_WORK_VK(WORK_VK);
    _result.set_rollback_statement_additional(rollback_statement_additional);
    return _result;
  }

  public static boolean canParserollback_statement_additional(org.antlr.runtime.tree.Tree tree) {
    return canParserollback_statement_to(tree) || canParserollback_statement_force(tree);
  }

  public static rollback_statement_additional parserollback_statement_additional(org.antlr.runtime.tree.Tree tree) {
    if (!canParserollback_statement_additional(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParserollback_statement_to(tree)) return parserollback_statement_to(tree);
    if (canParserollback_statement_force(tree)) return parserollback_statement_force(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParserollback_statement_to(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_TO;
  }

  public static rollback_statement_to parserollback_statement_to(org.antlr.runtime.tree.Tree tree) {
    if (!canParserollback_statement_to(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    rollback_statement_to _result = new rollback_statement_to();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsesavepoint_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_savepoint_name(parsesavepoint_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static rollback_statement_to make_rollback_statement_to(ru.barsopen.plsqlconverter.ast.typed.savepoint_name savepoint_name) {
    rollback_statement_to _result = new rollback_statement_to();
    _result.set_savepoint_name(savepoint_name);
    return _result;
  }

  public static boolean canParserollback_statement_force(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FORCE_VK;
  }

  public static rollback_statement_force parserollback_statement_force(org.antlr.runtime.tree.Tree tree) {
    if (!canParserollback_statement_force(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    rollback_statement_force _result = new rollback_statement_force();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CHAR_STRING))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_CHAR_STRING(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static rollback_statement_force make_rollback_statement_force(org.antlr.runtime.tree.Tree CHAR_STRING) {
    rollback_statement_force _result = new rollback_statement_force();
    _result.set_CHAR_STRING(CHAR_STRING);
    return _result;
  }

  public static boolean canParsesavepoint_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SAVEPOINT_VK;
  }

  public static savepoint_statement parsesavepoint_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesavepoint_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    savepoint_statement _result = new savepoint_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsesavepoint_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_savepoint_name(parsesavepoint_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static savepoint_statement make_savepoint_statement(ru.barsopen.plsqlconverter.ast.typed.savepoint_name savepoint_name) {
    savepoint_statement _result = new savepoint_statement();
    _result.set_savepoint_name(savepoint_name);
    return _result;
  }

  public static boolean canParsepipe_row_statement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PIPE_ROW;
  }

  public static pipe_row_statement parsepipe_row_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepipe_row_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    pipe_row_statement _result = new pipe_row_statement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static pipe_row_statement make_pipe_row_statement(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    pipe_row_statement _result = new pipe_row_statement();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseexpression_list(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXPR_LIST;
  }

  public static expression_list parseexpression_list(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_list(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_list _result = new expression_list();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.add_expressions(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_list make_expression_list(java.util.List<ru.barsopen.plsqlconverter.ast.typed.expression> expressions) {
    expression_list _result = new expression_list();
    if (expressions != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.expression _value: expressions) { _result.add_expressions(_value); }
    }
    return _result;
  }

  public static boolean canParseexpression(org.antlr.runtime.tree.Tree tree) {
    return canParselogic_expression(tree) || canParsegeneral_expression(tree);
  }

  public static expression parseexpression(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParselogic_expression(tree)) return parselogic_expression(tree);
    if (canParsegeneral_expression(tree)) return parsegeneral_expression(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParselogic_expression(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LOGIC_EXPR;
  }

  public static logic_expression parselogic_expression(org.antlr.runtime.tree.Tree tree) {
    if (!canParselogic_expression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    logic_expression _result = new logic_expression();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_element(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static logic_expression make_logic_expression(ru.barsopen.plsqlconverter.ast.typed.expression_element expression_element) {
    logic_expression _result = new logic_expression();
    _result.set_expression_element(expression_element);
    return _result;
  }

  public static boolean canParsegeneral_expression(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXPR;
  }

  public static general_expression parsegeneral_expression(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_expression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    general_expression _result = new general_expression();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_element(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static general_expression make_general_expression(ru.barsopen.plsqlconverter.ast.typed.expression_element expression_element) {
    general_expression _result = new general_expression();
    _result.set_expression_element(expression_element);
    return _result;
  }

  public static boolean canParseexpression_element(org.antlr.runtime.tree.Tree tree) {
    return canParsecase_statement(tree) || canParseconstant(tree) || canParsegeneral_element(tree) || canParsehosted_variable_name(tree) || canParsesubquery(tree) || canParseexpression_element_or(tree) || canParseexpression_element_and(tree) || canParseexpression_element_eq(tree) || canParseexpression_element_neq(tree) || canParseexpression_element_lt(tree) || canParseexpression_element_gt(tree) || canParseexpression_element_lte(tree) || canParseexpression_element_gte(tree) || canParseexpression_element_concat(tree) || canParseexpression_element_plus(tree) || canParseexpression_element_minus(tree) || canParseexpression_element_asterisk(tree) || canParseexpression_element_solidus(tree) || canParseexpression_element_percent(tree) || canParseexpression_element_mod(tree) || canParseexpression_element_div(tree) || canParseexpression_element_not(tree) || canParseexpression_element_not_null(tree) || canParseexpression_element_null(tree) || canParseexpression_element_not_nan(tree) || canParseexpression_element_nan(tree) || canParseexpression_element_not_present(tree) || canParseexpression_element_present(tree) || canParseexpression_element_not_infinite(tree) || canParseexpression_element_infinite(tree) || canParseexpression_element_not_a_set(tree) || canParseexpression_element_a_set(tree) || canParseexpression_element_not_empty(tree) || canParseexpression_element_empty(tree) || canParseexpression_element_not_in(tree) || canParseexpression_element_in(tree) || canParseexpression_element_not_between(tree) || canParseexpression_element_between(tree) || canParseexpression_element_like(tree) || canParseexpression_element_likec(tree) || canParseexpression_element_like2(tree) || canParseexpression_element_like4(tree) || canParseexpression_element_not_like(tree) || canParseexpression_element_unary(tree) || canParseexpression_element_prior(tree) || canParseexpression_element_stanrd(tree) || canParseexpression_element_some(tree) || canParseexpression_element_exists(tree) || canParseexpression_element_all(tree) || canParseexpression_element_any(tree) || canParseexpression_element_dot_asterisk(tree) || canParseexpression_element_found(tree) || canParseexpression_element_notfound(tree) || canParseexpression_element_rowcount(tree) || canParseexpression_element_isopen(tree) || canParseexpression_element_outer_join_sign(tree);
  }

  public static expression_element parseexpression_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecase_statement(tree)) return parsecase_statement(tree);
    if (canParseconstant(tree)) return parseconstant(tree);
    if (canParsegeneral_element(tree)) return parsegeneral_element(tree);
    if (canParsehosted_variable_name(tree)) return parsehosted_variable_name(tree);
    if (canParsesubquery(tree)) return parsesubquery(tree);
    if (canParseexpression_element_or(tree)) return parseexpression_element_or(tree);
    if (canParseexpression_element_and(tree)) return parseexpression_element_and(tree);
    if (canParseexpression_element_eq(tree)) return parseexpression_element_eq(tree);
    if (canParseexpression_element_neq(tree)) return parseexpression_element_neq(tree);
    if (canParseexpression_element_lt(tree)) return parseexpression_element_lt(tree);
    if (canParseexpression_element_gt(tree)) return parseexpression_element_gt(tree);
    if (canParseexpression_element_lte(tree)) return parseexpression_element_lte(tree);
    if (canParseexpression_element_gte(tree)) return parseexpression_element_gte(tree);
    if (canParseexpression_element_concat(tree)) return parseexpression_element_concat(tree);
    if (canParseexpression_element_plus(tree)) return parseexpression_element_plus(tree);
    if (canParseexpression_element_minus(tree)) return parseexpression_element_minus(tree);
    if (canParseexpression_element_asterisk(tree)) return parseexpression_element_asterisk(tree);
    if (canParseexpression_element_solidus(tree)) return parseexpression_element_solidus(tree);
    if (canParseexpression_element_percent(tree)) return parseexpression_element_percent(tree);
    if (canParseexpression_element_mod(tree)) return parseexpression_element_mod(tree);
    if (canParseexpression_element_div(tree)) return parseexpression_element_div(tree);
    if (canParseexpression_element_not(tree)) return parseexpression_element_not(tree);
    if (canParseexpression_element_not_null(tree)) return parseexpression_element_not_null(tree);
    if (canParseexpression_element_null(tree)) return parseexpression_element_null(tree);
    if (canParseexpression_element_not_nan(tree)) return parseexpression_element_not_nan(tree);
    if (canParseexpression_element_nan(tree)) return parseexpression_element_nan(tree);
    if (canParseexpression_element_not_present(tree)) return parseexpression_element_not_present(tree);
    if (canParseexpression_element_present(tree)) return parseexpression_element_present(tree);
    if (canParseexpression_element_not_infinite(tree)) return parseexpression_element_not_infinite(tree);
    if (canParseexpression_element_infinite(tree)) return parseexpression_element_infinite(tree);
    if (canParseexpression_element_not_a_set(tree)) return parseexpression_element_not_a_set(tree);
    if (canParseexpression_element_a_set(tree)) return parseexpression_element_a_set(tree);
    if (canParseexpression_element_not_empty(tree)) return parseexpression_element_not_empty(tree);
    if (canParseexpression_element_empty(tree)) return parseexpression_element_empty(tree);
    if (canParseexpression_element_not_in(tree)) return parseexpression_element_not_in(tree);
    if (canParseexpression_element_in(tree)) return parseexpression_element_in(tree);
    if (canParseexpression_element_not_between(tree)) return parseexpression_element_not_between(tree);
    if (canParseexpression_element_between(tree)) return parseexpression_element_between(tree);
    if (canParseexpression_element_like(tree)) return parseexpression_element_like(tree);
    if (canParseexpression_element_likec(tree)) return parseexpression_element_likec(tree);
    if (canParseexpression_element_like2(tree)) return parseexpression_element_like2(tree);
    if (canParseexpression_element_like4(tree)) return parseexpression_element_like4(tree);
    if (canParseexpression_element_not_like(tree)) return parseexpression_element_not_like(tree);
    if (canParseexpression_element_unary(tree)) return parseexpression_element_unary(tree);
    if (canParseexpression_element_prior(tree)) return parseexpression_element_prior(tree);
    if (canParseexpression_element_stanrd(tree)) return parseexpression_element_stanrd(tree);
    if (canParseexpression_element_some(tree)) return parseexpression_element_some(tree);
    if (canParseexpression_element_exists(tree)) return parseexpression_element_exists(tree);
    if (canParseexpression_element_all(tree)) return parseexpression_element_all(tree);
    if (canParseexpression_element_any(tree)) return parseexpression_element_any(tree);
    if (canParseexpression_element_dot_asterisk(tree)) return parseexpression_element_dot_asterisk(tree);
    if (canParseexpression_element_found(tree)) return parseexpression_element_found(tree);
    if (canParseexpression_element_notfound(tree)) return parseexpression_element_notfound(tree);
    if (canParseexpression_element_rowcount(tree)) return parseexpression_element_rowcount(tree);
    if (canParseexpression_element_isopen(tree)) return parseexpression_element_isopen(tree);
    if (canParseexpression_element_outer_join_sign(tree)) return parseexpression_element_outer_join_sign(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseexpression_element_or(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_OR;
  }

  public static expression_element_or parseexpression_element_or(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_or(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_or _result = new expression_element_or();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_or make_expression_element_or(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_or _result = new expression_element_or();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_and(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_AND;
  }

  public static expression_element_and parseexpression_element_and(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_and(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_and _result = new expression_element_and();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_and make_expression_element_and(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_and _result = new expression_element_and();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_eq(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EQUALS_OP;
  }

  public static expression_element_eq parseexpression_element_eq(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_eq(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_eq _result = new expression_element_eq();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_eq make_expression_element_eq(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_eq _result = new expression_element_eq();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_neq(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_EQUAL_OP;
  }

  public static expression_element_neq parseexpression_element_neq(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_neq(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_neq _result = new expression_element_neq();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_neq make_expression_element_neq(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_neq _result = new expression_element_neq();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_lt(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LESS_THAN_OP;
  }

  public static expression_element_lt parseexpression_element_lt(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_lt(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_lt _result = new expression_element_lt();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_lt make_expression_element_lt(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_lt _result = new expression_element_lt();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_gt(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.GREATER_THAN_OP;
  }

  public static expression_element_gt parseexpression_element_gt(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_gt(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_gt _result = new expression_element_gt();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_gt make_expression_element_gt(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_gt _result = new expression_element_gt();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_lte(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LESS_THAN_OR_EQUALS_OP;
  }

  public static expression_element_lte parseexpression_element_lte(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_lte(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_lte _result = new expression_element_lte();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_lte make_expression_element_lte(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_lte _result = new expression_element_lte();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_gte(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.GREATER_THAN_OR_EQUALS_OP;
  }

  public static expression_element_gte parseexpression_element_gte(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_gte(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_gte _result = new expression_element_gte();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_gte make_expression_element_gte(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_gte _result = new expression_element_gte();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_concat(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONCATENATION_OP;
  }

  public static expression_element_concat parseexpression_element_concat(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_concat(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_concat _result = new expression_element_concat();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_concat make_expression_element_concat(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_concat _result = new expression_element_concat();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_plus(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLUS_SIGN;
  }

  public static expression_element_plus parseexpression_element_plus(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_plus(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_plus _result = new expression_element_plus();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_plus make_expression_element_plus(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_plus _result = new expression_element_plus();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_minus(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MINUS_SIGN;
  }

  public static expression_element_minus parseexpression_element_minus(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_minus(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_minus _result = new expression_element_minus();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_minus make_expression_element_minus(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_minus _result = new expression_element_minus();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_asterisk(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASTERISK;
  }

  public static expression_element_asterisk parseexpression_element_asterisk(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_asterisk(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_asterisk _result = new expression_element_asterisk();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_asterisk make_expression_element_asterisk(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_asterisk _result = new expression_element_asterisk();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_solidus(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SOLIDUS;
  }

  public static expression_element_solidus parseexpression_element_solidus(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_solidus(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_solidus _result = new expression_element_solidus();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_solidus make_expression_element_solidus(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_solidus _result = new expression_element_solidus();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_percent(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT;
  }

  public static expression_element_percent parseexpression_element_percent(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_percent(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_percent _result = new expression_element_percent();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_percent make_expression_element_percent(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_percent _result = new expression_element_percent();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_mod(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MOD_VK;
  }

  public static expression_element_mod parseexpression_element_mod(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_mod(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_mod _result = new expression_element_mod();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_mod make_expression_element_mod(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_mod _result = new expression_element_mod();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_div(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DIV_VK;
  }

  public static expression_element_div parseexpression_element_div(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_div(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_div _result = new expression_element_div();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_lhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_rhs(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_div make_expression_element_div(ru.barsopen.plsqlconverter.ast.typed.expression_element lhs,
      ru.barsopen.plsqlconverter.ast.typed.expression_element rhs) {
    expression_element_div _result = new expression_element_div();
    _result.set_lhs(lhs);
    _result.set_rhs(rhs);
    return _result;
  }

  public static boolean canParseexpression_element_not(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NOT;
  }

  public static expression_element_not parseexpression_element_not(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not _result = new expression_element_not();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not make_expression_element_not(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not _result = new expression_element_not();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_null(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_NULL;
  }

  public static expression_element_not_null parseexpression_element_not_null(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_null(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_null _result = new expression_element_not_null();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_null make_expression_element_not_null(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_null _result = new expression_element_not_null();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_null(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NULL;
  }

  public static expression_element_null parseexpression_element_null(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_null(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_null _result = new expression_element_null();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_null make_expression_element_null(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_null _result = new expression_element_null();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_nan(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_NAN;
  }

  public static expression_element_not_nan parseexpression_element_not_nan(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_nan(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_nan _result = new expression_element_not_nan();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_nan make_expression_element_not_nan(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_nan _result = new expression_element_not_nan();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_nan(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NAN;
  }

  public static expression_element_nan parseexpression_element_nan(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_nan(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_nan _result = new expression_element_nan();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_nan make_expression_element_nan(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_nan _result = new expression_element_nan();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_present(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_PRESENT;
  }

  public static expression_element_not_present parseexpression_element_not_present(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_present(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_present _result = new expression_element_not_present();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_present make_expression_element_not_present(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_present _result = new expression_element_not_present();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_present(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_PRESENT;
  }

  public static expression_element_present parseexpression_element_present(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_present(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_present _result = new expression_element_present();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_present make_expression_element_present(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_present _result = new expression_element_present();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_infinite(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_INFINITE;
  }

  public static expression_element_not_infinite parseexpression_element_not_infinite(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_infinite(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_infinite _result = new expression_element_not_infinite();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_infinite make_expression_element_not_infinite(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_infinite _result = new expression_element_not_infinite();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_infinite(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_INFINITE;
  }

  public static expression_element_infinite parseexpression_element_infinite(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_infinite(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_infinite _result = new expression_element_infinite();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_infinite make_expression_element_infinite(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_infinite _result = new expression_element_infinite();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_a_set(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_A_SET;
  }

  public static expression_element_not_a_set parseexpression_element_not_a_set(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_a_set(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_a_set _result = new expression_element_not_a_set();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_a_set make_expression_element_not_a_set(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_a_set _result = new expression_element_not_a_set();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_a_set(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_A_SET;
  }

  public static expression_element_a_set parseexpression_element_a_set(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_a_set(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_a_set _result = new expression_element_a_set();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_a_set make_expression_element_a_set(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_a_set _result = new expression_element_a_set();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_empty(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_NOT_EMPTY;
  }

  public static expression_element_not_empty parseexpression_element_not_empty(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_empty(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_empty _result = new expression_element_not_empty();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_empty make_expression_element_not_empty(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_not_empty _result = new expression_element_not_empty();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_empty(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IS_EMPTY;
  }

  public static expression_element_empty parseexpression_element_empty(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_empty(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_empty _result = new expression_element_empty();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_empty make_expression_element_empty(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_empty _result = new expression_element_empty();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_not_in(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_IN;
  }

  public static expression_element_not_in parseexpression_element_not_in(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_in(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_in _result = new expression_element_not_in();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsein_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_in_elements(parsein_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_in make_expression_element_not_in(ru.barsopen.plsqlconverter.ast.typed.expression_element arg,
      ru.barsopen.plsqlconverter.ast.typed.in_elements in_elements) {
    expression_element_not_in _result = new expression_element_not_in();
    _result.set_arg(arg);
    _result.set_in_elements(in_elements);
    return _result;
  }

  public static boolean canParseexpression_element_in(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_IN;
  }

  public static expression_element_in parseexpression_element_in(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_in(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_in _result = new expression_element_in();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsein_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_in_elements(parsein_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_in make_expression_element_in(ru.barsopen.plsqlconverter.ast.typed.expression_element arg,
      ru.barsopen.plsqlconverter.ast.typed.in_elements in_elements) {
    expression_element_in _result = new expression_element_in();
    _result.set_arg(arg);
    _result.set_in_elements(in_elements);
    return _result;
  }

  public static boolean canParseexpression_element_not_between(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_BETWEEN;
  }

  public static expression_element_not_between parseexpression_element_not_between(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_between(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_between _result = new expression_element_not_between();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_low(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_high(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_between make_expression_element_not_between(ru.barsopen.plsqlconverter.ast.typed.expression_element arg,
      ru.barsopen.plsqlconverter.ast.typed.expression_element expr_low,
      ru.barsopen.plsqlconverter.ast.typed.expression_element expr_high) {
    expression_element_not_between _result = new expression_element_not_between();
    _result.set_arg(arg);
    _result.set_expr_low(expr_low);
    _result.set_expr_high(expr_high);
    return _result;
  }

  public static boolean canParseexpression_element_between(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_BETWEEN;
  }

  public static expression_element_between parseexpression_element_between(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_between(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_between _result = new expression_element_between();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_low(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_high(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_between make_expression_element_between(ru.barsopen.plsqlconverter.ast.typed.expression_element arg,
      ru.barsopen.plsqlconverter.ast.typed.expression_element expr_low,
      ru.barsopen.plsqlconverter.ast.typed.expression_element expr_high) {
    expression_element_between _result = new expression_element_between();
    _result.set_arg(arg);
    _result.set_expr_low(expr_low);
    _result.set_expr_high(expr_high);
    return _result;
  }

  public static boolean canParseexpression_element_like(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_LIKE;
  }

  public static expression_element_like parseexpression_element_like(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_like(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_like _result = new expression_element_like();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pattern(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_escape(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_like make_expression_element_like(ru.barsopen.plsqlconverter.ast.typed.expression_element text,
      ru.barsopen.plsqlconverter.ast.typed.expression_element pattern,
      ru.barsopen.plsqlconverter.ast.typed.expression_element escape) {
    expression_element_like _result = new expression_element_like();
    _result.set_text(text);
    _result.set_pattern(pattern);
    _result.set_escape(escape);
    return _result;
  }

  public static boolean canParseexpression_element_likec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LIKEC_VK;
  }

  public static expression_element_likec parseexpression_element_likec(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_likec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_likec _result = new expression_element_likec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pattern(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_escape(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_likec make_expression_element_likec(ru.barsopen.plsqlconverter.ast.typed.expression_element text,
      ru.barsopen.plsqlconverter.ast.typed.expression_element pattern,
      ru.barsopen.plsqlconverter.ast.typed.expression_element escape) {
    expression_element_likec _result = new expression_element_likec();
    _result.set_text(text);
    _result.set_pattern(pattern);
    _result.set_escape(escape);
    return _result;
  }

  public static boolean canParseexpression_element_like2(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LIKE2_VK;
  }

  public static expression_element_like2 parseexpression_element_like2(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_like2(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_like2 _result = new expression_element_like2();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pattern(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_escape(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_like2 make_expression_element_like2(ru.barsopen.plsqlconverter.ast.typed.expression_element text,
      ru.barsopen.plsqlconverter.ast.typed.expression_element pattern,
      ru.barsopen.plsqlconverter.ast.typed.expression_element escape) {
    expression_element_like2 _result = new expression_element_like2();
    _result.set_text(text);
    _result.set_pattern(pattern);
    _result.set_escape(escape);
    return _result;
  }

  public static boolean canParseexpression_element_like4(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LIKE4_VK;
  }

  public static expression_element_like4 parseexpression_element_like4(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_like4(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_like4 _result = new expression_element_like4();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pattern(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_escape(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_like4 make_expression_element_like4(ru.barsopen.plsqlconverter.ast.typed.expression_element text,
      ru.barsopen.plsqlconverter.ast.typed.expression_element pattern,
      ru.barsopen.plsqlconverter.ast.typed.expression_element escape) {
    expression_element_like4 _result = new expression_element_like4();
    _result.set_text(text);
    _result.set_pattern(pattern);
    _result.set_escape(escape);
    return _result;
  }

  public static boolean canParseexpression_element_not_like(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOT_LIKE;
  }

  public static expression_element_not_like parseexpression_element_not_like(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_not_like(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_not_like _result = new expression_element_not_like();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_pattern(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_escape(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_not_like make_expression_element_not_like(ru.barsopen.plsqlconverter.ast.typed.expression_element text,
      ru.barsopen.plsqlconverter.ast.typed.expression_element pattern,
      ru.barsopen.plsqlconverter.ast.typed.expression_element escape) {
    expression_element_not_like _result = new expression_element_not_like();
    _result.set_text(text);
    _result.set_pattern(pattern);
    _result.set_escape(escape);
    return _result;
  }

  public static boolean canParseexpression_element_unary(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNARY_OPERATOR;
  }

  public static expression_element_unary parseexpression_element_unary(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_unary(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_unary _result = new expression_element_unary();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.op = tree.getText();

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_unary make_expression_element_unary(String op,
      ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_unary _result = new expression_element_unary();
    _result.set_op(op);
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_prior(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_PRIOR;
  }

  public static expression_element_prior parseexpression_element_prior(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_prior(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_prior _result = new expression_element_prior();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_arg(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_prior make_expression_element_prior(ru.barsopen.plsqlconverter.ast.typed.expression_element arg) {
    expression_element_prior _result = new expression_element_prior();
    _result.set_arg(arg);
    return _result;
  }

  public static boolean canParseexpression_element_stanrd(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.STANDARD_FUNCTION;
  }

  public static expression_element_stanrd parseexpression_element_stanrd(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_stanrd(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_stanrd _result = new expression_element_stanrd();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsestandard_function(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_standard_function(parsestandard_function(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_stanrd make_expression_element_stanrd(ru.barsopen.plsqlconverter.ast.typed.standard_function standard_function) {
    expression_element_stanrd _result = new expression_element_stanrd();
    _result.set_standard_function(standard_function);
    return _result;
  }

  public static boolean canParseexpression_element_some(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SOME_VK;
  }

  public static expression_element_some parseexpression_element_some(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_some(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_some _result = new expression_element_some();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_some make_expression_element_some(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    expression_element_some _result = new expression_element_some();
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParseexpression_element_exists(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_EXISTS;
  }

  public static expression_element_exists parseexpression_element_exists(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_exists(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_exists _result = new expression_element_exists();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_exists make_expression_element_exists(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    expression_element_exists _result = new expression_element_exists();
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParseexpression_element_all(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL;
  }

  public static expression_element_all parseexpression_element_all(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_all(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_all _result = new expression_element_all();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_all make_expression_element_all(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    expression_element_all _result = new expression_element_all();
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParseexpression_element_any(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ANY;
  }

  public static expression_element_any parseexpression_element_any(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_any(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_any _result = new expression_element_any();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_any make_expression_element_any(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery) {
    expression_element_any _result = new expression_element_any();
    _result.set_expression_or_subquery(expression_or_subquery);
    return _result;
  }

  public static boolean canParseexpression_element_dot_asterisk(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DOT_ASTERISK;
  }

  public static expression_element_dot_asterisk parseexpression_element_dot_asterisk(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_dot_asterisk(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_dot_asterisk _result = new expression_element_dot_asterisk();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetableview_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_tableview_name(parsetableview_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_dot_asterisk make_expression_element_dot_asterisk(ru.barsopen.plsqlconverter.ast.typed.tableview_name tableview_name) {
    expression_element_dot_asterisk _result = new expression_element_dot_asterisk();
    _result.set_tableview_name(tableview_name);
    return _result;
  }

  public static boolean canParseexpression_element_found(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_FOUND_VK;
  }

  public static expression_element_found parseexpression_element_found(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_found(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_found _result = new expression_element_found();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_found make_expression_element_found(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name) {
    expression_element_found _result = new expression_element_found();
    _result.set_cursor_name(cursor_name);
    return _result;
  }

  public static boolean canParseexpression_element_notfound(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_NOTFOUND_VK;
  }

  public static expression_element_notfound parseexpression_element_notfound(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_notfound(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_notfound _result = new expression_element_notfound();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_notfound make_expression_element_notfound(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name) {
    expression_element_notfound _result = new expression_element_notfound();
    _result.set_cursor_name(cursor_name);
    return _result;
  }

  public static boolean canParseexpression_element_rowcount(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_ROWCOUNT_VK;
  }

  public static expression_element_rowcount parseexpression_element_rowcount(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_rowcount(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_rowcount _result = new expression_element_rowcount();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_rowcount make_expression_element_rowcount(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name) {
    expression_element_rowcount _result = new expression_element_rowcount();
    _result.set_cursor_name(cursor_name);
    return _result;
  }

  public static boolean canParseexpression_element_isopen(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PERCENT_ISOPEN_VK;
  }

  public static expression_element_isopen parseexpression_element_isopen(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_isopen(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_isopen _result = new expression_element_isopen();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecursor_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_cursor_name(parsecursor_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_isopen make_expression_element_isopen(ru.barsopen.plsqlconverter.ast.typed.cursor_name cursor_name) {
    expression_element_isopen _result = new expression_element_isopen();
    _result.set_cursor_name(cursor_name);
    return _result;
  }

  public static boolean canParseexpression_element_outer_join_sign(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OUTER_JOIN_SIGN;
  }

  public static expression_element_outer_join_sign parseexpression_element_outer_join_sign(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_element_outer_join_sign(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    expression_element_outer_join_sign _result = new expression_element_outer_join_sign();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static expression_element_outer_join_sign make_expression_element_outer_join_sign(ru.barsopen.plsqlconverter.ast.typed.expression_element expr) {
    expression_element_outer_join_sign _result = new expression_element_outer_join_sign();
    _result.set_expr(expr);
    return _result;
  }

  public static boolean canParsein_elements(org.antlr.runtime.tree.Tree tree) {
    return canParsesubquery(tree) || canParseexpression(tree) || canParseexpression_list(tree);
  }

  public static in_elements parsein_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsein_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsesubquery(tree)) return parsesubquery(tree);
    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParseexpression_list(tree)) return parseexpression_list(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsedatetime_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.AT_VK;
  }

  public static datetime_element parsedatetime_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsedatetime_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    datetime_element _result = new datetime_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static datetime_element make_datetime_element() {
    datetime_element _result = new datetime_element();
    return _result;
  }

  public static boolean canParsemodel_expression(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MODEL_EXPRESSION;
  }

  public static model_expression parsemodel_expression(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_expression(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_expression _result = new model_expression();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_element(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsemodel_expression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsemodel_expression_element(tree.getChild(_i)))) {
      _result.add_model_expression_elements(parsemodel_expression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_expression make_model_expression(ru.barsopen.plsqlconverter.ast.typed.expression_element expression_element,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.model_expression_element> model_expression_elements) {
    model_expression _result = new model_expression();
    _result.set_expression_element(expression_element);
    if (model_expression_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.model_expression_element _value: model_expression_elements) { _result.add_model_expression_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsemodel_expression_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ANY;
  }

  public static model_expression_element parsemodel_expression_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemodel_expression_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    model_expression_element _result = new model_expression_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static model_expression_element make_model_expression_element() {
    model_expression_element _result = new model_expression_element();
    return _result;
  }

  public static boolean canParsefor_single_column_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_IN;
  }

  public static for_single_column_element parsefor_single_column_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_single_column_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_single_column_element _result = new for_single_column_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_single_column_element make_for_single_column_element() {
    for_single_column_element _result = new for_single_column_element();
    return _result;
  }

  public static boolean canParsefor_like_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_LIKE;
  }

  public static for_like_part parsefor_like_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefor_like_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    for_like_part _result = new for_like_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static for_like_part make_for_like_part() {
    for_like_part _result = new for_like_part();
    return _result;
  }

  public static boolean canParsecase_statement(org.antlr.runtime.tree.Tree tree) {
    return canParsecase_statement_simple(tree) || canParsecase_statement_searched(tree);
  }

  public static case_statement parsecase_statement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecase_statement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecase_statement_simple(tree)) return parsecase_statement_simple(tree);
    if (canParsecase_statement_searched(tree)) return parsecase_statement_searched(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecase_statement_simple(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SIMPLE_CASE;
  }

  public static case_statement_simple parsecase_statement_simple(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecase_statement_simple(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    case_statement_simple _result = new case_statement_simple();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsecase_when_part(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecase_when_part(tree.getChild(_i)))) {
      _result.add_case_when_parts(parsecase_when_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsecase_else_part(tree.getChild(_i)))) {
      _result.set_case_else_part(parsecase_else_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static case_statement_simple make_case_statement_simple(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.case_when_part> case_when_parts,
      ru.barsopen.plsqlconverter.ast.typed.case_else_part case_else_part) {
    case_statement_simple _result = new case_statement_simple();
    _result.set_expression(expression);
    if (case_when_parts != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.case_when_part _value: case_when_parts) { _result.add_case_when_parts(_value); }
    }
    _result.set_case_else_part(case_else_part);
    return _result;
  }

  public static boolean canParsecase_statement_searched(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SEARCHED_CASE;
  }

  public static case_statement_searched parsecase_statement_searched(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecase_statement_searched(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    case_statement_searched _result = new case_statement_searched();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsecase_when_part(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsecase_when_part(tree.getChild(_i)))) {
      _result.add_case_when_parts(parsecase_when_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsecase_else_part(tree.getChild(_i)))) {
      _result.set_case_else_part(parsecase_else_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static case_statement_searched make_case_statement_searched(java.util.List<ru.barsopen.plsqlconverter.ast.typed.case_when_part> case_when_parts,
      ru.barsopen.plsqlconverter.ast.typed.case_else_part case_else_part) {
    case_statement_searched _result = new case_statement_searched();
    if (case_when_parts != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.case_when_part _value: case_when_parts) { _result.add_case_when_parts(_value); }
    }
    _result.set_case_else_part(case_else_part);
    return _result;
  }

  public static boolean canParsecase_when_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHEN;
  }

  public static case_when_part parsecase_when_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecase_when_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    case_when_part _result = new case_when_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_condition(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_seq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_seq_of_statements(parseexpression_or_seq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static case_when_part make_case_when_part(ru.barsopen.plsqlconverter.ast.typed.expression condition,
      ru.barsopen.plsqlconverter.ast.typed.expression_or_seq_of_statements expression_or_seq_of_statements) {
    case_when_part _result = new case_when_part();
    _result.set_condition(condition);
    _result.set_expression_or_seq_of_statements(expression_or_seq_of_statements);
    return _result;
  }

  public static boolean canParseexpression_or_seq_of_statements(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree) || canParseseq_of_statements(tree);
  }

  public static expression_or_seq_of_statements parseexpression_or_seq_of_statements(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexpression_or_seq_of_statements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    if (canParseseq_of_statements(tree)) return parseseq_of_statements(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecase_else_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ELSE;
  }

  public static case_else_part parsecase_else_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecase_else_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    case_else_part _result = new case_else_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_seq_of_statements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_seq_of_statements(parseexpression_or_seq_of_statements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static case_else_part make_case_else_part(ru.barsopen.plsqlconverter.ast.typed.expression_or_seq_of_statements expression_or_seq_of_statements) {
    case_else_part _result = new case_else_part();
    _result.set_expression_or_seq_of_statements(expression_or_seq_of_statements);
    return _result;
  }

  public static boolean canParsestandard_function(org.antlr.runtime.tree.Tree tree) {
    return canParsestandard_function_enabling_over(tree) || canParsestandard_function_count(tree) || canParsestandard_function_xmlcast(tree) || canParsestandard_function_case(tree) || canParsestandard_function_enabling_within_or_over(tree) || canParsestandard_function_extract(tree) || canParsestandard_function_translate(tree) || canParsestandard_function_trim(tree) || canParsestandard_function_xmlagg(tree) || canParsestandard_function_xmlparse(tree) || canParsestandard_function_root(tree) || canParsestandard_function_table(tree) || canParsestandard_function_xmlelement(tree);
  }

  public static standard_function parsestandard_function(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsestandard_function_enabling_over(tree)) return parsestandard_function_enabling_over(tree);
    if (canParsestandard_function_count(tree)) return parsestandard_function_count(tree);
    if (canParsestandard_function_xmlcast(tree)) return parsestandard_function_xmlcast(tree);
    if (canParsestandard_function_case(tree)) return parsestandard_function_case(tree);
    if (canParsestandard_function_enabling_within_or_over(tree)) return parsestandard_function_enabling_within_or_over(tree);
    if (canParsestandard_function_extract(tree)) return parsestandard_function_extract(tree);
    if (canParsestandard_function_translate(tree)) return parsestandard_function_translate(tree);
    if (canParsestandard_function_trim(tree)) return parsestandard_function_trim(tree);
    if (canParsestandard_function_xmlagg(tree)) return parsestandard_function_xmlagg(tree);
    if (canParsestandard_function_xmlparse(tree)) return parsestandard_function_xmlparse(tree);
    if (canParsestandard_function_root(tree)) return parsestandard_function_root(tree);
    if (canParsestandard_function_table(tree)) return parsestandard_function_table(tree);
    if (canParsestandard_function_xmlelement(tree)) return parsestandard_function_xmlelement(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsestandard_function_enabling_over(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FUNCTION_ENABLING_OVER;
  }

  public static standard_function_enabling_over parsestandard_function_enabling_over(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_enabling_over(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_enabling_over _result = new standard_function_enabling_over();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.name = tree.getText();

    if (!(_i < tree.getChildCount() && (canParsefunction_argument(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_argument(parsefunction_argument(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseover_clause(tree.getChild(_i)))) {
      _result.set_over_clause(parseover_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_enabling_over make_standard_function_enabling_over(String name,
      ru.barsopen.plsqlconverter.ast.typed.function_argument function_argument,
      ru.barsopen.plsqlconverter.ast.typed.over_clause over_clause) {
    standard_function_enabling_over _result = new standard_function_enabling_over();
    _result.set_name(name);
    _result.set_function_argument(function_argument);
    _result.set_over_clause(over_clause);
    return _result;
  }

  public static boolean canParsestandard_function_count(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COUNT_VK;
  }

  public static standard_function_count parsestandard_function_count(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_count(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_count _result = new standard_function_count();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DISTINCT)) {
      _result.set_SQL92_RESERVED_DISTINCT(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_UNIQUE)) {
      _result.set_SQL92_RESERVED_UNIQUE(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ALL)) {
      _result.set_SQL92_RESERVED_ALL(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASTERISK)) {
      _result.set_ASTERISK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseover_clause(tree.getChild(_i)))) {
      _result.set_over_clause(parseover_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_count make_standard_function_count(org.antlr.runtime.tree.Tree SQL92_RESERVED_DISTINCT,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_UNIQUE,
      org.antlr.runtime.tree.Tree SQL92_RESERVED_ALL,
      org.antlr.runtime.tree.Tree ASTERISK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.over_clause over_clause) {
    standard_function_count _result = new standard_function_count();
    _result.set_SQL92_RESERVED_DISTINCT(SQL92_RESERVED_DISTINCT);
    _result.set_SQL92_RESERVED_UNIQUE(SQL92_RESERVED_UNIQUE);
    _result.set_SQL92_RESERVED_ALL(SQL92_RESERVED_ALL);
    _result.set_ASTERISK(ASTERISK);
    _result.set_expression(expression);
    _result.set_over_clause(over_clause);
    return _result;
  }

  public static boolean canParsestandard_function_xmlcast(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLCAST_VK;
  }

  public static standard_function_xmlcast parsestandard_function_xmlcast(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_xmlcast(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_xmlcast _result = new standard_function_xmlcast();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_xmlcast make_standard_function_xmlcast(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    standard_function_xmlcast _result = new standard_function_xmlcast();
    _result.set_expression(expression);
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParsestandard_function_case(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CAST_VK;
  }

  public static standard_function_case parsestandard_function_case(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_case(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_case _result = new standard_function_case();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_or_subquery(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression_or_subquery(parseexpression_or_subquery(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_case make_standard_function_case(ru.barsopen.plsqlconverter.ast.typed.expression_or_subquery expression_or_subquery,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec) {
    standard_function_case _result = new standard_function_case();
    _result.set_expression_or_subquery(expression_or_subquery);
    _result.set_type_spec(type_spec);
    return _result;
  }

  public static boolean canParsestandard_function_enabling_within_or_over(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FUNCTION_ENABLING_WITHIN_OR_OVER;
  }

  public static standard_function_enabling_within_or_over parsestandard_function_enabling_within_or_over(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_enabling_within_or_over(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_enabling_within_or_over _result = new standard_function_enabling_within_or_over();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.name = tree.getText();

    if (!(_i < tree.getChildCount() && (canParsefunction_argument(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_function_argument(parsefunction_argument(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsewithin_clause(tree.getChild(_i)))) {
      _result.set_within_clause(parsewithin_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseover_clause(tree.getChild(_i)))) {
      _result.set_over_clause(parseover_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_enabling_within_or_over make_standard_function_enabling_within_or_over(String name,
      ru.barsopen.plsqlconverter.ast.typed.function_argument function_argument,
      ru.barsopen.plsqlconverter.ast.typed.within_clause within_clause,
      ru.barsopen.plsqlconverter.ast.typed.over_clause over_clause) {
    standard_function_enabling_within_or_over _result = new standard_function_enabling_within_or_over();
    _result.set_name(name);
    _result.set_function_argument(function_argument);
    _result.set_within_clause(within_clause);
    _result.set_over_clause(over_clause);
    return _result;
  }

  public static boolean canParsestandard_function_extract(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXTRACT_VK;
  }

  public static standard_function_extract parsestandard_function_extract(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_extract(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_extract _result = new standard_function_extract();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseextract_part(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_extract_part(parseextract_part(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_extract make_standard_function_extract(ru.barsopen.plsqlconverter.ast.typed.extract_part extract_part,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    standard_function_extract _result = new standard_function_extract();
    _result.set_extract_part(extract_part);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsestandard_function_translate(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TRANSLATE_VK;
  }

  public static standard_function_translate parsestandard_function_translate(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_translate(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_translate _result = new standard_function_translate();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_from(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expr_to(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_translate make_standard_function_translate(ru.barsopen.plsqlconverter.ast.typed.expression expr,
      ru.barsopen.plsqlconverter.ast.typed.expression expr_from,
      ru.barsopen.plsqlconverter.ast.typed.expression expr_to) {
    standard_function_translate _result = new standard_function_translate();
    _result.set_expr(expr);
    _result.set_expr_from(expr_from);
    _result.set_expr_to(expr_to);
    return _result;
  }

  public static boolean canParsestandard_function_trim(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TRIM_VK;
  }

  public static standard_function_trim parsestandard_function_trim(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_trim(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_trim _result = new standard_function_trim();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_text_expr(parseexpression_element(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseexpression_element(tree.getChild(_i)))) {
      _result.set_trim_char_expr(parseexpression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LEADING_VK)) {
      _result.set_LEADING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TRAILING_VK)) {
      _result.set_TRAILING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BOTH_VK)) {
      _result.set_BOTH_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_trim make_standard_function_trim(ru.barsopen.plsqlconverter.ast.typed.expression_element text_expr,
      ru.barsopen.plsqlconverter.ast.typed.expression_element trim_char_expr,
      org.antlr.runtime.tree.Tree LEADING_VK,
      org.antlr.runtime.tree.Tree TRAILING_VK,
      org.antlr.runtime.tree.Tree BOTH_VK) {
    standard_function_trim _result = new standard_function_trim();
    _result.set_text_expr(text_expr);
    _result.set_trim_char_expr(trim_char_expr);
    _result.set_LEADING_VK(LEADING_VK);
    _result.set_TRAILING_VK(TRAILING_VK);
    _result.set_BOTH_VK(BOTH_VK);
    return _result;
  }

  public static boolean canParsestandard_function_xmlagg(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLAGG_VK;
  }

  public static standard_function_xmlagg parsestandard_function_xmlagg(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_xmlagg(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_xmlagg _result = new standard_function_xmlagg();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseorder_by_clause(tree.getChild(_i)))) {
      _result.set_order_by_clause(parseorder_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_xmlagg make_standard_function_xmlagg(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.order_by_clause order_by_clause) {
    standard_function_xmlagg _result = new standard_function_xmlagg();
    _result.set_expression(expression);
    _result.set_order_by_clause(order_by_clause);
    return _result;
  }

  public static boolean canParsestandard_function_xmlparse(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLPARSE_VK;
  }

  public static standard_function_xmlparse parsestandard_function_xmlparse(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_xmlparse(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_xmlparse _result = new standard_function_xmlparse();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsexmlparse_document_or_content(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_xmlparse_document_or_content(parsexmlparse_document_or_content(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WELLFORMED_VK)) {
      _result.set_WELLFORMED_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_xmlparse make_standard_function_xmlparse(ru.barsopen.plsqlconverter.ast.typed.xmlparse_document_or_content xmlparse_document_or_content,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      org.antlr.runtime.tree.Tree WELLFORMED_VK) {
    standard_function_xmlparse _result = new standard_function_xmlparse();
    _result.set_xmlparse_document_or_content(xmlparse_document_or_content);
    _result.set_expression(expression);
    _result.set_WELLFORMED_VK(WELLFORMED_VK);
    return _result;
  }

  public static boolean canParsexmlparse_document_or_content(org.antlr.runtime.tree.Tree tree) {
    return canParsexmlparse_document(tree) || canParsexmlparse_content(tree);
  }

  public static xmlparse_document_or_content parsexmlparse_document_or_content(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlparse_document_or_content(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsexmlparse_document(tree)) return parsexmlparse_document(tree);
    if (canParsexmlparse_content(tree)) return parsexmlparse_content(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsexmlparse_document(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DOCUMENT_VK;
  }

  public static xmlparse_document parsexmlparse_document(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlparse_document(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xmlparse_document _result = new xmlparse_document();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xmlparse_document make_xmlparse_document() {
    xmlparse_document _result = new xmlparse_document();
    return _result;
  }

  public static boolean canParsexmlparse_content(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONTENT_VK;
  }

  public static xmlparse_content parsexmlparse_content(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlparse_content(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xmlparse_content _result = new xmlparse_content();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xmlparse_content make_xmlparse_content() {
    xmlparse_content _result = new xmlparse_content();
    return _result;
  }

  public static boolean canParsestandard_function_root(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLROOT_VK;
  }

  public static standard_function_root parsestandard_function_root(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_root(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_root _result = new standard_function_root();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsexml_param_version_part(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_xml_param_version_part(parsexml_param_version_part(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsexmlroot_param_standalone_part(tree.getChild(_i)))) {
      _result.set_xmlroot_param_standalone_part(parsexmlroot_param_standalone_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_root make_standard_function_root(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.xml_param_version_part xml_param_version_part,
      ru.barsopen.plsqlconverter.ast.typed.xmlroot_param_standalone_part xmlroot_param_standalone_part) {
    standard_function_root _result = new standard_function_root();
    _result.set_expression(expression);
    _result.set_xml_param_version_part(xml_param_version_part);
    _result.set_xmlroot_param_standalone_part(xmlroot_param_standalone_part);
    return _result;
  }

  public static boolean canParsestandard_function_table(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLTABLE_VK;
  }

  public static standard_function_table parsestandard_function_table(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_table(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_table _result = new standard_function_table();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsexml_namespaces_clause(tree.getChild(_i)))) {
      _result.set_xml_namespaces_clause(parsexml_namespaces_clause(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsexml_passing_clause(tree.getChild(_i)))) {
      _result.set_xml_passing_clause(parsexml_passing_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsexml_table_column(tree.getChild(_i)))) {
      _result.add_xml_table_columns(parsexml_table_column(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_table make_standard_function_table(ru.barsopen.plsqlconverter.ast.typed.xml_namespaces_clause xml_namespaces_clause,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.xml_passing_clause xml_passing_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.xml_table_column> xml_table_columns) {
    standard_function_table _result = new standard_function_table();
    _result.set_xml_namespaces_clause(xml_namespaces_clause);
    _result.set_expression(expression);
    _result.set_xml_passing_clause(xml_passing_clause);
    if (xml_table_columns != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.xml_table_column _value: xml_table_columns) { _result.add_xml_table_columns(_value); }
    }
    return _result;
  }

  public static boolean canParsestandard_function_xmlelement(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLELEMENT_VK;
  }

  public static standard_function_xmlelement parsestandard_function_xmlelement(org.antlr.runtime.tree.Tree tree) {
    if (!canParsestandard_function_xmlelement(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    standard_function_xmlelement _result = new standard_function_xmlelement();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ENTITYESCAPING_VK)) {
      _result.set_ENTITYESCAPING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOENTITYESCAPING_VK)) {
      _result.set_NOENTITYESCAPING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NAME_VK)) {
      _result.set_NAME_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EVALNAME_VK)) {
      _result.set_EVALNAME_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsexml_attributes_clause(tree.getChild(_i)))) {
      _result.set_xml_attributes_clause(parsexml_attributes_clause(tree.getChild(_i)));
      ++_i;
    }

    while (_i < tree.getChildCount() && (canParsexmlelement_value_expr(tree.getChild(_i)))) {
      _result.add_xmlelement_value_exprs(parsexmlelement_value_expr(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static standard_function_xmlelement make_standard_function_xmlelement(org.antlr.runtime.tree.Tree ENTITYESCAPING_VK,
      org.antlr.runtime.tree.Tree NOENTITYESCAPING_VK,
      org.antlr.runtime.tree.Tree NAME_VK,
      org.antlr.runtime.tree.Tree EVALNAME_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.xml_attributes_clause xml_attributes_clause,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.xmlelement_value_expr> xmlelement_value_exprs) {
    standard_function_xmlelement _result = new standard_function_xmlelement();
    _result.set_ENTITYESCAPING_VK(ENTITYESCAPING_VK);
    _result.set_NOENTITYESCAPING_VK(NOENTITYESCAPING_VK);
    _result.set_NAME_VK(NAME_VK);
    _result.set_EVALNAME_VK(EVALNAME_VK);
    _result.set_expression(expression);
    _result.set_xml_attributes_clause(xml_attributes_clause);
    if (xmlelement_value_exprs != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.xmlelement_value_expr _value: xmlelement_value_exprs) { _result.add_xmlelement_value_exprs(_value); }
    }
    return _result;
  }

  public static boolean canParseextract_part(org.antlr.runtime.tree.Tree tree) {
    return canParseextract_part_year(tree) || canParseextract_part_month(tree) || canParseextract_part_day(tree) || canParseextract_part_hour(tree) || canParseextract_part_minute(tree) || canParseextract_part_second(tree) || canParseextract_part_tzhour(tree) || canParseextract_part_tzminute(tree) || canParseextract_part_tzabbr(tree) || canParseextract_part_tz_region(tree);
  }

  public static extract_part parseextract_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseextract_part_year(tree)) return parseextract_part_year(tree);
    if (canParseextract_part_month(tree)) return parseextract_part_month(tree);
    if (canParseextract_part_day(tree)) return parseextract_part_day(tree);
    if (canParseextract_part_hour(tree)) return parseextract_part_hour(tree);
    if (canParseextract_part_minute(tree)) return parseextract_part_minute(tree);
    if (canParseextract_part_second(tree)) return parseextract_part_second(tree);
    if (canParseextract_part_tzhour(tree)) return parseextract_part_tzhour(tree);
    if (canParseextract_part_tzminute(tree)) return parseextract_part_tzminute(tree);
    if (canParseextract_part_tzabbr(tree)) return parseextract_part_tzabbr(tree);
    if (canParseextract_part_tz_region(tree)) return parseextract_part_tz_region(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseextract_part_year(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.YEAR_VK;
  }

  public static extract_part_year parseextract_part_year(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_year(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_year _result = new extract_part_year();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_year make_extract_part_year() {
    extract_part_year _result = new extract_part_year();
    return _result;
  }

  public static boolean canParseextract_part_month(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MONTH_VK;
  }

  public static extract_part_month parseextract_part_month(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_month(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_month _result = new extract_part_month();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_month make_extract_part_month() {
    extract_part_month _result = new extract_part_month();
    return _result;
  }

  public static boolean canParseextract_part_day(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DAY_VK;
  }

  public static extract_part_day parseextract_part_day(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_day(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_day _result = new extract_part_day();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_day make_extract_part_day() {
    extract_part_day _result = new extract_part_day();
    return _result;
  }

  public static boolean canParseextract_part_hour(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.HOUR_VK;
  }

  public static extract_part_hour parseextract_part_hour(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_hour(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_hour _result = new extract_part_hour();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_hour make_extract_part_hour() {
    extract_part_hour _result = new extract_part_hour();
    return _result;
  }

  public static boolean canParseextract_part_minute(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MINUTE_VK;
  }

  public static extract_part_minute parseextract_part_minute(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_minute(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_minute _result = new extract_part_minute();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_minute make_extract_part_minute() {
    extract_part_minute _result = new extract_part_minute();
    return _result;
  }

  public static boolean canParseextract_part_second(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SECOND_VK;
  }

  public static extract_part_second parseextract_part_second(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_second(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_second _result = new extract_part_second();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_second make_extract_part_second() {
    extract_part_second _result = new extract_part_second();
    return _result;
  }

  public static boolean canParseextract_part_tzhour(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMEZONE_HOUR_VK;
  }

  public static extract_part_tzhour parseextract_part_tzhour(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_tzhour(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_tzhour _result = new extract_part_tzhour();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_tzhour make_extract_part_tzhour() {
    extract_part_tzhour _result = new extract_part_tzhour();
    return _result;
  }

  public static boolean canParseextract_part_tzminute(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMEZONE_MINUTE_VK;
  }

  public static extract_part_tzminute parseextract_part_tzminute(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_tzminute(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_tzminute _result = new extract_part_tzminute();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_tzminute make_extract_part_tzminute() {
    extract_part_tzminute _result = new extract_part_tzminute();
    return _result;
  }

  public static boolean canParseextract_part_tzabbr(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMEZONE_ABBR_VK;
  }

  public static extract_part_tzabbr parseextract_part_tzabbr(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_tzabbr(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_tzabbr _result = new extract_part_tzabbr();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_tzabbr make_extract_part_tzabbr() {
    extract_part_tzabbr _result = new extract_part_tzabbr();
    return _result;
  }

  public static boolean canParseextract_part_tz_region(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIMEZONE_REGION_VK;
  }

  public static extract_part_tz_region parseextract_part_tz_region(org.antlr.runtime.tree.Tree tree) {
    if (!canParseextract_part_tz_region(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    extract_part_tz_region _result = new extract_part_tz_region();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static extract_part_tz_region make_extract_part_tz_region() {
    extract_part_tz_region _result = new extract_part_tz_region();
    return _result;
  }

  public static boolean canParsexmlelement_value_expr(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree);
  }

  public static xmlelement_value_expr parsexmlelement_value_expr(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlelement_value_expr(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseover_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OVER_VK;
  }

  public static over_clause parseover_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseover_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    over_clause _result = new over_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsequery_partition_clause(tree.getChild(_i)))) {
      _result.set_query_partition_clause(parsequery_partition_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseorder_by_clause(tree.getChild(_i)))) {
      _result.set_order_by_clause(parseorder_by_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsewindowing_clause(tree.getChild(_i)))) {
      _result.set_windowing_clause(parsewindowing_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static over_clause make_over_clause(ru.barsopen.plsqlconverter.ast.typed.query_partition_clause query_partition_clause,
      ru.barsopen.plsqlconverter.ast.typed.order_by_clause order_by_clause,
      ru.barsopen.plsqlconverter.ast.typed.windowing_clause windowing_clause) {
    over_clause _result = new over_clause();
    _result.set_query_partition_clause(query_partition_clause);
    _result.set_order_by_clause(order_by_clause);
    _result.set_windowing_clause(windowing_clause);
    return _result;
  }

  public static boolean canParsewindowing_clause(org.antlr.runtime.tree.Tree tree) {
    return canParsewindowing_clause_rows(tree) || canParsewindowing_clause_range(tree);
  }

  public static windowing_clause parsewindowing_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsewindowing_clause_rows(tree)) return parsewindowing_clause_rows(tree);
    if (canParsewindowing_clause_range(tree)) return parsewindowing_clause_range(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewindowing_clause_rows(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROWS_VK;
  }

  public static windowing_clause_rows parsewindowing_clause_rows(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_clause_rows(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_clause_rows _result = new windowing_clause_rows();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsewindowing_clause_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_windowing_clause_spec(parsewindowing_clause_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_clause_rows make_windowing_clause_rows(ru.barsopen.plsqlconverter.ast.typed.windowing_clause_spec windowing_clause_spec) {
    windowing_clause_rows _result = new windowing_clause_rows();
    _result.set_windowing_clause_spec(windowing_clause_spec);
    return _result;
  }

  public static boolean canParsewindowing_clause_range(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RANGE_VK;
  }

  public static windowing_clause_range parsewindowing_clause_range(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_clause_range(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_clause_range _result = new windowing_clause_range();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsewindowing_clause_spec(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_windowing_clause_spec(parsewindowing_clause_spec(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_clause_range make_windowing_clause_range(ru.barsopen.plsqlconverter.ast.typed.windowing_clause_spec windowing_clause_spec) {
    windowing_clause_range _result = new windowing_clause_range();
    _result.set_windowing_clause_spec(windowing_clause_spec);
    return _result;
  }

  public static boolean canParsewindowing_clause_spec(org.antlr.runtime.tree.Tree tree) {
    return canParsewindowing_clause_between(tree) || canParsewindowing_elements(tree);
  }

  public static windowing_clause_spec parsewindowing_clause_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_clause_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsewindowing_clause_between(tree)) return parsewindowing_clause_between(tree);
    if (canParsewindowing_elements(tree)) return parsewindowing_elements(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewindowing_clause_between(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_BETWEEN;
  }

  public static windowing_clause_between parsewindowing_clause_between(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_clause_between(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_clause_between _result = new windowing_clause_between();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsewindowing_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_e1(parsewindowing_elements(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParsewindowing_elements(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_e2(parsewindowing_elements(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_clause_between make_windowing_clause_between(ru.barsopen.plsqlconverter.ast.typed.windowing_elements e1,
      ru.barsopen.plsqlconverter.ast.typed.windowing_elements e2) {
    windowing_clause_between _result = new windowing_clause_between();
    _result.set_e1(e1);
    _result.set_e2(e2);
    return _result;
  }

  public static boolean canParsewindowing_elements(org.antlr.runtime.tree.Tree tree) {
    return canParsewindowing_elements_unbounded_preceding(tree) || canParsewindowing_elements_current_row(tree) || canParsewindowing_elements_preceding(tree) || canParsewindowing_elements_following(tree);
  }

  public static windowing_elements parsewindowing_elements(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_elements(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsewindowing_elements_unbounded_preceding(tree)) return parsewindowing_elements_unbounded_preceding(tree);
    if (canParsewindowing_elements_current_row(tree)) return parsewindowing_elements_current_row(tree);
    if (canParsewindowing_elements_preceding(tree)) return parsewindowing_elements_preceding(tree);
    if (canParsewindowing_elements_following(tree)) return parsewindowing_elements_following(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsewindowing_elements_unbounded_preceding(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNBOUNDED_VK;
  }

  public static windowing_elements_unbounded_preceding parsewindowing_elements_unbounded_preceding(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_elements_unbounded_preceding(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_elements_unbounded_preceding _result = new windowing_elements_unbounded_preceding();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PRECEDING_VK))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_PRECEDING_VK(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_elements_unbounded_preceding make_windowing_elements_unbounded_preceding(org.antlr.runtime.tree.Tree PRECEDING_VK) {
    windowing_elements_unbounded_preceding _result = new windowing_elements_unbounded_preceding();
    _result.set_PRECEDING_VK(PRECEDING_VK);
    return _result;
  }

  public static boolean canParsewindowing_elements_current_row(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CURRENT_VK;
  }

  public static windowing_elements_current_row parsewindowing_elements_current_row(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_elements_current_row(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_elements_current_row _result = new windowing_elements_current_row();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROW_VK))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_ROW_VK(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_elements_current_row make_windowing_elements_current_row(org.antlr.runtime.tree.Tree ROW_VK) {
    windowing_elements_current_row _result = new windowing_elements_current_row();
    _result.set_ROW_VK(ROW_VK);
    return _result;
  }

  public static boolean canParsewindowing_elements_preceding(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PRECEDING_VK;
  }

  public static windowing_elements_preceding parsewindowing_elements_preceding(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_elements_preceding(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_elements_preceding _result = new windowing_elements_preceding();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_elements_preceding make_windowing_elements_preceding(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    windowing_elements_preceding _result = new windowing_elements_preceding();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsewindowing_elements_following(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FOLLOWING_VK;
  }

  public static windowing_elements_following parsewindowing_elements_following(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewindowing_elements_following(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    windowing_elements_following _result = new windowing_elements_following();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static windowing_elements_following make_windowing_elements_following(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    windowing_elements_following _result = new windowing_elements_following();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseusing_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PLSQL_NON_RESERVED_USING;
  }

  public static using_clause parseusing_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseusing_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    using_clause _result = new using_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseusing_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseusing_element(tree.getChild(_i)))) {
      _result.add_using_elements(parseusing_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static using_clause make_using_clause(java.util.List<ru.barsopen.plsqlconverter.ast.typed.using_element> using_elements) {
    using_clause _result = new using_clause();
    if (using_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.using_element _value: using_elements) { _result.add_using_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseusing_element(org.antlr.runtime.tree.Tree tree) {
    return canParseusing_element_element(tree) || canParseusing_element_asterisk(tree);
  }

  public static using_element parseusing_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParseusing_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseusing_element_element(tree)) return parseusing_element_element(tree);
    if (canParseusing_element_asterisk(tree)) return parseusing_element_asterisk(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseusing_element_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ELEMENT;
  }

  public static using_element_element parseusing_element_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParseusing_element_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    using_element_element _result = new using_element_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_IN)) {
      _result.set_SQL92_RESERVED_IN(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.OUT_VK)) {
      _result.set_OUT_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsealias(tree.getChild(_i)))) {
      _result.set_alias(parsealias(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static using_element_element make_using_element_element(org.antlr.runtime.tree.Tree SQL92_RESERVED_IN,
      org.antlr.runtime.tree.Tree OUT_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.alias alias) {
    using_element_element _result = new using_element_element();
    _result.set_SQL92_RESERVED_IN(SQL92_RESERVED_IN);
    _result.set_OUT_VK(OUT_VK);
    _result.set_expression(expression);
    _result.set_alias(alias);
    return _result;
  }

  public static boolean canParseusing_element_asterisk(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ASTERISK;
  }

  public static using_element_asterisk parseusing_element_asterisk(org.antlr.runtime.tree.Tree tree) {
    if (!canParseusing_element_asterisk(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    using_element_asterisk _result = new using_element_asterisk();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static using_element_asterisk make_using_element_asterisk() {
    using_element_asterisk _result = new using_element_asterisk();
    return _result;
  }

  public static boolean canParsecollect_order_by_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_ORDER;
  }

  public static collect_order_by_part parsecollect_order_by_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecollect_order_by_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    collect_order_by_part _result = new collect_order_by_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static collect_order_by_part make_collect_order_by_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    collect_order_by_part _result = new collect_order_by_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsewithin_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.WITHIN_VK;
  }

  public static within_clause parsewithin_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewithin_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    within_clause _result = new within_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseorder_by_clause(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_order_by_clause(parseorder_by_clause(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static within_clause make_within_clause(ru.barsopen.plsqlconverter.ast.typed.order_by_clause order_by_clause) {
    within_clause _result = new within_clause();
    _result.set_order_by_clause(order_by_clause);
    return _result;
  }

  public static boolean canParsecost_matrix_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COST_VK;
  }

  public static cost_matrix_clause parsecost_matrix_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecost_matrix_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cost_matrix_clause _result = new cost_matrix_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cost_matrix_clause make_cost_matrix_clause() {
    cost_matrix_clause _result = new cost_matrix_clause();
    return _result;
  }

  public static boolean canParsexml_passing_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PASSING_VK;
  }

  public static xml_passing_clause parsexml_passing_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_passing_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_passing_clause _result = new xml_passing_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VALUE_VK)) {
      _result.set_VALUE_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsexml_passing_clause_expr(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_xml_passing_clause_expr(parsexml_passing_clause_expr(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_passing_clause make_xml_passing_clause(org.antlr.runtime.tree.Tree VALUE_VK,
      ru.barsopen.plsqlconverter.ast.typed.xml_passing_clause_expr xml_passing_clause_expr) {
    xml_passing_clause _result = new xml_passing_clause();
    _result.set_VALUE_VK(VALUE_VK);
    _result.set_xml_passing_clause_expr(xml_passing_clause_expr);
    return _result;
  }

  public static boolean canParsexml_passing_clause_expr(org.antlr.runtime.tree.Tree tree) {
    return canParseexpression(tree);
  }

  public static xml_passing_clause_expr parsexml_passing_clause_expr(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_passing_clause_expr(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseexpression(tree)) return parseexpression(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsexml_attributes_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLATTRIBUTES_VK;
  }

  public static xml_attributes_clause parsexml_attributes_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_attributes_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_attributes_clause _result = new xml_attributes_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ENTITYESCAPING_VK)) {
      _result.set_ENTITYESCAPING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOENTITYESCAPING_VK)) {
      _result.set_NOENTITYESCAPING_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SCHEMACHECK_VK)) {
      _result.set_SCHEMACHECK_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NOSCHEMACHECK_VK)) {
      _result.set_NOSCHEMACHECK_VK(tree.getChild(_i));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParsexml_multiuse_expression_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsexml_multiuse_expression_element(tree.getChild(_i)))) {
      _result.add_xml_multiuse_expression_elements(parsexml_multiuse_expression_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_attributes_clause make_xml_attributes_clause(org.antlr.runtime.tree.Tree ENTITYESCAPING_VK,
      org.antlr.runtime.tree.Tree NOENTITYESCAPING_VK,
      org.antlr.runtime.tree.Tree SCHEMACHECK_VK,
      org.antlr.runtime.tree.Tree NOSCHEMACHECK_VK,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.xml_multiuse_expression_element> xml_multiuse_expression_elements) {
    xml_attributes_clause _result = new xml_attributes_clause();
    _result.set_ENTITYESCAPING_VK(ENTITYESCAPING_VK);
    _result.set_NOENTITYESCAPING_VK(NOENTITYESCAPING_VK);
    _result.set_SCHEMACHECK_VK(SCHEMACHECK_VK);
    _result.set_NOSCHEMACHECK_VK(NOSCHEMACHECK_VK);
    if (xml_multiuse_expression_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.xml_multiuse_expression_element _value: xml_multiuse_expression_elements) { _result.add_xml_multiuse_expression_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsexml_namespaces_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XMLNAMESPACES_VK;
  }

  public static xml_namespaces_clause parsexml_namespaces_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_namespaces_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_namespaces_clause _result = new xml_namespaces_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_namespaces_clause make_xml_namespaces_clause() {
    xml_namespaces_clause _result = new xml_namespaces_clause();
    return _result;
  }

  public static boolean canParsexml_table_column(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XML_COLUMN;
  }

  public static xml_table_column parsexml_table_column(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_table_column(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_table_column _result = new xml_table_column();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsexml_column_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_xml_column_name(parsexml_column_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ORDINALITY_VK)) {
      _result.set_ORDINALITY_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsetype_spec(tree.getChild(_i)))) {
      _result.set_type_spec(parsetype_spec(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsexml_general_default_part(tree.getChild(_i)))) {
      _result.set_xml_general_default_part(parsexml_general_default_part(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_table_column make_xml_table_column(ru.barsopen.plsqlconverter.ast.typed.xml_column_name xml_column_name,
      org.antlr.runtime.tree.Tree ORDINALITY_VK,
      ru.barsopen.plsqlconverter.ast.typed.type_spec type_spec,
      ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.xml_general_default_part xml_general_default_part) {
    xml_table_column _result = new xml_table_column();
    _result.set_xml_column_name(xml_column_name);
    _result.set_ORDINALITY_VK(ORDINALITY_VK);
    _result.set_type_spec(type_spec);
    _result.set_expression(expression);
    _result.set_xml_general_default_part(xml_general_default_part);
    return _result;
  }

  public static boolean canParsexml_general_default_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DEFAULT;
  }

  public static xml_general_default_part parsexml_general_default_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_general_default_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_general_default_part _result = new xml_general_default_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_general_default_part make_xml_general_default_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    xml_general_default_part _result = new xml_general_default_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsexml_multiuse_expression_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XML_ELEMENT;
  }

  public static xml_multiuse_expression_element parsexml_multiuse_expression_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_multiuse_expression_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_multiuse_expression_element _result = new xml_multiuse_expression_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParsexml_alias(tree.getChild(_i)))) {
      _result.set_xml_alias(parsexml_alias(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_multiuse_expression_element make_xml_multiuse_expression_element(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.xml_alias xml_alias) {
    xml_multiuse_expression_element _result = new xml_multiuse_expression_element();
    _result.set_expression(expression);
    _result.set_xml_alias(xml_alias);
    return _result;
  }

  public static boolean canParsexml_alias(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XML_ALIAS;
  }

  public static xml_alias parsexml_alias(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_alias(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_alias _result = new xml_alias();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid_or_evalname(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id_or_evalname(parseid_or_evalname(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_alias make_xml_alias(ru.barsopen.plsqlconverter.ast.typed.id_or_evalname id_or_evalname) {
    xml_alias _result = new xml_alias();
    _result.set_id_or_evalname(id_or_evalname);
    return _result;
  }

  public static boolean canParseid_or_evalname(org.antlr.runtime.tree.Tree tree) {
    return canParseid(tree) || canParsexml_alias_evalname(tree);
  }

  public static id_or_evalname parseid_or_evalname(org.antlr.runtime.tree.Tree tree) {
    if (!canParseid_or_evalname(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseid(tree)) return parseid(tree);
    if (canParsexml_alias_evalname(tree)) return parsexml_alias_evalname(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsexml_alias_evalname(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EVALNAME_VK;
  }

  public static xml_alias_evalname parsexml_alias_evalname(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_alias_evalname(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_alias_evalname _result = new xml_alias_evalname();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_alias_evalname make_xml_alias_evalname(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    xml_alias_evalname _result = new xml_alias_evalname();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsexml_param_version_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VERSION_VK;
  }

  public static xml_param_version_part parsexml_param_version_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_param_version_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_param_version_part _result = new xml_param_version_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NO_VK)) {
      _result.set_NO_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VALUE_VK)) {
      _result.set_VALUE_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_param_version_part make_xml_param_version_part(org.antlr.runtime.tree.Tree NO_VK,
      org.antlr.runtime.tree.Tree VALUE_VK,
      ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    xml_param_version_part _result = new xml_param_version_part();
    _result.set_NO_VK(NO_VK);
    _result.set_VALUE_VK(VALUE_VK);
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsexmlroot_param_standalone_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.STANDALONE_VK;
  }

  public static xmlroot_param_standalone_part parsexmlroot_param_standalone_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlroot_param_standalone_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xmlroot_param_standalone_part _result = new xmlroot_param_standalone_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.YES_VK)) {
      _result.set_YES_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NO_VK)) {
      _result.set_NO_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VALUE_VK)) {
      _result.set_VALUE_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xmlroot_param_standalone_part make_xmlroot_param_standalone_part(org.antlr.runtime.tree.Tree YES_VK,
      org.antlr.runtime.tree.Tree NO_VK,
      org.antlr.runtime.tree.Tree VALUE_VK) {
    xmlroot_param_standalone_part _result = new xmlroot_param_standalone_part();
    _result.set_YES_VK(YES_VK);
    _result.set_NO_VK(NO_VK);
    _result.set_VALUE_VK(VALUE_VK);
    return _result;
  }

  public static boolean canParsexmlserialize_param_enconding_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ENCODING_VK;
  }

  public static xmlserialize_param_enconding_part parsexmlserialize_param_enconding_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlserialize_param_enconding_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xmlserialize_param_enconding_part _result = new xmlserialize_param_enconding_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xmlserialize_param_enconding_part make_xmlserialize_param_enconding_part(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    xmlserialize_param_enconding_part _result = new xmlserialize_param_enconding_part();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsexmlserialize_param_ident_part(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INDENT_VK;
  }

  public static xmlserialize_param_ident_part parsexmlserialize_param_ident_part(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexmlserialize_param_ident_part(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xmlserialize_param_ident_part _result = new xmlserialize_param_ident_part();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xmlserialize_param_ident_part make_xmlserialize_param_ident_part() {
    xmlserialize_param_ident_part _result = new xmlserialize_param_ident_part();
    return _result;
  }

  public static boolean canParsepartition_extension_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARTITION_VK;
  }

  public static partition_extension_clause parsepartition_extension_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepartition_extension_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    partition_extension_clause _result = new partition_extension_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static partition_extension_clause make_partition_extension_clause() {
    partition_extension_clause _result = new partition_extension_clause();
    return _result;
  }

  public static boolean canParsealias(org.antlr.runtime.tree.Tree tree) {
    return canParsecolumn_alias(tree) || canParsetable_alias(tree);
  }

  public static alias parsealias(org.antlr.runtime.tree.Tree tree) {
    if (!canParsealias(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsecolumn_alias(tree)) return parsecolumn_alias(tree);
    if (canParsetable_alias(tree)) return parsetable_alias(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsecolumn_alias(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COLUMN_ALIAS;
  }

  public static column_alias parsecolumn_alias(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecolumn_alias(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    column_alias _result = new column_alias();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static column_alias make_column_alias(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    column_alias _result = new column_alias();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsetable_alias(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_ALIAS;
  }

  public static table_alias parsetable_alias(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_alias(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_alias _result = new table_alias();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_alias make_table_alias(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    table_alias _result = new table_alias();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsewhere_clause(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_WHERE;
  }

  public static where_clause parsewhere_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParsewhere_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    where_clause _result = new where_clause();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static where_clause make_where_clause(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    where_clause _result = new where_clause();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseinto_clause(org.antlr.runtime.tree.Tree tree) {
    return canParseinto_clause_normal(tree) || canParseinto_clause_bulk(tree);
  }

  public static into_clause parseinto_clause(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinto_clause(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseinto_clause_normal(tree)) return parseinto_clause_normal(tree);
    if (canParseinto_clause_bulk(tree)) return parseinto_clause_bulk(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseinto_clause_normal(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_INTO;
  }

  public static into_clause_normal parseinto_clause_normal(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinto_clause_normal(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    into_clause_normal _result = new into_clause_normal();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegeneral_element(tree.getChild(_i)))) {
      _result.add_general_elements(parsegeneral_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static into_clause_normal make_into_clause_normal(java.util.List<ru.barsopen.plsqlconverter.ast.typed.general_element> general_elements) {
    into_clause_normal _result = new into_clause_normal();
    if (general_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.general_element _value: general_elements) { _result.add_general_elements(_value); }
    }
    return _result;
  }

  public static boolean canParseinto_clause_bulk(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BULK_VK;
  }

  public static into_clause_bulk parseinto_clause_bulk(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinto_clause_bulk(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    into_clause_bulk _result = new into_clause_bulk();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_element(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegeneral_element(tree.getChild(_i)))) {
      _result.add_general_elements(parsegeneral_element(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static into_clause_bulk make_into_clause_bulk(java.util.List<ru.barsopen.plsqlconverter.ast.typed.general_element> general_elements) {
    into_clause_bulk _result = new into_clause_bulk();
    if (general_elements != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.general_element _value: general_elements) { _result.add_general_elements(_value); }
    }
    return _result;
  }

  public static boolean canParsexml_column_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.XML_COLUMN_NAME;
  }

  public static xml_column_name parsexml_column_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsexml_column_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    xml_column_name _result = new xml_column_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static xml_column_name make_xml_column_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    xml_column_name _result = new xml_column_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsecost_class_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COST_CLASS_NAME;
  }

  public static cost_class_name parsecost_class_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecost_class_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cost_class_name _result = new cost_class_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cost_class_name make_cost_class_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    cost_class_name _result = new cost_class_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParseattribute_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ATTRIBUTE_NAME;
  }

  public static attribute_name parseattribute_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseattribute_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    attribute_name _result = new attribute_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static attribute_name make_attribute_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    attribute_name _result = new attribute_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsesavepoint_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SAVEPOINT_NAME;
  }

  public static savepoint_name parsesavepoint_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesavepoint_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    savepoint_name _result = new savepoint_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static savepoint_name make_savepoint_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    savepoint_name _result = new savepoint_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParserollback_segment_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROLLBACK_SEGMENT_NAME;
  }

  public static rollback_segment_name parserollback_segment_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParserollback_segment_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    rollback_segment_name _result = new rollback_segment_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static rollback_segment_name make_rollback_segment_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    rollback_segment_name _result = new rollback_segment_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsetable_var_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLE_VAR_NAME;
  }

  public static table_var_name parsetable_var_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetable_var_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    table_var_name _result = new table_var_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static table_var_name make_table_var_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    table_var_name _result = new table_var_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParseschema_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SCHEMA_NAME;
  }

  public static schema_name parseschema_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseschema_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    schema_name _result = new schema_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static schema_name make_schema_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    schema_name _result = new schema_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParseroutine_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ROUTINE_NAME;
  }

  public static routine_name parseroutine_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseroutine_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    routine_name _result = new routine_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParselink_name(tree.getChild(_i)))) {
      _result.set_link_name(parselink_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static routine_name make_routine_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids,
      ru.barsopen.plsqlconverter.ast.typed.link_name link_name) {
    routine_name _result = new routine_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    _result.set_link_name(link_name);
    return _result;
  }

  public static boolean canParsepackage_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PACKAGE_NAME;
  }

  public static package_name parsepackage_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsepackage_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    package_name _result = new package_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static package_name make_package_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    package_name _result = new package_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParseimplementation_type_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.IMPLEMENTATION_TYPE_NAME;
  }

  public static implementation_type_name parseimplementation_type_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseimplementation_type_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    implementation_type_name _result = new implementation_type_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static implementation_type_name make_implementation_type_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    implementation_type_name _result = new implementation_type_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParseparameter_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PARAMETER_NAME;
  }

  public static parameter_name parseparameter_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseparameter_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    parameter_name _result = new parameter_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static parameter_name make_parameter_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    parameter_name _result = new parameter_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsereference_model_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REFERENCE_MODEL_NAME;
  }

  public static reference_model_name parsereference_model_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsereference_model_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    reference_model_name _result = new reference_model_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static reference_model_name make_reference_model_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    reference_model_name _result = new reference_model_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsemain_model_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MAIN_MODEL_NAME;
  }

  public static main_model_name parsemain_model_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsemain_model_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    main_model_name _result = new main_model_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static main_model_name make_main_model_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    main_model_name _result = new main_model_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsequery_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.QUERY_NAME;
  }

  public static query_name parsequery_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsequery_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    query_name _result = new query_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static query_name make_query_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    query_name _result = new query_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParseconstraint_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CONSTRAINT_NAME;
  }

  public static constraint_name parseconstraint_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstraint_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constraint_name _result = new constraint_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParselink_name(tree.getChild(_i)))) {
      _result.set_link_name(parselink_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constraint_name make_constraint_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids,
      ru.barsopen.plsqlconverter.ast.typed.link_name link_name) {
    constraint_name _result = new constraint_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    _result.set_link_name(link_name);
    return _result;
  }

  public static boolean canParselabel_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LABEL_NAME;
  }

  public static label_name parselabel_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParselabel_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    label_name _result = new label_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static label_name make_label_name(ru.barsopen.plsqlconverter.ast.typed.id id) {
    label_name _result = new label_name();
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsetype_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TYPE_NAME;
  }

  public static type_name parsetype_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_name _result = new type_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_name make_type_name(java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    type_name _result = new type_name();
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsesequence_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SEQUENCE_NAME;
  }

  public static sequence_name parsesequence_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsesequence_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    sequence_name _result = new sequence_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static sequence_name make_sequence_name(java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    sequence_name _result = new sequence_name();
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParseexception_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXCEPTION_NAME;
  }

  public static exception_name parseexception_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseexception_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    exception_name _result = new exception_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static exception_name make_exception_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    exception_name _result = new exception_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsefunction_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.FUNCTION_NAME;
  }

  public static function_name parsefunction_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefunction_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    function_name _result = new function_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static function_name make_function_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    function_name _result = new function_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParseprocedure_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PROCEDURE_NAME;
  }

  public static procedure_name parseprocedure_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseprocedure_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    procedure_name _result = new procedure_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static procedure_name make_procedure_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    procedure_name _result = new procedure_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsetrigger_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TRIGGER_NAME;
  }

  public static trigger_name parsetrigger_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetrigger_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    trigger_name _result = new trigger_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static trigger_name make_trigger_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    trigger_name _result = new trigger_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsehosted_variable_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.HOSTED_VARIABLE_NAME;
  }

  public static hosted_variable_name parsehosted_variable_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsehosted_variable_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    hosted_variable_name _result = new hosted_variable_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BINDVAR)) {
      _result.set_BINDVAR(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNSIGNED_INTEGER)) {
      _result.set_UNSIGNED_INTEGER(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static hosted_variable_name make_hosted_variable_name(org.antlr.runtime.tree.Tree BINDVAR,
      org.antlr.runtime.tree.Tree UNSIGNED_INTEGER) {
    hosted_variable_name _result = new hosted_variable_name();
    _result.set_BINDVAR(BINDVAR);
    _result.set_UNSIGNED_INTEGER(UNSIGNED_INTEGER);
    return _result;
  }

  public static boolean canParsevariable_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.VARIABLE_NAME;
  }

  public static variable_name parsevariable_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsevariable_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    variable_name _result = new variable_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static variable_name make_variable_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    variable_name _result = new variable_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParseindex_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INDEX_NAME;
  }

  public static index_name parseindex_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParseindex_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    index_name _result = new index_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static index_name make_index_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    index_name _result = new index_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsecursor_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CURSOR_NAME;
  }

  public static cursor_name parsecursor_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecursor_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    cursor_name _result = new cursor_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static cursor_name make_cursor_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    cursor_name _result = new cursor_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParserecord_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.RECORD_NAME;
  }

  public static record_name parserecord_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParserecord_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    record_name _result = new record_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static record_name make_record_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    record_name _result = new record_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsecollection_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COLLECTION_NAME;
  }

  public static collection_name parsecollection_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecollection_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    collection_name _result = new collection_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static collection_name make_collection_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    collection_name _result = new collection_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParselink_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LINK_NAME;
  }

  public static link_name parselink_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParselink_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    link_name _result = new link_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static link_name make_link_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      ru.barsopen.plsqlconverter.ast.typed.id id) {
    link_name _result = new link_name();
    _result.set_char_set_name(char_set_name);
    _result.set_id(id);
    return _result;
  }

  public static boolean canParsecolumn_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.COLUMN_NAME;
  }

  public static column_name parsecolumn_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsecolumn_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    column_name _result = new column_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static column_name make_column_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    column_name _result = new column_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsetableview_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TABLEVIEW_NAME;
  }

  public static tableview_name parsetableview_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetableview_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    tableview_name _result = new tableview_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParsechar_set_name(tree.getChild(_i)))) {
      _result.set_char_set_name(parsechar_set_name(tree.getChild(_i)));
      ++_i;
    }

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParselink_name(tree.getChild(_i)))) {
      _result.set_link_name(parselink_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsepartition_extension_clause(tree.getChild(_i)))) {
      _result.set_partition_extension_clause(parsepartition_extension_clause(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static tableview_name make_tableview_name(ru.barsopen.plsqlconverter.ast.typed.char_set_name char_set_name,
      java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids,
      ru.barsopen.plsqlconverter.ast.typed.link_name link_name,
      ru.barsopen.plsqlconverter.ast.typed.partition_extension_clause partition_extension_clause) {
    tableview_name _result = new tableview_name();
    _result.set_char_set_name(char_set_name);
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    _result.set_link_name(link_name);
    _result.set_partition_extension_clause(partition_extension_clause);
    return _result;
  }

  public static boolean canParsechar_set_name(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CHAR_SET_NAME;
  }

  public static char_set_name parsechar_set_name(org.antlr.runtime.tree.Tree tree) {
    if (!canParsechar_set_name(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    char_set_name _result = new char_set_name();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParseid(tree.getChild(_i)))) {
      _result.add_ids(parseid(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static char_set_name make_char_set_name(java.util.List<ru.barsopen.plsqlconverter.ast.typed.id> ids) {
    char_set_name _result = new char_set_name();
    if (ids != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.id _value: ids) { _result.add_ids(_value); }
    }
    return _result;
  }

  public static boolean canParsefunction_argument(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ARGUMENTS;
  }

  public static function_argument parsefunction_argument(org.antlr.runtime.tree.Tree tree) {
    if (!canParsefunction_argument(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    function_argument _result = new function_argument();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    while (_i < tree.getChildCount() && (canParseargument(tree.getChild(_i)))) {
      _result.add_arguments(parseargument(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static function_argument make_function_argument(java.util.List<ru.barsopen.plsqlconverter.ast.typed.argument> arguments) {
    function_argument _result = new function_argument();
    if (arguments != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.argument _value: arguments) { _result.add_arguments(_value); }
    }
    return _result;
  }

  public static boolean canParseargument(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ARGUMENT;
  }

  public static argument parseargument(org.antlr.runtime.tree.Tree tree) {
    if (!canParseargument(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    argument _result = new argument();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_expression(parseexpression(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseparameter_name(tree.getChild(_i)))) {
      _result.set_parameter_name(parseparameter_name(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static argument make_argument(ru.barsopen.plsqlconverter.ast.typed.expression expression,
      ru.barsopen.plsqlconverter.ast.typed.parameter_name parameter_name) {
    argument _result = new argument();
    _result.set_expression(expression);
    _result.set_parameter_name(parameter_name);
    return _result;
  }

  public static boolean canParsetype_spec(org.antlr.runtime.tree.Tree tree) {
    return canParsetype_spec_custom(tree) || canParsenative_datatype_spec(tree) || canParsetype_spec_interval(tree);
  }

  public static type_spec parsetype_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsetype_spec_custom(tree)) return parsetype_spec_custom(tree);
    if (canParsenative_datatype_spec(tree)) return parsenative_datatype_spec(tree);
    if (canParsetype_spec_interval(tree)) return parsetype_spec_interval(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsetype_spec_custom(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CUSTOM_TYPE;
  }

  public static type_spec_custom parsetype_spec_custom(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_spec_custom(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_spec_custom _result = new type_spec_custom();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsetype_name(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_type_name(parsetype_name(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.REF_VK)) {
      _result.set_REF_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (canParsepercent_type_or_rowtype(tree.getChild(_i)))) {
      _result.set_percent_type_or_rowtype(parsepercent_type_or_rowtype(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_spec_custom make_type_spec_custom(ru.barsopen.plsqlconverter.ast.typed.type_name type_name,
      org.antlr.runtime.tree.Tree REF_VK,
      ru.barsopen.plsqlconverter.ast.typed.percent_type_or_rowtype percent_type_or_rowtype) {
    type_spec_custom _result = new type_spec_custom();
    _result.set_type_name(type_name);
    _result.set_REF_VK(REF_VK);
    _result.set_percent_type_or_rowtype(percent_type_or_rowtype);
    return _result;
  }

  public static boolean canParsetype_spec_interval(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.INTERVAL_DATATYPE;
  }

  public static type_spec_interval parsetype_spec_interval(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_spec_interval(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_spec_interval _result = new type_spec_interval();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseinterval_type_spec_first(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_interval_type_spec_first(parseinterval_type_spec_first(tree.getChild(_i)));
    ++_i;

    if (!(_i < tree.getChildCount() && (canParseinterval_type_spec_second(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_interval_type_spec_second(parseinterval_type_spec_second(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_spec_interval make_type_spec_interval(ru.barsopen.plsqlconverter.ast.typed.interval_type_spec_first interval_type_spec_first,
      ru.barsopen.plsqlconverter.ast.typed.interval_type_spec_second interval_type_spec_second) {
    type_spec_interval _result = new type_spec_interval();
    _result.set_interval_type_spec_first(interval_type_spec_first);
    _result.set_interval_type_spec_second(interval_type_spec_second);
    return _result;
  }

  public static boolean canParseinterval_type_spec_first(org.antlr.runtime.tree.Tree tree) {
    return canParseinterval_type_spec_first_year(tree) || canParseinterval_type_spec_first_day(tree);
  }

  public static interval_type_spec_first parseinterval_type_spec_first(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_first(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseinterval_type_spec_first_year(tree)) return parseinterval_type_spec_first_year(tree);
    if (canParseinterval_type_spec_first_day(tree)) return parseinterval_type_spec_first_day(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseinterval_type_spec_first_year(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.YEAR_VK;
  }

  public static interval_type_spec_first_year parseinterval_type_spec_first_year(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_first_year(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    interval_type_spec_first_year _result = new interval_type_spec_first_year();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static interval_type_spec_first_year make_interval_type_spec_first_year(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    interval_type_spec_first_year _result = new interval_type_spec_first_year();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseinterval_type_spec_first_day(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DAY_VK;
  }

  public static interval_type_spec_first_day parseinterval_type_spec_first_day(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_first_day(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    interval_type_spec_first_day _result = new interval_type_spec_first_day();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static interval_type_spec_first_day make_interval_type_spec_first_day(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    interval_type_spec_first_day _result = new interval_type_spec_first_day();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseinterval_type_spec_second(org.antlr.runtime.tree.Tree tree) {
    return canParseinterval_type_spec_second_month(tree) || canParseinterval_type_spec_second_second(tree);
  }

  public static interval_type_spec_second parseinterval_type_spec_second(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_second(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseinterval_type_spec_second_month(tree)) return parseinterval_type_spec_second_month(tree);
    if (canParseinterval_type_spec_second_second(tree)) return parseinterval_type_spec_second_second(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseinterval_type_spec_second_month(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MONTH_VK;
  }

  public static interval_type_spec_second_month parseinterval_type_spec_second_month(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_second_month(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    interval_type_spec_second_month _result = new interval_type_spec_second_month();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static interval_type_spec_second_month make_interval_type_spec_second_month(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    interval_type_spec_second_month _result = new interval_type_spec_second_month();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParseinterval_type_spec_second_second(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SECOND_VK;
  }

  public static interval_type_spec_second_second parseinterval_type_spec_second_second(org.antlr.runtime.tree.Tree tree) {
    if (!canParseinterval_type_spec_second_second(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    interval_type_spec_second_second _result = new interval_type_spec_second_second();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount() && (canParseexpression(tree.getChild(_i)))) {
      _result.set_expression(parseexpression(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static interval_type_spec_second_second make_interval_type_spec_second_second(ru.barsopen.plsqlconverter.ast.typed.expression expression) {
    interval_type_spec_second_second _result = new interval_type_spec_second_second();
    _result.set_expression(expression);
    return _result;
  }

  public static boolean canParsetype_precision(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.PRECISION;
  }

  public static type_precision parsetype_precision(org.antlr.runtime.tree.Tree tree) {
    if (!canParsetype_precision(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    type_precision _result = new type_precision();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseconstant(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_size1(parseconstant(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount() && (canParseconstant(tree.getChild(_i)))) {
      _result.set_size2(parseconstant(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CHAR_VK)) {
      _result.set_CHAR_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.BYTE_VK)) {
      _result.set_BYTE_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static type_precision make_type_precision(ru.barsopen.plsqlconverter.ast.typed.constant size1,
      ru.barsopen.plsqlconverter.ast.typed.constant size2,
      org.antlr.runtime.tree.Tree CHAR_VK,
      org.antlr.runtime.tree.Tree BYTE_VK) {
    type_precision _result = new type_precision();
    _result.set_size1(size1);
    _result.set_size2(size2);
    _result.set_CHAR_VK(CHAR_VK);
    _result.set_BYTE_VK(BYTE_VK);
    return _result;
  }

  public static boolean canParsenative_datatype_spec(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.NATIVE_DATATYPE;
  }

  public static native_datatype_spec parsenative_datatype_spec(org.antlr.runtime.tree.Tree tree) {
    if (!canParsenative_datatype_spec(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    native_datatype_spec _result = new native_datatype_spec();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (true))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_name(tree.getChild(_i));
    ++_i;

    if (_i < tree.getChildCount() && (canParsetype_precision(tree.getChild(_i)))) {
      _result.set_type_precision(parsetype_precision(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.TIME_VK)) {
      _result.set_TIME_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount() && (tree.getChild(_i).getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.LOCAL_VK)) {
      _result.set_LOCAL_VK(tree.getChild(_i));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static native_datatype_spec make_native_datatype_spec(org.antlr.runtime.tree.Tree name,
      ru.barsopen.plsqlconverter.ast.typed.type_precision type_precision,
      org.antlr.runtime.tree.Tree TIME_VK,
      org.antlr.runtime.tree.Tree LOCAL_VK) {
    native_datatype_spec _result = new native_datatype_spec();
    _result.set_name(name);
    _result.set_type_precision(type_precision);
    _result.set_TIME_VK(TIME_VK);
    _result.set_LOCAL_VK(LOCAL_VK);
    return _result;
  }

  public static boolean canParsegeneral_element(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CASCATED_ELEMENT;
  }

  public static general_element parsegeneral_element(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_element(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    general_element _result = new general_element();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParsegeneral_element_item(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    while (_i < tree.getChildCount() && (canParsegeneral_element_item(tree.getChild(_i)))) {
      _result.add_general_element_items(parsegeneral_element_item(tree.getChild(_i)));
      ++_i;
    }

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static general_element make_general_element(java.util.List<ru.barsopen.plsqlconverter.ast.typed.general_element_item> general_element_items) {
    general_element _result = new general_element();
    if (general_element_items != null) {
      for (ru.barsopen.plsqlconverter.ast.typed.general_element_item _value: general_element_items) { _result.add_general_element_items(_value); }
    }
    return _result;
  }

  public static boolean canParsegeneral_element_item(org.antlr.runtime.tree.Tree tree) {
    return canParsegeneral_element_id(tree) || canParsefunction_argument(tree);
  }

  public static general_element_item parsegeneral_element_item(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_element_item(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParsegeneral_element_id(tree)) return parsegeneral_element_id(tree);
    if (canParsefunction_argument(tree)) return parsefunction_argument(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParsegeneral_element_id(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ANY_ELEMENT;
  }

  public static general_element_id parsegeneral_element_id(org.antlr.runtime.tree.Tree tree) {
    if (!canParsegeneral_element_id(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    general_element_id _result = new general_element_id();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseid(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_id(parseid(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static general_element_id make_general_element_id(ru.barsopen.plsqlconverter.ast.typed.id id) {
    general_element_id _result = new general_element_id();
    _result.set_id(id);
    return _result;
  }

  public static boolean canParseconstant(org.antlr.runtime.tree.Tree tree) {
    return canParseconstant_unsigned(tree) || canParseconstant_minus_unsigned(tree) || canParseconstant_exact_num(tree) || canParseconstant_approx_num(tree) || canParseconstant_char_string(tree) || canParseconstant_null(tree) || canParseconstant_true(tree) || canParseconstant_false(tree) || canParseconstant_dbtimezone(tree) || canParseconstant_sessiontimezone(tree) || canParseconstant_minvalue(tree) || canParseconstant_maxvalue(tree) || canParseconstant_default(tree);
  }

  public static constant parseconstant(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    if (canParseconstant_unsigned(tree)) return parseconstant_unsigned(tree);
    if (canParseconstant_minus_unsigned(tree)) return parseconstant_minus_unsigned(tree);
    if (canParseconstant_exact_num(tree)) return parseconstant_exact_num(tree);
    if (canParseconstant_approx_num(tree)) return parseconstant_approx_num(tree);
    if (canParseconstant_char_string(tree)) return parseconstant_char_string(tree);
    if (canParseconstant_null(tree)) return parseconstant_null(tree);
    if (canParseconstant_true(tree)) return parseconstant_true(tree);
    if (canParseconstant_false(tree)) return parseconstant_false(tree);
    if (canParseconstant_dbtimezone(tree)) return parseconstant_dbtimezone(tree);
    if (canParseconstant_sessiontimezone(tree)) return parseconstant_sessiontimezone(tree);
    if (canParseconstant_minvalue(tree)) return parseconstant_minvalue(tree);
    if (canParseconstant_maxvalue(tree)) return parseconstant_maxvalue(tree);
    if (canParseconstant_default(tree)) return parseconstant_default(tree);
    throw new RuntimeException("Tree type mismatch");
  }

  public static boolean canParseconstant_unsigned(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.UNSIGNED_INTEGER;
  }

  public static constant_unsigned parseconstant_unsigned(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_unsigned(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_unsigned _result = new constant_unsigned();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.value = tree.getText();

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_unsigned make_constant_unsigned(String value) {
    constant_unsigned _result = new constant_unsigned();
    _result.set_value(value);
    return _result;
  }

  public static boolean canParseconstant_minus_unsigned(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MINUS_SIGN;
  }

  public static constant_minus_unsigned parseconstant_minus_unsigned(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_minus_unsigned(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_minus_unsigned _result = new constant_minus_unsigned();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (!(_i < tree.getChildCount() && (canParseconstant_unsigned(tree.getChild(_i))))) { throw new RuntimeException("Tree type mismatch"); }
    _result.set_constant_unsigned(parseconstant_unsigned(tree.getChild(_i)));
    ++_i;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_minus_unsigned make_constant_minus_unsigned(ru.barsopen.plsqlconverter.ast.typed.constant_unsigned constant_unsigned) {
    constant_minus_unsigned _result = new constant_minus_unsigned();
    _result.set_constant_unsigned(constant_unsigned);
    return _result;
  }

  public static boolean canParseconstant_exact_num(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.EXACT_NUM_LIT;
  }

  public static constant_exact_num parseconstant_exact_num(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_exact_num(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_exact_num _result = new constant_exact_num();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.value = tree.getText();

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_exact_num make_constant_exact_num(String value) {
    constant_exact_num _result = new constant_exact_num();
    _result.set_value(value);
    return _result;
  }

  public static boolean canParseconstant_approx_num(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.APPROXIMATE_NUM_LIT;
  }

  public static constant_approx_num parseconstant_approx_num(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_approx_num(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_approx_num _result = new constant_approx_num();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.value = tree.getText();

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_approx_num make_constant_approx_num(String value) {
    constant_approx_num _result = new constant_approx_num();
    _result.set_value(value);
    return _result;
  }

  public static boolean canParseconstant_char_string(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.CHAR_STRING;
  }

  public static constant_char_string parseconstant_char_string(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_char_string(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_char_string _result = new constant_char_string();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.value = tree.getText();

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_char_string make_constant_char_string(String value) {
    constant_char_string _result = new constant_char_string();
    _result.set_value(value);
    return _result;
  }

  public static boolean canParseconstant_null(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_NULL;
  }

  public static constant_null parseconstant_null(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_null(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_null _result = new constant_null();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_null make_constant_null() {
    constant_null _result = new constant_null();
    return _result;
  }

  public static boolean canParseconstant_true(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_TRUE;
  }

  public static constant_true parseconstant_true(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_true(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_true _result = new constant_true();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_true make_constant_true() {
    constant_true _result = new constant_true();
    return _result;
  }

  public static boolean canParseconstant_false(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_FALSE;
  }

  public static constant_false parseconstant_false(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_false(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_false _result = new constant_false();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_false make_constant_false() {
    constant_false _result = new constant_false();
    return _result;
  }

  public static boolean canParseconstant_dbtimezone(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.DBTIMEZONE_VK;
  }

  public static constant_dbtimezone parseconstant_dbtimezone(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_dbtimezone(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_dbtimezone _result = new constant_dbtimezone();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_dbtimezone make_constant_dbtimezone() {
    constant_dbtimezone _result = new constant_dbtimezone();
    return _result;
  }

  public static boolean canParseconstant_sessiontimezone(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SESSIONTIMEZONE_VK;
  }

  public static constant_sessiontimezone parseconstant_sessiontimezone(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_sessiontimezone(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_sessiontimezone _result = new constant_sessiontimezone();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_sessiontimezone make_constant_sessiontimezone() {
    constant_sessiontimezone _result = new constant_sessiontimezone();
    return _result;
  }

  public static boolean canParseconstant_minvalue(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MINVALUE_VK;
  }

  public static constant_minvalue parseconstant_minvalue(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_minvalue(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_minvalue _result = new constant_minvalue();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_minvalue make_constant_minvalue() {
    constant_minvalue _result = new constant_minvalue();
    return _result;
  }

  public static boolean canParseconstant_maxvalue(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.MAXVALUE_VK;
  }

  public static constant_maxvalue parseconstant_maxvalue(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_maxvalue(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_maxvalue _result = new constant_maxvalue();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_maxvalue make_constant_maxvalue() {
    constant_maxvalue _result = new constant_maxvalue();
    return _result;
  }

  public static boolean canParseconstant_default(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.SQL92_RESERVED_DEFAULT;
  }

  public static constant_default parseconstant_default(org.antlr.runtime.tree.Tree tree) {
    if (!canParseconstant_default(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    constant_default _result = new constant_default();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static constant_default make_constant_default() {
    constant_default _result = new constant_default();
    return _result;
  }

  public static boolean canParseid(org.antlr.runtime.tree.Tree tree) {
    return tree.getType() == ru.barsopen.plsqlconverter.PLSQLPrinter.ID;
  }

  public static id parseid(org.antlr.runtime.tree.Tree tree) {
    if (!canParseid(tree)) {
      throw new RuntimeException("Tree type mismatch");
    }

    id _result = new id();

    _result._line = tree.getLine();
    _result._col = tree.getCharPositionInLine();
    _result._tokenStartIndex = tree.getTokenStartIndex();
    _result._tokenStopIndex = tree.getTokenStopIndex();
    int _i = 0;

    _result.value = tree.getText();

    if (_i < tree.getChildCount()) { throw new RuntimeException("Tree type mismatch"); }
    return _result;
  }

  public static id make_id(String value) {
    id _result = new id();
    _result.set_value(value);
    return _result;
  }

}
