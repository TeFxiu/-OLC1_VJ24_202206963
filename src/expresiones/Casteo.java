/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Casteo extends Instruccion{
    TipoDato casteador;
    Instruccion operacion;

    public Casteo(TipoDato casteador, Instruccion operacion, int linea, int columna) {
        super(new Tipo(TipoDato.DECIMAL), linea, columna);
        this.casteador = casteador;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object op1 = null;
        op1 = operacion.interpretar(arbol, tabla);
       
        if (op1 instanceof ErrorS){
            return op1;
        }
        
        var tipoCasteando = operacion.tipo.getTipo();
        
        switch(casteador){
            case TipoDato.ENTERO ->{
                switch(tipoCasteando){
                    case TipoDato.DECIMAL ->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int)((double)op1);
                    }case TipoDato.CARACTER->{
                            this.tipo.setTipo(TipoDato.ENTERO);
                            int retorno = (int)((char)op1);
                            return retorno;
                    }default -> {
                        return new ErrorS("SEMANTICO", "Tipos no compatibles para el casteo(1)", this.linea, this.columna);
                    }
                }
            }case TipoDato.DECIMAL -> {
                switch (tipoCasteando){
                    case TipoDato.ENTERO ->{
                        return (double)((int)op1);
                    }case TipoDato.CARACTER->{
                            int retorno = (int)((char)op1);
                            return (double)retorno;
                    }default -> {
                        return new ErrorS("SEMANTICO", "Tipos no compatibles para el casteo(2)", this.linea, this.columna);
                    }
                }
            }case TipoDato.CARACTER->{
                this.tipo.setTipo(TipoDato.CARACTER);
                if (tipoCasteando == TipoDato.ENTERO){
                            this.tipo.setTipo(TipoDato.CARACTER);
                            return (char)((int)op1);
                }else{
                    return new ErrorS("SEMANTICO", "Tipos no compatibles para el casteo(3)", this.linea, this.columna);
                }
            }default -> {
                return new ErrorS("SEMANTICO", "Tipos no compatibles para el casteo(4)", this.linea, this.columna);
            }
        }
        
    }
    
    
    
    
}
