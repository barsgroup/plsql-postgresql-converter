package parser;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.tree.Tree;

import parser.ast.transforms.AstUtil;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class SqlPrinter /*extends TreeWalker */ {
	StringBuilder output;
	
	public SqlPrinter(StringBuilder output) {
		this.output = output;
	}
	
	public void visitNode(Tree node) {
		String result = printNode(node);
		output.append(result);
	}
	
	private static Tree getChildOfType(Tree node, int type) {
		for (int i = 0; i < node.getChildCount(); ++i) {
			if (node.getChild(i).getType() == type) {
				return node.getChild(i);
			}
		}
		return null;
	}
	
	private static List<Tree> getChildrenOfType(Tree node, int type) {
		List<Tree> result = new ArrayList<Tree>();
		for (int i = 0; i < node.getChildCount(); ++i) {
			if (node.getChild(i).getType() == type) {
				result.add(node.getChild(i));
			}
		}
		return result;
	}
	
	private void assertThat(boolean condition, String messageIfFailed) {
		if (!condition) {
			throw new RuntimeException(messageIfFailed);
		}
	}
	
	private void assertTodo(boolean condition) {
		if (!condition) {
			throw new RuntimeException("TODO");
		}
	}
	
	private void assertTodo(Tree... nodes) {
		for (Tree node: nodes) {
			if (node != null) {
				throw new RuntimeException("TODO");
			}
		}
	}

	private String printNode(Tree node) {
		switch (node.getType()) {
		case PLSQLParser.SELECT_STATEMENT: return print_select_statement(node);
		default:
			return String.format("<Unprintable: %s>\n", AstUtil.tokenNames[node.getType()]);
		}
	}

	private String print_select_statement(Tree node) {
		Tree subqueryFactoringClause = getChildOfType(node, PLSQLParser.FACTORING);
		Tree subquery = getChildOfType(node, PLSQLParser.SUBQUERY);
		Tree forUpdateClause = getChildOfType(node, PLSQLParser.SQL92_RESERVED_FOR);
		Tree orderByClause = getChildOfType(node, PLSQLParser.SQL92_RESERVED_ORDER);

		return print_subquery(subquery);
	}

	private String print_subquery(Tree subquery) {
		String result = print_subquery_basic_elements(subquery.getChild(0));
		for (int i = 1; i < subquery.getChildCount(); ++i) {
			result += " " + print_subquery_operation_part(subquery.getChild(i));
		}
		return result;
	}

	private String print_subquery_basic_elements(Tree subquery_basic_elements) {
		if (subquery_basic_elements.getType() == PLSQLParser.SQL92_RESERVED_SELECT) {
			return print_query_block(subquery_basic_elements);
		} else if (subquery_basic_elements.getType() == PLSQLParser.SUBQUERY) {
			return print_subquery(subquery_basic_elements);
		} else {
			throw new RuntimeException();
		}
	}

	private String print_query_block(Tree query_block) {
		Tree from_clause = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_FROM);
		Tree distinct_node = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_DISTINCT);
		Tree unique_node = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_UNIQUE);
		Tree all_node = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_ALL);
		Tree asterisk_node = getChildOfType(query_block, PLSQLParser.ASTERISK);
		Tree select_list = getChildOfType(query_block, PLSQLParser.SELECT_LIST);
		Tree into_clause = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_INTO);
		Tree where_clause = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_WHERE);
		Tree hierarchical_query_clause = getChildOfType(query_block, PLSQLParser.HIERARCHICAL);
		Tree group_by_clause = getChildOfType(query_block, PLSQLParser.SQL92_RESERVED_GROUP);
		Tree model_clause = getChildOfType(query_block, PLSQLParser.PLSQL_NON_RESERVED_MODEL);

		assertTodo(distinct_node, unique_node, all_node, select_list, into_clause, where_clause, hierarchical_query_clause, group_by_clause, model_clause);

		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		return "";
	}

	private String print_subquery_operation_part(Tree child) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
