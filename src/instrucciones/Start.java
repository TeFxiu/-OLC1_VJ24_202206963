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
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author TeFxiu
 */
public class Start extends Instruccion{
    public String id;
    public LinkedList<Instruccion> parametro;

    public Start(String id, LinkedList<Instruccion> parametro, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.parametro = parametro;
    }
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        TablaSimbolos newTabla = null; 
        var funcion = arbol.getFuncion(id);
        newTabla= new TablaSimbolos(arbol.getTablaGlobal());
        newTabla.setNombre("Tabla entorno General");
        if (funcion == null){
            return new ErrorS("Semantico", "Funcion no existe", this.linea, this.columna);   
        }
        if (funcion instanceof Metodo){
            var metodoMain = (Metodo) funcion;
            if (metodoMain.parametros.size() != parametro.size()){
                return new ErrorS("Semantico", "La cantidad de parametros no coincide con los parametros establecidos", this.linea, this.columna);
            }else if (!parametro.isEmpty()){
                for (int i =0; i<parametro.size(); i++){
                    var parametro = (Tipo) metodoMain.parametros.get(i).get("tipo");
                    var parametroEntrada = this.parametro.get(i);
                    var result = parametroEntrada.interpretar(arbol, tabla);
                    if (result instanceof ErrorS){
                        return result;
                    }
                    if (parametro.getTipo() != parametroEntrada.tipo.getTipo()){
                        return new ErrorS("Semantico", "El tipo de los parametros no coincide", this.linea, this.columna);
                    }
                    
                    var identificador = (String)metodoMain.parametros.get(i).get("id");
                    var declaracion = new Declaracion(true,TipoMutable.VAR,identificador, parametroEntrada, parametro, this.linea, this.columna);
                    if (declaracion.conValor){
                    var valorInterpretado = declaracion.valor.interpretar(arbol, tabla);
                    if (valorInterpretado instanceof ErrorS){
                    return valorInterpretado;
                    }
                    Simbolo declaracion2 = new Simbolo(declaracion.tipo, declaracion.mutabilidad, declaracion.identificador ,valorInterpretado, this.linea, this.columna);
                    newTabla.setVariable(declaracion2);
                    }
                } 
                var result2 = metodoMain.interpretar(arbol, newTabla);
                if (result2 instanceof ErrorS){
                    return result2;
                }
                this.tipo.setTipo(metodoMain.tipo.getTipo());
                return result2;
            }else{
                    var result2 = metodoMain.interpretar(arbol, newTabla);
                    if (result2 instanceof ErrorS){
                        return result2;
                    }
                    return result2;
            }
        }
            return null;
    }
}
