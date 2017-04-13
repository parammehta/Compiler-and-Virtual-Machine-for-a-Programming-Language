package de.letsbuildacompiler.compiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import javax.sound.midi.Patch;

import jasmin.ClassFile;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bsh.commands.dir;

public class CompilerTest {
  private Path tempDir;
  
  @BeforeMethod
  public void createTempDir() throws IOException{
	  tempDir = Files.createTempDirectory("compilerTest");
	
}
  @AfterMethod
  public void deleteTempDir(){
	  deleteRecursive(tempDir.toFile());
  }
	private void deleteRecursive(File file) {
	if(file.isDirectory()){
		for(File child : file.listFiles()){
			deleteRecursive(child);
		}
	}
	if(!file.delete()){
		throw new Error("Could not find file <" +file+ ">");
	}
	
}
	@Test(dataProvider = "provide_code_expectedText")
  public void runningCodeoutputsExpectedText(String code, String expectedText) throws Exception {
	  // executing...
	  String actualOutput = compileAndRun(code);
	  
	  //evaluating...
	  Assert.assertEquals(actualOutput, expectedText);
  }
	
	@DataProvider
  public Object[][] provide_code_expectedText(){
	  return new Object[][]{
			  {"1+2","3\n"},
			  {"1+2+42","45\n"}
	  };
  }

private String compileAndRun(String code) throws Exception{
	code = Main.compile(new ANTLRInputStream(code));
	ClassFile classfile = new ClassFile();
	classfile.readJasmin(new StringReader(code), "", false);
	Path outputPath = tempDir.resolve(classfile.getClassName() + ".class");
	classfile.write(Files.newOutputStream(outputPath));
	return runJavaClass(tempDir,classfile.getClassName());	
}

private String runJavaClass(Path dir, String className) throws Exception{
	Process process = Runtime.getRuntime().exec(new String[]{"java","-cp",dir.toString(),className});
	try(InputStream in = process.getInputStream()){
		return new Scanner(in).useDelimiter("\\A").next();
		
	}
}
}
