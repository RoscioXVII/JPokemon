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
    private String spriteMini;
    private int lvlEvoluzione;
    private int salute; // la salute verrà aggiornata durante gli attacchi, ps è invece il valore totale
    private String nomeEvoluzione;

    public Pokemon(String nome, String tipo1,String tipo2, int lvlEvoluzione,String nomeEvoluzione, int ps, int esp,
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

    public String getTipo1() {
        return tipo1;
    }
    public String getTipo2() {
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



    public int getLvl() {
        return lvl;
    }

    public int getPs() {
        return ps;
    } // valore totale salute, non viene alterato

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

    public void Evolvi(){} // TODO: da implementare, potrebbere restituire un oggetto pokemon o semplicemente modificare il .this che chiama
    public void cambiaMossa(){} //TODO: da implementare, ad ogni livello sblocca una mossa e la sostituisce con un'altra (nel caso in cui gia gli slot siano pieni)
}
