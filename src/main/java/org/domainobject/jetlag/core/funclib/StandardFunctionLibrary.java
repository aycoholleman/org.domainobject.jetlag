package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.runtime.CallInfo;
import org.domainobject.jetlag.core.util.tuple.DoubleInt;
import org.domainobject.jetlag.core.util.tuple.StringInt;

import static org.domainobject.jetlag.core.funclib.Default.*;

/**
 * @author Ayco Holleman
 */
@Library(namespace = "std")
public final class StandardFunctionLibrary extends FunctionLibrary {

	public StandardFunctionLibrary()
	{
		super();
	}

	@Declare()
	@Description("Extracts a slice from a string.")
	@Param(uiName = "input",
			descr = "The string to extract a slice from",
			dfault = THIS)
	@Param(uiName = "from",
			descr = "The position of the first character of the slice")
	@Param(uiName = "to",
			descr = "The position of the last character of the slice",
			dfault = AUTO,
			autoValue = "The position of the last character of the input "
					+ "string (i.e. until the end of the input string)")
	@Return("A slice from the supplied string")
	private static String substr;

	@Declare()
	@Description("Maps a value to an integer.")
	@Param(uiName = "input",
			descr = "The value to map",
			dfault = THIS)
	@VarArgsParam(
			varArgs =
			{
					@VarArg(uiName = "match_candidate",
							description = "A value to compare the input value with"),
					@VarArg(uiName = "output",
							description = "The integer to output if the input value "
									+ "was equal to the match candidate")
			})
	@Param(uiName = "default",
			descr = "The integer to output if the input did not match any of the "
					+ "match candidates",
			dfault = AUTO,
			autoValue = "null")
	private static int map_to_int;

	public String substr(CallInfo callInfo, int from)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from);
	}

	public String substr(CallInfo callInfo, int from, int to)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from, to);
	}

	public static String substr(CallInfo callInfo, String input, int from, int to)
	{
		return input.substring(from, to);
	}

	public static Integer map(CallInfo callInfo, String input, StringInt[] varargs)
	{
		for (StringInt arg : varargs) {
			if (input.equals(arg.getString())) {
				return arg.getInt();
			}
		}
		return null;
	}

	@VarArgsDefinition(factoryClass = StringInt.class,
			factoryMethod = "valueOf",
			userSignature = { String.class, int.class })
	public static int mapToInt(CallInfo callInfo, String input, StringInt[] varargs,
			int dfault)
	{
		for (StringInt arg : varargs) {
			if (input.equals(arg.getString())) {
				return arg.getInt();
			}
		}
		return dfault;
	}

	public Integer mapToInt(CallInfo callInfo, int[] varargs)
	{
		int input = Integer.parseInt(rules[callInfo.ruleNumber].out);
		for (int i = 0; i < varargs.length - 1; i += 2) {
			if (input == varargs[i])
				return Integer.valueOf(varargs[i + i]);
		}
		return null;
	}

	public int mapToInt(CallInfo callInfo, int[] varargs, int dfault)
	{
		int input = Integer.parseInt(rules[callInfo.ruleNumber].out);
		for (int i = 0; i < varargs.length - 1; i += 2) {
			if (input == varargs[i])
				return varargs[i + i];
		}
		return dfault;
	}

	public static Integer mapToInt(CallInfo callInfo, int input, int[] varargs)
	{
		for (int i = 0; i < varargs.length - 1; i += 2) {
			if (input == varargs[i])
				return Integer.valueOf(varargs[i + i]);
		}
		return null;
	}

	public static int mapToInt(CallInfo callInfo, int input, int[] varargs, int dfault)
	{
		for (int i = 0; i < varargs.length - 1; i += 2) {
			if (input == varargs[i])
				return varargs[i + i];
		}
		return dfault;
	}

	public Integer mapToInt(CallInfo callInfo, DoubleInt[] varargs)
	{
		double input = Double.parseDouble(rules[callInfo.ruleNumber].out);
		for (DoubleInt arg : varargs) {
			if (input == arg.getDouble()) {
				return arg.getInt();
			}
		}
		return null;
	}

	public int mapToInt(CallInfo callInfo, DoubleInt[] varargs, int dfault)
	{
		double input = Double.parseDouble(rules[callInfo.ruleNumber].out);
		for (DoubleInt arg : varargs) {
			if (input == arg.getDouble()) {
				return arg.getInt();
			}
		}
		return dfault;
	}

	public static Integer mapToInt(CallInfo callInfo, double input, DoubleInt[] varargs)
	{
		for (DoubleInt arg : varargs) {
			if (input == arg.getDouble()) {
				return Integer.valueOf(arg.getInt());
			}
		}
		return null;
	}

	public static int mapToInt(CallInfo callInfo, double input, DoubleInt[] varargs, int dfault)
	{
		for (DoubleInt arg : varargs) {
			if (input == arg.getDouble()) {
				return arg.getInt();
			}
		}
		return dfault;
	}

	public static double mapIntToDouble(CallInfo callInfo, int input, DoubleInt[] varargs,
			double dfault)
	{
		return 0;
	}

}
