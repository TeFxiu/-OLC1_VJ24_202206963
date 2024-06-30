/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import java.util.HashMap;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Struct extends Instruccion {
    public HashMap<String, String> atributos;
    public String id;

    public Struct(HashMap<String, String> atributos, String id, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.atributos = atributos;
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        
        return null;
    }
    
    
    
    
    
    
    
}
