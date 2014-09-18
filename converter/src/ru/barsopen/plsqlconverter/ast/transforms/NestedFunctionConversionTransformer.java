package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;

public class NestedFunctionConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<create_function_body> functionClauses = AstUtil.getDescendantsOfType(tree, create_function_body.class);
		for (create_function_body clause: functionClauses) {
			if (clause._getParent() instanceof block) {
				transform(clause);
			}
		}
	}

	private static void transform(create_function_body clause) {
		Map<String, _baseNode> functionScope = getScope(clause);
		List<general_element> elts = AstUtil.getDescendantsOfType(clause.function_impl, general_element.class);
		Map<String, _baseNode> referenced = new HashMap<String, _baseNode>();
		Map<String, List<_baseNode>> references = new HashMap<String, List<_baseNode>>();
		for (general_element elt: elts) {
			String referencedName = getReferencedName(elt);
			if (referencedName != null) {
				Map<String, _baseNode> eltScope = getScope(elt);
				if (eltScope.containsKey(referencedName) && functionScope.containsKey(referencedName) && eltScope.get(referencedName) == functionScope.get(referencedName)) {
					referenced.put(referencedName, eltScope.get(referencedName));
					if (!references.containsKey(referencedName)) {
						references.put(referencedName, new ArrayList<_baseNode>());
					}
					references.get(referencedName).add(elt);
				}
			}
		}
		System.out.printf("Inner function %s (from %d:%d)\n", AstUtil.getLastIdString(clause.function_name.ids), clause._line, clause._col);
		for (Entry<String, _baseNode> entry: referenced.entrySet()) {
			System.out.printf(" references external variable %s (defined at %d:%d) from:\n", entry.getKey(), entry.getValue()._getLine(), entry.getValue()._getCol());
			for (_baseNode refSource: references.get(entry.getKey())) {
				System.out.printf("  %s at %d:%d\n", refSource.getClass().getName(), refSource._getLine(), refSource._getCol());
			}
		}
		System.out.println();
	}

	private static String getReferencedName(general_element elt) {
		if (elt._getParent() instanceof seq_of_statements || elt._getParent() instanceof labeled_statement) {
			return null;
		}
		boolean hasCalls = false;
		id lastId = null;
		for (general_element_item item: elt.general_element_items) {
			if (item instanceof function_argument) {
				hasCalls = true;
				break;
			}
			general_element_id gei = (general_element_id)item;
			lastId = gei.id;
		}
		if (hasCalls) {
			return null;
		}
		String name = AstUtil.normalizeId(lastId.value);
		return name;
	}

	private static Map<String, _baseNode> getScope(_baseNode node) {
		Map<String, _baseNode> result = new HashMap<String, _baseNode>();
		node = node._getParent();
		while (node != null) {
			if (node instanceof block) {
				block b = (block)node;
				for (declare_spec d: b.declare_specs) {
					// variable_declaration, subtype_declaration, cursor_declaration, exception_declaration,
					// pragma_declaration, record_declaration, table_declaration, create_procedure_body, create_function_body, create_type
					if (d instanceof variable_declaration) {
						variable_declaration vd = (variable_declaration)d;
						String name = AstUtil.getLastIdString(vd.variable_name.ids);
						result.put(name, d);
					} else if (d instanceof create_procedure_body) {
						create_procedure_body cpb = (create_procedure_body)d;
						String name = AstUtil.getLastIdString(cpb.procedure_name.ids);
						result.put(name, cpb);
					} else if (d instanceof create_function_body) {
						create_function_body cfb = (create_function_body)d;
						String name = AstUtil.getLastIdString(cfb.function_name.ids);
						result.put(name, cfb);
					}
				}
			} else if (node instanceof create_function_body) {
				create_function_body cfb = (create_function_body)node;
				String name = AstUtil.getLastIdString(cfb.function_name.ids);
				result.put(name, cfb);
				for (parameter p: cfb.parameters.parameters) {
					String paramName = AstUtil.normalizeId(p.parameter_name.id.value);
					result.put(paramName, p);
				}
			} else if (node instanceof create_procedure_body) {
				create_procedure_body cpb = (create_procedure_body)node;
				String name = AstUtil.getLastIdString(cpb.procedure_name.ids);
				result.put(name, cpb);
				for (parameter p: cpb.parameters.parameters) {
					String paramName = AstUtil.normalizeId(p.parameter_name.id.value);
					result.put(paramName, p);
				}
			}
			node = node._getParent();
		}
		return result;
	}
}
