package com.jpokemon;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pokemon {
    private String nome;
    private Tipo tipo1;
    private Tipo tipo2;
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
    private String spriteMini;
    private int lvlEvoluzione;
    private int salute; // la salute verrà aggiornata durante gli attacchi, ps è invece il valore totale
    private String nomeEvoluzione;

    public Pokemon(String nome, Tipo tipo1,Tipo tipo2, int lvlEvoluzione,String nomeEvoluzione, int ps, int esp,
                   int attacco, int difesa, int attaccoSpeciale, int difesaSpeciale, int velocita){ // devo trovare il modo di mettere le mosse
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;

        this.lvlEvoluzione = lvlEvoluzione;
        this.nomeEvoluzione = nomeEvoluzione;
        this.ps = ps;   // queste ultime due poi ci penso io
        this.salute = ps;
        this.esp = esp;
        this.attacco = attacco;
        this.difesa = difesa;
        this.attaccoSpeciale = attaccoSpeciale;
        this.difesaSpeciale = difesaSpeciale;
        this.velocita = velocita;
        this.spriteFront = "img/front/"+nome.toLowerCase() + "-front.gif";
        this.spriteBack = "img/retro/"+nome.toLowerCase() + "-retro.gif";
        this.spriteMini = "img/mini/"+nome.toLowerCase() + "-mini.gif";
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public Tipo getTipo1() {
        return tipo1;
    }
    public Tipo getTipo2() {
        return tipo2;
    }

    public void setMosse(Mossa[] mosse){
        this.mosse = mosse;
    }
    public Mossa[] getMosse(){
        return mosse;
    }
    public String mossaString(){
        String s = "";
        for(int i = 0; i < mosse.length; i++) {
            if(mosse[i] != null){
                s += mosse[i].getNome() + " ";
            }else{
                s += "null ";
            }

        }
        return s;
    }

// qui definisco i pokemon con questo formato (nome-tipo1-tipo2-mosse[4]) - livello evoluzione - nomeEvoluzione - ps - esp - attacco - difesa - speciale - velocita

    public int getPs() {
        return ps;
    } // valore totale salute, non viene alterato

    public void setTipo1(Tipo tipo) {
        this.tipo1 = tipo;
    }
    public void setTipo2(Tipo tipo) {
        this.tipo2 = tipo;
    }

    public void setLvl(int lvl) {this.lvl = lvl;}
    public int getLvl(){return lvl;}

    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setDifesa(int difesa) {this.difesa = difesa;}
    public int getDifesa(){return difesa;}

    public void setAttacco(int attacco){this.attacco = attacco;}
    public int getAttacco(){return attacco;}

    public void setVelocita(int velocita){this.velocita = velocita;}
    public int getVelocita(){return velocita;}

    public String getSpriteFront() {
        return spriteFront;
    }

    public String getSpriteBack() {
        return spriteBack;
    }

    public String getSpriteMini() {
        return spriteMini;
    }

    public int getSalute(){
        return salute;
    }

    public void evolvi() throws FileNotFoundException {
        Reader lettore = new Reader();
        try {
            Pokemon evoluzione = lettore.buildPokemonByString(lettore.getRigaByIndex("testo/pokemon.txt",lettore.cercaRiga(this.nomeEvoluzione)));
            this.copia(evoluzione);
        } catch (IOException e) {
            System.err.println("File non formattato correttamente ");
        }
    } // TODO: da implementare, potrebbere restituire un oggetto pokemon o semplicemente modificare il .this che chiama
    public void cambiaMossa(){} //TODO: da implementare, ad ogni livello sblocca una mossa e la sostituisce con un'altra (nel caso in cui gia gli slot siano pieni)

    public int attacca(Pokemon avversario, Mossa mossa){
        int bruttoColpo = Formule.bruttoColpo(this.velocita);

        int danno = Formule.danno(this.tipo1,this.tipo2,avversario.getTipo1(),avversario.getTipo2(),mossa.getTipo(),bruttoColpo,this.lvl,mossa.getPotenza(),this.attacco,avversario.getDifesa());

        avversario.salute-=danno;
        mossa.setPP((mossa.getPP())-1);

        return danno;
    }


    //mossa.getTipo();
    // ogni bottone è specifico per una mossa e
    // richiama il metodo fornendo l'avversario
    // e la mossa (presa dall'array) che verrà eseguita
    public void copia(Pokemon evoluzione){ //copia dopo evoluzione
        this.nome = evoluzione.nome;
        this.ps = evoluzione.ps;
        this.esp = evoluzione.esp;
        this.attacco = evoluzione.attacco;
        this.difesa = evoluzione.difesa;
        this.mosse = evoluzione.mosse;
        this.attaccoSpeciale = evoluzione.attaccoSpeciale;
        this.difesaSpeciale = evoluzione.difesaSpeciale;
        this.velocita = evoluzione.velocita;
        this.spriteFront = evoluzione.spriteFront;
        this.spriteBack = evoluzione.spriteBack;
        this.spriteMini = evoluzione.spriteMini;
        this.nomeEvoluzione = evoluzione.nomeEvoluzione;
        this.lvlEvoluzione = evoluzione.lvlEvoluzione;
        this.tipo1 = evoluzione.tipo1;
        this.tipo2 = evoluzione.tipo2;






    }

}

