package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.CommonToken;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;
import ru.barsopen.plsqlconverter.util.AttachedComments;

public class NestedFunctionConversionTransformer {
	
	static boolean isDebugEnabled = false;
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<create_function_or_procedure_body> functionClauses = AstUtil.getDescendantsOfType(tree, create_function_or_procedure_body.class);
		ScopeAssignment sa = ScopeAssignment.compute(tree);
		//ProcedureToFunctionConversionTransformer.printReferences(sa);
		Collections.reverse(functionClauses);
		for (create_function_or_procedure_body clause: functionClauses) {
			if (clause._getParent() instanceof block) {
				new NestedFunctionConversionTransformer(clause, sa).transform();
			}
		}
	}
	
	ScopeAssignment sa;
	block parentBlock;
	create_function_or_procedure_body func_node;
	create_function_or_procedure_body toplevelFuncNode;
	List<create_function_or_procedure_body> owningFuncNodes = new ArrayList<create_function_or_procedure_body>();
	
	private NestedFunctionConversionTransformer(create_function_or_procedure_body clause, ScopeAssignment sa) {
		this.sa = sa;
		func_node = clause;
		parentBlock = (block)clause._getParent();
	}

	String funcName, newFuncName;
	Set<_baseNode> referenced = new HashSet<_baseNode>();
	Map<String, _baseNode> functionScope;
	List<parameter> addedParameters = new ArrayList<parameter>();
	
	private void transform() {
		findParentFuncNode();
		funcName = AstUtil.normalizeId(findNameNode(func_node).value);
		findFreeVariables();
		addParameters();
		renameFunction();
		moveFunctionToSibling();
		transformCallSites();
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
		sa.defToScopeEntry.get(func_node).rename(newFuncName);
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
		for (_baseNode defNode: referenced) {
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
		for (general_element_item ref_item: sa.defToScopeEntry.get(func_node).references) {
			general_element callSite = (general_element)ref_item._getParent();
			if (callSite == null) {
				continue;
			}
			// skip suspicious references: those that are in sql statements and lack parenthesis
			if (ScopeAssignment.isInSqlStatement(ref_item)) {
				boolean hasCall = false;
				for (general_element_item item: callSite.general_element_items) {
					if (item instanceof function_argument) {
						hasCall = true;
						break;
					}
				}
				if (!hasCall) {
					continue;
				}
			}

			int idx = callSite.general_element_items.indexOf(ref_item);
			if (idx == 1) {
				general_element_item item0 = callSite.general_element_items.get(0);
				callSite.remove_general_element_items(0);
				MiscConversionsTransformer.reattachCommentsFromDeletedNodes(ref_item, item0);
			}
			general_element_id callFuncName = (general_element_id)callSite.general_element_items.get(0);
			callFuncName.id.set_value(newFuncName);
			function_argument callArgs;
			if (callSite.general_element_items.size() == 1) {
				callArgs = parser.make_function_argument(null);
				callSite.add_general_element_items(callArgs);
			} else {
				callArgs = (function_argument)callSite.general_element_items.get(1);
			}
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

	private void findFreeVariables() {
		List<general_element> elts = FunctionNamedResultConversionTransformer.getOwnNodesOfType(func_node, general_element.class);
		for (general_element elt: elts) {
			Scope scope = sa.generalElementToScope.get(elt);
			if (scope == null) {
				continue;
			}
			_baseNode target = ScopeAssignment.getGeneralElementInitialTarget((general_element_id)elt.general_element_items.get(0), scope);
			if (target != null) {
				addReferenced(target);
				for (int i = 1; target != null && i < elt.general_element_items.size(); ++i) {
					general_element_item item = elt.general_element_items.get(i);
					target = ScopeAssignment.getGeneralElementNextTarget(item, target, sa);
					if (target != null) {
						addReferenced(target);
					}
				}
			}
		}
	}

	private void addReferenced(_baseNode target) {
		_baseNode node = target;
		while (node != null && node != func_node) {
			node = node._getParent();
		}
		if (node == null) {
			referenced.add(target);
		}
	}
}
