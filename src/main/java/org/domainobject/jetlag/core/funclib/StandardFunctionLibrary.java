package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.runtime.CallInfo;
import org.domainobject.jetlag.core.util.tuple.StringInt;

/**
 * @author Ayco Holleman
 */
@Library(namespace = "std")
public class StandardFunctionLibrary extends FunctionLibrary {

	public StandardFunctionLibrary()
	{
		super();
	}

	@Function(ref = "substring")
	public String substr(CallInfo callInfo, int from)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from);
	}

	@Function(ref = "substring")
	public String substr(CallInfo callInfo, int from, int to)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from, to);
	}

	@Function(uiName = "substring")
	@Description("Extract a slice from a string.")
	@Param("The string to extract a slice from")
	@Param("The position of the first character of the slice. The from-th character will be included in the slice.")
	@Param("The position of the last character of the slice. The to-th character will be included in the slice.")
	@Return("A slice from the supplied string")
	public static String substr(CallInfo callInfo, String input, int from, int to)
	{
		return input.substring(from, to);
	}

	public static Integer map(CallInfo callInfo, String input, StringInt[] varargs)
	{
		for (StringInt arg : varargs) {
			if (input.equals(arg.string)) {
				return arg.integer;
			}
		}
		return null;
	}

	@Function()
	@Description("Map strings to numbers.")
	@Param("The string to map")
	@VarArgsParam(
			factoryClass = StringInt.class,
			factoryMethod = "test",
			varArgs = {
					@VarArg(uiName = "match_candidate",
							description = "The string to compare the input string with"),
					@VarArg(uiName = "output",
							description = "The number to output if the input string was equal to the match candidate"),
			})
	@Param(uiName = "default_output",
			value = "The number to output if the input did not match any of the match candidates")
	@Return("")
	public static int map(CallInfo callInfo, String input, StringInt[] varargs,
			int dfault)
	{
		for (StringInt arg : varargs) {
			if (input.equals(arg.string)) {
				return arg.integer;
			}
		}
		return dfault;
	}

}
