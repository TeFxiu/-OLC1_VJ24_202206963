/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import java.util.HashMap;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.RetornInter;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Metodo extends Instruccion{
    public String id;
    public LinkedList<HashMap> parametros;
    public LinkedList<Instruccion> instrucciones;

    public Metodo(String id, LinkedList<HashMap> parametros, LinkedList<Instruccion> instrucciones,Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (this.tipo.getTipo() != TipoDato.VOID){
            for (var i : this.instrucciones){
                var resultado = i.interpretar(arbol, tabla);
                if (resultado instanceof ErrorS){
                    return resultado;
                }
                if (resultado instanceof RetornInter){
                    var dato = (RetornInter) resultado;
                    if (dato.tipo.getTipo() != this.tipo.getTipo()){
                        return new ErrorS("Semantico", "El valor retornado no coincide con el tipo de la funcion", this.linea, this.columna);
                    }
                    return dato.valor;
                }
            }
            return new ErrorS("Semantico", "La funcion debe de tener un retorno", this.linea, this.columna);
        }else{ 
            for (var i : this.instrucciones)
            {if (i == null) {
                    continue;
                }
                var resultado = i.interpretar(arbol, tabla);

                if (resultado instanceof ErrorS){
                    return resultado;
                }
                if (resultado instanceof RetornInter){
                    var dato = (RetornInter) resultado;
                    if (dato.tipo.getTipo() != this.tipo.getTipo()){
                        return new ErrorS("Semantico", "El valor retornado no coincide con el metodo", this.linea, this.columna);
                    }
                    return dato.valor;
                }
            }
        }
        return null;
    }
    
    
}
