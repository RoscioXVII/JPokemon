package com.jpokemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Reader {
    //classe responsabile della lettura delle mosse e dei pokemon contenuti nei file .txt
    private static final String infoPokemon = "testo/pokemon.txt";
    private static final String infoMosse = "testo/mosse.txt";
    private static final String infoUtente = "testo/utenti.txt";
    private static Scanner sc;
    private String[] mosse = new String[4]; // //Le metto come string per prendere solo il nome poi con l'altro metodo prenderò anche tutto il resto

    public Reader() { // vuoto perche tutti i campi sono static / final
    }

    public Pokemon buildPokemonByString(String string) throws IOException {
        Random a = new Random();    //SERVONO PER LA CREAZIONE DEGLI IV
        int[] IV = new int[6];      //SPIEGAZIONE VELOCE: Valori random che vanno da 0 a 15 che indicano dove il pokemon "spicca" di piu
                                    //ESEMPIO: se creiamo 2 pokemon, 1 snorlax e 1 charizard, sappiamo che charizard sarà sempre piu veloce
                                    // se creiamo 2 charizard pero' grazie a questi IV uno avrà le statistiche migliori dell altro
        String[] info;
        // devo fare una funz uguale che funziona solo per i file di testo e quindi splitta con #
        info = string.split(":");
        Mossa[] mossa = new Mossa[4];
        int cont = 3;

        for(int i = 0; i < 4; i++){
            mossa[i] = buildMossaByString(info[cont]);
            cont++;
        }

        Tipo tipo1 = Tipo.getTipoByString(info[1]);
        Tipo tipo2 = Tipo.getTipoByString(info[2]);
        int lvl = 5;

        for(int i = 0; i < 6; i++){
            IV[i] = a.nextInt(15);
        }
        int Ps = Formule.calcolaHpBase(Integer.parseInt(info[9]),lvl,IV[0],0);
        int attacco = Formule.calcolaStatisticheBase(Integer.parseInt(info[11]),lvl,IV[1],0);
        int difesa = Formule.calcolaStatisticheBase(Integer.parseInt(info[12]),lvl,IV[2],0);
        int attaccoSpeciale = Formule.calcolaStatisticheBase(Integer.parseInt(info[13]),lvl,IV[3],0);
        int difesaSpeciale = Formule.calcolaStatisticheBase(Integer.parseInt(info[14]),lvl,IV[4],0);
        int velocita = Formule.calcolaStatisticheBase(Integer.parseInt(info[15]),lvl,IV[5],0);

        Pokemon pokemon = new Pokemon(info[0],tipo1,tipo2,Integer.parseInt(info[7]),info[8],Integer.parseInt(info[9]),Integer.parseInt(info[10]),
                Integer.parseInt(info[11]),Integer.parseInt(info[12]),Integer.parseInt(info[13]),
                Integer.parseInt(info[14]),Integer.parseInt(info[15]),Integer.parseInt(info[17]),Integer.parseInt(info[18]),Integer.parseInt(info[19]),Integer.parseInt(info[20])
                ,Integer.parseInt(info[21]),Integer.parseInt(info[22]));

        pokemon.setLvl(lvl);

        pokemon.setMosse(mossa);

        pokemon.setPs(Ps);
        pokemon.setSalute(Ps);

        pokemon.setAttacco(attacco);
        pokemon.setDifesa(difesa);
        pokemon.setAttaccoSpeciale(attaccoSpeciale);
        pokemon.setDifesaSpeciale(difesaSpeciale);
        pokemon.setVelocita(velocita);

        return pokemon;

    }

    public Mossa buildMossaByString(String string)throws IOException{
        String[] info;
        try{
            sc = new Scanner(new File(infoMosse));
            while (sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if(info[0].equals(string)) {
                    return new Mossa(info[0],Tipo.valueOf(info[1].toUpperCase()),TipoMossa.valueOf(info[2].toUpperCase()),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]));
                }
            }
            return null; // non arrivera mai
        } catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
    }



    public Mossa buildMossa() throws IOException {
        String[] info;
        try{
            sc = new Scanner(new File(infoMosse));
            if(sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if (info.length<6) // numero argomenti file .txt che deve NECESSARIAMENTE avere
                    throw new IOException("Il formato del file è errato, numero di elementi forniti insufficiente ");
                // costruttore mossa -->
                return new Mossa(info[0],Tipo.valueOf(info[1]),TipoMossa.valueOf(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]));
            }
            else
                return null;

        } catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
    }

    public int contaRighe() throws FileNotFoundException { //cosi posso determinare il limite massimo del numero da generare tramite random
        int i=0;
        try{
            Scanner sc = new Scanner(new File(infoPokemon)); // il random serve solo per i pokemon, non per le mosse
            while(sc.hasNextLine()){
                sc.nextLine();
                i++;
            }
            return i;
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato");
            throw e;
        }
    }
    //genero l'indice random e con questa funzione ottengo la riga desiderata
    public String getRigaByIndex(String path,int index) throws FileNotFoundException {

        try{
            Scanner sc = new Scanner(new File(path));
            for(int i=0;i<index;i++){
                if(!sc.hasNextLine()) // se == false quindi
                    return null;
                else
                    sc.nextLine(); // va avanti (ignora le righe non corrispondenti all'indice dato)
            }
            if (sc.hasNextLine()) { // Controlla se la riga desiderata esiste
                return sc.nextLine(); // Restituisce la riga desiderata
            } else {
                return null; // Se l'indice è fuori dal range delle righe
            }



        } catch (FileNotFoundException e){
            System.err.println("File non trovato");
            throw e;
        }

    }

    public int contaRigheMosse() throws FileNotFoundException { //cosi posso determinare il massimo del file Mosse
        int i=0;
        try{
            Scanner sc = new Scanner(new File(infoMosse));
            while(sc.hasNextLine()){
                sc.nextLine();
                i++;
            }//PRODUCTOR CODE NON TOCCARE PER NESSUN MOTIVO

            return i;
        } catch (FileNotFoundException e) {
            System.err.println("File non trovato");
            throw e;
        }
    }

    public String[] BuildHashMossa() throws FileNotFoundException {
        String[] res = new String[contaRigheMosse()];
        int i = 0;
        try{
            sc = new Scanner(new File(infoMosse));
            while(sc.hasNextLine()){
                res[i] = sc.nextLine(); //con questo mi creo una base per fare il for dopo nella classe formule
                i++;
            }
            return res;

        } catch(FileNotFoundException e) {
            System.err.println("File non trovato");
            throw e;
        }

    }
    public int cercaRiga(String nomeEvoluzione) throws FileNotFoundException {
        int i=0;
        try{
            sc = new Scanner(new File(infoPokemon));
            while(sc.hasNextLine()){
                String[] info = sc.nextLine().split(":");
                if(info[0].equals(nomeEvoluzione)){
                    return i;
                }
                i++;
            }
        } catch (FileNotFoundException e){
            System.err.println("File non trovato");
            throw e;
        }
        return -1; // non è stata trovata l'evoluzione TODO: da cambiare
    }

    public Utente buildUtentebyString(String parametriUtente) throws IOException {
        String[] info;
        info = parametriUtente.split(":");
        Pokemon[] pokemons;
        /*try{
            sc = new Scanner(new File(infoUtente));
            // o leggo tutto e creo una lista oppure leggo una linea random con cui costruire la squadra dell'utente
            if (sc.hasNextLine())
                info = sc.nextLine().split(":");
            else
                return null; // caso in cui sono finite le righe da leggere
        } catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
         */


        //cerca squadra
        String squad = cercaSquadra(info[0]);
        //qua dentro mi serve un build squadra
        // da info dovrei lanciare 6 build pokemon con gli attributi contenuti in info
        // --- fare if per vedere se squad è vuoto ---

        //build squadra by string qua dentro
        System.out.println("SQUAD = "+ squad);
        pokemons = buildSquadrabyString(squad);
        // non metto if perche i pokemon in squadra sono SEMPRE 6 (vengono selezionati random)
        // metto tutti i parametri dentro
        return new Utente(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]),Integer.parseInt(info[3]),pokemons);
    }

    private String cercaSquadra(String squadra) throws FileNotFoundException {
        System.out.println("QUESTO ARRIVA = "+ squadra);
        sc = new Scanner(new File("testo/squadreUtenti.txt"));
        String info;
        while(sc.hasNextLine()){
            info = sc.nextLine();
            if(info.startsWith(squadra))
                return info;

        }
        return null;
        // squadra non trovata
    }

    public Pokemon[] generaSquadra() throws IOException {
        Random random = new Random();
        Pokemon[] squad = new Pokemon[6];
        for(int i=0;i<6;i++){
            //questo da errore
            squad[i]=buildPokemonByString(getRigaByIndex("testo/pokemon.txt",random.nextInt(contaRighe())));
            System.out.println("nome = "+squad[i].getNome());
        }
        return squad;
    }

    public Pokemon[] buildSquadrabyString(String parametriSquadra) throws IOException {
        String pokemons[];
        Pokemon[] squadra = new Pokemon[6];
        pokemons = parametriSquadra.split(":");
        // i pokemon so che sono 6 fissi di conseguenza
        // prevedere se sono null
        // ogni pokemon è diviso da : ed ogni attributo di un pokemon è diviso da #
        // controllare bene sta roba in build pokemon e la funzione per scrivere / leggere ecc...

        for(int i=0;i<6;i++){
            // if se null mi mette null
            pokemons[i] = pokemons[i].replace("#",":"); // risolto con questo
            squadra[i] = buildPokemonByString(pokemons[i]); // i parametri sono divisi da #, nel buildpokemon vengono splittati pero con il :, devo quindi usare un replace
        }
        return squadra;
    }



/*
    public String[] cercaSquadraPokemon(String nomeUtente){
        String[] info;
        // devo splittare le info lette dal file .txt
        return info;
    }


 */


    }


