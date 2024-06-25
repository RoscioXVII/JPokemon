package com.jpokemon;

public class Mossa {
    private String nome;
    private Tipo tipo; // i tipi sono speciale, fisico, stato
    private int potenza;

    private int precisione;
    private TipoMossa tipoMossa;//FISICO SPECIALE STATO
    private int PP; // limite utilizzo mossa --> ad ogni utilizzo decrementa

    public Mossa(String nome, Tipo tipo,TipoMossa tipoMossa, int potenza,int precisione, int pp){
        this.nome = nome;
        this.tipo = tipo;
        this.tipoMossa = tipoMossa;
        this.potenza = potenza;
        this.precisione = precisione;
        this.PP = pp;
    }
    public TipoMossa getTipoMossa(){
        return tipoMossa;
    }

    public void setTipoMossa(TipoMossa tipoMossa){this.tipoMossa = tipoMossa;}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getPotenza() {
        return potenza;
    }

    public void setPotenza(int potenza) {
        this.potenza = potenza;
    }

    public int getPrecisione() {return precisione;}

    public void setPrecisione(int precisione) {this.precisione = precisione;}

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

}
