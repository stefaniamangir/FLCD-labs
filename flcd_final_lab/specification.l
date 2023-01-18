%{ 	 
#include <stdio.h>
#include <string.h>
#include "y.tab.h"
int current_line = 0;
%} 

%option noyywrap
%option caseless


IDENTIFIER           [a-zA-Z]+[a-zA-Z0-9_]*    
NUMBER_CONST         [+-]?[1-9][0-9]*|0
CHAR_CONST	     \'[a-zA-Z0-9]\'
STRING_CONST	     \'\'\'[a-zA-Z0-9 ]+\'\'\'


%%

"and"		     {printf( "Reserved word: %s\n", yytext ); return AND;} 
"or"		     {printf( "Reserved word: %s\n", yytext ); return OR;} 
"arr"		     {printf( "Reserved word: %s\n", yytext ); return ARR;}   
"number"             {printf( "Reserved word: %s\n", yytext ); return NUMBER;}
"string"	     {printf( "Reserved word: %s\n", yytext ); return STRING;}
"if"	    	     {printf( "Reserved word: %s\n", yytext ); return IF;}
"else"		     {printf( "Reserved word: %s\n", yytext ); return ELSE;}
"for"		     {printf( "Reserved word: %s\n", yytext ); return FOR;}
"while"		     {printf( "Reserved word: %s\n", yytext ); return WHILE;}
"input"		     {printf( "Reserved word: %s\n", yytext ); return INPUT;}
"print"		     {printf( "Reserved word: %s\n", yytext ); return PRINT;}

"(" 			{printf( "Separator: %s\n", yytext ); return OPEN_ROUND_BRACKET;}
")"			{printf( "Separator: %s\n", yytext ); return CLOSED_ROUND_BRACKET;}
"["			{printf( "Separator: %s\n", yytext ); return OPEN_RIGHT_BRACKET;}
"]"			{printf( "Separator: %s\n", yytext ); return CLOSED_RIGHT_BRACKET;}
"{"			{printf( "Separator: %s\n", yytext ); return OPEN_CURLY_BRACKET;}
"}"			{printf( "Separator: %s\n", yytext ); return CLOSED_CURLY_BRACKET;}
":"			{printf( "Separator: %s\n", yytext ); return COLON;}
";"			{printf( "Separator: %s\n", yytext ); return SEMICOLON;}
"."			{printf( "Separator: %s\n", yytext ); return DOT;}
","			{printf( "Separator: %s\n", yytext ); return COMMA;}


"+"			{printf( "Operator: %s\n", yytext ); return PLUS;}
"-"			{printf( "Operator: %s\n", yytext ); return MINUS;}
"*"			{printf( "Operator: %s\n", yytext ); return MULTIPLICATION;}
"//"			{printf( "Operator: %s\n", yytext ); return DIVISION;}
"%"			{printf( "Operator: %s\n", yytext ); return MOD;}
"="			{printf( "Operator: %s\n", yytext ); return ASSIGN;}
"<"			{printf( "Operator: %s\n", yytext ); return LESS;}
">"			{printf( "Operator: %s\n", yytext ); return MORE;}
"<="			{printf( "Operator: %s\n", yytext ); return LESS_OR_EQUAL;}
">="			{printf( "Operator: %s\n", yytext ); return MORE_OR_EQUAL;}
"=="			{printf( "Operator: %s\n", yytext ); return EQUAL;}
"!="			{printf( "Operator: %s\n", yytext ); return DIFFERENT;}
"!"			{printf( "Operator: %s\n", yytext ); return NOT;}

{IDENTIFIER}		{printf( "Identifier: %s\n", yytext ); return IDENTIFIER;}
{NUMBER_CONST}		{printf( "Number: %s\n", yytext ); return NUMBER_CONST;}
{CHAR_CONST}		{printf( "Character: %s\n", yytext ); return CHAR_CONST;}
{STRING_CONST}		{printf( "String: %s\n", yytext ); return STRING_CONST;}

[ \t]+  	{}
[\n]+ {current_line++;}

[+-]?0[0-9]*            {printf("Illegal number constant at line %d\n", current_line); return -1;}
[0-9_][a-zA-Z0-9_]*     {printf("Illegal identifier at line %d\n", current_line); return -1;}
[\'][a-zA-Z0-9_]{2,}[\']|[\'][a-zA-Z0-9_]|[a-zA-Z0-9_][\']  {printf("Illegal character constant at line %d\n", current_line); return -1;}
\'\'\'[a-zA-Z0-9 ]+|[a-zA-Z0-9 ]+\'\'\'      {printf("Illegal string constant at line %d\n", current_line); return -1;}

%%

