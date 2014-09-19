import java.util.*;

public class AstNodes {
  public static class AstSpec {
    public List<String> packageName = new ArrayList<String>();
    public List<String> tokenVocabName = new ArrayList<String>();
    public List<RuleSpec> rules = new ArrayList<RuleSpec>();
    public String parserClassName;
    public String nodeCode;
    public String baseNodeCode;
    
    public RuleSpec findRuleSpec(String name) {
      for (RuleSpec rs: rules) {
        if (rs.name.equals(name)) {
          return rs;
        }
      }
      return null;
    }
  }
  
  public static abstract class RuleSpec {
    public String name;
  }
  
  public static class RuleWithoutAlts extends RuleSpec {
    public RuleBody body;
  }
  
  public static class RuleWithAlts extends RuleSpec {
    public List<String> alternatives = new ArrayList<String>();
  }
  
  public static class RuleBody {
    public String rootType;
    public List<RuleItem> items = new ArrayList<RuleItem>();
  }
  
  public static class RuleItem {
    public PropSpec propSpec;
    public PropMatchSpec propMatchSpec;
  }
  
  public static class PropSpec {
    public boolean isQuestion;
    public boolean isArray;
    public String name;
  }
  
  public static class PropMatchSpec {
    public String name;
    public boolean isAny;
    public boolean isTokenText;
    public boolean isQuestion;
    public boolean isAsterisk;
    public boolean isPlus;
    
    public boolean isToken() {
      return isAny || (name != null && Character.isUpperCase(name.charAt(0)));
    }
    
    public PropSpec createDefaultPropSpec() {
      String name = this.name;
      if (name == null) {
        name = "content";
      }
      PropSpec result = new PropSpec();
      result.name = (isPlus || isAsterisk) ? name + "s" : name;
      result.isQuestion = isQuestion;
      result.isArray = isAsterisk || isPlus;
      return result;
    }
  }
}