/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import expresiones.TipoMutable;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class OperacionUnitaria extends Instruccion{
    public String id;
    public boolean operacion;

    public OperacionUnitaria(String id, boolean operacion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var simbolo = (Simbolo)tabla.getVariable(id.toLowerCase());
        if (simbolo == null){
            return new ErrorS("Semantico", "No se encontro la variable", this.linea,this.columna);
        }
        if (operacion){
            if(simbolo.getMutabilidad() == TipoMutable.VAR){
                switch(simbolo.getTipo().getTipo()){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        int suma = (int)simbolo.getValor();
                        suma++;
                        simbolo.setValor(suma);
                        return suma;
                    }case TipoDato.DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        double suma = (double)simbolo.getValor();
                        suma++;
                        simbolo.setValor(suma);
                        return suma;
                    }default ->{
                        return new ErrorS("Semantico", "Este dato no puede ser incrementado", this.linea, this.columna);
                    }
                }
            }else{
                return new ErrorS("Semantico", "Una constante no pude ser modificada", this.linea, this.columna);
            }
        }else{
            if(simbolo.getMutabilidad() == TipoMutable.VAR){
                switch(simbolo.getTipo().getTipo()){
                    case TipoDato.ENTERO->{
                        this.tipo.setTipo(TipoDato.ENTERO);
                        int resta= (int)simbolo.getValor();
                        resta--;
                        simbolo.setValor(resta);
                        return resta;
                    }case TipoDato.DECIMAL->{
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        double resta = (double)simbolo.getValor();
                        resta--;
                        simbolo.setValor(resta);
                        return resta;
                    }default ->{
                        return new ErrorS("Semantico", "Este dato no puede ser decrementado", this.linea, this.columna);
                    }
                }
            }else{
                return new ErrorS("Semantico", "Este dato no puede ser decrementado", this.linea, this.columna);
            }
        }
    }
    
    
    
    
}
