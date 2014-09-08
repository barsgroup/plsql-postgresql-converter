package parser;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.Tree;

public class TokenCounter {
	Map<Integer, Integer> occurences = new HashMap<Integer, Integer>();
	
	public void addTree(Tree tree) {
		new CounterWalker().visitNode(tree);
	}
	
	public Map<Integer, Integer> getOccurences() {
		return occurences;
	}
	
	class CounterWalker extends TreeWalker {
		@Override
		public Tree visitNode(Tree node) {
			int type = node.getType();
			if (!occurences.containsKey(type)) {
				occurences.put(type, 1);
			} else {
				occurences.put(type, occurences.get(type) + 1);
			}
			return super.visitNode(node);
		}
	}
}
