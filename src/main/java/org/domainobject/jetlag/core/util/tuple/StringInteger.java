package org.domainobject.jetlag.core.util.tuple;

public final class StringInteger {

	public final String string;
	public final Integer integer;

	public StringInteger(String string, Integer integer)
	{
		this.string = string;
		this.integer = integer;
	}

	public StringInteger(Integer integer, String string)
	{
		this.string = string;
		this.integer = integer;
	}

}
