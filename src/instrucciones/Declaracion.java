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
public class Declaracion extends Instruccion{
    public boolean conValor;
    public TipoMutable mutabilidad;
    public String identificador;
    public Instruccion valor;

    public Declaracion(boolean conValor, TipoMutable mutabilidad, String identiificador, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.conValor = conValor;
        this.mutabilidad = mutabilidad;
        this.identificador = identiificador;
    }

    public Declaracion(boolean conValor, TipoMutable mutabilidad, String identiificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.conValor = conValor;
        this.mutabilidad = mutabilidad;
        this.identificador = identiificador;
        this.valor = valor;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        if (conValor){
            var valorInterpretado = this.valor.interpretar(arbol, tabla);
            if (valorInterpretado instanceof ErrorS){
            return valorInterpretado;
             }
       
            if(this.valor.tipo.getTipo() != this.tipo.getTipo()){
                return new ErrorS("Semantico", "Asignacion no compatible entre valores", this.linea, this.columna);
            } 
           Simbolo declaracion = new Simbolo(this.tipo, mutabilidad, identificador ,valorInterpretado, this.linea, this.columna);
           tabla.setVariable(declaracion);
           return null;
       }else{
            Simbolo declaracion = new Simbolo(this.tipo, mutabilidad, identificador, this.linea, this.columna);
            declaracion.valoresIniciales();
            tabla.setVariable(declaracion);
            return null;
       }
    }
    
    
}
