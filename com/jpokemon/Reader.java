package com.jpokemon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public Mossa buildMossa() throws IOException {
        String[] info;
        try{
            sc = new Scanner(new File(infoMosse));
            if(sc.hasNextLine()){
                info = sc.nextLine().split(":");
                if (info.length<6) // numero argomenti file .txt che deve NECESSARIAMENTE avere
                    throw new IOException("Il formato del file è errato, numero di elementi forniti insufficiente ");
                // costruttore mossa -->
                return new Mossa(info[0],Tipo.valueOf(info[1]),TipoMossa.valueOf(info[2]),Integer.parseInt(info[3]),Integer.parseInt(info[4]),Integer.parseInt(info[4]));
            }
            else
                return null;

        } catch (FileNotFoundException e){
            System.err.println("file non presente");
            throw e;
        }
    }

}
