package ru.barsopen.plsqlconverter.ast.transforms;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class AstXml {
	public static org.w3c.dom.Document astToXml(List<Token> tokens, Tree tree) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		// root elements
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("parseResult");
		doc.appendChild(root);
		
		addTokens(root, tokens);
		addTree(root, tree);
		
		return doc;
	}
	
	private static void addTree(Element root, Tree tree) {
		Element treeElement = root.getOwnerDocument().createElement("tree");
		root.appendChild(treeElement);
		addNode(treeElement, tree);
	}

	private static void addNode(Element parentElt, Tree node) {
		Element elt = parentElt.getOwnerDocument().createElement(AstUtil.tokenNames[node.getType()]);
		parentElt.appendChild(elt);

		//addAttr(elt, "type", AstUtil.tokenNames[node.getType()]);
		//addAttr(elt, "type_num", node.getType());
		addAttr(elt, "text", node.getText());
		addAttr(elt, "col", node.getCharPositionInLine());
		addAttr(elt, "line", node.getLine());
		addAttr(elt, "tokenStartIndex", node.getTokenStartIndex());
		addAttr(elt, "tokenStopIndex", node.getTokenStopIndex());
		
		for (int i = 0; i < node.getChildCount(); ++i) {
			addNode(elt, node.getChild(i));
		}
	}

	public static String xmlToString(org.w3c.dom.Document doc) throws Exception {
		StringWriter writer = new StringWriter();
		TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute("indent-number", 2);
		Transformer transformer = tf.newTransformer();
		//transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString();
		return output;
	}

	public static org.w3c.dom.Document stringToXml(String xmlString) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document result = docBuilder.parse(new InputSource(new StringReader(xmlString)));
		return result;
	}

	private static void addTokens(Element root, List<Token> tokens) {
		Element tokensElement = root.getOwnerDocument().createElement("tokens");
		root.appendChild(tokensElement);
		
		for (Token token: tokens) {
			String typeStr = token.getType() == Token.EOF ? "EOF" : AstUtil.tokenNames[token.getType()];
			Element tokenElement = root.getOwnerDocument().createElement(typeStr);
			tokensElement.appendChild(tokenElement);
			addAttr(tokenElement, "channel", token.getChannel());
			addAttr(tokenElement, "col", token.getCharPositionInLine());
			addAttr(tokenElement, "line", token.getLine());
			addAttr(tokenElement, "text", token.getText());
			addAttr(tokenElement, "index", token.getTokenIndex());
			//addAttr(tokenElement, "type", typeStr);
			//addAttr(tokenElement, "type_num", token.getType());
		}
	}
	
	private static void addAttr(Element elt, String name, Object value) {
		elt.setAttribute(name, value.toString());
	}
	
	public static ParseResult xmlToAst(Document doc) throws Exception {
		Element root = doc.getDocumentElement();
		List<Token> tokens = getTokens(root);
		Tree tree = getTree(root);

		ParseResult result = new ParseResult();
		result.lexerErrors = new ArrayList<RecognitionException>();
		result.parserErrors = new ArrayList<RecognitionException>();
		result.tokens = tokens;
		result.tree = tree;
		
		return result;
	}

	private static List<Token> getTokens(Element root) throws Exception {
		Element tokensElement = null;
		{
			NodeList childNodes = root.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); ++i) {
				Node node = childNodes.item(i);
				if (node instanceof Element) {
					Element elt = (Element)node;
					if (elt.getNodeName().equals("tokens")) {
						tokensElement = elt;
					}
				}
			}
		}
		if (tokensElement == null) {
			throw new Exception("<tokens> element not found");
		}
		List<Token> tokens = new ArrayList<Token>();
		NodeList childNodes = tokensElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node node = childNodes.item(i);
			if (!(node instanceof Element)) {
				continue;
			}
			Element elt = (Element)node;
			
			int type = AstUtil.tokenName2NumberMap.get(elt.getNodeName());

			Token token = new CommonToken(type);
			token.setChannel(Integer.valueOf(elt.getAttribute("channel")));
			token.setCharPositionInLine(Integer.valueOf(elt.getAttribute("col")));
			token.setLine(Integer.valueOf(elt.getAttribute("line")));
			token.setText(elt.getAttribute("text"));
			token.setTokenIndex(Integer.valueOf(elt.getAttribute("index")));
			tokens.add(token);
		}
		return tokens;
	}

	private static Tree getTree(Element root) throws Exception {
		Element treeElement = null;
		{
			NodeList childNodes = root.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); ++i) {
				Node node = childNodes.item(i);
				if (node instanceof Element) {
					Element elt = (Element)node;
					if (elt.getNodeName().equals("tree")) {
						treeElement = elt;
					}
				}
			}
		}
		if (treeElement == null) {
			throw new Exception("<tree> element not found");
		}
		Element treeRootElement = null;
		{
			NodeList childNodes = treeElement.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); ++i) {
				Node node = childNodes.item(i);
				if (node instanceof Element) {
					Element elt = (Element)node;
					treeRootElement = elt;
				}
			}
		}
		if (treeRootElement == null) {
			throw new Exception("Tree root element not found");
		}
		Tree result = getTreeNodeFromXmlElement(treeRootElement);
		return result;
	}

	private static Tree getTreeNodeFromXmlElement(Element elt) {
		int type = AstUtil.tokenName2NumberMap.get(elt.getNodeName());
		Token token = new CommonToken(type);

		token.setCharPositionInLine(Integer.valueOf(elt.getAttribute("col")));
		token.setLine(Integer.valueOf(elt.getAttribute("line")));
		token.setText(elt.getAttribute("text"));

		Tree tree = new CommonTree(token);

		tree.setTokenStartIndex(Integer.valueOf(elt.getAttribute("tokenStartIndex")));
		tree.setTokenStopIndex(Integer.valueOf(elt.getAttribute("tokenStopIndex")));
		
		NodeList childNodes = elt.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node childNode = childNodes.item(i);
			if (childNode instanceof Element) {
				Element childElement = (Element)childNode;
				Tree childTree = getTreeNodeFromXmlElement(childElement);
				tree.addChild(childTree);
			}
		}
		
		return tree;
	}
}
