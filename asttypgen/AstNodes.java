import java.util.*;

public class AstNodes {
  public static class AstSpec {
    public List<String> packageName = new ArrayList<String>();
    public List<String> tokenVocabName = new ArrayList<String>();
    public List<RuleSpec> rules = new ArrayList<RuleSpec>();
  }
  
  public static abstract class RuleSpec {
    public String name;
  }
  
  public static class RuleSpec1 extends RuleSpec {
    public RuleBody body;
  }
  
  public static class RuleSpec2 extends RuleSpec {
    public List<RuleAlternative> alternatives = new ArrayList<RuleAlternative>();
  }
  
  public static class RuleAlternative {
    public String name;
    public RuleBody body;
  }
  
  public static class RuleBody {
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
    public boolean isQuestion;
    public boolean isAsterisk;
    public boolean isPlus;
  }
}