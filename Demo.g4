grammar Demo;

program
	: (println ';')+;

expression
	: left=expression '/' right=expression #DIV
	| left=expression '*' right=expression #MULT
	| left=expression '+' right=expression #PLUS
	| left=expression '-' right=expression #MINUS
	| num=NUM #Number
	;

println
	: 'println(' argument=expression ')' ;

NUM
	: [0-9]+;

WHITESPACE
	: [ \t\n\r]+ -> skip;