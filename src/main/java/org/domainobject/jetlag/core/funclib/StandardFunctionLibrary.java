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


	@Function
	public String substring(CallInfo callInfo, int from)
	{
		String input = this.rules[callInfo.ruleNumber].out;
		return input.substring(from);
	}

}
