package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.List;

import ru.barsopen.plsqlconverter.ast.typed.*;

public class ScopeEntry {
	public Scope scope;
	public String name;
	public _baseNode defNode;
	public List<general_element_item> references = new ArrayList<general_element_item>();
	public void rename(String name) {
		String oldName = this.name;
		this.name = name;
		this.scope.entries.remove(oldName);
		this.scope.entries.put(name, this);
	}
}
