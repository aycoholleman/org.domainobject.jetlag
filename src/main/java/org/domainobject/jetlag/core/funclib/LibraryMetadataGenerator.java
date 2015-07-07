package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.funclib.def.LibraryDefinition;

/**
 * @author ayco
 * @created May 7, 2015
 *
 */
public final class LibraryMetadataGenerator<T extends AbstractFunctionLibrary> {

	private final Class<T> libClass;


	public LibraryMetadataGenerator(Class<T> libClass)
	{
		this.libClass = libClass;
	}


	public LibraryDefinition<T> generate()
	{
		LibraryDefinition<T> metadata = new LibraryDefinition<>();
		metadata.setLibraryClass(libClass);
		return metadata;
	}
	
	private String getNamespace() {
		//libClass.getAnnotationsByType(annotationClass)
		return null;
	}

}
