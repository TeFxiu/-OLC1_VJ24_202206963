/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import instrucciones.Fin;
import java.util.LinkedList;
import simbolo.Arbol;
import static simbolo.Arbol.global;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Caso extends Instruccion{
    public Instruccion expresion;
    public LinkedList<Instruccion> instrucciones;

    public Caso(Instruccion expresion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
        this.instrucciones = instrucciones;
    }

    public Caso(LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.CASO), linea, columna);
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre("Caso-Match");
        for(var result : instrucciones){
            var def = result.interpretar(arbol, newTabla);
            if (def instanceof Fin  ){
                    Fin aux = (Fin)result;
                    if(aux.isEstado() == true){
                        break;
                    }
                    return null;
                }   
        }
         global.add(newTabla);
        return this;
    }
    
    
    
    
}
