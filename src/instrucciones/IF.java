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
import simbolo.RetornInter;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class IF extends Instruccion{
    private Instruccion condicion;
    private LinkedList<Instruccion> instruccion;
    private IF elseif;
    private Else elseC;

    public IF(Instruccion condicion, LinkedList<Instruccion> instruccion, IF elseif, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccion = instruccion;
        this.elseif = elseif;
        this.elseC = null;
    }

    public IF(Instruccion condicion, LinkedList<Instruccion> instruccion, Else elseC, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccion = instruccion;
        this.elseC = elseC;
        this.elseif = null;
    }
    
    

    public IF(Instruccion condicion, LinkedList<Instruccion> instruccion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID) , linea, columna);
        this.condicion = condicion;
        this.instruccion = instruccion;
        this.elseif = null;
        this.elseC = null;
    }
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof ErrorS){
            return cond;
        }
        if (condicion.tipo.getTipo() != TipoDato.BOOL){
            return new ErrorS("Semantico", "No se puede evaluar la condicion para el if", this.linea, this.columna);
        }
        var newTabla =  new TablaSimbolos(tabla);
        newTabla.setNombre("If");
        if ((boolean)cond){
            for (var i: this.instruccion){
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof RetornInter){
                    return resultado;
                }
                if (resultado instanceof Fin){
                    Fin aux = (Fin)resultado;
                    if(aux.isEstado() == true){
                        return resultado;
                    }
                    
                    return resultado;
                }
            }
        }else{
            if (elseif != null){
                var resultado = elseif.interpretar(arbol, tabla);
                if (resultado instanceof RetornInter){
                    return resultado;
                }
                if (resultado instanceof Fin){
                    Fin aux = (Fin)resultado;
                    if(aux.isEstado() == true){
                        return resultado;
                    }
                    
                    return resultado;
                }
            }else if (elseC != null){
                var resultado = elseC.interpretar(arbol, tabla);
                if (resultado instanceof RetornInter){
                    return resultado;
                }
                if (resultado instanceof Fin){
                    Fin aux = (Fin)resultado;
                    if(aux.isEstado() == true){
                        return resultado;
                    }
                    
                    return resultado;
                }
            }
        }
        global.add(newTabla);
        return null;
    }
    
    
}
