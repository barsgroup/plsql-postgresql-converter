package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.PLSQLPrinter;
import ru.barsopen.plsqlconverter.ast.typed.*;
import ru.barsopen.plsqlconverter.util.AttachedComments;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class MiscConversionsTransformer {
	
	public static void transformAll(_baseNode tree) throws Exception {
		List<_baseNode> nodes = AstUtil.getDescendantsOfType(tree, _baseNode.class);
		for (_baseNode node: nodes) {
			transform(node);
		}
	}

	public static void transform(_baseNode node) throws Exception {
		change_nvl_to_coalesce(node);
		
		replace_sysdate_plus_number(node);
		
		replace_sysdate_with_current_timestamp(node);
		
		remove_from_dual(node);
		
		statement_select_without_into_to_perform(node);
		
		replace_sequence_nextval(node);
		
		change_exception_names(node);
		
		dbms_output_to_raise_notice(node);
		
		raise_application_error_to_raise_exception(node);
		
		// # Replace raise_application_error by PG standard RAISE EXCEPTION
		
	}

	private static void replace_sysdate_with_current_timestamp(_baseNode node) {
		// Change SYSDATE to current timestamp.
		if (node instanceof general_element) {
			general_element ge = (general_element)node;
			if (ge.general_element_items.size() == 1) {
				general_element_id ge_id = (general_element_id)ge.general_element_items.get(0);
				if (AstUtil.normalizeId(ge_id.id.value).equals("SYSDATE")) {
					ge_id.id.set_value("current_timestamp");
				}
			}
		}
	}

	private static void replace_sysdate_plus_number(_baseNode node) {
		// Replace sysdate +/- N by localtimestamp - 1 day interval
		if (node instanceof expression_element_plus
			|| node instanceof expression_element_minus) {
			expression_element lhs = (node instanceof expression_element_plus) ? ((expression_element_plus)node).lhs : ((expression_element_minus)node).lhs;
			expression_element rhs = (node instanceof expression_element_plus) ? ((expression_element_plus)node).rhs : ((expression_element_minus)node).rhs;
			if (lhs instanceof general_element) {
				general_element ge_lhs = (general_element)lhs;
				if (ge_lhs.general_element_items.size() == 1) {
					general_element_id lhs_ge_id = (general_element_id)ge_lhs.general_element_items.get(0);
					if (AstUtil.normalizeId(lhs_ge_id.id.value).equals("SYSDATE")) {
						// node: (plus (general_element (any_element SYSDATE))
						//             <expr>)
						// ->    (plus (general_element (any_element CURRENT_TIMESTAMP))
						//             (mul (typed_literal "interval" "1 day") <expr>))
						lhs_ge_id.id.set_value("current_timestamp");
						expression_element interval_expr_1day = parser.make_constant_pgsql_typed_literal(
							parser.make_native_datatype_spec(
								AstUtil.createAstNode(PLSQLPrinter.PGSQL_NATIVE_DATATYPE_INTERVAL),
								null, null, null
							),
							parser.make_constant_char_string("'1 day'")
						);
						expression_element new_rhs = parser.make_expression_element_asterisk(
							rhs,
							interval_expr_1day
						);
						node._replace(rhs, new_rhs);
					}
				}
			}
		}
	}

	private static void change_nvl_to_coalesce(_baseNode node) {
		// Change NVL to COALESCE
		if (node instanceof general_element) {
			general_element ge = (general_element)node;
			if (ge.general_element_items.size() == 2
				&& ge.general_element_items.get(0) instanceof general_element_id
				&& ge.general_element_items.get(1) instanceof function_argument) {
				general_element_id functionIdNode = (general_element_id)ge.general_element_items.get(0);
				String functionName = AstUtil.normalizeId(functionIdNode.id.value);
				if (functionName.equals("NVL")) {
					functionIdNode.id.set_value("coalesce");
				}
			}
		}
	}

	private static void remove_from_dual(_baseNode node) {
		// remove FROM DUAL
		if (node instanceof query_block) {
			query_block q = (query_block)node;
			if (q.from_clause != null
				&& q.from_clause.table_refs.size() == 1
				&& q.from_clause.table_refs.get(0).join_clauses.size() == 0
				&& q.from_clause.table_refs.get(0).table_ref_aux.dml_table_expression_clause
				   instanceof table_expression) {
				table_expression te = (table_expression)q.from_clause.table_refs.get(0).table_ref_aux.dml_table_expression_clause;
				if (te.table_expression_element instanceof direct_mode) {
					direct_mode dm = (direct_mode)te.table_expression_element;
					if (dm.tableview_name.ids.size() == 1) {
						id idNode = dm.tableview_name.ids.get(0);
						if (AstUtil.normalizeId(idNode.value).equals("DUAL")) {
							q.set_from_clause(null);
						}
					}
				}
			}
		}
	}

	private static void statement_select_without_into_to_perform(_baseNode node) {
		// SELECT without INTO should be PERFORM
		if (node instanceof select_statement
			&& (
				node._getParent() instanceof seq_of_statements
				|| node._getParent() instanceof labeled_statement
			)) {
			select_statement selectNode = (select_statement)node;
			if (selectNode.subquery.subquery_operation_parts.size() == 0) {
				if (selectNode.subquery.subquery_basic_elements instanceof query_block) {
					query_block query = (query_block)selectNode.subquery.subquery_basic_elements;
					if (query.into_clause == null) {
						_baseNode parentNode = node._getParent();
						perform_statement new_statement = parser.make_perform_statement(selectNode);
						parentNode._replace(node, new_statement);
					}
				}
			}
		}
	}

	private static void replace_sequence_nextval(_baseNode node) {
		// Change nextval on sequence
		// Oracle's sequence grammar is sequence_name.nextval.
		// Postgres's sequence grammar is nextval('sequence_name').
		if (node instanceof general_element) {
			general_element ge = (general_element)node;
			if (ge.general_element_items.size() == 2
				&& ge.general_element_items.get(1) instanceof general_element_id) {
				general_element_id id1 = (general_element_id)ge.general_element_items.get(0);
				general_element_id id2 = (general_element_id)ge.general_element_items.get(1);
				String lastIdValue = AstUtil.normalizeId(id2.id.value);
				if (lastIdValue.equals("NEXTVAL") || lastIdValue.equals("CURRVAL")) {
					expression_element new_expr = parser.make_general_element(
						Arrays.asList(
							id2,
							parser.make_function_argument(
								Arrays.asList(
									parser.make_argument(
										parser.make_general_expression(
											parser.make_constant_char_string(
												String.format("'%s'", AstUtil.normalizeId(id1.id.value).toLowerCase())
											)
										),
										null
									)
								)
							)
						)
					);
					ge._getParent()._replace(ge, new_expr);
					if (id1.getAttachedComments() != null) {
						if (id2.getAttachedComments() == null) {
							id2.setComments(new AttachedComments());
						}
						id2.getAttachedComments().add(id1.getAttachedComments());
					}
				}
			}
		}
	}

	private static void change_exception_names(_baseNode node) {
		// dup_val_on_index => unique_violation
		if (node instanceof exception_name) {
			exception_name en = (exception_name)node;
			if (en.ids.size() == 1) {
				id en_id = en.ids.get(0);
				if (AstUtil.normalizeId(en_id.value).equals("DUP_VAL_ON_INDEX")) {
					en_id.set_value("unique_violation");
				}
			}
		}
	}

	private static void dbms_output_to_raise_notice(_baseNode node) {
		if (node instanceof general_element
			&& (node._getParent() instanceof seq_of_statements
				|| node._getParent() instanceof labeled_statement)) {
			general_element ge = (general_element)node;
			if (ge.general_element_items.size() == 3
				&& ge.general_element_items.get(1) instanceof general_element_id
				&& ge.general_element_items.get(2) instanceof function_argument) {
				id id1 = ((general_element_id)ge.general_element_items.get(0)).id;
				id id2 = ((general_element_id)ge.general_element_items.get(1)).id;
				function_argument arg = (function_argument)ge.general_element_items.get(2);
				_baseNode parent = node._getParent();
				if (AstUtil.normalizeId(id1.value).equals("DBMS_OUTPUT")) {
					pgsql_raise_statement new_statement = parser.make_pgsql_raise_statement(
						AstUtil.createAstNode(PLSQLPrinter.PGSQL_NOTICE),
						null,
						null);
					if (arg.arguments.size() == 0) {
						// This is DBMS_OUTPUT.NEW_LINE
						new_statement.set_format(parser.make_constant_char_string("''"));
					} else {
						// This is DBMS_OUTPUT.PUT or DBMS_OUTPUT.PUT_LINE, it has one argument
						// '%' is a format string for one argument
						new_statement.set_format(parser.make_constant_char_string("'%'"));
						expression lineExpr = arg.arguments.get(0).expression;
						new_statement.add_expressions(lineExpr);
					}
					parent._replace(node, new_statement);
					reattachCommentsFromDeletedNodes(new_statement, node);
				}
			}
		}
			

		//# Raise information to the client
		//$str =~ s/DBMS_OUTPUT\.(put_line|put|new_line)*\((.*?)\);/&raise_output($2)/igse;
	}

	private static void reattachCommentsFromDeletedNodes(_baseNode new_node, _baseNode old_node) {
		List<id> old_ids = AstUtil.getDescendantsOfType(old_node, id.class);
		Set<id> new_ids = new HashSet<id>(AstUtil.getDescendantsOfType(new_node, id.class));
		List<id> deleted_ids = new ArrayList<id>();
		for (id old_id: old_ids) {
			if (!new_ids.contains(old_id)) {
				deleted_ids.add(old_id);
			}
		}
		reattachCommentsFromDeletedNodes(new_node, old_node, deleted_ids);
	}

	private static void reattachCommentsFromDeletedNodes(_baseNode new_node, _baseNode old_node, List<id> old_ids) {
		if (old_ids == null) {
			old_ids = AstUtil.getDescendantsOfType(old_node, id.class);
		}
		List<AttachedComments> comments = new ArrayList<AttachedComments>();
		for (id old_id: old_ids) {
			if (old_id.getAttachedComments() != null) {
				comments.add(old_id.getAttachedComments());
			}
		}
		if (comments.size() == 0) {
			return;
		}
		// Костыль: приаттачим комменты к ближайшему id
		_baseNode n = new_node;
		while (n != null) {
			List<_baseNode> nodes = getDescendantIdsOrNode(n, new_node);
			int idx = nodes.indexOf(new_node);
			while (idx == -1 || !(nodes.get(idx) instanceof id)) {
				idx++;
			}
			if (idx == -1) {
				n = n._getParent();
			} else {
				id comments_receiver = (id)nodes.get(idx);
				if (comments_receiver.getAttachedComments() == null) {
					comments_receiver.setComments(new AttachedComments());
				}
				for (AttachedComments comment: comments) {
					comments_receiver.getAttachedComments().after.addAll(comment.before);
					comments_receiver.getAttachedComments().after.addAll(comment.after);
				}
				break;
			}
		}
	}

	private static List<_baseNode> getDescendantIdsOrNode(_baseNode node,
			final _baseNode new_node) {

		final List<_baseNode> elts = new ArrayList<_baseNode>();
		node._walk(new _visitor() {
			public void visit(Tree nonNode) {
			}
			public void visit(_baseNode node) {
				if (node == new_node || node instanceof id) {
					elts.add(node);
				}
			}
		});
		return elts;
	}

	private static void raise_application_error_to_raise_exception(
			_baseNode node) {
		if (node instanceof general_element) {
			general_element ge = (general_element)node;
			if (ge.general_element_items.size() == 2
				&& ge.general_element_items.get(1) instanceof function_argument
				&& AstUtil.normalizeId(((general_element_id)ge.general_element_items.get(0)).id.value)
				.equals("RAISE_APPLICATION_ERROR")
			) {
				function_argument args = (function_argument)ge.general_element_items.get(1);
			}
			
		}
	}
}
