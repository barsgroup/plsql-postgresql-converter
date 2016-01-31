# plsql-postgresql-converter
Tool for automated conversion of Oracle PL/SQL procedural code to PostgreSQL PL/pgSQL

# Building

To build, use Ant:

    $ cd converter
    $ ant

# Examples

Help page:

    $ converter/output/plsqlconvert --help
    Usage: plsqconverter [options]
    
      --help
      --all-packages
      --validate-reparse
      --validate-reparse-ast-path
      --limit-all-packages <N>
      --tree-type (sql_script|statement|expression)
      --input-sql (path|-)
      --input-xml (path|-)
      --input-serialized path
      --convert
      --use-pgsql
      --split-output
        (in this case --output-sql, --output-ast are templates:
          {i} is replaced by item index,
          {t} is replaced by item type,
          {n} is replaced by item name
          e.g., --output-sql '{i}_{t}_{n}.sql'
      --output-sql (path|-)
      --output-ast (path|-)
      --output-xml (path|-)
      --output-serialized path
      --debug

Conversion of a single SQL statement:

    $ echo 'select t1.x from Table1 t1, Table2 t2, Table3 t3 where t1.t3_id (+) = t3.id and t1.x = t2.y' |
      output/plsqlconvert --tree-type statement --input-sql - --output-sql - --convert --use-pgsql 2>/dev/null
      
Output:

    select t1.x
    from Table2 t2,
      Table3 t3
    left join Table1 t1 on t1.t3_id = t3.id
    where t1.x = t2.y

Conversion of stored function:

    $ cat example.txt
    CREATE OR REPLACE FUNCTION totalCustomers
    RETURN number IS
       total number(2) := 0;
       ts date;
    BEGIN
    
       ts := add_years(now(), 1);
    
       SELECT count(*) into total
       FROM customers;
       
       RETURN total;
    END;
    $ converter/output/plsqlconvert --tree-type sql_script --input-sql example.txt --output-sql - --convert --use-pgsql 2>/dev/null
    
    create or replace function totalCustomers ()
    returns numeric
    
    as $$
    #variable_conflict use_column
    declare
      total smallint default 0;
      ts timestamp;
    begin
      ts := cast(now() as timestamp) + 1 * interval '1 year';
      select count(*)
      into strict total
      from customers
      ;
      return total;
    end;
    $$
    language plpgsql;

