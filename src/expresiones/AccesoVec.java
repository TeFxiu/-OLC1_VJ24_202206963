/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

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
public class AccesoVec extends Instruccion {
    public String id;
    public Instruccion buscarIndice;

    public AccesoVec(String identificador, Instruccion buscarIndice, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = identificador;
        this.buscarIndice = buscarIndice;
    }

    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(id);
        if (valor == null){
            return new ErrorS("Semantica","La variable no existe", this.linea, this.columna);
        }
        
        var vector = (LinkedList) valor.getValor();
        var indice = this.buscarIndice.interpretar(arbol, tabla);
        if (indice instanceof ErrorS){
            return indice;
        }
        if (this.buscarIndice.tipo.getTipo() != TipoDato.ENTERO){
            return new ErrorS("Semantico", "El indice debe ser un entero", this.linea, this.columna);
        }
        int i = (int) indice;
        if (i > valor.indiceMax || i<0){
            return new ErrorS("Semantico", "El indice no existe", this.linea, this.columna);
        }
        var result = (Instruccion) vector.get(i);
        var retorno = result.interpretar(arbol, tabla);
        this.tipo.setTipo(result.tipo.getTipo());
        return retorno;
        
    }
    
}
