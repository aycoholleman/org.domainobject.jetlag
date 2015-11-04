package org.domainobject.jetlag.core.funclib.def;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.domainobject.jetlag.core.funclib.Declare;
import org.domainobject.jetlag.core.funclib.Description;
import org.domainobject.jetlag.core.funclib.FunctionLibrary;
import org.domainobject.jetlag.core.funclib.Library;
import org.domainobject.jetlag.core.funclib.Param;
import org.domainobject.jetlag.core.funclib.VarArgsParam;

/**
 * @author Ayco Holleman
 */
public final class LibDefFactory<T extends FunctionLibrary> {

	private final Class<T> libClass;
	private LibDef<T> libDef;

	public LibDefFactory(Class<T> libClass)
	{
		this.libClass = libClass;
	}

	public LibDef<T> getLibraryDefinition() throws LibraryDefinitionException
	{
		libDef = new LibDef<>();
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

	private void processDeclarations() throws LibraryDefinitionException
	{
		Field[] fields = libClass.getDeclaredFields();
		for (Field f : fields) {
			if (!Modifier.isStatic(f.getModifiers()))
				continue;
			Declare fnc = f.getAnnotation(Declare.class);
			if (fnc == null)
				continue;
			FuncDef fncDef = new FuncDef();
			String uiName = fncDef.getUiName();
			if (uiName.isEmpty())
				fncDef.setUiName(f.getName());
			else
				fncDef.setUiName(uiName);
			fncDef.setType(f.getType());
			Description descr = f.getAnnotation(Description.class);
			if (descr == null)
				throw new LibraryDefinitionException("Missing description for function");
			fncDef.setDescription(descr.value());
		}
	}

	private void processParams(Field f)
	{
		ArrayList<ParamDef> paramDefs = new ArrayList<ParamDef>(5);
		for (Annotation a : f.getAnnotations()) {
			if (a.annotationType() == Param.class) {
				paramDefs.add(processParam(f, (Param) a));
			}
			else if (a.annotationType() == VarArgsParam.class) {

			}
		}
	}

	private ParamDef processParam(Field f, Param a)
	{
		ParamDef pd = new ParamDef();

		return null;
	}

	private void processMethods() throws LibraryDefinitionException
	{
		Method[] methods = libClass.getDeclaredMethods();
		ArrayList<Method> annotated = new ArrayList<>(methods.length);
		for (Method method : methods) {
			if (method.getAnnotation(Declare.class) != null) {
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
				Declare fnc1 = m1.getAnnotation(Declare.class);
				Declare fnc2 = m1.getAnnotation(Declare.class);
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
		// if (!Modifier.isPublic(method.getModifiers())) {
		// throw new MethodNotPublicException(method);
		// }
		// Declare fnc = method.getAnnotation(Declare.class);
		// if (isSet(fnc.uiName())) {
		// if (isSet(fnc.ref())) {
		// String fmt =
		// "Method %s (%s): uiName and ref must not both be set in @Function annotation";
		// String msg = String.format(fmt, method.getName(),
		// libClass.getName());
		// throw new FunctionDefinitionException(msg);
		// }
		// }
		// else {
		// if (empty(fnc.ref())) {
		// String fmt =
		// "Method %s (%s): either uiName or ref must be set in @Function annotation";
		// String msg = String.format(fmt, method.getName(),
		// libClass.getName());
		// throw new FunctionDefinitionException(msg);
		// }
		// FunctionDefinition base = libDef.getFunctionDefinition(fnc.ref());
		// if (base == null) {
		// String fmt =
		// "Method %s (%s): ref attribute in @Function annotation specifies a non-existing function: \"%s\".";
		// String msg = String.format(fmt, method.getName(), libClass.getName(),
		// fnc.ref());
		// throw new FunctionDefinitionException(msg);
		// }
		// // base.overload(funcDef);
		// }
		// FunctionDefinition funcDef = new FunctionDefinition();
		// funcDef.setUiName(fnc.uiName());
		// funcDef.setMethodName(method.getName());
		// if (Modifier.isStatic(method.getModifiers())) {
		// funcDef.setCallStatic(true);
		// }
		// libDef.addFunctionDefinition(funcDef);
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
