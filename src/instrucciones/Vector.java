/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import expresiones.TipoMutable;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoEDD;

/**
 *
 * @author TeFxiu
 */
public class Vector extends Instruccion{
    public TipoMutable mutabilidad;
    public String identificador;
    public LinkedList<Instruccion> valores;
    public LinkedList<LinkedList> vector2d;
    public int indice;
    public int subIndice;
    public TipoEDD edd;

    public Vector(TipoEDD edd,TipoMutable mutabilidad, String identificador, LinkedList<Instruccion> valores, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.valores = valores;
        this.edd = edd;
    }

    public Vector(TipoMutable mutabilidad, String identificador, LinkedList<LinkedList> vector2d, TipoEDD edd, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        this.vector2d = vector2d;
        this.edd = edd;
    }
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (TipoEDD.VECTOR == edd){
            for (var i: valores){
                var result = i.interpretar(arbol, tabla);
                if (result instanceof ErrorS){
                    return result;
                }
                if (i.tipo.getTipo() != this.tipo.getTipo()){
                    return new ErrorS("SEMANTICO", "El tipo de dato dentro del vector no es compatible con el vector", this.linea, this.columna);
                }
                indice++;
            }
            valores = valores.reversed();
            Simbolo declaracion = new Simbolo(edd,this.tipo, mutabilidad, identificador ,valores, this.linea, this.columna, indice);
            tabla.setVariable(declaracion);
        }else{
            var primero = this.interpretar(arbol, tabla, vector2d.getLast());
            if (primero instanceof ErrorS){
                return primero;
            }
            indice++;
            for(var i: vector2d){
                if (indice == vector2d.size()){
                    continue;
                }
                var lista = this.interpretar(arbol, tabla, i, subIndice);
                if (lista instanceof ErrorS){
                    return lista;
                }
                indice++;
            }
            vector2d = vector2d.reversed();
            Simbolo declaracion = new Simbolo(edd,this.tipo, mutabilidad, identificador ,vector2d, this.linea, this.columna, indice);
            tabla.setVariable(declaracion);
        }
        return null;
    }
    
    public Object interpretar(Arbol arbol, TablaSimbolos tabla, LinkedList<Instruccion> instru) {
            for (var i: instru){
                var result = i.interpretar(arbol, tabla);
                if (result instanceof ErrorS){
                    return result;
                }
                if (i.tipo.getTipo() != this.tipo.getTipo()){
                    return new ErrorS("SEMANTICO", "El tipo de dato dentro del vector no es compatible con el vector", this.linea, this.columna);
                }
                subIndice++;
            }
            instru = instru.reversed();
        return instru;
    }
    public Object interpretar(Arbol arbol, TablaSimbolos tabla, LinkedList<Instruccion> instru, int limite) {
        if (instru.size() == limite){    
        for (var i: instru){
                var result = i.interpretar(arbol, tabla);
                if (result instanceof ErrorS){
                    return result;
                }
                if (i.tipo.getTipo() != this.tipo.getTipo()){
                    return new ErrorS("SEMANTICO", "El tipo de dato dentro del vector no es compatible con el vector", this.linea, this.columna);
                }
            }
            instru = instru.reversed();
        }else{
            return new ErrorS("Semantico", "Los vectores no tienen las mismas dimensiones", this.linea, this.columna);
        }
        return instru;
    }
    
    
}
