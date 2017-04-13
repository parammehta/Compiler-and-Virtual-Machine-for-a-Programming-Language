package de.letsbuildacompiler.compiler;

import de.letsbuildacompiler.parser.DemoBaseVisitor;
import de.letsbuildacompiler.parser.DemoParser.AdditionContext;
import de.letsbuildacompiler.parser.DemoParser.NumberContext;
import de.letsbuildacompiler.parser.DemoParser.PLUSContext;


public class MyVisitor extends DemoBaseVisitor<String> {
	@Override
	public String visitPLUS(PLUSContext ctx) {
		return visitChildren(ctx) + "\n" +  
			"ldc " + ctx.right.getText() + "\n" + 
			"iadd";
	}
	
	@Override
	public String visitNumber(NumberContext ctx) {
		return "ldc " + ctx.num.getText();
	}
	
	@Override
	protected String aggregateResult(String aggregate, String nextResult) {
		if(aggregate == null){
			return nextResult;
		}
		if(nextResult == null){
			return aggregate;
		}
		return aggregate + "\n" + nextResult;
	}

}
