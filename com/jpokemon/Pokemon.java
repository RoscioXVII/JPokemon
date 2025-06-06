package com.jpokemon;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;


/**
 * Classe descrivente l'entità pokemon, che farà parte della squdra dell'utente
 * e che verrà coinvolta nello svolgimento della Lotta
 */
public class Pokemon implements Cloneable {
    private String nome;
    private Tipo tipo1;
    private Tipo tipo2;
    private int lvl;

    private int esp; // esperienza per l'aumento del livello

    private int XpNecessaria;
    private Mossa[] mosse = new Mossa[4];

    private String[] listaMosse;

    //statistiche base
    private int psBase;
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
    private int EV;
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

    // PRENDE LE MOSSE CHE HA GIA IMPARATO E AUMENTA DI 2 OGNI VOLTA
    private int mosseImparate = 0;

    public void aggiornaMosseImparate(){
        mosseImparate += 2;
    }
    public String getMossaDaImparare(){
        return listaMosse[this.mosseImparate+1];
    }
    public int getIndiceDaImparare(){
        return Integer.parseInt(listaMosse[this.mosseImparate]);
    }

    public void imparaMossa(String mossa, int indice) throws IOException {
        Reader a = Reader.getInstance();
        Mossa ausiliare = a.buildMossaByString(mossa);

        if(indice == 0){
            for(int i = 0; i < 4; i++){
                if(Objects.equals(mosse[i].getNome(), "null")){
                    mosse[i] = ausiliare;
                }
            }
        }else{
            mosse[indice] = ausiliare;
        }

        aggiornaMosseImparate();
    }


    /**
     * Costruttore utilizzato per i pokemon generati dal file di testo 'pokemon.txt' contente le informazioni di default, senza quindi progressi e modifiche
     * @param nome nome pokemon
     * @param tipo1 primo tipo pokemon
     * @param tipo2 secondo tipo pokemon
     * @param lvlEvoluzione livello in cui il pokemon effettua l'evoluzione
     * @param nomeEvoluzione nome del pokemon in cui si evolverà
     * @param ps valori vitali
     * @param esp esperienza per l'aumento di livello
     * @param attacco statistica attacco attacco
     * @param difesa statistica difesa difesa
     * @param attaccoSpeciale statistica attaccospeciale
     * @param difesaSpeciale statistica difesaspeciale
     * @param velocita statistica velocita
     * @param evps statistica evps
     * @param evattacco statistica evattacco
     * @param evdifesa statistica evdifesa
     * @param evattaccoSpeciale statistica evattaccoespeciale
     * @param evdifesaSpeciale statistica evdifesaspeciale
     * @param evvelocita statistica evvelocita
     */
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

    public void setXpNecessaria(int lvl){
        this.XpNecessaria = lvl * lvl * lvl;
    }

    public int getEsp(){
        return this.esp;
    }


    public String getNome(){
        return nome;
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
    public void setListaMosse(String listaMosse){
        this.listaMosse = listaMosse.split(":");
    }




    public void setAttacco(int attacco){this.attacco = attacco;}
    public int getAttacco(){return attacco;}

    public int getLvlEvoluzione(){
        return lvlEvoluzione;
    }

    public void setDifesa(int difesa) {this.difesa = difesa;}
    public int getDifesa(){return difesa;}


    public void setAttaccoSpeciale(int ATS){
        this.attaccoSpeciale = ATS;
    }


    public void setDifesaSpeciale(int DFS){
        this.difesaSpeciale = DFS;
    }
    public int getDifesaSpeciale(){
        return difesaSpeciale;
    }


    public void setVelocita(int velocita){this.velocita = velocita;}
    public int getVelocita(){return velocita;}




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


    /**
     * aumenta l'attacco di un pokemon
     * @param valore : valore dell'attacco di un pokemon
     * @return flag relativo all'aumento
     */
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

    /**
     *  aumenta la difesa di un pokemon
     * @param valore valore di difesa di un pokemon
     * @return flag relativo all'aumento
     */
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
    /**
     * aumenta l'attacco speciale di un pokemon
     * @param valore valore di attaccoSpeciale di un pokemon
     * @return flag relativo all'aumento
     */
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
    /**
     * aumenta la difesa speciale di un pokemon
     * @param valore valore di difesaSpeciale di un pokemon
     * @return flag relativo all'aumento
     */
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
    /**
     *
     * @param valore valore di velocita di un pokemon
     * @return flag relativo all'aumento
     */
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
    /**
     * aumenta la precisione di un pokemon
     * @param valore valore di precisione di un pokemon
     * @return flag relativo all'aumento
     */
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
    /**
     * diminuisce la precisione di un pokemon
     * @param valore valore di precisione di un pokemon
     * @return flag relativo alla diminuzione
     */
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

    /**
     * aumenta l'elusione di un pokemon
     * @param valore : elusione di un pokemon
     * @return flag relativo all'aumento
     */
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

    /**
     *  diminuisce l'elusione di un pokemon
     * @param valore : valore di elusione di un pokemon
     * @return flag relativo alla diminuzione
     */
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

    /**
     * diminuisce l'attacco di un pokemon
     * @param valore : valore di attacco di un pokemon
     * @return flag di aumento
     */
    public int diminuisciAttacco(int valore){
        if(this.ripetizioniAttacco == -6){

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

    /**
     * diminuisce la difesa di un pokemon
     * @param valore : difesa di un pokemon
     * @return flag di diminuzione
     */
    public int diminuisciDifesa(int valore){
        if(this.ripetizioniDifesa == -6){

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

    /**
     * diminuisce l' attacco speciale di un pokemon
     * @param valore : attacco speciale di un pokemon
     * @return flag di diminuzione
     */
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

    /**
     * diminusice la difesa speciale di un pokemon
     * @param valore : difesa speciale di un pokemon
     * @return flag di diminuzione
     */
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

    /**
     * diminuisce la velocita di un pokemon
     * @param valore :velocita di un pokemon
     * @return flag di diminuzione
     */
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

    /**
     * determina la sconfitto di un pokemon, comportando quindi un aumento di alcune statistiche nel pokemon vincitore dello scontro
     * @param sconfitta : pokemon sconfitto nella lotta
     */

    public void sconfitto(Pokemon sconfitta){

        int sommaEV = this.EVps + this.EVattacco + this.EVdifesa + this.EVattaccoSpeciale + this.EVdifesaSpeciale + this.EVvelocita;

        int[] array =  new int[] {this.EVps, this.EVattacco, this.EVdifesa, this.EVattaccoSpeciale, this.EVdifesaSpeciale,this.EVvelocita};
        int[] array2 = new int[] {sconfitta.EVpsYield, sconfitta.EVattaccoYield, sconfitta.EVdifesaYield, sconfitta.EVattaccoSpecialeYield,
                sconfitta.EVdifesaSpecialeYield, sconfitta.EVvelocitaYield};
        int[] array3 = new int[] {0,0,0,0,0,0};

        /*
        510 equivale al massimo di ev che un pokemon puo avere in tutte e 6 le stats:
        252 equivale a quante stats puo avere in una stat in particolare

        esempio:

        ps : 252
        attacco : 0
        difesa : 252
        attaccoSpeciale : 0
        difesaSpeciale : 6
        velocita : 0

        tutti gli ev sono stati messi 252 + 252 + 6 = 510 quindi quando viene sconfitto un altro pokemon i valori sono invariati

         */

        for(int i = 0; i<5;i++){
            if(sommaEV < 510){
                if(array[i] + array2[i] < 252){
                    if(array2[i] + sommaEV < 510){
                        array3[i] = array2[i];
                    }else{
                        int cont = 0;
                        while(sommaEV + cont < 510){
                            cont++;
                        }
                        array3[i] = cont;
                    }
                }else{
                    if(array2[i] + sommaEV < 510){
                        int cont = 0;
                        while(array[i] + cont < 252){
                            cont++;
                        }
                        array3[i] = cont;
                    }else{
                        int cont = 0;
                        while(array[i] + cont < 510){
                            cont++;
                        }
                        array3[i] = cont;
                    }
                }
            }
        }
        this.EVps += array3[0];
        this.EVattacco += array3[1];
        this.EVdifesa += array3[2];
        this.EVattaccoSpeciale += array3[3];
        this.EVdifesaSpeciale += array3[4];
        this.EVvelocita += array3[5];

        int xpPresa = xpGain(sconfitta);

        this.esp += xpPresa;
        if(this.esp >= this.XpNecessaria && this.lvl != 100){
            this.lvl +=1;
            setXpNecessaria(this.lvl);
        }
    }

    /**
     * determina il guadagno di esperienza nel pokemon vincitore di uno scontro
     * @param sconfitta : pokemon sconfitto nella lotta
     * @return esperienza guadagnata dallo scontro
     */
    public int xpGain(Pokemon sconfitta){
        double risultato = (1.5 * sconfitta.getEsp() * sconfitta.getLvl())/7;

        return (int)risultato;
    }

    /**
     * permette a un pokemon di evolversi al raggiungimento del livello necessario indicato nel file di testo 'pokemon.txt'
     */
    public void evolvi()   {
        Reader lettore =  Reader.getInstance();
        try {
            Pokemon evoluzione = lettore.buildPokemonByString(lettore.getRigaByIndex("testo/pokemon.txt",lettore.cercaRiga(this.nomeEvoluzione)));
            this.copia(evoluzione);
        } catch (IOException e) {
            System.err.println("File non formattato correttamente ");
        }
    } // TODO: da implementare, potrebbere restituire un oggetto pokemon o semplicemente modificare il .this che chiama
    public void cambiaMossa(){} //TODO: da implementare, ad ogni livello sblocca una mossa e la sostituisce con un'altra (nel caso in cui gia gli slot siano pieni)


    /**
     * permette di sferrare un attacco verso un pokemon nemico
     * @param avversario : pokemon nemico
     * @param mossa : mossa utilizzata per l'attacco
     * @return valore stimato del danno della mossa utilizzata
     */
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
                "#" + mosse[0].getNome();
        if(mosse[1]==null)
            Stringa+="#null";
        else
            Stringa+="#" + mosse[1].getNome();

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

    /**
     * costruttore utilizzato per i pokemon caricati da un salvataggio di una squadra di un utente precedentemnte definito in memoria su file di testo
     * @param nome : nome pokemon
     * @param tipo1 : primo tipo del pokemon
     * @param tipo2 : secondo tipo del pokemon
     * @param lvl : livello attuale del pokemon
     * @param esp : esperienza fino ad ora guadagnata dal pokemon
     * @param mosse : array di mosse che il pokemon puo utilizzare
     * @param psBase : salute di base
     * @param attaccoBase
     * @param difesaBase
     * @param attaccoSpecialeBase
     * @param difesaSpecialeBase
     * @param velocitaBase
     * @param spriteFront
     * @param spriteBack
     * @param spriteMini
     * @param lvlEvoluzione
     * @param ps
     * @param attacco
     * @param difesa
     * @param attaccoSpeciale
     * @param difesaSpeciale
     * @param velocita
     * @param EV
     * @param ripetizioniAttacco
     * @param ripetizioniDifesa
     * @param ripetizioniAttaccoSpeciale
     * @param ripetizioniDifesaSpeciale
     * @param tipetizioniVelocita
     * @param IVps
     * @param IVattacco
     * @param IVdifesa
     * @param IVattaccoSpeciale
     * @param IVdifesaSpeciale
     * @param IVvelocita
     * @param EVps
     * @param EVattacco
     * @param EVdifesa
     * @param EVvelocita
     * @param EVattaccoSpeciale
     * @param EVdifesaSpeciale
     * @param nomeEvoluzione
     * @param salute : salute attuale
     */
    // questo viene usato solo quando vengono caricati i pokemon dal file di testo, senno per quelli nuovi (non salvati), viene utilizzato l'altro costruttore
    public Pokemon(String nome, Tipo tipo1, Tipo tipo2, int lvl, int esp, Mossa[] mosse, int psBase, int attaccoBase, int difesaBase,
                   int attaccoSpecialeBase, int difesaSpecialeBase, int velocitaBase, String spriteFront, String spriteBack, String spriteMini,
                   int lvlEvoluzione, int ps, int attacco, int difesa, int attaccoSpeciale, int difesaSpeciale, int velocita, int EV, int ripetizioniAttacco,
                   int ripetizioniDifesa, int ripetizioniAttaccoSpeciale, int ripetizioniDifesaSpeciale,int tipetizioniVelocita, int IVps, int IVattacco, int IVdifesa ,int IVattaccoSpeciale,
                   int IVdifesaSpeciale, int IVvelocita, int EVps,
                   int EVattacco, int EVdifesa,int EVvelocita, int EVattaccoSpeciale, int EVdifesaSpeciale, String nomeEvoluzione, int salute,String lista) {
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.lvl = lvl;
        this.setXpNecessaria(lvl);
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
        this.setListaMosse(lista);
        setSalute(ps);
    }


    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
}

