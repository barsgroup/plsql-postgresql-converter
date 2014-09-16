#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
rm -rf generated/*
mkdir -p generated/ru/barsopen/plsqlconverter
java -cp tools/antlr-3.5.2.jar:tools/antlr-runtime-3.5.2.jar:tools/ST4-4.0.8.jar org.antlr.Tool -lib . -o generated/ru/barsopen/plsqlconverter PLSQLPrinter.g
