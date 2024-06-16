/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Nativo extends Instruccion{
    public Object valor;
    
    public Nativo(Object valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if(this.tipo.getTipo() == TipoDato.CARACTER){
            return (char)this.convertirChar();
        }
        return valor;
    }
    
    public int convertirChar(){
        String cadena =(String) valor;
        if (cadena.length()>3){
            char retorno = cadena.charAt(2);
            switch(retorno){
                case 'n'-> {
                    return 10;
                }case 't' -> {
                    return 9;
                }case 'r'->{
                    return 13;
                }case '\'' ->{
                    return 39;
                }case '\\'->{
                    return 92;
                }case '\"'->{
                    return 34;
                }default ->{
                    return -1;
                }
            }
        }else{
            char retorno = cadena.charAt(1);
            return (int) retorno;
        }
    }
    
}
