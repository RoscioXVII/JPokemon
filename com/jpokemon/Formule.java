package com.jpokemon;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class Formule {

    public Formule(){}

    public static int danno(Tipo tipoPoke1_1, Tipo tipoPoke1_2,Tipo tipoPoke2_1,Tipo tipoPoke2_2, Tipo tipoMossa,int bruttoColpo,int livello, int potenza, int attacco, int difesa){

        double stab = 1;
        double superefficace = 1;

        if(tipoPoke1_2 == null){
            if(tipoPoke1_1 == tipoMossa){
                stab = 1.5;
            }
        }else{
            if(tipoPoke1_1 == tipoMossa || tipoPoke1_2 == tipoMossa){
                stab = 1.5;
            }
        }

        if(tipoPoke2_2 == null){
            superefficace = tabellaDebolezze.get(tipoMossa).get(tipoPoke2_1);
        }else{
            superefficace = tabellaDebolezze.get(tipoMossa).get(tipoPoke2_1) * tabellaDebolezze.get(tipoMossa).get(tipoPoke2_2);
        }

        double danno = ((((((((2.0*livello)/5) + 2) * (potenza) * (((double) attacco /difesa) )/50) + 2)) * stab * bruttoColpo * superefficace));

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


        for(Tipo tipo1: Tipo.values()){
            tabellaDebolezze.put(tipo1,new HashMap<>());
            for(Tipo tipo2: Tipo.values()){
                tabellaDebolezze.get(tipo1).put(tipo2,1.0);
            }
        }

        //AGGIUNTA MANUALE DI TUTTE LE DEBOLEZZE
        //ES.

        tabellaDebolezze.get(Tipo.NORMALE).put(Tipo.ROCCIA,0.5);
        tabellaDebolezze.get(Tipo.NORMALE).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.NORMALE).put(Tipo.SPETTRO,0.0);

        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.NORMALE, 2.0);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.ROCCIA, 2.0);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.ACCIAIO, 2.0);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.GHIACCIO, 2.0);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.BUIO, 2.0);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.VOLANTE, 0.5);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.VELENO, 0.5);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.COLEOTTERO, 0.5);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.PSICO, 0.5);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.FOLLETTO, 0.5);
        tabellaDebolezze.get(Tipo.LOTTA).put(Tipo.SPETTRO, 0.0);

        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.LOTTA,2.0);
        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.COLEOTTERO,2.0);
        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.ROCCIA,0.5);
        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.VOLANTE).put(Tipo.ELETTRO,0.5);

        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.FOLLETTO,2.0);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.VELENO,0.5);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.TERRA,0.5);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.ROCCIA,0.5);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.SPETTRO,0.5);
        tabellaDebolezze.get(Tipo.VELENO).put(Tipo.ACCIAIO,0.0);

        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.VELENO,2.0);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.ROCCIA,2.0);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.ACCIAIO,2.0);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.FUOCO,2.0);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.ELETTRO,2.0);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.COLEOTTERO,0.5);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.ERBA,0.5);
        tabellaDebolezze.get(Tipo.TERRA).put(Tipo.VOLANTE,0.0);

        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.VOLANTE,2.0);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.COLEOTTERO,2.0);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.FUOCO,2.0);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.GHIACCIO,2.0);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.LOTTA,0.5);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.TERRA,0.5);
        tabellaDebolezze.get(Tipo.ROCCIA).put(Tipo.ACCIAIO,0.5);

        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.PSICO,2.0);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.BUIO,2.0);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.LOTTA,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.VOLANTE,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.VELENO,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.SPETTRO,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.FUOCO,0.5);
        tabellaDebolezze.get(Tipo.COLEOTTERO).put(Tipo.FOLLETTO,0.5);

        tabellaDebolezze.get(Tipo.SPETTRO).put(Tipo.SPETTRO,2.0);
        tabellaDebolezze.get(Tipo.SPETTRO).put(Tipo.PSICO,2.0);
        tabellaDebolezze.get(Tipo.SPETTRO).put(Tipo.BUIO,0.5);
        tabellaDebolezze.get(Tipo.SPETTRO).put(Tipo.NORMALE,0.0);

        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.ROCCIA,2.0);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.GHIACCIO,2.0);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.FOLLETTO,2.0);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.FUOCO,0.5);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.ACQUA,0.5);
        tabellaDebolezze.get(Tipo.ACCIAIO).put(Tipo.ELETTRO,0.5);

        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.COLEOTTERO,2.0);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ACCIAIO,2.0);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.GHIACCIO,2.0);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ROCCIA,0.5);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.FUOCO,0.5);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.ACQUA,0.5);
        tabellaDebolezze.get(Tipo.FUOCO).put(Tipo.DRAGO,0.5);

        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.TERRA,2.0);
        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.ROCCIA,2.0);
        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.FUOCO,2.0);
        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.ACQUA,0.5);
        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.ERBA,0.5);
        tabellaDebolezze.get(Tipo.ACQUA).put(Tipo.DRAGO,0.5);

        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.ROCCIA,2.0);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.TERRA,2.0);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.ACQUA,2.0);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.VOLANTE,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.VELENO,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.COLEOTTERO,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.FUOCO,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.ERBA,0.5);
        tabellaDebolezze.get(Tipo.ERBA).put(Tipo.DRAGO,0.5);

        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.VOLANTE,2.0);
        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.ACQUA,2.0);
        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.ERBA,0.5);
        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.ELETTRO,0.5);
        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.DRAGO,0.5);
        tabellaDebolezze.get(Tipo.ELETTRO).put(Tipo.TERRA,0.0);

        tabellaDebolezze.get(Tipo.PSICO).put(Tipo.LOTTA,2.0);
        tabellaDebolezze.get(Tipo.PSICO).put(Tipo.VELENO,2.0);
        tabellaDebolezze.get(Tipo.PSICO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.PSICO).put(Tipo.PSICO,0.5);
        tabellaDebolezze.get(Tipo.PSICO).put(Tipo.BUIO,0.0);

        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.VOLANTE,2.0);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.TERRA,2.0);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.ERBA,2.0);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.DRAGO,2.0);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.FUOCO,0.5);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.ACQUA,0.5);
        tabellaDebolezze.get(Tipo.GHIACCIO).put(Tipo.GHIACCIO,0.5);

        tabellaDebolezze.get(Tipo.DRAGO).put(Tipo.DRAGO,2.0);
        tabellaDebolezze.get(Tipo.DRAGO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.DRAGO).put(Tipo.FOLLETTO,0.0);

        tabellaDebolezze.get(Tipo.BUIO).put(Tipo.SPETTRO,2.0);
        tabellaDebolezze.get(Tipo.BUIO).put(Tipo.PSICO,2.0);
        tabellaDebolezze.get(Tipo.BUIO).put(Tipo.LOTTA,0.5);
        tabellaDebolezze.get(Tipo.BUIO).put(Tipo.BUIO,0.5);
        tabellaDebolezze.get(Tipo.BUIO).put(Tipo.FOLLETTO,0.5);

        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.LOTTA,2.0);
        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.DRAGO,2.0);
        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.BUIO,2.0);
        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.TERRA,0.5);
        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.ACCIAIO,0.5);
        tabellaDebolezze.get(Tipo.FOLLETTO).put(Tipo.FUOCO,0.5);

    }

    public Double getCostanteMoltiplicativa(Tipo tipo1,Tipo tipo2){
        return tabellaDebolezze.get(tipo1).get(tipo2);
    }

    //MAPPA PER EFFETTI DELLE MOSSE
    private static final Map<String,String> Effetti = new HashMap<>();

    private static String[] creaStringa(){      //Dovuto creare questo perche' per qualche motivo non posso usare la funzione fatta su reader
        Reader a = new Reader();
        try{
            return a.BuildHashMossa();
        }catch(FileNotFoundException e) {
            throw new RuntimeException("File non trovato"); // messa cosi perchè non essendo una classe non riesco a fare throw IOException
        }
    }

    static{
        String[] a = creaStringa();
        int cont = a.length;

        for(String i: a){
            Effetti.put(i,null);
        }

        //AGGIUNTA MANUALE DEGLI EFFETTI (SPERO FUNZIONI PERCHE STO IMPAZZENDO GRAZIE)

        Effetti.put(a[0],"Recupera:0.5:Danno");
        Effetti.put(a[1],"Diminuisci:1:difesaSpeciale");
        Effetti.put(a[2],"Aumenta:2:difesa");
        Effetti.put(a[3],"Aumenta:2:velocita");
        Effetti.put(a[4],"Aumenta:2:difesaSpeciale");
        Effetti.put(a[5],"Diminuisci:1:attacco");
        Effetti.put(a[6],"RipetiAttacco:2:5");
        Effetti.put(a[7],"Aumenta:2:difesa");
        Effetti.put(a[8],"Bide"); //mossa con effetto unico (salva il danno subito per due turni poi attacca con il doppio del danno subito)
        Effetti.put(a[9],"Trappola"); //altro effetto unico
        Effetti.put(a[10],"Tentenna:");

    }

    public void Aumenta(){

    }
    public void Diminuisci(){}
    public void RipetiAttacco(){}
    public void Bide(){}
    public void Trappola(){}
    public void Tentenna(){}

}
