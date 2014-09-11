package parser.ast.transforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import br.com.porcelli.parser.plsql.PLSQLParser;

public class OracleOuterJoinTransformer {
	/*
	 * See http://etutorials.org/SQL/Mastering+Oracle+SQL/Appendix+A.+Oracles+Old+Join+Syntax/A.2+Old+Outer+Join+Syntax/
	 * for information on old outer join
	 * 
	 */
	
	
	public static void transform(Tree queryBlockNode) throws Exception {
		OracleOuterJoinTransformer transformer = new OracleOuterJoinTransformer(queryBlockNode);
		transformer.transform();
	}
	
	Tree queryBlockNode;
	
	private OracleOuterJoinTransformer(Tree queryBlockNode) throws Exception {
		if (queryBlockNode.getType() != PLSQLParser.SQL92_RESERVED_SELECT) {
			throw new Exception("Wrong queryBlockNode.type");
		}
		this.queryBlockNode = queryBlockNode;
	}

	private void transform() {
		findQueryClauses();
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

	Tree fromNode;
	Tree whereNode;
	Map<String, Tree> tableRefElements = new HashMap<String, Tree>(); // Tree is TABLE_REF_ELEMENT
	List<Tree> outerJoinExpressions = new ArrayList<Tree>(); // Tree is OUTER_JOIN_SIGN
	List<OuterJoinPredicate> outerJoinPredicates = new ArrayList<OuterJoinPredicate>();
	List<String> aliasOrder = new ArrayList<String>();
	List<OuterJoinNode> rootJoinNodes = new ArrayList<OuterJoinNode>();
	List<OuterJoinNode> allJoinNodes = new ArrayList<OuterJoinNode>();
	
	static class OuterJoinPredicate {
		public String sourceAlias;
		public String targetAlias;
		public Tree equalsOpTree;
	}
	
	public static class OuterJoinNode {
		public String alias;
		public List<OuterJoinNode> childNodes = new ArrayList<OuterJoinNode>();
	}

	private void findQueryClauses() {
		fromNode = AstUtil.getChildOfType(queryBlockNode, PLSQLParser.SQL92_RESERVED_FROM);
		whereNode = AstUtil.getChildOfType(queryBlockNode, PLSQLParser.SQL92_RESERVED_WHERE);
	}
	
	private void findTableRefs() {
		for (int i = 0; i < fromNode.getChildCount(); ++i) {
			Tree tableRefNode = fromNode.getChild(i);
			AstUtil.assertThat(tableRefNode.getType() == PLSQLParser.TABLE_REF);
			if (AstUtil.getChildrenOfType(tableRefNode, PLSQLParser.JOIN_DEF).size() > 0) {
				continue;
			}
			for (int j = 0; j < tableRefNode.getChildCount(); ++j) {
				Tree tableRefElementNode = tableRefNode.getChild(0);
				AstUtil.assertThat(tableRefElementNode.getType() == PLSQLParser.TABLE_REF_ELEMENT);
				Tree alias = AstUtil.getChildOfType(tableRefElementNode, PLSQLParser.TABLE_ALIAS);
				
				Tree tableExpressionNode = AstUtil.getChildOfType(tableRefElementNode, PLSQLParser.TABLE_EXPRESSION);
				if (tableExpressionNode.getChild(0).getType() != PLSQLParser.DIRECT_MODE) {
					continue;
				}
				
				String idString;
				if (alias != null) {
					Tree aliasId = alias.getChild(0);
					AstUtil.assertThat(aliasId.getType() == PLSQLParser.ID);
					
					idString = aliasId.getText();
					idString = AstUtil.normalizeId(idString);
				} else {
					continue;
				}
				
				tableRefElements.put(idString, tableRefElementNode);
				
				System.out.printf("Found direct table ref: alias: '%s', tree:\n%s\n", idString, AstPrinter.prettyPrint(tableRefElementNode));
			}
		}
	}

	private void findOuterJoinSigns() {
		if (whereNode != null) {
			Tree logicExprNode = whereNode.getChild(0);
			AstUtil.assertThat(logicExprNode.getType() == PLSQLParser.LOGIC_EXPR);
			Tree exprElementNode = logicExprNode.getChild(0);
			findOuterJoinSigns(exprElementNode);
		}
	}

	private void findOuterJoinSigns(Tree node) {
		// See the link at top of file;
		// (+)'s may only be joined with 'and'
		if (node.getType() == PLSQLParser.OUTER_JOIN_SIGN) {
			outerJoinExpressions.add(node);
			System.out.printf("Found outer join node at:\n%s\n", AstPrinter.prettyPrint(node.getParent()));
			AstUtil.assertThat(node.getParent().getType() == PLSQLParser.EQUALS_OP);
		} if (node.getType() == PLSQLParser.SQL92_RESERVED_AND || node.getType() == PLSQLParser.EQUALS_OP) {
			findOuterJoinSigns(node.getChild(0));
			findOuterJoinSigns(node.getChild(1));
		}
	}

	private void parseOuterJoinPredicates() {
		for (Tree outerJoinSignNode: outerJoinExpressions) {
			Tree parentEqualsOp = outerJoinSignNode.getParent();
			JoinSide side1 = parseJoinSide(parentEqualsOp.getChild(0));
			JoinSide side2 = parseJoinSide(parentEqualsOp.getChild(1));
			AstUtil.assertThat(side1.isOuterSide || side2.isOuterSide);
			AstUtil.assertThat(!side1.isOuterSide || !side2.isOuterSide);
			OuterJoinPredicate predicate = new OuterJoinPredicate();
			predicate.sourceAlias = side1.isOuterSide ? side2.alias : side1.alias;
			predicate.targetAlias = side1.isOuterSide ? side1.alias : side2.alias;
			predicate.equalsOpTree = parentEqualsOp;
			System.out.printf("Analyzed join predicate: %s -> %s with:\n%s\n", predicate.sourceAlias, predicate.targetAlias, AstPrinter.prettyPrint(parentEqualsOp));
			outerJoinPredicates.add(predicate);
		}
	}

	static class JoinSide {
		public String alias;
		public Tree exprNode;
		public boolean isOuterSide;
	}
	
	private JoinSide parseJoinSide(Tree equalsChild) {
		if (equalsChild.getType() == PLSQLParser.OUTER_JOIN_SIGN) {
			JoinSide result = parseJoinSide(equalsChild.getChild(0));
			result.isOuterSide = true;
			return result;
		} else {
			AstUtil.assertThat(equalsChild.getType() == PLSQLParser.CASCATED_ELEMENT);
			JoinSide result = new JoinSide();
			result.exprNode = equalsChild;
			List<String> cascadedIds = convertCascadedElementToIds(equalsChild);
			AstUtil.assertThat(cascadedIds.size() == 2, "Unexpected number of ID elements in cascaded element");
			result.alias = cascadedIds.get(0);
			return result;
		}
	}

	private List<String> convertCascadedElementToIds(Tree cascadedElement) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < cascadedElement.getChildCount(); ++i) {
			Tree anyElement = cascadedElement.getChild(i);
			AstUtil.assertNodeType(anyElement, PLSQLParser.ANY_ELEMENT);
			Tree idNode = anyElement.getChild(0);
			AstUtil.assertNodeType(idNode, PLSQLParser.ID);
			String idString = AstUtil.normalizeId(idNode.getText());
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
			sourceNode.childNodes.add(targetNode);
			rootNodes.remove(targetNode);
		}
		
		rootJoinNodes.addAll(rootNodes);
		allJoinNodes.addAll(joinNodes.values());
	}

	private void rewriteTree() {
		// 1) Remove referenced nodes
		for (OuterJoinNode joinNode: allJoinNodes) {
			Tree tableRefElementNode = this.tableRefElements.get(joinNode.alias);
			Tree tableRefNode = tableRefElementNode.getParent();
			tableRefNode.deleteChild(tableRefElementNode.getChildIndex());
			tableRefNode.getParent().deleteChild(tableRefNode.getChildIndex());
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
			Tree tableRefNode = AstUtil.createAstNode(PLSQLParser.TABLE_REF);
			tableRefNode.addChild(tableRefElements.get(rootJoinNode.alias));
			for (OuterJoinNode targetJoinNode: rootJoinNode.childNodes) {
				addJoinNode(tableRefNode, rootJoinNode, targetJoinNode);
			}
			fromNode.addChild(tableRefNode);
		}
	}

	private void addJoinNode(Tree tableRefNode, OuterJoinNode source, OuterJoinNode target) {
		List<Tree> joinExpressions = getJoinExpressions(source.alias, target.alias);
		for (Tree joinExpr: joinExpressions) {
			removeNodeFromWhere(joinExpr);
		}
		
		Tree joinExpr = null;
		for (Tree expr: joinExpressions) {
			Tree lhs = expr.getChild(0);
			Tree rhs = expr.getChild(1);
			if (lhs.getType() == PLSQLParser.OUTER_JOIN_SIGN) {
				lhs = lhs.getChild(0);
			}
			if (rhs.getType() == PLSQLParser.OUTER_JOIN_SIGN) {
				rhs = rhs.getChild(0);
			}
			Tree condition = AstUtil.createAstNode(PLSQLParser.EQUALS_OP, lhs, rhs);
			if (joinExpr == null) {
				joinExpr = condition;
			} else {
				joinExpr = AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_AND,
					joinExpr,
					condition
				);
			}
		}
		
		Tree joinDefNode = AstUtil.createAstNode(PLSQLParser.JOIN_DEF,
			AstUtil.createAstNode(PLSQLParser.LEFT_VK),
			tableRefElements.get(target.alias),
			AstUtil.createAstNode(PLSQLParser.SQL92_RESERVED_ON,
				AstUtil.createAstNode(PLSQLParser.LOGIC_EXPR, joinExpr)));
		
		tableRefNode.addChild(joinDefNode);
		
		for (OuterJoinNode childNode: target.childNodes) {
			addJoinNode(tableRefNode, target, childNode);
		}
	}

	private List<Tree> getJoinExpressions(String sourceAlias, String targetAlias) {
		List<Tree> result = new ArrayList<Tree>();
		for (OuterJoinPredicate predicate: outerJoinPredicates) {
			if (predicate.sourceAlias.equals(sourceAlias) && predicate.targetAlias.equals(targetAlias)) {
				result.add(predicate.equalsOpTree);
			}
		}
		return result;
	}

	private void removeNodeFromWhere(Tree joinExpr) {
		Tree node = joinExpr;
		Tree parent = node.getParent();
		parent.deleteChild(node.getChildIndex());
		if (parent.getType() == PLSQLParser.LOGIC_EXPR) {
			whereNode.getParent().deleteChild(whereNode.getChildIndex());
		} else if (parent.getType() == PLSQLParser.SQL92_RESERVED_AND) {
			Tree grandParent = parent.getParent();
			Tree sibling = parent.getChild(0);
			parent.deleteChild(sibling.getChildIndex());
			grandParent.replaceChildren(parent.getChildIndex(), parent.getChildIndex(), sibling);
		} else {
			AstUtil.assertThat(false);
		}
	}
}
