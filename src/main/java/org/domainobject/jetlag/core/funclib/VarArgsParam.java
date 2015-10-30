package org.domainobject.jetlag.core.funclib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code VarArgsParameter} parameter describes a parameter denoting
 * variable argument list. Contrary to Java, a varargs parameter in a Jetlag
 * function does not have to be the last parameter. Also, the arguments
 * expressing the varargs parameter can cycle in tuples through multiple types.
 * A typical example would be a function that maps an input value to an output
 * value, outputting a default value if the input value could not be mapped. For
 * example:
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
 * multiple types takes place. A Jetlag varargs parameter must be implemented as
 * a regular Java array. The class of the array elements must capable of being
 * instantiated with the user-provided tuples through its constructor, e.g. a
 * three-arg contructor for triplet. Alternatively, you can also specify or
 * through an applicable factory method.
 * <p>
 * <h3>Example</h3>
 * 
 * <pre>
 * 	&#64;Function()
 * 	&#64;Description("Map strings to integers")
 *	&#64;Param("The string to map")
 *	&#64;VarArgsParam(
 *		// Class capable of creating a StringIntTuple. Can be omitted if
 *		// that class is StringIntTuple itself. Must be omitted if the
 *		// StringIntTuple objects must be created with the StringIntTuple
 *		// contructor.
 *		factoryClass = StringIntTuple.class
 *		// Static method capable of creating a StringIntTuple.
 *		factoryMethod = "valueOf"
 *		varArgs = {
 *			&#64;VarArg(uiName = "match_against",
 *				description = "The string to compare the input string with"),
 *			&#64;VarArg(uiName = "output",
 * 				description = "The number to output if the input string was equal to the match candidate")
 * 		}
 *	)
 *	&#64;Param("The number to output if the input did not match any of the match candidates")
 * 	&#64;Return("The integer that the input string mapped to or the specified default value")
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
@Target({ ElementType.METHOD, ElementType.FIELD })
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
