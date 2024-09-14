package com.jpokemon;

import java.io.*;

/**
 * Descrive l'utente che affronterà la lotta
 * tramite la squadra che gli appartiene
 * e lo storico dei progressi ottenuti
 */
public class Utente {
    private String nome;
    private int vittorie;
    private int sconfitte;
    private int partiteGiocate;

    private Pokemon[] squadra;

    /**
     * Costruttore per un nuovo utente
     * @param nome
     * @throws IOException
     */
    public Utente(String nome) throws IOException {
        this.nome = nome;
        vittorie=0;
        sconfitte=0;
        partiteGiocate=0;
        Reader rd = Reader.getInstance();
        squadra = rd.generaSquadra();

    }
    // caso in cui l'utente non esiste, viene preso solo in input il nome
    // e la squadra viene generata random

    /**
     * Costruttore per un Utente già definito in memoria
     * che viene caricato dal file dei salvataggi
     * @param nome
     * @param vittorie
     * @param sconfitte
     * @param partiteGiocate
     * @param squadra array di pokemon
     */
    public Utente(String nome, int vittorie, int sconfitte, int partiteGiocate,Pokemon[] squadra){
        //caso in cui ho una stringa nel file di testo relativa ad un utente
        this.nome = nome;
        this.vittorie = vittorie;
        this.sconfitte = sconfitte;
        this.partiteGiocate = partiteGiocate;
        this.squadra = squadra;
    }

    /**
     * Scrive su file di testo i progressi e la squadra relativa all'utente
     * @throws IOException
     */
    public void scrittore() throws IOException {
        // crea i file di testo quando non sono gia presenti
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testo/utenti.txt",true))){
            writer.write(toString());
            writer.newLine();

        } catch (IOException e){
            System.err.println("Errore nella scrittura nel file");
            throw e;
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("testo/squadreUtenti.txt",true))){
            writer.write(nome+":"+squadraString());
            writer.newLine();

        } catch (IOException e){
            System.err.println("Errore nella scrittura nel file");
            throw e;
        }


        //FORMATO FILE .TXT:
        //nome:vittorie:sconfitte:partiteGiocate

        //FORMATO FILE .TXT SQUADREUTENTI:
        //nomeUtente:pokemon:statistichePokemon:pokemon2:statistichePokemon
    }

    /**
     * Sovrascrive i salvataggi precedenti contenuti nel file dei salvataggi 'utenti.txt'
     * di un utente già definiti con quelli aggiornati
     */
    public void scrittoreModifica() {
        // la uso ad ogni chiusura dell'applicazione, prima di uscire la mando in esecuzione
        // aggiorna i salvataggi quando sono stati gia creati e vengono registrati progressi
        File sorgente = new File("testo/utenti.txt");
        String stringaOverwrite = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(sorgente))){
            String stringa;
            String[] ContieniStringheFile = new String[4];
            int cont = 0;

            while((stringa=reader.readLine())!=null){
                if (stringa.startsWith(this.nome+":")){
                    ContieniStringheFile[cont] = this.toString();
                }
                else{
                    ContieniStringheFile[cont] = stringa;
                }
                cont++;
            }
            if(ContieniStringheFile[3] != null) {
                stringaOverwrite = ContieniStringheFile[0] + "\n" + ContieniStringheFile[1] + "\n" + ContieniStringheFile[2] + "\n" + ContieniStringheFile[3] + "\n";
            }else{
                stringaOverwrite = ContieniStringheFile[0] + "\n" + ContieniStringheFile[1] + "\n" + ContieniStringheFile[2] + "\n";
            }
        } catch (IOException e ){
            System.err.println("File non formattato corretamente");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(sorgente))){
            writer.write(stringaOverwrite);
        }catch (IOException e ){
            System.err.println("File non formattato corretamente");
        }

        //resetto stringaOverwrite e modifico la directory sorgente/destinazione
        stringaOverwrite = "";
        sorgente = new File("testo/squadreUtenti.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(sorgente));){
            String stringa;
            String[] ContieniStringheFile = new String[4];
            int cont = 0;

            while((stringa=reader.readLine())!=null){
                if (stringa.startsWith(this.nome+":")){
                    ContieniStringheFile[cont] = nome + ":" + squadraString();
                }
                else{
                    ContieniStringheFile[cont] = stringa;
                }
                cont++;
            }
            if(ContieniStringheFile[3] != null) {
                stringaOverwrite = ContieniStringheFile[0] + "\n" + ContieniStringheFile[1] + "\n" + ContieniStringheFile[2] + "\n" + ContieniStringheFile[3] + "\n";
            }else{
                stringaOverwrite = ContieniStringheFile[0] + "\n" + ContieniStringheFile[1] + "\n" + ContieniStringheFile[2] + "\n";
            }
        } catch (IOException e ){
            System.err.println("File non formattato corretamente");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(sorgente))){
            writer.write(stringaOverwrite);
        }catch (IOException e ){
            System.err.println("File non formattato corretamente");
        }
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
    public void setSquadra(Pokemon[] squadra){
        this.squadra = squadra;
    }
    public Pokemon[] getSquadra(){
        return squadra;
    }

    @Override
    public String toString() {
        return nome + ":" + partiteGiocate + ":" + vittorie + ":" + sconfitte;
    }


    public String squadraString(){
        String info="";
        for(int i=0;i<6;i++){
            info+= squadra[i].toString();
            info+=":";
        }
        return info;

    }


}
