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
public class ForC extends Instruccion{
    public Instruccion acceso;
    public Instruccion condicion;
    public Instruccion actualizacion;
    public LinkedList<Instruccion> instrucciones;

    public ForC(Instruccion acceso, Instruccion condicion, Instruccion actualizacion, LinkedList instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.acceso = acceso;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var newTabla =  new TablaSimbolos(tabla);
        newTabla.setNombre("For");
        
        var res1 = this.acceso.interpretar(arbol, newTabla);
        
        if (res1 instanceof ErrorS){
            return res1;
        }
        
        var cond = this.condicion.interpretar(arbol, newTabla);
        
        if (cond instanceof ErrorS){
            return cond;
        }
        
        if (this.condicion.tipo.getTipo() != TipoDato.BOOL){
            return new ErrorS("Semantico", "La condicion debe ser un boolean", this.linea, this.columna);
        }
        while((boolean) this.condicion.interpretar(arbol, newTabla)){

            for(var i : this.instrucciones){
                var result = i.interpretar(arbol, newTabla);
                if (result instanceof RetornInter){
                    return result;
                }
                if (result instanceof Fin  ){
                    Fin aux = (Fin)result;
                    if(aux.isEstado() == true){
                        break;
                    }
                    return null;
                }   
            }
            var act = this.actualizacion.interpretar(arbol, newTabla);
            if (act instanceof ErrorS){
                return act;
            }
        }
        global.add(newTabla);
        return null;
    }
    
    
    
    
}
