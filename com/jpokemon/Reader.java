package com.jpokemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    //classe responsabile della lettura delle mosse e dei pokemon contenuti nei file .txt
    private static final String infoPokemon = "testo/pokemon.txt";
    private static final String infoMosse = "testo/mosse.txt";
    private static Scanner sc;
    private String[] mosse = new String[4]; // //Le metto come string per prendere solo il nome poi con l'altro metodo prenderò anche tutto il resto

    public Reader() { // vuoto perche tutti i campi sono static / final
    }

    public Pokemon buildPokemon() throws IOException {
        String[] info;
        try{
            sc = new Scanner(new File(infoPokemon));
            // o leggo tutto e creo una lista oppure leggo una linea random con cui costruire la squadra dell'utente
            if (sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if(info.length<16)
                    throw new IOException("Il formato del file è errato, numero di elementi forniti insufficiente.");

                //mosse[0] = info[3];
                //mosse[1] = info[4];
                //mosse[2] = info[5];
                //mosse[3] = info[6];


                return new Pokemon(info[0],info[1],info[2],Integer.parseInt(info[7]),info[8],Integer.parseInt(info[9]),Integer.parseInt(info[10]),
                        Integer.parseInt(info[11]),Integer.parseInt(info[12]),Integer.parseInt(info[13]),
                        Integer.parseInt(info[14]),Integer.parseInt(info[15]));

            }
            else
                return null; // caso in cui sono finite le righe da leggere
        } catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
    }

    public Pokemon buildPokemonByString(String string) throws IOException {
        String[] info;
        info = string.split(":");
        Mossa[] mossa = new Mossa[4];
        int cont = 3;

        for(int i = 0; i < 4; i++){
            mossa[i] = buildMossaByString(info[cont]);
            cont++;
        }

        Pokemon ritorno = new Pokemon(info[0],info[1],info[2],Integer.parseInt(info[7]),info[8],Integer.parseInt(info[9]),Integer.parseInt(info[10]),
                Integer.parseInt(info[11]),Integer.parseInt(info[12]),Integer.parseInt(info[13]),
                Integer.parseInt(info[14]),Integer.parseInt(info[15]));

        ritorno.setMosse(mossa);

        return ritorno;

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
            while(sc.hasNextLine())
                i++;
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
                sc.nextLine(); // va avanti (ignora le righe non corrispondenti all'indice dato)
            }
            return sc.nextLine(); // riga desiderata


        } catch (FileNotFoundException e){
            System.err.println("File non trovato");
            throw e;
        }

    }

    public int contaRigheMosse() throws FileNotFoundException { //cosi posso determinare il massimo del file Mosse
        int i=0;
        try{
            Scanner sc = new Scanner(new File(infoMosse));
            while(sc.hasNextLine())
                i++;
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

}
