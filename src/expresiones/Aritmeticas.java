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
public class Aritmeticas extends Instruccion{
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    public Aritmeticas( Instruccion operandoUnico,OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.ENTERO), linea, columna);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzquierda = null, opDerecha = null, unico = null;
        if (this.operandoUnico != null){
            unico = this.operandoUnico.interpretar(arbol, tabla);
            if (unico instanceof ErrorS){
                return unico;
            }
        }else{
            opIzquierda = this.operando1.interpretar(arbol, tabla);
            if (opIzquierda instanceof ErrorS){
                return opIzquierda;
            }
            opDerecha = this.operando2.interpretar(arbol, tabla);
            if (opDerecha instanceof ErrorS){
                return opDerecha;
            }
        }
        
        return switch (operacion){
            case OperadoresAritmeticos.SUMA -> 
                this.suma(opIzquierda, opDerecha);
            case OperadoresAritmeticos.RESTA -> 
                this.resta(opIzquierda, opDerecha);
            case OperadoresAritmeticos.MULTIPLICACION ->
                this.multiplicacion(opIzquierda, opDerecha);
            case OperadoresAritmeticos.DIVISION -> 
                this.division(opIzquierda, opDerecha);
            case OperadoresAritmeticos.NEGACION -> 
                this.negacion(unico);
            case OperadoresAritmeticos.POTENCIA -> 
                this.potencia(opIzquierda, opDerecha);
            case OperadoresAritmeticos.MODULO ->
                this.modulo(opIzquierda, opDerecha);
            default -> 
                new ErrorS("SEMANTICO", "Operador Aritmetico no definido", this.linea, this.columna);
        };
    }
    
    public Object suma(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }case TipoDato.DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int)op1+ (int)((char)op2);
                    }case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default ->{
                        return new ErrorS("Semantico", "Suma Erronea (1)", this.linea,this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch(tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double) op1 + (int) op2;
                    }case TipoDato.DECIMAL->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double) op1 + (double) op2;
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.DECIMAL);    
                        return (double) op1 + (int)((char)op2);
                    }case TipoDato.CADENA->{
                    this.tipo.setTipo(TipoDato.CADENA);
                    return op1.toString() +  op2.toString();
                    }default ->{
                        return new ErrorS("Semantico", "Suma Erronea (2)", this.linea,this.columna);
                    }
                }
            }case TipoDato.BOOL ->{
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString(); 
                    }default -> {
                        return new ErrorS("Semantico", "Suma Erronea(3)", this.linea,this.columna);
                    }
                }
            }case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }case TipoDato.DECIMAL->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }case TipoDato.CADENA->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }case TipoDato.BOOL->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString()+"" + (char)op2;
                    }default -> {
                        return new ErrorS("Semantico", "Suma Erronea(4)", this.linea,this.columna);
                    }
                
                }
            }case TipoDato.CARACTER -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int)((char)op1) + (int)op2;
                    }case TipoDato.DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int)((char)op1)+(double)op2;
                    }case TipoDato.CADENA->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return (char)op1 + ""+op2.toString();
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.CADENA);
                        return (char)op1+"" + (char)op2 ;
                    }default -> {
                        return new ErrorS("Semantico", "Suma Erronea(5)", this.linea,this.columna);
                    }
                
                }
            }
            default ->{
                        return new ErrorS("Semantico", "Suma Erronea(6)", this.linea,this.columna);
                    }
        }
    }
    public Object negacion(Object op1){
        var opU = this.operandoUnico.tipo.getTipo();
        switch(opU){
            case TipoDato.ENTERO->{
                this.tipo.setTipo(TipoDato.ENTERO);
                return (int) op1*-1;
            }case TipoDato.DECIMAL->{
                this.tipo.setTipo(TipoDato.DECIMAL);
                return (double) op1*-1;
            }default ->{
                return new ErrorS("Semantico", "Estos valores no se pueden multiplicar por -1", this.linea,this.columna);
            }
        }
    }
    public Object resta(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO->{
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.ENTERO);
                    return (int) op1 - (int)op2;
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 - (double)op2; 
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int)op1- (int)((char)op2);
                    }
                    default ->{
                        return new ErrorS("Semantico", "Resta Erronea(1)", this.linea,this.columna);
                    }
                }   
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double) op1 - (int)op2;
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (double)op2; 
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)op1- (int)((char)op2);
                        
                    }
                    default ->{
                        return new ErrorS("Semantico", "Resta Erronea(2)", this.linea,this.columna);
                    }
                } 
            
            }case TipoDato.CARACTER->{
                switch (tipo2){
                    case TipoDato.ENTERO->{    
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int)((char)op1) - (int)op2;
                    }case TipoDato.DECIMAL->{    
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int)((char)op1) - (double)op2;
                    }default ->{
                        return new ErrorS("Semantico", "Resta Erronea(3)", this.linea,this.columna);
                    }
                }
            }default ->{
                return new ErrorS("Semantico", "Suma Erronea(4)", this.linea,this.columna);
            }
        }
    }
    public Object multiplicacion(Object op1, Object op2){
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO->{
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.ENTERO);
                    return (int) op1 * (int)op2;
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 * (double)op2; 
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        var resultado = (int) op1 * (int)((char)op2);
                        return (int) resultado;
                    }
                    default ->{
                        return new ErrorS("Semantico", "Multiplicacion Erronea(1)", this.linea,this.columna);
                    }
                }   
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double) op1 * (int)op2;
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (double)op2; 
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (int)((char)op2);
                    }
                    default ->{
                        return new ErrorS("Semantico", "Multiplicacion Erronea(2)", this.linea,this.columna);
                    }
                } 
            
            }case TipoDato.CARACTER->{
                switch (tipo2){
                    case TipoDato.ENTERO->{    
                        this.tipo.setTipo(TipoDato.ENTERO);
                        var resultado = (int)((char)op1) * (int)op2;
                        return (int)resultado;
                    }case TipoDato.DECIMAL->{    
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int)((char)op1) * (double)op2;
                    }default ->{
                        return new ErrorS("Semantico", "Multiplicacion Erronea(3)", this.linea,this.columna);
                    }
                }
            }default ->{
                return new ErrorS("Semantico", "Multiplicacion Erronea(4)", this.linea,this.columna);
            }
        }
    }
    public Object division(Object op1, Object op2){
        try{
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO->{
                switch (tipo2){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int) op1 / (int)op2);
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 / (double)op2;
                    }case TipoDato.CARACTER->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                         return (double)((int) op1 / (int)((char)op2));
                    }
                    default ->{
                        return new ErrorS("Semantico", "Division Erronea(1)", this.linea,this.columna);
                    }
                }   
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 / (int)op2;
                    }case TipoDato.DECIMAL -> {
                        
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 / (double)op2;
                
                    }
                    case TipoDato.CARACTER->{
                            this.tipo.setTipo(TipoDato.DECIMAL);
                            return (double) op1 / (int)((char)op2);
                    }default ->{
                        return new ErrorS("Semantico", "Division Erronea(2)", this.linea,this.columna);
                    }
                }
            }case TipoDato.CARACTER->{
                switch (tipo2){
                    case TipoDato.ENTERO->{    
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double)((int)((char)op1) / (int)op2);
                    }case TipoDato.DECIMAL->{    
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int)((char)op1) / (double)op2;
                    }default ->{
                        return new ErrorS("Semantico", "Division Erronea(3)", this.linea,this.columna);
                    }
                }
            }default ->{
                        return new ErrorS("Semantico", "Division Erronea(4)", this.linea,this.columna);
                    }
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ErrorS("Semantico", "No pudo realizar ninguna operacion en la division", this.linea,this.columna);
        
    }
    public Object potencia(Object op1, Object op2){
        try{
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO->{
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.ENTERO);
                    return (int) (Math.pow((double)((int) op1) ,(double)(int) op2));
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)(int) op1 ,(double) op2); 
                    }
                    default ->{
                        return new ErrorS("Semantico", "Potencia Erronea(1)", this.linea,this.columna);
                    }
                }   
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return Math.pow((double) op1 ,(double)(int) op2);
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double) op1 ,(double) op2);
                    }
                    default ->{
                        return new ErrorS("Semantico", "Potencia Erronea(2)", this.linea,this.columna);
                    }
                } 
            
            }default ->{
                        return new ErrorS("Semantico", "Potencia Erronea(3)", this.linea,this.columna);
                    }
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ErrorS("Semantico", "No pudo realizar ninguna operacion en la potencia", this.linea,this.columna);
        
    }
    public Object modulo(Object op1, Object op2){
        try{
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        switch (tipo1){
            case TipoDato.ENTERO->{
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double)((int) op1 % (int) op2);
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 % (double) op2; 
                    }
                    default ->{
                        return new ErrorS("Semantico", "Modulo Erroneo(1)", this.linea,this.columna);
                    }
                }   
            }case TipoDato.DECIMAL -> {
                switch (tipo2){
                    case TipoDato.ENTERO->{
                    this.tipo.setTipo(TipoDato.DECIMAL);
                    return (double)((double) op1 % (int) op2);
                    }case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 % (double) op2;
                    }
                    default ->{
                        return new ErrorS("Semantico", "Modulo Erroneo(2)", this.linea,this.columna);
                    }
                } 
            
            }default ->{
                        return new ErrorS("Semantico", "Modulo Erroneo(3)", this.linea,this.columna);
                    }
        }
        }catch(Exception e){
            System.out.println(e);
        }
        return new ErrorS("Semantico", "No pudo realizar ninguna operacion en el modulo", this.linea,this.columna);
    }
}
