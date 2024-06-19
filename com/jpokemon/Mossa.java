package com.jpokemon;

public class Mossa {
    private String nome;
    private String tipo; // i tipi sono speciale, fisico, stato
    private int danno;
    private int PP; // limite utilizzo mossa --> ad ogni utilizzo decrementa
    public Mossa(String nome, String tipo, int danno, int pp){
        this.nome = nome;
        this.tipo = tipo;
        this.danno = danno;
        this.PP = pp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDanno() {
        return danno;
    }

    public void setDanno(int danno) {
        this.danno = danno;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }
}
