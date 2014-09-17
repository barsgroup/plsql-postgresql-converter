package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class DatatypeConversionTransformer {
	
	public static void transformAll(Tree tree) throws Exception {
		List<Tree> nodes = AstUtil.getDescendantsOfType(tree, PLSQLParser.NATIVE_DATATYPE);
		for (Tree node: nodes) {
			transform(node);
		}
	}

	public static void transform(Tree node) throws Exception {
		DatatypeConversionTransformer transformer = new DatatypeConversionTransformer(node);
		transformer.transform();
	}
	
	Tree node;
	
	private DatatypeConversionTransformer(Tree node) throws Exception {
		if (node.getType() != PLSQLParser.NATIVE_DATATYPE) {
			throw new Exception("Wrong packageNode.type");
		}
		this.node = node;
	}

	private void transform() throws Exception {
		native_datatype_spec typeSpec = parser.parsenative_datatype_spec(node);
		int nodeType = typeSpec.name.getType();
		if (nodeType == PLSQLParser.NUMBER_VK) {
			typeSpec.name = AstUtil.createAstNode(PLSQLParser.NUMERIC_VK);
		} else if (nodeType == PLSQLParser.VARCHAR2_VK) {
			typeSpec.name = AstUtil.createAstNode(PLSQLParser.VARCHAR_VK);
		} else {
			return;
		}
		AstUtil.replaceNode(node, typeSpec.unparse());
	}
}
