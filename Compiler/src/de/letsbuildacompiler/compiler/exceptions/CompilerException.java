package de.letsbuildacompiler.compiler.exceptions;

import org.antlr.v4.runtime.Token;

public class CompilerException extends RuntimeException{
	protected int line;
	protected int column;
	
	
	public CompilerException(Token token){
		line = token.getLine();
		column = token.getCharPositionInLine();
	}
	

}
