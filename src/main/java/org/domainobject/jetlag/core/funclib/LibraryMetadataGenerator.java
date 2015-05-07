package org.domainobject.jetlag.core.funclib;

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


	public LibraryMetadata<T> generate()
	{
		LibraryMetadata<T> metadata = new LibraryMetadata<>();
		metadata.setLibraryClass(libClass);
		return metadata;
	}
	
	private String getNamespace() {
		//libClass.getAnnotationsByType(annotationClass)
		return null;
	}

}
