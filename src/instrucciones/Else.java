/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
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
public class Else extends Instruccion{
    private LinkedList<Instruccion> instruccion;

    public Else(LinkedList<Instruccion> instruccion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instruccion = instruccion;
    }

    

    public LinkedList<Instruccion> getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(LinkedList<Instruccion> instruccion) {
        this.instruccion = instruccion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var newTabla =  new TablaSimbolos(tabla);
        newTabla.setNombre("Else");
            for (var i: this.instruccion){
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Fin){
                    Fin aux = (Fin)resultado;
                    if(aux.isEstado() == true){
                        return resultado;
                    }
                    
                    return resultado;
                }
        }
        global.add(newTabla);
        return null;
    }
    
}
