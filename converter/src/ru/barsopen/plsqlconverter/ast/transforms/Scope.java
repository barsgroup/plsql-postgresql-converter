package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.barsopen.plsqlconverter.ast.typed._baseNode;

public class Scope {
	public String name;
	public Scope parent;
	public Map<String, ScopeEntry> entries = new HashMap<String, ScopeEntry>();
	public List<Scope> children = new ArrayList<Scope>();
	
	static int counter = 0;
	
	public Scope createChild() {
		return createChild("scope_" + String.valueOf(++counter));
	}
	
	public Scope createChild(String name) {
		Scope child = new Scope();
		child.name = name;
		child.parent = this;
		children.add(child);
		return child;
	}
	
	public ScopeEntry addEntry(String name, _baseNode defNode) {
		ScopeEntry entry = new ScopeEntry();
		entry.scope = this;
		entry.name = name;
		entry.defNode = defNode;
		entries.put(name, entry);
		return entry;
	}

	@Override
	public String toString() {
		return String.format("%s[%d entries, %d children]", name, entries.size(), children.size());
	}

	public ScopeEntry lookup(String id) {
		ScopeEntry entry = entries.get(id);
		if (entry == null && parent != null) {
			entry = parent.lookup(id);
		}
		return entry;
	}
}
