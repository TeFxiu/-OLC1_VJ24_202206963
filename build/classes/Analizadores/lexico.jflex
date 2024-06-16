package Analizadores;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.ErrorS;

%%

//definicion de variables
%{
    public LinkedList<ErrorS> listaErrores =  new LinkedList<>();
%}

// Definiciones iniciales
%init{
    yyline = 1;
    yycolumn = 1;
%init}

//declaraciones de caracteristicas de jflex
%cup
%class scanner //nombre de la clase
%public //acceso de la clase
%line //conteo de lineas
%column //conteo de columnas
%char //conteo de caracteres
%full //reconocimiento de caracteres
%ignorecase //quitar la distincion entre mayusculas y minusculas (case insensitive)


//estados


// definir los simbolos del sistema
COMILLA_SIMPLE = [\']
PAR_A = "("
COMENTARIOS_LINEA= [/][/][^\n]*
COMENTARIOS_MULTILINEA = [/][*][^]*[*][/]
PAR_C = ")"
FINCADENA = ";"
MAS = "+"
MENOS = "-"
MULT = "*"
DIV = "/"
BLANCOS = [\ \r\t\n\f]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
CADENA = [\"](([\\][\"])|[\\][\\]|[^\"\n])*[\"]
CARACTER = [']([^\\\n']|\\.)[']
POTENCIA = "**"
MODULO = "%"
IGUAL = "="
INCREMENTO = "++"
DECREMENTO = "--"

OR = [|][|]
AND = [&][&]
XOR = [\^]
NOT = [!]

// palabras reservadas
IGUALACION = "=="
DIFERENCIA = "!="
MAYOR_IGUAL = ">="
MENOR_IGUAL = "<="
MENOR = "<"
MAYOR = ">"
IMPRIMIR = "println"
BOOL = "true" | "false"
INT = "int" 
DOUBLE = "double" 
CHAR = "char"
ID = [a-zA-Z][a-zA-Z0-9_]*
IF ="if"
ELSE = "else"
LLAVE_A="{"
LLAVE_C="}"
MATCH = "match"
FLECHA = "=>"
DEFAULT = "_"

FORC="for"
WHILEC = "while"
DO_C = "do"
FIN ="break"
CONTINUAR = "continue"

STRING = "string"
BOOLEAN = "bool"
VAR = "var"
CONST = "const"
DOS_PUNTOS = ":"


%%


<YYINITIAL> {COMENTARIOS_MULTILINEA} {}
<YYINITIAL> {COMENTARIOS_LINEA} {}
<YYINITIAL> {VAR}                       {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> {CONTINUAR}                       {return new Symbol(sym.CONTINUAR, yyline, yycolumn,yytext());}
<YYINITIAL> {FIN}                       {return new Symbol(sym.FIN, yyline, yycolumn,yytext());}
<YYINITIAL> {DO_C}                       {return new Symbol(sym.DO_C, yyline, yycolumn,yytext());}
<YYINITIAL> {FORC}                       {return new Symbol(sym.FORC, yyline, yycolumn,yytext());}
<YYINITIAL> {WHILEC}                       {return new Symbol(sym.WHILEC, yyline, yycolumn,yytext());}
<YYINITIAL> {DOS_PUNTOS}                       {return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> {CONST}                     {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}
<YYINITIAL> {IF}                     {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {DEFAULT}                     {return new Symbol(sym.DEFAULT, yyline, yycolumn,yytext());}
<YYINITIAL> {MATCH}                     {return new Symbol(sym.MATCH, yyline, yycolumn,yytext());}
<YYINITIAL> {FLECHA}                     {return new Symbol(sym.FLECHA, yyline, yycolumn,yytext());}
<YYINITIAL> {ELSE}                     {return new Symbol(sym.ELSE, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE_A}                     {return new Symbol(sym.LLAVE_A, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE_C}                     {return new Symbol(sym.LLAVE_C, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR_IGUAL}               {return new Symbol(sym.MAYOR_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR_IGUAL}               {return new Symbol(sym.MENOR_IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {DIFERENCIA}                {return new Symbol(sym.DIFERENCIA, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUALACION}                {return new Symbol(sym.IGUALACION, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR}                     {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR}                     {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {NOT}                       {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}
<YYINITIAL> {XOR}                       {return new Symbol(sym.XOR, yyline, yycolumn,yytext());}
<YYINITIAL> {AND}                       {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> {OR}                        {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING}                    {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {DECREMENTO}                    {return new Symbol(sym.DECREMENTO, yyline, yycolumn,yytext());}
<YYINITIAL> {INCREMENTO}                    {return new Symbol(sym.INCREMENTO, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL}                {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {INT}                       {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE}                    {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {CHAR}                       {return new Symbol(sym.CHAR, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOLEAN}                      {return new Symbol(sym.BOOLEAN, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL}                      {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {IMPRIMIR}                  {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> {ID}                        {return new Symbol(sym.ID, yyline, yycolumn,yytext());}
<YYINITIAL> {CADENA}    {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    cadena = cadena.replace("\\n", "\n");
    cadena = cadena.replace("\\t","\t");
    cadena = cadena.replace("\\\\","\\");
    cadena = cadena.replace("\"","'"+"'");
    cadena = cadena.replace("\\'", "'");
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }
<YYINITIAL> {CARACTER}  {return new Symbol(sym.CARACTER, yyline, yycolumn,yytext());}
<YYINITIAL> {DECIMAL}   {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO}    {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {MAS}       {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS}     {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {POTENCIA}   {return new Symbol(sym.POTENCIA, yyline, yycolumn,yytext());}
<YYINITIAL> {MODULO}     {return new Symbol(sym.MODULO, yyline, yycolumn,yytext());}
<YYINITIAL> {MULT}      {return new Symbol(sym.MULT, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV}       {return new Symbol(sym.DIV, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR_A}      {return new Symbol(sym.PAR_A, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR_C}      {return new Symbol(sym.PAR_C, yyline, yycolumn,yytext());}
<YYINITIAL> {BLANCOS}   {}
<YYINITIAL> {COMILLA_SIMPLE} {return new Symbol(DESCARTE, yyline, yycolumn, yytext());}

<YYINITIAL> . {
        listaErrores.add(new ErrorS("Error lexico", "Caracter " + yytext() + "no pertenece al lenguaje", yyline, yycolumn));
}
// estado cadena