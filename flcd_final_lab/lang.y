%{
#include <stdio.h>
#include <stdlib.h>

#define YYDEBUG 1 
%}

%token AND
%token OR
%token ARR
%token NUMBER
%token STRING
%token IF
%token ELSE
%token FOR
%token WHILE
%token INPUT
%token PRINT

%token OPEN_ROUND_BRACKET
%token CLOSED_ROUND_BRACKET
%token OPEN_RIGHT_BRACKET
%token CLOSED_RIGHT_BRACKET
%token OPEN_CURLY_BRACKET
%token CLOSED_CURLY_BRACKET
%token COLON
%token SEMICOLON
%token DOT
%token COMMA

%token PLUS
%token MINUS
%token MULTIPLICATION
%token DIVISION
%token MOD
%token ASSIGN
%token LESS
%token MORE
%token LESS_OR_EQUAL
%token MORE_OR_EQUAL
%token EQUAL
%token DIFFERENT
%token NOT

%token IDENTIFIER
%token NUMBER_CONST
%token CHAR_CONST
%token STRING_CONST

%start program

%%

program : statement_list
statement_list : statement | statement statement_list
statement : simple_statement | compound_statement
simple_statement : assignment_statement | io_statement | declaration_statement
compound_statement : if_statement | while_statement | for_statement

assignment_statement : IDENTIFIER ASSIGN expression

type : NUMBER | STRING 
string_expression : STRING_CONST | IDENTIFIER | index_identifier |
                      string_expression PLUS string_expression
factor : OPEN_ROUND_BRACKET number_expression CLOSED_ROUND_BRACKET | IDENTIFIER | NUMBER_CONST | IDENTIFIER OPEN_RIGHT_BRACKET NUMBER CLOSED_RIGHT_BRACKET 
term : term MULTIPLICATION factor | term DIVISION factor | factor
number_expression : number_expression PLUS term | number_expression MINUS term | term
expression : number_expression | string_expression
var_declaration : type IDENTIFIER | type IDENTIFIER ASSIGN expression
arr_declaration : ARR OPEN_RIGHT_BRACKET NUMBER CLOSED_RIGHT_BRACKET IDENTIFIER | ARR OPEN_RIGHT_BRACKET NUMBER CLOSED_RIGHT_BRACKET IDENTIFIER ASSIGN NUMBER
declaration_statement : var_declaration | arr_declaration


index_identifier : IDENTIFIER OPEN_RIGHT_BRACKET NUMBER CLOSED_RIGHT_BRACKET 
io_statement : function_name OPEN_ROUND_BRACKET IDENTIFIER CLOSED_ROUND_BRACKET
function_name : PRINT | INPUT


if_statement : IF condition OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET SEMICOLON | IF condition OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET SEMICOLON ELSE OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET SEMICOLON

condition : OPEN_ROUND_BRACKET expression  relation  expression CLOSED_ROUND_BRACKET
relation : EQUAL | LESS | LESS_OR_EQUAL | MORE | MORE_OR_EQUAL | DIFFERENT 
while_statement : WHILE condition OPEN_CURLY_BRACKET statement_list CLOSED_CURLY_BRACKET SEMICOLON
for_statement : FOR for_header COLON statement_list
for_header : OPEN_ROUND_BRACKET assignment_statement COMMA condition COMMA assignment_statement CLOSED_ROUND_BRACKET

%%


yyerror(char *s)
{
	printf("%s\n",s);
}

extern FILE *yyin;

main(int argc, char **argv)
{
	if(argc>1) yyin :  fopen(argv[1],"r");
	if(argc>2 && !strcmp(argv[2],"-d")) yydebug: 1;
	if(!yyparse()) fprintf(stderr, "\tO.K.\n");
}










