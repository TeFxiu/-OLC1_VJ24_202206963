/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Fin extends Instruccion {

    public boolean estado;
    
    public Fin( int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.estado = false;
    }

    public Fin(boolean estado, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.estado = estado;
    }
    
    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        return this;
    }
    
    
    
}
