package de.letsbuildacompiler.compiler;

import org.antlr.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import de.letsbuildacompiler.parser.DemoLexer;
import de.letsbuildacompiler.parser.DemoParser;
import de.letsbuildacompiler.parser.DemoParser.AdditionContext;

public class Main {

	public static void main(String[] args) throws Exception {
		ANTLRInputStream input = new ANTLRFileStream("code.demo");
		System.out.println(compile(input));
		
	}
	
	public static String compile(ANTLRInputStream input){
		
		DemoLexer lexer = new DemoLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DemoParser parser = new DemoParser(tokens);
		
		AdditionContext tree = parser.addition();
		return createJasminFile(new MyVisitor().visit(tree));
	}
	
	private static String createJasminFile(String instructions){
		return ".class public HelloWorld\n" +
				".super java/lang/Object\n" +

				".method public static main([Ljava/lang/String;)V\n" +
					".limit stack 100\n" +
					".limit locals 100\n" +
	
				"getstatic java/lang/System/out Ljava/io/PrintStream;\n" +
				instructions + "\n" +
				"invokevirtual java/io/PrintStream/println(I)V\n" +
				"return\n"+ 
				"\n" +

				".end method";
	}
}
