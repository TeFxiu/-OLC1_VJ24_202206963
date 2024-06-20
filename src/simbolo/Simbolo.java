/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simbolo;

import expresiones.TipoMutable;

/**
 *
 * @author TeFxiu
 */
public class Simbolo {
    private Tipo tipo;
    private TipoMutable mutabilidad;
    private String id;
    private Object valor;
    private TipoEDD edd;
    public int linea;
    public int columna;
    public int indiceMax;

    public Simbolo(Tipo tipo, TipoMutable mutabilidad, String id, int linea, int columna) {
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.linea = linea;
        this.columna = columna;
        this.edd = TipoEDD.NON;
    }

    public Simbolo(Tipo tipo, TipoMutable mutabilidad, String id, Object valor, int linea, int columna) {
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.edd = TipoEDD.NON;
    }

    public Simbolo(TipoEDD edd, Tipo tipo, TipoMutable mutabilidad, String id, Object valor, int linea, int columna, int indiceMax) {
        this.tipo = tipo;
        this.mutabilidad = mutabilidad;
        this.id = id;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
        this.indiceMax = indiceMax;
        this.edd = edd;
    }

    public int getIndiceMax() {
        return indiceMax;
    }

    public void setIndiceMax(int indiceMax) {
        this.indiceMax = indiceMax;
    }
    
    public void valoresIniciales(){
        switch(tipo.getTipo()){
            case TipoDato.ENTERO ->{
                this.setValor(0);
            }case TipoDato.BOOL->{
                this.setValor(true);
            }case TipoDato.CADENA->{
                this.setValor("");
            }case TipoDato.DECIMAL->{
                this.setValor(0.0);
            }case TipoDato.CARACTER->{
                this.setValor('0');
            }
        }
    }
    
    public TipoMutable getMutabilidad() {
        return mutabilidad;
    }

    public void setMutabilidad(TipoMutable mutabilidad) {
        this.mutabilidad = mutabilidad;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
