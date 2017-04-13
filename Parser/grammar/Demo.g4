grammar Demo;

addition
	: left=addition '+' right=NUM #PLUS | num=NUM #Number
	;
NUM: [0-9]+;
