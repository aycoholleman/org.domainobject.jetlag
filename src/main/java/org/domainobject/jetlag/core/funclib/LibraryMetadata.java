package org.domainobject.jetlag.core.funclib;

/**
 * @author ayco
 * @created May 7, 2015
 *
 */
public final class LibraryMetadata {

	private Class<? extends AbstractFunctionLibrary> libraryClass;
	private String namespace;
	private String description;
	private FunctionMetadata[] functions;


	public Class<? extends AbstractFunctionLibrary> getLibraryClass()
	{
		return libraryClass;
	}


	public void setLibraryClass(Class<? extends AbstractFunctionLibrary> libraryClass)
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


	public FunctionMetadata[] getFunctions()
	{
		return functions;
	}


	public void setFunctions(FunctionMetadata[] functions)
	{
		this.functions = functions;
	}

}
