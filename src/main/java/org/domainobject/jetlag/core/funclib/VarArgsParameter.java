package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code VarArgsParameter} parameter describes a parameter denoting
 * variable argument list. Contrary to Java, a varargs parameter in a Jetlag
 * function does not to be the last parameter. Also, the concrete arguments
 * provided in a function call can cycle through multiple types. A typical
 * example would be a function that maps an input value to an output value, and
 * that outputs a default value if the input value could not be mapped. For
 * example:
 * 
 * <pre>
 * 	map(role, 'King', 0, 'President', 1, 'Minister', 2, 3)
 * </pre>
 * 
 * Here, all arguments from "King" to "2" belong to the varargs parameter and
 * "3" is again a "normal" argument. The arguments belonging to the varargs
 * parameter tuples of a string argument and an integer argument resepectively.
 * 
 * @author Ayco Holleman
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface VarArgsParameter {

	VarArg[] varArgs();

}
