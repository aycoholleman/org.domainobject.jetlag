package org.domainobject.jetlag.core.funclib.def;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.domainobject.jetlag.core.funclib.FunctionLibrary;
import org.domainobject.jetlag.core.funclib.Function;
import org.domainobject.jetlag.core.funclib.Library;

/**
 * @author Ayco Holleman
 * @created May 7, 2015
 *
 */
public final class LibraryDefinitionFactory<T extends FunctionLibrary> {

	private final Class<T> libClass;
	private LibraryDefinition<T> libDef;


	public LibraryDefinitionFactory(Class<T> libClass)
	{
		this.libClass = libClass;
	}


	public LibraryDefinition<T> getLibraryDefinition() throws LibraryDefinitionException
	{
		libDef = new LibraryDefinition<>();
		libDef.setLibraryClass(libClass);
		processClass();
		processMethods();
		return libDef;
	}


	private void processClass()
	{
		processLibraryAnnotation();
	}


	private void processLibraryAnnotation()
	{
		Library lib = libClass.getAnnotation(Library.class);
		libDef.setNamespace(lib.namespace());
	}


	private void processMethods() throws LibraryDefinitionException
	{
		Method[] methods = libClass.getDeclaredMethods();
		for (Method method : methods) {
			Function fnc = method.getAnnotation(Function.class);
			if (fnc != null) {
				processMethod(method, fnc);
			}
		}
	}


	private void processMethod(Method method, Function fnc) throws FunctionDefinitionException
	{
		if (!Modifier.isPublic(method.getModifiers())) {
			throw new MethodNotPublicException(method);
		}
		FunctionDefinition funcDef = new FunctionDefinition();
		funcDef.setUiName(fnc.uiName());
		funcDef.setMethodName(method.getName());
		if (Modifier.isStatic(method.getModifiers())) {
			funcDef.setCallStatic(true);
		}

		libDef.addFunctionDefinition(funcDef);
	}
}
