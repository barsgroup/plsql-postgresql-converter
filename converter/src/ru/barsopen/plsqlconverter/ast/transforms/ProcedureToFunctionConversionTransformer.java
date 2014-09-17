package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.tree.CommonTreeAdaptor;
import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class ProcedureToFunctionConversionTransformer {
	
	public static void transformAll(Tree tree) throws Exception {
		List<Tree> nodes = AstUtil.getDescendantsOfType(tree, PLSQLParser.CREATE_PROCEDURE);
		for (Tree node: nodes) {
			transform(node);
		}
	}

	public static void transform(Tree node) throws Exception {
		ProcedureToFunctionConversionTransformer transformer = new ProcedureToFunctionConversionTransformer(node);
		transformer.transform();
	}
	
	Tree node;
	
	private ProcedureToFunctionConversionTransformer(Tree node) throws Exception {
		this.node = node;
	}

	private void transform() throws Exception {
		create_procedure_body proc = parser.parsecreate_procedure_body(node);
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
		
		Tree replacement = func.unparse();
		
		AstUtil.replaceNode(node, replacement);
	}

	private type_spec computeReturnTypeSpec(create_procedure_body proc) {
		List<parameter> outParameters = new ArrayList<parameter>();
		for (parameter p: proc.parameters.parameters) {
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
