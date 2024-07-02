package com.jpokemon;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

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
    public void scrittore() throws IOException {
        // crea i file da testo quando non sono gia presenti
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testo/utenti.txt",true))){
            writer.write(toString());
            writer.newLine();

        } catch (IOException e){
            System.err.println("Errore nella scrittura nel file");
            throw e;
        }

        // file writer deve essere messo a true senno sovrascrive alla volta dopo

        //FORMATO FILE .TXT:
        //nome:vittorie:sconfitte:partiteGiocate

        //FORMATO FILE .TXT SQUADREUTENTI:
        //nomeUtente:pokemon:statistichePokemon:pokemon2:statistichePokemon
    }
    public String getNome(){
        return nome;
    }
    public void incrementaVittorie(){
        vittorie++;
    }
    public void incrementaSconfitte(){
        sconfitte++;
    }
    public void partiteGiocate(){
        partiteGiocate++;
    }
    public Pokemon[] getSquadra(){
        return squadra;
    }

    @Override
    public String toString() {
        return nome +
                ":" + vittorie + ':' + sconfitte +
                ":" + sconfitte +
                ":" + partiteGiocate;
    }


}
