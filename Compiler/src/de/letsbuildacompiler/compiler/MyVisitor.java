package de.letsbuildacompiler.compiler;

import java.util.Map;

import de.letsbuildacompiler.parser.DemoBaseVisitor;
import de.letsbuildacompiler.parser.DemoParser.*;


public class MyVisitor extends DemoBaseVisitor<String> {
	
	private Map<String, Integer> variables = new HashMao<>();
	
	@Override
	public String visitPrintln(PrintlnContext ctx) {
		return "getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
				visit(ctx.argument) + "\n" +
				"invokevirtual java/io/PrintStream/println(I)V\n";
	}
	
	@Override
	public String visitPLUS(PLUSContext ctx) {
		return visitChildren(ctx) + "\n" +   
				"iadd";
	}
	
	@Override
	public String visitMINUS(MINUSContext ctx) {
		return visitChildren(ctx) + "\n" +   
				"isub";
	}
	
	@Override
	public String visitDIV(DIVContext ctx) {
		return visitChildren(ctx) + "\n" +   
				"idiv";
	}
	
	@Override
	public String visitMULT(MULTContext ct x) {
		return visitChildren(ctx) + "\n" +   
				"imul";
	}
	
	@Override
	public String visitNumber(NumberContext ctx) {
		return "ldc " + ctx.num.getText();
	}
	
	@Override
	public String visitVarDeclaration(VarDeclarationContext ctx){
		if(variables.containsKey(ctx.varName.getText())){
			throw new VariableAlreadyDefinedException(ctx.varName);
		}
		variables.put(ctx.varName.getText(), variables.size());
		return "";
	}
	
	
	@Override
	public String visitAssignment(AssignmentContext ctx) {
		return visit(ctx.expr) + "\n" + 
				"istore" + requireVariableIndex(ctx.varName); 
	}
	
	@Override
	public String visitVariable(VariableContext ctx) {
		return "iload" + requireVariableIndex(ctx.varName); 
	}
	
	private int requireVariableIndex(Token varNameToken){
		Integer varindex = variables.get(varNameToken.getText());
		if (varindex == null){
			throw new UndeclaredVariableException(ctx.varName);
		}
		return varIndex;
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
