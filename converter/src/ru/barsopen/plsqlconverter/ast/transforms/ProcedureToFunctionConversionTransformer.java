package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.antlr.runtime.UnwantedTokenException;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class ProcedureToFunctionConversionTransformer {
	
	public static boolean isDebugEnabled = false;
	
	public static void transformAll(_baseNode tree) throws Exception {
		ScopeAssignment sa = ScopeAssignment.compute(tree);
		if (isDebugEnabled) {
			printReferences(sa);
		}
		
		List<create_function_or_procedure_body> nodes = AstUtil.getDescendantsOfType(tree, create_function_or_procedure_body.class);
		for (create_function_or_procedure_body node: nodes) {
			transform(node, sa);
		}
	}

	public static void printReferences(ScopeAssignment scopeAssignment) {
		for (Entry<_baseNode, ScopeEntry> mapEntry: scopeAssignment.defToScopeEntry.entrySet()) {
			_baseNode defNode = mapEntry.getKey();
			ScopeEntry scopeEntry = mapEntry.getValue();
			System.err.printf("def node %s at %d:%d has %d references:\n", defNode.getClass().getSimpleName(), defNode._getLine(), defNode._getCol(), scopeEntry.references.size());
			for (general_element_item reference: scopeEntry.references) {
				general_element ge = (general_element)reference._getParent();
				id idNode = ((general_element_id)ge.general_element_items.get(0)).id;
				System.err.printf(" => %s at %d:%d\n", idNode.value, idNode._line, idNode._col);
			}
		}
	}

	public static String get_function_name(create_function_or_procedure_body func_node) {
		String packageName = get_function_package_name(func_node);

		List <id> ids;
		if (func_node instanceof create_function_body) {
			ids = ((create_function_body)func_node).function_name.ids;
		} else {
			ids = ((create_procedure_body)func_node).procedure_name.ids;
		}
		id nameNode = ids.get(ids.size() - 1);
		String name = AstUtil.normalizeId(nameNode.value);
		String result = String.format("%s.%s@%d:%d", packageName, name, nameNode._line, nameNode._col);
		return result;
	}

	private static String get_function_package_name(create_function_or_procedure_body func_node) {
		_baseNode parent = func_node._getParent();
		while (parent != null) {
			if (parent instanceof create_package_body) {
				create_package_body pkg = (create_package_body)parent;
				return AstUtil.normalizeId(pkg.package_name.ids.get(pkg.package_name.ids.size() - 1).value);
			} else if (parent instanceof create_package_spec) {
				create_package_spec pkg = (create_package_spec)parent;
				return AstUtil.normalizeId(pkg.package_name.ids.get(pkg.package_name.ids.size() - 1).value);
			} else {
				parent = parent._getParent();
			}
		}
		return "<no package>";
	}

	public static void transform(create_function_or_procedure_body node, ScopeAssignment sa) throws Exception {
		ProcedureToFunctionConversionTransformer transformer = new ProcedureToFunctionConversionTransformer(node, sa);
		transformer.transform();
	}
	
	create_function_or_procedure_body proc_or_func;
	ScopeAssignment sa;
	
	private ProcedureToFunctionConversionTransformer(create_function_or_procedure_body node, ScopeAssignment sa) {
		this.proc_or_func = node;
		this.sa = sa;
	}

	private void transform() throws Exception {
		if (proc_or_func instanceof create_procedure_body) {
			transformProc();
		} else {
			transformFunc();
		}
	}

	private void transformFunc() {
		create_function_body func = (create_function_body)proc_or_func;
		List<Integer> in_params = new ArrayList<Integer>();
		List<Integer> out_params = new ArrayList<Integer>();
		classifyParameters(func.parameters, in_params, out_params);
		if (out_params.size() > 0) {
			transformFuncCallSites(in_params, out_params);
			addFuncPrimaryRetval(func);
			transformFuncReturns(func);
		}

		/*
		
		create_function_body func = parser.make_create_function_body(
				AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_CREATE),
				AstUtil.createAstNode(PLSQLParser.REPLACE_VK),
				parser.make_function_name(null, proc.procedure_name.ids),
				returnTypeSpec,
				proc.parameters,
				null,
				null,
				null,
				null,
				null,
				(function_impl)proc.create_procedure_body_impl);
		
		proc._getParent()._replace(proc, func);*/
	}

	private void transformFuncReturns(create_function_body func) {
		List<return_statement> statements = AstUtil.getDescendantsOfType(func, return_statement.class);
		for (return_statement rs: statements) {
			seq_of_statements parent = (seq_of_statements)rs._getParent();
			expression re = rs.expression;
			rs.set_expression(null);
			int idx = parent.stat_or_labels.indexOf(rs);
			parent.insert_stat_or_labels(
				idx,
				parser.make_assignment_statement(
					parser.make_general_element(
						Arrays.asList(
							(general_element_item)parser.make_general_element_id(parser.make_id("__retval__"))
						)
					),
					re
				)
			);
		}
	}

	private void addFuncPrimaryRetval(create_function_body func) {
		type_spec returnTypeSpec = parser.make_type_spec_custom(parser.make_type_name(Arrays.asList(parser.make_id("record"))), null, null);
		type_spec paramTypeSpec = func.type_spec;
		func.set_type_spec(returnTypeSpec);
		func.parameters.add_parameters(
			parser.make_parameter(
				parser.make_parameter_name(null, parser.make_id("__retval__")),
				Arrays.asList(
					(parameter_dir_spec)parser.make_parameter_out()
				),
				paramTypeSpec,
				null
			)
		);
	}

	private void transformFuncCallSites(List<Integer> in_params, List<Integer> out_params) {
		List<general_element_item> references = sa.defToScopeEntry.get(proc_or_func).references;
		List<parameter> formalArgs = (((create_function_body)proc_or_func).parameters).parameters;
		Map<String, Integer> name2pos = new HashMap<String, Integer>();
		{
			int i = 0;
			for (parameter p: formalArgs) {
				name2pos.put(AstUtil.normalizeId(p.parameter_name.id.value), i);
				++i;
			}
		}
		for (general_element_item refItem: references) {
			general_element_id refItemId = (general_element_id)refItem;
			general_element callSite = (general_element)refItem._getParent();
			if (!canTransformFuncCallSite(callSite)) {
				System.err.printf("Can't transform function %s call site at %d:%d\n", refItemId.id.value, refItemId._line, refItemId._col);
				continue;
			}

			int idx = callSite.general_element_items.indexOf(refItem);
			if (idx == callSite.general_element_items.size() - 1 || !(callSite.general_element_items.get(idx + 1) instanceof function_argument)) {
				callSite.insert_general_element_items(idx + 1, parser.make_function_argument(null));
			}
			function_argument argsNode = (function_argument)callSite.general_element_items.get(idx + 1);
			Map<String, argument> passedArgs = new HashMap<String, argument>();
			List<argument> deletedArgs = new ArrayList<argument>();
			{
				int i = 0;
				for (argument arg: argsNode.arguments) {
					String name;
					if (arg.is_parameter_name()) {
						name = AstUtil.normalizeId(arg.parameter_name.id.value);
					} else {
						name = AstUtil.normalizeId(formalArgs.get(i).parameter_name.id.value);
						++i;
					}
					passedArgs.put(name, arg);
					if (!in_params.contains(name2pos.get(name))) {
						deletedArgs.add(arg);
					}
				}
			}
			for (argument arg: deletedArgs) {
				argsNode.remove_arguments(arg);
				MiscConversionsTransformer.reattachCommentsFromDeletedNodes(argsNode, arg);
			}

			assignment_statement assignment = parser.make_assignment_statement(
				parser.make_general_element(
					Arrays.asList(
						(general_element_item)parser.make_general_element_id(parser.make_id("__tmp__"))
					)
				),
				null 
			);
			List<stat_or_label> statements = new ArrayList<stat_or_label>();
			statements.add(assignment);
			for (int out_param_no: out_params) {
				parameter formalArg = formalArgs.get(out_param_no);
				argument outArg = passedArgs.get(AstUtil.normalizeId(formalArg.parameter_name.id.value));
				general_element outArgExpr = (general_element)((general_expression)outArg.expression).expression_element;
				outArgExpr = parser.parsegeneral_element(AstUtil.cloneTree(outArgExpr.unparse()));
				assignment_statement unwrapArgStatement = parser.make_assignment_statement(
					outArgExpr,
					parser.make_general_expression(
						parser.make_general_element(
							Arrays.asList(
								(general_element_item)parser.make_general_element_id(parser.make_id("__tmp__")),
								(general_element_item)parser.make_general_element_id(parser.make_id(formalArg.parameter_name.id.value))
							)
						)
					)
				);
				statements.add(unwrapArgStatement);
			}
			if (isCallSiteInAssignment(callSite)) {
				assignment_statement ownerAssignment = (assignment_statement)callSite._getParent()._getParent();
				assignment_target at = ownerAssignment.assignment_target;
				ownerAssignment.set_assignment_target(null);

				assignment_statement unwrapArgStatement = parser.make_assignment_statement(
					at,
					parser.make_general_expression(
						parser.make_general_element(
							Arrays.asList(
								(general_element_item)parser.make_general_element_id(parser.make_id("__tmp__")),
								(general_element_item)parser.make_general_element_id(parser.make_id("__retval__"))
							)
						)
					)
				);
				statements.add(unwrapArgStatement);
			}
			block replacement = parser.make_block(
				Arrays.asList(
					(declare_spec)parser.make_variable_declaration(
						parser.make_variable_name(null, Arrays.asList(parser.make_id("__tmp__"))),
						parser.make_type_spec_custom(parser.make_type_name(Arrays.asList(parser.make_id("record"))), null, null),
						null,
						null,
						null
					)
				),
				parser.make_body(
					null,
					parser.make_seq_of_statements(
						statements
					),
					null
				)
			);
			_baseNode whatToReplace = isCallSiteInSeq(callSite) ? callSite : callSite._getParent()._getParent();
			whatToReplace._getParent()._replace(whatToReplace, replacement);
			assignment.set_expression(parser.make_general_expression(callSite));
		}
	}

	private boolean canTransformFuncCallSite(general_element callSite) {
		boolean isInAssignment = isCallSiteInAssignment(callSite);
		boolean isInSeq = isCallSiteInSeq(callSite);
		return isInAssignment || isInSeq;
	}

	public boolean isCallSiteInSeq(general_element callSite) {
		boolean isInSeq = callSite._getParent() instanceof seq_of_statements || callSite._getParent() instanceof labeled_statement;
		return isInSeq;
	}

	public boolean isCallSiteInAssignment(general_element callSite) {
		boolean isInAssignment = callSite._getParent() instanceof general_expression
				&& callSite._getParent()._getParent() instanceof assignment_statement;
		return isInAssignment;
	}

	private void transformProc() {
		create_procedure_body proc = (create_procedure_body)proc_or_func;
		List<Integer> in_params = new ArrayList<Integer>();
		List<Integer> out_params = new ArrayList<Integer>();
		classifyParameters(proc.parameters, in_params, out_params);
		if (out_params.size() > 0) {
			transformProcCallSites(in_params, out_params);
		}

		type_spec returnTypeSpec = computeReturnTypeSpec(proc);
		create_function_body func = parser.make_create_function_body(
				AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_CREATE),
				AstUtil.createAstNode(PLSQLParser.REPLACE_VK),
				parser.make_function_name(null, proc.procedure_name.ids),
				returnTypeSpec,
				proc.parameters,
				null,
				null,
				null,
				null,
				null,
				(function_impl)proc.create_procedure_body_impl);
		
		proc._getParent()._replace(proc, func);
	}

	private void transformProcCallSites(List<Integer> in_params, List<Integer> out_params) {
		List<general_element_item> references = sa.defToScopeEntry.get(proc_or_func).references;
		List<parameter> formalArgs = (((create_procedure_body)proc_or_func).parameters).parameters;
		Map<String, Integer> name2pos = new HashMap<String, Integer>();
		{
			int i = 0;
			for (parameter p: formalArgs) {
				name2pos.put(AstUtil.normalizeId(p.parameter_name.id.value), i);
				++i;
			}
		}
		for (general_element_item refItem: references) {
			general_element callSite = (general_element)refItem._getParent();
			int idx = callSite.general_element_items.indexOf(refItem);
			if (idx == callSite.general_element_items.size() - 1 || !(callSite.general_element_items.get(idx + 1) instanceof function_argument)) {
				callSite.insert_general_element_items(idx + 1, parser.make_function_argument(null));
			}
			function_argument argsNode = (function_argument)callSite.general_element_items.get(idx + 1);
			Map<String, argument> passedArgs = new HashMap<String, argument>();
			List<argument> deletedArgs = new ArrayList<argument>();
			{
				int i = 0;
				for (argument arg: argsNode.arguments) {
					String name;
					if (arg.is_parameter_name()) {
						name = AstUtil.normalizeId(arg.parameter_name.id.value);
					} else {
						name = AstUtil.normalizeId(formalArgs.get(i).parameter_name.id.value);
						++i;
					}
					passedArgs.put(name, arg);
					if (!in_params.contains(name2pos.get(name))) {
						deletedArgs.add(arg);
					}
				}
			}
			for (argument arg: deletedArgs) {
				argsNode.remove_arguments(arg);
				MiscConversionsTransformer.reattachCommentsFromDeletedNodes(argsNode, arg);
			}

			if (out_params.size() == 1) {
				argument outArg = passedArgs.get(AstUtil.normalizeId(formalArgs.get(out_params.get(0)).parameter_name.id.value));
				general_element outArgExpr = (general_element)((general_expression)outArg.expression).expression_element;
				
				assignment_statement replacement = parser.make_assignment_statement(outArgExpr, null);
				callSite._getParent()._replace(callSite, replacement);
				replacement.set_expression(parser.make_general_expression(callSite));
			} else {
				assignment_statement assignment = parser.make_assignment_statement(
					parser.make_general_element(
						Arrays.asList(
							(general_element_item)parser.make_general_element_id(parser.make_id("__tmp__"))
						)
					),
					null 
				);
				List<stat_or_label> statements = new ArrayList<stat_or_label>();
				statements.add(assignment);
				for (int out_param_no: out_params) {
					parameter formalArg = formalArgs.get(out_param_no);
					argument outArg = passedArgs.get(AstUtil.normalizeId(formalArg.parameter_name.id.value));
					general_element outArgExpr = (general_element)((general_expression)outArg.expression).expression_element;
					outArgExpr = parser.parsegeneral_element(AstUtil.cloneTree(outArgExpr.unparse()));
					assignment_statement unwrapArgStatement = parser.make_assignment_statement(
						outArgExpr,
						parser.make_general_expression(
							parser.make_general_element(
								Arrays.asList(
									(general_element_item)parser.make_general_element_id(parser.make_id("__tmp__")),
									(general_element_item)parser.make_general_element_id(parser.make_id(formalArg.parameter_name.id.value))
								)
							)
						)
					);
					statements.add(unwrapArgStatement);
				}
				block replacement = parser.make_block(
					Arrays.asList(
						(declare_spec)parser.make_variable_declaration(
							parser.make_variable_name(null, Arrays.asList(parser.make_id("__tmp__"))),
							parser.make_type_spec_custom(parser.make_type_name(Arrays.asList(parser.make_id("record"))), null, null),
							null,
							null,
							null
						)
					),
					parser.make_body(
						null,
						parser.make_seq_of_statements(
							statements
						),
						null
					)
				);
				callSite._getParent()._replace(callSite, replacement);
				assignment.set_expression(parser.make_general_expression(callSite));
			}
		}
	}

	private void classifyParameters(parameters parameters,
			List<Integer> in_params, List<Integer> out_params) {
		for (int i = 0; i < parameters.parameters.size(); ++i) {
			parameter p = parameters.parameters.get(i);
			boolean is_in = false, is_inout = false, is_out = false;
			for (parameter_dir_spec ds: p.parameter_dir_specs) {
				if (ds instanceof parameter_in) {
					is_in = true;
				} else if (ds instanceof parameter_out) {
					is_out = true;
				} else if (ds instanceof parameter_inout) {
					is_inout = true;
				}
			}
			if (is_in || is_inout) {
				in_params.add(i);
			}
			if (is_out || is_inout) {
				out_params.add(i);
			}
		}
	}

	private type_spec computeReturnTypeSpec(create_procedure_body proc) {
		parameters parametersNode = proc.parameters;
		return computeFuncionReturnTypeSpec(parametersNode);
	}

	public static type_spec computeFuncionReturnTypeSpec(parameters parametersNode) {
		List<parameter> outParameters = new ArrayList<parameter>();
		for (parameter p: parametersNode.parameters) {
			boolean is_out = false;
			for (parameter_dir_spec dir_spec: p.parameter_dir_specs) {
				if (dir_spec instanceof parameter_out || dir_spec instanceof parameter_inout) {
					is_out = true;
				}
			}
			if (is_out) {
				outParameters.add(p);
			}
		}
		if (outParameters.size() == 0) {
			return parser.make_type_spec_custom(parser.make_type_name(Arrays.asList(parser.make_id("void"))), null, null);
		} else if (outParameters.size() == 1) {
			return parser.parsetype_spec(AstUtil.cloneTree(outParameters.get(0).type_spec.unparse()));
		} else {
			return parser.make_type_spec_custom(parser.make_type_name(Arrays.asList(parser.make_id("record"))), null, null);
		}
	}
}
