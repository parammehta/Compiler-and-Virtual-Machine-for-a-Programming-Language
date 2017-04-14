package de.letsbuildacompiler.compiler;

import java.util.HashMap;
import java.util.Map;

import de.letsbuildacompiler.parser.DemoBaseVisitor;
import de.letsbuildacompiler.parser.DemoParser.*;


public class MyVisitor extends DemoBaseVisitor<String> {
	
	private Map<String, Integer> variables = new HashMap<>();
	
	@Override
	public String visitPrintln(PrintlnContext ctx) {
		return "getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
				visit(ctx.argument) + "\n" +
				"invokevirtual java/io/PrintStream/println(I)V\n";
	}
	
	@Override
	public String visitPLUSMINUS(PLUSMINUSContext ctx) {
		if (ctx.operator.getText().equals("+")) {
			return visitChildren(ctx) + "\n" +   
					"iadd";
		}
		else if (ctx.operator.getText().equals("-")) {
			return visitChildren(ctx) + "\n" +   
					"isub";
		}
		else {
			return "";
		}
	}
	
	@Override
	public String visitMULTDIV(MULTDIVContext ctx) {
		if (ctx.operator.getText().equals("*")) {
			return visitChildren(ctx) + "\n" +   
					"imul";
		}
		else if (ctx.operator.getText().equals("/")) {
			return visitChildren(ctx) + "\n" +   
					"idiv";
		}
		else {
			return "";
		}
	}
	
	@Override
	public String visitNumber(NumberContext ctx) {
		return "ldc " + ctx.num.getText();
	}
	

	@Override
	public String visitVarAssignment(VarAssignmentContext ctx) {
		variables.put(ctx.varName.getText(), variables.size());
		return "";
	}
	
	@Override
	public String visitAssignment(AssignmentContext ctx) {
		return visit(ctx.expr) + "\n" +
		"istore " + variables.get(ctx.varName.getText());
	}
	
	@Override
	public String visitVariable(VariableContext ctx) {
		return "iload " + variables.get(ctx.varName.getText());
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
