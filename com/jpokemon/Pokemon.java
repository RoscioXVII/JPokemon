package com.jpokemon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class Pokemon implements Cloneable {
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
    private int ripetizioniPrecisione = 0;
    private int ripetizioniElusione = 0;
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

    private int elusione = 3;
    private int elusioneN = 3;
    private int precisione = 3;
    private int precisioneN = 3;

    private int EVpsYield;
    private int EVattaccoYield;
    private int EVdifesaYield;
    private int EVattaccoSpecialeYield;
    private int EVdifesaSpecialeYield;
    private int EVvelocitaYield;


    public Pokemon(String nome, Tipo tipo1,Tipo tipo2, int lvlEvoluzione,String nomeEvoluzione, int ps, int esp,
                   int attacco, int difesa, int attaccoSpeciale, int difesaSpeciale, int velocita,int evps, int evattacco, int evdifesa,
                   int evattaccoSpeciale,int evdifesaSpeciale, int evvelocita){

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

        this.EVpsYield = evps;
        this.EVattaccoYield = evattacco;
        this.EVdifesaYield = evdifesa;
        this.EVattaccoSpecialeYield = evattaccoSpeciale;
        this.EVdifesaSpecialeYield = evdifesaSpeciale;
        this.EVvelocitaYield = evvelocita;
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
        for (Mossa mossa : mosse) {
            if (mossa != null) {
                s += mossa.getNome() + " ";
            } else {
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
    public int getLvlEvoluzione(){
        return lvlEvoluzione;
    }

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

    public int getPrecisione(){
        return precisione;
    }
    public int getPrecisioneN(){
        return precisioneN;
    }
    public int getElusione(){
        return this.ripetizioniElusione;
    }


    public int aumentaAttacco(int valore){
        if(this.ripetizioniAttacco == 6){
            return -1;
        }else if(this.ripetizioniAttacco+valore > 6){
            this.attacco += (6-this.ripetizioniAttacco);
            this.ripetizioniAttacco = 6;
            return 1;
        }else{
            this.attacco = this.attacco + valore;
            this.ripetizioniAttacco += valore;
            return 1;
        }
    }

    public int aumentaDifesa(int valore){
        if(this.ripetizioniDifesa == 6){
            return -1;
        }else if(this.ripetizioniDifesa+valore > 6){
            this.difesa += (6-this.ripetizioniDifesa);
            this.ripetizioniDifesa = 6;
            return 1;
        }else{
            this.difesa = this.difesa + valore;
            this.ripetizioniDifesa += valore;
            return 1;
        }
    }
    public int aumentaAttaccoSpeciale(int valore){
        if(this.ripetizioniAttaccoSpeciale == 6){
            return -1;
        }else if(this.ripetizioniAttaccoSpeciale+valore > 6){
            this.attaccoSpeciale += (6-this.ripetizioniAttaccoSpeciale);
            this.ripetizioniAttaccoSpeciale = 6;
            return 1;

        }else{
            this.attaccoSpeciale = this.attaccoSpeciale + 1;
            this.ripetizioniAttaccoSpeciale += 1;
            return 1;
        }
    }
    public int aumentaDifesaSpeciale(int valore){
        if(this.ripetizioniDifesaSpeciale == 6){
            return -1;
        }else if(this.ripetizioniDifesaSpeciale+valore > 6){
            this.difesaSpeciale += (6-this.ripetizioniDifesaSpeciale);
            this.ripetizioniDifesaSpeciale = 6;
            return 1;
        }else{
            this.difesaSpeciale = this.difesaSpeciale + 1;
            this.ripetizioniDifesaSpeciale += 1;
            return 1;
        }
    }
    public int aumentaVelocita(int valore){
        if(this.ripetizioniVelocita == 6){
            return -1;
        }else if(this.ripetizioniVelocita+valore > 6){
            this.velocita += (6-this.ripetizioniVelocita);
            this.ripetizioniVelocita = 6;
            return 1;
        }else{
            this.velocita = this.velocita + 1;
            this.ripetizioniVelocita += 1;
            return 1;
        }
    }
    public int aumentaPrecisione(int valore){
        if(this.ripetizioniPrecisione == 6){
            return -1;
        }else if(this.ripetizioniPrecisione+valore > 6){
            this.precisione = 9;
            this.ripetizioniPrecisione = 6;
            return 1;
        } else {
            for(int i=0;i<valore;i++){
                if(this.ripetizioniPrecisione<0)
                    this.precisioneN--;
                else
                    this.precisione++;

                this.ripetizioniPrecisione++;
            }
            return 1;
        }
    }
    public int diminuisciPrecisione(int valore){
        if(this.ripetizioniPrecisione == -6){
            return -1;
        }else if(this.ripetizioniPrecisione-valore >-6){
            this.precisioneN = 9;
            this.ripetizioniPrecisione = -6;
            return 1;
        }else{
            for(int i=0;i<valore;i++){
                if(this.ripetizioniPrecisione<0)
                    this.precisioneN++;
                else
                    this.precisione--;


                this.ripetizioniPrecisione--;
            }
            return 1;
        }
    }
    public int aumentaElusione(int valore){
        if(this.ripetizioniElusione == 6){
            return -1;
        }else if(this.ripetizioniElusione+valore > 6){
            this.elusione = 9;
            this.ripetizioniElusione = 6;
            return 1;
        }else{
            for(int i=0;i<valore;i++){
                if(this.ripetizioniElusione<0)
                    this.elusioneN--;

                else
                    this.elusione++;


                this.ripetizioniElusione++;
            }
            return 1;
        }
    }
    public int diminuisciElusione(int valore){
        if(this.ripetizioniElusione == -6){
            return -1;
        }else if(this.ripetizioniElusione-valore >-6){
            this.elusioneN = 9;
            this.ripetizioniElusione = -6;
            return 1;
        }else{
            for(int i=0;i<valore;i++){
                if(this.ripetizioniElusione<0)
                    this.elusioneN++;
                else
                    this.elusione--;

                this.ripetizioniElusione--;
            }
            return 1;
        }

    }


    public int diminuisciAttacco(int valore){
        if(this.ripetizioniAttacco == -6){
            System.out.println("Attacco al minimo");
            return -1;
        }else if(this.ripetizioniAttacco-valore < -6){
            this.attacco -= (6+this.ripetizioniAttacco);
            this.ripetizioniAttacco = -6;
            return 1;
        }else{
            this.attacco = this.attacco - 1;
            this.ripetizioniAttacco -= 1;
            return 1;
        }
    }
    public int diminuisciDifesa(int valore){
        if(this.ripetizioniDifesa == -6){
            System.out.println("Difesa al minimo");
            return -1;
        }else if(this.ripetizioniDifesa-valore < -6){
            this.difesa -= (6+this.ripetizioniDifesa);
            this.ripetizioniDifesa = -6;
            return 1;
        }else{
            this.difesa = this.difesa - 1;
            this.ripetizioniDifesa -= 1;
            return 1;
        }
    }
    public int diminuisciAttaccoSpeciale(int valore){
        if(this.ripetizioniAttaccoSpeciale == -6){
            return -1;
        }else if(this.ripetizioniAttaccoSpeciale-valore < -6){
            this.attaccoSpeciale -= (6+this.ripetizioniAttaccoSpeciale);
            this.ripetizioniAttaccoSpeciale = -6;
            return 1;
        }else{
            this.attaccoSpeciale = this.attaccoSpeciale - 1;
            this.ripetizioniAttaccoSpeciale -= 1;
            return 1;
        }
    }
    public int diminuisciDifesaSpeciale(int valore){
        if(this.ripetizioniDifesaSpeciale == -6){
            return -1;
        }else if(this.ripetizioniDifesaSpeciale-valore < -6){
            this.difesaSpeciale -= (6+this.ripetizioniDifesaSpeciale);
            this.ripetizioniDifesaSpeciale = -6;
            return 1;
        }else{
            this.difesaSpeciale = this.difesaSpeciale - 1;
            this.ripetizioniDifesaSpeciale -= 1;
            return 1;
        }
    }
    public int diminuisciVelocita(int valore){
        if(this.ripetizioniVelocita == -6){
            return -1;
        }else if(this.ripetizioniVelocita-valore < -6){
            this.velocita -= (6+this.ripetizioniVelocita);
            this.ripetizioniVelocita = -6;
            return 1;
        }else{
            this.velocita = this.velocita - 1;
            this.ripetizioniVelocita -= 1;
            return 1;
        }
    }

    public void sconfitto(Pokemon sconfitta){
        this.EVps += sconfitta.EVpsYield;
        this.EVattacco += sconfitta.EVattaccoYield;
        this.EVdifesa += sconfitta.EVdifesaYield;
        this.EVattaccoSpeciale += sconfitta.EVattaccoSpecialeYield;
        this.EVdifesaSpeciale += sconfitta.EVdifesaSpecialeYield;
        this.EVvelocita += sconfitta.EVvelocitaYield;

        int xpPresa = xpGain(sconfitta);
        // gestire xp per capire quando scatta il lvl successivo
        //this.esp +=xpPresa;

    }

    public int xpGain(Pokemon sconfitta){
        return 0;
    }

    public void evolvi()   {
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

        Random roll = new Random();

        int danno;
        double risultato;

        int prec = this.getPrecisione();
        int precN = this.getPrecisioneN();
        int elusione = avversario.getElusione();

        if(elusione <= 0){
            risultato = (((double) (prec + elusione) /precN));
        }else{
            risultato = (((double) (prec) /precN+elusione));
        }

        if(roll.nextInt(100) > (int)risultato*mossa.getPrecisione()){
            return 0;
        }

        if(mossa.getTipoMossa() == TipoMossa.FISICO){
            danno = Formule.danno(this.tipo1,this.tipo2,avversario.getTipo1(),avversario.getTipo2(),mossa.getTipo(),this.lvl,mossa.getPotenza(),this.attacco,avversario.getDifesa(),this.getVelocita());
        } else if (mossa.getTipoMossa() == TipoMossa.SPECIALE) {
            danno = Formule.danno(this.tipo1,this.tipo2,avversario.getTipo1(),avversario.getTipo2(),mossa.getTipo(),this.lvl,mossa.getPotenza(),this.attaccoSpeciale,avversario.getDifesaSpeciale(),this.getVelocita());
        } else {
            danno = 0;
        }

        avversario.salute-=danno;
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
        this.EVpsYield = evoluzione.EVpsYield;
        this.EVattaccoYield = evoluzione.EVattaccoYield;
        this.EVdifesaYield = evoluzione.EVdifesaYield;
        this.EVattaccoSpecialeYield = evoluzione.EVattaccoSpecialeYield;
        this.EVdifesaSpecialeYield = evoluzione.EVdifesaSpecialeYield;
        this.EVvelocitaYield = evoluzione.EVvelocitaYield;
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
        String Stringa = nome +
                "#" + tipo1 +
                "#" + tipo2 +
                "#" + lvl +
                "#" + esp +
                "#" + psBase +
                "#" + attaccoBase +
                "#" + difesaBase +
                "#" + attaccoSpecialeBase +
                "#" + difesaSpecialeBase +
                "#" + velocitaBase +
                "#" + spriteFront +
                "#" + spriteBack +
                "#" + spriteMini +
                "#" + lvlEvoluzione +
                "#" + ps +
                "#" + attacco +
                "#" + difesa +
                "#" + attaccoSpeciale +
                "#" + difesaSpeciale +
                "#" + velocita +
                "#" + EV +
                "#" + ripetizioniAttacco +
                "#" + ripetizioniDifesa +
                "#" + ripetizioniAttaccoSpeciale +
                "#" + ripetizioniDifesaSpeciale +
                "#" + ripetizioniVelocita +
                "#" + IVps +
                "#" + IVattacco +
                "#" + IVdifesa +
                "#" + IVattaccoSpeciale +
                "#" + IVdifesaSpeciale +
                "#" + IVvelocita +
                "#" + EVps +
                "#" + EVattacco +
                "#" + EVdifesa +
                "#" + EVattaccoSpeciale +
                "#" + EVdifesaSpeciale +
                "#" + EVvelocita +
                "#" + nomeEvoluzione +
                "#" + salute +
                "#" + mosse[0].getNome() + // implementare if che se e null restituisce null
                "#" + mosse[1].getNome();
        if(mosse[2]==null)
            Stringa+="#null";
        else
            Stringa+="#" + mosse[2].getNome();

        if(mosse[3]==null)
            Stringa+="#null";
        else
            Stringa+="#" + mosse[3].getNome();

        return Stringa;
    }

    // questo viene usato solo quando vengono caricati i pokemon dal file di testo, senno per quelli nuovi (non salvati), viene utilizzato l'altro costruttore
    public Pokemon(String nome, Tipo tipo1, Tipo tipo2, int lvl, int esp, Mossa[] mosse, int psBase, int attaccoBase, int difesaBase,
                   int attaccoSpecialeBase, int difesaSpecialeBase, int velocitaBase, String spriteFront, String spriteBack, String spriteMini,
                   int lvlEvoluzione, int ps, int attacco, int difesa, int attaccoSpeciale, int difesaSpeciale, int velocita, int EV, int ripetizioniAttacco,
                   int ripetizioniDifesa, int ripetizioniAttaccoSpeciale, int ripetizioniDifesaSpeciale, int IVps, int IVattacco, int IVdifesa, int IVattaccoSpeciale,
                   int IVdifesaSpeciale, int IVvelocita, int EVps,
                   int EVattacco, int EVdifesa, int EVattaccoSpeciale, int EVdifesaSpeciale, int EVvelocita, String nomeEvoluzione, int salute) {
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.lvl = lvl;
        this.esp = esp;
        this.mosse = mosse;
        this.psBase = psBase;
        this.attaccoBase = attaccoBase;
        this.difesaBase = difesaBase;
        this.attaccoSpecialeBase = attaccoSpecialeBase;
        this.difesaSpecialeBase = difesaSpecialeBase;
        this.velocitaBase = velocitaBase;
        this.spriteFront = spriteFront;
        this.spriteBack = spriteBack;
        this.spriteMini = spriteMini;
        this.lvlEvoluzione = lvlEvoluzione;
        this.ps = ps;
        this.attacco = attacco;
        this.difesa = difesa;
        this.attaccoSpeciale = attaccoSpeciale;
        this.difesaSpeciale = difesaSpeciale;
        this.velocita = velocita;
        this.EV = EV;
        this.ripetizioniAttacco = ripetizioniAttacco;
        this.ripetizioniDifesa = ripetizioniDifesa;
        this.ripetizioniAttaccoSpeciale = ripetizioniAttaccoSpeciale;
        this.ripetizioniDifesaSpeciale = ripetizioniDifesaSpeciale;
        this.IVps = IVps;
        this.IVattacco = IVattacco;
        this.IVdifesa = IVdifesa;
        this.IVattaccoSpeciale = IVattaccoSpeciale;
        this.IVdifesaSpeciale = IVdifesaSpeciale;
        this.IVvelocita = IVvelocita;
        this.EVps = EVps;
        this.EVattacco = EVattacco;
        this.EVdifesa = EVdifesa;
        this.EVattaccoSpeciale = EVattaccoSpeciale;
        this.EVdifesaSpeciale = EVdifesaSpeciale;
        this.EVvelocita = EVvelocita;
        this.nomeEvoluzione = nomeEvoluzione;
        this.salute = salute;


    }

    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            // Questo non dovrebbe mai succedere, perché implementiamo Cloneable
            throw new RuntimeException(e);
        }

    }
}

