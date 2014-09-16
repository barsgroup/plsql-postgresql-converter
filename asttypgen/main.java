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
    AstNodes.AstSpec spec = p.astSpec();
    String json = new GsonBuilder().setPrettyPrinting().create().toJson(spec);
    System.out.println(json);
  }
}