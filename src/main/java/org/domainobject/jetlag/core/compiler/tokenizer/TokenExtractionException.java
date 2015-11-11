package org.domainobject.jetlag.core.compiler.tokenizer;

public class TokenExtractionException extends TokenizerException {

	private static final long serialVersionUID = -3440244596549389442L;
	
	protected final Token token;



	public TokenExtractionException(Token token)
	{
		super();
		this.token = token;
	}


	public TokenExtractionException(Token token, String message)
	{
		super(message);
		this.token = token;
	}


	public TokenExtractionException(Token token, Throwable cause)
	{
		super(cause);
		this.token = token;
	}


	public TokenExtractionException(Token token, String message, Throwable cause)
	{
		super(message, cause);
		this.token = token;
	}


	public Token getToken()
	{
		return token;
	}

}
