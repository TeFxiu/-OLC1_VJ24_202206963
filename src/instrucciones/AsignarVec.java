/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import expresiones.TipoMutable;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class AsignarVec extends Instruccion{
    public String id;
    public Instruccion indice;
    public Instruccion valorNuevo;

    public AsignarVec(String id, Instruccion indice, Instruccion valorNuevo, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
        this.valorNuevo = valorNuevo;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(id);
        if (valor == null){
            return new ErrorS("Semantica","La variable no existe", this.linea, this.columna);
        }
        if(valor.getMutabilidad() != TipoMutable.CONST){
            var vector = (LinkedList) valor.getValor();
            var indice = this.indice.interpretar(arbol, tabla);
            if (indice instanceof ErrorS){
                return indice;
            }
            if (this.indice.tipo.getTipo() != TipoDato.ENTERO){
                return new ErrorS("Semantico", "El indice debe ser un entero", this.linea, this.columna);
            }
            int i = (int) indice;
            if (i > valor.indiceMax || i<0){
                return new ErrorS("Semantico", "El indice no existe", this.linea, this.columna);
            }
            var valorN = valorNuevo.interpretar(arbol, tabla);
            
            if (valorN instanceof ErrorS)
            {
            return valorN;
            }

            if (valorNuevo.tipo.getTipo() != valor.getTipo().getTipo()){
                return new ErrorS("Semantico", "El valor no puede ser asignado porque son de diferente tipo", this.linea, this.columna);
            }
            vector.set(i, valorNuevo);
            valor.setValor(vector);
        }else{
            return new ErrorS("SEMANTICA", "No se puede modificar el valor del vector porque es const", this.linea,this.columna);
        }
        return null;
    }
    
    
    
    
}
