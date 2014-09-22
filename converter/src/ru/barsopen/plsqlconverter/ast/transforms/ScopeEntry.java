package ru.barsopen.plsqlconverter.ast.transforms;

import java.util.ArrayList;
import java.util.List;

import ru.barsopen.plsqlconverter.ast.typed.*;

public class ScopeEntry {
	public String name;
	public _baseNode defNode;
	public List<general_element_item> references = new ArrayList<general_element_item>();
}
