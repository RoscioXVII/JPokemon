package com.jpokemon;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Formule {

    private static final Map<Tipo,Map<Tipo,Double>> tabellaDebolezze = new HashMap<>();
    private static final Map<String,String> Effetti = new HashMap<>();

    public Formule(){

    }
    public static int danno(Tipo tipoPoke1_1, Tipo tipoPoke1_2,Tipo tipoPoke2_1,Tipo tipoPoke2_2, Tipo tipoMossa,int bruttoColpo,int livello, int potenza, int attacco, int difesa){
        //RICORDA CHE IL BRUTTO COLPO VA MESSO DA FUORI COSI PUOI FAR USCIRE IL MESSAGGIO IN CASO
        //STESSA COSA PER IL SUPEREFFICACE
        Formule a =new Formule();

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
        // da 0 a 4 con valori 0 0.25 0.5 1 2 4
        if(tipoPoke2_2 == null){
            superefficace = a.getCostanteMoltiplicativa(tipoMossa,tipoPoke2_1);
        }else{
            superefficace = a.getCostanteMoltiplicativa(tipoMossa,tipoPoke2_1) * a.getCostanteMoltiplicativa(tipoMossa,tipoPoke2_2);;
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
    public Double getCostanteMoltiplicativa(Tipo tipo1,Tipo tipo2){
        return tabellaDebolezze.get(tipo1).get(tipo2);
    }

    //MAPPA PER EFFETTI DELLE MOSSE

    static{

        //AGGIUNTA MANUALE DEGLI EFFETTI (SPERO FUNZIONI PERCHE STO IMPAZZENDO GRAZIE)
        //TODO: Devo finire di mettere tutte le mosse, prima capisco come devo formattarli e poi compilo tutto.
        Effetti.put("Absorb","Assorbimento:Dopo");
        Effetti.put("Acid","Diminuisci:1:difesaSpeciale:Dopo");
        Effetti.put("Acid Armor","Aumenta:2:difesa:Dopo");
        Effetti.put("Agility","Aumenta:2:velocita:Dopo");
        Effetti.put("Amnesia","Aumenta:2:difesaSpeciale:Dopo");
        Effetti.put("Aurora Beam","Diminuisci:1:attacco:Dopo");
        Effetti.put("Barrage","RipetiAttacco:2:5:Durante");
        Effetti.put("Barrier","Aumenta:2:difesa:Dopo");
        Effetti.put("Bide","Bide"); //mossa con effetto unico (salva il danno subito per due turni poi attacca con il doppio del danno subito)
        Effetti.put("Bind","Trappola"); //altro effetto unico
        Effetti.put("Bite","Tentenna:Dopo");
        Effetti.put("Blizzard",null);
        Effetti.put("Body Slam",null);
        Effetti.put("Bone Club",null);
        Effetti.put("Bonemerang",null);
        Effetti.put("Bubble",null);
        Effetti.put("Bubble Beam",null);
        Effetti.put("Clamp",null);
        Effetti.put("Comet Punch",null);
        Effetti.put("Confuse Ray",null);
        Effetti.put("Confusion",null);
        Effetti.put("Constrict",null);
        Effetti.put("Conversion",null);
        Effetti.put("Counter",null);
        Effetti.put("Crabhammer",null);
        Effetti.put("Cut",null);
        Effetti.put("Defense Curl",null);
        Effetti.put("Dig",null);
        Effetti.put("Disable",null);
        Effetti.put("Dizzy Punch",null);
        Effetti.put("Double Kick",null);
        Effetti.put("Double Slap",null);
        Effetti.put("Double Team",null);
        Effetti.put("Double-Edge",null);
        Effetti.put("Dragon Rage",null);
        Effetti.put("Dream Eater",null);
        Effetti.put("Drill Peck",null);
        Effetti.put("Earthquake",null);
        Effetti.put("Egg Bomb",null);
        Effetti.put("Ember",null);
        Effetti.put("Explosion",null);
        Effetti.put("Fire Blast",null);
        Effetti.put("Fire Punch",null);
        Effetti.put("Fire Spin",null);
        Effetti.put("Fissure",null);
        Effetti.put("Flamethrower",null);
        Effetti.put("Flash",null);
        Effetti.put("Fly",null);
        Effetti.put("Focus Energy",null);
        Effetti.put("Fury Attack",null);
        Effetti.put("Fury Swipes",null);
        Effetti.put("Glare",null);
        Effetti.put("Growl","Diminuisci:1:attacco:Dopo");
        Effetti.put("Growth",null);
        Effetti.put("Guillotine",null);
        Effetti.put("Gust",null);
        Effetti.put("Harden",null);
        Effetti.put("Haze",null);
        Effetti.put("Headbutt",null);
        Effetti.put("High Jump Kick",null);
        Effetti.put("Horn Drill",null);
        Effetti.put("Hydro Pump",null);
        Effetti.put("Hyper Beam",null);
        Effetti.put("Hyper Fang",null);
        Effetti.put("Hypnosis",null);
        Effetti.put("Ice Beam",null);
        Effetti.put("Ice Punch",null);
        Effetti.put("Jump Kick",null);
        Effetti.put("Karate Chop",null);
        Effetti.put("Kinesis",null);
        Effetti.put("Leech Life",null);
        Effetti.put("Leech Seed",null);
        Effetti.put("Leer",null);
        Effetti.put("Lick",null);
        Effetti.put("Light Screen",null);
        Effetti.put("Lovely Kiss",null);
        Effetti.put("Low Kick",null);
        Effetti.put("Meditate",null);
        Effetti.put("Mega Drain",null);
        Effetti.put("Mega Kick",null);
        Effetti.put("Mega Punch",null);
        Effetti.put("Metronome",null);
        Effetti.put("Mimic",null);
        Effetti.put("Minimize",null);
        Effetti.put("Mirror Move",null);
        Effetti.put("Mist",null);
        Effetti.put("Night Shade",null);
        Effetti.put("Pay Day",null);
        Effetti.put("Peck",null);
        Effetti.put("Petal Dance",null);
        Effetti.put("Pin Missile",null);
        Effetti.put("Poison Gas",null);
        Effetti.put("Poison Powder",null);
        Effetti.put("Poison Sting",null);
        Effetti.put("Pound",null);
        Effetti.put("Psybeam",null);
        Effetti.put("Psychic",null);
        Effetti.put("Psywave",null);
        Effetti.put("Quick Attack",null);
        Effetti.put("Rage",null);
        Effetti.put("Razor Leaf",null);
        Effetti.put("Razor Wind",null);
        Effetti.put("Recover",null);
        Effetti.put("Reflect",null);
        Effetti.put("Rest",null);
        Effetti.put("Roar",null);
        Effetti.put("Rock Slide",null);
        Effetti.put("Rock Throw",null);
        Effetti.put("Sand Attack",null);
        Effetti.put("Scratch",null);
        Effetti.put("Screech",null);
        Effetti.put("Seismic Toss",null);
        Effetti.put("Self-Destruct",null);
        Effetti.put("Sharpen",null);
        Effetti.put("Sing",null);
        Effetti.put("Skull Bash",null);
        Effetti.put("Sky Attack",null);
        Effetti.put("Slam",null);
        Effetti.put("Slash",null);
        Effetti.put("Sleep Powder",null);
        Effetti.put("Sludge",null);
        Effetti.put("Smog",null);
        Effetti.put("Smokescreen",null);
        Effetti.put("Soft-Boiled",null);
        Effetti.put("Solar Beam",null);
        Effetti.put("Sonic Boom",null);
        Effetti.put("Spike Cannon",null);
        Effetti.put("Splash",null);
        Effetti.put("Spore",null);
        Effetti.put("Stomp",null);
        Effetti.put("Strength",null);
        Effetti.put("String Shot",null);
        Effetti.put("Struggle",null);
        Effetti.put("Stun Spore",null);
        Effetti.put("Submission",null);
        Effetti.put("Substitute",null);
        Effetti.put("Super Fang",null);
        Effetti.put("Supersonic",null);
        Effetti.put("Surf",null);
        Effetti.put("Swift",null);
        Effetti.put("Swords Dance",null);
        Effetti.put("Tackle","Diminuisci:1:difesa:Dopo");
        Effetti.put("Tail Whip",null);
        Effetti.put("Take Down",null);
        Effetti.put("Teleport",null);
        Effetti.put("Thrash",null);
        Effetti.put("Thunder",null);
        Effetti.put("Thunder Punch",null);
        Effetti.put("Thunder Shock",null);
        Effetti.put("Thunder Wave",null);
        Effetti.put("Thunderbolt",null);
        Effetti.put("Toxic",null);
        Effetti.put("Transform",null);
        Effetti.put("Tri Attack",null);
        Effetti.put("Twineedle",null);
        Effetti.put("Vine Whip",null);
        Effetti.put("Vise Grip",null);
        Effetti.put("Water Gun",null);
        Effetti.put("Waterfall",null);
        Effetti.put("Whirlwind",null);
        Effetti.put("Withdraw",null);
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
    public void Aumenta(Pokemon pokemon,int valore,String campo, int percentuale){
        int x;
        switch (campo){
            case "attacco" -> x = pokemon.aumentaAttacco(valore);
            case "difesa" -> x = pokemon.aumentaDifesa(valore);
            case "attaccoSpeciale" -> x = pokemon.aumentaAttaccoSpeciale(valore);
            case "difesaSpeciale" -> x = pokemon.aumentaDifesaSpeciale(valore);
            case "velocita" -> x =pokemon.aumentaVelocita(valore);
            default -> x = -1;
        }
        if (x == -1){
            System.out.println("Raggiunto massimo della statistica!");
        }
    }
    public void Diminuisci(Pokemon pokemon,int valore,String campo, int percentuale, int ripetizioni){
        int x;
        switch (campo){
            case "attacco" -> x = pokemon.diminuisciAttacco(valore);
            case "difesa" -> x = pokemon.diminuisciDifesa(valore);
            case "attaccoSpeciale" -> x = pokemon.diminuisciAttaccoSpeciale(valore);
            case "difesaSpeciale" -> x = pokemon.diminuisciDifesaSpeciale(valore);
            case "velocita" -> x =pokemon.diminuisciVelocita(valore);
            default -> x = -1;
        }
        if (x == -1){
            System.out.println("Raggiunto minimo della statistica!");
        }
    }
    public int Assorbimento(int danno){
        return danno/2;
    }
    public int[] RipetiAttacco(int minimo, int massimo, int percentuale){
        int[] attacchi = new int[5];
        Random roll = new Random();
        attacchi[0] = 1;
        attacchi[1] = 1;
        for(int i = 2; i<5; i++){
            int x = roll.nextInt();
            if(x <= 37 && i <= 3){
                attacchi[i] = 1;
            } else if (x <= 25 && i > 3) {
                attacchi[i] = 1;
            }else {
                attacchi[i] = 0;
                return attacchi; //Se returna qua allora ci sono un tot di attacchi non da calcolare che verranno controllati dal valore 0
            }
        }
        return attacchi; // se ritorna qua ha fatto 5 attacchi
    }
    public int Bide(int danno){
        return danno * 2;
    } // non implementiamo ti prego
    public int Trappola(int hpMAX){
        return hpMAX/8;
    }
    public Boolean Tentenna(int percentuale){
        Random roll = new Random();

        return roll.nextInt() < percentuale;
    }

}
