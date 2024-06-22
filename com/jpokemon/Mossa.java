package com.jpokemon;

public class Mossa {
    private String nome;
    private Tipo tipo; // i tipi sono speciale, fisico, stato
    private int danno;
    private int precisione;
    private int PP; // limite utilizzo mossa --> ad ogni utilizzo decrementa

    public Mossa(String nome, Tipo tipo, int danno,int precisione, int pp){
        this.nome = nome;
        this.tipo = tipo;
        this.danno = danno;
        this.precisione = precisione;
        this.PP = pp;
    }

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

    public int getDanno() {
        return danno;
    }

    public void setDanno(int danno) {
        this.danno = danno;
    }

    public int getPrecisione() {return precisione;}

    public void setPrecisione(int precisione) {this.precisione = precisione;}

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public void ToString(){
        System.out.println("Nome: "+nome);
    }

}
