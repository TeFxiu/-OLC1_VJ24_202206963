/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;

/**
 *
 * @author TeFxiu
 */
public class Metodo extends Instruccion{
    public String id;
    public LinkedList<Instruccion> instrucciones;

    public Metodo(String id, LinkedList<Instruccion> instrucciones, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.id = id;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        for (var i : this.instrucciones){
            var resultado = i.interpretar(arbol, tabla);
            if (resultado instanceof ErrorS){
                return resultado;
            }
            
        }
        return null;
    }
    
    
}
