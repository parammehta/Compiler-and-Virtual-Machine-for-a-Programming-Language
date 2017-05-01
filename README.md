**[SystemBuild]**
The compiler and the runtime are built on Linux.
antlr.jar used for Lexical Analysis and jasmin.jar used for creating .class files.
Runtime Environment is Java Virtual Machine and runs directly from Eclipse by clicking on run under Main.java.

**[Installation]**
When importing the project into Eclipse, you will require the plug-in TestNG. The plug-in is strictly used for our test cases, however, you may still run our Main.java without installing the plug-in.
Directions on how to acquire the plugin is dicussed below, taken from TestNG.org:

"Java 1.7+ is required for running the TestNG for Eclipse plugin.
Eclipse 4.2 and above is required. Eclipse 3.x is NOT supported any more, please update your Eclipse to 4.2 or above.
You can use either the Eclipse Marketplace (http://marketplace.eclipse.org/content/testng-eclipse) or the update site:

Install via Eclipse Marketplace:
Go to the TestNG page on the Eclipse Market Place (https://marketplace.eclipse.org/content/testng-eclipse) and drag the icon called "Install" onto your workspace.

Install from update site:
1. Select Help / Install New Software...
2. Enter the update site URL in "Work with:" field: http://beust.com/eclipse.
3. Make sure the check box next to URL is checked and click Next.
4. Eclipse will then guide you through the process.

Note that the URL's are update sites as well, not direct download links."

**[Setting up the project on Eclipse on your PC]**

1) Clone the repository
2) Open the project in Eclipse
3) Open Help -> Eclipse Marketplace
4) Go to http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1549 and drag the install button of TestNG for Eclipse to the Help -> Eclipse Marketplace and install the TestNG libraries 
5) Restart eclipse when prompted
6) Go to Compiler -> test -> de -> letsbuildacompiler -> compiler - > CompilerTest.java under the src folder
7) Click on the error part against @BeforeMethod and click on Add TestNG library to solve all the errors
8) Go to Compiler -> src -> de -> letsbuildacompiler -> compiler -> Main.java and check for the path of the input file (ANTLRInputStream input = new ANTLRFileStream("<INPUT_FILE_PATH>");) you are passing to check output
9) Change the paths to the code files you want to test by the one's given under Sample Programs (Example: add.apla, sub.apla, functions.apla)
10) Run the Main.java
11) Press 1 --> Intermediate 2 --> Output
12) Check rigorously for all sample programs 
13) You can check the grammar(Demo.g4) under the Parser folder inside the src folder


[Intermediate Code]
Our intermediate code is written using function calls that reads the parse tree generated by ANTLR. The intermediate code is basically a .class file for Java that we manually write using a stack, assembly-like load statements, and storing values into addresses etc. An example can be found by running the method "compile(ANTLRInputStream input)". While running the Main.java file, press 1 for generating intermediate code for your input code.

[Additional Features]
1. Functions:
	We implemented functions as a blueprint for if-then-else statements and decided to keep the feature. Functions do not accept arguments as of now.
2. Scope:
	To have multiple variables within/outside of functions, we implemented scope so this can be allowed.
