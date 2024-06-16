/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;
import excepciones.ErrorS;

/**
 *
 * @author TeFxiu
 */
public class Relacionales extends Instruccion {
    public Instruccion operacion1;
    public Instruccion operacion2;
    public OperadoresRelacionales operacion;

    public Relacionales(Instruccion operacion1, Instruccion operacion2, OperadoresRelacionales operacion, int linea, int columna) {
        super(new Tipo(TipoDato.BOOL), linea, columna);
        this.operacion1 = operacion1;
        this.operacion2 = operacion2;
        this.operacion = operacion;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzquierda = null, opDerecha = null;
        opIzquierda = operacion1.interpretar(arbol, tabla);
        opDerecha = operacion2.interpretar(arbol, tabla);
        
        if (opIzquierda instanceof ErrorS){
            return opIzquierda;
        }
        if (opDerecha instanceof ErrorS){
            return opDerecha;
        }
        
        return switch(operacion){
            case OperadoresRelacionales.IGUALACION ->
                this.igualacion(opIzquierda,opDerecha);
            case OperadoresRelacionales.DIFERENCIA -> 
                this.diferencia(opIzquierda, opDerecha);
            case OperadoresRelacionales.MENOR ->
                this.menor(opIzquierda, opDerecha);
            case OperadoresRelacionales.MAYOR -> 
                this.mayor(opIzquierda, opDerecha);
            case OperadoresRelacionales.MENOR_IGUAL -> 
                this.menor_igual(opIzquierda, opDerecha);
            case OperadoresRelacionales.MAYOR_IGUAL ->
                this.mayor_igual(opIzquierda, opDerecha);
            default -> 
                new excepciones.ErrorS("SEMANTICO", "Operador Relacional no definido", this.linea, this.columna);
        };
        
    }
    
    public Object igualacion(Object op1, Object op2){
        var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 == (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 == (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 == (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 == valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 == (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 == (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 == (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 == valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es igual a "+op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        return (boolean)op1 == (boolean) op2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Boolean no es igual a "+ op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return resultOp1.equals(resultOp2);
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Cadena no es igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 == (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 == (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 == (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 == valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es igual "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }default -> {
                return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles para igualacion", this.linea, this.columna);
            }
        }
        
    }
    public Object diferencia(Object op1, Object op2){
        var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 != (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 != (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 != (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 != valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es diferente a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 != (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 != (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 != (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 != valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es diferente a "+op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        return (boolean)op1 != (boolean) op2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Boolean no es diferente a "+ op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return !resultOp1.equals(resultOp2);
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Cadena no es diferente a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 != (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                       if(operacion2 instanceof AccesoVar){
                            return (int) op1 != (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 != valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es difetene a "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }default -> {
                return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles para diferenciacion", this.linea, this.columna);
            }
        }
        
    }
    public Object menor(Object op1, Object op2){
        var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 < (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 < (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 < (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 < valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es menor a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 < (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 < (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 < (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 < valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es menor a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        int retorno1;
                        int retorno2;
                        boolean resultado1 = (boolean) op1;
                        boolean resultado2 = (boolean) op2;
                        if (resultado1 == true){
                            retorno1 = 1;
                        }else{
                            retorno1 = 0;
                        }
                        if (resultado2 == true){
                            retorno2 = 1;
                        }else{
                            retorno2 = 0;
                        }
                        return retorno1 < retorno2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Booleano no es menor a" + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 < (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 < (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 < (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 < valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es menor a "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return resultOp1.length()<resultOp2.length();
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "String no es menor que " + op2.toString(), this.linea, this.columna);
                    }
                }
            }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles(1)", this.linea, this.columna);
                    }
        }
        
    }
    public Object mayor(Object op1, Object op2){
       var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 > (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 > (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 > (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 > valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es mayor a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 > (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 > (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 > (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 > valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es mayor a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        int retorno1;
                        int retorno2;
                        boolean resultado1 = (boolean) op1;
                        boolean resultado2 = (boolean) op2;
                        if (resultado1 == true){
                            retorno1 = 1;
                        }else{
                            retorno1 = 0;
                        }
                        if (resultado2 == true){
                            retorno2 = 1;
                        }else{
                            retorno2 = 0;
                        }
                        return retorno1 > retorno2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Booleano no es mayor a" + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 > (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 > (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 > (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 > valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es mayor a "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return resultOp1.length() > resultOp2.length();
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "String no es mayor que " + op2.toString(), this.linea, this.columna);
                    }
                }
            }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles(2)", this.linea, this.columna);
                    }
        }
        
    }
    public Object menor_igual(Object op1, Object op2){
        var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 <= (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 <= (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 <= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 <= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es menor o igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 <= (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 <= (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 <= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 <= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es menor o igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        int retorno1;
                        int retorno2;
                        boolean resultado1 = (boolean) op1;
                        boolean resultado2 = (boolean) op2;
                        if (resultado1 == true){
                            retorno1 = 1;
                        }else{
                            retorno1 = 0;
                        }
                        if (resultado2 == true){
                            retorno2 = 1;
                        }else{
                            retorno2 = 0;
                        }
                        return retorno1 <= retorno2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Booleano no es menor o igual a" + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 <= (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 <= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 <= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 <= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es menor o igual a "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return resultOp1.length() <= resultOp2.length();
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "String no es menor o igual que " + op2.toString(), this.linea, this.columna);
                    }
                }
            }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles(3)", this.linea, this.columna);
                    }
        }
        
    }
    public Object mayor_igual(Object op1, Object op2){
        var tipo1 = operacion1.tipo.getTipo();
        var tipo2 = operacion2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO ->{
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (int)op1 >= (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (int)op1 >= (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 >= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 >= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Entero no es mayor o igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return (double)op1 >= (int) op2;
                    }
                    case TipoDato.DECIMAL->{
                        return (double)op1 >= (double)op2;
                    }case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (double) op1 >= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (double)op1 >= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Decimal no es mayor o igual a " + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.BOOL -> {
                switch (tipo2){
                    case TipoDato.BOOL ->{
                        int retorno1;
                        int retorno2;
                        boolean resultado1 = (boolean) op1;
                        boolean resultado2 = (boolean) op2;
                        if (resultado1 == true){
                            retorno1 = 1;
                        }else{
                            retorno1 = 0;
                        }
                        if (resultado2 == true){
                            retorno2 = 1;
                        }else{
                            retorno2 = 0;
                        }
                        return retorno1 >= retorno2;
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Booleano no es mayor o igual a" + op2.toString(), this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER -> {
                int valor1;
                if(operacion1 instanceof AccesoVar){
                    valor1 = (int)((char)op1);
                }else{
                Nativo op_1 = (Nativo)this.operacion1;
                valor1 = op_1.convertirChar();
                }
                switch (tipo2){
                    case TipoDato.ENTERO ->{
                        return valor1 >= (int) op2;
                    }case TipoDato.DECIMAL -> {
                        return valor1 >= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        if(operacion2 instanceof AccesoVar){
                            return (int) op1 >= (int)((char)op2);
                        }else{
                        Nativo op_2 = (Nativo)this.operacion2;
                        int valor2 = op_2.convertirChar();
                        return (int)op1 >= valor2;
                        }
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Caracter no es mayor o igual a "+op2.toString(), this.linea, this.columna);
                    }
                }       
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA ->{
                        String resultOp1 = (String) op1;
                        String resultOp2 = (String) op2;
                        return resultOp1.length() >= resultOp2.length();
                    }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "String no es mayor o igual que " + op2.toString(), this.linea, this.columna);
                    }
                }
            }default -> {
                        return new excepciones.ErrorS("SEMANTICO", "Datos no compatibles(4)", this.linea, this.columna);
                    }
        }
        
    }
}
