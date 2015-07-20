package org.domainobject.jetlag.core.funclib.def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.domainobject.jetlag.core.funclib.FunctionLibrary;

/**
 * @author Ayco Holleman
 * @created May 7, 2015
 *
 */
public final class LibraryDefinition<T extends FunctionLibrary> {

	private Class<T> libraryClass;
	private String namespace;
	private String description;
	private List<FunctionDefinition> functionDefinitions = new ArrayList<FunctionDefinition>(50);
	private Map<String, FunctionDefinition> funcDefsPerUiName = new HashMap<>();


	public Class<? extends FunctionLibrary> getLibraryClass()
	{
		return libraryClass;
	}


	public void setLibraryClass(Class<T> libraryClass)
	{
		this.libraryClass = libraryClass;
	}


	public String getNamespace()
	{
		return namespace;
	}


	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public void addFunctionDefinition(FunctionDefinition funcDef)
	{
		funcDefsPerUiName.put(funcDef.getUiName(), funcDef);
	}


	public FunctionDefinition getFunctionDefinition(String uiName)
	{
		return funcDefsPerUiName.get(uiName);
	}

}
