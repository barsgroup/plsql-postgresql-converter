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
    ->   template() "not implemented: serveroutput_declaration"
    ;

unit_statement
    :    alter_function
    ->   template() "not implemented: unit_statement"
    |    alter_package
    ->   template() "not implemented: unit_statement"
    |    alter_procedure
    ->   template() "not implemented: unit_statement"
    |    alter_sequence
    ->   template() "not implemented: unit_statement"
    |    alter_trigger
    ->   template() "not implemented: unit_statement"
    |    alter_type
    ->   template() "not implemented: unit_statement"
    |    create_function_body { $unit_statement.st = $create_function_body.st; }
    |    create_procedure_body
    ->   template() "not implemented: unit_statement"
    |    create_package
    ->   template() "not implemented: unit_statement"
    |    create_sequence
    ->   template() "not implemented: unit_statement"
    |    create_trigger
    ->   template() "not implemented: unit_statement"
    |    create_type
    ->   template() "not implemented: unit_statement"
    |    drop_function
    ->   template() "not implemented: unit_statement"
    |    drop_package
    ->   template() "not implemented: unit_statement"
    |    drop_procedure
    ->   template() "not implemented: unit_statement"
    |    drop_sequence
    ->   template() "not implemented: unit_statement"
    |    drop_trigger
    ->   template() "not implemented: unit_statement"
    |    drop_type
    ->   template() "not implemented: unit_statement"
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
    :    ^(CREATE_FUNCTION REPLACE_VK? ^(FUNCTION_NAME name+=ID+) ret=type_spec ^(PARAMETERS args+=parameter*)
            ac+=invoker_rights_clause* ac+=parallel_enable_clause* ac+=result_cache_clause* DETERMINISTIC_VK*
            (    ^(USING_MODE PIPELINED_VK? AGGREGATE_VK? implementation_type_name)
    ->   template() "not implemented: create_function_body"
            |    ^(CALL_MODE PIPELINED_VK? call_spec)
    ->   template() "not implemented: create_function_body"
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
    :    ^(CREATE_PACKAGE_SPEC REPLACE_VK? package_name invoker_rights_clause? package_obj_spec*) 
    ->   template() "not implemented: create_package"
    |    ^(CREATE_PACKAGE_BODY REPLACE_VK? package_name package_obj_body* seq_of_statements?)
    ->   template() "not implemented: create_package"
    ;

// $<Create Package - Specific Clauses

package_obj_spec
    :    variable_declaration
    ->   template() "not implemented: package_obj_spec"
    |     subtype_declaration
    ->   template() "not implemented: package_obj_spec"
    |     cursor_declaration
    ->   template() "not implemented: package_obj_spec"
    |     exception_declaration
    ->   template() "not implemented: package_obj_spec"
    |     record_declaration
    ->   template() "not implemented: package_obj_spec"
    |     table_declaration
    ->   template() "not implemented: package_obj_spec"
    |     procedure_spec
    ->   template() "not implemented: package_obj_spec"
    |     function_spec
    ->   template() "not implemented: package_obj_spec"
    |     pragma_declaration
    ->   template() "not implemented: package_obj_spec"
    ;

procedure_spec
    :     ^(PROCEDURE_SPEC procedure_name ^(PARAMETERS parameter*)
            (^(CALL_MODE call_spec))?
    ) 
    ->   template() "not implemented: procedure_spec"
    ;

function_spec
    :    ^(FUNCTION_SPEC function_name (type_spec|SELF_VK) ^(PARAMETERS parameter*)
            invoker_rights_clause* parallel_enable_clause* result_cache_clause* DETERMINISTIC_VK* PIPELINED_VK?
            (    ^(CALL_MODE call_spec)
            |    ^(USING_MODE AGGREGATE_VK? implementation_type_name)
            |    ^(EXTERNAL_VK expression)
            )?
        )
    ->   template() "not implemented: function_spec"
    ;

package_obj_body
    :     variable_declaration 
    ->   template() "not implemented: package_obj_body"
    |     subtype_declaration 
    ->   template() "not implemented: package_obj_body"
    |     cursor_declaration 
    ->   template() "not implemented: package_obj_body"
    |     exception_declaration 
    ->   template() "not implemented: package_obj_body"
    |     record_declaration
    ->   template() "not implemented: package_obj_body"
    |     table_declaration
    ->   template() "not implemented: package_obj_body"
    |     create_procedure_body
    ->   template() "not implemented: package_obj_body"
    |     create_function_body
    ->   template() "not implemented: package_obj_body"
    |     create_type
    ->   template() "not implemented: package_obj_body"
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
    :    ^(CREATE_PROCEDURE REPLACE_VK? procedure_name ^(PARAMETERS parameter*) invoker_rights_clause?
            (    EXTERNAL_VK
            |    ^(CALL_MODE call_spec)
            |    ^(BODY_MODE declare_spec* body)
            )
        )
    ->   template() "not implemented: create_procedure_body"
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
    :    ^(CREATE_TYPE_BODY REPLACE_VK? type_name ^(TYPE_BODY_ELEMENTS type_body_elements+))
    ->   template() "not implemented: create_type"
    |    ^(CREATE_TYPE_SPEC REPLACE_VK? type_name CHAR_STRING? object_type_def?)
    ->   template() "not implemented: create_type"
    ;

object_type_def
    :    ^(OBJECT_TYPE_DEF (object_as_part|object_under_part) invoker_rights_clause?
             sqlj_object_type? modifier_clause* ^(OBJECT_MEMBERS element_spec*))  
    ->   template() "not implemented: object_type_def"
    ;

object_as_part
    :    ^(OBJECT_AS (OBJECT_VK|varray_type_def|nested_table_type_def))
    ->   template() "not implemented: object_as_part"
    ;

object_under_part
    :    ^(UNDER_VK type_spec)
    ->   template() "not implemented: object_under_part"
    ;

nested_table_type_def
    :    ^(NESTED_TABLE_TYPE_DEF type_spec SQL92_RESERVED_NULL? table_indexed_by_part?)
    ->   template() "not implemented: nested_table_type_def"
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
            |    ^(BODY_MODE declare_spec* body)
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
    |    ^(FIELD_SPEC id type_spec sqlj_object_type_attr?)
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
    :    id
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
    :    ^(START_VK UNSIGNED_INTEGER)
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
    |    ORDER_VK
    ->   template() "not implemented: sequence_spec"
    |    NOORDER_VK
    ->   template() "not implemented: sequence_spec"
    ;

// $>
// $>


// $<Common DDL Clauses

invoker_rights_clause
    :    ^(AUTHID_VK (CURRENT_USER_VK|DEFINER_VK))
    ->   template() "not implemented: invoker_rights_clause"
    ;

compiler_parameters_clause
    :    ^(COMPILER_PARAMETER ^(ASSIGN id expression))
    ->   template() "not implemented: compiler_parameters_clause"
    ;

call_spec
    :    ^(LANGUAGE_VK ( java_spec | c_spec ))
    ->   template() "not implemented: call_spec"
    ;

// $<Call Spec - Specific Clauses

java_spec
    :    ^(JAVA_VK CHAR_STRING)
    ->   template() "not implemented: java_spec"
    ;

c_spec
    :    ^(C_VK CHAR_STRING? CONTEXT_VK? ^(LIBRARY_VK id) c_agent_in_clause? c_parameters_clause?)
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
           is_out={$OUT_VK != null}, is_inout={$INOUT_VK != null}, is_nocopy={false}, type={$type_spec.st})
           // TODO: what to do with default_value_part?
           // TODO: can type_spec really be null?
    ;

default_value_part
    :    ^(DEFAULT_VALUE expression)
    ->   template() "not implemented: default_value_part"
    ;

// $>

// $>

// $<PL/SQL Elements Declarations

declare_spec
    :    variable_declaration
    ->   template() "not implemented: declare_spec"
    |     subtype_declaration
    ->   template() "not implemented: declare_spec"
    |     cursor_declaration
    ->   template() "not implemented: declare_spec"
    |     exception_declaration
    ->   template() "not implemented: declare_spec"
    |     pragma_declaration
    ->   template() "not implemented: declare_spec"
    |     record_declaration
    ->   template() "not implemented: declare_spec"
    |     table_declaration
    ->   template() "not implemented: declare_spec"
    |     create_procedure_body
    ->   template() "not implemented: declare_spec"
    |     create_function_body
    ->   template() "not implemented: declare_spec"
    ;

//incorporates constant_declaration
variable_declaration
    :    ^(VARIABLE_DECLARE variable_name type_spec CONSTANT_VK? SQL92_RESERVED_NULL? default_value_part?)
    ->   template() "not implemented: variable_declaration"
    ;    

subtype_declaration
      :    ^(SUBTYPE_DECLARE type_name type_spec SQL92_RESERVED_NULL? subtype_range?)
    ->   template() "not implemented: subtype_declaration"
      ;

subtype_range
    :    ^(RANGE_VK expression+)
    ->   template() "not implemented: subtype_range"
    ;

//cursor_declaration incorportates curscursor_body and cursor_spec
cursor_declaration
    :    ^(CURSOR_DECLARE cursor_name type_spec? select_statement? ^(PARAMETERS parameter_spec*)) 
    ->   template() "not implemented: cursor_declaration"
    ;

parameter_spec
    :    ^(PARAMETER parameter_name type_spec? default_value_part?)
    ->   template() "not implemented: parameter_spec"
    ;

exception_declaration 
    :    ^(EXCEPTION_DECLARE exception_name)
    ->   template() "not implemented: exception_declaration"
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
    ->   template() "not implemented: pragma_declaration"
    ;

record_declaration
    :    record_type_dec
    ->   template() "not implemented: record_declaration"
    |    record_var_dec
    ->   template() "not implemented: record_declaration"
    ;

// $<Record Declaration - Specific Clauses

//incorporates ref_cursor_type_definition
record_type_dec
    :    ^(RECORD_TYPE_DECLARE type_name REF_VK? type_spec? ^(FIELDS field_spec*))
    ->   template() "not implemented: record_type_dec"
    ;

field_spec
    :    ^(FIELD_SPEC column_name type_spec? SQL92_RESERVED_NULL? default_value_part?)
    ->   template() "not implemented: field_spec"
    ;

record_var_dec
    :    ^(RECORD_VAR_DECLARE record_name type_name (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK))
    ->   template() "not implemented: record_var_dec"
    ;

// $>

table_declaration
    :    table_type_dec
    ->   template() "not implemented: table_declaration"
    |    table_var_dec
    ->   template() "not implemented: table_declaration"
    ;

table_type_dec
    :    ^(TABLE_TYPE_DECLARE type_name 
            (    varray_type_def
            |    SQL92_RESERVED_NULL? ^(SQL92_RESERVED_TABLE type_spec table_indexed_by_part?)
            )
        )
    ->   template() "not implemented: table_type_dec"
    ;

table_indexed_by_part
    :    ^(INDEXED_BY type_spec)
    ->   template() "not implemented: table_indexed_by_part"
    ;

varray_type_def
    :    SQL92_RESERVED_NULL? ^(VARR_ARRAY_DEF expression type_spec)
    ->   template() "not implemented: varray_type_def"
    ;

table_var_dec
    :    ^(TABLE_VAR_DECLARE table_var_name type_spec)
    ->   template() "not implemented: table_var_dec"
    ;

// $>

// $<PL/SQL Statements

seq_of_statements
    :     ^(STATEMENTS statements+=statement+)
    -> seq_of_statements(statements={$statements})
    ;

statement
    :    label_declaration -> { $label_declaration.st }
    |    assignment_statement -> { $assignment_statement.st }
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
    |    function_call -> { $function_call.st }
    |    body -> { $body.st }
    |    block -> { $block.st }
    ;

label_declaration
    :    ^(LABEL_DECLARE label_name)
    ->   template() "not implemented: label_declaration"
    ;

assignment_statement
    :     ^(ASSIGN general_element expression)
    ->   template() "not implemented: assignment_statement"
    ;

continue_statement
    :    ^(CONTINUE_VK label_name? general_when?)
    ->   template() "not implemented: continue_statement"
    ;

general_when
    :    ^(SQL92_RESERVED_WHEN expression)
    ->   template() "not implemented: general_when"
    ;

exit_statement
    :    ^(EXIT_VK label_name? general_when?)
    ->   template() "not implemented: exit_statement"
    ;

goto_statement
    :    ^(SQL92_RESERVED_GOTO label_name)
    ->   template() "not implemented: goto_statement"
    ;

if_statement
    :    ^(PLSQL_RESERVED_IF expression seq_of_statements elsif_part* else_part?)
    ->   template() "not implemented: if_statement"
    ;

elsif_part
    :    ^(PLSQL_NON_RESERVED_ELSIF expression seq_of_statements)
    ->   template() "not implemented: elsif_part"
    ;

else_part
    :    ^(SQL92_RESERVED_ELSE seq_of_statements)
    ->   template() "not implemented: else_part"
    ;

loop_statement
    :    ^(WHILE_LOOP label_name* expression seq_of_statements)
    ->   template() "not implemented: loop_statement"
    |    ^(FOR_LOOP label_name* cursor_loop_param seq_of_statements)
    ->   template() "not implemented: loop_statement"
    |    ^(LOOP_VK label_name* seq_of_statements)
    ->   template() "not implemented: loop_statement"
    ;

// $<Loop - Specific Clause

cursor_loop_param
    :    ^(INDEXED_FOR index_name REVERSE_VK? ^(SIMPLE_BOUND expression expression))
    ->   template() "not implemented: cursor_loop_param"
    |    ^(CURSOR_BASED_FOR record_name cursor_name expression_list?)
    ->   template() "not implemented: cursor_loop_param"
    |    ^(SELECT_BASED_FOR record_name select_statement)
    ->   template() "not implemented: cursor_loop_param"
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
    ->   template() "not implemented: null_statement"
    ;

raise_statement
    :    ^(RAISE_VK exception_name?)
    ->   template() "not implemented: raise_statement"
    ;

return_statement
    :    ^(RETURN_VK expression?)
    ->   template() "not implemented: return_statement"
    ;

function_call
    :    ^(ROUTINE_CALL general_element)
    ->   template() "not implemented: function_call"
    ;

body
    :    ^(BODY label_name? seq_of_statements exception_clause?) 
    ->   body(bodyLabel={$label_name.st}, statements={$seq_of_statements.st}, exception_clause={$exception_clause.st})
    ;

// $<Body - Specific Clause

exception_clause
    :    ^(SQL92_RESERVED_EXCEPTION exception_handler+)
    ->   template() "not implemented: exception_clause"
    ;

exception_handler
    :    ^(SQL92_RESERVED_WHEN exception_name+ seq_of_statements)
    ->   template() "not implemented: exception_handler"
    ;

// $>

block
    :    ^(BLOCK declare_spec* body)
    ->   template() "not implemented: block"
    ;

// $>

// $<SQL PL/SQL Statements

sql_statement
    :    execute_immediate
    ->   template() "not implemented: sql_statement"
    |    data_manipulation_language_statements
    ->   template() "not implemented: sql_statement"
    |    cursor_manipulation_statements
    ->   template() "not implemented: sql_statement"
    |    transaction_control_statements
    ->   template() "not implemented: sql_statement"
    ;

execute_immediate
    :    ^(EXECUTE_VK expression (into_clause|using_clause|dynamic_returning_clause)?) 
    ->   template() "not implemented: execute_immediate"
    ;

// $<Execute Immediate - Specific Clause
dynamic_returning_clause
    :    ^(DYNAMIC_RETURN into_clause)
    ->   template() "not implemented: dynamic_returning_clause"
    ;
// $>


// $<DML SQL PL/SQL Statements

data_manipulation_language_statements
    :    merge_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    |    lock_table_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    |    select_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    |     update_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    |     delete_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    |    insert_statement
    ->   template() "not implemented: data_manipulation_language_statements"
    ;

select_statement
    :    ^(SELECT_STATEMENT subquery_factoring_clause? subquery for_update_clause* order_by_clause*)  
    ->   template() "not implemented: select_statement"
    ;

// $<Select - Specific Clauses
subquery_factoring_clause
    :    ^(SQL92_RESERVED_WITH factoring_element+)
    ->   template() "not implemented: subquery_factoring_clause"
    ;

factoring_element
    :    ^(FACTORING query_name subquery)
    ->   template() "not implemented: factoring_element"
    ;

subquery
    :    ^(SUBQUERY subquery_basic_elements subquery_operation_part*)
    ->   template() "not implemented: subquery"
    ;

subquery_operation_part
    :    ^((SQL92_RESERVED_UNION|SQL92_RESERVED_INTERSECT|PLSQL_RESERVED_MINUS) SQL92_RESERVED_ALL? subquery_basic_elements)
    ->   template() "not implemented: subquery_operation_part"
    ;

subquery_basic_elements
    :    query_block
    ->   template() "not implemented: subquery_basic_elements"
    |    subquery
    ->   template() "not implemented: subquery_basic_elements"
    ;

query_block
    :    ^(SQL92_RESERVED_SELECT 
            from_clause 
            (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE)? SQL92_RESERVED_ALL? 
            (    ASTERISK
            |    ^(SELECT_LIST selected_element+)
            )
            into_clause? where_clause? hierarchical_query_clause? 
            group_by_clause? model_clause?
        )
    ->   template() "not implemented: query_block"
    ;

selected_element
    :    ^(SELECT_ITEM expression alias?)
    ->   template() "not implemented: selected_element"
    ;

from_clause
    :    ^(SQL92_RESERVED_FROM table_ref+)
    ->   template() "not implemented: from_clause"
    ;

table_ref
    :    ^(TABLE_REF table_ref_aux join_clause*)
    ->   template() "not implemented: table_ref"
    ;

table_ref_aux
    :    ^(TABLE_REF_ELEMENT alias? dml_table_expression_clause ONLY_VK? pivot_clause? unpivot_clause? flashback_query_clause*)
    ->   template() "not implemented: table_ref_aux"
    ;

join_clause
    :    ^(JOIN_DEF (CROSS_VK|NATURAL_VK)? (INNER_VK|FULL_VK|LEFT_VK|RIGHT_VK)? table_ref_aux query_partition_clause* (join_on_part|join_using_part)?) 
    ->   template() "not implemented: join_clause"
    ;

join_on_part
    :    ^(SQL92_RESERVED_ON expression) 
    ->   template() "not implemented: join_on_part"
    ;

join_using_part
    :    ^(PLSQL_NON_RESERVED_USING column_name+)
    ->   template() "not implemented: join_using_part"
    ;

query_partition_clause
    :    ^(PARTITION_VK (subquery|expression_list|expression+))
    ->   template() "not implemented: query_partition_clause"
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
    ->   template() "not implemented: hierarchical_query_clause"
    ;

start_part
    :    ^(PLSQL_RESERVED_START expression)
    ->   template() "not implemented: start_part"
    ;

group_by_clause
    :    ^(SQL92_RESERVED_GROUP group_by_element+ having_clause?)
    ->   template() "not implemented: group_by_clause"
    ;

group_by_element
    :    ^(GROUP_BY_ELEMENT group_by_elements)
    ->   template() "not implemented: group_by_element"
    ;

group_by_elements
    :    ^(GROUPING_VK groupin_set+)
    ->   template() "not implemented: group_by_elements"
    |    grouping_element 
    ->   template() "not implemented: group_by_elements"
    ;

groupin_set
    :    ^(GROUPIN_SET grouping_element)
    ->   template() "not implemented: groupin_set"
    ;

grouping_element
    :    ^((ROLLUP_VK|CUBE_VK) grouping_element+)
    ->   template() "not implemented: grouping_element"
    |    expression_list
    ->   template() "not implemented: grouping_element"
    |    expression 
    ->   template() "not implemented: grouping_element"
    ;

having_clause
    :    ^(SQL92_RESERVED_HAVING expression)
    ->   template() "not implemented: having_clause"
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
    :    ^(SQL92_RESERVED_ORDER SIBLINGS_VK? ^(ORDER_BY_ELEMENTS order_by_elements+))
    ->   template() "not implemented: order_by_clause"
    ;

order_by_elements
    :    ^(ORDER_BY_ELEMENT expression (SQL92_RESERVED_ASC|SQL92_RESERVED_DESC)? (NULLS_VK (FIRST_VK|LAST_VK))?)
    ->   template() "not implemented: order_by_elements"
    ;

for_update_clause
    :    ^(SQL92_RESERVED_FOR for_update_of_part? for_update_options?)
    ->   template() "not implemented: for_update_clause"
    ;

for_update_of_part
    :    ^(SQL92_RESERVED_OF column_name+)
    ->   template() "not implemented: for_update_of_part"
    ;

for_update_options
    :    SKIP_VK
    ->   template() "not implemented: for_update_options"
    |    PLSQL_RESERVED_NOWAIT
    ->   template() "not implemented: for_update_options"
    |    ^(WAIT_VK expression)
    ->   template() "not implemented: for_update_options"
    ;

// $>

update_statement
    :    ^(SQL92_RESERVED_UPDATE general_table_ref
            update_set_clause
            where_clause? static_returning_clause? error_logging_clause?
        )
    ->   template() "not implemented: update_statement"
    ;

// $<Update - Specific Clauses
update_set_clause
    :    ^(SET_VK update_set_elements+)
    ->   template() "not implemented: update_set_clause"
    ;

update_set_elements
    :    ^(ASSIGN column_name expression)
    ->   template() "not implemented: update_set_elements"
    |    ^(ASSIGN column_name+ subquery)
    ->   template() "not implemented: update_set_elements"
    |    ^(VALUE_VK char_set_name? ID expression)
    ->   template() "not implemented: update_set_elements"
    ;

// $>

delete_statement
    :    ^(SQL92_RESERVED_DELETE general_table_ref
            where_clause? static_returning_clause? error_logging_clause?)
    ->   template() "not implemented: delete_statement"
    ;

insert_statement
    :    ^(SQL92_RESERVED_INSERT
        (    single_table_insert
        |    multi_table_insert
        )
        )
    ->   template() "not implemented: insert_statement"
    ;

// $<Insert - Specific Clauses

single_table_insert
    :    ^(SINGLE_TABLE_MODE insert_into_clause (values_clause static_returning_clause?| select_statement) error_logging_clause?)
    ->   template() "not implemented: single_table_insert"
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
    :    ^(SQL92_RESERVED_INTO general_table_ref ^(COLUMNS column_name*))
    ->   template() "not implemented: insert_into_clause"
    ;

values_clause
    :    ^(SQL92_RESERVED_VALUES (expression_list|expression))
    ->   template() "not implemented: values_clause"
    ;

// $>
merge_statement
    :    ^(MERGE_VK alias? tableview_name 
            ^(PLSQL_NON_RESERVED_USING selected_tableview expression)
             merge_update_clause? merge_insert_clause? error_logging_clause?)
    ->   template() "not implemented: merge_statement"
    ;

// $<Merge - Specific Clauses

merge_update_clause
    :    ^(MERGE_UPDATE merge_element+ where_clause? merge_update_delete_part?)
    ->   template() "not implemented: merge_update_clause"
    ;

merge_element
    :    ^(ASSIGN column_name expression)
    ->   template() "not implemented: merge_element"
    ;

merge_update_delete_part
    :    ^(SQL92_RESERVED_DELETE where_clause)
    ->   template() "not implemented: merge_update_delete_part"
    ;

merge_insert_clause
    :    ^(MERGE_INSERT ^(COLUMNS column_name*) expression_list where_clause?) 
    ->   template() "not implemented: merge_insert_clause"
    ;

selected_tableview
    :    ^(SELECTED_TABLEVIEW alias? (tableview_name|subquery))
    ->   template() "not implemented: selected_tableview"
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
    ->   template() "not implemented: general_table_ref"
    ;

static_returning_clause
    :    ^(STATIC_RETURNING expression+ into_clause)
    ->   template() "not implemented: static_returning_clause"
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
        (    ^(COLLECTION_MODE expression PLUS_SIGN?)
        |    ^(SELECT_MODE select_statement subquery_restriction_clause?)
        |    ^(DIRECT_MODE tableview_name sample_clause?)
        |    table_ref
        |    general_element
        )
        )
    ->   template() "not implemented: dml_table_expression_clause"
        |    table_ref
    ->   template() "not implemented: dml_table_expression_clause"
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
    :    close_statement
    ->   template() "not implemented: cursor_manipulation_statements"
    |    open_statement
    ->   template() "not implemented: cursor_manipulation_statements"
    |    fetch_statement
    ->   template() "not implemented: cursor_manipulation_statements"
    |    open_for_statement
    ->   template() "not implemented: cursor_manipulation_statements"
    ;

close_statement
    :     ^(CLOSE_VK variable_name) 
    ->   template() "not implemented: close_statement"
    ;

open_statement
    :    ^(OPEN_VK cursor_name expression_list?)
    ->   template() "not implemented: open_statement"
    ;

fetch_statement
    :    ^(SQL92_RESERVED_FETCH cursor_name 
            (    ^(SQL92_RESERVED_INTO variable_name+)
            |    ^(BULK_VK variable_name+)
            )
        )
    ->   template() "not implemented: fetch_statement"
    ;

open_for_statement
    :    ^(OPEN_VK variable_name (expression|select_statement) using_clause?)
    ->   template() "not implemented: open_for_statement"
    ;

// $>

// $<Transaction Control SQL PL/SQL Statements

transaction_control_statements
    :    set_transaction_command
    ->   template() "not implemented: transaction_control_statements"
    |    set_constraint_command
    ->   template() "not implemented: transaction_control_statements"
    |    commit_statement
    ->   template() "not implemented: transaction_control_statements"
    |    rollback_statement
    ->   template() "not implemented: transaction_control_statements"
    |    savepoint_statement
    ->   template() "not implemented: transaction_control_statements"
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
            (    ^(COMMENT_VK expression)
            |    ^(FORCE_VK (CORRUPT_XID_VK expression|CORRUPT_XID_ALL_VK|expression expression?))
            )?
            write_clause?
        )
    ->   template() "not implemented: commit_statement"
    ;

write_clause
    :    ^(WRITE_VK (WAIT_VK|PLSQL_RESERVED_NOWAIT)? (IMMEDIATE_VK|BATCH_VK)?)
    ->   template() "not implemented: write_clause"
    ;

rollback_statement
    :     ^(ROLLBACK_VK WORK_VK? 
            (    ^(SQL92_RESERVED_TO savepoint_name)
            |    ^(FORCE_VK CHAR_STRING)
            )?
        )
    ->   template() "not implemented: rollback_statement"
    ;

savepoint_statement
    :    ^(SAVEPOINT_VK savepoint_name) 
    ->   template() "not implemented: savepoint_statement"
    ;
    
pipe_row_statement
    :    ^(PIPE_ROW expression)
    ->   template() "not implemented: pipe_row_statement"
    ;

// $>

// $<Expression & Condition

expression_list
    :    ^(EXPR_LIST expression*)
    ->   template() "not implemented: expression_list"
    ;

expression
    :    ^(LOGIC_EXPR expression_element)
    ->   template() "not implemented: expression"
    |    ^(EXPR expression_element)
    ->   template() "not implemented: expression"
    ;

expression_element
    :    ^(SQL92_RESERVED_OR expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_AND expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_NOT expression_element)
    ->   template() "not implemented: expression_element"
    |    ^((EQUALS_OP|NOT_EQUAL_OP|LESS_THAN_OP|GREATER_THAN_OP|LESS_THAN_OR_EQUALS_OP|GREATER_THAN_OR_EQUALS_OP) expression_element expression_element)

    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_NULL expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NULL expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_NAN expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NAN expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_PRESENT expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_PRESENT expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_INFINITE expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_INFINITE expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_A_SET expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_A_SET expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_EMPTY expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_EMPTY expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(IS_NOT_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: expression_element"
    |    ^(IS_OF_TYPE expression_element type_spec+)
    ->   template() "not implemented: expression_element"

    |    ^((MEMBER_VK|SUBMULTISET_VK) expression_element expression_element)
    ->   template() "not implemented: expression_element"

    |    ^(NOT_IN expression_element in_elements)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_IN expression_element in_elements)
    ->   template() "not implemented: expression_element"
    |    ^(NOT_BETWEEN expression_element expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_BETWEEN expression_element expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(NOT_LIKE expression_element expression_element expression_element?)
    ->   template() "not implemented: expression_element"
    |    ^((SQL92_RESERVED_LIKE|LIKEC_VK|LIKE2_VK|LIKE4_VK) expression_element expression_element expression_element?)
    ->   template() "not implemented: expression_element"

    |    ^(CONCATENATION_OP expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(PLUS_SIGN expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(MINUS_SIGN expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(ASTERISK expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(SOLIDUS expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(MOD_VK expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(DIV_VK expression_element expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(PIPE_VK expression_element expression_element)
    ->   template() "not implemented: expression_element"

    |    ^(UNARY_OPERATOR expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_PRIOR expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(NEW_VK expression)
    ->   template() "not implemented: expression_element"
    |    ^(SQL92_RESERVED_DISTINCT expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(STANDARD_FUNCTION standard_function)
    ->   template() "not implemented: expression_element"
    |    ^((SOME_VK|SQL92_RESERVED_EXISTS|SQL92_RESERVED_ALL|SQL92_RESERVED_ANY) expression_element)
    ->   template() "not implemented: expression_element"
    |    ^(VECTOR_EXPR expression_element+)
    ->   template() "not implemented: expression_element"

    |    ^(DATETIME_OP expression_element datetime_element)
    ->   template() "not implemented: expression_element"
    |    model_expression
    ->   template() "not implemented: expression_element"
    |    ^(KEEP_VK expression_element DENSE_RANK_VK (FIRST_VK|LAST_VK) order_by_clause over_clause?)
    ->   template() "not implemented: expression_element"

    |    ^(DOT_ASTERISK tableview_name)
    ->   template() "not implemented: expression_element"
    |    ^((PERCENT_FOUND_VK|PERCENT_NOTFOUND_VK|PERCENT_ROWCOUNT_VK|PERCENT_ISOPEN_VK) cursor_name)
    ->   template() "not implemented: expression_element"
    |    ^(OUTER_JOIN_SIGN expression_element)
    ->   template() "not implemented: expression_element"

    |    case_statement
    ->   template() "not implemented: expression_element"
    |    constant
    ->   template() "not implemented: expression_element"
    |    general_element
    ->   template() "not implemented: expression_element"
    |    subquery
    ->   template() "not implemented: expression_element"
    ;

in_elements
    :    subquery
    ->   template() "not implemented: in_elements"
    |    expression_list
    ->   template() "not implemented: in_elements"
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
    :    ^(SIMPLE_CASE label_name* expression case_when_part+ case_else_part?)  
    ->   template() "not implemented: case_statement"
    |    ^(SEARCHED_CASE label_name* case_when_part+ case_else_part?) 
    ->   template() "not implemented: case_statement"
    ;

// $<CASE - Specific Clauses

case_when_part
    :    ^(SQL92_RESERVED_WHEN expression (seq_of_statements|expression))
    ->   template() "not implemented: case_when_part"
    ;

case_else_part
    :    ^(SQL92_RESERVED_ELSE (seq_of_statements|expression))
    ->   template() "not implemented: case_else_part"
    ;
// $>

standard_function
    :    ^(FUNCTION_ENABLING_OVER function_argument over_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(FUNCTION_ENABLING_USING function_argument using_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(COUNT_VK ( ASTERISK | expression ) over_clause?)
    ->   template() "not implemented: standard_function"
    |    ^((CAST_VK|XMLCAST_VK) (subquery|expression) type_spec)
    ->   template() "not implemented: standard_function"
    |    ^(CHR_VK expression NCHAR_CS_VK)
    ->   template() "not implemented: standard_function"
    |    ^(COLLECT_VK (SQL92_RESERVED_DISTINCT|SQL92_RESERVED_UNIQUE)? column_name collect_order_by_part?)
    ->   template() "not implemented: standard_function"
    |    ^(FUNCTION_ENABLING_WITHIN_OR_OVER function_argument (within_clause|over_clause)+ )
    ->   template() "not implemented: standard_function"
    |    ^(DECOMPOSE_VK expression (CANONICAL_VK|COMPATIBILITY_VK)?) 
    ->   template() "not implemented: standard_function"
    |    ^(EXTRACT_VK REGULAR_ID expression)
    ->   template() "not implemented: standard_function"
    |    ^((FIRST_VALUE_VK|LAST_VALUE_VK) expression NULLS_VK? over_clause) 
    ->   template() "not implemented: standard_function"
    |    ^(PREDICTION_FUNCTION expression+ cost_matrix_clause? using_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(TRANSLATE_VK expression (CHAR_CS_VK|NCHAR_CS_VK)? expression*)
    ->   template() "not implemented: standard_function"
    |    ^(TREAT_VK expression REF_VK? type_spec)
    ->   template() "not implemented: standard_function"
    |    ^(TRIM_VK (LEADING_VK|TRAILING_VK|BOTH_VK)? expression expression?) 
    ->   template() "not implemented: standard_function"

    |    ^(XMLAGG_VK expression order_by_clause?)
    ->   template() "not implemented: standard_function"
    |    ^((XMLCOLATTVAL_VK|XMLFOREST_VK) xml_multiuse_expression_element+)
    ->   template() "not implemented: standard_function"
    |    ^(XMLEXISTS_VK expression xml_passing_clause?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLPARSE_VK (DOCUMENT_VK|CONTENT_VK) expression WELLFORMED_VK?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLQUERY_VK expression xml_passing_clause? SQL92_RESERVED_NULL?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLROOT_VK expression xml_param_version_part xmlroot_param_standalone_part?)
    ->   template() "not implemented: standard_function"
    |    ^(XMLTABLE_VK xml_namespaces_clause? expression xml_passing_clause? xml_table_column*)
    ->   template() "not implemented: standard_function"
    |    ^(XMLELEMENT_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (NAME_VK|EVALNAME_VK)? expression
            xml_attributes_clause? (expression alias?)*
        )
    ->   template() "not implemented: standard_function"
    |    ^(XMLPI_VK
                (    NAME_VK char_set_name? ID
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

over_clause
    :    ^(OVER_VK query_partition_clause? (order_by_clause windowing_clause?)?)
    ->   template() "not implemented: over_clause"
    ;

windowing_clause
    :    ^((ROWS_VK|RANGE_VK)
            (    ^(SQL92_RESERVED_BETWEEN windowing_elements windowing_elements)
            |    windowing_elements+
            )
        )
    ->   template() "not implemented: windowing_clause"
    ;

windowing_elements
    :    ^(UNBOUNDED_VK PRECEDING_VK)
    ->   template() "not implemented: windowing_elements"
    |    ^(CURRENT_VK ROW_VK)
    ->   template() "not implemented: windowing_elements"
    |    ^((PRECEDING_VK|FOLLOWING_VK) expression)
    ->   template() "not implemented: windowing_elements"
    ;

using_clause
    :    ^(PLSQL_NON_RESERVED_USING using_element+)
    ->   template() "not implemented: using_clause"
    ;

using_element
    :    ^(ELEMENT SQL92_RESERVED_IN? OUT_VK? expression alias?)
    ->   template() "not implemented: using_element"
    |    ASTERISK
    ->   template() "not implemented: using_element"
    ;

collect_order_by_part
    :    ^(SQL92_RESERVED_ORDER expression)
    ->   template() "not implemented: collect_order_by_part"
    ;

within_clause
    :    ^(WITHIN_VK order_by_clause)
    ->   template() "not implemented: within_clause"
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
    :    ^(PASSING_VK VALUE_VK? expression alias? (expression alias?)?)
    ->   template() "not implemented: xml_passing_clause"
    ;

xml_attributes_clause
    :    ^(XMLATTRIBUTES_VK
            (ENTITYESCAPING_VK|NOENTITYESCAPING_VK)?
            (SCHEMACHECK_VK|NOSCHEMACHECK_VK)?
            xml_multiuse_expression_element+
        )
    ->   template() "not implemented: xml_attributes_clause"
    ;

xml_namespaces_clause
    :    ^(XMLNAMESPACES_VK
            (expression alias?)* xml_general_default_part?
        )
    ->   template() "not implemented: xml_namespaces_clause"
    ;

xml_table_column
    :    ^(XML_COLUMN xml_column_name (ORDINALITY_VK|type_spec expression? xml_general_default_part?) )
    ->   template() "not implemented: xml_table_column"
    ;

xml_general_default_part
    :    ^(SQL92_RESERVED_DEFAULT expression)
    ->   template() "not implemented: xml_general_default_part"
    ;

xml_multiuse_expression_element
    :    ^(XML_ELEMENT expression xml_alias?)
    ->   template() "not implemented: xml_multiuse_expression_element"
    ;

xml_alias
    :    ^(XML_ALIAS ID)
    ->   template() "not implemented: xml_alias"
    |    ^(XML_ALIAS ^(EVALNAME_VK expression))
    ->   template() "not implemented: xml_alias"
    ;

xml_param_version_part
    :    ^(VERSION_VK (NO_VK VALUE_VK|expression))
    ->   template() "not implemented: xml_param_version_part"
    ;

xmlroot_param_standalone_part
    :    ^(STANDALONE_VK (YES_VK|NO_VK VALUE_VK?))
    ->   template() "not implemented: xmlroot_param_standalone_part"
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
    :    ^(ALIAS char_set_name? ID)
    ->   template() "not implemented: alias"
    ;

where_clause
    :    ^(SQL92_RESERVED_WHERE expression)
    ->   template() "not implemented: where_clause"
    ;

into_clause
    :    ^(SQL92_RESERVED_INTO general_element+) 
    ->   template() "not implemented: into_clause"
    |    ^(BULK_VK general_element+) 
    ->   template() "not implemented: into_clause"
    ;

// $>

// $<Common PL/SQL Named Elements

xml_column_name
    :    ^(XML_COLUMN_NAME char_set_name? ID)
    ->   template() "not implemented: xml_column_name"
    ;

cost_class_name
    :    ^(COST_CLASS_NAME char_set_name? ID)
    ->   template() "not implemented: cost_class_name"
    ;

attribute_name
    :    ^(ATTRIBUTE_NAME char_set_name? ID)
    ->   template() "not implemented: attribute_name"
    ;

savepoint_name
    :    ^(SAVEPOINT_NAME char_set_name? ID)
    ->   template() "not implemented: savepoint_name"
    ;

rollback_segment_name
    :    ^(ROLLBACK_SEGMENT_NAME char_set_name? ID)
    ->   template() "not implemented: rollback_segment_name"
    ;


table_var_name
    :    ^(TABLE_VAR_NAME char_set_name? ID)
    ->   template() "not implemented: table_var_name"
    ;

schema_name
    :    ^(SCHEMA_NAME char_set_name? ID)
    ->   template() "not implemented: schema_name"
    ;

routine_name
    :    ^(ROUTINE_NAME char_set_name? ID+ link_name?)
    ->   template() "not implemented: routine_name"
    ;

package_name
    :    ^(PACKAGE_NAME char_set_name? ID+)
    ->   template() "not implemented: package_name"
    ;

implementation_type_name
    :    ^(IMPLEMENTATION_TYPE_NAME char_set_name? ID+)
    ->   template() "not implemented: implementation_type_name"
    ;

parameter_name
    :    ^(PARAMETER_NAME char_set_name? ID)
    ->   parameter_name(name={$ID.text})
    ;

reference_model_name
    :    ^(REFERENCE_MODEL_NAME char_set_name? ID)
    ->   template() "not implemented: reference_model_name"
    ;

main_model_name
    :    ^(MAIN_MODEL_NAME char_set_name? ID)
    ->   template() "not implemented: main_model_name"
    ;

query_name
    :    ^(QUERY_NAME char_set_name? ID)
    ->   template() "not implemented: query_name"
    ;

constraint_name
    :    ^(CONSTRAINT_NAME char_set_name? ID+ link_name?)
    ->   template() "not implemented: constraint_name"
    ;

label_name
    :    ^(LABEL_NAME ID) -> string_literal(val={$ID.text})
    ;

type_name
    :    ^(TYPE_NAME ID+)
    ->   template() "not implemented: type_name"
    ;

sequence_name
    :    ^(SEQUENCE_NAME ID+)
    ->   template() "not implemented: sequence_name"
    ;

exception_name
    :    ^(EXCEPTION_NAME char_set_name? ID+)
    ->   template() "not implemented: exception_name"
    ;

function_name
    :    ^(FUNCTION_NAME char_set_name? ID+)
    ->   template() "not implemented: function_name"
    ;

procedure_name
    :    ^(PROCEDURE_NAME char_set_name? ID+)
    ->   template() "not implemented: procedure_name"
    ;

trigger_name
    :    ^(TRIGGER_NAME char_set_name? ID+)
    ->   template() "not implemented: trigger_name"
    ;

variable_name
    :    ^(HOSTED_VARIABLE_NAME char_set_name? ID+)
    ->   template() "not implemented: variable_name"
    |    ^(VARIABLE_NAME char_set_name? ID+)
    ->   template() "not implemented: variable_name"
    ;

index_name
    :    ^(INDEX_NAME char_set_name? ID)
    ->   template() "not implemented: index_name"
    ;

cursor_name
    :    ^(CURSOR_NAME char_set_name? ID)
    ->   template() "not implemented: cursor_name"
    ;

record_name
    :    ^(RECORD_NAME char_set_name? ID)
    ->   template() "not implemented: record_name"
    ;

collection_name
    :    ^(COLLECTION_NAME char_set_name? ID+)
    ->   template() "not implemented: collection_name"
    ;

link_name
    :    ^(LINK_NAME char_set_name? ID)
    ->   template() "not implemented: link_name"
    ;

column_name
    :    ^(COLUMN_NAME char_set_name? ID+)
    ->   template() "not implemented: column_name"
    ;

tableview_name
    :    ^(TABLEVIEW_NAME char_set_name? ID+ link_name? partition_extension_clause?)
    ->   template() "not implemented: tableview_name"
    ;

char_set_name
    :    ^(CHAR_SET_NAME ID+)
    ->   template() "not implemented: char_set_name"
    ;

// $>

// $<Common PL/SQL Specs

function_argument
    :    ^(ARGUMENTS argument*)
    ->   template() "not implemented: function_argument"
    ;

argument
    :    ^(ARGUMENT expression parameter_name?)
    ->   template() "not implemented: argument"
    ;

type_spec
    :     ^(CUSTOM_TYPE type_name REF_VK? (PERCENT_ROWTYPE_VK|PERCENT_TYPE_VK)?)
    ->   template() "not implemented: type_spec"
    |    native_datatype_spec -> { $native_datatype_spec.st; }
    |    ^(INTERVAL_DATATYPE (YEAR_VK|DAY_VK) (MONTH_VK|SECOND_VK) expression*)
    ->   template() "not implemented: type_spec"
    ;

type_precision
    :    ^(PRECISION size1=constant size2=constant? (precision_char=CHAR_VK|precision_byte=BYTE_VK)?)
    ->   base_type_spec_precision(
  size1={$size1.st}, size2={$size2.st}, is_byte={$precision_byte != null}, is_char={$precision_char != null}
)
    ;

native_datatype_spec
@init { String typeBaseName = null; }
    :    ^(NATIVE_DATATYPE
    (    BFILE_VK { typeBaseName = "bfile"; }
    |    BINARY_FLOAT_VK { typeBaseName = "binary_float"; }
    |    BINARY_INTEGER_VK { typeBaseName = "binary_integer"; }
    |    BLOB_VK { typeBaseName = "blob"; }
    |    BOOLEAN_VK { typeBaseName = "boolean"; }
    |    CHARACTER_VK  { typeBaseName = "character"; }
    |    CHAR_VK { typeBaseName = "char"; }
    |    CLOB_VK { typeBaseName = "clob"; }
    |    DATE_VK { typeBaseName = "date"; }
    |    SQL92_RESERVED_DATE { typeBaseName = "date"; }
    |    DAY_VK
    |    DECIMAL_VK  { typeBaseName = "decimal"; }
    |    DEC_VK { typeBaseName = "dec"; }
    |    DOUBLE_VK { typeBaseName = "double"; }
    |    DOUBLE_VK PRECISION_VK { typeBaseName = "double precision"; }
    |    DSINTERVAL_UNCONSTRAINED_VK
    |    FLOAT_VK { typeBaseName = "float"; }
    |    HOUR_VK
    |    INTEGER_VK { typeBaseName = "integer"; }
    |    INT_VK { typeBaseName = "int"; }
    |    LONG_VK { typeBaseName = "long"; }
    |    LONG_VK RAW_VK { typeBaseName = "long raw"; }
    |    MINUTE_VK
    |    MLSLABEL_VK
    |    MONTH_VK
    |    NATURALN_VK
    |    NATURAL_VK
    |    NCHAR_VK { typeBaseName = "nchar"; }
    |    NCLOB_VK { typeBaseName = "nclob"; }
    |    NUMBER_VK { typeBaseName = "number"; }
    |    NUMERIC_VK { typeBaseName = "numeric"; }
    |    NVARCHAR2_VK { typeBaseName = "nvarchar2"; }
    |    PLS_INTEGER_VK { typeBaseName = "pls_integer"; }
    |    POSITIVEN_VK
    |    POSITIVE_VK
    |    RAW_VK { typeBaseName = "raw"; }
    |    REAL_VK
    |    ROWID_VK { typeBaseName = "rowid"; }
    |    SECOND_VK
    |    SIGNTYPE_VK
    |    SIMPLE_INTEGER_VK
    |    SMALLINT_VK { typeBaseName = "smallint"; }
    |    STRING_VK
    |    TIMESTAMP_LTZ_UNCONSTRAINED_VK
    |    TIMESTAMP_TZ_UNCONSTRAINED_VK
    |    TIMESTAMP_UNCONSTRAINED_VK
    |    TIMESTAMP_VK { typeBaseName = "timestamp"; }
    |    TIMEZONE_ABBR_VK
    |    TIMEZONE_HOUR_VK
    |    TIMEZONE_MINUTE_VK
    |    TIMEZONE_REGION_VK
    |    UROWID_VK { typeBaseName = "urowid"; }
    |    VARCHAR2_VK { typeBaseName = "varchar2"; }
    |    VARCHAR_VK { typeBaseName = "varchar"; }
    |    YEAR_VK
    |    YMINTERVAL_UNCONSTRAINED_VK)
    prec=type_precision?
    (is_tz=TIME_VK is_tz_local=LOCAL_VK?)?
    )
    { if (typeBaseName == null) { typeBaseName = "Unsupported datatype"; } }
    -> base_type_spec(
  baseName={typeBaseName}, precision={$prec.st},
  is_with_time_zone={$is_tz != null}, is_time_zone_local={$is_tz_local != null}
)
    ;

general_element
    :    ^(CASCATED_ELEMENT general_element+)
    ->   template() "not implemented: general_element"
    |    ^(HOSTED_VARIABLE_ROUTINE_CALL routine_name function_argument)
    ->   template() "not implemented: general_element"
    |    ^(HOSTED_VARIABLE char_set_name? ID+)
    ->   template() "not implemented: general_element"
    |    ^(ROUTINE_CALL routine_name function_argument)
    ->   template() "not implemented: general_element"
    |    ^(ANY_ELEMENT char_set_name? ID+)
    ->   template() "not implemented: general_element"
    ;

// $>

// $<Lexer Mappings

constant
    :    v1=UNSIGNED_INTEGER -> string_literal(val={$v1.text})
    |    ^(MINUS_SIGN v2=UNSIGNED_INTEGER) -> string_literal(val={"-" + $v2.text})
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
    ;

// $>

id
    :    char_set_name? ID
    ->   template() "not implemented: id"
    ;

    
