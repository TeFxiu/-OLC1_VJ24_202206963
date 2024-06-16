/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class AccesoVar extends Instruccion{
    private String id;
    public boolean cara;

    public AccesoVar(String id, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.cara = false;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(id);
        
        if (valor == null){
            return new ErrorS("Semantica","La variable no existe", this.linea, this.columna);
        }
        
        this.tipo.setTipo(valor.getTipo().getTipo());
        cara =true;
        return valor.getValor();
    }
    
    
}
