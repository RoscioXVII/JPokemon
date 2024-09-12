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

/**
 * Classe per la lettura e la manipolazione dei dati
 * contenuti all'interno dei file di testo
 * del package 'testo'
 *
 */
public class Reader {
    //classe responsabile della lettura delle mosse e dei pokemon contenuti nei file .txt
    private static final String infoPokemon = "testo/pokemon.txt";
    private static final String infoMosse = "testo/mosse.txt";
    private static final String infoUtente = "testo/utenti.txt";
    private static final String infoListaMosse = "testo/MosseLista.txt";
    private static Scanner sc;
    private static Reader instance = null;
    private String[] mosse = new String[4]; // //Le metto come string per prendere solo il nome poi con l'altro metodo prenderò anche tutto il resto


    // singleton pattern
    private Reader() { // vuoto perche tutti i campi sono static / final
    }
    public static Reader getInstance(){
        if(instance==null)
            instance = new Reader();
        return instance;
    }

    // si usa pe i pokemon nuovi, senno si usa il load

    /**
     * Genera un'istanza pokemon a partire da una stringa di attributi fornita come parametro
     * @param string La stringa contenente tutti gli attributi del pokemon
     * @return pokemon l'istanza pokemon generato dagli attributi forniti
     * @throws IOException
     */
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

        String listaMosse = buildListaMosse(info[0]);
        pokemon.setListaMosse(listaMosse);


        pokemon.setPs(Ps);
        pokemon.setSalute(Ps);

        pokemon.setAttacco(attacco);
        pokemon.setDifesa(difesa);
        pokemon.setAttaccoSpeciale(attaccoSpeciale);
        pokemon.setDifesaSpeciale(difesaSpeciale);
        pokemon.setVelocita(velocita);

        return pokemon;

    }
    public String buildListaMosse(String nome) throws FileNotFoundException {
        String[] info;
        String mosse = "";
        try{
            sc = new Scanner(new File(infoListaMosse));
            while(sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if(info[1].equals(nome)){
                    for(int i = 2; i < info.length; i++){
                        if(i == 2){
                            mosse = mosse + info[i];
                        }else{
                            mosse = mosse + ":" + info[i];
                        }
                    }
                    System.out.println(mosse);
                    return mosse;
                }
            }
        }catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
        return null;
    }
    /**
     * Metodo che data una stringa di attributi, se correttemente formattata
     * restituisce un'istanza di Mossa
     * @param string Attributi per creare un'istanza di mossa
     * @return Mossa Mossa generata
     * @throws IOException
     */
    public Mossa buildMossaByString(String string)throws IOException{
        String[] info;
        try{
            sc = new Scanner(new File(infoMosse));
            while (sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if(info[0].equals(string)) {
                    // da problemi qui, probabilmente alcune mosse non sono ancor state definite e da problemi
                    return new Mossa(info[0],Tipo.valueOf(info[1].toUpperCase()),TipoMossa.valueOf(info[2].toUpperCase()),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[5]));
                }
                if(info[0].equals("null"))
                    return null; // se è null il bottone non sarà cliccabile
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

    /**
     * Conta il numero di righe del file di testo "infopokemon" e lo resituisce
     * @return i Il numero di righe presenti nel file
     * @throws FileNotFoundException
     */
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


    /**
     * Conta il numero di righe contenuto all'intento del file 'infomosse' e le restituisce
     * @return i Numero righe del file di testo
     * @throws FileNotFoundException
     */
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

    /**
     * Dato il nome dell'evoluzione di un pokemon cerca la riga all'intenrno del file 'infopokemon'
     * contenente tute le informazioni relative al pokemon ricercato e le restiuisce
     * @param nomeEvoluzione Nome del pokemon di cui di richiedono le informazioni
     * @return i Indice della riga del file di testo che contiene le operazioni
     * @throws FileNotFoundException
     */
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

    /**
     * Data una strigna contenente i parametri di salvataggio di un utente
     * ne restituisce l'istanza generata da questi ultimi
     * @param parametriUtente Parametri rilevati dal file di testo 'utenti.txt'
     * @return Utente Utente generato
     * @throws IOException
     */
    public Utente buildUtentebyString(String parametriUtente) throws IOException {
        String[] info;
        info = parametriUtente.split(":");
        Pokemon[] pokemons;

        //cerca squadra
        String squad = cercaSquadra(info[0]);

        //build squadra by string qua dentro
        pokemons = buildSquadrabyString(squad);
        // non metto if perche i pokemon in squadra sono SEMPRE 6 (vengono selezionati random)
        return new Utente(info[0],Integer.parseInt(info[1]),Integer.parseInt(info[2]),Integer.parseInt(info[3]),pokemons);
    }

    /**
     * Dato il nome di un utente viene restituita la squadra che gli appartiene, contenuta
     * all'interno del file 'squadreUtenti.txt'
     * @param squadra
     * @return La lista di attributi della squadra dell'utente fornito
     * @throws FileNotFoundException
     */
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

    /**
     * Genera una squadra di pokemon, scegliendone 6 dal file di testo
     * contenente tutte le informazioni di base dei pokemon definiti
     * @return La squadra generata (array di 6 pokemon)
     * @throws IOException
     */
    public Pokemon[] generaSquadra() throws IOException {
        Random random = new Random();
        Pokemon[] squad = new Pokemon[6];
        for(int i=0;i<6;i++){
            //questo da errore
            squad[i]=buildPokemonByString(getRigaByIndex("testo/pokemon.txt",random.nextInt(contaRighe())));
            System.out.println("nome = "+squad[i].getNome()); // da togliere è solo per prova chiaramente
        }
        return squad;
    }

    /**
     * Data una stringa contenente tutti i parametri di base di una squadra
     * viene generata, a partire da una stringa fornita, un array di pokemon (inteso come squadra)
     * @param parametriSquadra stringa letto dal file di testo 'squadreUtenti.txt' contenente tutti gli attributi fondamentali
     * @return array di 6 Pokemon
     * @throws IOException
     */

    public Pokemon[] buildSquadrabyString(String parametriSquadra) throws IOException {
        String pokemons[];
        Pokemon[] squadra = new Pokemon[6];
        pokemons = parametriSquadra.split(":"); // ogni pokemon è diviso dai :, mentre i parametri dal #
        // i pokemon so che sono 6 fissi di conseguenza
        // prevedere se sono null
        // controllare bene sta roba in build pokemon e la funzione per scrivere / leggere ecc...

        for(int i=1;i<=6;i++){ // inizio da 1 perche il primo elemento (indice 0), sarebbe il nome dell'utente proprietario della squadra
            // if se null mi mette null (non è da mettere in quanto vengono generate automaticamente squadre da 6 piene)
            squadra[i-1] = loadPokemon(pokemons[i]);
        }

        return squadra;
    }


    /**
     * Fornita una stringa di informazioni, genera un'istanza di un pokemon
     * salvato all'interno di un file di testo, tenendo conto dei progressi salvati
     * @param info Stringa prelevata dal file di testo
     * @return Pokemon caricato dalla squadra del file di testo
     * @throws IOException
     */
    public Pokemon loadPokemon(String info) throws IOException {
        //serve per caricare i pokemon da file di testo (sono separati da #)
        String[] attributi = info.split("#");

        //for (int i=0;i<attributi.length;i++)
            //System.out.println("£££££££ attributi = " + attributi[i] + " indice = " + i);
        Tipo tipo1 = Tipo.getTipoByString(attributi[1]); // index 1 out of bound for lenght 1
        Tipo tipo2 = Tipo.getTipoByString(attributi[2]);
        Mossa[] mosse = new Mossa[4];

        for (int i=0;i<3;i++){
            if(attributi[42+i].equals("null"))
                mosse[i] = null;
            else
                mosse[i] = buildMossaByString(attributi[41+i]);
        }
        for (Mossa mossa: mosse){
            if (mossa == null)
                System.out.println("mosse = null");
            else
                System.out.println("mosse = " + mossa.getNome());
        }


       String listaMosse = buildListaMosse(attributi[0]);


        return new Pokemon(attributi[0],tipo1,tipo2,Integer.parseInt(attributi[3]),Integer.parseInt(attributi[4]),mosse,Integer.parseInt(attributi[5]),
                Integer.parseInt(attributi[6]),Integer.parseInt(attributi[7]),Integer.parseInt(attributi[8]),Integer.parseInt(attributi[9]),
                Integer.parseInt(attributi[10]),attributi[11],attributi[12],attributi[13],Integer.parseInt(attributi[14]),Integer.parseInt(attributi[15]),
                Integer.parseInt(attributi[16]), Integer.parseInt(attributi[17]),Integer.parseInt(attributi[18]),Integer.parseInt(attributi[19]),Integer.parseInt(attributi[20]),
                Integer.parseInt(attributi[21]),Integer.parseInt(attributi[22]),Integer.parseInt(attributi[23]),
                Integer.parseInt(attributi[24]), Integer.parseInt(attributi[25]),Integer.parseInt(attributi[26]),Integer.parseInt(attributi[27]),
                Integer.parseInt(attributi[28]),Integer.parseInt(attributi[29]),Integer.parseInt(attributi[30]),Integer.parseInt(attributi[31]),Integer.parseInt(attributi[32]),
                Integer.parseInt(attributi[33]),Integer.parseInt(attributi[34]),Integer.parseInt(attributi[35]),Integer.parseInt(attributi[36]),Integer.parseInt(attributi[37]),
                Integer.parseInt(attributi[38]),attributi[39],Integer.parseInt(attributi[40]),listaMosse);

    }



    }


