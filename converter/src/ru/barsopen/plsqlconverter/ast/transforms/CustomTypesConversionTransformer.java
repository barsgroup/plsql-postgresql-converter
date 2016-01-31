package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.List;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;

public class CustomTypesConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<type_spec_custom> clauses = AstUtil.getDescendantsOfType(tree, type_spec_custom.class);
		for (type_spec_custom clause: clauses) {
			type_spec replacement = maybeGetNewTypeSpec(clause);
			if (replacement != null) {
				clause._getParent()._replace(clause, replacement);
			}
		}
	}

	private static type_spec maybeGetNewTypeSpec(type_spec_custom clause) {
		String name = getFullName(clause.type_name);
		type_spec replacement = null;
		switch (name) {
		case "d_pkg_std.tref":
			replacement = parser.make_native_datatype_spec(
				AstUtil.createAstNode(PLSQLPrinter.NUMERIC_VK),
				parser.make_type_precision(parser.make_constant_unsigned("17"), null, null, null),
				null, null
			);
		case "d_pkg_std.tstr":
			replacement = parser.make_native_datatype_spec(
				AstUtil.createAstNode(PLSQLPrinter.VARCHAR_VK),
				parser.make_type_precision(parser.make_constant_unsigned("4000"), null, null, null),
				null, null
			);
		case "d_pkg_std.tlstr":
			replacement = parser.make_native_datatype_spec(
				AstUtil.createAstNode(PLSQLPrinter.PGSQL_TEXT),
				null,
				null, null
			);
		}
		return replacement;
	}

	private static String getFullName(type_name type_name) {
		List<String> ids = new ArrayList<String>();
		for (id id: type_name.ids) {
			ids.add(AstUtil.normalizeId(id.value).toLowerCase());
		}
		String result = AstUtil.stringJoin(".", ids);
		return result;
	}
}
