package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class DatatypeConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<native_datatype_spec> nodes = AstUtil.getDescendantsOfType(tree, native_datatype_spec.class);
		for (native_datatype_spec node: nodes) {
			transform(node);
		}
	}

	public static void transform(native_datatype_spec typeSpec) throws Exception {
		int nodeType = typeSpec.name.getType();
		if (nodeType == PLSQLParser.NUMBER_VK) {
			typeSpec.name = AstUtil.createAstNode(PLSQLParser.NUMERIC_VK);
			if (typeSpec.type_precision != null
				&& (typeSpec.type_precision.size2 == null
				|| Integer.valueOf(((constant_unsigned)typeSpec.type_precision.size2).value) == 0
				)) {
				constant_unsigned size1Node = (constant_unsigned)typeSpec.type_precision.size1;
				int size1 = Integer.valueOf(size1Node.value);
				if (size1 < 5) {
					typeSpec.set_name(AstUtil.createAstNode(PLSQLParser.SMALLINT_VK));
					typeSpec.set_type_precision(null);
				} else if (size1 <= 9) {
					typeSpec.set_name(AstUtil.createAstNode(PLSQLParser.INTEGER_VK));
					typeSpec.set_type_precision(null);
				} else if (size1 <= 18) {
					typeSpec.set_name(AstUtil.createAstNode(PLSQLPrinter.PGSQL_BIGINT));
					typeSpec.set_type_precision(null);
				}
			}
		} else if (nodeType == PLSQLParser.VARCHAR2_VK) {
			typeSpec.name = AstUtil.createAstNode(PLSQLParser.VARCHAR_VK);
		} else if (nodeType == PLSQLParser.RAW_VK) {
			// Convert raw(123) ro bytea
			// raw -> bytea is handled by template
			// here we remove precision
			typeSpec.set_type_precision(null);
		} else {
			return;
		}
	}
}
