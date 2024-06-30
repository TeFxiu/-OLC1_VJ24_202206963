/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import java.util.Collection;
import java.util.LinkedList;
import simbolo.Arbol;
import static simbolo.Arbol.global;
import simbolo.RetornInter;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class WhileC extends Instruccion{
    public Instruccion condicion;
    public LinkedList<Instruccion> instrucciones;

    public WhileC(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var condicional = condicion.interpretar(arbol, tabla);
        if (condicional instanceof ErrorS){
            return condicional;
        }
        if (condicion.tipo.getTipo() != TipoDato.BOOL){
            return new ErrorS("SEMANTICO", "La condicion debe de ser un boolean", this.linea, this.columna);
        }
        
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre("While");
        
        while ((boolean) condicion.interpretar(arbol, tabla) ){
            for (var i: instrucciones){
                var result = i.interpretar(arbol, newTabla);
                if (result instanceof RetornInter){
                    return result;
                }
                if (result instanceof Fin){
                    Fin aux = (Fin)result;
                    if(aux.isEstado() == true){
                        break;
                    }
                    return null;
                }
            }
        }
        global.add(newTabla);
        return null;
    }
    
    
    
    
}
