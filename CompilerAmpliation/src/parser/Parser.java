//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Java declarations*/
/*   This code is copied in the beginning of the generated Java file*/
import scanner.Scanner;
import ast.expression.*;
import ast.program.*;
import ast.statement.*;
import ast.statement.switchCase.*;
import ast.type.*;
import ast.ASTNode;
import java.util.*;
//#line 28 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short CHAR_CONSTANT=258;
public final static short REAL_CONSTANT=259;
public final static short LEQ=260;
public final static short GEQ=261;
public final static short DOUBLEEQUALS=262;
public final static short DIFFERENT=263;
public final static short SHIFTLEFT=264;
public final static short SHIFTRIGHT=265;
public final static short AND=266;
public final static short OR=267;
public final static short ID=268;
public final static short WRITE=269;
public final static short READ=270;
public final static short VOID=271;
public final static short MAIN=272;
public final static short CHAR=273;
public final static short DOUBLE=274;
public final static short INT=275;
public final static short STRUCT=276;
public final static short IF=277;
public final static short ELSE=278;
public final static short WHILE=279;
public final static short RETURN=280;
public final static short FOR=281;
public final static short DO=282;
public final static short SWITCH=283;
public final static short CASE=284;
public final static short DEFAULT=285;
public final static short INC=286;
public final static short DEC=287;
public final static short DIVIDEEQUALS=288;
public final static short TIMESEQUALS=289;
public final static short MINUSEQUALS=290;
public final static short PLUSEQUALS=291;
public final static short BREAK=292;
public final static short IFX=293;
public final static short POTENTIAL=294;
public final static short unary_minus=295;
public final static short CAST=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   19,   19,   19,
   19,   19,   19,   19,   21,   21,   28,   31,   31,   22,
   25,   23,   24,   32,   32,   34,   34,   33,   33,   36,
   36,   35,   29,    9,    9,   37,   37,   10,   10,   10,
   30,   38,   38,   38,    4,    4,    4,   13,   13,   27,
   18,   20,   39,   26,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    1,    1,    1,    1,    1,    2,
    3,    3,    3,    3,    3,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    2,    2,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    0,    2,    2,
    3,    3,    3,    3,    7,    5,    5,    3,    1,    5,
    6,    8,    7,    1,    2,    4,    3,    2,    1,    5,
    4,    2,    4,    3,    1,    3,    1,    1,    1,    1,
    4,    4,    4,    4,    3,    3,    3,    2,    0,    3,
    2,    2,    4,    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   88,   90,   89,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   99,    0,
    0,    0,   87,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   95,    0,   96,    0,   97,    0,    0,
    0,   19,    0,  103,   98,    0,    0,    0,   93,   86,
   92,   94,   99,   20,    0,    0,   99,   99,    0,    0,
    0,   99,   18,    0,    0,   99,   13,    0,    0,   14,
   11,    0,   54,   56,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   49,   16,    0,
    0,    0,    0,   25,   26,   27,   28,   29,    0,   36,
   37,   52,   53,   12,   10,    0,    0,    0,    0,    0,
    0,    0,    0,   17,   69,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   59,   60,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   21,   22,   23,   24,   30,
    9,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   50,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    8,    0,    0,    0,    0,   68,    0,
    0,    0,    0,   91,    0,   70,    0,    0,    0,    0,
    0,    0,    0,   71,    0,    0,    0,   79,   65,    0,
    0,   73,    0,   75,   78,   72,   17,   17,    0,    0,
    0,   80,   76,   82,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   45,   11,   12,   13,   88,  107,   40,
   41,   60,   61,   68,  115,   42,   90,   91,   92,   93,
   94,   95,   96,   97,   98,   99,  100,  101,  102,  103,
  116,  206,  207,  214,  222,  208,   22,   15,   16,
};
final static short yysindex[] = {                         0,
    0,    0,   62, -247,    0,    0,    0, -107,    0,    0,
    0,    0,    0,  -90,  -89,  -88,  -16,   -2,    0,   14,
 -201,  -22,    0, -199,   95, -180,  144,  -35,   45, -108,
   19,   42, -178,    0,   81,    0,   84,    0,   61,  -68,
    5,    0,   79,    0,    0,  -87,   82,   43,    0,    0,
    0,    0,    0,    0,   85, -179,    0,    0,   86,   91,
  177,    0,    0,   96,   97,    0,    0, 1126,  102,    0,
    0,  107,    0,    0,    0,  167,  409,  409,  178,  180,
  409,  193,  719,  194,  409,  409,  510,    0,    0,  110,
  158,  183,  184,    0,    0,    0,    0,    0,  186,    0,
    0,    0,    0,    0,    0,  374,  213,  628,  213,  409,
  409,  628, 1126,    0,    0,  -19,  409,  -32,  -23,  220,
  385,  409,  409,  409,  409,  409,  409,    0,    0,  409,
  409,  409,  409,  409,  409,  409,  409,  409,  409,  409,
  409,  409,  409,  409,  409,    0,    0,    0,    0,    0,
    0,   93,  409,  420,  432,  409, 1097,  229,  467,  409,
    0,   -3,   -3,   -3,   -3,  -32,  -32,  628,  628,  628,
  628,  628,  479,   -3,   -3,  221,  221,  -38,  -38,  -38,
  -23,  514,    0,    0,  628,  719,  719,  526,    0,  409,
  148,  -23,  409,    0,   -6,    0,  409,  567,  -10,  675,
  719,  150,  243,    0,  409,  160, -155,    0,    0,  719,
  578,    0,  212,    0,    0,    0,    0,    0,   -4,   -4,
  230,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  147,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 1268,    0,    0,    0,    0,    0,    0,  -50,    0,    0,
    0,    0,    0,    0,    0,   20,    0,    0,    0,    0,
    0,    0,  231,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  236,   -9,  237,    0,
    0,  244,  231,    0,    0,    0,    0, 1223,   56,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  231,    0,    0,    0,
    0,  902,  912,  963, 1153, 1276, 1288,  -14,   -8,   30,
   32,   92,    0, 1197, 1233,  857,  866,  753,  806,  815,
   65,    0,    0,    0,  145,  231,  231,    0,    0,    0,
    0,  101,    0,    0,  -33,    0,  263,    0,    0,   11,
  231,    0,    0,    0,    0,    0,  188,    0,    0,  231,
    0,    0,    0,    0,    0,    0,    0,    0,   73,  -40,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  302,    0,    0,    0,    0,  -58,   44,
  284,  157,  301,  -96,  -37,  265, 1503,    0,  127,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -11,    0,    0,    0,  109,  123,  125,    0,    0,
};
final static int YYTABLESIZE=1708;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         66,
   21,   24,   26,   21,  142,   39,   66,  145,   58,  140,
  138,   66,  139,  145,  141,   19,   44,  157,   58,  109,
   17,   33,  145,   28,   18,   66,   64,  137,   85,  136,
   89,   85,   63,  142,   85,   87,   34,   29,  140,  138,
   86,  139,  145,  141,   64,   55,   14,  152,   56,   85,
   63,   67,  144,   31,   67,   32,   57,   35,  144,   47,
   57,   57,   57,   57,   57,   57,   57,  144,   67,   67,
   62,   67,   61,   46,   15,  156,   37,   57,   57,   57,
   57,   57,   57,   59,   77,   43,   56,  144,   62,   50,
   61,   66,   47,    5,    6,    7,   47,   47,   47,   47,
   47,   38,   47,   67,   46,   38,   38,   38,   38,   38,
   57,   38,   57,   47,   47,   47,   47,   47,   47,   89,
  219,  220,   38,   38,   38,   38,   38,   38,  205,  213,
  120,   58,  100,  184,   49,  100,  153,   83,   33,   25,
   27,   83,   83,   83,   83,   83,  142,   83,   47,  100,
  100,  140,  138,   36,  139,  145,  141,   38,   83,   83,
   83,   83,   83,   83,    5,    6,    7,    8,  146,  137,
  134,  136,  135,   51,  195,  196,   52,   20,   23,   23,
   23,   89,   89,   53,  100,   84,  142,   33,   84,  209,
   87,  140,  138,   83,  139,  145,  141,   81,  216,   54,
  144,   57,   38,   84,   58,   87,  106,   62,   66,  137,
  134,  136,  135,   64,   65,   67,  147,  110,   69,  111,
   70,   71,   72,   66,   66,   66,  104,  122,  123,  124,
  125,  105,  113,  117,   66,   66,   66,    5,    6,    7,
  144,  148,  149,   66,  150,   66,   66,   66,   66,   66,
   66,   66,   73,   74,   75,  143,  153,  142,   66,  158,
  160,  143,  140,   76,   77,   78,  145,  141,  190,  218,
  199,  201,   79,  205,   80,   81,   82,   83,   84,   57,
   57,   57,   57,  210,  212,   57,   57,  221,  224,   58,
  143,    5,    6,    7,  102,  101,   67,   67,   67,   67,
   67,   67,  104,   58,   10,   57,   57,   57,   57,   57,
   57,  144,   74,   57,   48,   47,   47,   47,   47,   30,
   63,   47,   47,  203,   38,   38,   38,   38,  223,  215,
   38,   38,    4,    0,    5,    6,    7,    8,    0,    0,
    0,   47,   47,   47,   47,   47,   47,    0,    0,   47,
   38,   38,   38,   38,   38,   38,   81,   81,   38,    0,
   83,   83,   83,   83,    0,    0,   83,   83,    0,  122,
  123,  124,  125,    0,    0,  126,  127,  100,  100,  100,
  100,  100,  100,    0,    0,    0,   83,   83,   83,   83,
   83,   83,    0,    0,   83,  128,  129,  130,  131,  132,
  133,    0,    0,  143,    0,    0,   85,    0,    0,  122,
  123,  124,  125,   87,  151,  126,  127,    0,   86,    0,
    0,  142,    0,    0,    0,  161,  140,  138,    0,  139,
  145,  141,    0,    0,    0,  128,  129,  130,  131,  132,
  133,   85,    0,  143,  137,  134,  136,  135,   87,    5,
    6,    7,    8,   86,    0,    0,  142,    0,    0,    0,
  186,  140,  138,    0,  139,  145,  141,    0,  142,    0,
    0,    0,  187,  140,  138,  144,  139,  145,  141,  137,
  134,  136,  135,    0,    0,    0,    0,    0,    0,    0,
    0,  137,  134,  136,  135,    0,    0,    0,    0,    0,
    0,    0,    0,  142,    0,    0,    0,  191,  140,  138,
  144,  139,  145,  141,  143,  142,    0,    0,    0,    0,
  140,  138,  144,  139,  145,  141,  137,  134,  136,  135,
    0,    0,    0,    0,    0,    0,  193,    0,  137,  134,
  136,  135,   85,    0,    0,    0,    0,    0,    0,   87,
  142,    0,    0,    0,   86,  140,  138,  144,  139,  145,
  141,    0,  142,    0,    0,    0,    0,  140,  138,  144,
  139,  145,  141,  137,  134,  136,  135,    0,    0,    0,
    0,    0,    0,    0,  197,  137,  134,  136,  135,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  142,  144,    0,  194,  204,  140,  138,
    0,  139,  145,  141,  142,    0,  144,    0,    0,  140,
  138,    0,  139,  145,  141,    0,  137,  134,  136,  135,
   73,   74,   75,    0,    0,  217,    0,  137,  134,  136,
  135,   76,    0,    0,  122,  123,  124,  125,    0,    0,
  126,  127,    0,    0,    0,    0,    0,  144,    0,    0,
    0,    0,    0,    0,  142,   73,   74,   75,  144,  140,
  138,    0,  139,  145,  141,    0,   76,    0,  143,  122,
  123,  124,  125,    0,    0,  126,  127,  137,  134,  136,
  135,  122,  123,  124,  125,    0,    0,  126,  127,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  142,    0,  143,    0,    0,  140,  138,  144,  139,
  145,  141,    0,    0,    0,  143,  122,  123,  124,  125,
    0,    0,  126,  127,  137,    0,  136,  135,  122,  123,
  124,  125,    0,    0,  126,  127,    0,    0,    0,    0,
    0,   85,    0,    0,    0,    0,    0,    0,   87,    0,
  143,    0,    0,   86,    0,  144,   73,   74,   75,    0,
    0,    0,  143,  122,  123,  124,  125,   76,    0,  126,
  127,    0,    5,    6,    7,  122,  123,  124,  125,   32,
    0,  126,  127,   32,   32,   32,   32,   32,    0,   32,
    0,    0,    0,    0,    0,    0,    0,  143,    0,    0,
   32,   32,   32,   32,   32,   32,    0,    0,    0,  143,
    0,    0,    0,    0,    0,    0,  122,  123,  124,  125,
    0,    0,  126,  127,    0,    0,    0,  122,  123,  124,
  125,  114,   33,  126,  127,   32,   33,   33,   33,   33,
   33,   35,   33,    0,    0,   35,   35,   35,   35,   35,
  143,   35,    0,   33,   33,   33,   33,   33,   33,    0,
    0,  143,   35,   35,   35,   35,   35,   35,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  122,  123,  124,
  125,    0,    0,  126,  127,    0,    0,   31,   33,   31,
   31,   31,    0,    0,    0,    0,   34,   35,   34,   34,
   34,    0,    0,    0,   31,   31,   31,   31,   31,   31,
    0,  143,    0,   34,   34,   34,   34,   34,   34,    0,
    0,    0,    0,    0,  122,  123,  124,  125,    0,    0,
  126,  127,   43,    0,    0,   43,    0,    0,    0,   31,
    0,    0,   41,    0,    0,   41,    0,    0,   34,   43,
   43,   43,   43,   43,   43,    0,    0,    0,  143,   41,
   41,   41,   41,   41,   41,   73,   74,   75,    0,    0,
    0,    0,    0,    0,    0,    0,   76,   77,   78,    0,
    0,    0,    0,    0,   43,   79,    0,   80,   81,   82,
   83,   84,    0,   39,   41,    0,   39,    0,    0,    0,
    0,    0,   32,   32,   32,   32,    0,    0,   32,   32,
   39,   39,   39,   39,   39,   39,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   32,   32,
   32,   32,   32,   32,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   39,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   33,   33,   33,   33,    0,
    0,   33,   33,    0,   35,   35,   35,   35,    0,    0,
   35,   35,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   33,   33,   33,   33,   33,   33,    0,    0,    0,
   35,   35,   35,   35,   35,   35,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   31,   31,   31,   31,
    0,    0,   31,   31,    0,   34,   34,   34,   34,   85,
    0,   34,   34,    0,    0,    0,   87,    0,    0,    0,
    0,   86,   31,   31,   31,   31,   31,   31,    0,    0,
    0,   34,   34,   34,   34,   34,   34,    0,   85,    0,
    0,   43,   43,   43,   43,   87,    0,   43,   43,    0,
   86,   41,   41,   41,   41,    0,    0,   41,   41,    0,
    0,    0,    0,    0,    0,    0,    0,   43,   43,   43,
   43,   43,   43,   44,    0,    0,   44,   41,   41,   41,
   41,   41,   41,    0,    0,    0,    0,    0,    0,    0,
   44,   44,   44,   44,   44,   44,    0,    0,    0,    0,
    0,  189,   39,   39,   39,   39,    0,    0,   39,   39,
    0,    0,    0,    0,    0,    0,    0,   40,    0,    0,
   40,    0,    0,    0,    0,   44,    0,    0,   39,   39,
   39,   39,   39,   39,   40,   40,   40,   40,   40,   40,
    0,    0,    0,   48,    0,    0,   48,    0,    0,    0,
    0,    0,    0,   42,    0,    0,   42,    0,    0,    0,
   48,   48,    0,   48,    0,   48,    0,    0,    0,   40,
   42,   42,   42,   42,   42,   42,    0,    0,    0,    0,
   17,    0,    0,    0,    0,    0,    0,   17,    0,    0,
    0,    0,   17,    0,    0,   48,   45,    0,    0,   45,
    0,    0,    0,    0,    0,   42,   17,    0,   46,    0,
    0,   46,    0,   45,   45,    0,   45,    0,   45,    0,
    0,    0,    0,    0,    0,   46,   46,    0,   46,    0,
   46,    0,    0,   73,   74,   75,    0,    0,    0,    0,
    0,    0,    0,    0,   76,   77,   78,    0,   45,    0,
    0,    0,    0,   79,    0,   80,   81,   82,   83,   84,
   46,    0,   73,   74,   75,    0,    0,    0,    0,    0,
    0,    0,   17,   76,   77,   78,    0,    0,    0,    0,
    0,    0,   79,    0,   80,   81,   82,   83,   84,    0,
    0,    0,   44,   44,   44,   44,    0,    0,   44,   44,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   44,   44,
   44,   44,   44,   44,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   40,   40,   40,
    0,    0,   40,   40,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   40,   40,   40,   40,   40,   40,   48,   48,
    0,    0,   42,   42,   42,   42,    0,    0,   42,   42,
    0,    0,    0,    0,    0,    0,    0,    0,   48,   48,
   48,   48,   48,   48,    0,    0,    0,    0,   42,   42,
   42,   42,   42,   42,   17,   17,   17,    0,    0,    0,
    0,    0,    0,    0,    0,   17,   17,   17,    0,    0,
    0,   45,   45,    0,   17,    0,   17,   17,   17,   17,
   17,    0,    0,   46,   46,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   45,   45,    0,    0,    0,
    0,    0,    0,   46,   46,   46,   46,   46,   46,  108,
  108,    0,    0,  112,    0,    0,    0,  118,  119,  121,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  108,    0,
    0,    0,  154,  155,    0,    0,    0,    0,    0,  159,
    0,    0,    0,    0,  162,  163,  164,  165,  166,  167,
    0,    0,  168,  169,  170,  171,  172,  173,  174,  175,
  176,  177,  178,  179,  180,  181,  182,  183,    0,    0,
    0,    0,    0,    0,    0,  185,    0,    0,  188,    0,
    0,    0,  192,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  198,    0,    0,  200,    0,    0,    0,  202,
    0,    0,    0,    0,    0,    0,    0,  211,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   91,   91,   37,   41,   40,   46,   59,   42,
   43,   45,   45,   46,   47,  123,  125,  114,   59,   78,
  268,   44,   46,   40,  272,   59,   41,   60,   33,   62,
   68,   41,   41,   37,   44,   40,   59,   40,   42,   43,
   45,   45,   46,   47,   59,   41,    3,  106,   44,   59,
   59,   41,   91,   40,   44,  257,   37,  257,   91,   41,
   41,   42,   43,   44,   45,   46,   47,   91,   58,   59,
   41,   61,   41,   30,  125,  113,  257,   58,   59,   60,
   61,   62,   63,   41,  125,   41,   44,   91,   59,  268,
   59,  125,   37,  273,  274,  275,   41,   42,   43,   44,
   45,   37,   47,   93,   61,   41,   42,   43,   44,   45,
   91,   47,   93,   58,   59,   60,   61,   62,   63,  157,
  217,  218,   58,   59,   60,   61,   62,   63,  284,  285,
   87,   59,   41,   41,   93,   44,   44,   37,   44,   15,
   16,   41,   42,   43,   44,   45,   37,   47,   93,   58,
   59,   42,   43,   59,   45,   46,   47,   93,   58,   59,
   60,   61,   62,   63,  273,  274,  275,  276,   59,   60,
   61,   62,   63,   93,  186,  187,   93,  268,  268,  268,
  268,  219,  220,  123,   93,   41,   37,   44,   44,  201,
   44,   42,   43,   93,   45,   46,   47,  125,  210,  268,
   91,  123,   59,   59,  123,   59,   40,  123,  123,   60,
   61,   62,   63,   57,   58,  125,   59,   40,   62,   40,
  125,  125,   66,  257,  258,  259,  125,  260,  261,  262,
  263,  125,   40,   40,  268,  269,  270,  273,  274,  275,
   91,   59,   59,  277,   59,  279,  280,  281,  282,  283,
  284,  285,  257,  258,  259,  294,   44,   37,  292,  279,
   41,  294,   42,  268,  269,  270,   46,   47,   40,   58,
  123,  278,  277,  284,  279,  280,  281,  282,  283,  260,
  261,  262,  263,   41,  125,  266,  267,  292,   59,   59,
  294,  273,  274,  275,   59,   59,  286,  287,  288,  289,
  290,  291,   59,   41,    3,  286,  287,  288,  289,  290,
  291,   91,  125,  294,   31,  260,  261,  262,  263,   19,
   56,  266,  267,  197,  260,  261,  262,  263,  220,  207,
  266,  267,  271,   -1,  273,  274,  275,  276,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,  294,
  286,  287,  288,  289,  290,  291,  284,  285,  294,   -1,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,  260,
  261,  262,  263,   -1,   -1,  266,  267,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,  286,  287,  288,  289,
  290,  291,   -1,   -1,  294,  286,  287,  288,  289,  290,
  291,   -1,   -1,  294,   -1,   -1,   33,   -1,   -1,  260,
  261,  262,  263,   40,   41,  266,  267,   -1,   45,   -1,
   -1,   37,   -1,   -1,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  286,  287,  288,  289,  290,
  291,   33,   -1,  294,   60,   61,   62,   63,   40,  273,
  274,  275,  276,   45,   -1,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,   37,   -1,
   -1,   -1,   41,   42,   43,   91,   45,   46,   47,   60,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,
   91,   45,   46,   47,  294,   37,   -1,   -1,   -1,   -1,
   42,   43,   91,   45,   46,   47,   60,   61,   62,   63,
   -1,   -1,   -1,   -1,   -1,   -1,   58,   -1,   60,   61,
   62,   63,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   37,   -1,   -1,   -1,   45,   42,   43,   91,   45,   46,
   47,   -1,   37,   -1,   -1,   -1,   -1,   42,   43,   91,
   45,   46,   47,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   37,   91,   -1,   93,   41,   42,   43,
   -1,   45,   46,   47,   37,   -1,   91,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   60,   61,   62,   63,
  257,  258,  259,   -1,   -1,   58,   -1,   60,   61,   62,
   63,  268,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   -1,   37,  257,  258,  259,   91,   42,
   43,   -1,   45,   46,   47,   -1,  268,   -1,  294,  260,
  261,  262,  263,   -1,   -1,  266,  267,   60,   61,   62,
   63,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   37,   -1,  294,   -1,   -1,   42,   43,   91,   45,
   46,   47,   -1,   -1,   -1,  294,  260,  261,  262,  263,
   -1,   -1,  266,  267,   60,   -1,   62,   63,  260,  261,
  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,
  294,   -1,   -1,   45,   -1,   91,  257,  258,  259,   -1,
   -1,   -1,  294,  260,  261,  262,  263,  268,   -1,  266,
  267,   -1,  273,  274,  275,  260,  261,  262,  263,   37,
   -1,  266,  267,   41,   42,   43,   44,   45,   -1,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  294,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,  294,
   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,  260,  261,  262,
  263,  123,   37,  266,  267,   93,   41,   42,   43,   44,
   45,   37,   47,   -1,   -1,   41,   42,   43,   44,   45,
  294,   47,   -1,   58,   59,   60,   61,   62,   63,   -1,
   -1,  294,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,
  263,   -1,   -1,  266,  267,   -1,   -1,   41,   93,   43,
   44,   45,   -1,   -1,   -1,   -1,   41,   93,   43,   44,
   45,   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,
   -1,  294,   -1,   58,   59,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   41,   -1,   -1,   44,   -1,   -1,   -1,   93,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   93,   58,
   59,   60,   61,   62,   63,   -1,   -1,   -1,  294,   58,
   59,   60,   61,   62,   63,  257,  258,  259,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,   -1,
   -1,   -1,   -1,   -1,   93,  277,   -1,  279,  280,  281,
  282,  283,   -1,   41,   93,   -1,   44,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,  260,  261,  262,  263,   33,
   -1,  266,  267,   -1,   -1,   -1,   40,   -1,   -1,   -1,
   -1,   45,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   33,   -1,
   -1,  260,  261,  262,  263,   40,   -1,  266,  267,   -1,
   45,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   41,   -1,   -1,   44,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
   -1,  125,  260,  261,  262,  263,   -1,   -1,  266,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   93,   -1,   -1,  286,  287,
  288,  289,  290,  291,   58,   59,   60,   61,   62,   63,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
   58,   59,   -1,   61,   -1,   63,   -1,   -1,   -1,   93,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   93,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   93,   59,   -1,   41,   -1,
   -1,   44,   -1,   58,   59,   -1,   61,   -1,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,   61,   -1,
   63,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  268,  269,  270,   -1,   93,   -1,
   -1,   -1,   -1,  277,   -1,  279,  280,  281,  282,  283,
   93,   -1,  257,  258,  259,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  125,  268,  269,  270,   -1,   -1,   -1,   -1,
   -1,   -1,  277,   -1,  279,  280,  281,  282,  283,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,  266,  267,
   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,  257,  258,  259,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  268,  269,  270,   -1,   -1,
   -1,  266,  267,   -1,  277,   -1,  279,  280,  281,  282,
  283,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,
   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,   77,
   78,   -1,   -1,   81,   -1,   -1,   -1,   85,   86,   87,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  106,   -1,
   -1,   -1,  110,  111,   -1,   -1,   -1,   -1,   -1,  117,
   -1,   -1,   -1,   -1,  122,  123,  124,  125,  126,  127,
   -1,   -1,  130,  131,  132,  133,  134,  135,  136,  137,
  138,  139,  140,  141,  142,  143,  144,  145,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  153,   -1,   -1,  156,   -1,
   -1,   -1,  160,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  190,   -1,   -1,  193,   -1,   -1,   -1,  197,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  205,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","CHAR_CONSTANT",
"REAL_CONSTANT","LEQ","GEQ","DOUBLEEQUALS","DIFFERENT","SHIFTLEFT","SHIFTRIGHT",
"AND","OR","ID","WRITE","READ","VOID","MAIN","CHAR","DOUBLE","INT","STRUCT",
"IF","ELSE","WHILE","RETURN","FOR","DO","SWITCH","CASE","DEFAULT","INC","DEC",
"DIVIDEEQUALS","TIMESEQUALS","MINUSEQUALS","PLUSEQUALS","BREAK","IFX",
"POTENTIAL","\"unary_minus\"","CAST",
};
final static String yyrule[] = {
"$accept : final",
"final : program",
"program : program_body main",
"program_body : program_body variable",
"program_body : program_body function",
"program_body :",
"function : typed_function",
"function : void_function",
"function_invocation : ID '(' listOfExpressions ')'",
"function_invocation : ID '(' ')'",
"typed_function : built_in_type ID '(' listOfParameters ')' '{' function_body '}'",
"typed_function : built_in_type ID '(' ')' '{' function_body '}'",
"void_function : VOID ID '(' listOfParameters ')' '{' function_body '}'",
"void_function : VOID ID '(' ')' '{' function_body '}'",
"main : VOID MAIN '(' ')' '{' function_body '}'",
"function_body : listOfVariables listOfStatements",
"listOfStatements : listOfStatements statement",
"listOfStatements :",
"listOfParameters : listOfParameters ',' parameter",
"listOfParameters : parameter",
"parameter : built_in_type ID",
"statement : expression ';'",
"statement : read ';'",
"statement : increment ';'",
"statement : write ';'",
"statement : if_else",
"statement : while",
"statement : for",
"statement : switch",
"statement : do_while",
"statement : return ';'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '-' expression",
"expression : expression '%' expression",
"expression : assignment",
"expression : ternary",
"expression : expression POTENTIAL expression",
"expression : expression DOUBLEEQUALS expression",
"expression : expression '>' expression",
"expression : expression GEQ expression",
"expression : expression '<' expression",
"expression : expression LEQ expression",
"expression : expression DIFFERENT expression",
"expression : expression AND expression",
"expression : expression OR expression",
"expression : '-' expression",
"expression : '!' expression",
"expression : function_invocation",
"expression : '(' expression ')'",
"expression : expression '.' expression",
"expression : cast",
"expression : array",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"increment :",
"increment : expression INC",
"increment : expression DEC",
"increment : expression PLUSEQUALS expression",
"increment : expression MINUSEQUALS expression",
"increment : expression TIMESEQUALS expression",
"increment : expression DIVIDEEQUALS expression",
"if_else : IF '(' expression ')' body ELSE body",
"if_else : IF '(' expression ')' body",
"ternary : expression '?' expression ':' expression",
"body : '{' listOfStatements '}'",
"body : statement",
"while : WHILE '(' expression ')' body",
"do_while : DO body WHILE '(' expression ')'",
"for : FOR '(' statement expression ';' increment ')' body",
"switch : SWITCH '(' expression ')' '{' switch_body '}'",
"switch_body : listOfCases",
"switch_body : listOfCases default_case",
"default_case : DEFAULT ':' listOfStatements break",
"default_case : DEFAULT ':' listOfStatements",
"listOfCases : listOfCases case",
"listOfCases : case",
"case : CASE expression ':' listOfStatements break",
"case : CASE expression ':' listOfStatements",
"break : BREAK ';'",
"cast : '(' built_in_type ')' expression",
"listOfExpressions : listOfExpressions ',' expression",
"listOfExpressions : expression",
"listOfIds : listOfIds ',' ID",
"listOfIds : ID",
"built_in_type : CHAR",
"built_in_type : INT",
"built_in_type : DOUBLE",
"array : expression '[' expression ']'",
"array_creation : array_creation '[' INT_CONSTANT ']'",
"array_creation : built_in_type '[' INT_CONSTANT ']'",
"array_creation : struct '[' INT_CONSTANT ']'",
"variable : built_in_type listOfIds ';'",
"variable : array_creation listOfIds ';'",
"variable : struct listOfIds ';'",
"listOfVariables : listOfVariables variable",
"listOfVariables :",
"assignment : expression '=' expression",
"read : READ listOfExpressions",
"write : WRITE listOfExpressions",
"struct : STRUCT '{' listOfVariables '}'",
"return : RETURN expression",
};

//#line 399 "../../src/parser/parser.y"

// * Code
//   Members of the generated Parser class
// We must implement at least:
// - int yylex()
// - void yyerror(String)

// * Lexical analyzer

private Scanner scanner;
private ASTNode ast;

public ASTNode getAST(){
	return this.ast;
}

// * Invocation to the scanner
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex();
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Syntax error handler
public void yyerror (String error) {
    System.err.println ("Syntax error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor
public Parser(Scanner scanner) {
	this.scanner= scanner;
	//this.yydebug = true;
}
//#line 781 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 51 "../../src/parser/parser.y"
{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)val_peek(0)); }
break;
case 2:
//#line 54 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>)val_peek(1);
														defList.add((FunctionDefinition) val_peek(0));
	   													yyval = defList; }
break;
case 3:
//#line 59 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														for(Definition def : (List<Definition>)val_peek(0)){
															defList.add(def);
														}
														yyval = defList; }
break;
case 4:
//#line 64 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														defList.add((Definition)val_peek(0));
														yyval = defList; }
break;
case 5:
//#line 67 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 69 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 7:
//#line 70 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 8:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) val_peek(1) , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(3))); }
break;
case 9:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(2))); }
break;
case 10:
//#line 82 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(6),
																			  		  (List<VariableDefinition>) val_peek(4), (Type) val_peek(7))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 11:
//#line 87 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(5),
																			  		  new ArrayList<VariableDefinition>(), (Type) val_peek(6))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 96 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(6),
																			  (List<VariableDefinition>) val_peek(4), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 13:
//#line 101 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 14:
//#line 108 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 15:
//#line 115 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<>();
																  	for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(1)){
																  		body.add(varDef);
																  }
																  for(Statement stm : (List<Statement>) val_peek(0)){
																  	body.add(stm);
																  }
																  yyval = body; }
break;
case 16:
//#line 125 "../../src/parser/parser.y"
{ List<Statement> list = (List<Statement>) val_peek(1);
																  list.add((Statement) val_peek(0));
																  yyval = list; }
break;
case 17:
//#line 128 "../../src/parser/parser.y"
{ yyval = new ArrayList<>(); }
break;
case 18:
//#line 131 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(2);
																  	list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 19:
//#line 134 "../../src/parser/parser.y"
{ List<VariableDefinition> list = new ArrayList<>();
																  list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 20:
//#line 139 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 21:
//#line 143 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 22:
//#line 144 "../../src/parser/parser.y"
{ yyval = (Read) val_peek(1); }
break;
case 23:
//#line 145 "../../src/parser/parser.y"
{ yyval = (Assignment) val_peek(1); }
break;
case 24:
//#line 146 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 25:
//#line 147 "../../src/parser/parser.y"
{ yyval = (IfStatement) val_peek(0); }
break;
case 26:
//#line 148 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 27:
//#line 149 "../../src/parser/parser.y"
{ yyval = (ForStatement) val_peek(0); }
break;
case 28:
//#line 150 "../../src/parser/parser.y"
{ yyval = (SwitchCase) val_peek(0); }
break;
case 29:
//#line 151 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 30:
//#line 152 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 31:
//#line 155 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 32:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 33:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 34:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 35:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 36:
//#line 165 "../../src/parser/parser.y"
{ yyval = (Assignment)val_peek(0); }
break;
case 37:
//#line 167 "../../src/parser/parser.y"
{ yyval = (Ternary) val_peek(0); }
break;
case 38:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Power(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 39:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 40:
//#line 173 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 41:
//#line 175 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 42:
//#line 177 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 43:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 44:
//#line 181 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 45:
//#line 183 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 46:
//#line 185 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 47:
//#line 187 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 48:
//#line 189 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 49:
//#line 191 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 50:
//#line 192 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 51:
//#line 193 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 52:
//#line 195 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 53:
//#line 196 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 54:
//#line 197 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 55:
//#line 199 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 56:
//#line 201 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 57:
//#line 203 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 59:
//#line 207 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 60:
//#line 211 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 61:
//#line 215 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0))); }
break;
case 62:
//#line 218 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0))); }
break;
case 63:
//#line 221 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0))); }
break;
case 64:
//#line 224 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0))); }
break;
case 65:
//#line 229 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 66:
//#line 232 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 67:
//#line 237 "../../src/parser/parser.y"
{ yyval = new Ternary(scanner.getLine(), scanner.getColumn()
																		,(Expression) val_peek(4), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 68:
//#line 240 "../../src/parser/parser.y"
{ yyval = (List<Statement>)val_peek(1); }
break;
case 69:
//#line 241 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list; }
break;
case 70:
//#line 246 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 71:
//#line 251 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 72:
//#line 256 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Assignment) val_peek(2), body); }
break;
case 73:
//#line 261 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  yyval = new SwitchCase(scanner.getLine(), scanner.getColumn()
																	  		, (Expression)val_peek(4), cases); }
break;
case 74:
//#line 266 "../../src/parser/parser.y"
{ yyval = (List<Case>)val_peek(0); }
break;
case 75:
//#line 267 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
		   															  cases.add((Case)val_peek(0));
		   															  yyval = cases; }
break;
case 76:
//#line 272 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, stms); }
break;
case 77:
//#line 276 "../../src/parser/parser.y"
{ yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, (List<Statement>)val_peek(0)); }
break;
case 78:
//#line 280 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 79:
//#line 283 "../../src/parser/parser.y"
{ List<Case> cases = new ArrayList<>();
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 80:
//#line 288 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(3), stms); }
break;
case 81:
//#line 292 "../../src/parser/parser.y"
{ yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 82:
//#line 296 "../../src/parser/parser.y"
{ yyval = new BreakInstruction(scanner.getLine(), scanner.getColumn()); }
break;
case 83:
//#line 299 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 84:
//#line 303 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 85:
//#line 306 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 86:
//#line 311 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 87:
//#line 318 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 88:
//#line 323 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 89:
//#line 324 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 90:
//#line 325 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 91:
//#line 328 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 92:
//#line 333 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 93:
//#line 336 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 94:
//#line 339 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 95:
//#line 344 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 96:
//#line 349 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 97:
//#line 354 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 98:
//#line 361 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(1);
															for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(0)){
																if(list.contains(varDef))
																	new ErrorType(scanner.getLine(), scanner.getColumn()
																			, "The variable "+varDef.getName()
																			+" has been already defined");
															  	list.add(varDef);
															}
															yyval = list; }
break;
case 99:
//#line 370 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 100:
//#line 373 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 101:
//#line 377 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 102:
//#line 380 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 103:
//#line 383 "../../src/parser/parser.y"
{ List<RecordField> list = new ArrayList<>();
													RecordField rec = null;
													for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(1)){
													  	rec = new RecordField(scanner.getLine(), scanner.getColumn()
													  		, varDef.getType(), varDef.getName());
													  	list.add(rec);
													}
													yyval = new Struct(scanner.getLine(), scanner.getColumn()
														  , list); }
break;
case 104:
//#line 394 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1504 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
