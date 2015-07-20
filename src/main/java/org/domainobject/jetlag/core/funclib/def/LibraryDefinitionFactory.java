package org.domainobject.jetlag.core.funclib.def;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.domainobject.jetlag.core.funclib.Function;
import org.domainobject.jetlag.core.funclib.FunctionLibrary;
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
		ArrayList<Method> annotated = new ArrayList<>(methods.length);
		for (Method method : methods) {
			if (method.getAnnotation(Function.class) != null) {
				annotated.add(method);
			}
		}
		/*
		 * Make sure "uiName" methods are processed before "ref" methods,
		 * because the latter reference the former.
		 */
		Collections.sort(annotated, new Comparator<Method>() {

			@Override
			public int compare(Method m1, Method m2)
			{
				Function fnc1 = m1.getAnnotation(Function.class);
				Function fnc2 = m1.getAnnotation(Function.class);
				if (isSet(fnc1.uiName())) {
					if (isSet(fnc2.uiName())) {
						return 0;
					}
					return -1;
				}
				return isSet(fnc2.uiName()) ? 1 : 0;
			}
		});
	}


	private void processMethod(Method method) throws FunctionDefinitionException
	{
		if (!Modifier.isPublic(method.getModifiers())) {
			throw new MethodNotPublicException(method);
		}
		Function fnc = method.getAnnotation(Function.class);
		if(isSet(fnc.uiName())) {
			if(isSet(fnc.ref())) {
				String fmt = "Method %s (%s): uiName attribute and ref attribute must not both be set in @Function annotation";
				String msg = String.format(fmt, method.getName(), libClass.getName());
				throw new FunctionDefinitionException(msg);
			}
		}
		FunctionDefinition funcDef = new FunctionDefinition();
		funcDef.setUiName(fnc.uiName());
		funcDef.setMethodName(method.getName());
		if (Modifier.isStatic(method.getModifiers())) {
			funcDef.setCallStatic(true);
		}
		libDef.addFunctionDefinition(funcDef);
	}


	private static boolean isSet(String s)
	{
		return s != null && s.trim().length() != 0;
	}


	private static boolean empty(String s)
	{
		return s == null || s.trim().length() == 0;
	}
}
