package org.domainobject.jetlag.core.funclib;

import org.domainobject.jetlag.core.runtime.CallInfo;

/**
 * @author ayco
 * @created May 7, 2015
 *
 */
public class StandardFunctionLibrary extends AbstractFunctionLibrary {

	public StandardFunctionLibrary()
	{
		super();
	}


	@Function(uiName="substring")
	@Description("Extract a slice from a string")
	public String substring(CallInfo callInfo, int from)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from);
	}


	public String substring(CallInfo callInfo, int from, int to)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from, to);
	}


	public String substring(CallInfo callInfo, String string, int from, int to)
	{
		return string.substring(from, to);
	}

}
