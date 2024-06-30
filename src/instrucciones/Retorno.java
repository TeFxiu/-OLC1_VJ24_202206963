/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import simbolo.Arbol;
import simbolo.RetornInter;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Retorno extends Instruccion {
    public Instruccion expresion;

    public Retorno(Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (expresion != null){
            var ret = expresion.interpretar(arbol, tabla);
            if (ret instanceof ErrorS){
                return ret;
            }
        RetornInter retorno = new RetornInter(expresion.tipo, ret);
        return retorno;
        }else{
            RetornInter retorno = new RetornInter(new Tipo(TipoDato.VOID), null);
            return retorno;
        }
    }

    
}
