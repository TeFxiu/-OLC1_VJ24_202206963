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
public class Dow extends Instruccion {
    public LinkedList<Instruccion> instrucciones;
    public Instruccion condicion;

    public Dow(LinkedList<Instruccion> instrucciones, Instruccion condicion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.instrucciones = instrucciones;
        this.condicion = condicion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var newTabla = new TablaSimbolos(tabla);
        newTabla.setNombre("Do-While");
        do{
            for(var i: instrucciones){
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
            var resultado = condicion.interpretar(arbol, tabla);
            if (resultado instanceof ErrorS){
                return resultado;
            }
   
            if(condicion.tipo.getTipo() != TipoDato.BOOL){
                return new ErrorS("SEMANTICO", "La condicion debe de ser un boolena", this.linea, this.columna);
            }
            
        }while((boolean) condicion.interpretar(arbol, tabla));
        global.add(newTabla);
        return null;
    }
    
    
    
    
}
