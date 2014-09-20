package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class PackageConversionTransformer {
	
	public static void transformAllPackages(_baseNode tree) throws Exception {
		List<create_package_body> packageNodes = AstUtil.getDescendantsOfType(tree, create_package_body.class);
		for (create_package_body packageNode: packageNodes) {
			transformPackage(packageNode);
		}
	}

	public static void transformPackage(create_package_body packageNode) throws Exception {
		PackageConversionTransformer transformer = new PackageConversionTransformer(packageNode);
		transformer.transform();
	}
	
	String packageName;
	create_package_body body;
	
	List<sql_script_item> packageContents = new ArrayList<sql_script_item>();
	
	private PackageConversionTransformer(create_package_body packageNode) throws Exception {
		this.body = packageNode;
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
		
		sql_script script = (sql_script)body._getParent();
		int idx = script.sql_script_items.indexOf(body);
		script.remove_sql_script_items(idx);
		for (int i = 0; i < packageContents.size(); ++i) {
			script.insert_sql_script_items(idx + i, packageContents.get(i));
		}
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
		packageContents.add(item);
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
		packageContents.add(item);
	}

	private void findPackageName() {
		String tokenValue = body.package_name.ids.get(body.package_name.ids.size() - 1).value;
		packageName = AstUtil.normalizeId(tokenValue);
	}
}
