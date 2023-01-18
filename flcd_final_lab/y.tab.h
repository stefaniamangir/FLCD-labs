/* A Bison parser, made by GNU Bison 2.3.  */

/* Skeleton interface for Bison's Yacc-like parsers in C

   Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2, or (at your option)
   any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor,
   Boston, MA 02110-1301, USA.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.

   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */

/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     AND = 258,
     OR = 259,
     ARR = 260,
     NUMBER = 261,
     STRING = 262,
     IF = 263,
     ELSE = 264,
     FOR = 265,
     WHILE = 266,
     INPUT = 267,
     PRINT = 268,
     OPEN_ROUND_BRACKET = 269,
     CLOSED_ROUND_BRACKET = 270,
     OPEN_RIGHT_BRACKET = 271,
     CLOSED_RIGHT_BRACKET = 272,
     OPEN_CURLY_BRACKET = 273,
     CLOSED_CURLY_BRACKET = 274,
     COLON = 275,
     SEMICOLON = 276,
     DOT = 277,
     COMMA = 278,
     PLUS = 279,
     MINUS = 280,
     MULTIPLICATION = 281,
     DIVISION = 282,
     MOD = 283,
     ASSIGN = 284,
     LESS = 285,
     MORE = 286,
     LESS_OR_EQUAL = 287,
     MORE_OR_EQUAL = 288,
     EQUAL = 289,
     DIFFERENT = 290,
     NOT = 291,
     IDENTIFIER = 292,
     NUMBER_CONST = 293,
     CHAR_CONST = 294,
     STRING_CONST = 295
   };
#endif
/* Tokens.  */
#define AND 258
#define OR 259
#define ARR 260
#define NUMBER 261
#define STRING 262
#define IF 263
#define ELSE 264
#define FOR 265
#define WHILE 266
#define INPUT 267
#define PRINT 268
#define OPEN_ROUND_BRACKET 269
#define CLOSED_ROUND_BRACKET 270
#define OPEN_RIGHT_BRACKET 271
#define CLOSED_RIGHT_BRACKET 272
#define OPEN_CURLY_BRACKET 273
#define CLOSED_CURLY_BRACKET 274
#define COLON 275
#define SEMICOLON 276
#define DOT 277
#define COMMA 278
#define PLUS 279
#define MINUS 280
#define MULTIPLICATION 281
#define DIVISION 282
#define MOD 283
#define ASSIGN 284
#define LESS 285
#define MORE 286
#define LESS_OR_EQUAL 287
#define MORE_OR_EQUAL 288
#define EQUAL 289
#define DIFFERENT 290
#define NOT 291
#define IDENTIFIER 292
#define NUMBER_CONST 293
#define CHAR_CONST 294
#define STRING_CONST 295




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
# define YYSTYPE_IS_TRIVIAL 1
#endif

extern YYSTYPE yylval;

