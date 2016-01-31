package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class PackageConversionTransformer {
	
	public static void transformAllPackages(_baseNode tree) throws Exception {
		ScopeAssignment sa = ScopeAssignment.compute(tree);
		List<create_package_body> packageNodes = AstUtil.getDescendantsOfType(tree, create_package_body.class);
		for (create_package_body packageNode: packageNodes) {
			transformPackage(packageNode, sa);
		}
	}

	public static void transformPackage(create_package_body packageNode, ScopeAssignment sa) throws Exception {
		PackageConversionTransformer transformer = new PackageConversionTransformer(packageNode, sa);
		transformer.transform();
	}
	
	ScopeAssignment sa;
	String packageName;
	create_package_body body;
	
	List<sql_script_item> packageContents = new ArrayList<sql_script_item>();
	
	private PackageConversionTransformer(create_package_body packageNode, ScopeAssignment sa) throws Exception {
		this.body = packageNode;
		this.sa = sa;
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
					System.err.printf("Don't know how to handle %s at %d:%d\n", item.getClass().getName(), item._getLine(), item._getCol());
				}
			} catch (Exception ex) {
				System.err.printf("While converting %s at %d:%d:\n", item.getClass().getName(), item._getLine(), item._getCol());
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
		transformReferences(item, name);
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
		transformReferences(item, name);
	}

	private void findPackageName() {
		String tokenValue = body.package_name.ids.get(body.package_name.ids.size() - 1).value;
		packageName = AstUtil.normalizeId(tokenValue);
	}

	private void transformReferences(create_function_or_procedure_body fn, String name) {
		ScopeEntry scopeEntry = sa.defToScopeEntry.get(fn);
		if (scopeEntry == null) {
			System.err.printf("Function at %s at %d:%d does not have scope entry\n", name, fn._getLine(), fn._getCol());
			return;
		}
		for (general_element_item refItem: scopeEntry.references) {
			general_element callSite = (general_element)refItem._getParent();
			int idx = callSite.general_element_items.indexOf(refItem);
			if (ScopeAssignment.isInSqlStatement(callSite)) {
				if (idx == callSite.general_element_items.size() - 1) {
					continue;
				}
				if (callSite.general_element_items.get(idx + 1) instanceof function_argument) {
					continue;
				}
			}
			while (idx > 0) {
				general_element_item item0 = callSite.general_element_items.get(0);
				callSite.remove_general_element_items(0);
				MiscConversionsTransformer.reattachCommentsFromDeletedNodes(callSite, item0);
				--idx;
			}
			((general_element_id)refItem).id.set_value(name);
		}
	}
}
