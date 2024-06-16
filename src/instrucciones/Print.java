/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;
import excepciones.ErrorS;

/**
 *
 * @author TeFxiu
 */
public class Print extends Instruccion {
    private Instruccion expresion;

    public Print(Instruccion expresion, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var resultado = expresion.interpretar(arbol, tabla);
        if (resultado instanceof ErrorS){
            return resultado;
        }
        arbol.Print(resultado.toString());
        return null;
    }
    
    
    
    
    
}
