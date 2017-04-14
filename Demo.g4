grammar Demo;

program
	: programPiece+ ;

programPiece: statement ';' #StatementPiece
			| method #MethodPiece
			;

statement
	: println
	| varAssignment
	| assignment
	;

expression
	: left=expression operator=('*' | '/') right=expression #MULTDIV
	| left=expression operator=('+' | '-') right=expression #PLUSMINUS
	| num=NUM #Number
	| varName=NAME #Variable
	| methodCall #MethodExp
	;
	
assignment: varName=NAME '=' expr=expression;

varAssignment
	: 'int' varName=NAME;

println
	: 'println(' argument=expression ')' ;

method
	: 'int' methName=NAME '(' ')' '{' statements=statementList 'return' returnVal=expression ';' '}' ;
	
statementList: (statement ';')* ;

methodCall
	: methName=NAME '(' ')' ;

NAME
	: [a-zA-Z][a-zA-Z0-9]*;

NUM
	: [0-9]+;

WHITESPACE
	: [ \t\n\r]+ -> skip;