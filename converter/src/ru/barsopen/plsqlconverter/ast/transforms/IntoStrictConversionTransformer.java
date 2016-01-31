package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;

public class IntoStrictConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<into_clause_normal> intoClauses = AstUtil.getDescendantsOfType(tree, into_clause_normal.class);
		for (into_clause_normal clause: intoClauses) {
			clause.PGSQL_STRICT = AstUtil.createAstNode(PLSQLPrinter.PGSQL_STRICT);
		}
	}
}
