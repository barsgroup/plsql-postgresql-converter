(cd .. && mvn clean validate) &&
mvn package &&
cp target/lexer-parser-walker-1.0-SNAPSHOT.jar ../../eclipse-workspace-parser/parser/lib/ &&
cp target/generated-sources/antlr3/br/com/porcelli/parser/plsql/PLSQLParser.tokens ../../eclipse-workspace-parser/parser/antlr-source
