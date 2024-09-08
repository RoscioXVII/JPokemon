package com.jpokemon;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * Contiene tutte le formule necesserie per il calcolo e la gestione dei danni
 * in relazione ai pokemon, alle loro mosse e ai loro tipi
 */
public class Formule {

    private static final Map<Tipo,Map<Tipo,Double>> tabellaDebolezze = new HashMap<>();
    private static final Map<String,String> Effetti = new HashMap<>();

    public Formule(){

    }

    /**
     * Produce il calcolo e la stima del danno tenendo conto delle diverse variabili di efficacia
     * @param tipoPoke1_1
     * @param tipoPoke1_2
     * @param tipoPoke2_1
     * @param tipoPoke2_2
     * @param tipoMossa
     * @param livello
     * @param potenza
     * @param attacco
     * @param difesa
     * @param velocita
     * @return
     */
    public static int danno(Tipo tipoPoke1_1, Tipo tipoPoke1_2,Tipo tipoPoke2_1,Tipo tipoPoke2_2, Tipo tipoMossa,int livello, int potenza, int attacco, int difesa, int velocita){

        if(potenza <= 0){
            return 0;
        }

        int bruttoColpo = bruttoColpo(velocita);

        double stab = 1;
        double superefficace;

        if(tipoPoke1_2 == null){
            if(tipoPoke1_1 == tipoMossa){
                stab = 1.5;
            }
        }else{
            if(tipoPoke1_1 == tipoMossa || tipoPoke1_2 == tipoMossa){
                stab = 1.5;
            }
        }
        // da 0 a 4 con valori 0 0.25 0.5 1 2 4
        if(tipoPoke2_2 == null){
            superefficace = getCostanteMoltiplicativa(tipoMossa,tipoPoke2_1);
        }else{
            superefficace = getCostanteMoltiplicativa(tipoMossa,tipoPoke2_1) * getCostanteMoltiplicativa(tipoMossa,tipoPoke2_2);
        }

        if(superefficace >= 2){
            System.out.println("superefficace");
        } else if (superefficace < 1 && superefficace > 0) {
            System.out.println("non molto efficace....");
        }else if (superefficace == 0) {
            System.out.println("Non ha effetto!");
        }

        double danno = ((((((((2.0*livello*bruttoColpo)/5) + 2) * (potenza) * (((double) attacco /difesa) )/50) + 2)) * stab * superefficace));

        return (int)danno;
    }

    /**
     * Calcolo dle brutto colpo (determinato randomicamente)
     * @param velocita
     * @return
     */
    public static int bruttoColpo(int velocita){
        Random rand = new Random();
        int x = rand.nextInt(255);

        if(x < velocita/2){
            System.out.println("BRUTTO COLPO!!!!!");
            return 2;
        }else{
            return 1;
        }


    }

    /**
     * Calcolo del guadagno dell'esperienza dopo aver sconfitto un pokemon
     * @param baseExp
     * @param livelloPokemon
     * @return
     */
    public static int exp(int baseExp, int livelloPokemon){

        double formula = ((1.5 * baseExp * livelloPokemon)/(7*2));

        return (int)formula;

    }

    public static int floor(double valore){
        return (int)valore;
    }
    //QUESTE DUE FORMULE PROBABILMENTE DOVRANNO ESSERE CHIAMATE OGNI LVL UP
    //DI SOLITO IL CAMPO statistica SI RIFERISCE ALLA STATISTICA BASE DEL POKEMON
    //HP = floor(0.01 x (2 x Base + IV + floor(0.25 x EV)) x Level) + Level + 10
    //Other Stats = (floor(0.01 x (2 x Base + IV + floor(0.25 x EV)) x Level) + 5) x Nature
    public static int calcolaStatisticheBase(int statistica,int livello, int IV, int EV){
        return floor(0.01 * (2 * statistica + IV + floor(0.25 * EV)) * livello) + 5;
    }

    /**
     * Calcolo della salute di base di un pokeon
     * @param statistica
     * @param livello
     * @param IV
     * @param EV
     * @return
     */
    public static int calcolaHpBase(int statistica,int livello, int IV, int EV){
        return floor(0.01 * (2 * statistica + IV + floor(0.25 * EV)) * livello) + livello + 10;
    }

    // TABELLA DEBOLEZZE - POI LA SPOSTIAMO DOVE MEGLIO è
    // è una mappa che associa un tipo ad un'altra mappa con associazione tipo-moltiplicatore
    // è una costante in quanto predefinita dalle meccaniche di gioco
    //infatti questa viene riempita una volta e non piu modificata
    // con coppie UNIVOCHE
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

    //Prendi il valore del confronto tra i tipi

    /**
     * Tenendo conto dei tipi, viene restituita la costante motliplictiva del danno
     * legata alla tabella di efficacia dei tipi
     * @param tipo1
     * @param tipo2
     * @return costante moltiplicativa del danno
     */
    public static Double getCostanteMoltiplicativa(Tipo tipo1,Tipo tipo2){
        return tabellaDebolezze.get(tipo1).get(tipo2);
    }

    //MAPPA PER EFFETTI DELLE MOSSE

    static{

        //AGGIUNTA MANUALE DEGLI EFFETTI (SPERO FUNZIONI PERCHE STO IMPAZZENDO GRAZIE)
        //TODO: Devo finire di mettere tutte le mosse, prima capisco come devo formattarli e poi compilo tutto.
        Effetti.put("Cambio","Attacco Rapido:Prima");
        Effetti.put("Absorb","Assorbimento:Dopo");
        Effetti.put("Acid","Diminuisci:1:difesaSpeciale:30:Dopo");
        Effetti.put("Acid Armor","Aumenta:2:difesa:100:Dopo");
        Effetti.put("Agility","Aumenta:2:velocita:100:Dopo");
        Effetti.put("Amnesia","Aumenta:2:difesaSpeciale:100:Dopo");
        Effetti.put("Aurora Beam","Diminuisci:1:attacco:30:Dopo");
        Effetti.put("Barrage","RipetiAttacco:2:5:Durante");
        Effetti.put("Barrier","Aumenta:2:difesa:100:Dopo");
        Effetti.put("Bide","Bide"); //mossa con effetto unico (salva il danno subito per due turni poi attacca con il doppio del danno subito)
        Effetti.put("Bind",null); //altro effetto unico
        Effetti.put("Bite","Tentenna:Dopo");
        Effetti.put("Blizzard",null);
        Effetti.put("Body Slam",null);
        Effetti.put("Bone Club","Tentenna:Dopo");
        Effetti.put("Bonemerang","RipetiAttacco:2:2:Durante");
        Effetti.put("Bubble","Diminuisci:1:velocita:30:Dopo");
        Effetti.put("Bubble Beam","Diminuisci:1:velocita:30:Dopo");
        Effetti.put("Clamp",null);
        Effetti.put("Comet Punch","RipetiAttacco:2:5:Durante");
        Effetti.put("Confuse Ray",null);
        Effetti.put("Confusion",null);
        Effetti.put("Constrict","Diminuisci:1:velocita:30:Dopo");
        Effetti.put("Conversion",null); //RIMUOVI
        Effetti.put("Counter",null); //RIMUOVI
        Effetti.put("Crabhammer","Critico:Durante");
        Effetti.put("Cut",null);
        Effetti.put("Defense Curl","Aumenta:1:Difesa:100:Dopo");
        Effetti.put("Dig","FossaVolo"); //RIMUOVI
        Effetti.put("Disable",null); //RIMUOVI
        Effetti.put("Dizzy Punch",null);
        Effetti.put("Double Kick","RipetiAttacco:2:2:Durante");
        Effetti.put("Double Slap","RipetiAttacco:2:5:Durante");
        Effetti.put("Double Team","Aumenta:1:Evasione:100:Dopo");   //BRUTTA DEVO IMPLEMENTA
        Effetti.put("Double-Edge","Rinculo:1:3:Dopo");
        Effetti.put("Dragon Rage","DannoFisso:40:Durante");
        Effetti.put("Dream Eater",null); //RIMUOVI
        Effetti.put("Drill Peck",null);
        Effetti.put("Earthquake",null);
        Effetti.put("Egg Bomb",null);
        Effetti.put("Ember",null);
        Effetti.put("Explosion","Esplosione:Durante");
        Effetti.put("Fire Blast",null);
        Effetti.put("Fire Punch",null);
        Effetti.put("Fire Spin",null);
        Effetti.put("Fissure","UnColpo:Durante");
        Effetti.put("Flamethrower",null);
        Effetti.put("Flash","Diminuisci:1:Accuracy:30:Dopo");
        Effetti.put("Fly","FossaVolo");//RIMUOVI
        Effetti.put("Focus Energy",null);//RIMUOVI
        Effetti.put("Fury Attack","RipetiAttacco:2:5:Durante");
        Effetti.put("Fury Swipes","RipetiAttacco:2:5:Durante");
        Effetti.put("Glare",null);//RIMUOVI
        Effetti.put("Growl","Diminuisci:1:attacco:100:Dopo");
        Effetti.put("Growth","Aumenta:1:attacco:100:Dopo");
        Effetti.put("Guillotine","UnColpo:Durante");
        Effetti.put("Gust",null);
        Effetti.put("Harden","Aumenta:1:Difesa:100:Dopo");
        Effetti.put("Haze",null);//RIMUOVI
        Effetti.put("Headbutt","Tentenna:Dopo");
        Effetti.put("High Jump Kick","JKick:Dopo");
        Effetti.put("Horn Drill","UnColpo:Durante");
        Effetti.put("Hydro Pump",null);
        Effetti.put("Hyper Beam","Ricarica:Dopo");
        Effetti.put("Hyper Fang","Tentenna:Dopo");
        Effetti.put("Hypnosis",null);//RIMUOVI
        Effetti.put("Ice Beam",null);
        Effetti.put("Ice Punch",null);
        Effetti.put("Jump Kick","JKick:Dopo");
        Effetti.put("Karate Chop","Critico:Durante");
        Effetti.put("Kinesis","Diminuisci:1:Accuracy:30:Dopo");
        Effetti.put("Leech Life","Assorbimento:Dopo");
        Effetti.put("Leech Seed",null); //RIMUOVI TROPPO DIFF
        Effetti.put("Leer","Diminuisci:1:Difesa:30:Dopo");
        Effetti.put("Lick",null);
        Effetti.put("Light Screen","Schermo:Dopo"); //EFFETTO DA CAMPO
        Effetti.put("Lovely Kiss",null); //RIMUOVI
        Effetti.put("Low Kick",null);
        Effetti.put("Meditate","Aumenta:1:attacco:100:Dopo");
        Effetti.put("Mega Drain","Assorbimento:Dopo");
        Effetti.put("Mega Kick",null);
        Effetti.put("Mega Punch",null);
        Effetti.put("Metronome","Metronomo"); //RIMUOVI
        Effetti.put("Mimic",null); //Rimuovi
        Effetti.put("Minimize","Aumenta:2:Evasione:100:Dopo");
        Effetti.put("Mirror Move",null); //Rimuovi
        Effetti.put("Mist",null); //rimuovi
        Effetti.put("Night Shade","DannoFisso:Livello:Durante");
        Effetti.put("Pay Day",null);
        Effetti.put("Peck",null);
        Effetti.put("Petal Dance",null);
        Effetti.put("Pin Missile","RipetiAttacco:2:5:Durante");
        Effetti.put("Poison Gas",null); //RIMUOVI
        Effetti.put("Poison Powder",null); //RIMUOVI
        Effetti.put("Poison Sting",null);
        Effetti.put("Pound",null);
        Effetti.put("Psybeam",null);
        Effetti.put("Psychic",null);
        Effetti.put("Psywave","DannoFisso:Livello:Durante");
        Effetti.put("Quick Attack","Attacco Rapido:Prima");
        Effetti.put("Rage",null); //RIMUOVI
        Effetti.put("Razor Leaf","Critico:Durante");
        Effetti.put("Razor Wind","Critico:Durante");
        Effetti.put("Recover","Cura:Dopo");
        Effetti.put("Reflect","Riflesso");
        Effetti.put("Rest",null); //RIMUOVI
        Effetti.put("Roar",null); //RIMUOVI
        Effetti.put("Rock Slide","Tentenna:Dopo");
        Effetti.put("Rock Throw",null);
        Effetti.put("Rolling Kick","Tentenna:Dopo");
        Effetti.put("Sand Attack","Diminuisci:1:Accuracy:30:Dopo");
        Effetti.put("Scratch",null);
        Effetti.put("Screech","Diminuisci:2:Difesa:30:Dopo");
        Effetti.put("Seismic Toss","DannoFisso:Livello:Durante");
        Effetti.put("Self-Destruct","Esplosione:Dopo");
        Effetti.put("Sharpen","Aumenta:1:Attacco:100:Dopo");
        Effetti.put("Sing",null);//RIMUOVI
        Effetti.put("Skull Bash",null);
        Effetti.put("Sky Attack","Tentenna:Dopo");
        Effetti.put("Slam",null);
        Effetti.put("Slash","Critico:Durante");
        Effetti.put("Sleep Powder",null);//RIMUOVI
        Effetti.put("Sludge",null);
        Effetti.put("Smog",null);
        Effetti.put("Smokescreen","Diminuisci:1:Accuracy:30:Dopo");
        Effetti.put("Soft-Boiled","Cura:Dopo");
        Effetti.put("Solar Beam",null);
        Effetti.put("Sonic Boom","DannoFisso:20:Durante");
        Effetti.put("Spike Cannon","RipetiAttacco:2:5:Durante");
        Effetti.put("Splash",null);
        Effetti.put("Spore",null);//RIMUOVI
        Effetti.put("Stomp","Tentenna:Dopo");
        Effetti.put("Strength",null);
        Effetti.put("String Shot","Diminuisci:2:Velocita:30:Dopo");
        Effetti.put("Struggle","Rinculo:1:3:Dopo");
        Effetti.put("Stun Spore",null);//RIMUOVI
        Effetti.put("Submission","Rinculo:1:4:Dopo");
        Effetti.put("Substitute",null);//RIMUOVI
        Effetti.put("Super Fang","DannoFisso:Meta:Durante");
        Effetti.put("Supersonic",null);//RIMUOVI
        Effetti.put("Surf",null);
        Effetti.put("Swift",null);
        Effetti.put("Swords Dance","Aumenta:2:Attacco:100:Dopo");
        Effetti.put("Tackle",null);
        Effetti.put("Tail Whip","Diminuisci:1:difesa:100:Dopo");
        Effetti.put("Take Down","Rinculo:1:4:Dopo");
        Effetti.put("Teleport",null);//RIMUOVI
        Effetti.put("Thrash",null);
        Effetti.put("Thunder",null);
        Effetti.put("Thunder Punch",null);
        Effetti.put("Thunder Shock",null);
        Effetti.put("Thunder Wave",null);//RIMUOVI
        Effetti.put("Thunderbolt",null);
        Effetti.put("Toxic",null);//RIMUOVI
        Effetti.put("Transform",null);//RIMUOVI
        Effetti.put("Tri Attack",null);
        Effetti.put("Twineedle","RipetiAttacco:2:2:Durante");
        Effetti.put("Vine Whip",null);
        Effetti.put("Vise Grip",null);
        Effetti.put("Water Gun",null);
        Effetti.put("Waterfall","Tentenna:Dopo");
        Effetti.put("Whirlwind",null);//RIMUOVI
        Effetti.put("Withdraw","Aumenta:1:Difesa:100:Dopo");
        Effetti.put("Wrap",null);
    }
    public static String getEffettoFromTabella(String nome){
        if(Effetti.get(nome) == null){
            return "Nulla";
        }else{
            return Effetti.get(nome);
        }
    }
    /*
    TODO: questi due devono essere implementati in modo da prendere le statistiche del pokemon e le modificano non permanentemente
    TODO: inoltre questo aumento/diminuzione delle statistiche arriva fino ad un massimo di 6 (se hai una statistica a +5 e fai una mossa da +2 il risultato deve essere +6)
     */

    /**
     * Aumenta le statistiche generali di un pokemon
     * @param pokemon
     * @param valore
     * @param campo
     * @param percentuale
     */
    public static void Aumenta(Pokemon pokemon,int valore,String campo, int percentuale){
        int x;
        Random roll = new Random(100);
        if(roll.nextInt() > percentuale){
            return;
        }
        switch (campo.toLowerCase()){
            case "attacco" -> x = pokemon.aumentaAttacco(valore);
            case "difesa" -> x = pokemon.aumentaDifesa(valore);
            case "attaccospeciale" -> x = pokemon.aumentaAttaccoSpeciale(valore);
            case "difesaspeciale" -> x = pokemon.aumentaDifesaSpeciale(valore);
            case "velocita" -> x =pokemon.aumentaVelocita(valore);
            case "precisione" -> x=pokemon.aumentaPrecisione(valore);
            case "elusione" -> x=pokemon.aumentaElusione(valore);
            default -> x = -1;
        }
    }

    /**
     * Diminiusce le statistiche generali di un pokemon
     * @param pokemon
     * @param valore
     * @param campo
     * @param percentuale
     */
    public static void Diminuisci(Pokemon pokemon,int valore,String campo, int percentuale){
        int x;
        Random roll = new Random(100);
        if(roll.nextInt() > percentuale){
            return;
        }
        switch (campo.toLowerCase()){
            case "attacco" -> x = pokemon.diminuisciAttacco(valore);
            case "difesa" -> x = pokemon.diminuisciDifesa(valore);
            case "attaccospeciale" -> x = pokemon.diminuisciAttaccoSpeciale(valore);
            case "difesaspeciale" -> x = pokemon.diminuisciDifesaSpeciale(valore);
            case "velocita" -> x =pokemon.diminuisciVelocita(valore);
            case "precisione" -> x =pokemon.diminuisciPrecisione(valore);
            case "elusione" -> x =pokemon.diminuisciElusione(valore);
            default -> x = -1;
        }
    }
    public static void Esplosione(Pokemon attaccante){
        attaccante.setSalute(0);
    }
    public static int UnColpo(Pokemon attaccante, Pokemon bersaglio){
        Random roll = new Random();

        if(attaccante.getVelocita() > bersaglio.getVelocita() && roll.nextInt(100) < 30){
            return 99999;
        }

        return 0;
    }
    public static int DannoFisso(Pokemon attaccante, Pokemon bersaglio, Mossa mossa,String criterio){
        Double controllo = 1.0;
        if(bersaglio.getTipo2() == null){
            controllo = getCostanteMoltiplicativa(mossa.getTipo(),bersaglio.getTipo1());
        }else{
            controllo = getCostanteMoltiplicativa(mossa.getTipo(),bersaglio.getTipo1()) * getCostanteMoltiplicativa(mossa.getTipo(),bersaglio.getTipo2());
        }

        if(controllo == 0){
            return 0;
        }else{
            if(criterio.equals("Livello")){
                return attaccante.getLvl();
            }else{
                return Integer.parseInt(criterio);
            }
        }


    }
    public static int Assorbimento(Pokemon pokemon,int danno){

        if(pokemon.getSalute()+(danno/2) > pokemon.getPs()){
            pokemon.setSalute(pokemon.getPs());
        }else{
            pokemon.setSalute(pokemon.getSalute()+(danno/2));
        }

        return 1;
    }
    public static int RipetiAttacco(int minimo, int massimo, Pokemon attaccante,Pokemon bersaglio, Mossa mossa){

        int attacchi = minimo;
        int risultato = 0;
        Random roll = new Random();
        for(int i=minimo; i<massimo;i++){
            int x = roll.nextInt(100);
            if(x <= 37 && i <= 3){
                attacchi += 1;
            }else if(x <= 25){
                attacchi += 1;
            }else{
                for(int z =0; z<attacchi;z++){
                    risultato += danno(attaccante.getTipo1(),attaccante.getTipo2(),bersaglio.getTipo1(),bersaglio.getTipo2(),mossa.getTipo(),attaccante.getLvl(),
                            mossa.getPotenza(),attaccante.getAttacco(),bersaglio.getDifesa(),attaccante.getVelocita());
                }
                return risultato;
            }
        }
        for(int z =0; z<attacchi;z++){
            risultato += danno(attaccante.getTipo1(),attaccante.getTipo2(),bersaglio.getTipo1(),bersaglio.getTipo2(),mossa.getTipo(),attaccante.getLvl(),
                    mossa.getPotenza(),attaccante.getAttacco(),bersaglio.getDifesa(),attaccante.getVelocita());
        }
        return risultato;
    }

    public static int CreaTrappola(){
        return 3;
    }
    public static int Trappola(int hpMAX){
        return hpMAX/8;
    }
    public static int Tentenna(int percentuale){
        Random roll = new Random();

        if(roll.nextInt(100) < percentuale){
            return 1;
        }else{
            return -3;
        }
    }
    public static void Rinculo(int danno,Pokemon pokemon,int numeratore,int denominatore){

        int risultato = 0;

        risultato = pokemon.getSalute() - (danno/denominatore);

        if(risultato <= 0){
            pokemon.setSalute(0);
        }else{
            pokemon.setSalute(risultato);
        }

    }
    public static void Cura(Pokemon pokemon){
        int risultato = pokemon.getSalute() + (pokemon.getPs()/2);

        if(risultato >= pokemon.getPs()){
            pokemon.setSalute(pokemon.getPs());
        }else{
            pokemon.setSalute(risultato);
        }

    }

}
