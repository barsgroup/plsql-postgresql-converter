package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class ProcedurePerformConversionTransformer {
	
	public static void transformAll(Tree tree) throws Exception {
		List<Tree> nodes = AstUtil.getDescendantsOfType(tree, PLSQLParser.CASCATED_ELEMENT);
		for (Tree node: nodes) {
			if (node.getParent().getType() == PLSQLParser.STATEMENTS || node.getParent().getType() == PLSQLParser.LABEL_DECLARE) {
				transform(node);
			}
		}
	}

	public static void transform(Tree node) throws Exception {
		general_element elt = parser.parsegeneral_element(node);
		perform_statement statement = parser.make_perform_statement(elt);
		AstUtil.replaceNode(node, statement.unparse());
	}
}
