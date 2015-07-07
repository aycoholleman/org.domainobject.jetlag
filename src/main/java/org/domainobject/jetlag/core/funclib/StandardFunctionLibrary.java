package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.runtime.CallInfo;

/**
 * @author Ayco Holleman
 * @created May 7, 2015
 *
 */
@Library(namespace = "std")
public class StandardFunctionLibrary extends AbstractFunctionLibrary {

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
	@Parameter("The string to extract a slice from")
	@Parameter("The position of the first character of the slice. The from-th character will be included in the slice.")
	@Parameter("The position of the last character of the slice. The to-th character will be included in the slice.")
	@Return("A slice from the supplied string")
	public static String substr(@SuppressWarnings("unused") CallInfo callInfo, String string, int from, int to)
	{
		return string.substring(from, to);
	}

}
