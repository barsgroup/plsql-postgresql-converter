package parser.ast.transforms;

import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

public class ParseResult {
	public List<RecognitionException> lexerErrors;
	public List<RecognitionException> parserErrors;
	public Tree tree;
}
