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

    public Vector(TipoMutable mutabilidad, String identificador, Object vector2d, TipoEDD edd, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.mutabilidad = mutabilidad;
        this.identificador = identificador;
        if (vector2d instanceof LinkedList){
            if (TipoEDD.VECTOR2D == edd){
                this.vector2d = (LinkedList<LinkedList>)vector2d;
            }
            else if (TipoEDD.VECTOR == edd){
                var lista = (LinkedList<LinkedList>)vector2d;
                if (lista.size() == 1){
                    var vals = lista.getFirst();
                    this.valores = (LinkedList<Instruccion>)vals;
                }
            }
        }
        this.edd = edd;
    }
    
    public Object comprobarLista(){
        if (valores == null && edd == TipoEDD.VECTOR){
            return new ErrorS("Semantico", "La estructura de la declaracion no es correcta para una estructura de datos", this.linea, this.columna);
        }else if (vector2d == null && edd == TipoEDD.VECTOR2D){
            return new ErrorS("Semantico", "La estructura de la declaracion no es correcta para una estructura de datos", this.linea, this.columna);
        }
        return null;
    }
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = comprobarLista();
        if (valor instanceof ErrorS){
            return valor;
        }
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
            declaracion.setIndiceMax(indice);
            tabla.setVariable(declaracion);
        }else{
            var primero = this.interpretar(arbol, tabla, vector2d.getLast());
            if (primero instanceof ErrorS){
                return primero;
            }
            indice++;
            vector2d.set(vector2d.size()-indice, (LinkedList<Instruccion>)primero);
            for(var i: vector2d){
                if (indice == vector2d.size()){
                    continue;
                }
                var lista = this.interpretar(arbol, tabla, i, subIndice);
                if (lista instanceof ErrorS){
                    return lista;
                }
                indice++;
                vector2d.set(vector2d.size()-indice, (LinkedList<Instruccion>)lista);
            }
            vector2d = vector2d.reversed();
            Simbolo declaracion = new Simbolo(edd,this.tipo, mutabilidad, identificador ,vector2d, this.linea, this.columna, indice);
            declaracion.setIndiceMax(indice);
            declaracion.setSubMax(subIndice);
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
        }else{
            return new ErrorS("Semantico", "Los vectores no tienen las mismas dimensiones", this.linea, this.columna);
        }
        instru = instru.reversed();
        return instru;
    }
    
    
}
