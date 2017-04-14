grammar Demo;

program
	: (statement ';')+;
	
statement : println 
			| varDeclartaion
			| assignment
			;

expression
	: left=expression '/' right=expression #DIV
	| left=expression '*' right=expression #MULT
	| left=expression '+' right=expression #PLUS
	| left=expression '-' right=expression #MINUS
	| num=NUM #Number
	| varName =IDENTIFIER #Variable
	;

varDeclaration:  'int' IDENTIFIER ;

assignment: varName=IDENTIFIER '=' expr=expression;

println
	: 'println(' argument=expression ')' ;

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;

NUM
	: [0-9]+;

WHITESPACE
	: [ \t\n\r]+ -> skip;