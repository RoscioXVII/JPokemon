package com.jpokemon;

import java.util.HashMap;
import java.util.Map;

public class Formule {

    public Formule(){}

    public static int danno(int livello,String tipoPoke1_1, String tipoPoke1_2,String tipoPoke2_1,String tipoPoke2_2, String tipoMossa, int potenza, int attacco, int difesa){

        double stab = 1;
        double superefficace = 0; // da implementare
        int bruttoColpo = 0;        // DA IMPLEMENTARE INSIEME A SUPEREFFICACE

        if(tipoPoke1_1 == tipoMossa || tipoPoke1_2 == tipoMossa){
            stab = 1.5;
        }
        // if(tipoMossa is superefficace)

        double danno = ((((((((2*livello)/5) + 2) * (potenza) * (attacco/difesa) )/50) + 2)) * stab * bruttoColpo * superefficace);

        return (int)danno;
    }
    public static int exp(int baseExp, int livelloPokemon){

        double formula = ((1.5 * baseExp * livelloPokemon)/(7*2));

        return (int)formula;

    }

    // TABELLA DEBOLEZZE - POI LA SPOSTIAMO DOVE MEGLIO è
    // è una mappa che associa un tipo ad un'altra mappa con associazione tipo-moltiplicatore
    // è una costante in quanto predefinita dalle meccaniche di gioco
    //infatti questa viene riempita una volta e non piu modificata
    // con coppie UNIVOCHE
    private static final Map<Tipo,Map<Tipo,Double>> tabellaDebolezze = new HashMap<>();

    // static perche devo istanziare SEMPRE la tabella che anch'essa risulta essere un campo static
    static {
        for(Tipo tipo: Tipo.values()){
            tabellaDebolezze.put(tipo, new HashMap<>());
        }
        //AGGIUNTA MANUALE DI TUTTE LE DEBOLEZZE
        //ES.
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ACQUA,0.5);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.NORMALE,1.0);
    }






}
