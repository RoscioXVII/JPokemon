package com.jpokemon;

public class Utente {
    private String nome;
    private int vittorie;
    private int sconfitte;
    private int partiteGiocate;

    private Pokemon[] squadra = new Pokemon[6];
    public Utente(String nome){
        this.nome = nome;
        vittorie=0;
        sconfitte=0;
        partiteGiocate=0;
        // per la squadra chiamo una serie di metodi del reader random
    }
    // caso in cui l'utente non esiste, viene preso solo in input il nome
    // e la squadra viene random
    public Utente(String nome, int vittorie, int sconfitte, int partiteGiocate,Pokemon[] squadra){
        // questo è nel caso in cui ho una stringa nel file di testo
        this.nome = nome;
        this.vittorie = vittorie;
        this.sconfitte = sconfitte;
        this.partiteGiocate = partiteGiocate;
        this.squadra = squadra;
    }
    public void scrittore(){ // crea i file da testo quando non sono gia presenti

    }
    public String getNome(){
        return nome;
    }
}
