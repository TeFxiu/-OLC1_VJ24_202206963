/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import expresiones.TipoMutable;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class AsignacionVar extends Instruccion{
    private String id;
    private Instruccion exp;
    private String id2;

    public AsignacionVar(String id, Instruccion exp, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.exp = exp;
    }

    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var variable = (Simbolo) tabla.getVariable(id);
        if (variable ==  null){
            return new ErrorS("Semantico", "Variable inexistente", this.linea, this.columna);
        }
        
        var newValor = this.exp.interpretar(arbol, tabla);
        if (newValor instanceof ErrorS){
            return newValor;
        }
        
        if (variable.getTipo().getTipo() != this.exp.tipo.getTipo()){
            return new ErrorS("Semantico", "La asignacion no pudo completarse ya que los tipos no son compatibles", this.linea, this.columna);
        }
     
        if (variable.getMutabilidad() == TipoMutable.VAR){
            variable.setValor(newValor);
            return null;
        }else{
            return new ErrorS("Semantico", "Una constante no puede ser modificada", this.linea, this.columna);
        }
    }
}
