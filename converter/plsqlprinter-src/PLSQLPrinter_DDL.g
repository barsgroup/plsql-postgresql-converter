tree grammar PLSQLPrinter_DDL;

options {
    ASTLabelType=CommonTree;
    output = template;
}
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
        | out_of_line_constraint_clause -> { $out_of_line_constraint_clause.st }
    ;
    
view_type_constraint_item_inline
    :   ^(VIEW_TYPE_CONSTRAINT_ITEM_INLINE id=commented_id items+=inline_constraint_clause*)
        -> view_type_constraint_item_inline(id={$id.st}, items={$items})
    ;
 
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
    ;

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