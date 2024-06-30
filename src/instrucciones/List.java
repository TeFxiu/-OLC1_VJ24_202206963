/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
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
public class List extends Instruccion{
    public String id;
    public LinkedList<Instruccion> lista;

    public List(String id, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.tipo = tipo;
        this.id = id;
        this.lista = new LinkedList();
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Simbolo declaracion = new Simbolo(this.tipo, id, lista, TipoEDD.LISTA,this.linea, this.columna);
        tabla.setVariable(declaracion);
        return null;
    }
    
    
    
}
