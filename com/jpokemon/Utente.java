package com.jpokemon;

import java.io.*;


public class Utente {
    private String nome;
    private int vittorie;
    private int sconfitte;
    private int partiteGiocate;

    private Pokemon[] squadra;
    public Utente(String nome) throws IOException {
        this.nome = nome;
        vittorie=0;
        sconfitte=0;
        partiteGiocate=0;
        Reader rd = Reader.getInstance();
        squadra = rd.generaSquadra();

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
    public void scrittoreModifica() {
        // la uso ad ogni chiusura dell'applicazione, prima di uscire la mando in esecuzione
        // aggiorna i salvataggi quando sono stati gia creati e vengono registrati progressi
        File fileTemp  = new File("fileTemp.txt");
        File sorgente = new File("testo/utenti.txt");

        try(BufferedReader reader = new BufferedReader(new FileReader(sorgente));BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp))){
            String stringa;
            while((stringa=reader.readLine())!=null){
                if (stringa.startsWith(this.nome+":")){
                    writer.write(this.toString());
                    writer.newLine();
                }

                else{
                    writer.write(stringa);
                    writer.newLine();
                }

            }
            sorgente.delete();
            fileTemp.renameTo(new File("testo/utenti.txt"));
            // scrivi la squadra nell'apposito file
        } catch (IOException e ){
            System.err.println("File non formattato corretamente");
        }

        sorgente = new File("testo/squadreUtenti.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(sorgente));BufferedWriter writer = new BufferedWriter(new FileWriter(fileTemp))){
            String stringa;
            while((stringa=reader.readLine())!=null){
                // da modificare
                if (stringa.startsWith(this.nome+":")){ // potrebbe andarci --> #
                    writer.write(nome+":"+squadraString());
                    writer.newLine();
                }

                else{
                    writer.write(stringa);
                    writer.newLine();
                }

            }
            sorgente.delete();
            fileTemp.renameTo(new File("testo/squadreUtenti.txt"));
        } catch (IOException e ){
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


    public String squadraString(){
        String info="";
        for(int i=0;i<6;i++){
            info+= squadra[i].toString();
            info+=":";
        }
        return info;

    }







}
