package de.letsbuildacompiler.compiler;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import de.letsbuildacompiler.parser.DemoBaseVisitor;
import de.letsbuildacompiler.parser.DemoParser.*;


public class MyVisitor extends DemoBaseVisitor<String> {
	
	private Map<String, Integer> variables = new HashMap<>();
	private int branchCount = 0;
	
	@Override
	public String visitProgram(ProgramContext ctx) {
		String statements = "";
		String methods = "";
		for(int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree child = ctx.getChild(i);
			String codepiece = visit(child);
			if (child instanceof StatementPieceContext) {
				statements += codepiece + "\n";
			}
			else {
				methods += codepiece + "\n";
			}
		}
		return (methods + "\n" +
		".method public static main([Ljava/lang/String;)V\n" +
		" .limit stack 100\n" +
		" .limit locals 100\n" +

		" \n" +
		statements + "\n" +
		" return\n"+ 
		" \n" +
	
		".end method");
	}
	
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
		/*
		if (ctx.varName.getText().equals("true")) {
			return "ifne " + variables.get(ctx.varName.getText());
		}
		else {
			
		}
		*/
			return "iload " + variables.get(ctx.varName.getText());
		//}
	}
	
	@Override
	public String visitMethodCall(MethodCallContext ctx) {
		return "invokestatic HelloWorld/" + ctx.methName.getText() + "()I\n";
	}
	
	@Override
	public String visitMethod(MethodContext ctx) {
		
		//Scope
		Map<String, Integer> oldVariables = variables;
		variables = new HashMap<>();
		
		String instructions = visit(ctx.statements);
		String classcode = ".method public static " + ctx.methName.getText() + "()I\n"
				+ ".limit locals 100\n"
				+ ".limit stack 100\n";
		
		if (instructions == null) {
			classcode += "";
		}
		else {
			classcode += instructions + "\n";
		}
		
		classcode += visit(ctx.returnVal)
					+ "\nireturn\n"
					+ ".end method";
		
		//Return scope
		variables = oldVariables;
		
		return classcode;
	}
	
	@Override
	public String visitBranch(BranchContext ctx) {
		String instructions = visit(ctx.condition);
		String isTrue = visit(ctx.True);
		String isFalse = visit(ctx.False);
		int counter = branchCount;
		branchCount++;
		
		return (instructions + "\n"
				+ "ifne ifTrue" + counter + "\n"
				+ isFalse + "\n"
				+ "goto endIf" + counter + "\n"
				+ "ifTrue" + counter + ":\n"
				+ isTrue + "\n"
				+ "endIf" + counter
				+ ":\n");
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
