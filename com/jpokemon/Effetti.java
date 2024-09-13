package com.jpokemon;

import java.text.Normalizer;
import java.util.Objects;

/**
 * Classe incaricata della gestione delle mosse e del calcolo degli effetti e delle variazioni
 * che queste generano sulle statistiche di altre mosse pokemon, prevedendo anche le tempestiche con cui questi dovranno avere effetto
 */
public class Effetti {
    public Effetti() {
    }

    /**
     *
     * @param nome : nome della mossa fornita, la quale verrà ricercata nel file di testo 'mosse.txt' per prelvare le sue caratteristiche
     *
     */
    public static int Effetto(String nome) {
        String effetto = Formule.getEffettoFromTabella(nome);
        if (Objects.equals(effetto, "Nulla")) {
            return -1;
        }
        String[] Condizioni = effetto.split(":");
        int x = Condizioni.length;
        int res;
        switch (Condizioni[x - 1]) {
            case "Prima" -> res = Prima();
            case "Durante" -> res = Durante();
            case "Dopo" -> res = Dopo();
            default -> res = -1;
        }
        return res;
    }

    public static int Prima() {
        return 1;
    }

    public static int Durante() {
        return 2;
    }

    public static int Dopo() {
        return 3;
    }



    /**
     *
     * @param attaccante : pokemon che sferra la mossa
     * @param bersaglio : pokemon che subisce la mossa
     * @param mossa : mossa sferrata dal pokemon attaccante
     * @return danno generato dalla mossa, modiicata dagli effetti che essa ha generato (in funzione dei tipi dei pokemon)
     */
    public static int attivaEffettoDurante(Pokemon attaccante, Pokemon bersaglio, Mossa mossa) {
        //questi di solito sono effetti che alterano il danno inflitto
        int danno;

        String place = Formule.getEffettoFromTabella(mossa.getNome());
        if (place == null) {
            return Formule.danno(attaccante.getTipo1(), attaccante.getTipo2(), bersaglio.getTipo1(), bersaglio.getTipo2(), mossa.getTipo(), attaccante.getLvl(), mossa.getPotenza()
                    , attaccante.getAttacco(), bersaglio.getDifesa(), attaccante.getVelocita());
        }
        String[] accesso = place.split(":");

        switch (accesso[0]) {
            case "RipetiAttacco" ->
                    danno = Formule.RipetiAttacco(Integer.parseInt(accesso[1]), Integer.parseInt(accesso[2]), attaccante, bersaglio, mossa);
            case "Critico" ->
                    danno = Formule.danno(attaccante.getTipo1(), attaccante.getTipo2(), bersaglio.getTipo1(), bersaglio.getTipo2(), mossa.getTipo(), attaccante.getLvl(), mossa.getPotenza()
                            , attaccante.getAttacco(), bersaglio.getDifesa(), (attaccante.getVelocita() * 4)); //LA PROBABILITA QUA DOVREBBE ESSERE * 8 MA HO MESSO 4 PERCHE TROPPO FORTE
            case "UnColpo" -> danno = Formule.UnColpo(attaccante, bersaglio);
            case "DannoFisso" -> danno = Formule.DannoFisso(attaccante, bersaglio, mossa, accesso[1]);
            default -> danno = -6000;
        }

        return danno;
    }

    /**
     *
     * @param attaccante : pokemon che sferra la mossa
     * @param bersaglio : pokemon che subisce la mossa
     * @param mossa : mossa sferrata dal pokemon attaccante
     * @param danno : danno generato da una mossa
     * @return danno ulteriore generato dopo l'esecuzione della mossa principale
     */
    public static int attivaEffettoDopo(Pokemon attaccante, Pokemon bersaglio, Mossa mossa, int danno) {
        //questi sono aumenti/diminuzioni di stats e cose che accadono a fine azione, probabilmente aggirabili se
        // il pokemon attacca per secondo
        int risultato = -3;
        String place = Formule.getEffettoFromTabella(mossa.getNome());
        if (place == null) {
            return risultato;
        }
        String[] accesso = place.split(":");

        switch (accesso[0]) {
            case "Aumenta" -> Formule.Aumenta(attaccante,Integer.parseInt(accesso[1]),accesso[2],Integer.parseInt(accesso[3]));
            case "Diminuisci" -> Formule.Diminuisci(attaccante,Integer.parseInt(accesso[1]),accesso[2],Integer.parseInt(accesso[3]));
            case "Tentenna" -> risultato = Formule.Tentenna(30);
            case "Rinculo" -> Formule.Rinculo(danno,attaccante,Integer.parseInt(accesso[1]),Integer.parseInt(accesso[2]));
            case "Esplosione" -> Formule.Esplosione(attaccante);

        }
        if(accesso[0].equals("Tentenna") && danno == 0){
            return -3;
        }

        return risultato;
    }

}
