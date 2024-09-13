package com.jpokemon;

/**
 * Mossa appartenente al pokemon che l'utente puo selezionare durante la lotta
 */
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

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getPotenza() {
        return potenza;
    }

    public int getPrecisione() {return precisione;}

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }


}
