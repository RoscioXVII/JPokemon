package com.jpokemon;

public class Pokemon {
    private String nome;
    private String tipo;
    private int lvl;

    private int esp; // esperienza per l'aumento del livello
    private Mossa[] mosse = new Mossa[4];
    //statistiche base
    private int ps;
    private int attacco;
    private int difesa;
    private int attaccoSpeciale;
    private int difesaSpeciale;
    private int velocita;
    private int sprite;


    public Pokemon(String nome, String tipo, int lvl, int ps, int esp){
        this.nome = nome;
        this.tipo = tipo;
        this.lvl = lvl;
        this.ps = ps;
        this. esp = esp;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getLvl() {
        return lvl;
    }

    public int getPs() {
        return ps;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
}
