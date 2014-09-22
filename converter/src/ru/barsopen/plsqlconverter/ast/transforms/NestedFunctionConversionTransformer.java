package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.runtime.CommonToken;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;
import ru.barsopen.plsqlconverter.util.AttachedComments;

public class NestedFunctionConversionTransformer {
	
	static boolean isDebugEnabled = false;
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<create_function_or_procedure_body> functionClauses = AstUtil.getDescendantsOfType(tree, create_function_or_procedure_body.class);
		Collections.reverse(functionClauses);
		for (create_function_or_procedure_body clause: functionClauses) {
			if (clause._getParent() instanceof block) {
				new NestedFunctionConversionTransformer(clause).transform();
			}
		}
	}
	
	block parentBlock;
	create_function_or_procedure_body func_node;
	create_function_or_procedure_body toplevelFuncNode;
	List<create_function_or_procedure_body> owningFuncNodes = new ArrayList<create_function_or_procedure_body>();
	
	private NestedFunctionConversionTransformer(create_function_or_procedure_body clause) {
		func_node = clause;
		parentBlock = (block)clause._getParent();
	}

	String funcName, newFuncName;
	Map<String, _baseNode> referenced = new HashMap<String, _baseNode>();
	Map<String, List<_baseNode>> references = new HashMap<String, List<_baseNode>>();
	Map<String, _baseNode> functionScope;
	List<general_element> functionCalls = new ArrayList<general_element>();
	List<parameter> addedParameters = new ArrayList<parameter>();
	
	private void transform() {
		findParentFuncNode();
		funcName = AstUtil.normalizeId(findNameNode(func_node).value);
		functionScope = getScope(func_node);
		findFreeVariables();
		findFunctionCalls();
		addParameters();
		renameFunction();
		moveFunctionToSibling();
		transformCallSites();
		
		if (isDebugEnabled) {
			System.out.printf("Inner function %s\n", ProcedureToFunctionConversionTransformer.get_function_name(func_node));
			for (Entry<String, _baseNode> entry: referenced.entrySet()) {
				System.out.printf(" references external variable %s (defined at %d:%d) from:\n", entry.getKey(), entry.getValue()._getLine(), entry.getValue()._getCol());
				for (_baseNode refSource: references.get(entry.getKey())) {
					System.out.printf("  %s at %d:%d\n", refSource.getClass().getName(), refSource._getLine(), refSource._getCol());
				}
			}
			for (general_element elt: functionCalls) {
				id id1 = ((general_element_id)elt.general_element_items.get(0)).id;
				System.out.printf(" called from %d:%d\n", id1._line, id1._col);
			}
			System.out.println();
		}
	}

	public static id findNameNode(create_function_or_procedure_body func) {
		List<id> ids;
		if (func instanceof create_function_body) {
			ids = ((create_function_body)func).function_name.ids;
		} else {
			ids = ((create_procedure_body)func).procedure_name.ids;
		}
		return ids.get(ids.size() - 1);
	}

	private void findParentFuncNode() {
		_baseNode node = func_node._getParent();
		while (node != null) {
			if (node instanceof create_function_or_procedure_body) {
				owningFuncNodes.add((create_function_or_procedure_body)node);
			}
			node = node._getParent();
		}
		toplevelFuncNode = owningFuncNodes.get(owningFuncNodes.size() - 1);
	}

	private void renameFunction() {
		StringBuilder newNameSb = new StringBuilder();
		StringBuilder nestedFnSb = new StringBuilder();
		for (int i = owningFuncNodes.size() - 1; i >= 0; --i) {
			String intermediateName = AstUtil.normalizeId(findNameNode(owningFuncNodes.get(i)).value);
			if (i != owningFuncNodes.size() - 1) {
				newNameSb.append("_");
				nestedFnSb.append(" / ");
			}
			newNameSb.append(intermediateName);
			nestedFnSb.append(intermediateName);
		}
		newNameSb.append("_");
		newNameSb.append(funcName);
		newFuncName = newNameSb.toString();
		id nameNode = findNameNode(func_node);
		if (nameNode.getAttachedComments() == null) {
			nameNode.setComments(new AttachedComments());
		}
		nameNode.getAttachedComments().before.add(
			new CommonToken(
				PLSQLPrinter.COMMENT,
				String.format(
					"-- Converted from inner function %s of function %s \n", 
					funcName,
					nestedFnSb.toString()
				)
			)
		);
		nameNode.set_value(newFuncName);
	}

	private void moveFunctionToSibling() {
		parentBlock.remove_declare_specs(func_node);
		boolean isCreate = false, isReplace = false;
		if (toplevelFuncNode instanceof create_procedure_body) {
			create_procedure_body p = (create_procedure_body)toplevelFuncNode;
			isCreate = p.is_SQL92_RESERVED_CREATE();
			isReplace = p.is_REPLACE_VK();
		} else {
			create_function_body p = (create_function_body)toplevelFuncNode;
			isCreate = p.is_SQL92_RESERVED_CREATE();
			isReplace = p.is_REPLACE_VK();
		}
		if (func_node instanceof create_procedure_body) {
			create_procedure_body p = (create_procedure_body)func_node;
			p.set_SQL92_RESERVED_CREATE(isCreate ? AstUtil.createAstNode(PLSQLPrinter.SQL92_RESERVED_CREATE) : null);
			p.set_REPLACE_VK(isReplace ? AstUtil.createAstNode(PLSQLPrinter.REPLACE_VK) : null);
		} else {
			create_function_body p = (create_function_body)func_node;
			p.set_SQL92_RESERVED_CREATE(isCreate ? AstUtil.createAstNode(PLSQLPrinter.SQL92_RESERVED_CREATE) : null);
			p.set_REPLACE_VK(isReplace ? AstUtil.createAstNode(PLSQLPrinter.REPLACE_VK) : null);
		}
		
		if (toplevelFuncNode._getParent() instanceof sql_script) {
			sql_script script = (sql_script)toplevelFuncNode._getParent();
			int idx = script.sql_script_items.indexOf(toplevelFuncNode);
			script.insert_sql_script_items(idx, func_node);
		} else if (toplevelFuncNode._getParent() instanceof create_package_body) {
			create_package_body pkg = (create_package_body)toplevelFuncNode._getParent();
			int idx = pkg.package_obj_bodys.indexOf(toplevelFuncNode);
			pkg.insert_package_obj_bodys(idx, func_node);
		}
	}

	private void addParameters() {
		for (_baseNode defNode: referenced.values()) {
			if (defNode instanceof parameter) {
				parameter defParam = (parameter)defNode;
				parameter newParam = parser.make_parameter(
					parser.make_parameter_name(
						null,
						parser.make_id(defParam.parameter_name.id.value)
					),
					Arrays.asList((parameter_dir_spec)parser.make_parameter_inout()),// TODO: maybe just in or just out
					parser.parsetype_spec(AstUtil.cloneTree(defParam.type_spec.unparse())),
					null
				);
				addedParameters.add(newParam);
				if (func_node instanceof create_function_body) {
					((create_function_body)func_node).parameters.add_parameters(newParam);
				} else {
					((create_procedure_body)func_node).parameters.add_parameters(newParam);
				}
			} else if (defNode instanceof variable_declaration) {
				variable_declaration defVar = (variable_declaration)defNode;
				parameter newParam = parser.make_parameter(
					parser.make_parameter_name(
						null,
						parser.make_id(defVar.variable_name.ids.get(defVar.variable_name.ids.size() - 1).value)
					),
					Arrays.asList((parameter_dir_spec)parser.make_parameter_inout()),// TODO: maybe just in or just out
					parser.parsetype_spec(AstUtil.cloneTree(defVar.type_spec.unparse())),
					null
				);
				addedParameters.add(newParam);
				if (func_node instanceof create_function_body) {
					((create_function_body)func_node).parameters.add_parameters(newParam);
				} else {
					((create_procedure_body)func_node).parameters.add_parameters(newParam);
				}
			}
		}
	}

	private void transformCallSites() {
		for (general_element callSite: functionCalls) {
			general_element_id callFuncName = (general_element_id)callSite.general_element_items.get(0);
			callFuncName.id.set_value(newFuncName);
			function_argument callArgs = (function_argument)callSite.general_element_items.get(1);
			boolean should_use_names = false;
			for (argument arg: callArgs.arguments) {
				if (arg.parameter_name != null) {
					should_use_names = true;
				}
			}
			for (parameter newParam: addedParameters) {
				callArgs.add_arguments(
					parser.make_argument(
						parser.make_general_expression(
							parser.make_general_element(
								Arrays.asList(
									(general_element_item)parser.make_general_element_id(
										parser.make_id(newParam.parameter_name.id.value)
									)
								)
							)
						),
						should_use_names ? parser.make_parameter_name(
							null,
							parser.make_id(newParam.parameter_name.id.value)
						) : null
					)
				);
			}
		}
	}

	private void findFunctionCalls() {
		List<general_element> elts = AstUtil.getDescendantsOfType(parentBlock.body, general_element.class);
		for (general_element elt: elts) {
			if (elt.general_element_items.size() == 2
					&& elt.get_general_element_items().get(1) instanceof function_argument) {
				// Hope that there will not be a nested function inside a body block with same name
				id id1 = ((general_element_id)elt.general_element_items.get(0)).id;
				if (AstUtil.normalizeId(id1.value).equals(funcName)) {
					functionCalls.add(elt);
				}
			}
		}
	}

	private void findFreeVariables() {
		List<general_element> elts = FunctionNamedResultConversionTransformer.getOwnNodesOfType(func_node, general_element.class);
		for (general_element elt: elts) {
			String referencedName = getReferencedVariableName(elt);
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
	}

	private static String getReferencedVariableName(general_element elt) {
		if (elt._getParent() instanceof seq_of_statements || elt._getParent() instanceof labeled_statement) {
			return null;
		}
		String rv1 = getReferencedVariableNameAsCollectionMethod(elt);
		if (rv1 != null) {
			return rv1;
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

	private static String getReferencedVariableNameAsCollectionMethod(
			general_element elt) {
		List<general_element_item> items = new ArrayList<general_element_item>(elt.general_element_items);
		if (items.get(items.size() - 1) instanceof function_argument) {
			items.remove(items.size() - 1);
		}

		boolean hasCalls = false;
		for (general_element_item item: items) {
			if (item instanceof function_argument) {
				hasCalls = true;
				break;
			}
		}
		if (hasCalls) {
			return null;
		}

		if (items.size() >= 2) {
			id lastId = ((general_element_id)items.get(items.size() - 1)).id;
			String lastAttr = AstUtil.normalizeId(lastId.value);
			if (collectionMethodNames.contains(lastAttr)) {
				id prevId = ((general_element_id)items.get(items.size() - 2)).id;

				String name = AstUtil.normalizeId(prevId.value);
				return name;
			}
		}
		
		return null;
	}
	
	static Set<String> collectionMethodNames = new HashSet<String>();
	{
		collectionMethodNames.add("EXISTS");
		collectionMethodNames.add("COUNT");
		collectionMethodNames.add("LIMIT");
		collectionMethodNames.add("FIRST");
		collectionMethodNames.add("LAST");
		collectionMethodNames.add("PRIOR");
		collectionMethodNames.add("NEXT");
		collectionMethodNames.add("EXTEND");
		collectionMethodNames.add("TRIM");
		collectionMethodNames.add("DELETE");
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
