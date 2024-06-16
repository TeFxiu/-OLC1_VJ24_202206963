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
%debug

//estados


// definir los simbolos del sistema
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
IMPRIMIR = "imprimir"
BOOL = "true" | "false"

%%


<YYINITIAL> {COMENTARIOS_MULTILINEA} {}
<YYINITIAL> {COMENTARIOS_LINEA} {}
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
<YYINITIAL> {BOOL}                      {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {IMPRIMIR}                  {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> {CADENA}    {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
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

<YYINITIAL> . {
        listaErrores.add(new ErrorS("Error lexico", "Caracter " + yytext() + "no pertenece al lenguaje", yyline, yycolumn));
}
// estado cadena