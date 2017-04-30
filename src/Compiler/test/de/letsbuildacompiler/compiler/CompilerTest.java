package de.letsbuildacompiler.compiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import jasmin.ClassFile;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CompilerTest {
	
	private Path tempDir;
  
  	@BeforeMethod
  	public void createTempDir() throws IOException {
  		tempDir = Files.createTempDirectory("compilerTest");
  	}
  
  	@AfterMethod
  	public void deleteTempDir() {
  		deleteRecursively(tempDir.toFile());
  	}
  
  	private void deleteRecursively(File file) {
  		if(file.isDirectory()){
  			for(File child : file.listFiles()){
  				deleteRecursively(child);
			}
		}
		if(!file.delete()){
			throw new Error("Could not find the file <" +file+ ">");
		}
  	}
  
  	@Test(dataProvider = "provide_code_expectedText")
	public void runningCodeoutputsExpectedText(String description, String code, String expectedText) throws Exception {
  		// executing...
		String actualOutput = compileAndRun(code);
		  
		//evaluating...
		Assert.assertEquals(actualOutput, expectedText);
	}
	
	@DataProvider
	public Object[][] provide_code_expectedText() throws Exception {
		return new Object[][] {
			  {"plus","println(1+2);","3" + System.lineSeparator()},
			  {"chained plus","println(1+2+53);","56" + System.lineSeparator()},
			  {"multiple statements","println(1); println(2);",
				  "1" + System.lineSeparator() +
				  "2" + System.lineSeparator(),
			  },
			  {"minus","println(3-2);","1" + System.lineSeparator()},
			  {"times","println(2*3);","6" + System.lineSeparator()},
			  {"divide","println(8/2);","4" + System.lineSeparator()},
			  {"divide and truncate","println(9-2*3);","3" + System.lineSeparator()},
			  {"precedence times and divide","println(8/2*4);","16" + System.lineSeparator()},
			  {"precedence plus and times","println(2+3*3);","11" + System.lineSeparator()},
			  {"precedence minus and times","println(18-2+7);","23" + System.lineSeparator()},
			  {"print","int x; x = 33; println(x);", "33" + System.lineSeparator()},
			  {"print with add","int y; y = 8843; println(y+2);","8845" + System.lineSeparator()},
			  {"print add","int a; int b; a = 3; b = 5; println(a+b);","8" + System.lineSeparator()},
			  {"return only","int example() {return 1;} println(example());", "1" + System.lineSeparator()},
			  {"return only function","int example() {int i; i = 4; return i;} println(example());", "4" + System.lineSeparator()},
			  {"function within function","int example() {int i; i = 1; return i;} int i; i = 63; println(example()); println(i);", 
				  "1" + System.lineSeparator() 
				+ "63" + System.lineSeparator()
			  },
			  example("branch/if_int_false", "42" + System.lineSeparator()),
			  example("branch/if_int_true", "81" + System.lineSeparator()),
			  
			  {"lower than true", "println(1 < 2);", "1" + System.lineSeparator()},
			  {"lower than false", "println(2 < 2);", "0" + System.lineSeparator()},
			  
			  {"lower than or equal true", "println(2 <= 2);", "1" + System.lineSeparator()},
			  {"lower than or equal false", "println(3 <= 2);", "0" + System.lineSeparator()},
			  
			  {"greater than true", "println(3 > 2);", "1" + System.lineSeparator()},
			  {"greater than false", "println(2 > 2);", "0" + System.lineSeparator()},
			  
			  {"greater than or equal true", "println(2 >= 2);", "1" + System.lineSeparator()},
			  {"greater than or equal false", "println(1 >= 2);", "0" + System.lineSeparator()},
		};
	}
	
	private static String[] example(String name, String expectedResult) throws Exception {
		
		try(InputStream in = CompilerTest.class.getResourceAsStream("/examples/" + name + ".txt")) {
			
			if (in == null) {
				throw new IllegalArgumentException("No such example <" + name + ">");
			}
			
			String code = new Scanner(in, "UTF-8").useDelimiter("\\A").next(); 
			return new String[]{name, code, expectedResult};
		}
	}
	
	private String compileAndRun(String code) throws Exception {
		code = Main.compile(new ANTLRInputStream(code));
		ClassFile classfile = new ClassFile();
		classfile.readJasmin(new StringReader(code), "", false);
		Path outputPath = tempDir.resolve(classfile.getClassName() + ".class");
		classfile.write(Files.newOutputStream(outputPath));
		return runJavaClass(tempDir,classfile.getClassName());	
	}
	
	private String runJavaClass(Path dir, String className) throws Exception {
		Process process = Runtime.getRuntime().exec(new String[]{"java","-cp",dir.toString(),className});
		try(InputStream in = process.getInputStream()){
			return new Scanner(in).useDelimiter("\\A").next();
		}
	}
}