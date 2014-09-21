/**
 * Oracle(c) PL/SQL 11g Parser  
 *
 * Copyright (c) 2014 Bars Group
 */
tree grammar PLSQLPrinter;

options {
    tokenVocab=PLSQLParser;
    ASTLabelType=CommonTree;
    output = template;
}

tokens {
    PGSQL_PERFORM;
    PGSQL_STRICT;
    PGSQL_TEXT;
    PGSQL_TYPED_LITERAL;
    PGSQL_NATIVE_DATATYPE_INTERVAL;
    PGSQL_EXCEPT;
    PGSQL_RAISE;
    PGSQL_NOTICE;
    PGSQL_EXCEPTION;
    PGSQL_USING;
    PGSQL_OPTION;
    PGSQL_ERRCODE;
}


@members {

  static String[] correctTokenNames = getCorrectTokenNames();

  static String[] getCorrectTokenNames() {
    // This is workaround for bug in antlr3 (at least in 3.5.2)
    java.lang.reflect.Field[] fields = PLSQLPrinter.class.getDeclaredFields();
    java.util.Map<Integer, String> tokenNamesMap = new java.util.HashMap<Integer, String>();
    int maxTokenValue = 0;
    for (java.lang.reflect.Field field: fields) {
      int mod = field.getModifiers();
      if (java.lang.reflect.Modifier.isStatic(mod) && java.lang.reflect.Modifier.isFinal(mod) && field.getType() == int.class) {
        String name = field.getName();
        int value;
        try {
          value = field.getInt(null);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        tokenNamesMap.put(value, name);
        maxTokenValue = Math.max(maxTokenValue, value);
      }
    }
    String[] result = new String[maxTokenValue + 1];
    for (int i = 0; i < maxTokenValue; ++i) {
      if (tokenNamesMap.containsKey(i)) {
        result[i] = tokenNamesMap.get(i);
      } else {
        result[i] = "<none>";
      }
    }
    return result;
  }
  
  
  public void reportError(RecognitionException e) {
    // if we've already reported an error and have not matched a token
    // yet successfully, don't report any errors.
    if ( state.errorRecovery ) {
      //System.err.print("[SPURIOUS] ");
      return;
    }
    state.syntaxErrors++; // don't count spurious
    state.errorRecovery = true;

    displayRecognitionError(correctTokenNames, e);
  }
  
  /*
  
  14 OR CURSOR
  13 AND
  12 NOT
  11 IS_NOT_NULL IS_NULL IS_NOT_NAN IS_NAN IS_NOT_PRESENT IS_PRESENT
     IS_NOT_INFINITE IS_INFINITE IS_NOT_A_SET IS_A_SET IS_NOT_EMPTY IS_EMPTY
     IS_NOT_OF_TYPE IS_OF_TYPE
  10 MEMBER_VK SUBMULTISET_VK
  9 EQUALS_OP NOT_EQUAL_OP LESS_THAN_OP GREATER_THAN_OP  LESS_THAN_OR_EQUALS_OP GREATER_THAN_OR_EQUALS_OP
  8 NOT_IN SQL92_RESERVED_IN NOT_BETWEEN SQL92_RESERVED_BETWEEN NOT_LIKE SQL92_RESERVED_LIKE LIKEC_VK LIKE2_VK LIKE4_VK
  7 CONCATENATION_OP
  6 PLUS_SIGN MINUS_SIGN
  5 ASTERISK SOLIDUS PERCENT MOD_VK DIV_VK
  4 DATETIME_OP
  3 MODEL_EXPRESSION
  2 unary: MINUS_SIGN PLUS_SIGN SQL92_RESERVED_PRIOR PLSQL_NON_RESERVED_CONNECT_BY_ROOT NEW_VK SQL92_RESERVED_DISTINCT
         SQL92_RESERVED_ALL SIMPLE_CASE SEARCHED_CASE SOME_VK SQL92_RESERVED_EXISTS SQL92_RESERVED_ANY
         PERCENT_NOTFOUND_VK PERCENT_FOUND_VK PERCENT_ISOPEN_VK PERCENT_ROWCOUNT_VK
  1 OUTER_JOIN_SIGN
  0 HOSTED_VARIABLE_NAME  UNSIGNED_INTEGER CONSTANT_NEGATED EXACT_NUM_LIT APPROXIMATE_NUM_LIT CHAR_STRING SQL92_RESERVED_NULL
        SQL92_RESERVED_TRUE SQL92_RESERVED_FALSE  DBTIMEZONE_VK  SESSIONTIMEZONE_VK  MINVALUE_VK  MAXVALUE_VK  SQL92_RESERVED_DEFAULT
        STANDARD_FUNCTION CASCATED_ELEMENT PGSQL_TYPED_LITERAL
  */
  
  static java.util.Map<Integer, Integer> unaryPriorityMap = new java.util.HashMap<Integer, Integer>();
  static java.util.Map<Integer, Integer> priorityMap = new java.util.HashMap<Integer, Integer>();
  {
    int priority = 0;
    // 0
    priorityMap.put(PLSQLPrinter.HOSTED_VARIABLE_NAME, priority);
    priorityMap.put(PLSQLPrinter.UNSIGNED_INTEGER, priority);
    priorityMap.put(PLSQLPrinter.CONSTANT_NEGATED, priority);
    priorityMap.put(PLSQLPrinter.EXACT_NUM_LIT, priority);
    priorityMap.put(PLSQLPrinter.APPROXIMATE_NUM_LIT, priority);
    priorityMap.put(PLSQLPrinter.CHAR_STRING, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_NULL, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_TRUE, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_FALSE, priority);
    priorityMap.put(PLSQLPrinter.DBTIMEZONE_VK, priority);
    priorityMap.put(PLSQLPrinter.SESSIONTIMEZONE_VK, priority);
    priorityMap.put(PLSQLPrinter.MINVALUE_VK, priority);
    priorityMap.put(PLSQLPrinter.MAXVALUE_VK, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_DEFAULT, priority);
    priorityMap.put(PLSQLPrinter.STANDARD_FUNCTION, priority);
    priorityMap.put(PLSQLPrinter.CASCATED_ELEMENT, priority);
    priorityMap.put(PLSQLPrinter.PGSQL_TYPED_LITERAL, priority);
    // 1
    ++priority;
    priorityMap.put(PLSQLPrinter.OUTER_JOIN_SIGN, priority);;
    // 2
    ++priority;
    priorityMap.put(PLSQLPrinter.UNARY_PLUS, priority);
    priorityMap.put(PLSQLPrinter.UNARY_MINUS, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_PRIOR, priority);
    priorityMap.put(PLSQLPrinter.PLSQL_NON_RESERVED_CONNECT_BY_ROOT, priority);
    priorityMap.put(PLSQLPrinter.NEW_VK, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_DISTINCT, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_ALL, priority);
    priorityMap.put(PLSQLPrinter.SIMPLE_CASE, priority);
    priorityMap.put(PLSQLPrinter.SEARCHED_CASE, priority);
    priorityMap.put(PLSQLPrinter.SOME_VK, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_EXISTS, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_ANY, priority);
    priorityMap.put(PLSQLPrinter.PERCENT_NOTFOUND_VK, priority);
    priorityMap.put(PLSQLPrinter.PERCENT_FOUND_VK, priority);
    priorityMap.put(PLSQLPrinter.PERCENT_ISOPEN_VK, priority);
    priorityMap.put(PLSQLPrinter.PERCENT_ROWCOUNT_VK, priority);
    // 3
    ++priority;
    priorityMap.put(PLSQLPrinter.MODEL_EXPRESSION, priority);
    // 4
    ++priority;
    priorityMap.put(PLSQLPrinter.DATETIME_OP, priority);
    // 5
    ++priority;
    priorityMap.put(PLSQLPrinter.ASTERISK, priority);
    priorityMap.put(PLSQLPrinter.SOLIDUS, priority);
    priorityMap.put(PLSQLPrinter.PERCENT, priority);
    priorityMap.put(PLSQLPrinter.MOD_VK, priority);
    priorityMap.put(PLSQLPrinter.DIV_VK, priority);
    // 6
    ++priority;
    priorityMap.put(PLSQLPrinter.PLUS_SIGN, priority);
    priorityMap.put(PLSQLPrinter.MINUS_SIGN, priority);
    // 7
    ++priority;
    priorityMap.put(PLSQLPrinter.CONCATENATION_OP, priority);
    // 8
    ++priority;
    priorityMap.put(PLSQLPrinter.NOT_IN, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_IN, priority);
    priorityMap.put(PLSQLPrinter.NOT_BETWEEN, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_BETWEEN, priority);
    priorityMap.put(PLSQLPrinter.NOT_LIKE, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_LIKE, priority);
    priorityMap.put(PLSQLPrinter.LIKEC_VK, priority);
    priorityMap.put(PLSQLPrinter.LIKE2_VK, priority);
    priorityMap.put(PLSQLPrinter.LIKE4_VK, priority);
    // 9
    ++priority;
    priorityMap.put(PLSQLPrinter.EQUALS_OP, priority);
    priorityMap.put(PLSQLPrinter.NOT_EQUAL_OP, priority);
    priorityMap.put(PLSQLPrinter.LESS_THAN_OP, priority);
    priorityMap.put(PLSQLPrinter.GREATER_THAN_OP, priority);
    priorityMap.put(PLSQLPrinter.LESS_THAN_OR_EQUALS_OP, priority);
    priorityMap.put(PLSQLPrinter.GREATER_THAN_OR_EQUALS_OP, priority);
    // 10
    ++priority;
    priorityMap.put(PLSQLPrinter.MEMBER_VK, priority);
    priorityMap.put(PLSQLPrinter.SUBMULTISET_VK, priority);
    // 11
    ++priority;
    priorityMap.put(PLSQLPrinter.IS_NOT_NULL, priority);
    priorityMap.put(PLSQLPrinter.IS_NULL, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_NAN, priority);
    priorityMap.put(PLSQLPrinter.IS_NAN, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_PRESENT, priority);
    priorityMap.put(PLSQLPrinter.IS_PRESENT, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_INFINITE, priority);
    priorityMap.put(PLSQLPrinter.IS_INFINITE, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_A_SET, priority);
    priorityMap.put(PLSQLPrinter.IS_A_SET, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_EMPTY, priority);
    priorityMap.put(PLSQLPrinter.IS_EMPTY, priority);
    priorityMap.put(PLSQLPrinter.IS_NOT_OF_TYPE, priority);
    priorityMap.put(PLSQLPrinter.IS_OF_TYPE, priority);
    // 12
    ++priority;
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_NOT, priority);
    // 13
    ++priority;
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_AND, priority);
    // 14
    ++priority;
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_OR, priority);
    priorityMap.put(PLSQLPrinter.SQL92_RESERVED_CURSOR, priority);
  }
  
  static int getUnaryPriority(int nodeType) {
    if (unaryPriorityMap.containsKey(nodeType)) {
      return unaryPriorityMap.get(nodeType);
    }
    return -1;
  }
  
  static int getPriority(int nodeType) {
    if (priorityMap.containsKey(nodeType)) {
      return priorityMap.get(nodeType);
    }
    return -1;
  }
  
  static boolean canOmitParens(int parentPriority, int childPriority) {
    return parentPriority != -1 && childPriority != -1 && parentPriority >= childPriority;
  }
  
  static boolean canOmitParensNonAssoc(int parentPriority, int childPriority) {
    return parentPriority != -1 && childPriority != -1 && parentPriority > childPriority;
  }

}

@header {
/**
 * Oracle(c) PL/SQL 11g Parser  
 *
 * Copyright (c) 2014 Bars Group
 */
package ru.barsopen.plsqlconverter;
}

compilation_unit
    :    ^(COMPILATION_UNIT u+=unit_statement*)
    ->  template(statements={$u})
<<
<statements:{v|<v>}; separator="\n\n">
>>
    ;

sql_script
    :    ^(SQL_SCRIPT (u+=unit_statement|u+=sql_plus_command)*)
    ->  template(statements={$u})
<<
<statements; separator="\n\n">
>>
    ;
    

sql_plus_command 
    :   whenever_command
    |   exit_command
    |   prompt_command
    |   set_command
    ;

whenever_command
    :   ^(WHENEVER_VK
          (SQLERROR_VK|OSERROR_VK)
          (
            EXIT_VK
            (SUCCESS_VK|FAILURE_VK|WARNING_VK)
            (COMMIT_VK|ROLLBACK_VK)
            | CONTINUE_VK (COMMIT_VK|ROLLBACK_VK|NONE_VK)
          )
        )
    ;

set_command
    :    ^(SET_VK
            REGULAR_ID
            (CHAR_STRING|SQL92_RESERVED_ON|OFF_VK|EXACT_NUM_LIT|REGULAR_ID)
          )
    ;

exit_command
    :    EXIT_VK 
    ;

prompt_command
    :    PROMPT
    ;

unit_statement
    :    alter_function -> { $alter_function.st }
    |    alter_package -> { $alter_package.st}
    |    alter_procedure -> { $alter_procedure.st}
    |    alter_sequence -> { $alter_sequence.st}
    |    alter_trigger -> { $alter_trigger.st}
    |    alter_type -> { $alter_type.st}
    |    create_function_body -> { $create_function_body.st; }
    |    create_procedure_body -> { $create_procedure_body.st}
    |    create_package -> { $create_package.st}
    |    create_sequence -> { $create_sequence.st}
    |    create_trigger -> { $create_trigger.st}
    |    create_type -> { $create_type.st}
    |    drop_function -> { $drop_function.st}
    |    drop_package -> { $drop_package.st}
    |    drop_procedure -> { $drop_procedure.st}
    |    drop_sequence -> { $drop_sequence.st}
    |    drop_trigger -> { $drop_trigger.st}
    |    drop_type -> { $drop_type.st}
    ;

// $<DDL -> SQL Statements for Stored PL/SQL Units

// $<Function DDLs

drop_function
    :    ^(DROP_FUNCTION function_name)
    ->   template() "not implemented: drop_function"
    ;

alter_function
    :    ^(ALTER_FUNCTION function_name DEBUG_VK? REUSE_VK? compiler_parameters_clause*)
    ->   template() "not implemented: alter_function"
    ;

create_function_body
    :    ^(CREATE_FUNCTION SQL92_RESERVED_CREATE? REPLACE_VK? ^(FUNCTION_NAME name+=commented_id+) ret=type_spec ^(PARAMETERS args+=parameter*)
            ac+=invoker_rights_clause* ac+=parallel_enable_clause* ac+=result_cache_clause* DETERMINISTIC_VK* PIPELINED_VK?
            (    ^(USING_MODE AGGREGATE_VK? implementation_type_name)
    ->   template() "not implemented: create_function_body[USING_MODE]"
            |    ^(CALL_MODE call_spec)
    ->   template() "not implemented: create_function_body[CALL_MODE]"
            |    ^(BODY_MODE block)
                 -> create_function_body(
                      is_create={$SQL92_RESERVED_CREATE != null}, is_replace={$REPLACE_VK != null}, name_parts={$name},
                      arguments={$args}, return_type={$ret.st}, add_clauses={$ac}, is_pipelined={$PIPELINED_VK != null},
                      block={$block.st})
            )
        );

// $<Creation Function - Specific Clauses

parallel_enable_clause
    :    ^(PARALLEL_ENABLE_VK partition_by_clause?)
    ->   template() "not implemented: parallel_enable_clause"
    ;

partition_by_clause
    :    ^(PARTITION_VK expression 
            (    SQL92_RESERVED_ANY
            |    ^(HASH_VK ^(COLUMNS column_name+))
            |    ^(RANGE_VK ^(COLUMNS column_name+))
            ) 
            streaming_clause?
        )
    ->   template() "not implemented: partition_by_clause"
    ;

result_cache_clause
    :    ^(RESULT_CACHE_VK relies_on_part?)
    ->   template() "not implemented: result_cache_clause"
    ;

relies_on_part
    :    ^(RELIES_ON_VK tableview_name+)
    ->   template() "not implemented: relies_on_part"
    ;

streaming_clause
    :    ^(STREAMING_CLAUSE (SQL92_RESERVED_ORDER|CLUSTER_VK) expression ^(COLUMNS column_name+)) 
    ->   template() "not implemented: streaming_clause"
    ;
// $>
// $>

// $<Package DDLs

drop_package
    :    ^(DROP_PACKAGE package_name BODY_VK?)
    ->   template() "not implemented: drop_package"
    ;

alter_package
    :    ^(ALTER_PACKAGE package_name DEBUG_VK? REUSE_VK? 
                (PACKAGE_VK|BODY_VK|SPECIFICATION_VK)? compiler_parameters_clause*)
    ->   template() "not implemented: alter_package"
    ;

create_package
    :    ^(CREATE_PACKAGE_SPEC REPLACE_VK? package_name invoker_rights_clause? items+=package_obj_spec*) 
    ->   create_package_spec(
           is_replace={$REPLACE_VK != null}, name={$package_name.st},
           invoker_rights_clause={$invoker_rights_clause.st}, items={$items})
    |    ^(CREATE_PACKAGE_BODY REPLACE_VK? package_name items+=package_obj_body* seq_of_statements? exception_clause?)
    ->   create_package_body(
           is_replace={$REPLACE_VK != null}, name={$package_name.st}, items={$items},
           init_section_seq_of_statements={$seq_of_statements.st},
           init_section_exception_clause={$exception_clause.st})
    ;

// $<Create Package - Specific Clauses

package_obj_spec
    :    variable_declaration -> { $variable_declaration.st}
    |     subtype_declaration -> { $subtype_declaration.st}
    |     cursor_declaration -> { $cursor_declaration.st}
    |     exception_declaration -> { $exception_declaration.st}
    |     record_declaration -> { $record_declaration.st}
    |     table_declaration -> { $table_declaration.st}
    |     procedure_spec -> { $procedure_spec.st}
    |     function_spec -> { $function_spec.st}
    |     pragma_declaration -> { $pragma_declaration.st}
    ;

procedure_spec
    :     ^(PROCEDURE_SPEC procedure_name ^(PARAMETERS arguments+=parameter*)
            (^(CALL_MODE call_spec))?
    )
    ->   procedure_spec(name={$procedure_name.st}, arguments={$arguments})
    ;

function_spec
    :    ^(FUNCTION_SPEC function_name type_spec ^(PARAMETERS arguments+=parameter*)
            PIPELINED_VK? RESULT_CACHE_VK? DETERMINISTIC_VK?
        )
    ->   function_spec(
           name={$function_name.st}, arguments={$arguments}, return_type={$type_spec.st}, is_pipelined={$PIPELINED_VK != null},
           is_result_cache={$RESULT_CACHE_VK != null}, is_deterministic={$DETERMINISTIC_VK != null})
    ;

package_obj_body
    :     procedure_spec -> { $procedure_spec.st}
    |     function_spec -> { $function_spec.st}
    |     variable_declaration -> {$variable_declaration.st}
    |     subtype_declaration -> {$subtype_declaration.st}
    |     cursor_declaration -> {$cursor_declaration.st}
    |     exception_declaration -> {$exception_declaration.st}
    |     record_declaration -> {$record_declaration.st}
    |     table_declaration -> {$table_declaration.st}
    |     create_procedure_body -> {$create_procedure_body.st}
    |     create_function_body -> {$create_function_body.st}
    |     create_type -> {$create_type.st}
    ;

// $>

// $>

// $<Procedure DDLs

drop_procedure
    :    ^(DROP_PROCEDURE procedure_name)
    ->   template() "not implemented: drop_procedure"
    ;

alter_procedure
    :    ^(ALTER_PROCEDURE procedure_name DEBUG_VK? REUSE_VK? compiler_parameters_clause*)
    ->   template() "not implemented: alter_procedure"
    ;

create_procedure_body
    :    ^(CREATE_PROCEDURE SQL92_RESERVED_CREATE? REPLACE_VK? procedure_name ^(PARAMETERS arguments+=parameter*) invoker_rights_clause?
           create_procedure_body_impl
        )
    ->   create_procedure_body(
          is_create={$SQL92_RESERVED_CREATE != null}, is_replace={$REPLACE_VK != null}, name={$procedure_name.st}, arguments={$arguments},
          invoker_rights_clause={$invoker_rights_clause.st}, impl={$create_procedure_body_impl.st})
    ;
    
create_procedure_body_impl
    :   EXTERNAL_VK -> create_procedure_body_impl_external()
        |    ^(CALL_MODE call_spec) -> { $call_spec.st }
        |    ^(BODY_MODE block) -> { $block.st }
    ;

// $>

// $<Trigger DDLs

drop_trigger
    :    ^(DROP_TRIGGER trigger_name)
    ->   template() "not implemented: drop_trigger"
    ;

alter_trigger
    :    ^(ALTER_TRIGGER trigger_name 
            (    (ENABLE_VK|DISABLE_VK)
            |    ^(RENAME_VK trigger_name)
            |    DEBUG_VK? REUSE_VK? compiler_parameters_clause*
            )
        )
    ->   template() "not implemented: alter_trigger"
    ;

create_trigger
    :    ^(CREATE_TRIGGER REPLACE_VK? trigger_name  
            simple_dml_trigger? compound_dml_trigger? non_dml_trigger?
            trigger_follows_clause? (ENABLE_VK|DISABLE_VK)? trigger_when_clause? trigger_body)
    ->   template() "not implemented: create_trigger"
    ;

trigger_follows_clause
    :    ^(FOLLOWS_VK trigger_name+)
    ->   template() "not implemented: trigger_follows_clause"
    ;

trigger_when_clause
    :    ^(SQL92_RESERVED_WHEN expression)
    ->   template() "not implemented: trigger_when_clause"
    ;

// $<Create Trigger- Specific Clauses
simple_dml_trigger
    :    ^(SIMPLE_DML (BEFORE_VK|AFTER_VK|INSTEAD_VK) FOR_EACH_ROW? referencing_clause? dml_event_clause)
    ->   template() "not implemented: simple_dml_trigger"
    ;

compound_dml_trigger
    :    ^(COMPOUND_DML referencing_clause? dml_event_clause)
    ->   template() "not implemented: compound_dml_trigger"
    ;

non_dml_trigger
    :    ^(NON_DML (BEFORE_VK|AFTER_VK) non_dml_event+ (DATABASE_VK|schema_name? SCHEMA_VK))
    ->   template() "not implemented: non_dml_trigger"
    ;

trigger_body
    :    ^(COMPOUND_VK trigger_name declare_spec* timing_point_section+)
    ->   template() "not implemented: trigger_body"
    |    ^(CALL_VK routine_name function_argument?) 
    ->   template() "not implemented: trigger_body"
    |    ^(BODY_MODE block)
    ->   template() "not implemented: trigger_body"
    ;

timing_point_section
    :    ^(BEFORE_STATEMENT block)
    ->   template() "not implemented: timing_point_section"
    |    ^(BEFORE_EACH_ROW block)
    ->   template() "not implemented: timing_point_section"
    |    ^(AFTER_STATEMENT block)
    ->   template() "not implemented: timing_point_section"
    |    ^(AFTER_EACH_ROW block)
    ->   template() "not implemented: timing_point_section"
    ;

non_dml_event
    :    SQL92_RESERVED_ALTER
    ->   template() "not implemented: non_dml_event"
    |    ANALYZE_VK
    ->   template() "not implemented: non_dml_event"
    |    ASSOCIATE_VK STATISTICS_VK
    ->   template() "not implemented: non_dml_event"
    |    AUDIT_VK
    ->   template() "not implemented: non_dml_event"
    |    COMMENT_VK
    ->   template() "not implemented: non_dml_event"
    |    SQL92_RESERVED_CREATE
    ->   template() "not implemented: non_dml_event"
    |    DISASSOCIATE_VK STATISTICS_VK
    ->   template() "not implemented: non_dml_event"
    |    SQL92_RESERVED_DROP
    ->   template() "not implemented: non_dml_event"
    |    SQL92_RESERVED_GRANT
    ->   template() "not implemented: non_dml_event"
    |    NOAUDIT_VK
    ->   template() "not implemented: non_dml_event"
    |    RENAME_VK
    ->   template() "not implemented: non_dml_event"
    |    SQL92_RESERVED_REVOKE
    ->   template() "not implemented: non_dml_event"
    |    TRUNCATE_VK
    ->   template() "not implemented: non_dml_event"
    |    DDL_VK
    ->   template() "not implemented: non_dml_event"
    |    STARTUP_VK
    ->   template() "not implemented: non_dml_event"
    |    SHUTDOWN_VK
    ->   template() "not implemented: non_dml_event"
    |    DB_ROLE_CHANGE_VK
    ->   template() "not implemented: non_dml_event"
    |    LOGON_VK
    ->   template() "not implemented: non_dml_event"
    |    LOGOFF_VK
    ->   template() "not implemented: non_dml_event"
    |    SERVERERROR_VK
    ->   template() "not implemented: non_dml_event"
    |    SUSPEND_VK
    ->   template() "not implemented: non_dml_event"
    |    DATABASE_VK
    ->   template() "not implemented: non_dml_event"
    |    SCHEMA_VK
    ->   template() "not implemented: non_dml_event"
    |    FOLLOWS_VK
    ->   template() "not implemented: non_dml_event"
    ;

dml_event_clause
    :    ^(DML_EVENT dml_event_element+ ^(SQL92_RESERVED_ON tableview_name dml_event_nested_clause?))  
    ->   template() "not implemented: dml_event_clause"
    ;

dml_event_element
    :    ^(DML_EVENT_ELEMENT (SQL92_RESERVED_DELETE|SQL92_RESERVED_INSERT|SQL92_RESERVED_UPDATE) ^(COLUMNS column_name*))
    ->   template() "not implemented: dml_event_element"
    ;

dml_event_nested_clause
    :    ^(NESTED_VK tableview_name)
    ->   template() "not implemented: dml_event_nested_clause"
    ;

referencing_clause
    :    ^(REFERENCING_VK referencing_element+)
    ->   template() "not implemented: referencing_clause"
    ;

referencing_element
    :    ^((NEW_VK|OLD_VK|PARENT_VK) alias)
    ->   template() "not implemented: referencing_element"
    ;

// $>
// $>

// $<Type DDLs

drop_type
    :    ^(DROP_TYPE type_name BODY_VK? FORCE_VK? VALIDATE_VK?)
    ->   template() "not implemented: drop_type"
    ;

alter_type
    :    ^(ALTER_TYPE type_name 
            (    ^(REPLACE_VK ^(OBJECT_MEMBERS element_spec+) invoker_rights_clause?)
            |    ^(ALTER_ATTRIBUTE (ADD_VK|MODIFY_VK|SQL92_RESERVED_DROP) ^(ATTRIBUTES attribute_definition+))
            |    ^(ALTER_METHOD alter_method_element+)
            |    alter_collection_clauses
            |    modifier_clause
            |    ^(COMPILE_VK (SPECIFICATION_VK|BODY_VK)? DEBUG_VK? REUSE_VK? compiler_parameters_clause*)
            ) 
            dependent_handling_clause?
        )
    ->   template() "not implemented: alter_type"
    ;

// $<Alter Type - Specific Clauses

alter_method_element
    :    ^(ALTER_METHOD_ELEMENT (ADD_VK|SQL92_RESERVED_DROP) map_order_function_spec? subprogram_spec?)
    ->   template() "not implemented: alter_method_element"
    ;

attribute_definition
    :    ^(ATTRIBUTE attribute_name type_spec?)
    ->   template() "not implemented: attribute_definition"
    ;

alter_collection_clauses
    :    ^(ALTER_COLLECTION 
            (    ^(TYPE_VK type_spec)
            |    ^(LIMIT_VK expression)
            )
        ) 
    ->   template() "not implemented: alter_collection_clauses"
    ;

dependent_handling_clause
    :    ^(DEPENDENT_HANDLING 
            (    INVALIDATE_VK
            |    ^(CASCADE_VK 
                    (    CONVERT_VK
                    |    SQL92_RESERVED_NOT? INCLUDING_VK
                    )?
                )
            )
        )
    ->   template() "not implemented: dependent_handling_clause"
    ;

dependent_exceptions_part
    :    ^(EXCEPTIONS_VK FORCE_VK? tableview_name)
    ->   template() "not implemented: dependent_exceptions_part"
    ;

// $>

create_type
    :    ^(CREATE_TYPE_BODY SQL92_RESERVED_CREATE? REPLACE_VK? type_name type_body_elements_list)
    ->   type_decl(is_create={$SQL92_RESERVED_CREATE != null}, is_replace={$REPLACE_VK != null}, type_name={$type_name.st}, oid={""}, def={$type_body_elements_list.st})
    |    ^(CREATE_TYPE_SPEC SQL92_RESERVED_CREATE? REPLACE_VK? type_name CHAR_STRING? object_type_def)
    ->   type_decl(is_create={$SQL92_RESERVED_CREATE != null}, is_replace={$REPLACE_VK != null}, type_name={$type_name.st}, oid={$CHAR_STRING.text}, def={$object_type_def.st})
    ;
    
type_body_elements_list
    :   ^(TYPE_BODY_ELEMENTS type_body_elements+) 
    ->   template() "not implemented: type_body_elements_list"
    ;

object_type_def
    :    ^(OBJECT_TYPE_DEF (object_as_part|object_under_part) invoker_rights_clause?
             sqlj_object_type? mod_clauses+=modifier_clause* ^(OBJECT_MEMBERS specs+=element_spec*))  
    ->   object_type_def(
          invoker_rights_clause={$invoker_rights_clause.st},
          object_as_or_under_part={$object_as_part.st != null ? $object_as_part.st : $object_under_part.st},
          sqlj_object_type={$sqlj_object_type.st},
          object_member_specs={$specs}, modifier_clauses={$mod_clauses})
    ;

object_as_part
    :    ^(OBJECT_AS
            (
              OBJECT_VK
              -> object_as_part(def={ %object_as_part_def_object() })
              | varray_type_def
              -> object_as_part(def={ $varray_type_def.st })
              | nested_table_type_def
              -> object_as_part(def={ $nested_table_type_def.st })
            )
          )
    ;

object_under_part
    :    ^(UNDER_VK type_spec)
    ->  object_under_part(type_spec={$type_spec.st})
    ;

nested_table_type_def
    :    ^(NESTED_TABLE_TYPE_DEF type_spec SQL92_RESERVED_NULL? table_indexed_by_part?)
    ->   nested_table_type_def(
          type_spec={$type_spec.st}, is_not_null={$SQL92_RESERVED_NULL != null},
          table_indexed_by_part={$table_indexed_by_part.st}
        )
    ;

sqlj_object_type
    :    ^(JAVA_VK expression (SQLDATA_VK|CUSTOMDATUM_VK|ORADATA_VK))
    ->   template() "not implemented: sqlj_object_type"
    ;

type_body_elements
    :    map_order_func_declaration
    ->   template() "not implemented: type_body_elements"
    |    subprog_decl_in_type
    ->   template() "not implemented: type_body_elements"
    ;

map_order_func_declaration
    :    ^((MAP_VK|SQL92_RESERVED_ORDER) create_function_body)
    ->   template() "not implemented: map_order_func_declaration"
    ;

subprog_decl_in_type
    :    ^((MEMBER_VK|STATIC_VK)
            (    create_procedure_body
            |    create_function_body
            |    constructor_declaration
            )
        )
    ->   template() "not implemented: subprog_decl_in_type"
    ;

constructor_declaration
    :    ^(CONSTRUCTOR_VK type_spec FINAL_VK? INSTANTIABLE_VK? ^(PARAMETERS type_elements_parameter*) 
            (    ^(CALL_MODE call_spec)
            |    ^(BODY_MODE block)
            )
        )
    ->   template() "not implemented: constructor_declaration"
    ;

// $>

// $<Common Type Clauses

modifier_clause
    :    ^(MODIFIER SQL92_RESERVED_NOT? (INSTANTIABLE_VK|FINAL_VK|OVERRIDING_VK))
    ->   template() "not implemented: modifier_clause"
    ;

sqlj_object_type_attr
    :    ^(EXTERNAL_VK expression)
    ->   template() "not implemented: sqlj_object_type_attr"
    ;

element_spec
    :    ^(ELEMENT_SPEC element_spec_options+ modifier_clause? pragma_clause?)
    ->   template() "not implemented: element_spec"
    ;

element_spec_options
    :    subprogram_spec
    ->   template() "not implemented: element_spec_options"
    |    constructor_spec
    ->   template() "not implemented: element_spec_options"
    |    map_order_function_spec
    ->   template() "not implemented: element_spec_options"
    |    ^(FIELD_SPEC commented_id type_spec sqlj_object_type_attr?)
    ->   template() "not implemented: element_spec_options"
    ;

subprogram_spec
    :    ^((MEMBER_VK|STATIC_VK)
            (    procedure_spec
            |    function_spec
            )
        )
    ->   template() "not implemented: subprogram_spec"
    ;

constructor_spec
    :    ^(CONSTRUCTOR_SPEC type_spec FINAL_VK? INSTANTIABLE_VK? ^(PARAMETERS type_elements_parameter*) constructor_call_mode?)
    ->   template() "not implemented: constructor_spec"
    ;

constructor_call_mode
    :    ^(CALL_MODE call_spec)
    ->   template() "not implemented: constructor_call_mode"
    ;

map_order_function_spec
    :    ^((MAP_VK|SQL92_RESERVED_ORDER) function_spec)
    ->   template() "not implemented: map_order_function_spec"
    ;

pragma_clause
    :    ^(PRAGMA_VK pragma_elements+)
    ->   template() "not implemented: pragma_clause"
    ;

pragma_elements
    :    commented_id
    ->   template() "not implemented: pragma_elements"
    |    SQL92_RESERVED_DEFAULT
    ->   template() "not implemented: pragma_elements"
    ;

type_elements_parameter
    :    ^(PARAMETER parameter_name type_spec)
    ->   template() "not implemented: type_elements_parameter"
    ;

// $>
// $>


// $<Sequence DDLs

drop_sequence
    :   ^(DROP_SEQUENCE sequence_name)
    ->   template() "not implemented: drop_sequence"
    ;

alter_sequence
    :    ^(ALTER_SEQUENCE sequence_name sequence_spec+)
    ->   template() "not implemented: alter_sequence"
    ;

create_sequence
    :    ^(CREATE_SEQUENCE sequence_name sequence_spec*)
    ->   template() "not implemented: create_sequence"
    ;

// $<Common Sequence

sequence_spec
    :    ^(PLSQL_RESERVED_START UNSIGNED_INTEGER)
    ->   template() "not implemented: sequence_spec"
    |    ^(INCREMENT_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: sequence_spec"
    |    ^(MAXVALUE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: sequence_spec"
    |    ^(MINVALUE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: sequence_spec"
    |    ^(CACHE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: sequence_spec"
    |    NOMAXVALUE_VK
    ->   template() "not implemented: sequence_spec"
    |    NOMINVALUE_VK
    ->   template() "not implemented: sequence_spec"
    |    CYCLE_VK
    ->   template() "not implemented: sequence_spec"
    |    NOCYCLE_VK
    ->   template() "not implemented: sequence_spec"
    |    NOCACHE_VK
    ->   template() "not implemented: sequence_spec"
    |    SQL92_RESERVED_ORDER
    ->   template() "not implemented: sequence_spec"
    |    NOORDER_VK
    ->   template() "not implemented: sequence_spec"
    ;

// $>
// $>

// $< DDL


create_view
    :   ^(CREATE_VIEW
          REPLACE_VK?
          create_view_force_clause?
          view_name
          view_type_clause?
          subquery
          subquery_restriction_clause?
        )
        -> create_view(
          is_replace={$REPLACE_VK != null}, force_clause={$create_view_force_clause.st},
          name={$view_name.st}, view_type_clause={$view_type_clause.st}, subquery={$subquery.st},
          subquery_restriction_clause={$subquery_restriction_clause.st})
    ;
    
create_view_force_clause
    :   NOFORCE -> create_view_force_clause_noforce()
    |   FORCE_VK -> create_view_force_clause_force()
    ;
    
view_type_clause
    :   view_type_constraints_clause -> { $view_type_constraints_clause.st }
        // TODO not implemented http://docs.oracle.com/cd/B28359_01/server.111/b28286/statements_8004.htm
        //| object_view_clause
        //| xmltype_view_clause
    ;
    
view_type_constraints_clause
    :   ^(VIEW_TYPE_CONSTRAINTS items+=view_type_constraints_clause_item+)
        -> view_type_constraints_clause(items={ $items })
    ;

view_type_constraints_clause_item
    :   view_type_constraint_item_inline -> { $view_type_constraint_item_inline.st }
        //| out_of_line_constraint_clause -> { $out_of_line_constraint_clause.st }
    ;
    
view_type_constraint_item_inline
    :   ^(VIEW_TYPE_CONSTRAINT_ITEM_INLINE id=commented_id /* items+=inline_constraint_clause* */)
        -> view_type_constraint_item_inline(id={$id.st}/*, items={$items}*/)
    ;
 /*   
inline_constraint_clause
    :   ^(INLINE_CONSTRAINT_CLAUSE id=commented_id? inline_constraint_def items+=constraint_state_item*)
        -> inline_constraint_clause(id={$id.st}, inline_constraint_def={$inline_constraint_def.st}, items={$items})
    ;
    
inline_constraint_def
    :
        inline_constraint_null
        | inline_constraint_not_null
        | inline_constraint_unique
        | inline_constraint_primary_key
        | references_clause
        | checks_clause
    ;
    
inline_constraint_null: SQL92_RESERVED_NULL -> inline_constraint_null();
inline_constraint_not_null: NOT_NULL -> inline_constraint_not_null();
inline_constraint_unique: SQL92_RESERVED_UNIQUE -> inline_constraint_unique();
inline_constraint_primary_key: PRIMARY_KEY -> inline_constraint_primary_key();

references_clause
    : ^(REFERENCES_CLAUSE tableview_name ids+=commented_id+ references_on_delete_clause?)
      -> references_clause(name={$tableview_name.st}, columns={$ids}, on_delete_clause={$references_on_delete_clause.st})
    ;

references_on_delete_clause
    : ^(ON_DELETE on_delete_clause_action)
      -> references_on_delete_clause(action={$on_delete_clause_action.st})
    ;
    
on_delete_clause_action
    : CASCADE_VK -> on_delete_clause_action_cascade()
    | SET_NULL -> on_delete_clause_action_set_null()
    ;
    
checks_clause
    : ^(SQL92_RESERVED_CHECK expression)
      -> checks_clause(expr={$expression.st})
    ;
    
constraint_state_item
    : NOT_DEFERRABLE -> constraint_state_item_not_deferrable()
    | DEFERRABLE_VK -> constraint_state_item_deferrable()
    | IMMEDIATE_VK -> constraint_state_item_immediate()
    | DEFERRED_VK -> constraint_state_item_deferred()
    | RELY_VK -> constraint_state_item_rely()
    | NORELY_VK -> constraint_state_item_norely()
    | using_index_clause -> { $using_index_clause.st }
    | ENABLE_VK -> constraint_state_item_enable()
    | DISABLE_VK -> constraint_state_item_disable()
    | VALIDATE_VK -> constraint_state_item_validate()
    | NOVALIDATE_VK -> constraint_state_item_novalidate()
    | exceptions_clause -> { $exceptions_clause.st }
    ;
    
using_index_clause
    : ^(USING_INDEX using_index_def)
      -> using_index_clause(index_def={$using_index_def.st})
    ;

using_index_def
    : tableview_name -> { $tableview_name.st }
    // TODO not implemented http://docs.oracle.com/cd/B28359_01/server.111/b28286/clauses002.htm#CJAIHHGC
    // | LEFT_PAREN create_index_statement RIGHT_PAREN
    // | index_properties
    ;
  
exceptions_clause
    : ^(EXCEPTIONS_VK tableview_name)
      -> exceptions_clause(name={$tableview_name.st})
    ;
    
out_of_line_constraint_clause
    : ^(OUT_OF_LINE_CONSTRAINT id=commented_id? out_of_line_constraint_def items+=constraint_state_item*)
      -> out_of_line_constraint_clause(
          name={$id.st},out_of_line_constraint_def={$out_of_line_constraint_def.st}, items={$items})
    ;

out_of_line_constraint_def
    : out_of_line_constraint_def_unique -> { $out_of_line_constraint_def_unique.st }
    | out_of_line_constraint_def_primary_key -> { $out_of_line_constraint_def_primary_key.st }
    | out_of_line_constraint_def_foreign_key -> { $out_of_line_constraint_def_foreign_key.st }
    | checks_clause -> { $checks_clause.st }
    ;
    
out_of_line_constraint_def_unique
    : ^(SQL92_RESERVED_UNIQUE ids+=commented_id+)
      -> out_of_line_constraint_def_unique(columns={$ids})
    ;
    
out_of_line_constraint_def_primary_key
    : ^(PRIMARY_KEY ids+=commented_id+)
      -> out_of_line_constraint_def_primary_key(columns={$ids})
    ;

out_of_line_constraint_def_foreign_key
    : ^(FOREIGN_KEY ids+=commented_id+ references_clause)
      -> out_of_line_constraint_def_foreign_key(columns={$ids}, references_clause={$references_clause.st})
    ;*/

// $>


// $<Common DDL Clauses

invoker_rights_clause
    :    ^(AUTHID_VK (CURRENT_USER_VK|DEFINER_VK))
    ->   template() "not implemented: invoker_rights_clause"
    ;

compiler_parameters_clause
    :    ^(COMPILER_PARAMETER ^(ASSIGN id=commented_id expression))
    ->   template() "not implemented: compiler_parameters_clause"
    ;

call_spec
    :    ^(LANGUAGE_VK ( declaration=java_spec | declaration=c_spec ))
    ->   call_spec(declaration={$declaration.st})
    ;

// $<Call Spec - Specific Clauses

java_spec
    :    ^(JAVA_VK CHAR_STRING)
    ->   call_spec_java(name={$CHAR_STRING.text})
    ;

c_spec
    :    ^(C_VK CHAR_STRING? CONTEXT_VK? ^(LIBRARY_VK commented_id) c_agent_in_clause? c_parameters_clause?)
    ->   template() "not implemented: c_spec"
    ;

c_agent_in_clause
    :    ^(AGENT_VK expression+)
    ->   template() "not implemented: c_agent_in_clause"
    ;

c_parameters_clause
    :    ^(PARAMETERS_VK (THREE_DOTS|expression+))
    ->   template() "not implemented: c_parameters_clause"
    ;

// $>

parameter
    :    ^(PARAMETER parameter_name (SQL92_RESERVED_IN|OUT_VK|INOUT_VK)* type_spec? default_value_part?)
    ->   parameter(name={$parameter_name.st}, is_in={$SQL92_RESERVED_IN != null},
           is_out={$OUT_VK != null}, is_inout={$INOUT_VK != null}, is_nocopy={false}, type={$type_spec.st},
           default_value_part={$default_value_part.st})
    ;

default_value_part
    :    ^(DEFAULT_VALUE expression)
    ->   default_value_part(expression={$expression.st})
    ;

// $>

// $>

// $<PL/SQL Elements Declarations

declare_spec
    :    variable_declaration -> { $variable_declaration.st }
    |     subtype_declaration -> { $subtype_declaration.st }
    |     cursor_declaration -> { $cursor_declaration.st }
    |     exception_declaration -> { $exception_declaration.st }
    |     pragma_declaration -> { $pragma_declaration.st }
    |     record_declaration -> { $record_declaration.st }
    |     table_declaration -> { $table_declaration.st }
    |     create_procedure_body -> { $create_procedure_body.st }
    |     create_function_body -> { $create_function_body.st }
    |     create_type -> { $create_type.st }
    ;

//incorporates constant_declaration
variable_declaration
    :    ^(VARIABLE_DECLARE variable_name type_spec CONSTANT_VK? SQL92_RESERVED_NULL? default_value_part?)
    ->   variable_declaration(
           name={$variable_name.st}, type={$type_spec.st}, is_constant={$CONSTANT_VK != null},
           is_not_null={$SQL92_RESERVED_NULL != null}, default_value_part={$default_value_part.st})
    ;    

subtype_declaration
    :    ^(SUBTYPE_DECLARE type_name type_spec SQL92_RESERVED_NULL? subtype_range?)
    ->   subtype_declaration(
            name={$type_name.st}, type_spec={$type_spec.st},
            range={$subtype_range.st}, is_not_null={$SQL92_RESERVED_NULL != null})
    ;

subtype_range
    :    ^(RANGE_VK e1=expression e2=expression)
    ->   subtype_range(low_bound={$e1.st}, high_bound={$e2.st})
    ;

//cursor_declaration incorportates curscursor_body and cursor_spec
cursor_declaration
    :    ^(CURSOR_DECLARE cursor_name type_spec? select_statement? ^(PARAMETERS parameters+=parameter_spec*)) 
    ->   cursor_declaration(
          cursor_name={$cursor_name.st}, parameters={$parameters},
          type_spec={$type_spec.st}, select_statement={$select_statement.st})
    ;

parameter_spec
    :    ^(PARAMETER parameter_name type_spec? default_value_part?)
    ->   parameter_spec(
          parameter_name={$parameter_name.st}, type_spec={$type_spec.st},
          default_value_part={$default_value_part.st})
    ;

exception_declaration 
    :    ^(EXCEPTION_DECLARE exception_name)
    ->   exception_declaration(name={$exception_name.st})
    ;             

pragma_declaration
    :    ^(PRAGMA_DECLARE pragma_declaration_impl)
    ->   pragma_declaration(impl={$pragma_declaration_impl.st})
    ;
    
pragma_declaration_impl
@init { StringTemplate firstSt = null; }
    :    SERIALLY_REUSABLE_VK -> pragma_declaration_impl_serially_reusable()
         |     AUTONOMOUS_TRANSACTION_VK -> pragma_declaration_impl_serially_autonomous_transaction()
         |    ^(EXCEPTION_INIT_VK exception_name constant)
         ->   pragma_declaration_impl_serially_exception_init(name={$exception_name.st}, numeric={$constant.st})
         |    ^(INLINE_VK id=commented_id expression)
         ->   pragma_declaration_impl_serially_inline(id={$id.st}, expression={$expression.st})
         |    ^(RESTRICT_REFERENCES_VK
                (
                  SQL92_RESERVED_DEFAULT { firstSt = %string_literal(val={"default"}); }
                  | firstId=commented_id { firstSt = $firstId.st; }
                )
                rest+=commented_id+
              )
         ->   pragma_declaration_impl_serially_restrict_references(arg1={firstSt}, restArgs={$rest})
    ;
   

record_declaration
    :    record_type_dec -> { $record_type_dec.st }
    |    record_var_dec -> { $record_var_dec.st }
    ;

// $<Record Declaration - Specific Clauses

//incorporates ref_cursor_type_definition
record_type_dec
    :    record_type_dec_record -> { $record_type_dec_record.st }
    |    record_type_dec_refcursor -> { $record_type_dec_refcursor.st }
    ;
    
record_type_dec_record
    :    ^(RECORD_TYPE_DECLARE_FIELDS type_name fields+=field_spec+)
    ->   record_type_dec_record(name={$type_name.st}, field_specs={$fields})
    ;
    
record_type_dec_refcursor
    :    ^(RECORD_TYPE_DECLARE_REFCURSOR type_name type_spec?)
    ->   record_type_dec_refcursor(name={$type_name.st}, type_spec={$type_spec.st})
    ;

field_spec
    :    ^(FIELD_SPEC column_name type_spec? SQL92_RESERVED_NULL? default_value_part?)
    ->  field_spec(
          name={$column_name.st}, type_spec={$type_spec.st}, is_not_null={$SQL92_RESERVED_NULL != null},
          default_value_part={$default_value_part.st})
    ;

record_var_dec
    :    ^(RECORD_VAR_DECLARE record_name type_name (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK))
    ->   template() "not implemented: record_var_dec"
    ;

// $>

table_declaration
    :    table_type_dec -> { $table_type_dec.st }
    |    table_var_dec -> { $table_var_dec.st }
    ;

table_type_dec
    :    ^(TABLE_TYPE_DECLARE type_name table_type_dec_impl)
    ->   table_type_dec(name={$type_name.st}, impl={$table_type_dec_impl.st})
    ;
    
table_type_dec_impl
    :     table_type_dec_table_of -> { $table_type_dec_table_of.st }
    |     varray_type_def -> { $varray_type_def.st }
    ;
    
table_type_dec_table_of
    :    SQL92_RESERVED_NULL? ^(SQL92_RESERVED_TABLE type_spec table_indexed_by_part?)
    ->   table_type_dec_table_of(
            type_spec={$type_spec.st}, table_indexed_by_part={$table_indexed_by_part.st}, is_not_null={$SQL92_RESERVED_NULL != null})
    ;

table_indexed_by_part
    :    ^(INDEXED_BY type_spec)
    ->   table_indexed_by_part(type_spec={$type_spec.st})
    ;

varray_type_def
    :    SQL92_RESERVED_NULL? ^(VARR_ARRAY_DEF expression type_spec)
    ->   varray_type_def(limit_expression={$expression.st}, element_type={$type_spec.st}, is_not_null={$SQL92_RESERVED_NULL != null})
    ;

table_var_dec
    :    ^(TABLE_VAR_DECLARE table_var_name type_spec)
    ->   template() "not implemented: table_var_dec"
    ;

// $>

// $<PL/SQL Statements

seq_of_statements
    :     ^(STATEMENTS (statements+=statement|statements+=labeled_statement)+)
    -> seq_of_statements(statements={$statements})
    ;
    
labeled_statement
    :    ^(LABEL_DECLARE label_name statement) -> labeled_statement(name={$label_name.st}, statement={$statement.st})
    ;

statement
    :    assignment_statement -> { $assignment_statement.st }
    |    continue_statement -> { $continue_statement.st }
    |    exit_statement -> { $exit_statement.st }
    |    goto_statement -> { $goto_statement.st }
    |    if_statement -> { $if_statement.st }
    |    loop_statement -> { $loop_statement.st }
    |    forall_statement -> { $forall_statement.st }
    |    null_statement -> { $null_statement.st }
    |    raise_statement -> { $raise_statement.st }
    |    return_statement -> { $return_statement.st }
    |    pipe_row_statement -> { $pipe_row_statement.st }
    |    case_statement -> { $case_statement.st }
    |    sql_statement -> { $sql_statement.st }
    |    perform_statement -> { $perform_statement.st }
    |    general_element -> { $general_element.st }
    |    pgsql_raise_statement -> { $pgsql_raise_statement.st }
    |    body -> { $body.st }
    |    block -> { $block.st }
    ;

assignment_statement
    :     ^(ASSIGN (dst=general_element|dst=hosted_variable_name) expression)
    ->   assignment_statement(dst={$dst.st}, expression={$expression.st})
    ;

continue_statement
    :    ^(CONTINUE_VK label_name? general_when?)
    ->   continue_statement(label_name={$label_name.st}, condition={$general_when.st})
    ;

general_when
    :    ^(SQL92_RESERVED_WHEN expression)
    ->   general_when(expression={$expression.st})
    ;

exit_statement
    :    ^(EXIT_VK label_name? general_when?)
    ->   exit_statement(label_name={$label_name.st}, condition={$general_when.st})
    ;

goto_statement
    :    ^(SQL92_RESERVED_GOTO label_name)
    ->   goto_statement(label_name={$label_name.st})
    ;

if_statement
    :    ^(PLSQL_RESERVED_IF expression seq_of_statements elsif_parts+=elsif_part* else_part?)
    ->   if_statement(
          condition={$expression.st}, then_seq_of_statements={$seq_of_statements.st},
          elsif_parts={$elsif_parts}, else_part={$else_part.st})
    ;

elsif_part
    :    ^(PLSQL_NON_RESERVED_ELSIF expression seq_of_statements)
    ->   elsif_part(condition={$expression.st}, seq_of_statements={$seq_of_statements.st})
    ;

else_part
    :    ^(SQL92_RESERVED_ELSE seq_of_statements)
    ->   else_part(seq_of_statements={$seq_of_statements.st})
    ;

loop_statement
    :    ^(WHILE_LOOP expression seq_of_statements)
    ->   while_loop(condition={$expression.st}, seq_of_statements={$seq_of_statements.st})
    |    ^(FOR_LOOP cursor_loop_param seq_of_statements)
    ->   for_loop(loopDefinition={$cursor_loop_param.st}, statements={$seq_of_statements.st})
    |    ^(LOOP_VK seq_of_statements)
    ->   loop(seq_of_statements={$seq_of_statements.st})
    ;

// $<Loop - Specific Clause

cursor_loop_param
    :    ^(INDEXED_FOR index_name REVERSE_VK? ^(SIMPLE_BOUND b1=expression b2=expression))
    ->   loopDefinition_bounds(indexVar={$index_name.st}, isReverse={$REVERSE_VK != null}, lowerBound={$b1.st}, upperBound={$b2.st})
    |    ^(CURSOR_BASED_FOR record_name general_element)
    ->   loopDefinition_cursor(indexVar={$record_name.st}, cursor={$general_element.st})
    |    ^(SELECT_BASED_FOR record_name select_statement)
    ->   loopDefinition_select(indexVar={$record_name.st}, select_statement={$select_statement.st})
    ;

// $>

forall_statement
    :    ^(FORALL_VK index_name bounds_clause sql_statement EXCEPTIONS_VK?)
    ->   template() "not implemented: forall_statement"
    ;

bounds_clause
    :    ^(SIMPLE_BOUND expression expression)
    ->   template() "not implemented: bounds_clause"
    |    ^(INDICES_BOUND collection_name between_bound?)
    ->   template() "not implemented: bounds_clause"
    |    ^(VALUES_BOUND index_name) 
    ->   template() "not implemented: bounds_clause"
    ;

between_bound
    :    ^(SQL92_RESERVED_BETWEEN expression expression)
    ->   template() "not implemented: between_bound"
    ;

null_statement
    :    SQL92_RESERVED_NULL
    ->   null_statement()
    ;

raise_statement
    :    ^(RAISE_VK exception_name?)
    ->   raise_statement(name={$exception_name.st})
    ;

return_statement
    :    ^(RETURN_VK expression?) -> return_statement(expression={$expression.st})
    ;

body
    :    ^(BODY label_name? seq_of_statements exception_clause?) 
    ->   body(bodyLabel={$label_name.st}, statements={$seq_of_statements.st}, exception_clause={$exception_clause.st})
    ;

// $<Body - Specific Clause

exception_clause
    :    ^(SQL92_RESERVED_EXCEPTION handlers+=exception_handler+)
    ->   exception_clause(exception_handlers={$handlers})
    ;

exception_handler
    :    ^(SQL92_RESERVED_WHEN names+=exception_name+ seq_of_statements)
    ->   exception_handler(exception_names={$names}, seq_of_statements={$seq_of_statements.st})
    ;

// $>

block
    :    ^(BLOCK specs+=declare_spec* body)
    ->   block(declare_spec={$specs}, body={$body.st})
    ;

// $>

perform_statement
    :   ^(PGSQL_PERFORM inner=expression)
    ->  perform_statement_expr(inner={$inner.st})
    |   ^(PGSQL_PERFORM inner=data_manipulation_language_statements)
    ->  perform_statement_sql(inner={$inner.st});

// $<SQL PL/SQL Statements

sql_statement
    :    execute_immediate -> { $execute_immediate.st }
    |    data_manipulation_language_statements -> { $data_manipulation_language_statements.st }
    |    cursor_manipulation_statements -> { $cursor_manipulation_statements.st }
    |    transaction_control_statements -> { $transaction_control_statements.st }
    ;

execute_immediate
    :    ^(EXECUTE_VK expression
          (    into=into_clause using=using_clause?
          |    using=using_clause dynamic_returning=dynamic_returning_clause?
          |    dynamic_returning=dynamic_returning_clause
          )?
        )
    ->   execute_immediate_statement(
          expression={$expression.st}, into_clause={$into.st},
          using_clause={$using.st}, dynamic_returning_clause={$dynamic_returning.st})
    ;

// $<Execute Immediate - Specific Clause
dynamic_returning_clause
    :    ^(DYNAMIC_RETURN into_clause)
    ->   dynamic_returning_clause(into_clause={$into_clause.st})
    ;
// $>

pgsql_raise_statement
    :   ^(PGSQL_RAISE pgsql_raise_level CHAR_STRING expr+=expression* pgsql_raise_using_options?)
        -> pgsql_raise_statement(level={$pgsql_raise_level.st}, format={$CHAR_STRING.text}, expressions={$expr}, options={$pgsql_raise_using_options.st})
    ;

pgsql_raise_using_options
    :   ^(PGSQL_USING options+=pgsql_raise_using_option+)
        -> pgsql_raise_using_options(options={$options})
    ;
    
pgsql_raise_using_option
    :   ^(PGSQL_OPTION pgsql_raise_using_option_name expression)
        -> pgsql_raise_using_option(option_name={$pgsql_raise_using_option_name.st}, expression={$expression.st})
    ;

pgsql_raise_using_option_name
    :   PGSQL_ERRCODE -> pgsql_raise_using_option_name_errcode()
    ;

pgsql_raise_level
    :   PGSQL_NOTICE -> pgsql_raise_level_notice()
        | PGSQL_EXCEPTION -> pgsql_raise_level_exception()
    ;

// $<DML SQL PL/SQL Statements

data_manipulation_language_statements
    :    merge_statement -> { $merge_statement.st }
    |    lock_table_statement -> { $lock_table_statement.st }
    |    select_statement -> { $select_statement.st }
    |    update_statement -> { $update_statement.st }
    |    delete_statement -> { $delete_statement.st }
    |    insert_statement -> { $insert_statement.st }
    ;

select_statement
    :    ^(SELECT_STATEMENT subquery_factoring_clause? subquery order_by_clause? for_update_clause?)
    ->   select_statement(
            subquery_factoring_clause={$subquery_factoring_clause.st},
            subquery={$subquery.st},
            order_by_clause={$order_by_clause.st},
            for_update_clause={$for_update_clause.st})
    ;

// $<Select - Specific Clauses
subquery_factoring_clause
    :    ^(SQL92_RESERVED_WITH RECURSIVE_VK? factoring_elements+=factoring_element+)
    ->   subquery_factoring_clause(is_recursive={$RECURSIVE_VK != null}, factoring_elements={$factoring_elements})
    ;

factoring_element
    :    ^(FACTORING query_name column_names+=column_name* subquery order_by_clause? /*search_clause? cycle_clause?*/)
    ->   factoring_element(
            query_name={$query_name.st}, column_names={$column_names},
            subquery={$subquery.st}, order_by_clause={$order_by_clause.st})
    ;

subquery
    :    ^(SUBQUERY subquery_basic_elements parts+=subquery_operation_part*)
    ->   subquery(subquery_basic_elements={$subquery_basic_elements.st}, subquery_operation_parts={$parts})
    ;

subquery_operation_part
@init { StringTemplate op = null; }
    :    ^(
            (
              SQL92_RESERVED_UNION { op = %subquery_operation_part_union(); }
              |SQL92_RESERVED_INTERSECT { op = %subquery_operation_part_intersect(); }
              |PLSQL_RESERVED_MINUS { op = %subquery_operation_part_minus(); }
            )
            SQL92_RESERVED_ALL? subquery_basic_elements
          )
    ->   subquery_operation_part(operator={op}, is_all={$SQL92_RESERVED_ALL != null}, subquery_basic_elements={$subquery_basic_elements.st})
    ;

subquery_basic_elements
    :    query_block -> { $query_block.st }
    |    subquery
    ->   in_parens(val={$subquery.st})
    ;

query_block
    :    ^(SQL92_RESERVED_SELECT 
            from_clause? 
            (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE|SQL92_RESERVED_ALL)? 
            (    ASTERISK
            |    ^(SELECT_LIST selected+=selected_element+)
            )
            into_clause? where_clause? hierarchical_query_clause? 
            group_by_clause? model_clause?
        )
    ->   query_block(
           is_distinct={$SQL92_RESERVED_DISTINCT != null},
           is_unique={$SQL92_RESERVED_UNIQUE != null},
           is_all={$SQL92_RESERVED_ALL != null},
           is_asterisk={$ASTERISK != null},
           selected_elements={$selected},
           into_clause={$into_clause.st},
           from_clause={$from_clause.st},
           where_clause={$where_clause.st},
           hierarchical_query_clause={$hierarchical_query_clause.st},
           group_by_clause={$group_by_clause.st},
           model_clause={$model_clause.st}
         )
    ;

selected_element
    :    ^(SELECT_ITEM expression alias?)
    ->   selected_element(expression={$expression.st}, alias={$alias.st})
    ;

from_clause
    :    ^(SQL92_RESERVED_FROM refs+=table_ref+)
    ->   from_clause(table_refs={$refs})
    ;

table_ref
    :    ^(TABLE_REF table_ref_aux joins+=join_clause*)
    ->   table_ref(table_ref_aux={$table_ref_aux.st}, join_clauses={$joins})
    ;

table_ref_aux
    :    ^(TABLE_REF_ELEMENT alias? dml_table_expression_clause (ONLY_VK|pivot_clause|unpivot_clause)? flashback_query_clause*)
    ->   table_ref_aux(
           is_only={$ONLY_VK != null}, dml_table_expression_clause={$dml_table_expression_clause.st},
           pivot_clause={$pivot_clause.st}, unpivot_clause={$unpivot_clause.st},
           flashback_query_clause={$flashback_query_clause.st}, alias={$alias.st})
    ;

join_clause
    :    ^(JOIN_DEF
            qpc1=query_partition_clause?
            (CROSS_VK|NATURAL_VK)? (INNER_VK|FULL_VK|LEFT_VK|RIGHT_VK)? table_ref_aux
            qpc2=query_partition_clause?
            (join_on_part|join_using_part)?)
    ->   join_clause(
            qpc1={$qpc1.st}, is_cross={$CROSS_VK != null}, is_natural={$NATURAL_VK != null}, is_inner={$INNER_VK != null},
            is_left={$LEFT_VK != null}, is_right={$RIGHT_VK != null}, is_full={$FULL_VK != null}, table_ref_aux={$table_ref_aux.st},
            qpc2={$qpc2.st}, join_on_part={$join_on_part.st}, join_using_part={$join_using_part.st})
    ;

join_on_part
    :    ^(SQL92_RESERVED_ON expression)
    ->   join_on_part(expression={$expression.st})
    ;

join_using_part
    :    ^(PLSQL_NON_RESERVED_USING column_name+)
    ->   template() "not implemented: join_using_part"
    ;

query_partition_clause
    :    ^(PARTITION_VK query_partition_clause_impl)
    ->   query_partition_clause(impl={$query_partition_clause_impl.st})
    ;
    
query_partition_clause_impl
    :   subquery -> in_parens(val={$subquery.st})
        | expression_list -> { $expression_list.st }
        | ^(QUERY_PARTITION_CLAUSE_SPEC_EXPRESSIONS expressions+=expression+)
          -> query_partition_clause_impl_expressions(expressions={$expressions})
    ;

flashback_query_clause
    :    ^((VERSIONS_VK|SQL92_RESERVED_AS) (SCN_VK|TIMESTAMP_VK)? expression) 
    ->   template() "not implemented: flashback_query_clause"
    ;

pivot_clause
    :    ^(PIVOT_VK XML_VK? pivot_element+ pivot_for_clause pivot_in_clause)
    ->   template() "not implemented: pivot_clause"
    ;

pivot_element
    :    ^(PIVOT_ELEMENT alias? expression)
    ->   template() "not implemented: pivot_element"
    ;

pivot_for_clause
    :    ^(SQL92_RESERVED_FOR column_name+)
    ->   template() "not implemented: pivot_for_clause"
    ;

pivot_in_clause
    :    ^(SQL92_RESERVED_IN 
        (    subquery
        |    ^(ANY_MODE SQL92_RESERVED_ANY+)
        |    ^(ELEMENTS_MODE pivot_in_clause_element+)
        )
        )
    ->   template() "not implemented: pivot_in_clause"
    ;

pivot_in_clause_element
    :    ^(PIVOT_IN_ELEMENT alias? (expression|expression_list))
    ->   template() "not implemented: pivot_in_clause_element"
    ;

unpivot_clause
    :    ^(UNPIVOT_VK ((INCLUDE_VK|EXCLUDE_VK) NULLS_VK?)? column_name+ pivot_for_clause unpivot_in_clause)
    ->   template() "not implemented: unpivot_clause"
    ;

unpivot_in_clause
    :    ^(SQL92_RESERVED_IN unpivot_in_element+)
    ->   template() "not implemented: unpivot_in_clause"
    ;

unpivot_in_element
    :    ^(UNPIVOT_IN_ELEMENT column_name+ ^(PIVOT_ALIAS (expression|expression_list)))
    ->   template() "not implemented: unpivot_in_element"
    ;

hierarchical_query_clause
    :    ^(HIERARCHICAL start_part? ^(SQL92_RESERVED_CONNECT NOCYCLE_VK? expression))
    ->   hierarchical_query_clause(is_nocycle={$NOCYCLE_VK != null}, condition={$expression.st}, start_part={$start_part.st})
    ;

start_part
    :    ^(PLSQL_RESERVED_START expression)
    ->   start_part(condition={$expression.st})
    ;

group_by_clause
    :    ^(SQL92_RESERVED_GROUP elements+=group_by_element+ having_clause?)
    ->   group_by_clause(group_by_elements={$elements}, having_clause={$having_clause.st})
    ;

group_by_element
    :    ^(GROUP_BY_ELEMENT group_by_elements) -> {$group_by_elements.st}
    ;

group_by_elements
    :    ^(GROUPING_VK groupin_set+)
    ->   template() "not implemented: group_by_elements"
    |    grouping_element -> {$grouping_element.st}
    ;

groupin_set
    :    ^(GROUPIN_SET grouping_element)
    ->   template() "not implemented: groupin_set"
    ;

grouping_element
    :    ^(ROLLUP_VK elements+=grouping_element+)
    ->   rollup_grouping_element(elements={$elements})
    |    ^(CUBE_VK elements+=grouping_element+)
    ->   cube_grouping_element(elements={$elements})
    |    expression_list -> {$expression_list.st}
    |    expression -> {$expression.st}
    ;

having_clause
    :    ^(SQL92_RESERVED_HAVING expression)
    ->   having_clause(condition={$expression.st})
    ;

model_clause
    :    ^(PLSQL_NON_RESERVED_MODEL main_model cell_reference_options* return_rows_clause? reference_model*)
    ->   template() "not implemented: model_clause"
    ;

cell_reference_options
    :    ^((IGNORE_VK|KEEP_VK) NAV_VK)
    |    ^(SQL92_RESERVED_UNIQUE (DIMENSION_VK|SINGLE_VK))
    ->   template() "not implemented: cell_reference_options"
    ;

return_rows_clause
    :    ^(RETURN_VK (UPDATED_VK|SQL92_RESERVED_ALL))
    ->   template() "not implemented: return_rows_clause"
    ;

reference_model
    :    ^(REFERENCE_VK reference_model_name subquery model_column_clauses cell_reference_options*)
    ->   template() "not implemented: reference_model"
    ;

main_model
    :    ^(MAIN_MODEL main_model_name? model_column_clauses model_rules_clause cell_reference_options*)
    ->   template() "not implemented: main_model"
    ;

model_column_clauses
    :    ^(MODEL_COLUMN ^(DIMENSION_VK model_column_list) ^(MEASURES_VK model_column_list) model_column_partition_part?)
    ->   template() "not implemented: model_column_clauses"
    ;

model_column_partition_part
    :    ^(PARTITION_VK model_column_list)
    ->   template() "not implemented: model_column_partition_part"
    ;

model_column_list
    :    ^(MODEL_COLUMNS model_column+)
    ->   template() "not implemented: model_column_list"
    ;

model_column
    :    ^(MODEL_COLUMN alias? expression) 
    ->   template() "not implemented: model_column"
    ;

model_rules_clause
    :    ^(MODEL_RULES model_rules_element+ model_rules_part?)
    ->   template() "not implemented: model_rules_clause"
    ;

model_rules_part
    :    ^(RULES_VK 
            (SQL92_RESERVED_UPDATE|UPSERT_VK SQL92_RESERVED_ALL?)? 
            (AUTOMATIC_VK|SEQUENTIAL_VK)? 
            model_iterate_clause?
        )
    ->   template() "not implemented: model_rules_part"
    ;

model_rules_element
    :    ^(MODEL_RULE 
            ^(ASSIGN model_expression expression) 
            (SQL92_RESERVED_UPDATE|UPSERT_VK SQL92_RESERVED_ALL?)? 
            order_by_clause?
        )
    ->   template() "not implemented: model_rules_element"
    ;

model_iterate_clause
    :    ^(ITERATE_VK expression until_part?)
    ->   template() "not implemented: model_iterate_clause"
    ;

until_part
    :    ^(UNTIL_VK expression)
    ->   template() "not implemented: until_part"
    ;

order_by_clause
    :    ^(SQL92_RESERVED_ORDER SIBLINGS_VK? ^(ORDER_BY_ELEMENTS elements+=order_by_elements+))
    ->   order_by_clause(is_siblings={$SIBLINGS_VK != null}, elements={$elements})
    ;

order_by_elements
    :    ^(ORDER_BY_ELEMENT expression (SQL92_RESERVED_ASC|SQL92_RESERVED_DESC)? (NULLS_VK (FIRST_VK|LAST_VK))?)
    ->   order_by_elements(
          expression={$expression.st}, is_asc={$SQL92_RESERVED_ASC != null},
          is_desc={$SQL92_RESERVED_DESC != null}, is_nulls_first={$FIRST_VK != null}, is_nulls_last={$LAST_VK != null})
    ;

for_update_clause
    :    ^(SQL92_RESERVED_FOR for_update_of_part? for_update_options?)
    ->   for_update_clause(for_update_of_part={$for_update_of_part.st}, for_update_options={$for_update_options.st})
    ;

for_update_of_part
    :    ^(SQL92_RESERVED_OF column_names+=column_name+)
    ->   for_update_of_part(column_names={$column_names})
    ;

for_update_options
    :    SKIP_VK -> for_update_options_skip_locked()
    |    PLSQL_RESERVED_NOWAIT -> for_update_options_nowait()
    |    ^(WAIT_VK expression) -> for_update_options_wait(expression={$expression.st})
    ;

// $>

update_statement
    :    ^(SQL92_RESERVED_UPDATE general_table_ref
            ^(SET_VK update_elements+=update_set_elements+)
            where_clause? static_returning_clause? error_logging_clause?
        )
    ->   update_statement(
            general_table_ref={$general_table_ref.st}, update_set_elements={$update_elements},
            where_clause={$where_clause.st}, static_returning_clause={$static_returning_clause.st},
            error_logging_clause={$error_logging_clause.st})
    ;

// $<Update - Specific Clauses

update_set_elements
    :    ^(ASSIGN column_name expression)
    ->   update_set_element_column_expr(column_name={$column_name.st}, expression={$expression.st})
    |    ^(ASSIGN names+=column_name+ subquery)
    ->   update_set_element_columns_subquery(column_names={$names}, subquery={$subquery.st})
    |    ^(VALUE_VK char_set_name? id=commented_id expression)
    ->   update_set_element_column_value(column_name={$id.st}, expression={$expression.st})
    ;

// $>

delete_statement
    :    ^(SQL92_RESERVED_DELETE general_table_ref
            where_clause? static_returning_clause? error_logging_clause?)
    ->   delete_statement(
            general_table_ref={$general_table_ref.st}, where_clause={$where_clause.st},
            static_returning_clause={$static_returning_clause.st}, error_logging_clause={$error_logging_clause.st})
    ;

insert_statement
    :    ^(SQL92_RESERVED_INSERT
            (
              single_table_insert -> { $single_table_insert.st }
              |    multi_table_insert -> { $multi_table_insert.st }
            )
          )
    ;

// $<Insert - Specific Clauses

single_table_insert
    :    ^(SINGLE_TABLE_MODE insert_into_clause (values_clause static_returning_clause?| select_statement) error_logging_clause?)
    ->   single_table_insert(
            insert_into_clause={$insert_into_clause.st}, values_clause={$values_clause.st},
            static_returning_clause={$static_returning_clause.st}, select_statement={$select_statement.st},
            error_logging_clause={$error_logging_clause.st})
    ;

multi_table_insert
    :    ^(MULTI_TABLE_MODE select_statement (conditional_insert_clause|multi_table_element+))
    ->   template() "not implemented: multi_table_insert"
    ;

multi_table_element
    :    ^(TABLE_ELEMENT insert_into_clause values_clause? error_logging_clause?)
    ->   template() "not implemented: multi_table_element"
    ;

conditional_insert_clause
    :    ^(CONDITIONAL_INSERT (SQL92_RESERVED_ALL|FIRST_VK)? conditional_insert_when_part+ conditional_insert_else_part?) 
    ->   template() "not implemented: conditional_insert_clause"
    ;

conditional_insert_when_part
    :    ^(SQL92_RESERVED_WHEN expression multi_table_element+)
    ->   template() "not implemented: conditional_insert_when_part"
    ;

conditional_insert_else_part
    :    ^(SQL92_RESERVED_ELSE multi_table_element+)
    ->   template() "not implemented: conditional_insert_else_part"
    ;

insert_into_clause
    :    ^(SQL92_RESERVED_INTO general_table_ref ^(COLUMNS columns+=column_name*))
    ->   insert_into_clause(general_table_ref={$general_table_ref.st}, columns={$columns})
    ;

values_clause
    :    ^(SQL92_RESERVED_VALUES (r=expression_list|r=expression))
    ->   values_clause(expression_or_expression_list={$r.st})
    ;

// $>
merge_statement
    :    ^(MERGE_VK alias? tableview_name 
            ^(PLSQL_NON_RESERVED_USING selected_tableview expression)
             merge_update_clause? merge_insert_clause? error_logging_clause?)
    ->   merge_statement(
          table_name={$tableview_name.st}, table_alias={$alias.st}, selected_tableview={$selected_tableview.st},
          condition={$expression.st}, merge_update_clause={$merge_update_clause.st},
          merge_insert_clause={$merge_insert_clause.st}, error_logging_clause={$error_logging_clause.st})
    ;

// $<Merge - Specific Clauses

merge_update_clause
    :    ^(MERGE_UPDATE merge_elements+=merge_element+ where_clause? merge_update_delete_part?)
    ->   merge_update_clause(
          merge_elements={$merge_elements}, where_clause={$where_clause.st},
          merge_update_delete_part={$merge_update_delete_part.st})
    ;

merge_element
    :    ^(ASSIGN column_name expression)
    ->   merge_element(column_name={$column_name.st}, expression={$expression.st})
    ;

merge_update_delete_part
    :    ^(SQL92_RESERVED_DELETE where_clause)
    ->   merge_update_delete_part(where_clause={$where_clause.st})
    ;

merge_insert_clause
    :    ^(MERGE_INSERT ^(COLUMNS columns+=column_name*) expression_list where_clause?) 
    ->   merge_insert_clause(columns={$columns}, expression_list={$expression_list.st}, where_clause={$where_clause.st})
    ;

selected_tableview
    :    ^(SELECTED_TABLEVIEW
            alias?
            (
              tableview_name
                -> selected_tableview_table(name={$tableview_name.st}, alias={$alias.st})
              | select_statement
                -> selected_tableview_select_statement(select_statement={$select_statement.st}, alias={$alias.st})
            )
          )
    ;

// $>

lock_table_statement
    :    ^(PLSQL_RESERVED_LOCK lock_table_element+ lock_mode wait_nowait_part?)
    ->   template() "not implemented: lock_table_statement"
    ;

wait_nowait_part
    :    ^(WAIT_VK expression)
    ->   template() "not implemented: wait_nowait_part"
    |    PLSQL_RESERVED_NOWAIT
    ->   template() "not implemented: wait_nowait_part"
    ;

// $<Lock - Specific Clauses

lock_table_element
    :    ^(LOCK_TABLE_ELEMENT tableview_name partition_extension_clause?)
    ->   template() "not implemented: lock_table_element"
    ;

lock_mode
    :    ROW_VK PLSQL_RESERVED_SHARE
    ->   template() "not implemented: lock_mode"
    |    ROW_VK PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: lock_mode"
    |    PLSQL_RESERVED_SHARE SQL92_RESERVED_UPDATE?
    ->   template() "not implemented: lock_mode"
    |    PLSQL_RESERVED_SHARE ROW_VK PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: lock_mode"
    |    PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: lock_mode"
    ;
// $>

// $<Common DDL Clauses

general_table_ref
    :    ^(TABLE_REF alias? dml_table_expression_clause ONLY_VK?)
    ->   general_table_ref(
            is_only={$ONLY_VK != null},
            dml_table_expression_clause={$dml_table_expression_clause.st},
            table_alias={$alias.st})
    ;

static_returning_clause
    :    ^(STATIC_RETURNING expressions+=expression+ into_clause)
    ->   static_returning_clause(expressions={$expressions}, into_clause={$into_clause.st})
    ;

error_logging_clause
    :    ^(LOG_VK error_logging_into_part? expression? error_logging_reject_part?)
    ->   template() "not implemented: error_logging_clause"
    ;

error_logging_into_part
    :    ^(SQL92_RESERVED_INTO tableview_name)
    ->   template() "not implemented: error_logging_into_part"
    ;

error_logging_reject_part
    :    ^(REJECT_VK (UNLIMITED_VK|expression))
    ->   template() "not implemented: error_logging_reject_part"
    ;

dml_table_expression_clause
    :    ^(TABLE_EXPRESSION 
        (    ^(COLLECTION_MODE table_collection_expression) -> { $table_collection_expression.st }
        |    ^(SELECT_MODE select_statement subquery_restriction_clause?)
              ->   dml_table_expression_clause_select(
                    select_statement={$select_statement.st},
                    subquery_restriction_clause={$subquery_restriction_clause.st})
        |    ^(DIRECT_MODE tableview_name sample_clause?)
              -> dml_table_expression_clause_direct(table_or_view_name={$tableview_name.st}, sample_clause={$sample_clause.st})
        |    general_element -> { $general_element.st }
        |    standard_function -> { $standard_function.st }
        )
        )
        |    table_ref -> in_parens(val={$table_ref.st})
    ;

table_collection_expression
    :   expression
    ->  table_collection_expression(expression_or_subquery={$expression.st}, is_outer_join={false})
    |   subquery
    ->  table_collection_expression(expression_or_subquery={$subquery.st}, is_outer_join={false})
    |   ^(OUTER_JOIN_SIGN expression)
    ->  table_collection_expression(expression_or_subquery={$expression.st}, is_outer_join={true})
    |   ^(OUTER_JOIN_SIGN subquery)
    ->  table_collection_expression(expression_or_subquery={$subquery.st}, is_outer_join={true})
    ;

subquery_restriction_clause
    :    ^(SQL92_RESERVED_WITH (READ_VK|SQL92_RESERVED_CHECK constraint_name?))
    ->   template() "not implemented: subquery_restriction_clause"
    ;

sample_clause
    :    ^(SAMPLE_VK BLOCK_VK? expression seed_part?) 
    ->   template() "not implemented: sample_clause"
    ;

seed_part
    :    ^(SEED_VK expression)
    ->   template() "not implemented: seed_part"
    ;

// $>

// $>

// $<Cursor Manipulation SQL PL/SQL Statements

cursor_manipulation_statements
    :    close_statement -> { $close_statement.st }
    |    open_statement -> { $open_statement.st }
    |    fetch_statement -> { $fetch_statement.st }
    |    open_for_statement -> { $open_for_statement.st }
    ;

close_statement
    :     ^(CLOSE_VK cursor_name) 
    ->   close_statement(cursor_name={$cursor_name.st})
    ;

open_statement
    :    ^(OPEN_VK cursor_name expression_list?)
    ->   open_statement(cursor_name={$cursor_name.st}, expression_list={$expression_list.st})
    ;

fetch_statement
    :    ^(SQL92_RESERVED_FETCH cursor_name into_clause)
    ->   fetch_statement(cursor_name={$cursor_name.st}, into_clause={$into_clause.st})
    ;

open_for_statement
    :    ^(OPEN_FOR variable_name (e_or_s=expression|e_or_s=select_statement) using_clause?)
    ->   open_for_statement(cursor_name={$variable_name.st}, expression_or_select_statement={$e_or_s.st}, using_clause={$using_clause.st})
    ;

// $>

// $<Transaction Control SQL PL/SQL Statements

transaction_control_statements
    :    set_transaction_command -> { $set_transaction_command.st }
    |    set_constraint_command -> { $set_constraint_command.st }
    |    commit_statement -> { $commit_statement.st }
    |    rollback_statement -> { $rollback_statement.st }
    |    savepoint_statement -> { $savepoint_statement.st }
    ;

set_transaction_command
    :    ^(SET_TRANSACTION CHAR_STRING?
            (    ^(READ_VK (ONLY_VK|WRITE_VK))
            |    ^(ISOLATION_VK (SERIALIZABLE_VK|COMMITTED_VK))
            |    ^(ROLLBACK_VK rollback_segment_name)
            )?
        )
    ->   template() "not implemented: set_transaction_command"
    ;

set_constraint_command
    :    ^(SET_CONSTRAINT (SQL92_RESERVED_ALL|constraint_name+) (IMMEDIATE_VK|DEFERRED_VK))
    ->   template() "not implemented: set_constraint_command"
    ;

commit_statement
    :     ^(COMMIT_VK WORK_VK?
            (
              additional=commit_comment
              | additional=commit_force    
                 
            )?
          )
    -> commit_statement(is_work={$WORK_VK != null}, additional={$additional.st})
    ;
    
commit_comment
    :   ^(COMMENT_VK comment_expr=expression) write_clause?
    ->  commit_comment(comment_expr={$expression.st}, write_clause={$write_clause.st})
    ;
    
commit_force
    :   ^(FORCE_VK
          (
            CORRUPT_XID_VK expr1=expression -> commit_statement_force_2(expression={$expr1.st})
            | CORRUPT_XID_ALL_VK -> commit_statement_force_3()
            | expr1=expression expr2=expression? -> commit_statement_force_1(expr1={$expr1.st}, expr2={$expr2.st})
          )
        )
    ;

write_clause
    :    ^(WRITE_VK (WAIT_VK|PLSQL_RESERVED_NOWAIT)? (IMMEDIATE_VK|BATCH_VK)?)
    ->   commit_statement_write_clause(
          is_wait={$WAIT_VK != null}, is_nowait={$PLSQL_RESERVED_NOWAIT != null},
          is_immediate={$IMMEDIATE_VK != null}, is_batch={$BATCH_VK != null})
    ;

rollback_statement
    :     ^(ROLLBACK_VK WORK_VK? 
              (    ^(SQL92_RESERVED_TO savepoint_name)
              |    ^(FORCE_VK CHAR_STRING)
              )?
        )
        -> rollback_statement(
              is_work={$WORK_VK != null}, is_to_savepoint={$SQL92_RESERVED_TO != null}, savepoint_name={$savepoint_name.st},
              is_force={$FORCE_VK !=null}, force_string={$CHAR_STRING.text})
    ;

savepoint_statement
    :    ^(SAVEPOINT_VK savepoint_name) 
    ->   savepoint_statement(name={$savepoint_name.st})
    ;
    
pipe_row_statement
    :    ^(PIPE_ROW expression)
    ->   pipe_row_statement(expression={$expression.st})
    ;

// $>

// $<Expression & Condition

expression_list
    :    ^(EXPR_LIST expressions+=expression*)
    ->   expression_list(expressions={$expressions})
    ;

expression
    :    ^(LOGIC_EXPR expression_element) -> {$expression_element.st}
    |    ^(EXPR expression_element)  -> {$expression_element.st}
    ;

expression_element returns [int priority]
@init {
  String op = null;
  boolean omit1 = false;
  boolean omit2 = false;
  boolean omit3 = false;
  $priority = -1;
}
    :    ^(
          (
            t=SQL92_RESERVED_OR { op = "or"; }
            | t=SQL92_RESERVED_AND { op = "and"; }
            | t=EQUALS_OP { op = "="; }
            | t=NOT_EQUAL_OP { op = "<>"; }
            | t=LESS_THAN_OP { op = "<"; }
            | t=GREATER_THAN_OP { op = ">"; }
            | t=LESS_THAN_OR_EQUALS_OP { op = "<="; }
            | t=GREATER_THAN_OR_EQUALS_OP { op = ">="; }
            | t=CONCATENATION_OP { op = "||"; }
            | t=PLUS_SIGN { op = "+"; }
            | t=MINUS_SIGN { op = "-"; }
            | t=ASTERISK { op = "*"; }
            | t=SOLIDUS { op = "/"; }
            | t=PERCENT { op = "\%"; }
            | t=MOD_VK { op = "mod"; }
            | t=DIV_VK { op = "div"; }
          )
          arg1=expression_element arg2=expression_element
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $arg1.priority);
            omit2 = canOmitParensNonAssoc($priority, $arg2.priority);
          }
         )
    ->   expression_element_generic_binop(op={op}, arg1={$arg1.st}, arg2={$arg2.st}, omit_parens_1={omit1}, omit_parens_2={omit2})
    |    ^(t=SQL92_RESERVED_NOT expr=expression_element)
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $expr.priority);
          }
    ->   expression_element_not(expr={$expr.st}, omit_parens={omit1})
    |    ^(
            (
              t=IS_NOT_NULL { op = "is not null"; }
              | t=IS_NULL { op = "is null"; }
              | t=IS_NOT_NAN { op = "is not nan"; }
              | t=IS_NAN { op = "is nan"; }
              | t=IS_NOT_PRESENT { op = "is not present"; }
              | t=IS_PRESENT { op = "is present"; }
              | t=IS_NOT_INFINITE { op = "is not infinite"; }
              | t=IS_INFINITE { op = "is infinite"; }
              | t=IS_NOT_A_SET { op = "is not a set"; }
              | t=IS_A_SET { op = "is a set"; }
              | t=IS_NOT_EMPTY { op = "is not empty"; }
              | t=IS_EMPTY { op = "is empty"; }
            )
            arg=expression_element
          )
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $arg.priority);
          }
    ->   expression_element_generic_suffix_unary_op(op={op}, arg={$arg.st}, omit_parens={omit1})
    |    ^(t=IS_NOT_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: expression_element"
    |    ^(t=IS_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: expression_element"

    |    ^((t=MEMBER_VK|t=SUBMULTISET_VK) expression_element expression_element)
    ->   template() "not implemented: expression_element"

    |    ^(t=NOT_IN expr=expression_element in_elements)
    ->   expression_element_not_in(expr={$expr.st}, in_elements={$in_elements.st})
    |    ^(t=SQL92_RESERVED_IN expr=expression_element in_elements)
    ->   expression_element_in(expr={$expr.st}, in_elements={$in_elements.st})
    |    ^(t=NOT_BETWEEN expr=expression_element expr_low=expression_element expr_high=expression_element)
    ->   expression_element_between(expr={$expr.st}, is_not={true}, expr_low={$expr_low.st}, expr_high={$expr_high.st})
    |    ^(t=SQL92_RESERVED_BETWEEN expr=expression_element expr_low=expression_element expr_high=expression_element)
    ->   expression_element_between(expr={$expr.st}, is_not={false}, expr_low={$expr_low.st}, expr_high={$expr_high.st})
    |    ^(
            (
              t=SQL92_RESERVED_LIKE { op = "like"; }
              | t=LIKEC_VK { op = "likec"; }
              | t=LIKE2_VK { op = "like2"; }
              | t=LIKE4_VK { op = "like4"; }
              | t=NOT_LIKE { op = "not like"; }
            )
            text=expression_element
            pattern=expression_element
            escape=expression_element?
          )
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $text.priority);
            omit2 = canOmitParens($priority, $pattern.priority);
            omit3 = canOmitParens($priority, $escape.priority);
          }
    ->   expression_element_like(
          text={$text.st}, like_op={op}, pattern={$pattern.st}, escape_char={$escape.st},
          omit_parens_text={omit1}, omit_parens_pattern={omit2}, omit_parens_escape={omit3})
    
    |    ^(t=PIPE_VK expression_element expression_element)
    ->   template() "not implemented: expression_element"

    |    ^(t=UNARY_PLUS arg=expression_element)
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $arg.priority);
          }
    ->   expression_element_generic_prefix_unary_op(op={"+"}, is_spaced={false}, arg={$arg.st}, omit_parens={omit1})
    |    ^(t=UNARY_MINUS arg=expression_element)
          {
            $priority = getPriority($t.type);
            omit1 = canOmitParens($priority, $arg.priority);
          }
    ->   expression_element_generic_prefix_unary_op(op={"-"}, is_spaced={false}, arg={$arg.st}, omit_parens={omit1})
    |    ^(t=SQL92_RESERVED_PRIOR arg=expression_element)
    ->   expression_element_prior(expr={$arg.st})
    |    ^(t=NEW_VK expression)
    ->   template() "not implemented: expression_element"
    |    ^(t=SQL92_RESERVED_DISTINCT expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(t=STANDARD_FUNCTION standard_function)
          {
            $priority = getPriority($t.type);
          }
    ->   { $standard_function.st }
    |    ^((t=SOME_VK|t=SQL92_RESERVED_EXISTS|t=SQL92_RESERVED_ALL|t=SQL92_RESERVED_ANY) (s_or_e=subquery|s_or_e=expression))
          {
            $priority = getPriority($t.type);
          }
    ->   expression_element_quantified_expr(
          subquery_or_expression={$s_or_e.st}, is_some={$t.type == SOME_VK}, is_any={$t.type == SQL92_RESERVED_ANY},
          is_all={$t.type == SQL92_RESERVED_ALL}, is_exists={$t.type == SQL92_RESERVED_EXISTS})
    |    ^(t=VECTOR_EXPR expression_element+)
    ->   template() "not implemented: expression_element"

    |    ^(t=DATETIME_OP expression_element datetime_element)
    ->   template() "not implemented: expression_element"
    |    model_expression
    ->   template() "not implemented: expression_element"
    |    ^(t=KEEP_VK expression_element DENSE_RANK_VK (FIRST_VK|LAST_VK) order_by_clause over_clause?)
    ->   template() "not implemented: expression_element"

    |    ^(t=DOT_ASTERISK tableview_name)
    ->   expression_element_dot_star(tableview_name={$tableview_name.st})
    |    ^(
            (
              t=PERCENT_FOUND_VK { op = "\%FOUND"; }
              |t=PERCENT_NOTFOUND_VK { op = "\%NOTFOUND"; }
              |t=PERCENT_ROWCOUNT_VK { op = "\%ROWCOUNT"; }
              |t=PERCENT_ISOPEN_VK { op = "\%ISOPEN"; }
            )
            cursor_name
          )
          {
            $priority = getPriority($t.type);
          }
    ->   expression_element_cursor_op(op={op}, cursor_name={$cursor_name.st})
    |    ^(t=OUTER_JOIN_SIGN expr=expression_element)
          {
            $priority = getPriority($t.type);
          }
    ->   expression_element_outer_join_sign(expr={$expr.st})

    |    case_statement
          {
            $priority = getPriority(SEARCHED_CASE);
          }
          -> { $case_statement.st }
    |    constant
          {
            $priority = getPriority(UNSIGNED_INTEGER);
          }
          -> { $constant.st }
    |    general_element
          {
            $priority = getPriority(CASCATED_ELEMENT);
          }
          -> { $general_element.st }
    |    hosted_variable_name
          {
            $priority = getPriority(HOSTED_VARIABLE_NAME);
          }
          -> { $hosted_variable_name.st }
    |    subquery
          {
            $priority = getPriority(UNSIGNED_INTEGER); // a hack since subquery is already in parens
          }
          -> in_parens(val={$subquery.st})
    ;

in_elements
    :    subquery -> in_parens(val={$subquery.st})
    |    expression -> { $expression.st }
    |    expression_list -> { $expression_list.st }
    ;

datetime_element
    :    ^(AT_VK expression_element (LOCAL_VK|TIME_VK expression))
    ->   template() "not implemented: datetime_element"
    |    ^(DAY_VK SECOND_VK expression*)
    ->   template() "not implemented: datetime_element"
    |    ^(YEAR_VK MONTH_VK expression)
    ->   template() "not implemented: datetime_element"
    ;

model_expression
    :    ^(MODEL_EXPRESSION expression_element model_expression_element+)
    ->   template() "not implemented: model_expression"
    ;

model_expression_element
    :    SQL92_RESERVED_ANY
    ->   template() "not implemented: model_expression_element"
    |    expression
    ->   template() "not implemented: model_expression_element"
    |    ^(FOR_SINGLE_COLUMN column_name for_single_column_element for_like_part?)
    ->   template() "not implemented: model_expression_element"
    |    ^(FOR_MULTI_COLUMN column_name+ ^(SQL92_RESERVED_IN (subquery|expression_list+)))
    ->   template() "not implemented: model_expression_element"
    ;

for_single_column_element
    :    ^(SQL92_RESERVED_IN expression_list)
    ->   template() "not implemented: for_single_column_element"
    |    ^(SQL92_RESERVED_FROM expression) 
    ->   template() "not implemented: for_single_column_element"
    |    ^(SQL92_RESERVED_TO expression) 
    ->   template() "not implemented: for_single_column_element"
    |    ^((INCREMENT_VK|DECREMENT_VK) expression) 
    ->   template() "not implemented: for_single_column_element"
    ;

for_like_part
    :    ^(SQL92_RESERVED_LIKE expression)
    ->   template() "not implemented: for_like_part"
    ;

case_statement
    :    ^(SIMPLE_CASE expression parts+=case_when_part+ case_else_part?)  
    ->   case_statement_simple(expression={$expression.st}, case_when_parts={$parts}, case_else_part={$case_else_part.st})
    |    ^(SEARCHED_CASE parts+=case_when_part+ case_else_part?) 
    ->   case_statement_searched(case_when_parts={$parts}, case_else_part={$case_else_part.st})
    ;

// $<CASE - Specific Clauses

case_when_part
    :    ^(SQL92_RESERVED_WHEN condition=expression
            (
              seq_of_statements -> case_when_part_statements(condition={$condition.st}, seq_of_statements={$seq_of_statements.st})
              | then_expr=expression -> case_when_part_expression(condition={$condition.st}, expression={$then_expr.st})
            )
          )
    ;

case_else_part
    :    ^(SQL92_RESERVED_ELSE
            (
              seq_of_statements -> case_else_part_statements(seq_of_statements={$seq_of_statements.st})
              | expression -> case_else_part_expression(expression={$expression.st})
            )
          )
    ;
// $>

standard_function
@init { StringTemplate trimKind = null; }
    :    ^(FUNCTION_ENABLING_OVER function_argument over_clause?)
    ->   standard_function_enabling_over(
          function_name={$FUNCTION_ENABLING_OVER.text}, function_arguments={$function_argument.st}, over_clause={$over_clause.st})
    |    ^(FUNCTION_ENABLING_USING function_argument using_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(COUNT_VK (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE|SQL92_RESERVED_ALL)? ( ASTERISK | expression ) over_clause?)
    ->   standard_function_count(
            is_distinct={$SQL92_RESERVED_DISTINCT != null}, is_unique={$SQL92_RESERVED_UNIQUE != null},
            is_all={$SQL92_RESERVED_ALL != null}, is_asterisk={$ASTERISK != null}, expression={$expression.st},
            over_clause={$over_clause.st})
    |    ^(XMLCAST_VK expression type_spec)
    ->   standard_function_xmlcast(expression={$expression.st}, type_spec={$type_spec.st})
    |    ^(CAST_VK (s_or_e=subquery|s_or_e=expression) type_spec)
    ->   standard_function_cast(subquery_or_expression={$s_or_e.st}, type_spec={$type_spec.st})
    |    ^(CHR_VK expression NCHAR_CS_VK)
    ->   template() "not implemented: standard_function"
    |    ^(COLLECT_VK (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE)? column_name collect_order_by_part?)
    ->   template() "not implemented: standard_function"
    |    ^(FUNCTION_ENABLING_WITHIN_OR_OVER
            function_argument
            (
              within_clause
              -> standard_function_enabling_within(
                  function_name={$FUNCTION_ENABLING_WITHIN_OR_OVER.text}, function_arguments={$function_argument.st},
                  within_clause={$within_clause.st})
              | over_clause
              -> standard_function_enabling_over(
                  function_name={$FUNCTION_ENABLING_WITHIN_OR_OVER.text}, function_arguments={$function_argument.st},
                  over_clause={$over_clause.st})
            ) 
          )
    |    ^(DECOMPOSE_VK expression (CANONICAL_VK|COMPATIBILITY_VK)?) 
    ->   template() "not implemented: standard_function"
    |    ^(EXTRACT_VK extract_part expression)
    ->   standard_function_extract(part={$extract_part.st}, expression={$expression.st})
    |    ^((FIRST_VALUE_VK|LAST_VALUE_VK) expression NULLS_VK? over_clause) 
    ->   template() "not implemented: standard_function"
    |    ^(PREDICTION_FUNCTION expression+ cost_matrix_clause? using_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(TRANSLATE_VK /*(CHAR_CS_VK|NCHAR_CS_VK)?*/ expr=expression expr_from=expression expr_to=expression)
    ->   standard_function_translate(expr={$expr.st}, expr_from={$expr_from.st}, expr_to={$expr_to.st})
    |    ^(TREAT_VK expression REF_VK? type_spec)
    ->   template() "not implemented: standard_function"
    |    ^(TRIM_VK
            text_expr=expression_element
            (
              trim_char_expr=expression_element
              (
                LEADING_VK { trimKind = %trim_kind_leading(); }
                | TRAILING_VK { trimKind = %trim_kind_trailing(); }
                | BOTH_VK { trimKind = %trim_kind_both(); }
              )?
            )?
          ) 
    ->   standard_function_trim(text_expr={$text_expr.st}, trim_char_expr={$trim_char_expr.st}, trim_kind={trimKind})

    |    ^(XMLAGG_VK expression order_by_clause?)
    ->   xmlagg(expression={$expression.st}, order_by_clause={$order_by_clause.st})
    |    ^((XMLCOLATTVAL_VK|XMLFOREST_VK) xml_multiuse_expression_element+)
    ->   template() "not implemented: standard_function"
    |    ^(XMLEXISTS_VK expression xml_passing_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLPARSE_VK (DOCUMENT_VK|CONTENT_VK) expression WELLFORMED_VK?)
    ->   xml_parse(
          is_document={$DOCUMENT_VK != null}, is_content={$CONTENT_VK != null},
          expression={$expression.st}, is_wellformed={$WELLFORMED_VK != null})
    |    ^(XMLQUERY_VK expression xml_passing_clause? SQL92_RESERVED_NULL?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLROOT_VK expression xml_param_version_part xmlroot_param_standalone_part?)
    ->   xmlroot(
          expression={$expression.st}, xml_param_version_part={$xml_param_version_part.st},
          xmlroot_param_standalone_part={$xmlroot_param_standalone_part.st})
    |    ^(XMLTABLE_VK xml_namespaces_clause? expr=expression xml_passing_clause? xml_table_columns+=xml_table_column*)
    ->   xmltable(
          xml_namespaces_clause={$xml_namespaces_clause.st}, xquery_expression={$expr.st},
          xml_passing_clause={$xml_passing_clause.st}, xml_table_columns={$xml_table_columns})
    |    ^(XMLELEMENT_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (NAME_VK|EVALNAME_VK)? expression
            xml_attributes_clause? value_exprs+=xmlelement_value_expr*
        )
    ->   xmlelement(
          is_entity_escaping={$ENTITYESCAPING_VK != null}, is_no_entity_escaping={$NOENTITYESCAPING_VK != null},
          is_name={$NAME_VK != null}, is_evalname={$EVALNAME_VK != null}, expression={$expression.st},
          xml_attributes_clause={$xml_attributes_clause.st}, value_expressions={$value_exprs})
    |    ^(XMLPI_VK
                (    NAME_VK char_set_name? id=commented_id
                |    EVALNAME_VK expression
                )
                expression?
        )
    ->   template() "not implemented: standard_function"
    |    ^(XMLSERIALIZE_VK
                (DOCUMENT_VK|CONTENT_VK)
                expression type_spec?
                xmlserialize_param_enconding_part?
                xml_param_version_part?
                xmlserialize_param_ident_part?
                ((HIDE_VK|SHOW_VK) DEFAULTS_VK)?
        )
    ->   template() "not implemented: standard_function"
    ;
    
extract_part
    :
        YEAR_VK -> string_literal(val={"year"})
        | MONTH_VK -> string_literal(val={"month"})
        | DAY_VK -> string_literal(val={"day"})
        | HOUR_VK -> string_literal(val={"hour"})
        | MINUTE_VK -> string_literal(val={"minute"})
        | SECOND_VK -> string_literal(val={"second"})
        | TIMEZONE_HOUR_VK -> string_literal(val={"timezone_hour"})
        | TIMEZONE_MINUTE_VK -> string_literal(val={"timezone_minute"})
        | TIMEZONE_ABBR_VK -> string_literal(val={"timezone_abbr"})
        | TIMEZONE_REGION_VK -> string_literal(val={"timezone_region"})
    ;
    
xmlelement_value_expr
    :   expression alias?
    ->  xmlelement_value_expr(expression={$expression.st}, alias={$alias.st})
    ;

over_clause
    :    ^(OVER_VK query_partition_clause? (order_by_clause windowing_clause?)?)
    ->   over_clause(
          query_partition_clause={$query_partition_clause.st},
          order_by_clause={$order_by_clause.st}, windowing_clause={$windowing_clause.st})
    ;

windowing_clause
    :    ^((ROWS_VK|RANGE_VK)
            (    ^(SQL92_RESERVED_BETWEEN e1=windowing_elements e2=windowing_elements)
            |    e1=windowing_elements
            )
        )
    ->   windowing_clause(
          is_rows={$ROWS_VK != null}, is_range={$RANGE_VK != null},
          is_between={$SQL92_RESERVED_BETWEEN != null}, windowing_element_1={$e1.st}, windowing_element_2={$e2.st})
    ;

windowing_elements
    :    ^(UNBOUNDED_VK PRECEDING_VK) -> windowing_elements_unbounded_preceding()
    |    ^(CURRENT_VK ROW_VK) -> windowing_elements_current_row()
    |    ^(PRECEDING_VK expr=expression) -> windowing_elements_rows_preceding(expression={$expr.st})
    |    ^(FOLLOWING_VK expr=expression) -> windowing_elements_rows_following(expression={$expr.st})
    ;

using_clause
    :    ^(PLSQL_NON_RESERVED_USING using_elements+=using_element+)
    ->   using_clause(using_elements={$using_elements})
    ;

using_element
    :    ^(ELEMENT SQL92_RESERVED_IN? OUT_VK? expression alias?)
    ->   using_element_element(
          is_in={$SQL92_RESERVED_IN != null}, is_out={$OUT_VK != null},
          expression={$expression.st}, alias={$alias.st})
    |    ASTERISK
    ->   using_element_asterisk()
    ;

collect_order_by_part
    :    ^(SQL92_RESERVED_ORDER expression)
    ->   template() "not implemented: collect_order_by_part"
    ;

within_clause
    :    ^(WITHIN_VK order_by_clause)
    ->   within_clause(order_by_clause={$order_by_clause.st})
    ;

cost_matrix_clause
    :    ^(COST_VK
            (    PLSQL_NON_RESERVED_MODEL AUTO_VK?
            |    cost_class_name+ expression_list
            )
        )
    ->   template() "not implemented: cost_matrix_clause"
    ;

xml_passing_clause
    :    ^(PASSING_VK VALUE_VK? exprs+=xml_passing_clause_expr)
    ->   xml_passing_clause(is_by_value={$VALUE_VK != null}, xml_passing_clause_expressions={$exprs})
    ;
    
xml_passing_clause_expr
    :   expression alias?
    ->  xml_passing_clause_expr(expression={$expression.st}, alias={$alias.st})
    ;

xml_attributes_clause
    :    ^(XMLATTRIBUTES_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (SCHEMACHECK_VK|NOSCHEMACHECK_VK)?
            xml_multiuse_expression_elements+=xml_multiuse_expression_element+
        )
    ->   xml_attributes_clause(
          is_entity_escaping={$ENTITYESCAPING_VK != null}, is_no_entity_escaping={$NOENTITYESCAPING_VK != null},
          is_schema_check={$SCHEMACHECK_VK != null}, is_no_schema_check={$NOSCHEMACHECK_VK != null},
          xml_multiuse_expression_elements={$xml_multiuse_expression_elements})
    ;

xml_namespaces_clause
    :    ^(XMLNAMESPACES_VK
            (expression alias?)* xml_general_default_part?
        )
    ->   template() "not implemented: xml_namespaces_clause"
    ;

xml_table_column
    :    ^(XML_COLUMN xml_column_name
            (
              ORDINALITY_VK -> xml_table_column_ordinality(name={$xml_column_name.st})
              | type_spec expression? xml_general_default_part?
              -> xml_table_column_typespec(
                  name={$xml_column_name.st}, type_spec={$type_spec.st},
                  path_expr={$expression.st}, xml_general_default_part={$xml_general_default_part.st})
            )
          )
    ;

xml_general_default_part
    :    ^(SQL92_RESERVED_DEFAULT expression)
    ->   xml_general_default_part(expression={$expression.st})
    ;

xml_multiuse_expression_element
    :    ^(XML_ELEMENT expression xml_alias?)
    ->   xml_multiuse_expression_element(expression={$expression.st}, xml_alias={$xml_alias.st})
    ;

xml_alias
    :    ^(XML_ALIAS id=commented_id) -> xml_alias_id(id={$id.st})
    |    ^(XML_ALIAS ^(EVALNAME_VK expression))
    ->   xml_alias_evalname(expression={$expression.st})
    ;

xml_param_version_part
    :    ^(VERSION_VK
            (
              NO_VK VALUE_VK -> xml_param_version_part_novalue()
              | expression -> {$expression.st}
            )
          )
    ;

xmlroot_param_standalone_part
    :    ^(STANDALONE_VK
            (
              YES_VK -> xmlroot_param_standalone_part_yes()
              | NO_VK
                (
                  VALUE_VK -> xmlroot_param_standalone_part_no_value()
                  |  -> xmlroot_param_standalone_part_no()
                )
            )
          )
    ;

xmlserialize_param_enconding_part
    :    ^(ENCODING_VK expression)
    ->   template() "not implemented: xmlserialize_param_enconding_part"
    ;

xmlserialize_param_ident_part
    :    NO_VK INDENT_VK
    ->   template() "not implemented: xmlserialize_param_ident_part"
    |    ^(INDENT_VK expression?)
    ->   template() "not implemented: xmlserialize_param_ident_part"
    ;

// $>

// $<Common SQL PL/SQL Clauses/Parts

partition_extension_clause
    :    ^((SUBPARTITION_VK|PARTITION_VK) expression_list)
    ->   template() "not implemented: partition_extension_clause"
    ;

alias
    :    ^(COLUMN_ALIAS char_set_name? id=commented_id)
    ->   column_alias(name={$id.st})
    |    ^(TABLE_ALIAS char_set_name? id=commented_id)
    ->   table_alias(name={$id.st})
    ;

where_clause
    :    ^(SQL92_RESERVED_WHERE expression)
    ->   where_clause(expression={$expression.st})
    ;

into_clause
    :    ^(SQL92_RESERVED_INTO PGSQL_STRICT? elements+=general_element+) 
    ->   into_clause(is_bulk_collect={false}, is_strict={$PGSQL_STRICT != null}, general_elements={$elements})
    |    ^(BULK_VK elements+=general_element+) 
    ->   into_clause(is_bulk_collect={true}, is_strict={false}, general_elements={$elements})
    ;

// $>

// $<Common PL/SQL Named Elements

xml_column_name
    :    ^(XML_COLUMN_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

cost_class_name
    :    ^(COST_CLASS_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

attribute_name
    :    ^(ATTRIBUTE_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

savepoint_name
    :    ^(SAVEPOINT_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

rollback_segment_name
    :    ^(ROLLBACK_SEGMENT_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;


table_var_name
    :    ^(TABLE_VAR_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

schema_name
    :    ^(SCHEMA_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

routine_name
    :    ^(ROUTINE_NAME char_set_name? commented_id+ link_name?)
    ->   template() "not implemented: routine_name"
    ;

package_name
    :    ^(PACKAGE_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

implementation_type_name
    :    ^(IMPLEMENTATION_TYPE_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

parameter_name
    :    ^(PARAMETER_NAME char_set_name? id=commented_id)
    ->   string_literal(val={$id.st})
    ;

reference_model_name
    :    ^(REFERENCE_MODEL_NAME char_set_name? id=commented_id)
    ->   template() "not implemented: reference_model_name"
    ;

main_model_name
    :    ^(MAIN_MODEL_NAME char_set_name? id=commented_id)
    ->   template() "not implemented: main_model_name"
    ;

query_name
    :    ^(QUERY_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

constraint_name
    :    ^(CONSTRAINT_NAME char_set_name? commented_id+ link_name?)
    ->   template() "not implemented: constraint_name"
    ;

label_name
    :    ^(LABEL_NAME id=commented_id) -> string_literal(val={$id.st})
    ;

type_name
    :    ^(TYPE_NAME ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

sequence_name
    :    ^(SEQUENCE_NAME commented_id+)
    ->   template() "not implemented: sequence_name"
    ;

exception_name
    :    ^(EXCEPTION_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

function_name
    :    ^(FUNCTION_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

procedure_name
    :    ^(PROCEDURE_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

trigger_name
    :    ^(TRIGGER_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;
    
hosted_variable_name
    :    ^(HOSTED_VARIABLE_NAME
            (
              BINDVAR -> string_literal(val={$BINDVAR.text})
              |UNSIGNED_INTEGER -> string_literal(val={ ":" + $UNSIGNED_INTEGER.text})
            )
          )
    ;

variable_name
    :    ^(VARIABLE_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

index_name
    :    ^(INDEX_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

cursor_name
    :    ^(CURSOR_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

record_name
    :    ^(RECORD_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

collection_name
    :    ^(COLLECTION_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

link_name
    :    ^(LINK_NAME char_set_name? id=commented_id) -> string_literal(val={$id.st})
    ;

column_name
    :    ^(COLUMN_NAME char_set_name? ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

tableview_name
    :    ^(TABLEVIEW_NAME char_set_name? ids+=commented_id+ link_name? partition_extension_clause?)
    ->   tableview_name(ids={$ids}, link_name={$link_name.st}, partition_extension_clause={$partition_extension_clause.st})
    ;

view_name
    :    ^(VIEW_NAME ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

char_set_name
    :    ^(CHAR_SET_NAME ids+=commented_id+)
    ->   dotted_name(ids={$ids})
    ;

// $>

// $<Common PL/SQL Specs

function_argument
    :    ^(ARGUMENTS args+=argument*)
    ->   function_arguments(arguments={$args})
    ;

argument
    :    ^(ARGUMENT expression parameter_name?)
    ->   function_argument(valueExpr={$expression.st}, name={$parameter_name.st})
    ;

type_spec
    :     ^(CUSTOM_TYPE type_name REF_VK? (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK)?)
    ->   typespec_custom(
            name={$type_name.st}, is_ref={$REF_VK != null}, is_percent_rowtype={$PERCENT_ROWTYPE_VK != null},
            is_percent_type={$PERCENT_TYPE_VK != null})
    |    native_datatype_spec -> { $native_datatype_spec.st; }
    |    ^(INTERVAL_DATATYPE interval_type_spec_first interval_type_spec_second)
    ->   interval_type_spec(left_side={$interval_type_spec_first.st}, right_side={$interval_type_spec_second.st})
    ;

interval_type_spec_first
    :   ^(
          (YEAR_VK|DAY_VK)
          expression?
        )
        -> interval_type_spec_first(is_year={$YEAR_VK != null}, is_day={$DAY_VK != null}, expression={$expression.st})
    ;

interval_type_spec_second
    :   ^(
          (MONTH_VK|SECOND_VK)
          expression?
        )
        -> interval_type_spec_second(is_month={$MONTH_VK != null}, is_second={$SECOND_VK != null}, expression={$expression.st})
    ;

type_precision
    :    ^(PRECISION size1=constant size2=constant? (precision_char=CHAR_VK|precision_byte=BYTE_VK)?)
    ->   base_type_spec_precision(
  size1={$size1.st}, size2={$size2.st}, is_byte={$precision_byte != null}, is_char={$precision_char != null}
)
    ;

native_datatype_spec
@init { StringTemplate typeBaseName = null; }
    :    ^(NATIVE_DATATYPE
    (    BFILE_VK { typeBaseName = %native_datatype_bfile(); }
    |    BINARY_FLOAT_VK { typeBaseName = %native_datatype_binary_float(); }
    |    BINARY_INTEGER_VK { typeBaseName = %native_datatype_binary_integer(); }
    |    BLOB_VK { typeBaseName = %native_datatype_blob(); }
    |    BOOLEAN_VK { typeBaseName = %native_datatype_boolean(); }
    |    CHARACTER_VK  { typeBaseName = %native_datatype_character(); }
    |    CHAR_VK { typeBaseName = %native_datatype_char(); }
    |    CLOB_VK { typeBaseName = %native_datatype_clob(); }
    |    SQL92_RESERVED_DATE { typeBaseName = %native_datatype_date(); }
    |    DAY_VK
    |    DECIMAL_VK  { typeBaseName = %native_datatype_decimal(); }
    |    DEC_VK { typeBaseName = %native_datatype_dec(); }
    |    DOUBLE_VK { typeBaseName = %native_datatype_double(); }
    |    DSINTERVAL_UNCONSTRAINED_VK
    |    FLOAT_VK { typeBaseName = %native_datatype_float(); }
    |    HOUR_VK
    |    INTEGER_VK { typeBaseName = %native_datatype_integer(); }
    |    INT_VK { typeBaseName = %native_datatype_int(); }
    |    LONG_VK { typeBaseName = %native_datatype_long(); }
    |    LONG_RAW { typeBaseName = %native_datatype_long_raw(); }
    |    MINUTE_VK
    |    MLSLABEL_VK
    |    MONTH_VK
    |    NATURALN_VK
    |    NATURAL_VK
    |    NCHAR_VK { typeBaseName = %native_datatype_nchar(); }
    |    NCLOB_VK { typeBaseName = %native_datatype_nclob(); }
    |    NUMBER_VK { typeBaseName = %native_datatype_number(); }
    |    NUMERIC_VK { typeBaseName = %native_datatype_numeric(); }
    |    NVARCHAR2_VK { typeBaseName = %native_datatype_nvarchar2(); }
    |    PLS_INTEGER_VK { typeBaseName = %native_datatype_pls_integer(); }
    |    POSITIVEN_VK
    |    POSITIVE_VK
    |    RAW_VK { typeBaseName = %native_datatype_raw(); }
    |    REAL_VK
    |    ROWID_VK { typeBaseName = %native_datatype_rowid(); }
    |    SECOND_VK
    |    SIGNTYPE_VK
    |    SIMPLE_INTEGER_VK
    |    SMALLINT_VK { typeBaseName = %native_datatype_smallint(); }
    |    STRING_VK
    |    TIMESTAMP_LTZ_UNCONSTRAINED_VK
    |    TIMESTAMP_TZ_UNCONSTRAINED_VK
    |    TIMESTAMP_UNCONSTRAINED_VK
    |    TIMESTAMP_VK { typeBaseName = %native_datatype_timestamp(); }
    |    TIMEZONE_ABBR_VK
    |    TIMEZONE_HOUR_VK
    |    TIMEZONE_MINUTE_VK
    |    TIMEZONE_REGION_VK
    |    UROWID_VK { typeBaseName = %native_datatype_urowid(); }
    |    VARCHAR2_VK { typeBaseName = %native_datatype_varchar2(); }
    |    VARCHAR_VK { typeBaseName = %native_datatype_varchar(); }
    |    YEAR_VK
    |    YMINTERVAL_UNCONSTRAINED_VK
    |    PGSQL_TEXT { typeBaseName = %native_datatype_text(); }
    |    PGSQL_NATIVE_DATATYPE_INTERVAL { typeBaseName = %native_datatype_interval(); })
    prec=type_precision?
    (is_tz=TIME_VK is_tz_local=LOCAL_VK?)?
    )
    { if (typeBaseName == null) { typeBaseName = %native_datatype_unsupported(); } }
    -> base_type_spec(
  baseName={typeBaseName}, precision={$prec.st},
  is_with_time_zone={$is_tz != null}, is_time_zone_local={$is_tz_local != null}
)
    ;

general_element
@init {
    List<StringTemplate> parts = new ArrayList<StringTemplate>();
}
    :    ^(CASCATED_ELEMENT
            (
              general_element_id {
                if (parts.size() > 0) {
                   parts.add(%string_literal(val={"."}));
                }
                parts.add($general_element_id.st);
              }
              | function_argument { parts.add($function_argument.st); }
            )+
          )
    ->   general_element(parts={parts})
    ;
 
general_element_id
    : ^(ANY_ELEMENT id=commented_id) -> string_literal(val={$id.st})
    ;

// $>

// $<Lexer Mappings

constant
    :    v1=UNSIGNED_INTEGER -> string_literal(val={$v1.text})
    |    ^(CONSTANT_NEGATED v2=UNSIGNED_INTEGER) -> string_literal(val={"-" + $v2.text})
    |    EXACT_NUM_LIT -> string_literal(val={$EXACT_NUM_LIT.text})
    |    APPROXIMATE_NUM_LIT -> string_literal(val={$APPROXIMATE_NUM_LIT.text})
    |    CHAR_STRING -> string_literal(val={$CHAR_STRING.text})
    |    SQL92_RESERVED_NULL -> string_literal(val={"null"})
    |    SQL92_RESERVED_TRUE -> string_literal(val={"true"})
    |    SQL92_RESERVED_FALSE -> string_literal(val={"false"})
    |    DBTIMEZONE_VK  -> string_literal(val={"dbtimezone"})
    |    SESSIONTIMEZONE_VK -> string_literal(val={"sessiontimezone"})
    |    MINVALUE_VK -> string_literal(val={"minvalue"})
    |    MAXVALUE_VK -> string_literal(val={"maxvalue"})
    |    SQL92_RESERVED_DEFAULT -> string_literal(val={"default"})
    |    ^(PGSQL_TYPED_LITERAL type_spec CHAR_STRING)
         -> pgsql_typed_literal(type={$type_spec.st}, string={$CHAR_STRING.text})
    ;
    
// $>

id_with_charset
    :    char_set_name? commented_id
    ->   {$commented_id.st}
    ;

commented_id
    :   val=ID
        ->  commented_id(val={$val.text}, cbefore={null}, cafter={null})
    |   ^(val=ID ^(COMMENT cbefore=COMMENT cinside=COMMENT cafter=COMMENT))
        ->  commented_id(
              val={$val.text},
              cbefore={"".equals($cbefore.text) ? null : $cbefore.text},
              cafter={"".equals($cafter.text) ? null : $cafter.text})
    ;
    