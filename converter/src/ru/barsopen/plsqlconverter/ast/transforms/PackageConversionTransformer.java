package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class PackageConversionTransformer {
	
	public static void transformAllPackages(Tree tree) throws Exception {
		List<Tree> packageNodes = AstUtil.getDescendantsOfType(tree, PLSQLParser.CREATE_PACKAGE_BODY);
		for (Tree packageNode: packageNodes) {
			transformPackage(packageNode);
		}
	}

	public static void transformPackage(Tree packageNode) throws Exception {
		PackageConversionTransformer transformer = new PackageConversionTransformer(packageNode);
		transformer.transform();
	}
	
	Tree packageNode;
	String packageName;
	create_package_body body;
	
	List<Tree> packageContents = new ArrayList<Tree>();
	
	private PackageConversionTransformer(Tree packageNode) throws Exception {
		if (packageNode.getType() != PLSQLParser.CREATE_PACKAGE_BODY) {
			throw new Exception("Wrong packageNode.type");
		}
		this.packageNode = packageNode;
		this.body = parser.parsecreate_package_body(this.packageNode);
	}

	private void transform() throws Exception {
		findPackageName();
		
		for (package_obj_body item: body.package_obj_bodys) {
			try {
				if (item instanceof create_procedure_body) {
					transformProcedureBody((create_procedure_body)item);
				} else if (item instanceof create_function_body) {
					transformFunctionBody((create_function_body)item);
				} else {
					throw new Exception(
						String.format("Don't know how to handle %s at %d:%d", item.getClass().getName(), item._getLine(), item._getCol())
					);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			//procedure_spec, function_spec, variable_declaration, subtype_declaration, cursor_declaration, exception_declaration, record_declaration, table_declaration, create_procedure_body, create_function_body, create_type
		}
		
		AstUtil.replaceNode(packageNode, packageContents);
	}

	private void transformFunctionBody(create_function_body item) {
		item.SQL92_RESERVED_CREATE = AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_CREATE);
		if (item.REPLACE_VK == null) {
			item.REPLACE_VK = AstUtil.createAstNode(PLSQLParser.REPLACE_VK);
		}
		String name = AstUtil.normalizeId(item.function_name.ids.get(item.function_name.ids.size() - 1).value);
		name = packageName + "8" + name;
		item.function_name = parser.make_function_name(null, Arrays.asList(parser.make_id(name)));
		if (item.function_impl instanceof body_mode) {
			((body_mode)item.function_impl).block.body.set_label_name(null);
		}
		packageContents.add(item.unparse());
	}

	private void transformProcedureBody(create_procedure_body item) {
		item.SQL92_RESERVED_CREATE = AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_CREATE);
		if (item.REPLACE_VK == null) {
			item.REPLACE_VK = AstUtil.createAstNode(PLSQLParser.REPLACE_VK);
		}
		String name = AstUtil.normalizeId(item.procedure_name.ids.get(item.procedure_name.ids.size() - 1).value);
		name = packageName + "8" + name;
		item.procedure_name = parser.make_procedure_name(null, Arrays.asList(parser.make_id(name)));
		if (item.create_procedure_body_impl instanceof body_mode) {
			((body_mode)item.create_procedure_body_impl).block.body.set_label_name(null);
		}
		packageContents.add(item.unparse());
	}

	private void findPackageName() {
		String tokenValue = body.package_name.ids.get(body.package_name.ids.size() - 1).value;
		packageName = AstUtil.normalizeId(tokenValue);
	}
}
