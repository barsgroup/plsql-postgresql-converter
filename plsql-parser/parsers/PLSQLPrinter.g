/**
 * Oracle(c) PL/SQL 11g Parser  
 *
 * Copyright (c) 2009-2011 Alexandre Porcelli <alexandre.porcelli@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
tree grammar PLSQLPrinter;

options {
    tokenVocab=PLSQLParser;
    ASTLabelType=CommonTree;
    output = template;
}

@header {
/**
 * Oracle(c) PL/SQL 11g Parser  
 *
 * Copyright (c) 2014 Kalyanov Dmitry <kalyanov@bars-open.ru>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.porcelli.parser.plsql;
}


sql_script
    :   ^(SQL_SCRIPT unit_statement*)
    ->  template(statements={$unit_statement.st})
<<
SQL script
  <statements; separator="\n\n">
>>
    ;
    
unit_statement
    :
    /*
    alter_function
    |    alter_package
    |    alter_procedure
    |    alter_sequence
    |    alter_trigger
    |    alter_type
    */
        create_function_body { $unit_statement.st = $create_function_body.st; }
    /*
    |    create_procedure_body
    |    (create_key ( or_key replace_key )? package_key)=>create_package
    |    create_sequence
    |    create_trigger
    |    create_type
    |    drop_function
    |    drop_package
    |    drop_procedure
    |    drop_sequence
    |    drop_trigger
    |    drop_type
    */
    ;
  
create_function_body:
    ^(CREATE_FUNCTION replace=REPLACE_VK?
       ^(FUNCTION_NAME (name+=ID)+)
       r=.
       ^(PARAMETERS (p+=.)*)
       .*)
    -> template(replace={$replace}, name={$name})
<<
create <if(replace)>or replace<endif> function <name; separator=".">
>>;