package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.List;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class ProcedurePerformConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<general_element> nodes = AstUtil.getDescendantsOfType(tree, general_element.class);
		for (general_element node: nodes) {
			if (node._getParent() instanceof seq_of_statements
				|| node._getParent() instanceof labeled_statement) {
				transform(node);
			}
		}
	}

	public static void transform(general_element node) throws Exception {
		_baseNode parent = node._getParent();
		perform_statement statement = parser.make_perform_statement(node);
		parent._replace(node, statement);
	}
}
