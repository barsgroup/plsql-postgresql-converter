package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class ProcedureToFunctionConversionTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<create_function_body> funcNodes = AstUtil.getDescendantsOfType(tree, create_function_body.class);
		for (create_function_body node: funcNodes) {
			verifyFunctionHasNotOutParameters(node);
		}
		List<create_procedure_body> nodes = AstUtil.getDescendantsOfType(tree, create_procedure_body.class);
		for (create_procedure_body node: nodes) {
			transform(node);
		}
	}

	private static void verifyFunctionHasNotOutParameters(
			create_function_body func_node) {

		for (parameter p: func_node.parameters.parameters) {
			for (parameter_dir_spec pds: p.parameter_dir_specs) {
				if (pds instanceof parameter_out || pds instanceof parameter_inout) {
					System.err.printf("WARNING: There is a function with out parameter: %s\n", get_function_name(func_node));
				}
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

	public static void transform(create_procedure_body node) throws Exception {
		ProcedureToFunctionConversionTransformer transformer = new ProcedureToFunctionConversionTransformer(node);
		transformer.transform();
	}
	
	create_procedure_body proc;
	
	private ProcedureToFunctionConversionTransformer(create_procedure_body node) throws Exception {
		this.proc = node;
	}

	private void transform() throws Exception {
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
