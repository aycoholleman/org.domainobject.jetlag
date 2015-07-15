package org.domainobject.jetlag.core.funclib.def;

import org.domainobject.jetlag.core.funclib.FunctionLibrary;

/**
 * A {@code FunctionDefinition} describes and defines a function within a
 * function library.
 * 
 * @author Ayco Holleman
 * @created May 7, 2015
 *
 */
public final class FunctionDefinition {

	private boolean callStatic;
	private String methodName;
	private String uiName;


	/**
	 * Whether or not the method implementing the function can be called
	 * statically on the {@link FunctionLibrary} class.
	 * 
	 * @return
	 */
	public boolean isCallStatic()
	{
		return callStatic;
	}


	public void setCallStatic(boolean callStatic)
	{
		this.callStatic = callStatic;
	}


	/**
	 * Get the name of the method implementing the function.
	 * 
	 * @param name
	 */
	public String getMethodName()
	{
		return methodName;
	}


	public void setMethodName(String name)
	{
		this.methodName = name;
	}


	/**
	 * Get the name of the function.
	 * 
	 * @return
	 */
	public String getUiName()
	{
		return uiName;
	}


	public void setUiName(String uiName)
	{
		this.uiName = uiName;
	}

}
