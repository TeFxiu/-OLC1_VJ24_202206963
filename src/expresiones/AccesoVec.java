/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.ErrorS;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;
import simbolo.TipoEDD;

/**
 *
 * @author TeFxiu
 */
public class AccesoVec extends Instruccion {
    public String id;
    public Instruccion buscarIndice;
    public Instruccion indice2;

    public AccesoVec(String identificador, Instruccion buscarIndice, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = identificador;
        this.buscarIndice = buscarIndice;
    }

    public AccesoVec(String id, Instruccion buscarIndice, Instruccion indice2, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.buscarIndice = buscarIndice;
        this.indice2 = indice2;
    }

    
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var valor = tabla.getVariable(id);
        if (valor == null){
            return new ErrorS("Semantica","La variable no existe", this.linea, this.columna);
        }
        var vector = (LinkedList) valor.getValor();
        var indice = this.buscarIndice.interpretar(arbol, tabla);
            if (indice instanceof ErrorS){
                return indice;
            }
       if (this.buscarIndice.tipo.getTipo() != TipoDato.ENTERO){
                return new ErrorS("Semantico", "El indice debe ser un entero", this.linea, this.columna);
            }
                   int i = (int) indice;
            if (i > valor.indiceMax || i<0){
                return new ErrorS("Semantico", "El indice no existe", this.linea, this.columna);
            }
        if (valor.getEdd() == TipoEDD.VECTOR || valor.getEdd() == TipoEDD.LISTA){
            var result = (Instruccion) vector.get(i);
            var retorno = result.interpretar(arbol, tabla);
            this.tipo.setTipo(result.tipo.getTipo());
            return retorno;
        }else if (valor.getEdd() == TipoEDD.VECTOR2D){
            if (indice2 != null){
                var indice2 = this.indice2.interpretar(arbol, tabla);
                if (indice2 instanceof ErrorS){
                    return indice2;
                }
                int i2 = (int) indice2;
                if (i2 > valor.subMax || i<0){
                    return new ErrorS("Semantico", "El indice no existe", this.linea, this.columna);
                }
                var lista = (LinkedList)vector.get(i);
                var result = (Instruccion)lista.get(i2);
                var retorno = result.interpretar(arbol, tabla);
                this.tipo.setTipo(result.tipo.getTipo());
                return retorno;
            }else{
                var result = (LinkedList<Instruccion>) vector.get(i);
                LinkedList<Object> list = new LinkedList<>();
                for (var is : result){
                    var retorno = is.interpretar(arbol, tabla);
                    list.add(retorno);
                }
                return list;
               
            }
        }
        return null;
    }
    
}
