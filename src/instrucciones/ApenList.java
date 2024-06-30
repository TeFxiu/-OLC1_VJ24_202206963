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
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class ApenList extends Instruccion  {
    public String id;
    public Instruccion expresion;

    public ApenList(String id, Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var simbol = tabla.getVariable(id);
        if (simbol == null){
            return new ErrorS("Semantico", "La variable no existe", this.linea, this.columna);
        }
        var result = expresion.interpretar(arbol, tabla);
        if (result instanceof ErrorS){
            return result;
        }
        
        if (simbol.getTipo().getTipo() == expresion.tipo.getTipo()){
        var lista = (LinkedList) simbol.getValor();
        lista.add(expresion);
        simbol.setValor(lista);
        simbol.indiceMax++;
        }else{
            return new ErrorS("Semantico", "Los datos no son compatibles", this.linea, this.columna);
        }
        return null;
    }
    
    
}
