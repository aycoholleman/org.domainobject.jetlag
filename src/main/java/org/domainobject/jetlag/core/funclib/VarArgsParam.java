package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code VarArgsParameter} parameter describes a parameter denoting
 * variable argument list. Contrary to Java, a varargs parameter in a Jetlag
 * function does not have to be the last parameter. Also, the arguments
 * expressing the varargs parameter can cycle in tuples, triples, etc. through
 * multiple types. A typical example would be a function that maps an input
 * value to an output value, outputting a default value if the input value could
 * not be mapped. For example:
 * 
 * <pre>
 * 	map(role, 'King', 0, 'President', 1, 'Minister', 2, 3)
 * </pre>
 * 
 * Here, all arguments from "King" to "2" express the varargs parameter while
 * "3" is again a "normal" argument. The arguments expressing the varargs
 * parameter are repeating tuples of a string argument and an integer argument,
 * resepectively. The Java parameter corresponding to a Jetlag varargs parameter
 * must <i>not</i> be a varargs parameter, not even when no cycling through
 * multiple types takes place. Java varargs parameters are not supported when
 * implementing Jetlag functions. A Jetlag varargs parameter must be implemented
 * as a regular Java array. The array elements must be objects capable of being
 * instantiated with the provided tuples, triples, etc. Instantiation must be
 * either through an applicable constructor (a two-arg constructor for tuples, a
 * three-arg contructor for triples) or through an applicable factory method.
 * <p>
 * <h3>Example</h3>
 * 
 * <pre>
 * 	&amp;Function()
 * 	&amp;Description("Map strings to integers")
 *	&amp;Param("The string to map")
 *	&amp;VarArgsParam(
 *		factoryClass = StringIntTuple.class // Class capable of creating a
 *											// StringIntTuple. Can be omitted if
 *											// that class is StringIntTuple
 *											// itself
 *		factoryMethod = "valueOf"
 *		varArgs = {
 *			&amp;VarArg(uiName = "match_against",
 *				description = "The string to compare the input string with"),
 *			&amp;VarArg(uiName = "output",
 * 				description = "The number to output if the input string was equal to the match candidate")
 * 		}
 *	)
 *	&amp;Param("The number to output if the input did not match any of the match candidates")
 * 	&amp;Return("The integer that the input string mapped to or the specified default value")
 * 	public int map(CallInfo info, String role, StringIntTuple[] mappings, int dfault) {
 * 		for(StringIntTuple mapping : mappings) {
 * 			if(role.equals(mapping.getString()) {
 * 				return mapping.getInt();
 * 			}
 * 		}
 * 		return dfault;
 * 	}
 * </pre>
 * 
 * @author Ayco Holleman
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface VarArgsParam {

	/**
	 * The class upon which to invoke the static factory method.
	 * 
	 * @return
	 */
	Class<?> factoryClass() default void.class;

	/**
	 * The name of the factory method.
	 * 
	 * @return
	 */
	String factoryMethod() default "";

	VarArg[] varArgs();

}
