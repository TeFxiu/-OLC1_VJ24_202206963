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

/**
 *
 * @author TeFxiu
 */
public class Logicos extends Instruccion {
    public Instruccion operacion1;
    public Instruccion operacion2;
    public OperadoresLogicos operacion;
    public Instruccion unitario;

    public Logicos(Instruccion operacion1, Instruccion operacion2, OperadoresLogicos operacion, int linea, int columna) {
        super( new Tipo(TipoDato.BOOL), linea, columna);
        this.operacion1 = operacion1;
        this.operacion2 = operacion2;
        this.operacion = operacion;
    }

    public Logicos(OperadoresLogicos operacion, Instruccion unitario, int linea, int columna) {
        super(new Tipo(TipoDato.BOOL), linea, columna);
        this.operacion = operacion;
        this.unitario = unitario;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzquierda = null, opDerecha = null, unico = null;
        if (this.unitario != null){
            unico = this.unitario.interpretar(arbol, tabla);
            if (unico instanceof excepciones.ErrorS){
                return unico;
            }
        }else{
            opIzquierda = this.operacion1.interpretar(arbol, tabla);
            if (opIzquierda instanceof excepciones.ErrorS){
                return opIzquierda;
            }
            opDerecha = this.operacion2.interpretar(arbol, tabla);
            if (opDerecha instanceof excepciones.ErrorS){
                return opDerecha;
            }
        }
        
        return switch(operacion){
            case OperadoresLogicos.OR ->
                this.or(opIzquierda,opDerecha);
            case OperadoresLogicos.AND -> 
                this.and(opIzquierda, opDerecha);
            case OperadoresLogicos.XOR ->
                this.xor(opIzquierda, opDerecha);
            case OperadoresLogicos.NOT -> 
                this.not(unico);
            default -> 
                new excepciones.ErrorS("SEMANTICO", "Operador Logico no definido", this.linea, this.columna);
        };
    }
    
    public Object or(Object op1, Object op2){
        var tipo1 = this.operacion1.tipo.getTipo();
        var tipo2 = this.operacion2.tipo.getTipo();
        
        if (tipo1 == TipoDato.BOOL){
            if (tipo2 == TipoDato.BOOL){
                if (((boolean)op1 == false) && ((boolean)op2 == false)){
                    return false;
                }else{
                    return true;
                }
            }else{
                return new excepciones.ErrorS("SEMANTICO", "Tipos de datos no comparables(1)", this.linea, this.columna);
            }
        }else{
            return new excepciones.ErrorS("SEMANTICO", "OR no pudo validar las condiciones", this.linea, this.columna);
        }
        
    }
    public Object and(Object op1, Object op2){
        var tipo1 = this.operacion1.tipo.getTipo();
        var tipo2 = this.operacion2.tipo.getTipo();
        
        if (tipo1 == TipoDato.BOOL){
            if (tipo2 == TipoDato.BOOL){
                if (((boolean)op1 == true) && ((boolean)op2 == true)){
                    return true;
                }else{
                    return false;
                }
            }else{
                return new excepciones.ErrorS("SEMANTICO", "Tipos de datos no compatibles(2)", this.linea, this.columna);
            }
        }else{
            return new excepciones.ErrorS("SEMANTICO", "AND no pudo validar las condiciones", this.linea, this.columna);
        }
        
    }
    public Object xor(Object op1, Object op2){
        var tipo1 = this.operacion1.tipo.getTipo();
        var tipo2 = this.operacion2.tipo.getTipo();
        
        if (tipo1 == TipoDato.BOOL){
            if (tipo2 == TipoDato.BOOL){
                if ((boolean)op1 != (boolean)op2){
                    return true;
                }else{
                    return false;
                }
            }else{
                return new excepciones.ErrorS("SEMANTICO", "Tipos de datos no compatibles(3)", this.linea, this.columna);
            }
        }else{
            return new excepciones.ErrorS("SEMANTICO", "XOR no pudo validar las condiciones", this.linea, this.columna);
        }
        
    }
    public Object not(Object op1){
        var tipo1 = this.unitario.tipo.getTipo();
        if (tipo1 == TipoDato.BOOL){
            return !((boolean)op1);
        }else{
            return new excepciones.ErrorS("SEMANTICO", "Negacion invalida", this.linea, this.columna);
        }
        
    }
    
}
