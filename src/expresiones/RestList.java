/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class RestList extends Instruccion {
    public String id;
    public Instruccion expresion;

    public RestList(String id, Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var simbol = (Simbolo)tabla.getVariable(id);
        if (simbol == null){
            return new ErrorS("Semantico", "No existe la variablees",this.linea, this.columna );
        }
        
        var indice = expresion.interpretar(arbol, tabla);
        if (indice instanceof ErrorS){
            return indice;
        }
        if (expresion.tipo.getTipo() != TipoDato.ENTERO){
            return new ErrorS("Semantico", "El indice debe de ser un entero", this.linea, this.columna);
        }
        
        var i = (int) indice;
        if (i>=simbol.indiceMax || i<0){
            return new ErrorS("Semantico", "El indice esta fuera de rango", this.linea, this.columna);
        }
        
        var list = (LinkedList)simbol.getValor();
        var retorno =(Instruccion) list.remove(i);
        simbol.setValor(list);
        simbol.indiceMax--;
        var interpretado = retorno.interpretar(arbol, tabla);
        if (interpretado instanceof ErrorS){
            return interpretado;
        }
        this.tipo.setTipo(retorno.tipo.getTipo());
        return interpretado;
        
    }
    
    
}
