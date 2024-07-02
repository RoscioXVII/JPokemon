package com.jpokemon;

import java.util.Objects;

public class Effetti {
    public Effetti(){}

    public static int Effetto(String nome){
        String effetto = Formule.getEffettoFromTabella(nome);
        if(Objects.equals(effetto, "Nulla")){
            return -1;
        }
        String[] Condizioni = effetto.split(":");
        int x = Condizioni.length;
        int res;
        switch (Condizioni[x-1]){
            case "Prima" -> res = Prima();
            case "Durante" -> res = Durante();
            case "Dopo" -> res = Dopo();
            default -> res = -1;
        }
        return res;
    }
    public static int Prima(){
        return 1;
    }
    public static int Durante(){
        return 2;
    }
    public static int Dopo(){
        return 3;
    }

    public static String getEffetto(String nome){
        String effetto = Formule.getEffettoFromTabella(nome);
        String[] get = effetto.split(":");

        return get[0];
    }

    public static int attivaEffettoDurante(Pokemon attaccante,Pokemon bersaglio, Mossa mossa){
        //questi di solito sono effetti che alterano il danno inflitto
        int danno;

        String place = Formule.getEffettoFromTabella(mossa.getNome());
        if(place == null){
            return Formule.danno(attaccante.getTipo1(),attaccante.getTipo2(),bersaglio.getTipo1(),bersaglio.getTipo2(),mossa.getTipo(),attaccante.getLvl(),mossa.getPotenza()
            ,attaccante.getAttacco(),bersaglio.getDifesa(),attaccante.getVelocita());
        }
        String[] accesso = place.split(":");

        switch (accesso[0]){
            case "RipetiAttacco" -> danno = Formule.RipetiAttacco(Integer.parseInt(accesso[1]),Integer.parseInt(accesso[2]),attaccante,bersaglio,mossa);
            case "Critico" -> danno = Formule.danno(attaccante.getTipo1(),attaccante.getTipo2(),bersaglio.getTipo1(),bersaglio.getTipo2(),mossa.getTipo(),attaccante.getLvl(),mossa.getPotenza()
                    ,attaccante.getAttacco(),bersaglio.getDifesa(),(attaccante.getVelocita() * 4)); //LA PROBABILITA QUA DOVREBBE ESSERE * 8 MA HO MESSO 4 PERCHE TROPPO FORTE
            case "UnColpo" -> danno = Formule.UnColpo(attaccante,bersaglio);
            case "DannoFisso" -> danno = Formule.DannoFisso(attaccante,bersaglio,mossa,accesso[1]);
            default -> danno = -6000;
        }

        return danno;
    }
    public static int attivaEffettoDopo(Pokemon attaccante, Pokemon bersaglio,Mossa mossa, int danno){
        //questi sono aumenti/diminuzioni di stats e cose che accadono a fine azione, probabilmente aggirabili se
        // il pokemon attacca per secondo
        int risultato;
        String place = Formule.getEffettoFromTabella(mossa.getNome());
        if(place == null){
            return -3; //se ritorno -3 allora vado avanti senza problemi
        }
        String[] accesso = place.split(":");

        switch (accesso[0]){
            case "Assorbimento" ->risultato = Formule.Assorbimento(attaccante,danno);
            case "Aumenta" ->;
            case "Diminuisci" ->;
            case "Tentenna" ->;
            case "Rinculo" ->;
            case "Esplosione" ->;
            case "Cura" ->;
            case "JKick" ->;
            case "Schermo" ->;
            case "Riflesso" ->;
        }

        return risultato;
    }

}
