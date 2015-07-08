package org.domainobject.jetlag.core.funclib.def;

import org.domainobject.jetlag.core.funclib.AbstractFunctionLibrary;

/**
 * @author Ayco Holleman
 * @created May 7, 2015
 *
 */
public final class LibraryDefinitionFactory<T extends AbstractFunctionLibrary> {

	private final Class<T> libClass;


	public LibraryDefinitionFactory(Class<T> libClass)
	{
		this.libClass = libClass;
	}


	public LibraryDefinition<T> generate()
	{
		LibraryDefinition<T> libDef = new LibraryDefinition<>();
		libDef.setLibraryClass(libClass);
		return libDef;
	}
	
	private String getNamespace() {
		//libClass.getAnnotationsByType(annotationClass)
		return null;
	}

}
