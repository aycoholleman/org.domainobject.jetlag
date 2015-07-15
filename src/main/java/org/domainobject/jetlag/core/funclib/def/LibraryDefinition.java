package org.domainobject.jetlag.core.funclib.def;

import java.util.ArrayList;
import java.util.List;

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


	public List<FunctionDefinition> getFunctionDefinitions()
	{
		return functionDefinitions;
	}


	public void setFunctionDefinitions(List<FunctionDefinition> functionDefinitions)
	{
		this.functionDefinitions = functionDefinitions;
	}


	public void addFunctionDefinition(FunctionDefinition funcDef)
	{
		functionDefinitions.add(funcDef);
	}

}
