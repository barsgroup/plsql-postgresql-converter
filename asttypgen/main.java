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
    Path path = Paths.get(args[1], spec.packageName.toArray(new String[0]));
    Files.createDirectories(path);
    packageName = stringJoin(".", spec.packageName);
    tokenVocabName = stringJoin(".", spec.tokenVocabName);
    generateAstClasses(path);
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
  
  static AstNodes.AstSpec spec;
  static String packageName;
  static String tokenVocabName;
  
  public static void generateAstClasses(Path path) throws Exception {
    for (AstNodes.RuleSpec rule: spec.rules) {
      if (rule instanceof AstNodes.RuleWithoutAlts) {
        generateRuleWithoutAlternativesClass(path, (AstNodes.RuleWithoutAlts)rule, null);
      } else {
        AstNodes.RuleWithAlts theRule = (AstNodes.RuleWithAlts)rule;
        for (AstNodes.RuleWithoutAlts childRule: theRule.alternatives) {
          generateRuleWithoutAlternativesClass(path, childRule, theRule.name);
        }
        generateRuleWithAlternativesClass(path, theRule);
      }
    }
  }
  
  public static void generateRuleWithoutAlternativesClass(Path path, AstNodes.RuleWithoutAlts rule, String baseClass) throws Exception {
    File file = path.resolve(rule.name + ".java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      out.printf("public class %s %s{\n", rule.name, baseClass == null ? "" : String.format("extends %s ", baseClass));
      generateRuleBodyProperties(out, (AstNodes.RuleWithoutAlts)rule);
      generateRuleBodyChecker(out, rule);
      generateRuleBodyParser(out, rule);
      generateRuleBodyUnparser(out, rule);
      out.printf("}\n");
    }
  }
  
  public static void generateRuleBodyProperties(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    if (rule.body.isDelegate()) {
      out.printf("  public %s.%s %s = null;\n", packageName, rule.body.rootType, rule.body.rootType);
      return;
    }
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
      
      if (propSpec.isQuestion) {
        out.printf("  public boolean is_%s() { return this.%s != null; }\n", propSpec.name, propSpec.name);
      }
    }
    out.println();
  }
  
  public static void generateRuleBodyChecker(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    out.printf("  public static boolean canParse(org.antlr.runtime.tree.Tree tree) {\n");
    if (rule.body.isDelegate()) {
      out.printf("    return %s.%s.canParse(tree);\n", packageName, rule.body.rootType);
    } else {
      out.printf("    return %s;\n", generateTreeCheckCondition("tree.getType()", rule.body.rootType));
    }
    out.printf("  }\n");
    out.println();
  }
  
  public static String generateTreeCheckCondition(String tokenVar, String tokenType) {
    String result = String.format("%s == %s.%s", tokenVar, tokenVocabName, tokenType);
    return result;
  }
  
  public static String generateTreeCheckCondition(String tokenVar, List<String> tokenTypes) {
    List<String> conditions = new ArrayList<String>();
    for (String tokenType: tokenTypes) {
      conditions.add(generateTreeCheckCondition(tokenVar, tokenType));
    }
    String result = stringJoin(" || ", conditions);
    return result;
  }
  
  public static void generateRuleBodyParser(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    out.printf("  public static %s parse(org.antlr.runtime.tree.Tree tree) {\n", rule.name);
    out.printf("    if (!canParse(tree)) {\n");
    out.printf("      throw new RuntimeException(\"Tree type mismatch\");\n");
    out.printf("    }\n");
                
    out.println();
    out.printf("    %s result = new %s();\n", rule.name, rule.name);
    if (rule.body.isDelegate()) {
      out.printf("    result.%s = %s.%s.parse(tree);\n", rule.body.rootType, packageName, rule.body.rootType);
    } else {
      out.printf("    int i = 0;\n");
      out.println();
      for (AstNodes.RuleItem item: rule.body.items) {
        generateParserRuleItem(out, item);
        out.println();
      }
    out.printf(  "    if (i < tree.getChildCount()) { throw new RuntimeException(\"Tree type mismatch\"); }\n");
    }
    out.printf("    return result;\n");
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateParserRuleItem(PrintStream out, AstNodes.RuleItem item) throws Exception {
    if (item.propMatchSpec.isTokenText) {
      out.printf("    result.%s = tree.getText();\n", item.propSpec.name);
      return;
    }
    
    String itemMatchCondition;
    if (item.propMatchSpec.isToken()) {
      itemMatchCondition = String.format("tree.getChild(i).getType() == %s.%s", tokenVocabName, item.propMatchSpec.name);
    } else {
      itemMatchCondition = String.format("%s.%s.canParse(tree.getChild(i))", packageName, item.propMatchSpec.name);
    }
    itemMatchCondition = "i < tree.getChildCount() && (" + itemMatchCondition + ")";
    String itemProcess;
    String itemGet;
    if (item.propMatchSpec.isToken()) {
      itemGet = "tree.getChild(i)";
    } else {
      itemGet = String.format("%s.%s.parse(tree.getChild(i))", packageName, item.propMatchSpec.name);
    }
    if (item.propSpec.isArray) {
      itemProcess = String.format("result.%s.add(%s);", item.propSpec.name, itemGet);
    } else {
      itemProcess = String.format("result.%s = %s;", item.propSpec.name, itemGet);
    }
    if (item.propMatchSpec.isQuestion) {
      out.printf("    if (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++i;\n");
      out.printf("    }\n");
    } else if (item.propMatchSpec.isAsterisk) {
      out.printf("    while (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++i;\n");
      out.printf("    }\n");
    } else if (item.propMatchSpec.isPlus) {
      out.printf("    if (!(%s)) { throw new RuntimeException(\"Tree type mismatch\"); }\n", itemMatchCondition);
      out.printf("    while (%s) {\n", itemMatchCondition);
      out.printf("      %s\n", itemProcess);
      out.printf("      ++i;\n");
      out.printf("    }\n");
    } else {
      out.printf("    if (!(%s)) { throw new RuntimeException(\"Tree type mismatch\"); }\n", itemMatchCondition);
      out.printf("    %s\n", itemProcess);
      out.printf("    ++i;\n");
    }
  }
  
  public static void generateRuleBodyUnparser(PrintStream out, AstNodes.RuleWithoutAlts rule) throws Exception {
    out.printf("  public org.antlr.runtime.tree.Tree unparse() {\n");
    if (rule.body.isDelegate()) {
      out.printf("    return %s.unparse();\n", rule.body.rootType);
    } else {
      String tokenTextProperty = null;
      for (AstNodes.RuleItem item: rule.body.items) {
        if (item.propMatchSpec.isTokenText) {
          tokenTextProperty = item.propSpec.name;
        }
      }
      if (tokenTextProperty == null) {
        out.printf("    org.antlr.runtime.tree.Tree result = new org.antlr.runtime.tree.CommonTree(new org.antlr.runtime.CommonToken(%s.%s));\n", tokenVocabName, rule.body.rootType);
      } else {
        out.printf("    org.antlr.runtime.tree.Tree result = new org.antlr.runtime.tree.CommonTree(new org.antlr.runtime.CommonToken(%s.%s, %s));\n", tokenVocabName, rule.body.rootType, tokenTextProperty);
      }
      
      for (AstNodes.RuleItem item: rule.body.items) {
        generateUnparserRuleItem(out, item);
        out.println();
      }
      out.printf("    return result;\n");
    }
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
    String action = String.format("result.addChild(%s);", itemResultRef);
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
  
  public static void generateRuleWithAlternativesClass(Path path, AstNodes.RuleWithAlts rule) throws Exception {
    File file = path.resolve(rule.name + ".java").toFile();
    try (PrintStream out = new PrintStream(file, "UTF-8")) {
      out.printf("package %s;\n", packageName);
      out.printf("public abstract class %s {\n", rule.name);
      out.printf("  public abstract org.antlr.runtime.tree.Tree unparse();\n");
      generateRuleAltsChecker(out, rule);
      generateRuleAltsParser(out, rule);
      out.printf("}\n");
    }
  }
  
  public static void generateRuleAltsChecker(PrintStream out, AstNodes.RuleWithAlts rule) throws Exception {
    List<String> conditions = new ArrayList<String>();
    for (AstNodes.RuleWithoutAlts subrule: rule.alternatives) {
      conditions.add(String.format("%s.canParse(tree)", subrule.name));
    }
    String condition = stringJoin(" || ", conditions);
  
    out.printf("  public static boolean canParse(org.antlr.runtime.tree.Tree tree) {\n");
    out.printf("    return %s;\n", condition);
    out.printf("  }\n");
    out.println();
  }
  
  public static void generateRuleAltsParser(PrintStream out, AstNodes.RuleWithAlts rule) throws Exception {
    out.printf("  public static %s parse(org.antlr.runtime.tree.Tree tree) {\n", rule.name);
    for (AstNodes.RuleWithoutAlts alt: rule.alternatives) {
      out.printf("    if (%s.canParse(tree)) {\n", alt.name);
      out.printf("      return %s.parse(tree);\n", alt.name);
      out.printf("    }\n");
    }
    out.println();
    out.printf("    throw new RuntimeException(\"Tree type mismatch\");\n");
    out.printf("  }\n");
  }
}