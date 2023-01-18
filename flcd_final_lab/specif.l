%{ 	 
#include <stdio.h>
#include <string.h>
int current_line = 0;
%} 

%option noyywrap
%option caseless


IDENTIFIER           [a-zA-Z]+[a-zA-Z0-9_]*    
NUMBER_CONST         [+-]?[1-9][0-9]*|0
CHAR_CONST	     \'[a-zA-Z0-9]\'
STRING_CONST	     \'\'\'[a-zA-Z0-9 ]+\'\'\'


%%

"and"		     {printf( "Reserved word: %s\n", yytext );} 
"or"		     {printf( "Reserved word: %s\n", yytext );} 
"arr"		     {printf( "Reserved word: %s\n", yytext );}   
"number"             {printf( "Reserved word: %s\n", yytext );}
"string"	     {printf( "Reserved word: %s\n", yytext );}
"if"	    	     {printf( "Reserved word: %s\n", yytext );}
"else"		     {printf( "Reserved word: %s\n", yytext );}
"for"		     {printf( "Reserved word: %s\n", yytext );}
"while"		     {printf( "Reserved word: %s\n", yytext );}
"input"		     {printf( "Reserved word: %s\n", yytext );}
"print"		     {printf( "Reserved word: %s\n", yytext );}

"(" 			{printf( "Separator: %s\n", yytext );}
")"			{printf( "Separator: %s\n", yytext );}
"["			{printf( "Separator: %s\n", yytext );}
"]"			{printf( "Separator: %s\n", yytext );}
"{"			{printf( "Separator: %s\n", yytext );}
"}"			{printf( "Separator: %s\n", yytext );}
":"			{printf( "Separator: %s\n", yytext );}
";"			{printf( "Separator: %s\n", yytext );}
"."			{printf( "Separator: %s\n", yytext );}
","			{printf( "Separator: %s\n", yytext );}


"+"			{printf( "Operator: %s\n", yytext );}
"-"			{printf( "Operator: %s\n", yytext );}
"*"			{printf( "Operator: %s\n", yytext );}
"//"			{printf( "Operator: %s\n", yytext );}
"%"			{printf( "Operator: %s\n", yytext );}
"="			{printf( "Operator: %s\n", yytext );}
"<"			{printf( "Operator: %s\n", yytext );}
">"			{printf( "Operator: %s\n", yytext );}
"<="			{printf( "Operator: %s\n", yytext );}
">="			{printf( "Operator: %s\n", yytext );}
"=="			{printf( "Operator: %s\n", yytext );}
"!="			{printf( "Operator: %s\n", yytext );}
"!"			{printf( "Operator: %s\n", yytext );}

{IDENTIFIER}		{printf( "Identifier: %s\n", yytext );}
{NUMBER_CONST}		{printf( "Number: %s\n", yytext );}
{CHAR_CONST}		{printf( "Character: %s\n", yytext );}
{STRING_CONST}		{printf( "String: %s\n", yytext );}

[ \t]+  	{}
[\n]+ {current_line++;}

[+-]?0[0-9]*            {printf("Illegal number constant at line %d\n", current_line); return -1;}
[0-9_][a-zA-Z0-9_]*     {printf("Illegal identifier at line %d\n", current_line); return -1;}
\'[a-zA-Z0-9_]{2,}\'|\'[a-zA-Z0-9_]|[a-zA-Z0-9_]\'  {printf("Illegal character constant at line %d\n", current_line); return -1;}
\'\'\'[a-zA-Z0-9 ]+|[a-zA-Z0-9 ]+\'\'\'      {printf("Illegal string constant at line %d\n", current_line); return -1;}

%%

