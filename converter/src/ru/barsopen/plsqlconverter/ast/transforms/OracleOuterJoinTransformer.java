package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.tree.Tree;

import ru.barsopen.plsqlconverter.ast.typed.*;
import br.com.porcelli.parser.plsql.PLSQLParser;

public class OracleOuterJoinTransformer {
	/*
	 * See http://etutorials.org/SQL/Mastering+Oracle+SQL/Appendix+A.+Oracles+Old+Join+Syntax/A.2+Old+Outer+Join+Syntax/
	 * for information on old outer join
	 * 
	 */
	
	public static boolean isDebugEnabled = true;
	
	public static void transformAllQueries(_baseNode tree) throws Exception {
		List<query_block> queryBlocks = AstUtil.getDescendantsOfType(tree, query_block.class);
		for (query_block queryBlock: queryBlocks) {
			try {
				transformQueryBlock(queryBlock);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void transformQueryBlock(query_block queryBlockNode) throws Exception {
		OracleOuterJoinTransformer transformer = new OracleOuterJoinTransformer(queryBlockNode);
		transformer.transform();
	}
	

	query_block query;
	
	private OracleOuterJoinTransformer(query_block queryBlockNode) throws Exception {
		query = queryBlockNode;
	}

	private void transform() {
		if (query.from_clause == null) {
			return;
		}
		findTableRefs();
		findOuterJoinSigns();
		if (outerJoinExpressions.size() == 0) {
			return;
		}
		parseOuterJoinPredicates();
		computeJoinOrder();
		rewriteTree();
	}
	
	// SELECT (queryBlockNode)
	//  FROM (fromNode)
	//   TABLE_REF*
	//     TABLE_REF_ELEMENT
	//       ALIAS?
	//         ID
	//       TABLE_EXPRESSION
	//         (DIRECT_MODE|COLLECTION_MODE|SELECT_MODE|CASCATED_ELEMENT|STANDARD_FUNCTION|TABLE_REF)
	//     JOIN_DEF*
	//  WHERE
	//   LOGIC_EXPR
	//     ...

	Map<String, table_ref_aux> tableRefElements = new HashMap<String, table_ref_aux>(); // Tree is TABLE_REF_ELEMENT
	List<expression_element_outer_join_sign> outerJoinExpressions = new ArrayList<expression_element_outer_join_sign>(); // Tree is OUTER_JOIN_SIGN
	List<OuterJoinPredicate> outerJoinPredicates = new ArrayList<OuterJoinPredicate>();
	List<String> aliasOrder = new ArrayList<String>();
	List<OuterJoinNode> rootJoinNodes = new ArrayList<OuterJoinNode>();
	List<OuterJoinNode> allJoinNodes = new ArrayList<OuterJoinNode>();
	
	static class OuterJoinPredicate {
		public String sourceAlias;
		public String targetAlias;
		public expression_element_eq equalsOpTree;
	}
	
	public static class OuterJoinNode {
		public String alias;
		public List<OuterJoinNode> childNodes = new ArrayList<OuterJoinNode>();
	}
	
	private void findTableRefs() {
		for (table_ref tableRef: query.from_clause.table_refs) {
			if (tableRef.join_clauses.size() > 0) {
				continue;
			}
			dml_table_expression_clause tableOrRef = tableRef.table_ref_aux.dml_table_expression_clause;
			if (!(tableOrRef instanceof table_expression)) {
				continue;
			}
			
			table_expression te = (table_expression)tableOrRef;
			String idString;
			if (tableRef.table_ref_aux.alias != null) {
				table_alias alias = (table_alias)tableRef.table_ref_aux.alias;
				idString = AstUtil.normalizeId(alias.id.value);
			} else if (te.table_expression_element instanceof direct_mode) {
				direct_mode dm = (direct_mode)te.table_expression_element;
				id lastId = dm.tableview_name.ids.get(dm.tableview_name.ids.size() - 1);
				idString = AstUtil.normalizeId(lastId.value);
			} else {
				continue;
			}
				
			tableRefElements.put(idString, tableRef.table_ref_aux);
				
			if (isDebugEnabled) {
				System.out.printf("Found direct table ref: alias: '%s', tree:\n%s\n", idString, AstPrinter.prettyPrint(tableRef.unparse()));
			}
		}
	}

	private void findOuterJoinSigns() {
		if (query.where_clause != null) {
			logic_expression logicExpr = (logic_expression)query.where_clause.expression;
			findOuterJoinSigns(logicExpr.expression_element);
		}
	}

	private void findOuterJoinSigns(expression_element expr) {
		// See the link at top of file;
		// (+)'s may only be joined with 'and'
		if (expr instanceof expression_element_outer_join_sign) {
			outerJoinExpressions.add((expression_element_outer_join_sign)expr);
			if (isDebugEnabled) {
				System.out.printf("Found outer join node at:\n%s\n", AstPrinter.prettyPrint(expr._getParent().unparse()));
			}
			AstUtil.assertThat(expr._getParent() instanceof expression_element_eq);
		} if (expr instanceof expression_element_and) {
			expression_element_and andExpr = (expression_element_and)expr;
			findOuterJoinSigns(andExpr.lhs);
			findOuterJoinSigns(andExpr.rhs);
		} else if (expr instanceof expression_element_eq) {
			expression_element_eq andExpr = (expression_element_eq)expr;
			findOuterJoinSigns(andExpr.lhs);
			findOuterJoinSigns(andExpr.rhs);
		}
	}

	private void parseOuterJoinPredicates() {
		for (expression_element_outer_join_sign outerJoinSignNode: outerJoinExpressions) {
			expression_element_eq parentEqualsOp = (expression_element_eq)outerJoinSignNode._getParent();
			JoinSide side1 = parseJoinSide(parentEqualsOp.lhs);
			JoinSide side2 = parseJoinSide(parentEqualsOp.rhs);
			AstUtil.assertThat(side1.isOuterSide || side2.isOuterSide);
			AstUtil.assertThat(!side1.isOuterSide || !side2.isOuterSide);
			OuterJoinPredicate predicate = new OuterJoinPredicate();
			predicate.sourceAlias = side1.isOuterSide ? side2.alias : side1.alias;
			predicate.targetAlias = side1.isOuterSide ? side1.alias : side2.alias;
			predicate.equalsOpTree = parentEqualsOp;
			if (isDebugEnabled) {
				System.out.printf("Analyzed join predicate: %s -> %s with:\n%s\n", predicate.sourceAlias, predicate.targetAlias, AstPrinter.prettyPrint(parentEqualsOp.unparse()));
			}
			outerJoinPredicates.add(predicate);
		}
	}

	static class JoinSide {
		public String alias;
		public expression_element exprNode;
		public boolean isOuterSide;
	}
	
	private JoinSide parseJoinSide(expression_element equalsChild) {
		if (equalsChild instanceof expression_element_outer_join_sign) {
			JoinSide result = parseJoinSide(((expression_element_outer_join_sign)equalsChild).expr);
			result.isOuterSide = true;
			return result;
		} else {
			// 1) Найти в CASCATED_ELEMENT внутри equalsChild
			// 2) Отобрать из них те, которые содержат ровно два ANY_ELEMENT
			// 3) Отобрать из них все алиасы
			// 4) Алиас должен остаться только один
			// 1
			List<general_element> elements_2 = AstUtil.getDescendantsOfType(equalsChild, general_element.class);
			// 2
			List<general_element> descendantCascadedElements_2 = new ArrayList<general_element>();
			for (general_element elt: elements_2) {
				if (elt.general_element_items.size() != 2) {
					continue;
				}
				if (elt.general_element_items.get(0) instanceof general_element_id
					&& elt.general_element_items.get(1) instanceof general_element_id) {
					descendantCascadedElements_2.add(elt);
					if (isDebugEnabled) {
						System.out.printf("Found alias candidate node: %s\n", AstPrinter.prettyPrint(elt.unparse()));
					}
				}
			}
			// 3
			Set<String> aliases = new HashSet<String>();
			String alias = null;
			for (general_element elt: descendantCascadedElements_2) {
				List<String> cascadedIds = convertCascadedElementToIds(elt);
				AstUtil.assertThat(cascadedIds.size() == 2, "Unexpected number of ID elements in cascaded element");
				alias = cascadedIds.get(0);
				aliases.add(alias);
			}
			// 4
			AstUtil.assertThat(aliases.size() <= 1,
					String.format("Argument of outer join equals expression at %d:%d uses more than one alias", equalsChild._getLine(), equalsChild._getCol())
				);
			AstUtil.assertThat(aliases.size() > 0,
					String.format("Argument of outer join equals expression at %d:%d does not uses aliases", equalsChild._getLine(), equalsChild._getCol())
				);
			JoinSide result = new JoinSide();
			result.exprNode = equalsChild;
			result.alias = alias;
			return result;
		}
	}

	private List<String> convertCascadedElementToIds(general_element cascadedElement) {
		ArrayList<String> result = new ArrayList<String>();
		for (general_element_item item: cascadedElement.general_element_items) {
			String idString = ((general_element_id)item).id.value;
			idString = AstUtil.normalizeId(idString);
			result.add(idString);
		}
		return result;
	}

	private void computeJoinOrder() {
		Map<String, OuterJoinNode> joinNodes = new HashMap<String, OuterJoinNode>();
		Set<OuterJoinNode> rootNodes = new HashSet<OuterJoinNode>();
		Set<String> allAliases = new HashSet<String>();
		
		for (OuterJoinPredicate predicate: outerJoinPredicates) {
			allAliases.add(predicate.sourceAlias);
			allAliases.add(predicate.targetAlias);
		}
		
		for (String alias: allAliases) {
			OuterJoinNode node = new OuterJoinNode();
			node.alias = alias;
			joinNodes.put(alias, node);
			rootNodes.add(node);
		}
		
		for (OuterJoinPredicate predicate: outerJoinPredicates) {
			OuterJoinNode sourceNode = joinNodes.get(predicate.sourceAlias);
			OuterJoinNode targetNode = joinNodes.get(predicate.targetAlias);
			if (!sourceNode.childNodes.contains(targetNode)) {
				sourceNode.childNodes.add(targetNode);
			}
			rootNodes.remove(targetNode);
		}
		
		rootJoinNodes.addAll(rootNodes);
		allJoinNodes.addAll(joinNodes.values());
	}

	private void rewriteTree() {
		// 1) Remove referenced nodes
		for (OuterJoinNode joinNode: allJoinNodes) {
			table_ref_aux tra = this.tableRefElements.get(joinNode.alias);
			table_ref tr = (table_ref)tra._getParent();
			tr.set_table_ref_aux(null);
			query.from_clause.remove_table_refs(tr);
		}
		
		// SELECT (queryBlockNode)
		//  FROM (fromNode)
		//   TABLE_REF*
		//     TABLE_REF_ELEMENT
		//       ALIAS?
		//         ID
		//       TABLE_EXPRESSION
		//         (DIRECT_MODE|COLLECTION_MODE|SELECT_MODE|CASCATED_ELEMENT|STANDARD_FUNCTION|TABLE_REF)
		//     JOIN_DEF*
		//       (LEFT_VK|RIGHT_VK|FULL_VK|INNER_VK)?
		//       TABLE_REF_ELEMENT
		//       ON
		//         LOGIC_EXPR
		//  WHERE
		//   LOGIC_EXPR
		//     ...
		// 2) walk from join roots, add join trees
		for (OuterJoinNode rootJoinNode: rootJoinNodes) {
			table_ref tr = new table_ref();
			tr.set_table_ref_aux(tableRefElements.get(rootJoinNode.alias));
			for (OuterJoinNode targetJoinNode: rootJoinNode.childNodes) {
				addJoinNode(tr, rootJoinNode, targetJoinNode);
			}
			query.from_clause.add_table_refs(tr);
		}
	}

	private void addJoinNode(table_ref tableRefNode, OuterJoinNode source, OuterJoinNode target) {
		List<expression_element_eq> joinExpressions = getJoinExpressions(source.alias, target.alias);
		for (expression_element_eq joinExpr: joinExpressions) {
			removeNodeFromWhere(joinExpr);
		}
		
		expression_element joinExpr = null;
		for (expression_element_eq expr: joinExpressions) {
			expression_element lhs = expr.lhs;
			expression_element rhs = expr.rhs;
			if (lhs instanceof expression_element_outer_join_sign) {
				lhs = ((expression_element_outer_join_sign)lhs).expr;
			}
			if (rhs instanceof expression_element_outer_join_sign) {
				rhs = ((expression_element_outer_join_sign)rhs).expr;
			}
			expression_element_eq condition = parser.make_expression_element_eq(lhs, rhs);
			if (joinExpr == null) {
				joinExpr = condition;
			} else {
				joinExpr = parser.make_expression_element_and(joinExpr, condition);
			}
		}
		
		join_clause jc = new join_clause();
		jc.LEFT_VK = AstUtil.createAstNode(PLSQLParser.LEFT_VK);
		jc.set_table_ref_aux(tableRefElements.get(target.alias));
		jc.join_on_part = parser.make_join_on_part(parser.make_logic_expression(joinExpr));
		
		tableRefNode.add_join_clauses(jc);
		
		for (OuterJoinNode childNode: target.childNodes) {
			addJoinNode(tableRefNode, target, childNode);
		}
	}

	private List<expression_element_eq> getJoinExpressions(String sourceAlias, String targetAlias) {
		List<expression_element_eq> result = new ArrayList<expression_element_eq>();
		for (OuterJoinPredicate predicate: outerJoinPredicates) {
			if (predicate.sourceAlias.equals(sourceAlias) && predicate.targetAlias.equals(targetAlias)) {
				result.add(predicate.equalsOpTree);
			}
		}
		return result;
	}

	private void removeNodeFromWhere(expression_element_eq joinExpr) {
		expression_element node = joinExpr;
		if (node._getParent() instanceof logic_expression) {
			query.set_where_clause(null);
		} else {
			expression_element_and pa = (expression_element_and)node._getParent();
			expression_element other = pa.lhs == node ? pa.rhs : pa.lhs;
			if (pa._getParent() instanceof expression_element_and) {
				expression_element_and gpa = (expression_element_and)pa._getParent();
				if (gpa.lhs == pa) {
					gpa.set_lhs(other);
				} else {
					gpa.set_rhs(other);
				}
			} else {
				logic_expression le = (logic_expression)pa._getParent();
				le.set_expression_element(other);
			}
		}
	}
}
