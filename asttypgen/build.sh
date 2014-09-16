#!/bin/bash
set -xe
DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$DIR"
rm -rvf generated-code bin output
mkdir -p generated-code bin output
java -jar lib/antlr-3.5.2-complete.jar -o generated-code/ asttypgen.g
javac -g -cp lib/antlr-runtime-3.5.2.jar:lib/gson-2.3.jar -d bin *.java generated-code/*.java
jar cfm output/asttypgen.jar jar-manifest -C bin/ .
cp lib/antlr-runtime-3.5.2.jar lib/gson-2.3.jar output/
