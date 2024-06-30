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
import simbolo.TipoEDD;

/**
 *
 * @author TeFxiu
 */
public class AsignarVec extends Instruccion{
    public String id;
    public Instruccion indice;
    public Instruccion indice2;
    public Instruccion valorNuevo;
    public LinkedList<Instruccion> array;

    public AsignarVec(String id, Instruccion indice, Instruccion indice2, Instruccion valorNuevo, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
        this.indice2 = indice2;
        this.valorNuevo = valorNuevo;
    }

    public AsignarVec(String id, Instruccion indice, Object array, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.indice = indice;
        if (array instanceof LinkedList){
            var list = (LinkedList<LinkedList>) array;
            if (list.size() == 1){
                this.array = (LinkedList<Instruccion>)list.getFirst();
        
            }
        }
    }
    
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
            if (valor.getEdd() == TipoEDD.VECTOR || valor.getEdd() == TipoEDD.LISTA){
                var vector = (LinkedList<Instruccion>) valor.getValor();
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
            }else if (valor.getEdd() == TipoEDD.VECTOR2D){
                var vector = (LinkedList<LinkedList>) valor.getValor();
                var indice = this.indice.interpretar(arbol, tabla);
                if (indice2 != null){
                    var indice2 = this.indice2.interpretar(arbol, tabla);
                    if (indice instanceof ErrorS|| indice2 instanceof ErrorS){
                        return indice;
                    }
                    if (this.indice.tipo.getTipo() != TipoDato.ENTERO){
                        return new ErrorS("Semantico", "El indice debe ser un entero", this.linea, this.columna);
                    }
                    if (this.indice2.tipo.getTipo() != TipoDato.ENTERO){
                        return new ErrorS("Semantico", "El indice debe ser un entero", this.linea, this.columna);
                    }
                    int i = (int) indice;
                    int i2 = (int) indice2;
                    if (i > valor.indiceMax || i<0){
                        return new ErrorS("Semantico", "El indice no existe", this.linea, this.columna);
                    }
                    if (i2 > valor.subMax || i2<0){
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
                    var cambiando = vector.get(i);
                    cambiando.set(i2, valorNuevo);
                    vector.set(i, cambiando);
                    valor.setValor(vector);
                    return null;
                    
                }else{
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
                    if (array.size() == valor.subMax){
                        for (var uno : array){
                            var resultado = uno.interpretar(arbol, tabla);
                            if (resultado instanceof ErrorS){
                                return resultado;
                            }
                            if (uno.tipo.getTipo() != valor.getTipo().getTipo()){
                                return new ErrorS("Semantico", "Los tipos no son compatibles", this.linea, this.columna);
                            }
                        }
                        array = array.reversed();
                        vector.set(i, array);
                    }else{
                        return new ErrorS("Semantico", "No son de las mismas dimensionees", this.linea, this.columna);
                    }
                }
            }
        }else{
            return new ErrorS("SEMANTICA", "No se puede modificar el valor del vector porque es const", this.linea,this.columna);
        }
        return null;
    }
    
    
    
    
}
