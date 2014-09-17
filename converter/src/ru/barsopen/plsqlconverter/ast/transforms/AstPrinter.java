package ru.barsopen.plsqlconverter.ast.transforms;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTreeNodeStream;
import org.antlr.runtime.tree.Tree;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.AngleBracketTemplateLexer;

import ru.barsopen.plsqlconverter.ast.DerivedSqlPrinter;
import ru.barsopen.plsqlconverter.util.ReflectionUtil;

public class AstPrinter {

	public static String prettyPrint(org.antlr.runtime.tree.Tree tree) {
		StringBuilder sb = new StringBuilder();
		prettyPrint(tree, sb, 0);
		return sb.toString();
	}

	static void printIndent(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; ++i) {
			sb.append("  ");
		}
	}
	
	private static void prettyPrint(org.antlr.runtime.tree.Tree tree, StringBuilder sb, int indent) {
		sb.append('(');
		String nodeText = prettyPrintNodeTag(tree);
		sb.append(nodeText);
		if (tree.getChildCount() == 1 && tree.getChild(0).getChildCount() == 0) {
			sb.append(" ");
			org.antlr.runtime.tree.Tree childNode = tree.getChild(0);
			String childNodeText = prettyPrintNodeTag(childNode);
			sb.append(childNodeText);
		} else {
			for (int i = 0; i < tree.getChildCount(); ++i) {
				org.antlr.runtime.tree.Tree childNode = tree.getChild(i);
				sb.append("\n");
				printIndent(sb, indent + 1);
				if (childNode.getChildCount() == 0) {
					String childNodeText = prettyPrintNodeTag(childNode);
					sb.append(childNodeText);
				} else {
					prettyPrint(childNode, sb, indent + 1);
				}
			}
		}
		sb.append(")");
	}
	
	private static String prettyPrintNodeTag(Tree tree) {
		int ttype = tree.getType();
		String tokenName = ttype >= 0 && ttype < AstUtil.tokenNames.length ? AstUtil.tokenNames[ttype] : "";
		String text = tree.getText();
		if (tokenName.equals(text)) {
			return tokenName;
		} else {
			//int ntype = Arrays.asList(PLSQLParser.tokenNames).indexOf(tree.getText());
			return String.format("%s[%s]", tokenName, text);
		}
	}

	public static PrintResult printTreeToOracleString(org.antlr.runtime.tree.Tree theTree, String treeType)
			throws IOException, RecognitionException {
		DerivedSqlPrinter printer = new DerivedSqlPrinter(new CommonTreeNodeStream(theTree));
		
		try (InputStream templateInputStream = AstPrinter.class.getClassLoader().getResourceAsStream("ru/barsopen/plsqlconverter/ast/transforms/PLSQLPrinterTemplates.stg")) {
			StringTemplateGroup templateGroup = new StringTemplateGroup(new InputStreamReader(templateInputStream, Charset.forName("UTF-8")), AngleBracketTemplateLexer.class);
			printer.setTemplateLib(templateGroup);
		}
		StringTemplate printedTemplate = (StringTemplate)ReflectionUtil.getField(ReflectionUtil.callMethod(printer, treeType), "st");
		String printed = printedTemplate.toString();
		PrintResult result = new PrintResult();
		result.printErrors = printer.errors;
		result.text = printed;
		return result;
	}

	public static PrintResult printTreeToPostgresqlString(org.antlr.runtime.tree.Tree theTree, String treeType)
			throws IOException, RecognitionException {
		CommonTreeNodeStream stream = new CommonTreeNodeStream(theTree);
		List<Object> tokens = new ArrayList<Object>();
		while (true) {
			Object next = stream.nextElement();
			if (stream.isEOF(next)) {
				break;
			}
			tokens.add(next);
		}
		DerivedSqlPrinter printer = new DerivedSqlPrinter(new CommonTreeNodeStream(theTree));
		
		try (InputStream templateInputStream = AstPrinter.class.getClassLoader().getResourceAsStream("ru/barsopen/plsqlconverter/ast/transforms/PLPGSQLPrinterTemplates.stg")) {
			StringTemplateGroup templateGroup = new StringTemplateGroup(new InputStreamReader(templateInputStream, Charset.forName("UTF-8")), AngleBracketTemplateLexer.class);
			printer.setTemplateLib(templateGroup);
		}
		StringTemplate printedTemplate = (StringTemplate)ReflectionUtil.getField(ReflectionUtil.callMethod(printer, treeType), "st");
		String printed = printedTemplate.toString();
		PrintResult result = new PrintResult();
		result.printErrors = printer.errors;
		result.text = printed;
		return result;
	}
}
