package de.letsbuildacompiler.compiler;

import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.antlr.v4.runtime.*;

import de.letsbuildacompiler.parser.DemoLexer;
import de.letsbuildacompiler.parser.DemoParser;
import de.letsbuildacompiler.parser.DemoParser.ProgramContext;
import jasmin.ClassFile;

public class Main {

	private static Path tempDir;
	
	public static void main(String[] args) throws Exception {
		tempDir = Files.createTempDirectory("outputTest");
		ANTLRInputStream input = new ANTLRFileStream("./Compiler/code.demo");
		Scanner s = new Scanner(System.in);
		System.out.println("Instructions\nPress 1 for Intermediate Code\nPress 2 for Output");
		int inputfromuser = s.nextInt();
		if (inputfromuser == 1) {
			System.out.println(compile(input));
		} else if (inputfromuser == 2) {
			System.out.println(compileAndRun(input));
		} else {
			System.out.println("Invalid choice");
		}
	}
	
	private static String compileAndRun(ANTLRInputStream input) throws Exception {
		String code = Main.compile(input);
		ClassFile classfile = new ClassFile();
		classfile.readJasmin(new StringReader(code), "", false);
		Path outputPath = tempDir.resolve(classfile.getClassName() + ".class");
		classfile.write(Files.newOutputStream(outputPath));
		return runJavaClass(tempDir,classfile.getClassName());	
	}
	
	private static String runJavaClass(Path dir, String className) throws Exception {
		Process process = Runtime.getRuntime().exec(new String[]{"java","-cp",dir.toString(),className});
		try(InputStream in = process.getInputStream()){
			return new Scanner(in).useDelimiter("\\A").next();
		}
	}
	
	public static String compile(ANTLRInputStream input) {
		DemoLexer lexer = new DemoLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		DemoParser parser = new DemoParser(tokens);
		
		ProgramContext tree = parser.program();
		return createJasminFile(new MyVisitor().visit(tree));
	}
	
	private static String createJasminFile(String instructions){
		return ".class public HelloWorld\n" +
				".super java/lang/Object\n" +
				"\n" + 
				instructions;
	}
}
