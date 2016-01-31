#!/bin/bash
set -xe
rm -rf ../src/ru/barsopen/plsqlconverter/ast/typed/
java -jar asttypgen-binary/asttypgen.jar plsql.txt ../src/