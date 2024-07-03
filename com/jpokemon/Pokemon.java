package com.jpokemon;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Pokemon {
    private String nome;
    private Tipo tipo1;
    private Tipo tipo2;
    private int lvl;

    private int esp; // esperienza per l'aumento del livello
    private Mossa[] mosse = new Mossa[4];

    //statistiche base
    private int psBase; // TODO: AO NON SO A CHE SERVE, MI SERVE SALUTE
    private int attaccoBase;
    private int difesaBase;
    private int attaccoSpecialeBase;
    private int difesaSpecialeBase;
    private int velocitaBase;
    //variabili per sprite e quelle per le evoluzioni
    private String spriteFront;
    private String spriteBack;
    private String spriteMini;
    private int lvlEvoluzione;
    //Stats effettive ? dovrebbe funzionare cosi
    private int ps; // la salute verrà aggiornata durante gli attacchi, ps è invece il valore BASE
    private int attacco;
    private int difesa;
    private int attaccoSpeciale;
    private int difesaSpeciale;
    private int velocita;
    private int EV; //DA INSERIRE DENTRO IL COSTRUTTORE E DENTRO IL FILE DI TESTO DEI POKEMON
    //SET PER MOSSE
    //SERVONO PER GESTIRE GLI AUMENTI E LA DIMINUZIONE DELLE STATISTICHE, IL MASSIMO VALE +6 il minimo vale -6
    private int ripetizioniAttacco = 0;
    private int ripetizioniDifesa = 0;
    private int ripetizioniAttaccoSpeciale = 0;
    private int ripetizioniDifesaSpeciale = 0;
    private int ripetizioniVelocita = 0;
    //SET per EXTRA
    private int IVps;
    private int IVattacco;
    private int IVdifesa;
    private int IVattaccoSpeciale;
    private int IVdifesaSpeciale;
    private int IVvelocita;

    private int EVps;
    private int EVattacco;
    private int EVdifesa;
    private int EVattaccoSpeciale;
    private int EVdifesaSpeciale;
    private int EVvelocita;

    private String nomeEvoluzione;
    private int salute;

    public Pokemon(String nome, Tipo tipo1,Tipo tipo2, int lvlEvoluzione,String nomeEvoluzione, int ps, int esp,
                   int attacco, int difesa, int attaccoSpeciale, int difesaSpeciale, int velocita){ // devo trovare il modo di mettere le mosse
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.lvlEvoluzione = lvlEvoluzione;
        this.nomeEvoluzione = nomeEvoluzione;
        this.psBase = ps;
        this.esp = esp; //NOTA questa vale come xp "BASE" ovvero quella che ottieni VINCENDO contro il pokemon in questione
        this.attaccoBase = attacco;
        this.difesaBase = difesa;
        this.attaccoSpecialeBase = attaccoSpeciale;
        this.difesaSpecialeBase = difesaSpeciale;
        this.velocitaBase = velocita;
        this.spriteFront = "img/front/"+nome.toLowerCase() + "-front.gif";
        this.spriteBack = "img/retro/"+nome.toLowerCase() + "-retro.gif";
        this.spriteMini = "img/mini/"+nome.toLowerCase() + "-mini.gif";
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }
    public void setTipo1(Tipo tipo) {
        this.tipo1 = tipo;
    }
    public void setTipo2(Tipo tipo) {
        this.tipo2 = tipo;
    }
    public Tipo getTipo1() {
        return tipo1;
    }
    public Tipo getTipo2() {
        return tipo2;
    }


    public void setMosse(Mossa[] mosse){
        this.mosse = mosse;
    }
    public Mossa[] getMosse(){
        return mosse;
    }
    public String mossaString(){
        String s = "";
        for(int i = 0; i < mosse.length; i++) {
            if(mosse[i] != null){
                s += mosse[i].getNome() + " ";
            }else{
                s += "null ";
            }

        }
        return s;
    }
// i pokemon sono definiti con questo formato (nome-tipo1-tipo2-mosse[4]) - livello evoluzione - nomeEvoluzione - ps - esp - attacco - difesa - speciale - velocita
// SET E GET STATS EFFETTIVE, levato quelli per le stats base che tanto devono rimanere sempre uguali

    public void setPs(int Ps){
        this.ps = Ps;
    }
    public int getPs() {
        return ps;
    }
    public int getPsBase(){
        return psBase;
    }

    public void setSalute(int salute){
        this.salute = salute;
    }
    public int getSalute(){return salute;}

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public int getLvl(){
        return lvl;
    }

    public void setAttacco(int attacco){this.attacco = attacco;}
    public int getAttacco(){return attacco;}
    public int getAttaccoBase(){return attaccoBase;}

    public void setDifesa(int difesa) {this.difesa = difesa;}
    public int getDifesa(){return difesa;}
    public int getDifesaBase(){return difesaBase;}

    public void setAttaccoSpeciale(int ATS){
        this.attaccoSpeciale = ATS;
    }
    public int getAttaccoSpeciale(){
        return attaccoSpeciale;
    }
    public int getAttaccoSpecialeBase(){
        return attaccoSpecialeBase;
    }

    public void setDifesaSpeciale(int DFS){
        this.difesaSpeciale = DFS;
    }
    public int getDifesaSpeciale(){
        return difesaSpeciale;
    }
    public int getDifesaSpecialeBase(){
        return difesaSpecialeBase;
    }

    public void setVelocita(int velocita){this.velocita = velocita;}
    public int getVelocita(){return velocita;}
    public int getVelocitaBase(){return velocitaBase;}

    //per gli sprite basta il get

    public String getSpriteFront() {
        return spriteFront;
    }

    public String getSpriteBack() {
        return spriteBack;
    }

    public String getSpriteMini() {
        return spriteMini;
    }

    public int aumentaAttacco(int valore){
        if(this.ripetizioniAttacco == 6){
            return -1;
        }else if(this.ripetizioniAttacco+valore > 6){
            while(this.ripetizioniAttacco < 6){
                this.attacco += 1;
                this.ripetizioniAttacco += 1;
            }
            return 1;
        }else{
            this.attacco = this.attacco + 1;
            this.ripetizioniAttacco += 1;
            return 1;
        }
    };
    public int aumentaDifesa(int valore){
        if(this.ripetizioniDifesa == 6){
            return -1;
        }else if(this.ripetizioniDifesa+valore > 6){
            while(this.ripetizioniDifesa < 6){
                this.difesa += 1;
                this.ripetizioniDifesa += 1;
            }
            return 1;
        }else{
            this.difesa = this.difesa + 1;
            this.ripetizioniDifesa += 1;
            return 1;
        }
    };
    public int aumentaAttaccoSpeciale(int valore){
        if(this.ripetizioniAttaccoSpeciale == 6){
            return -1;
        }else if(this.ripetizioniAttaccoSpeciale+valore > 6){
            while(this.ripetizioniAttaccoSpeciale < 6){
                this.attaccoSpeciale += 1;
                this.ripetizioniAttaccoSpeciale += 1;
            }
            return 1;
        }else{
            this.attaccoSpeciale = this.attaccoSpeciale + 1;
            this.ripetizioniAttaccoSpeciale += 1;
            return 1;
        }
    };
    public int aumentaDifesaSpeciale(int valore){
        if(this.ripetizioniDifesaSpeciale == 6){
            return -1;
        }else if(this.ripetizioniDifesaSpeciale+valore > 6){
            while(this.ripetizioniDifesaSpeciale < 6){
                this.difesaSpeciale += 1;
                this.ripetizioniDifesaSpeciale += 1;
            }
            return 1;
        }else{
            this.difesaSpeciale = this.difesaSpeciale + 1;
            this.ripetizioniDifesaSpeciale += 1;
            return 1;
        }
    };
    public int aumentaVelocita(int valore){
        if(this.ripetizioniVelocita == 6){
            return -1;
        }else if(this.ripetizioniVelocita+valore > 6){
            while(this.ripetizioniVelocita < 6){
                this.velocita += 1;
                this.ripetizioniVelocita += 1;
            }
            return 1;
        }else{
            this.velocita = this.velocita + 1;
            this.ripetizioniVelocita += 1;
            return 1;
        }
    };


    public int diminuisciAttacco(int valore){
        if(this.ripetizioniAttacco == -6){
            return -1;
        }else if(this.ripetizioniAttacco-valore < -6){
            while(this.ripetizioniAttacco > -6){
                this.attacco -= 1;
                this.ripetizioniAttacco -= 1;
            }
            return 1;
        }else{
            this.attacco = this.attacco - 1;
            return 1;
        }
    };
    public int diminuisciDifesa(int valore){
        if(this.ripetizioniDifesa == -6){
            return -1;
        }else if(this.ripetizioniDifesa-valore < -6){
            while(this.ripetizioniDifesa > -6){
                this.difesa -= 1;
                this.ripetizioniDifesa -= 1;
            }
            return 1;
        }else{
            this.difesa = this.difesa - 1;
            return 1;
        }
    };
    public int diminuisciAttaccoSpeciale(int valore){
        if(this.ripetizioniAttaccoSpeciale == -6){
            return -1;
        }else if(this.ripetizioniAttaccoSpeciale-valore < -6){
            while(this.ripetizioniAttaccoSpeciale > -6){
                this.attaccoSpeciale -= 1;
                this.ripetizioniAttaccoSpeciale -= 1;
            }
            return 1;
        }else{
            this.attaccoSpeciale = this.attaccoSpeciale - 1;
            return 1;
        }
    };
    public int diminuisciDifesaSpeciale(int valore){
        if(this.ripetizioniDifesaSpeciale == -6){
            return -1;
        }else if(this.ripetizioniDifesaSpeciale-valore < -6){
            while(this.ripetizioniDifesaSpeciale > -6){
                this.difesaSpeciale -= 1;
                this.ripetizioniDifesaSpeciale -= 1;
            }
            return 1;
        }else{
            this.difesaSpeciale = this.difesaSpeciale - 1;
            return 1;
        }
    };
    public int diminuisciVelocita(int valore){
        if(this.ripetizioniVelocita == -6){
            return -1;
        }else if(this.ripetizioniVelocita-valore < -6){
            while(this.ripetizioniVelocita > -6){
                this.velocita -= 1;
                this.ripetizioniVelocita -= 1;
            }
            return 1;
        }else{
            this.velocita = this.velocita - 1;
            return 1;
        }
    };

    public void evolvi() throws FileNotFoundException {
        Reader lettore = new Reader();
        try {
            Pokemon evoluzione = lettore.buildPokemonByString(lettore.getRigaByIndex("testo/pokemon.txt",lettore.cercaRiga(this.nomeEvoluzione)));
            this.copia(evoluzione);
        } catch (IOException e) {
            System.err.println("File non formattato correttamente ");
        }
    } // TODO: da implementare, potrebbere restituire un oggetto pokemon o semplicemente modificare il .this che chiama
    public void cambiaMossa(){} //TODO: da implementare, ad ogni livello sblocca una mossa e la sostituisce con un'altra (nel caso in cui gia gli slot siano pieni)

    public int attacca(Pokemon avversario, Mossa mossa){
        int bruttoColpo = Formule.bruttoColpo(this.velocita);

        int danno = Formule.danno(this.tipo1,this.tipo2,avversario.getTipo1(),avversario.getTipo2(),mossa.getTipo(),bruttoColpo,this.lvl,mossa.getPotenza(),this.attacco,avversario.getDifesa());

        avversario.ps-=danno;
        mossa.setPP((mossa.getPP())-1);

        return danno;
    }


    //mossa.getTipo();
    // ogni bottone è specifico per una mossa e
    // richiama il metodo fornendo l'avversario
    // e la mossa (presa dall'array) che verrà eseguita
    public void copia(Pokemon evoluzione){ //copia dopo evoluzione
        this.nome = evoluzione.nome;
        this.psBase = evoluzione.psBase;
        this.esp = evoluzione.esp;
        this.attaccoBase = evoluzione.attaccoBase;
        this.difesaBase = evoluzione.difesaBase;
        this.mosse = evoluzione.mosse;
        this.attaccoSpecialeBase = evoluzione.attaccoSpecialeBase;
        this.difesaSpecialeBase = evoluzione.difesaSpecialeBase;
        this.velocitaBase = evoluzione.velocitaBase;
        this.spriteFront = evoluzione.spriteFront;
        this.spriteBack = evoluzione.spriteBack;
        this.spriteMini = evoluzione.spriteMini;
        this.nomeEvoluzione = evoluzione.nomeEvoluzione;
        this.lvlEvoluzione = evoluzione.lvlEvoluzione;
        this.tipo1 = evoluzione.tipo1;
        this.tipo2 = evoluzione.tipo2;
        //nuove statistiche effettive ancora tocca implementare IV ed EV ma tanto l evoluzione non parte senza quindi tranquillo
        this.setPs(Formule.calcolaHpBase(evoluzione.psBase,lvl,IVps,EVps));
        this.setAttacco(Formule.calcolaStatisticheBase(evoluzione.attaccoBase,lvl,IVattacco,EVattacco));
        this.setDifesa(Formule.calcolaStatisticheBase(evoluzione.difesaBase,lvl,IVdifesa,EVdifesa));
        this.setAttaccoSpeciale(Formule.calcolaStatisticheBase(evoluzione.attaccoSpecialeBase,lvl,IVattaccoSpeciale,EVattaccoSpeciale));
        this.setDifesaSpeciale(Formule.calcolaStatisticheBase(evoluzione.difesaSpecialeBase,lvl,IVdifesaSpeciale,EVdifesaSpeciale));
        this.setVelocita(Formule.calcolaStatisticheBase(evoluzione.velocitaBase,lvl,IVvelocita,EVvelocita));

    }

    @Override
    public String toString() {
        return nome + '-' +
                "-" + tipo1 +
                "-" + tipo2 +
                "-" + lvl +
                "-" + esp +
                "-" + psBase +
                "-" + attaccoBase +
                "-" + difesaBase +
                "-" + attaccoSpecialeBase +
                "-" + difesaSpecialeBase +
                "-" + velocitaBase +
                "-" + spriteFront +
                "-" + spriteBack +
                "-" + spriteMini +
                "-" + lvlEvoluzione +
                "-" + ps +
                "-" + attacco +
                "-" + difesa +
                "-" + attaccoSpeciale +
                "-" + difesaSpeciale +
                "-" + velocita +
                "-" + EV +
                "-" + ripetizioniAttacco +
                "-" + ripetizioniDifesa +
                "-" + ripetizioniAttaccoSpeciale +
                "-" + ripetizioniDifesaSpeciale +
                "-" + ripetizioniVelocita +
                "-" + IVps +
                "-" + IVattacco +
                "-" + IVdifesa +
                "-" + IVattaccoSpeciale +
                "-" + IVdifesaSpeciale +
                "-" + IVvelocita +
                "-" + EVps +
                "-" + EVattacco +
                "-" + EVdifesa +
                "-" + EVattaccoSpeciale +
                "-" + EVdifesaSpeciale +
                "-" + EVvelocita +
                "-" + nomeEvoluzione +
                "-" + salute;
        // da controllare
    }
}

