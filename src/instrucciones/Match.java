/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import expresiones.Caso;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.RetornInter;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Match extends Instruccion{
    public Instruccion evaluando;
    public LinkedList<Caso> caso;

    public Match(Instruccion evaluando, LinkedList<Caso> caso, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.evaluando = evaluando;
        this.caso = caso;
    }    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var eva = evaluando.interpretar(arbol, tabla);
        if (eva instanceof ErrorS){
            return eva;
        }
        for (Caso i : caso){
                if (i.tipo.getTipo() == TipoDato.CASO){
                    var result = i.interpretar(arbol, tabla);
                    if ( result instanceof ErrorS){
                        return result;
                    }
                    return null;
                }
                var evalua = i.expresion.interpretar(arbol, tabla);
                if (evalua instanceof Error ){
                    return evaluando;
                }
                if (i.expresion.tipo.getTipo() != evaluando.tipo.getTipo()){
                    return new ErrorS("Semantico", "Tipos no compatibles", this.linea, this.columna);
                }
                if (eva.equals(evalua)){
                    var actual = i.interpretar(arbol, tabla);
                    if (actual instanceof RetornInter){
                    return actual;
                    }
                    if (actual instanceof ErrorS|| actual instanceof Fin){
                        return actual;
                    }
                    return null;
            }
            
        }
        
        return null;
    }

    
    
    
}
