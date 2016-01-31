package ru.barsopen.plsqlconverter;

import java.io.PrintStream;

public class CliOptions {
	public boolean debug;
	public boolean help;
	public boolean allPackages;
	public boolean validateReparse;
	public String validateReparseOutputAstPath;
	public Integer limitAllPackages;
	public String inputSqlPath;
	public String inputXmlPath;
	public String inputSerialiedPath;
	public boolean splitOutput;
	public String outputSqlPath;
	public String outputAstPath;
	public String outputXmlPath;
	public String outputSerializedPath;
	public boolean usePgSql;
	
	public boolean convert;
	
	public String tree_type = "sql_script";
	
	public static void printUsage(PrintStream out) {
		out.println("Usage: plsqconverter [options]");
		out.println("");
		out.println("  --help");
		out.println("  --all-packages");
		out.println("  --validate-reparse");
		out.println("  --validate-reparse-ast-path");
		out.println("  --limit-all-packages <N>");
		out.println("  --tree-type (sql_script|statement|expression)");
		out.println("  --input-sql (path|-)");
		out.println("  --input-xml (path|-)");
		out.println("  --input-serialized path");
		out.println("  --convert");
		out.println("  --use-pgsql");
		out.println("  --split-output");
		out.println("    (in this case --output-sql, --output-ast are templates:");
		out.println("      {i} is replaced by item index,");
		out.println("      {t} is replaced by item type,");
		out.println("      {n} is replaced by item name");
		out.println("      e.g., --output-sql '{i}_{t}_{n}.sql'");
		out.println("  --output-sql (path|-)");
		out.println("  --output-ast (path|-)");
		out.println("  --output-xml (path|-)");
		out.println("  --output-serialized path");
		out.println("  --debug");
	}

	public static CliOptions parseCliOptions(String[] args) {
		CliOptions result = new CliOptions();
		int i = 0;
		while (i < args.length) {
			String arg = args[i];
			++i;
			switch (arg) {
			case "--help": result.help = true; break;
			case "--all-packages": result.allPackages = true; break;
			case "--validate-reparse": result.validateReparse = true; break;
			case "--validate-reparse-ast-path": result.validateReparseOutputAstPath = args[i]; ++i; break;
			case "--limit-all-packages": result.limitAllPackages = Integer.valueOf(args[i]); ++i; break;
			case "--tree-type": result.tree_type = args[i]; ++i; break;
			case "--input-sql": result.inputSqlPath = args[i]; ++i; break;
			case "--input-xml": result.inputXmlPath = args[i]; ++i; break;
			case "--input-serialized": result.inputSerialiedPath = args[i]; ++i; break;
			case "--split-output": result.splitOutput = true; break;
			case "--output-sql": result.outputSqlPath = args[i]; ++i; break;
			case "--output-ast": result.outputAstPath = args[i]; ++i; break;
			case "--output-xml": result.outputXmlPath = args[i]; ++i; break;
			case "--output-serialized": result.outputSerializedPath = args[i]; ++i; break;
			case "--convert": result.convert = true; break;
			case "--debug": result.debug = true; break;
			case "--use-pgsql": result.usePgSql = true; break;
			default: System.err.printf("Unrecognized option: %s\n", arg); System.exit(1); break;
			}
		}
		return result;
	}
}