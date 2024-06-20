package com.jpokemon;

public class Pokemon {
    private String nome;
    private String tipo1;
    private String tipo2;
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

    private String spriteFront;
    private String spriteBack;

    public Pokemon(String nome, String tipo1,String tipo2, int lvl, int ps, int esp){
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.lvl = lvl;
        this.ps = ps;   // queste ultime due poi ci penso io
        this. esp = esp;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTipo1() {
        return tipo1;
    }
    public String getTipo2() {
        return tipo2;
    }

    public int getLvl() {
        return lvl;
    }

    public int getPs() {
        return ps;
    }

    public void setTipo1(String tipo) {
        this.tipo1 = tipo;
    }
    public void setTipo2(String tipo) {
        this.tipo2 = tipo;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
}
