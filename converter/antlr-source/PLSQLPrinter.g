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

}

@header {
/**
 * Oracle(c) PL/SQL 11g Parser  
 *
 * Copyright (c) 2014 Bars Group
 */
package parser;
}


compilation_unit
    :    ^(COMPILATION_UNIT u+=unit_statement*)
    ->  template(statements={$u})
<<
--Compilation unit
<statements:{v|<v>}; separator="\n\n">
>>
    ;

sql_script
    :    ^(SQL_SCRIPT u+=unit_statement*)
    ->  template(statements={$u})
<<
--SQL script
<statements:{v|--script
<v>}; separator="\n\n">
>>
    ;

serveroutput_declaration
    :    ^(SET_SERVEROUTPUT (SQL92_RESERVED_ON|OFF_VK))
    ->   template() "not implemented: "
    ;

unit_statement
    :    alter_function
    ->   template() "not implemented: "
    |    alter_package
    ->   template() "not implemented: "
    |    alter_procedure
    ->   template() "not implemented: "
    |    alter_sequence
    ->   template() "not implemented: "
    |    alter_trigger
    ->   template() "not implemented: "
    |    alter_type
    ->   template() "not implemented: "
    |    create_function_body { $unit_statement.st = $create_function_body.st; }
    |    create_procedure_body
    ->   template() "not implemented: "
    |    create_package
    ->   template() "not implemented: "
    |    create_sequence
    ->   template() "not implemented: "
    |    create_trigger
    ->   template() "not implemented: "
    |    create_type
    ->   template() "not implemented: "
    |    drop_function
    ->   template() "not implemented: "
    |    drop_package
    ->   template() "not implemented: "
    |    drop_procedure
    ->   template() "not implemented: "
    |    drop_sequence
    ->   template() "not implemented: "
    |    drop_trigger
    ->   template() "not implemented: "
    |    drop_type
    ->   template() "not implemented: "
    ;

// $<DDL -> SQL Statements for Stored PL/SQL Units

// $<Function DDLs

drop_function
    :    ^(DROP_FUNCTION function_name)
    ->   template() "not implemented: "
    ;

alter_function
    :    ^(ALTER_FUNCTION function_name DEBUG_VK? REUSE_VK? compiler_parameters_clause*)
    ->   template() "not implemented: "
    ;

create_function_body
    :    ^(CREATE_FUNCTION REPLACE_VK? ^(FUNCTION_NAME name+=ID+) ret=type_spec ^(PARAMETERS args+=parameter*)
            ac+=invoker_rights_clause* ac+=parallel_enable_clause* ac+=result_cache_clause* DETERMINISTIC_VK*
            (    ^(USING_MODE PIPELINED_VK? AGGREGATE_VK? implementation_type_name)
    ->   template() "not implemented: "
            |    ^(CALL_MODE PIPELINED_VK? call_spec)
    ->   template() "not implemented: "
            |    ^(BODY_MODE body_pipe=PIPELINED_VK? declare_spec* body)
                 -> create_function_body(
                      is_replace={$REPLACE_VK != null}, name_parts={$name},
                      arguments={$args}, return_type={$ret.st}, add_clauses={$ac}, is_pipelined={$body_pipe != null},
                      body={$body.st})
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
    ->   template() "not implemented: "
    ;

result_cache_clause
    :    ^(RESULT_CACHE_VK relies_on_part?)
    ->   template() "not implemented: "
    ;

relies_on_part
    :    ^(RELIES_ON_VK tableview_name+)
    ->   template() "not implemented: "
    ;

streaming_clause
    :    ^(STREAMING_CLAUSE (SQL92_RESERVED_ORDER|CLUSTER_VK) expression ^(COLUMNS column_name+)) 
    ->   template() "not implemented: "
    ;
// $>
// $>

// $<Package DDLs

drop_package
    :    ^(DROP_PACKAGE package_name BODY_VK?)
    ->   template() "not implemented: "
    ;

alter_package
    :    ^(ALTER_PACKAGE package_name DEBUG_VK? REUSE_VK? 
                (PACKAGE_VK|BODY_VK|SPECIFICATION_VK)? compiler_parameters_clause*)
    ->   template() "not implemented: "
    ;

create_package
    :    ^(CREATE_PACKAGE_SPEC REPLACE_VK? package_name invoker_rights_clause? package_obj_spec*) 
    ->   template() "not implemented: "
    |    ^(CREATE_PACKAGE_BODY REPLACE_VK? package_name package_obj_body* seq_of_statements?)
    ->   template() "not implemented: "
    ;

// $<Create Package - Specific Clauses

package_obj_spec
    :    variable_declaration
    ->   template() "not implemented: "
    |     subtype_declaration
    ->   template() "not implemented: "
    |     cursor_declaration
    ->   template() "not implemented: "
    |     exception_declaration
    ->   template() "not implemented: "
    |     record_declaration
    ->   template() "not implemented: "
    |     table_declaration
    ->   template() "not implemented: "
    |     procedure_spec
    ->   template() "not implemented: "
    |     function_spec
    ->   template() "not implemented: "
    |     pragma_declaration
    ->   template() "not implemented: "
    ;

procedure_spec
    :     ^(PROCEDURE_SPEC procedure_name ^(PARAMETERS parameter*)
            (^(CALL_MODE call_spec))?
    ) 
    ->   template() "not implemented: "
    ;

function_spec
    :    ^(FUNCTION_SPEC function_name (type_spec|SELF_VK) ^(PARAMETERS parameter*)
            invoker_rights_clause* parallel_enable_clause* result_cache_clause* DETERMINISTIC_VK*
            (    ^(CALL_MODE call_spec)
            |    ^(EXTERNAL_VK expression)
            )?
        )
    ->   template() "not implemented: "
    ;

package_obj_body
    :     variable_declaration 
    ->   template() "not implemented: "
    |     subtype_declaration 
    ->   template() "not implemented: "
    |     cursor_declaration 
    ->   template() "not implemented: "
    |     exception_declaration 
    ->   template() "not implemented: "
    |     record_declaration
    ->   template() "not implemented: "
    |     table_declaration
    ->   template() "not implemented: "
    |     create_procedure_body
    ->   template() "not implemented: "
    |     create_function_body
    ->   template() "not implemented: "
    |     create_type
    ->   template() "not implemented: "
    ;

// $>

// $>

// $<Procedure DDLs

drop_procedure
    :    ^(DROP_PROCEDURE procedure_name)
    ->   template() "not implemented: "
    ;

alter_procedure
    :    ^(ALTER_PROCEDURE procedure_name DEBUG_VK? REUSE_VK? compiler_parameters_clause*)
    ->   template() "not implemented: "
    ;

create_procedure_body
    :    ^(CREATE_PROCEDURE REPLACE_VK? procedure_name ^(PARAMETERS parameter*) invoker_rights_clause?
            (    EXTERNAL_VK
            |    ^(CALL_MODE call_spec)
            |    ^(BODY_MODE declare_spec* body)
            )
        )
    ->   template() "not implemented: "
    ;

// $>

// $<Trigger DDLs

drop_trigger
    :    ^(DROP_TRIGGER trigger_name)
    ->   template() "not implemented: "
    ;

alter_trigger
    :    ^(ALTER_TRIGGER trigger_name 
            (    (ENABLE_VK|DISABLE_VK)
            |    ^(RENAME_VK trigger_name)
            |    DEBUG_VK? REUSE_VK? compiler_parameters_clause*
            )
        )
    ->   template() "not implemented: "
    ;

create_trigger
    :    ^(CREATE_TRIGGER REPLACE_VK? trigger_name  
            simple_dml_trigger? compound_dml_trigger? non_dml_trigger?
            trigger_follows_clause? (ENABLE_VK|DISABLE_VK)? trigger_when_clause? trigger_body)
    ->   template() "not implemented: "
    ;

trigger_follows_clause
    :    ^(FOLLOWS_VK trigger_name+)
    ->   template() "not implemented: "
    ;

trigger_when_clause
    :    ^(SQL92_RESERVED_WHEN expression)
    ->   template() "not implemented: "
    ;

// $<Create Trigger- Specific Clauses
simple_dml_trigger
    :    ^(SIMPLE_DML (BEFORE_VK|AFTER_VK|INSTEAD_VK) FOR_EACH_ROW? referencing_clause? dml_event_clause)
    ->   template() "not implemented: "
    ;

compound_dml_trigger
    :    ^(COMPOUND_DML referencing_clause? dml_event_clause)
    ->   template() "not implemented: "
    ;

non_dml_trigger
    :    ^(NON_DML (BEFORE_VK|AFTER_VK) non_dml_event+ (DATABASE_VK|schema_name? SCHEMA_VK))
    ->   template() "not implemented: "
    ;

trigger_body
    :    ^(COMPOUND_VK trigger_name declare_spec* timing_point_section+)
    ->   template() "not implemented: "
    |    ^(CALL_VK routine_name function_argument?) 
    ->   template() "not implemented: "
    |    ^(BODY_MODE block)
    ->   template() "not implemented: "
    ;

timing_point_section
    :    ^(BEFORE_STATEMENT block)
    ->   template() "not implemented: "
    |    ^(BEFORE_EACH_ROW block)
    ->   template() "not implemented: "
    |    ^(AFTER_STATEMENT block)
    ->   template() "not implemented: "
    |    ^(AFTER_EACH_ROW block)
    ->   template() "not implemented: "
    ;

non_dml_event
    :    SQL92_RESERVED_ALTER
    ->   template() "not implemented: "
    |    ANALYZE_VK
    ->   template() "not implemented: "
    |    ASSOCIATE_VK STATISTICS_VK
    ->   template() "not implemented: "
    |    AUDIT_VK
    ->   template() "not implemented: "
    |    COMMENT_VK
    ->   template() "not implemented: "
    |    SQL92_RESERVED_CREATE
    ->   template() "not implemented: "
    |    DISASSOCIATE_VK STATISTICS_VK
    |    SQL92_RESERVED_DROP
    ->   template() "not implemented: "
    |    SQL92_RESERVED_GRANT
    ->   template() "not implemented: "
    |    NOAUDIT_VK
    ->   template() "not implemented: "
    |    RENAME_VK
    ->   template() "not implemented: "
    |    SQL92_RESERVED_REVOKE
    ->   template() "not implemented: "
    |    TRUNCATE_VK
    ->   template() "not implemented: "
    |    DDL_VK
    ->   template() "not implemented: "
    |    STARTUP_VK
    ->   template() "not implemented: "
    |    SHUTDOWN_VK
    ->   template() "not implemented: "
    |    DB_ROLE_CHANGE_VK
    ->   template() "not implemented: "
    |    LOGON_VK
    ->   template() "not implemented: "
    |    LOGOFF_VK
    ->   template() "not implemented: "
    |    SERVERERROR_VK
    ->   template() "not implemented: "
    |    SUSPEND_VK
    ->   template() "not implemented: "
    |    DATABASE_VK
    ->   template() "not implemented: "
    |    SCHEMA_VK
    ->   template() "not implemented: "
    |    FOLLOWS_VK
    ->   template() "not implemented: "
    ;

dml_event_clause
    :    ^(DML_EVENT dml_event_element+ ^(SQL92_RESERVED_ON tableview_name dml_event_nested_clause?))  
    ->   template() "not implemented: "
    ;

dml_event_element
    :    ^(DML_EVENT_ELEMENT (SQL92_RESERVED_DELETE|SQL92_RESERVED_INSERT|SQL92_RESERVED_UPDATE) ^(COLUMNS column_name*))
    ->   template() "not implemented: "
    ;

dml_event_nested_clause
    :    ^(NESTED_VK tableview_name)
    ->   template() "not implemented: "
    ;

referencing_clause
    :    ^(REFERENCING_VK referencing_element+)
    ->   template() "not implemented: "
    ;

referencing_element
    :    ^((NEW_VK|OLD_VK|PARENT_VK) alias)
    ->   template() "not implemented: "
    ;

// $>
// $>

// $<Type DDLs

drop_type
    :    ^(DROP_TYPE type_name BODY_VK? FORCE_VK? VALIDATE_VK?)
    ->   template() "not implemented: "
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
    ->   template() "not implemented: "
    ;

// $<Alter Type - Specific Clauses

alter_method_element
    :    ^(ALTER_METHOD_ELEMENT (ADD_VK|SQL92_RESERVED_DROP) map_order_function_spec? subprogram_spec?)
    ->   template() "not implemented: "
    ;

attribute_definition
    :    ^(ATTRIBUTE attribute_name type_spec?)
    ->   template() "not implemented: "
    ;

alter_collection_clauses
    :    ^(ALTER_COLLECTION 
            (    ^(TYPE_VK type_spec)
            |    ^(LIMIT_VK expression)
            )
        ) 
    ->   template() "not implemented: "
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
    ->   template() "not implemented: "
    ;

dependent_exceptions_part
    :    ^(EXCEPTIONS_VK FORCE_VK? tableview_name)
    ->   template() "not implemented: "
    ;

// $>

create_type
    :    ^(CREATE_TYPE_BODY REPLACE_VK? type_name ^(TYPE_BODY_ELEMENTS type_body_elements+))
    ->   template() "not implemented: "
    |    ^(CREATE_TYPE_SPEC REPLACE_VK? type_name CHAR_STRING? object_type_def?)
    ->   template() "not implemented: "
    ;

object_type_def
    :    ^(OBJECT_TYPE_DEF (object_as_part|object_under_part) invoker_rights_clause?
             sqlj_object_type? modifier_clause* ^(OBJECT_MEMBERS element_spec*))  
    ->   template() "not implemented: "
    ;

object_as_part
    :    ^(OBJECT_AS (OBJECT_VK|varray_type_def|nested_table_type_def))
    ->   template() "not implemented: "
    ;

object_under_part
    :    ^(UNDER_VK type_spec)
    ->   template() "not implemented: "
    ;

nested_table_type_def
    :    ^(NESTED_TABLE_TYPE_DEF type_spec SQL92_RESERVED_NULL? table_indexed_by_part?)
    ->   template() "not implemented: "
    ;

sqlj_object_type
    :    ^(JAVA_VK expression (SQLDATA_VK|CUSTOMDATUM_VK|ORADATA_VK))
    ->   template() "not implemented: "
    ;

type_body_elements
    :    map_order_func_declaration
    ->   template() "not implemented: "
    |    subprog_decl_in_type
    ->   template() "not implemented: "
    ;

map_order_func_declaration
    :    ^((MAP_VK|SQL92_RESERVED_ORDER) create_function_body)
    ->   template() "not implemented: "
    ;

subprog_decl_in_type
    :    ^((MEMBER_VK|STATIC_VK)
            (    create_procedure_body
            |    create_function_body
            |    constructor_declaration
            )
        )
    ->   template() "not implemented: "
    ;

constructor_declaration
    :    ^(CONSTRUCTOR_VK type_spec FINAL_VK? INSTANTIABLE_VK? ^(PARAMETERS type_elements_parameter*) 
            (    ^(CALL_MODE call_spec)
            |    ^(BODY_MODE declare_spec* body)
            )
        )
    ->   template() "not implemented: "
    ;

// $>

// $<Common Type Clauses

modifier_clause
    :    ^(MODIFIER SQL92_RESERVED_NOT? (INSTANTIABLE_VK|FINAL_VK|OVERRIDING_VK))
    ->   template() "not implemented: "
    ;

sqlj_object_type_attr
    :    ^(EXTERNAL_VK expression)
    ->   template() "not implemented: "
    ;

element_spec
    :    ^(ELEMENT_SPEC element_spec_options+ modifier_clause? pragma_clause?)
    ->   template() "not implemented: "
    ;

element_spec_options
    :    subprogram_spec
    ->   template() "not implemented: "
    |    constructor_spec
    ->   template() "not implemented: "
    |    map_order_function_spec
    ->   template() "not implemented: "
    |    ^(FIELD_SPEC id type_spec sqlj_object_type_attr?)
    ->   template() "not implemented: "
    ;

subprogram_spec
    :    ^((MEMBER_VK|STATIC_VK)
            (    procedure_spec
            |    function_spec
            )
        )
    ->   template() "not implemented: "
    ;

constructor_spec
    :    ^(CONSTRUCTOR_SPEC type_spec FINAL_VK? INSTANTIABLE_VK? ^(PARAMETERS type_elements_parameter*) constructor_call_mode?)
    ->   template() "not implemented: "
    ;

constructor_call_mode
    :    ^(CALL_MODE call_spec)
    ->   template() "not implemented: "
    ;

map_order_function_spec
    :    ^((MAP_VK|SQL92_RESERVED_ORDER) function_spec)
    ->   template() "not implemented: "
    ;

pragma_clause
    :    ^(PRAGMA_VK pragma_elements+)
    ->   template() "not implemented: "
    ;

pragma_elements
    :    id
    ->   template() "not implemented: "
    |    SQL92_RESERVED_DEFAULT
    ->   template() "not implemented: "
    ;

type_elements_parameter
    :    ^(PARAMETER parameter_name type_spec)
    ->   template() "not implemented: "
    ;

// $>
// $>


// $<Sequence DDLs

drop_sequence
    :   ^(DROP_SEQUENCE sequence_name)
    ->   template() "not implemented: "
    ;

alter_sequence
    :    ^(ALTER_SEQUENCE sequence_name sequence_spec+)
    ->   template() "not implemented: "
    ;

create_sequence
    :    ^(CREATE_SEQUENCE sequence_name sequence_spec*)
    ->   template() "not implemented: "
    ;

// $<Common Sequence

sequence_spec
    :    ^(START_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    ^(INCREMENT_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    ^(MAXVALUE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    ^(MINVALUE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    ^(CACHE_VK UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    NOMAXVALUE_VK
    ->   template() "not implemented: "
    |    NOMINVALUE_VK
    ->   template() "not implemented: "
    |    CYCLE_VK
    ->   template() "not implemented: "
    |    NOCYCLE_VK
    ->   template() "not implemented: "
    |    NOCACHE_VK
    ->   template() "not implemented: "
    |    ORDER_VK
    ->   template() "not implemented: "
    |    NOORDER_VK
    ->   template() "not implemented: "
    ;

// $>
// $>


// $<Common DDL Clauses

invoker_rights_clause
    :    ^(AUTHID_VK (CURRENT_USER_VK|DEFINER_VK))
    ->   template() "not implemented: "
    ;

compiler_parameters_clause
    :    ^(COMPILER_PARAMETER ^(ASSIGN id expression))
    ->   template() "not implemented: "
    ;

call_spec
    :    ^(LANGUAGE_VK ( java_spec | c_spec ))
    ->   template() "not implemented: "
    ;

// $<Call Spec - Specific Clauses

java_spec
    :    ^(JAVA_VK CHAR_STRING)
    ->   template() "not implemented: "
    ;

c_spec
    :    ^(C_VK CHAR_STRING? CONTEXT_VK? ^(LIBRARY_VK id) c_agent_in_clause? c_parameters_clause?)
    ->   template() "not implemented: "
    ;

c_agent_in_clause
    :    ^(AGENT_VK expression+)
    ->   template() "not implemented: "
    ;

c_parameters_clause
    :    ^(PARAMETERS_VK (THREE_DOTS|expression+))
    ->   template() "not implemented: "
    ;

// $>

parameter
    :    ^(PARAMETER parameter_name (SQL92_RESERVED_IN|OUT_VK|INOUT_VK)* type_spec? default_value_part?)
    ->   template() "not implemented: "
    ;

default_value_part
    :    ^(DEFAULT_VALUE expression)
    ->   template() "not implemented: "
    ;

// $>

// $>

// $<PL/SQL Elements Declarations

declare_spec
    :    variable_declaration
    ->   template() "not implemented: "
    |     subtype_declaration
    ->   template() "not implemented: "
    |     cursor_declaration
    ->   template() "not implemented: "
    |     exception_declaration
    ->   template() "not implemented: "
    |     pragma_declaration
    ->   template() "not implemented: "
    |     record_declaration
    ->   template() "not implemented: "
    |     table_declaration
    ->   template() "not implemented: "
    |     create_procedure_body
    ->   template() "not implemented: "
    |     create_function_body
    ->   template() "not implemented: "
    ;

//incorporates constant_declaration
variable_declaration
    :    ^(VARIABLE_DECLARE variable_name type_spec CONSTANT_VK? SQL92_RESERVED_NULL? default_value_part?)
    ->   template() "not implemented: "
    ;    

subtype_declaration
      :    ^(SUBTYPE_DECLARE type_name type_spec SQL92_RESERVED_NULL? subtype_range?)
    ->   template() "not implemented: "
      ;

subtype_range
    :    ^(RANGE_VK expression+)
    ->   template() "not implemented: "
    ;

//cursor_declaration incorportates curscursor_body and cursor_spec
cursor_declaration
    :    ^(CURSOR_DECLARE cursor_name type_spec? select_statement? ^(PARAMETERS parameter_spec*)) 
    ->   template() "not implemented: "
    ;

parameter_spec
    :    ^(PARAMETER parameter_name type_spec? default_value_part?)
    ->   template() "not implemented: "
    ;

exception_declaration 
    :    ^(EXCEPTION_DECLARE exception_name)
    ->   template() "not implemented: "
    ;             

pragma_declaration
    :    ^(PRAGMA_DECLARE 
            (    SERIALLY_REUSABLE_VK
            |     AUTONOMOUS_TRANSACTION_VK
            |    ^(EXCEPTION_INIT_VK exception_name constant)
            |    ^(INLINE_VK id expression)
            |    ^(RESTRICT_REFERENCES_VK SQL92_RESERVED_DEFAULT? id*)
            )
        )
    ->   template() "not implemented: "
    ;

record_declaration
    :    record_type_dec
    ->   template() "not implemented: "
    |    record_var_dec
    ->   template() "not implemented: "
    ;

// $<Record Declaration - Specific Clauses

//incorporates ref_cursor_type_definition
record_type_dec
    :    ^(RECORD_TYPE_DECLARE type_name REF_VK? type_spec? ^(FIELDS field_spec*))
    ->   template() "not implemented: "
    ;

field_spec
    :    ^(FIELD_SPEC column_name type_spec? SQL92_RESERVED_NULL? default_value_part?)
    ->   template() "not implemented: "
    ;

record_var_dec
    :    ^(RECORD_VAR_DECLARE record_name type_name (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK))
    ->   template() "not implemented: "
    ;

// $>

table_declaration
    :    table_type_dec
    ->   template() "not implemented: "
    |    table_var_dec
    ->   template() "not implemented: "
    ;

table_type_dec
    :    ^(TABLE_TYPE_DECLARE type_name 
            (    varray_type_def
            |    SQL92_RESERVED_NULL? ^(SQL92_RESERVED_TABLE type_spec table_indexed_by_part?)
            )
        )
    ->   template() "not implemented: "
    ;

table_indexed_by_part
    :    ^(INDEXED_BY type_spec)
    ->   template() "not implemented: "
    ;

varray_type_def
    :    SQL92_RESERVED_NULL? ^(VARR_ARRAY_DEF expression type_spec)
    ->   template() "not implemented: "
    ;

table_var_dec
    :    ^(TABLE_VAR_DECLARE table_var_name type_spec)
    ->   template() "not implemented: "
    ;

// $>

// $<PL/SQL Statements

seq_of_statements
    :     ^(STATEMENTS statement+)
    ->   template() "not implemented: "
    ;

statement
    :    label_declaration
    ->   template() "not implemented: "
    |    assignment_statement
    ->   template() "not implemented: "
    |    continue_statement
    ->   template() "not implemented: "
    |    exit_statement
    ->   template() "not implemented: "
    |    goto_statement
    ->   template() "not implemented: "
    |    if_statement
    ->   template() "not implemented: "
    |    loop_statement
    ->   template() "not implemented: "
    |    forall_statement
    ->   template() "not implemented: "
    |    null_statement
    ->   template() "not implemented: "
    |    raise_statement
    ->   template() "not implemented: "
    |    return_statement
    ->   template() "not implemented: "
    |    pipe_row_statement
    ->   template() "not implemented: "
    |    case_statement
    ->   template() "not implemented: "
    |    sql_statement
    ->   template() "not implemented: "
    |    function_call
    ->   template() "not implemented: "
    |    body
    ->   template() "not implemented: "
    |    block
    ->   template() "not implemented: "
    ;

label_declaration
    :    ^(LABEL_DECLARE label_name)
    ->   template() "not implemented: "
    ;

assignment_statement
    :     ^(ASSIGN general_element expression)
    ->   template() "not implemented: "
    ;

continue_statement
    :    ^(CONTINUE_VK label_name? general_when?)
    ->   template() "not implemented: "
    ;

general_when
    :    ^(SQL92_RESERVED_WHEN expression)
    ->   template() "not implemented: "
    ;

exit_statement
    :    ^(EXIT_VK label_name? general_when?)
    ->   template() "not implemented: "
    ;

goto_statement
    :    ^(SQL92_RESERVED_GOTO label_name)
    ->   template() "not implemented: "
    ;

if_statement
    :    ^(PLSQL_RESERVED_IF expression seq_of_statements elsif_part* else_part?)
    ->   template() "not implemented: "
    ;

elsif_part
    :    ^(PLSQL_NON_RESERVED_ELSIF expression seq_of_statements)
    ->   template() "not implemented: "
    ;

else_part
    :    ^(SQL92_RESERVED_ELSE seq_of_statements)
    ->   template() "not implemented: "
    ;

loop_statement
    :    ^(WHILE_LOOP label_name* expression seq_of_statements)
    ->   template() "not implemented: "
    |    ^(FOR_LOOP label_name* cursor_loop_param seq_of_statements)
    ->   template() "not implemented: "
    |    ^(LOOP_VK label_name* seq_of_statements)
    ->   template() "not implemented: "
    ;

// $<Loop - Specific Clause

cursor_loop_param
    :    ^(INDEXED_FOR index_name REVERSE_VK? ^(SIMPLE_BOUND expression expression))
    ->   template() "not implemented: "
    |    ^(CURSOR_BASED_FOR record_name cursor_name expression_list?)
    ->   template() "not implemented: "
    |    ^(SELECT_BASED_FOR record_name select_statement)
    ->   template() "not implemented: "
    ;

// $>

forall_statement
    :    ^(FORALL_VK index_name bounds_clause sql_statement EXCEPTIONS_VK?)
    ->   template() "not implemented: "
    ;

bounds_clause
    :    ^(SIMPLE_BOUND expression expression)
    ->   template() "not implemented: "
    |    ^(INDICES_BOUND collection_name between_bound?)
    ->   template() "not implemented: "
    |    ^(VALUES_BOUND index_name) 
    ->   template() "not implemented: "
    ;

between_bound
    :    ^(SQL92_RESERVED_BETWEEN expression expression)
    ->   template() "not implemented: "
    ;

null_statement
    :    SQL92_RESERVED_NULL
    ->   template() "not implemented: "
    ;

raise_statement
    :    ^(RAISE_VK exception_name?)
    ->   template() "not implemented: "
    ;

return_statement
    :    ^(RETURN_VK expression?)
    ->   template() "not implemented: "
    ;

function_call
    :    ^(ROUTINE_CALL general_element)
    ->   template() "not implemented: "
    ;

body
    :    ^(BODY label_name? seq_of_statements exception_clause?) 
    ->   template() "not implemented: "
    ;

// $<Body - Specific Clause

exception_clause
    :    ^(SQL92_RESERVED_EXCEPTION exception_handler+)
    ->   template() "not implemented: "
    ;

exception_handler
    :    ^(SQL92_RESERVED_WHEN exception_name+ seq_of_statements)
    ->   template() "not implemented: "
    ;

// $>

block
    :    ^(BLOCK declare_spec* body)
    ->   template() "not implemented: "
    ;

// $>

// $<SQL PL/SQL Statements

sql_statement
    :    execute_immediate
    ->   template() "not implemented: "
    |    data_manipulation_language_statements
    ->   template() "not implemented: "
    |    cursor_manipulation_statements
    ->   template() "not implemented: "
    |    transaction_control_statements
    ->   template() "not implemented: "
    ;

execute_immediate
    :    ^(EXECUTE_VK expression (into_clause|using_clause|dynamic_returning_clause)?) 
    ->   template() "not implemented: "
    ;

// $<Execute Immediate - Specific Clause
dynamic_returning_clause
    :    ^(DYNAMIC_RETURN into_clause)
    ->   template() "not implemented: "
    ;
// $>


// $<DML SQL PL/SQL Statements

data_manipulation_language_statements
    :    merge_statement
    ->   template() "not implemented: "
    |    lock_table_statement
    ->   template() "not implemented: "
    |    select_statement
    ->   template() "not implemented: "
    |     update_statement
    ->   template() "not implemented: "
    |     delete_statement
    ->   template() "not implemented: "
    |    insert_statement
    ->   template() "not implemented: "
    ;

select_statement
    :    ^(SELECT_STATEMENT subquery_factoring_clause? subquery for_update_clause* order_by_clause*)  
    ->   template() "not implemented: "
    ;

// $<Select - Specific Clauses
subquery_factoring_clause
    :    ^(SQL92_RESERVED_WITH factoring_element+)
    ->   template() "not implemented: "
    ;

factoring_element
    :    ^(FACTORING query_name subquery)
    ->   template() "not implemented: "
    ;

subquery
    :    ^(SUBQUERY subquery_basic_elements subquery_operation_part*)
    ->   template() "not implemented: "
    ;

subquery_operation_part
    :    ^((SQL92_RESERVED_UNION|SQL92_RESERVED_INTERSECT|PLSQL_RESERVED_MINUS) SQL92_RESERVED_ALL? subquery_basic_elements)
    ->   template() "not implemented: "
    ;

subquery_basic_elements
    :    query_block
    ->   template() "not implemented: "
    |    subquery
    ->   template() "not implemented: "
    ;

query_block
    :    ^(SQL92_RESERVED_SELECT 
            from_clause 
            (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE)? SQL92_RESERVED_ALL? 
            (    ASTERISK
            |    ^(SELECT_LIST selected_element+)
            )
            into_clause? where_clause? hierarchical_query_clause? 
            group_by_clause? having_clause? model_clause?
        )
    ->   template() "not implemented: "
    ;

selected_element
    :    ^(SELECT_ITEM expression alias?)
    ->   template() "not implemented: "
    ;

from_clause
    :    ^(SQL92_RESERVED_FROM table_ref+)
    ->   template() "not implemented: "
    ;

table_ref
    :    ^(TABLE_REF table_ref_aux join_clause*)
    ->   template() "not implemented: "
    ;

table_ref_aux
    :    ^(TABLE_REF_ELEMENT alias? dml_table_expression_clause ONLY_VK? pivot_clause? unpivot_clause? flashback_query_clause*)
    ->   template() "not implemented: "
    ;

join_clause
    :    ^(JOIN_DEF (CROSS_VK|NATURAL_VK)? (INNER_VK|FULL_VK|LEFT_VK|RIGHT_VK)? table_ref_aux query_partition_clause* (join_on_part|join_using_part)?) 
    ->   template() "not implemented: "
    ;

join_on_part
    :    ^(SQL92_RESERVED_ON expression) 
    ->   template() "not implemented: "
    ;

join_using_part
    :    ^(PLSQL_NON_RESERVED_USING column_name+)
    ->   template() "not implemented: "
    ;

query_partition_clause
    :    ^(PARTITION_VK (subquery|expression_list|expression+))
    ->   template() "not implemented: "
    ;

flashback_query_clause
    :    ^((VERSIONS_VK|SQL92_RESERVED_AS) (SCN_VK|TIMESTAMP_VK)? expression) 
    ->   template() "not implemented: "
    ;

pivot_clause
    :    ^(PIVOT_VK XML_VK? pivot_element+ pivot_for_clause pivot_in_clause)
    ->   template() "not implemented: "
    ;

pivot_element
    :    ^(PIVOT_ELEMENT alias? expression)
    ->   template() "not implemented: "
    ;

pivot_for_clause
    :    ^(SQL92_RESERVED_FOR column_name+)
    ->   template() "not implemented: "
    ;

pivot_in_clause
    :    ^(SQL92_RESERVED_IN 
        (    subquery
        |    ^(ANY_MODE SQL92_RESERVED_ANY+)
        |    ^(ELEMENTS_MODE pivot_in_clause_element+)
        )
        )
    ->   template() "not implemented: "
    ;

pivot_in_clause_element
    :    ^(PIVOT_IN_ELEMENT alias? (expression|expression_list))
    ->   template() "not implemented: "
    ;

unpivot_clause
    :    ^(UNPIVOT_VK ((INCLUDE_VK|EXCLUDE_VK) NULLS_VK?)? column_name+ pivot_for_clause unpivot_in_clause)
    ->   template() "not implemented: "
    ;

unpivot_in_clause
    :    ^(SQL92_RESERVED_IN unpivot_in_element+)
    ->   template() "not implemented: "
    ;

unpivot_in_element
    :    ^(UNPIVOT_IN_ELEMENT column_name+ ^(PIVOT_ALIAS (expression|expression_list)))
    ->   template() "not implemented: "
    ;

hierarchical_query_clause
    :    ^(HIERARCHICAL start_part? ^(SQL92_RESERVED_CONNECT NOCYCLE_VK? expression))
    ->   template() "not implemented: "
    ;

start_part
    :    ^(PLSQL_RESERVED_START expression)
    ->   template() "not implemented: "
    ;

group_by_clause
    :    ^(SQL92_RESERVED_GROUP group_by_element+)
    ->   template() "not implemented: "
    ;

group_by_element
    :    ^(GROUP_BY_ELEMENT group_by_elements)
    ->   template() "not implemented: "
    ;

group_by_elements
    :    ^(GROUPING_VK groupin_set+)
    ->   template() "not implemented: "
    |    grouping_element 
    ->   template() "not implemented: "
    ;

groupin_set
    :    ^(GROUPIN_SET grouping_element)
    ->   template() "not implemented: "
    ;

grouping_element
    :    ^((ROLLUP_VK|CUBE_VK) grouping_element+)
    ->   template() "not implemented: "
    |    expression_list
    ->   template() "not implemented: "
    |    expression 
    ->   template() "not implemented: "
    ;

having_clause
    :    ^(SQL92_RESERVED_HAVING expression)
    ->   template() "not implemented: "
    ;

model_clause
    :    ^(PLSQL_NON_RESERVED_MODEL main_model cell_reference_options* return_rows_clause? reference_model*)
    ->   template() "not implemented: "
    ;

cell_reference_options
    :    ^((IGNORE_VK|KEEP_VK) NAV_VK)
    |    ^(SQL92_RESERVED_UNIQUE (DIMENSION_VK|SINGLE_VK))
    ->   template() "not implemented: "
    ;

return_rows_clause
    :    ^(RETURN_VK (UPDATED_VK|SQL92_RESERVED_ALL))
    ->   template() "not implemented: "
    ;

reference_model
    :    ^(REFERENCE_VK reference_model_name subquery model_column_clauses cell_reference_options*)
    ->   template() "not implemented: "
    ;

main_model
    :    ^(MAIN_MODEL main_model_name? model_column_clauses model_rules_clause cell_reference_options*)
    ->   template() "not implemented: "
    ;

model_column_clauses
    :    ^(MODEL_COLUMN ^(DIMENSION_VK model_column_list) ^(MEASURES_VK model_column_list) model_column_partition_part?)
    ->   template() "not implemented: "
    ;

model_column_partition_part
    :    ^(PARTITION_VK model_column_list)
    ->   template() "not implemented: "
    ;

model_column_list
    :    ^(MODEL_COLUMNS model_column+)
    ->   template() "not implemented: "
    ;

model_column
    :    ^(MODEL_COLUMN alias? expression) 
    ->   template() "not implemented: "
    ;

model_rules_clause
    :    ^(MODEL_RULES model_rules_element+ model_rules_part?)
    ->   template() "not implemented: "
    ;

model_rules_part
    :    ^(RULES_VK 
            (SQL92_RESERVED_UPDATE|UPSERT_VK SQL92_RESERVED_ALL?)? 
            (AUTOMATIC_VK|SEQUENTIAL_VK)? 
            model_iterate_clause?
        )
    ->   template() "not implemented: "
    ;

model_rules_element
    :    ^(MODEL_RULE 
            ^(ASSIGN model_expression expression) 
            (SQL92_RESERVED_UPDATE|UPSERT_VK SQL92_RESERVED_ALL?)? 
            order_by_clause?
        )
    ->   template() "not implemented: "
    ;

model_iterate_clause
    :    ^(ITERATE_VK expression until_part?)
    ->   template() "not implemented: "
    ;

until_part
    :    ^(UNTIL_VK expression)
    ->   template() "not implemented: "
    ;

order_by_clause
    :    ^(SQL92_RESERVED_ORDER SIBLINGS_VK? ^(ORDER_BY_ELEMENTS order_by_elements+))
    ->   template() "not implemented: "
    ;

order_by_elements
    :    ^(ORDER_BY_ELEMENT expression (SQL92_RESERVED_ASC|SQL92_RESERVED_DESC)? (NULLS_VK (FIRST_VK|LAST_VK))?)
    ->   template() "not implemented: "
    ;

for_update_clause
    :    ^(SQL92_RESERVED_FOR for_update_of_part? for_update_options?)
    ->   template() "not implemented: "
    ;

for_update_of_part
    :    ^(SQL92_RESERVED_OF column_name+)
    ->   template() "not implemented: "
    ;

for_update_options
    :    SKIP_VK
    ->   template() "not implemented: "
    |    PLSQL_RESERVED_NOWAIT
    ->   template() "not implemented: "
    |    ^(WAIT_VK expression)
    ->   template() "not implemented: "
    ;

// $>

update_statement
    :    ^(SQL92_RESERVED_UPDATE general_table_ref
            update_set_clause
            where_clause? static_returning_clause? error_logging_clause?
        )
    ->   template() "not implemented: "
    ;

// $<Update - Specific Clauses
update_set_clause
    :    ^(SET_VK update_set_elements+)
    ->   template() "not implemented: "
    ;

update_set_elements
    :    ^(ASSIGN column_name expression)
    ->   template() "not implemented: "
    |    ^(ASSIGN column_name+ subquery)
    ->   template() "not implemented: "
    |    ^(VALUE_VK char_set_name? ID expression)
    ->   template() "not implemented: "
    ;

// $>

delete_statement
    :    ^(SQL92_RESERVED_DELETE general_table_ref
            where_clause? static_returning_clause? error_logging_clause?)
    ->   template() "not implemented: "
    ;

insert_statement
    :    ^(SQL92_RESERVED_INSERT
        (    single_table_insert
        |    multi_table_insert
        )
        )
    ->   template() "not implemented: "
    ;

// $<Insert - Specific Clauses

single_table_insert
    :    ^(SINGLE_TABLE_MODE insert_into_clause (values_clause static_returning_clause?| select_statement) error_logging_clause?)
    ->   template() "not implemented: "
    ;

multi_table_insert
    :    ^(MULTI_TABLE_MODE select_statement (conditional_insert_clause|multi_table_element+))
    ->   template() "not implemented: "
    ;

multi_table_element
    :    ^(TABLE_ELEMENT insert_into_clause values_clause? error_logging_clause?)
    ->   template() "not implemented: "
    ;

conditional_insert_clause
    :    ^(CONDITIONAL_INSERT (SQL92_RESERVED_ALL|FIRST_VK)? conditional_insert_when_part+ conditional_insert_else_part?) 
    ->   template() "not implemented: "
    ;

conditional_insert_when_part
    :    ^(SQL92_RESERVED_WHEN expression multi_table_element+)
    ->   template() "not implemented: "
    ;

conditional_insert_else_part
    :    ^(SQL92_RESERVED_ELSE multi_table_element+)
    ->   template() "not implemented: "
    ;

insert_into_clause
    :    ^(SQL92_RESERVED_INTO general_table_ref ^(COLUMNS column_name*))
    ->   template() "not implemented: "
    ;

values_clause
    :    ^(SQL92_RESERVED_VALUES (expression_list|expression))
    ->   template() "not implemented: "
    ;

// $>
merge_statement
    :    ^(MERGE_VK alias? tableview_name 
            ^(PLSQL_NON_RESERVED_USING selected_tableview expression)
             merge_update_clause? merge_insert_clause? error_logging_clause?)
    ->   template() "not implemented: "
    ;

// $<Merge - Specific Clauses

merge_update_clause
    :    ^(MERGE_UPDATE merge_element+ where_clause? merge_update_delete_part?)
    ->   template() "not implemented: "
    ;

merge_element
    :    ^(ASSIGN column_name expression)
    ->   template() "not implemented: "
    ;

merge_update_delete_part
    :    ^(SQL92_RESERVED_DELETE where_clause)
    ->   template() "not implemented: "
    ;

merge_insert_clause
    :    ^(MERGE_INSERT ^(COLUMNS column_name*) expression_list where_clause?) 
    ->   template() "not implemented: "
    ;

selected_tableview
    :    ^(SELECTED_TABLEVIEW alias? (tableview_name|subquery))
    ->   template() "not implemented: "
    ;

// $>

lock_table_statement
    :    ^(PLSQL_RESERVED_LOCK lock_table_element+ lock_mode wait_nowait_part?)
    ->   template() "not implemented: "
    ;

wait_nowait_part
    :    ^(WAIT_VK expression)
    ->   template() "not implemented: "
    |    PLSQL_RESERVED_NOWAIT
    ->   template() "not implemented: "
    ;

// $<Lock - Specific Clauses

lock_table_element
    :    ^(LOCK_TABLE_ELEMENT tableview_name partition_extension_clause?)
    ->   template() "not implemented: "
    ;

lock_mode
    :    ROW_VK PLSQL_RESERVED_SHARE
    ->   template() "not implemented: "
    |    ROW_VK PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: "
    |    PLSQL_RESERVED_SHARE SQL92_RESERVED_UPDATE?
    ->   template() "not implemented: "
    |    PLSQL_RESERVED_SHARE ROW_VK PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: "
    |    PLSQL_RESERVED_EXCLUSIVE
    ->   template() "not implemented: "
    ;
// $>

// $<Common DDL Clauses

general_table_ref
    :    ^(TABLE_REF alias? dml_table_expression_clause ONLY_VK?)
    ->   template() "not implemented: "
    ;

static_returning_clause
    :    ^(STATIC_RETURNING expression+ into_clause)
    ->   template() "not implemented: "
    ;

error_logging_clause
    :    ^(LOG_VK error_logging_into_part? expression? error_logging_reject_part?)
    ->   template() "not implemented: "
    ;

error_logging_into_part
    :    ^(SQL92_RESERVED_INTO tableview_name)
    ->   template() "not implemented: "
    ;

error_logging_reject_part
    :    ^(REJECT_VK (UNLIMITED_VK|expression))
    ->   template() "not implemented: "
    ;

dml_table_expression_clause
    :    ^(TABLE_EXPRESSION 
        (    ^(COLLECTION_MODE expression PLUS_SIGN?)
        |    ^(SELECT_MODE select_statement subquery_restriction_clause?)
        |    ^(DIRECT_MODE tableview_name sample_clause?)
        |    table_ref
        |    general_element
        )
        )
    ->   template() "not implemented: "
        |    table_ref
    ->   template() "not implemented: "
    ;

subquery_restriction_clause
    :    ^(SQL92_RESERVED_WITH (READ_VK|SQL92_RESERVED_CHECK constraint_name?))
    ->   template() "not implemented: "
    ;

sample_clause
    :    ^(SAMPLE_VK BLOCK_VK? expression seed_part?) 
    ->   template() "not implemented: "
    ;

seed_part
    :    ^(SEED_VK expression)
    ->   template() "not implemented: "
    ;

// $>

// $>

// $<Cursor Manipulation SQL PL/SQL Statements

cursor_manipulation_statements
    :    close_statement
    ->   template() "not implemented: "
    |    open_statement
    ->   template() "not implemented: "
    |    fetch_statement
    ->   template() "not implemented: "
    |    open_for_statement
    ->   template() "not implemented: "
    ;

close_statement
    :     ^(CLOSE_VK variable_name) 
    ->   template() "not implemented: "
    ;

open_statement
    :    ^(OPEN_VK cursor_name expression_list?)
    ->   template() "not implemented: "
    ;

fetch_statement
    :    ^(SQL92_RESERVED_FETCH cursor_name 
            (    ^(SQL92_RESERVED_INTO variable_name+)
            |    ^(BULK_VK variable_name+)
            )
        )
    ->   template() "not implemented: "
    ;

open_for_statement
    :    ^(OPEN_VK variable_name (expression|select_statement) using_clause?)
    ->   template() "not implemented: "
    ;

// $>

// $<Transaction Control SQL PL/SQL Statements

transaction_control_statements
    :    set_transaction_command
    ->   template() "not implemented: "
    |    set_constraint_command
    ->   template() "not implemented: "
    |    commit_statement
    ->   template() "not implemented: "
    |    rollback_statement
    ->   template() "not implemented: "
    |    savepoint_statement
    ->   template() "not implemented: "
    ;

set_transaction_command
    :    ^(SET_TRANSACTION CHAR_STRING?
            (    ^(READ_VK (ONLY_VK|WRITE_VK))
            |    ^(ISOLATION_VK (SERIALIZABLE_VK|COMMITTED_VK))
            |    ^(ROLLBACK_VK rollback_segment_name)
            )?
        )
    ->   template() "not implemented: "
    ;

set_constraint_command
    :    ^(SET_CONSTRAINT (SQL92_RESERVED_ALL|constraint_name+) (IMMEDIATE_VK|DEFERRED_VK))
    ->   template() "not implemented: "
    ;

commit_statement
    :     ^(COMMIT_VK WORK_VK? 
            (    ^(COMMENT_VK expression)
            |    ^(FORCE_VK (CORRUPT_XID_VK expression|CORRUPT_XID_ALL_VK|expression expression?))
            )?
            write_clause?
        )
    ->   template() "not implemented: "
    ;

write_clause
    :    ^(WRITE_VK (WAIT_VK|PLSQL_RESERVED_NOWAIT)? (IMMEDIATE_VK|BATCH_VK)?)
    ->   template() "not implemented: "
    ;

rollback_statement
    :     ^(ROLLBACK_VK WORK_VK? 
            (    ^(SQL92_RESERVED_TO savepoint_name)
            |    ^(FORCE_VK CHAR_STRING)
            )?
        )
    ->   template() "not implemented: "
    ;

savepoint_statement
    :    ^(SAVEPOINT_VK savepoint_name) 
    ->   template() "not implemented: "
    ;
    
pipe_row_statement
    :    ^(PIPE_ROW expression)
    ->   template() "not implemented: "
    ;

// $>

// $<Expression & Condition

expression_list
    :    ^(EXPR_LIST expression*)
    ->   template() "not implemented: "
    ;

expression
    :    ^(LOGIC_EXPR expression_element)
    ->   template() "not implemented: "
    |    ^(EXPR expression_element)
    ->   template() "not implemented: "
    ;

expression_element
    :    ^(SQL92_RESERVED_OR expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_AND expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_NOT expression_element)
    ->   template() "not implemented: "
    |    ^((EQUALS_OP|NOT_EQUAL_OP|LESS_THAN_OP|GREATER_THAN_OP|LESS_THAN_OR_EQUALS_OP|GREATER_THAN_OR_EQUALS_OP) expression_element expression_element)

    ->   template() "not implemented: "
    |    ^(IS_NOT_NULL expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NULL expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_NAN expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NAN expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_PRESENT expression_element)
    ->   template() "not implemented: "
    |    ^(IS_PRESENT expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_INFINITE expression_element)
    ->   template() "not implemented: "
    |    ^(IS_INFINITE expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_A_SET expression_element)
    ->   template() "not implemented: "
    |    ^(IS_A_SET expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_EMPTY expression_element)
    ->   template() "not implemented: "
    |    ^(IS_EMPTY expression_element)
    ->   template() "not implemented: "
    |    ^(IS_NOT_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: "
    |    ^(IS_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: "

    |    ^((MEMBER_VK|SUBMULTISET_VK) expression_element expression_element)
    ->   template() "not implemented: "

    |    ^(NOT_IN expression_element in_elements)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_IN expression_element in_elements)
    ->   template() "not implemented: "
    |    ^(NOT_BETWEEN expression_element expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_BETWEEN expression_element expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(NOT_LIKE expression_element expression_element expression_element?)
    ->   template() "not implemented: "
    |    ^((SQL92_RESERVED_LIKE|LIKEC_VK|LIKE2_VK|LIKE4_VK) expression_element expression_element expression_element?)
    ->   template() "not implemented: "

    |    ^(CONCATENATION_OP expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(PLUS_SIGN expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(MINUS_SIGN expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(ASTERISK expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(SOLIDUS expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(MOD_VK expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(DIV_VK expression_element expression_element)
    ->   template() "not implemented: "
    |    ^(PIPE_VK expression_element expression_element)
    ->   template() "not implemented: "

    |    ^(UNARY_OPERATOR expression_element)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_PRIOR expression_element)
    ->   template() "not implemented: "
    |    ^(NEW_VK expression)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_DISTINCT expression_element)
    ->   template() "not implemented: "
    |    ^(STANDARD_FUNCTION standard_function)
    ->   template() "not implemented: "
    |    ^((SOME_VK|SQL92_RESERVED_EXISTS|SQL92_RESERVED_ALL|SQL92_RESERVED_ANY) expression_element)
    ->   template() "not implemented: "
    |    ^(VECTOR_EXPR expression_element+)
    ->   template() "not implemented: "

    |    ^(DATETIME_OP expression_element datetime_element)
    ->   template() "not implemented: "
    |    model_expression
    ->   template() "not implemented: "
    |    ^(KEEP_VK expression_element DENSE_RANK_VK (FIRST_VK|LAST_VK) order_by_clause over_clause?)
    ->   template() "not implemented: "

    |    ^(DOT_ASTERISK tableview_name)
    ->   template() "not implemented: "
    |    ^((PERCENT_FOUND_VK|PERCENT_NOTFOUND_VK|PERCENT_ROWCOUNT_VK|PERCENT_ISOPEN_VK) cursor_name)
    ->   template() "not implemented: "
    |    ^(OUTER_JOIN_SIGN expression_element)
    ->   template() "not implemented: "

    |    case_statement
    ->   template() "not implemented: "
    |    constant
    ->   template() "not implemented: "
    |    general_element
    ->   template() "not implemented: "
    |    subquery
    ->   template() "not implemented: "
    ;

in_elements
    :    subquery
    ->   template() "not implemented: "
    |    expression_list
    ->   template() "not implemented: "
    ;

datetime_element
    :    ^(AT_VK expression_element (LOCAL_VK|TIME_VK expression))
    ->   template() "not implemented: "
    |    ^(DAY_VK SECOND_VK expression*)
    ->   template() "not implemented: "
    |    ^(YEAR_VK MONTH_VK expression)
    ->   template() "not implemented: "
    ;

model_expression
    :    ^(MODEL_EXPRESSION expression_element model_expression_element+)
    ->   template() "not implemented: "
    ;

model_expression_element
    :    SQL92_RESERVED_ANY
    ->   template() "not implemented: "
    |    expression
    ->   template() "not implemented: "
    |    ^(FOR_SINGLE_COLUMN column_name for_single_column_element for_like_part?)
    ->   template() "not implemented: "
    |    ^(FOR_MULTI_COLUMN column_name+ ^(SQL92_RESERVED_IN (subquery|expression_list+)))
    ->   template() "not implemented: "
    ;

for_single_column_element
    :    ^(SQL92_RESERVED_IN expression_list)
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_FROM expression) 
    ->   template() "not implemented: "
    |    ^(SQL92_RESERVED_TO expression) 
    ->   template() "not implemented: "
    |    ^((INCREMENT_VK|DECREMENT_VK) expression) 
    ->   template() "not implemented: "
    ;

for_like_part
    :    ^(SQL92_RESERVED_LIKE expression)
    ->   template() "not implemented: "
    ;

case_statement
    :    ^(SIMPLE_CASE label_name* expression case_when_part+ case_else_part?)  
    ->   template() "not implemented: "
    |    ^(SEARCHED_CASE label_name* case_when_part+ case_else_part?) 
    ->   template() "not implemented: "
    ;

// $<CASE - Specific Clauses

case_when_part
    :    ^(SQL92_RESERVED_WHEN expression (seq_of_statements|expression))
    ->   template() "not implemented: "
    ;

case_else_part
    :    ^(SQL92_RESERVED_ELSE (seq_of_statements|expression))
    ->   template() "not implemented: "
    ;
// $>

standard_function
    :    ^(FUNCTION_ENABLING_OVER function_argument over_clause?)
    ->   template() "not implemented: "
    |    ^(FUNCTION_ENABLING_USING function_argument using_clause?)
    ->   template() "not implemented: "
    |    ^(COUNT_VK ( ASTERISK | expression ) over_clause?)
    ->   template() "not implemented: "
    |    ^((CAST_VK|XMLCAST_VK) (subquery|expression) type_spec)
    ->   template() "not implemented: "
    |    ^(CHR_VK expression NCHAR_CS_VK)
    ->   template() "not implemented: "
    |    ^(COLLECT_VK (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE)? column_name collect_order_by_part?)
    ->   template() "not implemented: "
    |    ^(FUNCTION_ENABLING_WITHIN_OR_OVER function_argument (within_clause|over_clause)+ )
    ->   template() "not implemented: "
    |    ^(DECOMPOSE_VK expression (CANONICAL_VK|COMPATIBILITY_VK)?) 
    ->   template() "not implemented: "
    |    ^(EXTRACT_VK REGULAR_ID expression)
    ->   template() "not implemented: "
    |    ^((FIRST_VALUE_VK|LAST_VALUE_VK) expression NULLS_VK? over_clause) 
    ->   template() "not implemented: "
    |    ^(PREDICTION_FUNCTION expression+ cost_matrix_clause? using_clause?)
    ->   template() "not implemented: "
    |    ^(TRANSLATE_VK expression (CHAR_CS_VK|NCHAR_CS_VK)? expression*)
    ->   template() "not implemented: "
    |    ^(TREAT_VK expression REF_VK? type_spec)
    ->   template() "not implemented: "
    |    ^(TRIM_VK (LEADING_VK|TRAILING_VK|BOTH_VK)? expression expression?) 
    ->   template() "not implemented: "

    |    ^(XMLAGG_VK expression order_by_clause?)
    ->   template() "not implemented: "
    |    ^((XMLCOLATTVAL_VK|XMLFOREST_VK) xml_multiuse_expression_element+)
    ->   template() "not implemented: "
    |    ^(XMLEXISTS_VK expression xml_passing_clause?)
    ->   template() "not implemented: "
    |    ^(XMLPARSE_VK (DOCUMENT_VK|CONTENT_VK) expression WELLFORMED_VK?)
    ->   template() "not implemented: "
    |    ^(XMLQUERY_VK expression xml_passing_clause? SQL92_RESERVED_NULL?)
    ->   template() "not implemented: "
    |    ^(XMLROOT_VK expression xml_param_version_part xmlroot_param_standalone_part?)
    ->   template() "not implemented: "
    |    ^(XMLTABLE_VK xml_namespaces_clause? expression xml_passing_clause? xml_table_column*)
    ->   template() "not implemented: "
    |    ^(XMLELEMENT_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (NAME_VK|EVALNAME_VK)? expression
            xml_attributes_clause? (expression alias?)*
        )
    ->   template() "not implemented: "
    |    ^(XMLPI_VK
                (    NAME_VK char_set_name? ID
                |    EVALNAME_VK expression
                )
                expression?
        )
    ->   template() "not implemented: "
    |    ^(XMLSERIALIZE_VK
                (DOCUMENT_VK|CONTENT_VK)
                expression type_spec?
                xmlserialize_param_enconding_part?
                xml_param_version_part?
                xmlserialize_param_ident_part?
                ((HIDE_VK|SHOW_VK) DEFAULTS_VK)?
        )
    ->   template() "not implemented: "
    ;

over_clause
    :    ^(OVER_VK query_partition_clause? (order_by_clause windowing_clause?)?)
    ->   template() "not implemented: "
    ;

windowing_clause
    :    ^((ROWS_VK|RANGE_VK)
            (    ^(SQL92_RESERVED_BETWEEN windowing_elements windowing_elements)
            |    windowing_elements+
            )
        )
    ->   template() "not implemented: "
    ;

windowing_elements
    :    ^(UNBOUNDED_VK PRECEDING_VK)
    ->   template() "not implemented: "
    |    ^(CURRENT_VK ROW_VK)
    ->   template() "not implemented: "
    |    ^((PRECEDING_VK|FOLLOWING_VK) expression)
    ->   template() "not implemented: "
    ;

using_clause
    :    ^(PLSQL_NON_RESERVED_USING using_element+)
    ->   template() "not implemented: "
    ;

using_element
    :    ^(ELEMENT SQL92_RESERVED_IN? OUT_VK? expression alias?)
    ->   template() "not implemented: "
    |    ASTERISK
    ->   template() "not implemented: "
    ;

collect_order_by_part
    :    ^(SQL92_RESERVED_ORDER expression)
    ->   template() "not implemented: "
    ;

within_clause
    :    ^(WITHIN_VK order_by_clause)
    ->   template() "not implemented: "
    ;

cost_matrix_clause
    :    ^(COST_VK
            (    PLSQL_NON_RESERVED_MODEL AUTO_VK?
            |    cost_class_name+ expression_list
            )
        )
    ->   template() "not implemented: "
    ;

xml_passing_clause
    :    ^(PASSING_VK VALUE_VK? expression alias? (expression alias?)?)
    ->   template() "not implemented: "
    ;

xml_attributes_clause
    :    ^(XMLATTRIBUTES_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (SCHEMACHECK_VK|NOSCHEMACHECK_VK)?
            xml_multiuse_expression_element+
        )
    ->   template() "not implemented: "
    ;

xml_namespaces_clause
    :    ^(XMLNAMESPACES_VK
            (expression alias?)* xml_general_default_part?
        )
    ->   template() "not implemented: "
    ;

xml_table_column
    :    ^(XML_COLUMN xml_column_name (ORDINALITY_VK|type_spec expression? xml_general_default_part?) )
    ->   template() "not implemented: "
    ;

xml_general_default_part
    :    ^(SQL92_RESERVED_DEFAULT expression)
    ->   template() "not implemented: "
    ;

xml_multiuse_expression_element
    :    ^(XML_ELEMENT expression xml_alias?)
    ->   template() "not implemented: "
    ;

xml_alias
    :    ^(XML_ALIAS ID)
    ->   template() "not implemented: "
    |    ^(XML_ALIAS ^(EVALNAME_VK expression))
    ->   template() "not implemented: "
    ;

xml_param_version_part
    :    ^(VERSION_VK (NO_VK VALUE_VK|expression))
    ->   template() "not implemented: "
    ;

xmlroot_param_standalone_part
    :    ^(STANDALONE_VK (YES_VK|NO_VK VALUE_VK?))
    ->   template() "not implemented: "
    ;

xmlserialize_param_enconding_part
    :    ^(ENCODING_VK expression)
    ->   template() "not implemented: "
    ;

xmlserialize_param_ident_part
    :    NO_VK INDENT_VK
    ->   template() "not implemented: "
    |    ^(INDENT_VK expression?)
    ->   template() "not implemented: "
    ;

// $>

// $<Common SQL PL/SQL Clauses/Parts

partition_extension_clause
    :    ^((SUBPARTITION_VK|PARTITION_VK) expression_list)
    ->   template() "not implemented: "
    ;

alias
    :    ^(ALIAS char_set_name? ID)
    ->   template() "not implemented: "
    ;

where_clause
    :    ^(SQL92_RESERVED_WHERE expression)
    ->   template() "not implemented: "
    ;

into_clause
    :    ^(SQL92_RESERVED_INTO general_element+) 
    ->   template() "not implemented: "
    |    ^(BULK_VK general_element+) 
    ->   template() "not implemented: "
    ;

// $>

// $<Common PL/SQL Named Elements

xml_column_name
    :    ^(XML_COLUMN_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

cost_class_name
    :    ^(COST_CLASS_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

attribute_name
    :    ^(ATTRIBUTE_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

savepoint_name
    :    ^(SAVEPOINT_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

rollback_segment_name
    :    ^(ROLLBACK_SEGMENT_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;


table_var_name
    :    ^(TABLE_VAR_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

schema_name
    :    ^(SCHEMA_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

routine_name
    :    ^(ROUTINE_NAME char_set_name? ID+ link_name?)
    ->   template() "not implemented: "
    ;

package_name
    :    ^(PACKAGE_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

implementation_type_name
    :    ^(IMPLEMENTATION_TYPE_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

parameter_name
    :    ^(PARAMETER_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

reference_model_name
    :    ^(REFERENCE_MODEL_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

main_model_name
    :    ^(MAIN_MODEL_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

query_name
    :    ^(QUERY_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

constraint_name
    :    ^(CONSTRAINT_NAME char_set_name? ID+ link_name?)
    ->   template() "not implemented: "
    ;

label_name
    :    ^(LABEL_NAME ID)
    ->   template() "not implemented: "
    ;

type_name
    :    ^(TYPE_NAME ID+)
    ->   template() "not implemented: "
    ;

sequence_name
    :    ^(SEQUENCE_NAME ID+)
    ->   template() "not implemented: "
    ;

exception_name
    :    ^(EXCEPTION_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

function_name
    :    ^(FUNCTION_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

procedure_name
    :    ^(PROCEDURE_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

trigger_name
    :    ^(TRIGGER_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

variable_name
    :    ^(HOSTED_VARIABLE_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    |    ^(VARIABLE_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

index_name
    :    ^(INDEX_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

cursor_name
    :    ^(CURSOR_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

record_name
    :    ^(RECORD_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

collection_name
    :    ^(COLLECTION_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

link_name
    :    ^(LINK_NAME char_set_name? ID)
    ->   template() "not implemented: "
    ;

column_name
    :    ^(COLUMN_NAME char_set_name? ID+)
    ->   template() "not implemented: "
    ;

tableview_name
    :    ^(TABLEVIEW_NAME char_set_name? ID+ link_name? partition_extension_clause?)
    ->   template() "not implemented: "
    ;

char_set_name
    :    ^(CHAR_SET_NAME ID+)
    ->   template() "not implemented: "
    ;

// $>

// $<Common PL/SQL Specs

function_argument
    :    ^(ARGUMENTS argument*)
    ->   template() "not implemented: "
    ;

argument
    :    ^(ARGUMENT expression parameter_name?)
    ->   template() "not implemented: "
    ;

type_spec
    :     ^(CUSTOM_TYPE type_name REF_VK? (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK)?)
    ->   template() "not implemented: "
    |    ^(NATIVE_DATATYPE native_datatype_element type_precision? (TIME_VK LOCAL_VK?)?)
    ->   template() "not implemented: "
    |    ^(INTERVAL_DATATYPE (YEAR_VK|DAY_VK) (MONTH_VK|SECOND_VK) expression*)
    ->   template() "not implemented: "
    ;

type_precision
    :    ^(PRECISION constant constant? (CHAR_VK|BYTE_VK)? (TIME_VK LOCAL_VK?)?)
    ->   template() "not implemented: "
    ;

native_datatype_element
    :    BINARY_INTEGER_VK
    ->   template() "not implemented: "
    |    PLS_INTEGER_VK
    ->   template() "not implemented: "
    |    NATURAL_VK
    ->   template() "not implemented: "
    |    BINARY_FLOAT_VK
    ->   template() "not implemented: "
    |    BINARY_DOUBLE_VK
    ->   template() "not implemented: "
    |    NATURALN_VK
    ->   template() "not implemented: "
    |    POSITIVE_VK
    ->   template() "not implemented: "
    |    POSITIVEN_VK
    ->   template() "not implemented: "
    |    SIGNTYPE_VK
    ->   template() "not implemented: "
    |    SIMPLE_INTEGER_VK
    ->   template() "not implemented: "
    |    NVARCHAR2_VK
    ->   template() "not implemented: "
    |    DEC_VK
    ->   template() "not implemented: "
    |    INTEGER_VK
    ->   template() "not implemented: "
    |    INT_VK
    ->   template() "not implemented: "
    |    NUMERIC_VK
    ->   template() "not implemented: "
    |    SMALLINT_VK
    ->   template() "not implemented: "
    |    NUMBER_VK
    ->   template() "not implemented: "
    |    DECIMAL_VK 
    ->   template() "not implemented: "
    |    DOUBLE_VK PRECISION_VK?
    ->   template() "not implemented: "
    |    FLOAT_VK
    ->   template() "not implemented: "
    |    REAL_VK
    ->   template() "not implemented: "
    |    NCHAR_VK
    ->   template() "not implemented: "
    |    LONG_VK RAW_VK?
    ->   template() "not implemented: "
    |    CHAR_VK  
    ->   template() "not implemented: "
    |    CHARACTER_VK 
    ->   template() "not implemented: "
    |    VARCHAR2_VK
    ->   template() "not implemented: "
    |    VARCHAR_VK
    ->   template() "not implemented: "
    |    STRING_VK
    ->   template() "not implemented: "
    |    RAW_VK
    ->   template() "not implemented: "
    |    BOOLEAN_VK
    ->   template() "not implemented: "
    |    DATE_VK
    ->   template() "not implemented: "
    |    ROWID_VK
    ->   template() "not implemented: "
    |    UROWID_VK
    ->   template() "not implemented: "
    |    YEAR_VK
    ->   template() "not implemented: "
    |    MONTH_VK
    ->   template() "not implemented: "
    |    DAY_VK
    ->   template() "not implemented: "
    |    HOUR_VK
    ->   template() "not implemented: "
    |    MINUTE_VK
    ->   template() "not implemented: "
    |    SECOND_VK
    ->   template() "not implemented: "
    |    TIMEZONE_HOUR_VK
    ->   template() "not implemented: "
    |    TIMEZONE_MINUTE_VK
    ->   template() "not implemented: "
    |    TIMEZONE_REGION_VK
    ->   template() "not implemented: "
    |    TIMEZONE_ABBR_VK
    ->   template() "not implemented: "
    |    TIMESTAMP_VK
    ->   template() "not implemented: "
    |    TIMESTAMP_UNCONSTRAINED_VK
    ->   template() "not implemented: "
    |    TIMESTAMP_TZ_UNCONSTRAINED_VK
    ->   template() "not implemented: "
    |    TIMESTAMP_LTZ_UNCONSTRAINED_VK
    ->   template() "not implemented: "
    |    YMINTERVAL_UNCONSTRAINED_VK
    ->   template() "not implemented: "
    |    DSINTERVAL_UNCONSTRAINED_VK
    ->   template() "not implemented: "
    |    BFILE_VK
    ->   template() "not implemented: "
    |    BLOB_VK
    ->   template() "not implemented: "
    |    CLOB_VK
    ->   template() "not implemented: "
    |    NCLOB_VK
    ->   template() "not implemented: "
    |    MLSLABEL_VK
    ->   template() "not implemented: "
    ;

general_element
    :    ^(CASCATED_ELEMENT general_element+)
    ->   template() "not implemented: "
    |    ^(HOSTED_VARIABLE_ROUTINE_CALL routine_name function_argument)
    ->   template() "not implemented: "
    |    ^(HOSTED_VARIABLE char_set_name? ID+)
    ->   template() "not implemented: "
    |    ^(ROUTINE_CALL routine_name function_argument)
    ->   template() "not implemented: "
    |    ^(ANY_ELEMENT char_set_name? ID+)
    ->   template() "not implemented: "
    ;

// $>

// $<Lexer Mappings

constant
    :    UNSIGNED_INTEGER
    ->   template() "not implemented: "
    |    ^(MINUS_SIGN UNSIGNED_INTEGER)
    ->   template() "not implemented: "
    |    EXACT_NUM_LIT
    ->   template() "not implemented: "
    |    APPROXIMATE_NUM_LIT
    ->   template() "not implemented: "
    |    CHAR_STRING
    ->   template() "not implemented: "
    |    SQL92_RESERVED_NULL
    ->   template() "not implemented: "
    |    SQL92_RESERVED_TRUE
    ->   template() "not implemented: "
    |    SQL92_RESERVED_FALSE
    ->   template() "not implemented: "
    |    DBTIMEZONE_VK 
    ->   template() "not implemented: "
    |    SESSIONTIMEZONE_VK
    ->   template() "not implemented: "
    |    MINVALUE_VK
    ->   template() "not implemented: "
    |    MAXVALUE_VK
    ->   template() "not implemented: "
    |    SQL92_RESERVED_DEFAULT
    ->   template() "not implemented: "
    ;

// $>

id
    :    char_set_name? ID
    ->   template() "not implemented: "
    ;

    
