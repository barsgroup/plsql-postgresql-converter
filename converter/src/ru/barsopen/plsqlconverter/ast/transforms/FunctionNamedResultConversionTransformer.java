package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.barsopen.plsqlconverter.ast.typed.*;

public class FunctionNamedResultConversionTransformer {
	
	public static void transformAllForward(_baseNode tree) throws Exception {
		List<create_function_body> nodes = AstUtil.getDescendantsOfType(tree, create_function_body.class);
		for (create_function_body node: nodes) {
			transform(node);
		}
	}

	public static void transform(create_function_body node) throws Exception {
		node.parameters.add_parameters(
			parser.make_parameter(
				parser.make_parameter_name(null, parser.make_id("_TMP_OUT")),
				Arrays.asList((parameter_dir_spec)parser.make_parameter_out()),
				parser.parsetype_spec(AstUtil.cloneTree(node.type_spec.unparse())),
				null
			)
		);
			
		List<return_statement> returnSites = getOwnNodesOfType(node, return_statement.class);
		for (return_statement rs: returnSites) {
			seq_of_statements seq = (seq_of_statements)rs._getParent();
			int idx = seq.stat_or_labels.indexOf(rs);
			expression expr = rs.expression;
			rs.set_expression(null);
			seq.insert_stat_or_labels(
				idx,
				parser.make_assignment_statement(
					parser.make_general_element(
						Arrays.asList(
							(general_element_item)parser.make_general_element_id(parser.make_id("_TMP_OUT"))
						)
					),
					expr
				)
			);
		}
		type_spec return_type_spec = ProcedureToFunctionConversionTransformer.computeFuncionReturnTypeSpec(node.parameters);
		type_spec old_type_spec = node.type_spec;
		node.set_type_spec(return_type_spec);
		MiscConversionsTransformer.reattachCommentsFromDeletedNodes(return_type_spec, old_type_spec);
	}
	
	@SuppressWarnings("unchecked")
	static <T> List<T> getOwnNodesOfType(_baseNode node, Class<T> klass) {
		List<T> result = new ArrayList<T>();
		List<_baseNode> ownNodes = getOwnNodes(node);
		for (_baseNode n: ownNodes) {
			if (klass.isAssignableFrom(n.getClass())) {
				result.add((T)n);
			}
		}
		return result;
	}
	
	static List<_baseNode> getOwnNodes(_baseNode node) {
		List<_baseNode> result = new ArrayList<_baseNode>();
		result.add(node);
		collectOwnNodes(node, result);
		return result;
	}
	
	private static void collectOwnNodes(_baseNode node, List<_baseNode> result) {
		List<_baseNode> children = node._getChildren();
		result.addAll(children);
		for (_baseNode child: children) {
			if (!(child instanceof create_function_body) && !(child instanceof create_procedure_body)) {
				collectOwnNodes(child, result);
			}
		}
	}
}
