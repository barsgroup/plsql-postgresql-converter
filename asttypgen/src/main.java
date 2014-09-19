import java.io.*;
import java.util.*;
import java.nio.charset.Charset;
import java.nio.file.*;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class main {
  public static void main(String[] args) throws Exception {
    String inputContent = new String(Files.readAllBytes(Paths.get(args[0])), Charset.forName("UTF-8"));
    ANTLRStringStream input = new ANTLRStringStream(inputContent);
    asttypgenLexer l = new asttypgenLexer(input);
    CommonTokenStream cts = new CommonTokenStream(l);
    asttypgenParser p = new asttypgenParser(cts);
    spec = p.astSpec();
    String json = new GsonBuilder().setPrettyPrinting().create().toJson(spec);
    //System.out.println(json);
    path = Paths.get(args[1], spec.packageName.toArray(new String[0]));
    Files.createDirectories(path);
    packageName = stringJoin(".", spec.packageName);
    tokenVocabName = stringJoin(".", spec.tokenVocabName);
    generateAstClasses();
  }
  
  static String stringJoin(String separator, List<String> parts) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < parts.size(); ++i) {
      if (i > 0) {
        sb.append(separator);
      }
      sb.append(parts.get(i));
    }
    return sb.toString();
  }
  
  static Path path;
  static AstNodes.AstSpec spec;
  static String packageName;
  static String tokenVocabName;
  static Map<String, List<String>> ruleInterfaces = new HashMap<String, List<String>>();
  
  public static void generateAstClasses() throws Exception {
    for (AstNodes.RuleSpec rule: spec.rules) {
      ruleInterfaces.put(rule.name, new ArrayList<String>());
    }
    for (AstNodes.RuleSpec rule: spec.rules) {
      if (rule instanceof AstNodes.RuleWithAlts) {
        for (String altName: ((AstNodes.RuleWithAlts)rule).alternatives) {
          if (!ruleInterfaces.containsKey(altName)) {
            System.err.printf("Rule %s references inexising rule %s\n", rule.name, altName);
            System.exit(1);
          }
          ruleInterfaces.get(altName).add(rule.name);
        }
      }
    }
    
    for (AstNodes.RuleSpec rule: spec.rules) {
      if (rule instanceof AstNodes.RuleWithoutAlts) {
        generateRuleWithoutAlternativesClass((AstNodes.RuleWithoutAlts)rule);
      } else {
        AstNodes.RuleWithAlts theRule = (AstNodes.RuleWithAlts)rule;
        generateRuleWithAlternativesClass(theRule);
      }
    }
    
    generateBaseClass();
    generateParserClass();
  }
  
  public static void generateRuleWithoutAlternativesClass(AstNodes.RuleWithoutAlts rule) throws Exception {
    File file = path.resolve(rule.name + ".java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      List<String> interfacesList = new ArrayList<String>(ruleInterfaces.get(rule.name));
      interfacesList.add("_baseNode");
      String interfaces = stringJoin(", ", interfacesList);
      out.printf("public class %s %s{\n", rule.name, interfaces.equals("") ? "" : String.format("implements %s ", interfaces));
      generateRuleBodyProperties(out, (AstNodes.RuleWithoutAlts)rule);
      generateRuleBodyUnparser(out, rule);
      out.printf("}\n");
    }
  }
  
  public static void generateRuleBodyProperties(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    out.printf("  public int _line = -1;\n");
    out.printf("  public int _col = -1;\n");
    out.printf("  public int _tokenStartIndex = -1;\n");
    out.printf("  public int _tokenStopIndex = -1;\n");
    out.printf("  public _baseNode _parent = null;\n");
    out.printf("  public _baseNode _getParent() { return _parent; }\n");
    out.printf("  public void _setParent(_baseNode value) { _parent = value; }\n");
    out.printf("  public void _setBaseNode(_baseNode value) { this._parent = value; }\n");
    out.printf("  public int _getLine() { return _line; }\n");
    out.printf("  public int _getCol() { return _col; }\n");
    out.printf("  public int _getTokenStartIndex() { return _tokenStartIndex; }\n");
    out.printf("  public int _getTokenStopIndex() { return _tokenStopIndex; }\n");
      
    for (AstNodes.RuleItem item: rule.body.items) {
      AstNodes.PropSpec propSpec = item.propSpec;
      boolean isToken = item.propMatchSpec.isToken();
      String type, initializer;
      String itemType = item.propMatchSpec.isTokenText ? "String" : isToken ? "org.antlr.runtime.tree.Tree" : item.propMatchSpec.name;
      if (propSpec.isArray) {
        type = String.format("java.util.List<%s>", itemType);
        initializer = String.format("new java.util.ArrayList<%s>()", itemType);
      } else {
        type = itemType;
        initializer = "null";
      }
      out.printf("  public %s %s = %s;\n", type, propSpec.name, initializer);
      out.printf("  public %s get_%s() { return this.%s; }\n", type, propSpec.name, propSpec.name);
      if (propSpec.isArray) {
        out.printf("  public void add_%s(%s value) {\n", propSpec.name, itemType);
        out.printf("    insert_%s(%s.size(), value);\n", propSpec.name, propSpec.name);
        out.printf("  }\n", propSpec.name, itemType);
        out.printf("  public void insert_%s(int pos, %s value) {\n", propSpec.name, itemType);
        out.printf("    this.%s.add(pos, value);\n", propSpec.name);
        if (!isToken) {
          out.printf("    value._setParent(this);\n", propSpec.name);
        }
        out.printf("  }\n", propSpec.name, itemType);
        out.printf("  public void remove_%s(int pos) {\n", propSpec.name);
        if (!isToken) {
          out.printf("    this.%s.get(pos)._setParent(null);\n", propSpec.name);
        }
        out.printf("    this.%s.remove(pos);\n", propSpec.name);
        out.printf("  }\n", propSpec.name, itemType);
        out.printf("  public void remove_%s(%s value) {\n", propSpec.name, itemType);
        out.printf("    this.remove_%s(this.%s.indexOf(value));\n", propSpec.name, propSpec.name);
        out.printf("  }\n", propSpec.name, itemType);
      } else {
        out.printf("  public void set_%s(%s value) {\n", propSpec.name, type);
        if (!isToken && !item.propMatchSpec.isTokenText) {
          out.printf("    if (this.%s != null) { this.%s._setParent(null); }\n", propSpec.name, propSpec.name);
        }
        out.printf("    this.%s = value;\n", propSpec.name);
        if (!isToken && !item.propMatchSpec.isTokenText) {
          out.printf("    if (this.%s != null) { this.%s._setParent(this); }\n", propSpec.name, propSpec.name);
        }
        out.printf("  }\n", type);
      }
      
      if (propSpec.isQuestion) {
        out.printf("  public boolean is_%s() { return this.%s != null; }\n", propSpec.name, propSpec.name);
      }
    }
    out.println();
    out.printf("  public void _walk(_visitor visitor) {\n");
    out.printf("    visitor.visit(this);\n");
      
    for (AstNodes.RuleItem item: rule.body.items) {
      AstNodes.PropSpec propSpec = item.propSpec;
      boolean isToken = item.propMatchSpec.isToken();
      boolean isText = item.propMatchSpec.isTokenText;
      boolean isArray = propSpec.isArray;
      String itemType = item.propMatchSpec.isTokenText ? "String" : isToken ? "org.antlr.runtime.tree.Tree" : item.propMatchSpec.name;
      
      if (isText) {
        continue;
      } else if (propSpec.isArray) {
        out.printf("    for (%s _value: this.%s) {\n", itemType, propSpec.name);
        if (isToken) {
          out.printf("      visitor.visit(_value);\n");
        } else {
          out.printf("      _value._walk(visitor);\n");
        }
        out.printf("    }\n");
      } else {
        out.printf("    if (this.%s != null) {\n", propSpec.name);
        if (isToken) {
          out.printf("      visitor.visit(this.%s);\n", propSpec.name);
        } else {
          out.printf("      this.%s._walk(visitor);\n", propSpec.name);
        }
        out.printf("    }\n");
      }
    }
    out.printf("  }\n");
    out.println();
    out.printf("  public void _replace(_baseNode child, _baseNode replacement) {\n");
      
    for (AstNodes.RuleItem item: rule.body.items) {
      if (item.propMatchSpec.isTokenText) {
        continue;
      }
      if (item.propMatchSpec.isToken()) {
        continue;
      }
      AstNodes.PropSpec propSpec = item.propSpec;
      boolean isArray = propSpec.isArray;
      String itemType = packageName + "." + item.propMatchSpec.name;
      String propName = propSpec.name;
      
      if (propSpec.isArray) {
        out.printf("    for (int _i = 0; _i < this.%s.size(); ++_i) {\n", propName);
        out.printf("      if (this.%s.get(_i) == child) {\n", propName);
        out.printf("        this.remove_%s(_i);\n", propName);
        out.printf("        this.insert_%s(_i, (%s)replacement);\n", propName, itemType);
        out.printf("        return;\n");
        out.printf("      }\n");
        out.printf("    }\n");
      } else {
        out.printf("    if (this.%s == child) {\n", propName);
        out.printf("      this.set_%s((%s)replacement);\n", propName, itemType);
        out.printf("      return;\n");
        out.printf("    }\n");
      }
    }
    out.printf("    throw new RuntimeException(\"Failed to replace node: no such node\");\n");
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateBaseClass() throws Exception {
    File file = path.resolve("_visitor.java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      out.printf("public interface _visitor {\n");
      out.printf("  void visit(_baseNode node);\n");
      out.printf("  void visit(org.antlr.runtime.tree.Tree nonNode);\n");
      out.printf("}\n");
    }
    file = path.resolve("_baseNode.java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      out.printf("public interface _baseNode {\n");
      out.printf("  _baseNode _getParent();\n");
      out.printf("  void _setParent(_baseNode parent);\n");
      out.printf("  int _getLine();\n");
      out.printf("  int _getCol();\n");
      out.printf("  int _getTokenStartIndex();\n");
      out.printf("  int _getTokenStopIndex();\n");
      out.printf("  org.antlr.runtime.tree.Tree unparse();\n");
      out.printf("  void _walk(_visitor visitor);\n");
      out.printf("  void _replace(_baseNode child, _baseNode replacement);\n");
      out.printf("}\n");
    }
  }
  
  public static void generateParserClass() throws Exception {
    File file = path.resolve(spec.parserClassName + ".java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      out.printf("public class %s {\n", spec.parserClassName);
      
      for (AstNodes.RuleSpec rule: spec.rules) {
        generateRuleBodyChecker(out, rule);
        generateRuleBodyParser(out, rule);
        if (rule instanceof AstNodes.RuleWithoutAlts) {
          generateRuleConstrator(out, (AstNodes.RuleWithoutAlts)rule);
        }
      }
      
      out.printf("}\n");
    }
  }
  
  public static void generateRuleBodyChecker(PrintStream out, AstNodes.RuleSpec rule) throws Exception {
    out.printf("  public static boolean canParse%s(org.antlr.runtime.tree.Tree tree) {\n", rule.name);
    String condition;
    if (rule instanceof AstNodes.RuleWithoutAlts) {
      AstNodes.RuleWithoutAlts theRule = (AstNodes.RuleWithoutAlts)rule;
      condition = generateTreeCheckCondition("tree.getType()", theRule.body.rootType);
    } else {
      AstNodes.RuleWithAlts theRule = (AstNodes.RuleWithAlts)rule;
      List<String> parts = new ArrayList<String>();
      for (String altName: theRule.alternatives) {
        parts.add(String.format("canParse%s(tree)", altName));
      }
      condition = stringJoin(" || ", parts);
    }
    out.printf("    return %s;\n", condition);
    out.printf("  }\n");
    out.println();
  }
  
  public static String generateTreeCheckCondition(String tokenVar, String tokenType) {
    String result = String.format("%s == %s.%s", tokenVar, tokenVocabName, tokenType);
    return result;
  }
  
  public static void generateRuleBodyParser(PrintStream out, AstNodes.RuleSpec rule) throws Exception {
    out.printf("  public static %s parse%s(org.antlr.runtime.tree.Tree tree) {\n", rule.name, rule.name);
    out.printf("    if (!canParse%s(tree)) {\n", rule.name);
    out.printf("      throw new RuntimeException(\"Tree type mismatch\");\n");
    out.printf("    }\n");
                
    out.println();
    if (rule instanceof AstNodes.RuleWithAlts) {
      AstNodes.RuleWithAlts theRule = (AstNodes.RuleWithAlts)rule;
      for (String altName: theRule.alternatives) {
        out.printf("    if (canParse%s(tree)) return parse%s(tree);\n", altName, altName);
      }
      out.printf("    throw new RuntimeException(\"Tree type mismatch\");\n");
    } else {
      AstNodes.RuleWithoutAlts theRule = (AstNodes.RuleWithoutAlts)rule;
      out.printf("    %s _result = new %s();\n", rule.name, rule.name);
    
      out.println();
      out.printf("    _result._line = tree.getLine();\n");
      out.printf("    _result._col = tree.getCharPositionInLine();\n");
      out.printf("    _result._tokenStartIndex = tree.getTokenStartIndex();\n");
      out.printf("    _result._tokenStopIndex = tree.getTokenStopIndex();\n");
      out.printf("    int _i = 0;\n");
      out.println();
      for (AstNodes.RuleItem item: theRule.body.items) {
        generateParserRuleItem(out, item);
        out.println();
      }
      out.printf("    if (_i < tree.getChildCount()) { throw new RuntimeException(\"Tree type mismatch\"); }\n");
      out.printf("    return _result;\n");
    }
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateParserRuleItem(PrintStream out, AstNodes.RuleItem item) throws Exception {
    if (item.propMatchSpec.isTokenText) {
      out.printf("    _result.%s = tree.getText();\n", item.propSpec.name);
      return;
    }
    
    String itemMatchCondition;
    if (item.propMatchSpec.isAny) {
      itemMatchCondition = "true";
    } else if (item.propMatchSpec.isToken()) {
      itemMatchCondition = String.format("tree.getChild(_i).getType() == %s.%s", tokenVocabName, item.propMatchSpec.name);
    } else {
      itemMatchCondition = String.format("canParse%s(tree.getChild(_i))", item.propMatchSpec.name);
    }
    itemMatchCondition = "_i < tree.getChildCount() && (" + itemMatchCondition + ")";
    String itemProcess;
    String itemGet;
    if (item.propMatchSpec.isToken()) {
      itemGet = "tree.getChild(_i)";
    } else {
      itemGet = String.format("parse%s(tree.getChild(_i))", item.propMatchSpec.name);
    }
    if (item.propSpec.isArray) {
      itemProcess = String.format("_result.add_%s(%s);", item.propSpec.name, itemGet);
    } else {
      itemProcess = String.format("_result.set_%s(%s);", item.propSpec.name, itemGet);
    }
    if (item.propMatchSpec.isQuestion) {
      out.printf("    if (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++_i;\n");
      out.printf("    }\n");
    } else if (item.propMatchSpec.isAsterisk) {
      out.printf("    while (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++_i;\n");
      out.printf("    }\n");
    } else if (item.propMatchSpec.isPlus) {
      out.printf("    if (!(%s)) { throw new RuntimeException(\"Tree type mismatch\"); }\n", itemMatchCondition);
      out.printf("    while (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++_i;\n");
      out.printf("    }\n");
    } else {
      out.printf("    if (!(%s)) { throw new RuntimeException(\"Tree type mismatch\"); }\n", itemMatchCondition);
      out.printf("    %s\n", itemProcess);
      out.printf("    ++_i;\n");
    }
  }
  
  public static void generateRuleConstrator(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    List<String> arguments = new ArrayList<String>();
    
    for (AstNodes.RuleItem item: rule.body.items) {
      AstNodes.PropSpec propSpec = item.propSpec;
      boolean isToken = item.propMatchSpec.isToken();
      String type;
      String itemType = item.propMatchSpec.isTokenText ? "String" : isToken ? "org.antlr.runtime.tree.Tree" : packageName + "." + item.propMatchSpec.name;
      if (propSpec.isArray) {
        type = String.format("java.util.List<%s>", itemType);
      } else {
        type = itemType;
      }
      arguments.add(String.format("%s %s", type, propSpec.name));
    }
    out.printf("  public static %s make_%s(%s) {\n", rule.name, rule.name, stringJoin(",\n      ", arguments));
    out.printf("    %s _result = new %s();\n", rule.name, rule.name);
    
    for (AstNodes.RuleItem item: rule.body.items) {
      boolean isToken = item.propMatchSpec.isToken();
      String type;
      String itemType = item.propMatchSpec.isTokenText ? "String" : isToken ? "org.antlr.runtime.tree.Tree" : packageName + "." + item.propMatchSpec.name;
      
      if (item.propSpec.isArray) {
        out.printf("    if (%s != null) {\n", item.propSpec.name);
        out.printf("      for (%s _value: %s) { _result.add_%s(_value); }\n", itemType, item.propSpec.name, item.propSpec.name);
        out.printf("    }\n");
      } else {
        out.printf("    _result.set_%s(%s);\n", item.propSpec.name, item.propSpec.name);
      }
    }
    
    out.printf("    return _result;\n");
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateRuleBodyUnparser(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    out.printf("  public org.antlr.runtime.tree.Tree unparse() {\n");
    String tokenTextProperty = null;
    for (AstNodes.RuleItem item: rule.body.items) {
      if (item.propMatchSpec.isTokenText) {
        tokenTextProperty = item.propSpec.name;
      }
    }
    out.printf("    org.antlr.runtime.CommonToken _token = new org.antlr.runtime.CommonToken(%s.%s);\n", tokenVocabName, rule.body.rootType);
    out.printf("    _token.setLine(_line);\n");
    out.printf("    _token.setCharPositionInLine(_col);\n");
    if (tokenTextProperty != null) {
      out.printf("    _token.setText(%s);\n", tokenTextProperty);
    } else {
      out.printf("    _token.setText(\"%s\");\n", rule.body.rootType);
    }
    out.printf("    org.antlr.runtime.tree.CommonTree _result = new org.antlr.runtime.tree.CommonTree(_token);\n");
    out.printf("    _result.setTokenStartIndex(_tokenStartIndex);\n");
    out.printf("    _result.setTokenStopIndex(_tokenStopIndex);\n");
    
    for (AstNodes.RuleItem item: rule.body.items) {
      generateUnparserRuleItem(out, item);
      out.println();
    }
    out.printf("    return _result;\n");
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateUnparserRuleItem(PrintStream out, AstNodes.RuleItem item) throws Exception {
    if (item.propMatchSpec.isTokenText) {
      return;
    }
    
    if (item.propMatchSpec.isPlus) {
      out.printf("    if (%s.size() == 0) { throw new RuntimeException(); }\n", item.propSpec.name);
    } else if (!item.propMatchSpec.isQuestion && !item.propMatchSpec.isAsterisk) {
      out.printf("    if (%s == null) { throw new RuntimeException(); }\n", item.propSpec.name);
    }
    
    String itemRef = item.propSpec.isArray ? String.format("%s.get(i)", item.propSpec.name) : item.propSpec.name;
    String itemResultRef = item.propMatchSpec.isToken() ? itemRef : String.format("%s.unparse()", itemRef);
    String action = String.format("_result.addChild(%s);", itemResultRef);
    if (item.propSpec.isArray) {
      out.printf("    for (int i = 0; i < %s.size(); ++i) {\n", item.propSpec.name);
      out.printf("      %s\n", action);
      out.printf("    }\n");
    } else {
      if (item.propMatchSpec.isQuestion) {
        out.printf("    if (%s != null) {\n", item.propSpec.name);
        out.printf("      %s\n", action);
        out.printf("    }\n");
      } else {
        out.printf("    %s\n", action);
      }
    }
    out.println();
  }
  
  public static void generateRuleWithAlternativesClass(AstNodes.RuleWithAlts rule) throws Exception {
    File file = path.resolve(rule.name + ".java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      List<String> interfacesList = new ArrayList<String>(ruleInterfaces.get(rule.name));
      interfacesList.add("_baseNode");
      String interfaces = stringJoin(", ", interfacesList);
      out.printf("public interface %s %s{\n", rule.name, interfaces.equals("") ? "" : String.format("extends %s ", interfaces));
      out.printf("  // implemented by: %s\n", stringJoin(", ", rule.alternatives));
      out.printf("}\n");
    }
  }
}