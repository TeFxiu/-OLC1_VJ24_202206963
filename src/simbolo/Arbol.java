/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import abstracto.Instruccion;
import java.util.LinkedList;
import excepciones.ErrorS;
import instrucciones.Metodo;

/**
 *
 * @author TeFxiu
 */
public class Arbol {
    public static LinkedList<TablaSimbolos> global;
    private LinkedList<Instruccion> instrucciones;
    private String consola;
    private TablaSimbolos tablaGlobal;
    private LinkedList<ErrorS> errores;
    private LinkedList<Instruccion> funciones;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.consola = "";
        this.tablaGlobal = new TablaSimbolos();
        this.errores = new LinkedList<>();
        this.global = new LinkedList<>();
        this.funciones = new LinkedList<>();
    }

    public LinkedList<Instruccion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Instruccion> funciones) {
        this.funciones = funciones;
    }

    
    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public TablaSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public void setTablaGlobal(TablaSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public LinkedList<ErrorS> getErrores() {
        return errores;
    }

    public void setErrores(LinkedList<ErrorS> errores) {
        this.errores = errores;
    }
    
    public void Print(String valor){
        consola += valor + "\n";
    }
    
    public void addFunciones(Instruccion funcion){
        this.funciones.add(funcion);
    }
    
    public Instruccion getFuncion(String id){
        for(var i : funciones){
            if (i instanceof Metodo metodo){
                if (metodo.id.equalsIgnoreCase(id)){
                    return i;
                }
            }
        }
        return null;
    }
    
}
