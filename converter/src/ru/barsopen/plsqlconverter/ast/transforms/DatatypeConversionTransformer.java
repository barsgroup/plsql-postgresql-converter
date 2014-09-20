package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import org.antlr.runtime.tree.Tree;

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
		} else if (nodeType == PLSQLParser.VARCHAR2_VK) {
			typeSpec.name = AstUtil.createAstNode(PLSQLParser.VARCHAR_VK);
		} else {
			return;
		}
	}
}
