package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;


public class Lotta extends JFrame {
    private Pokemon[] squadra; //= new Pokemon[6]; // ha dimensione fissa = 6, posso usare anche un array semplice
    private Pokemon[] squadra2; //= new Pokemon[6]; // squadra dell'avversario
    private int mossa = -10;
    private int numTurno = 0; // non la usiamo mai ao
    private int turno = 0;
    //devo fare un metodo grafico per selezionarli

    // Al posto di MOSSA metterò il nome della vera mossa ricavato dall'istanza
    // mettero tutto a static
    private final JButton attacca = new JButton("Attacca");
    private final JButton pokemon = new JButton("Pokemon");

    private JButton mossa1;
    private JButton mossa2;
    private JButton mossa3;
    private JButton mossa4;

    private JButton pokemon1;
    private JButton pokemon2;
    private JButton pokemon3 = new JButton("POKEMON 3");
    private JButton pokemon4 = new JButton("POKEMON 4");
    private JButton pokemon5 = new JButton("POKEMON 5");
    private JButton pokemon6 = new JButton("POKEMON 6");

    private JButton indietro = new JButton("INDIETRO");
    private JPanel pannello;
    private JLabel nomePok1;
    private JLabel nomePok2;
    private BarraPS barraPSpok1;
    private BarraPS barraPSpok2;
    private JLabel labelgif1;
    private JLabel PsPok1;
    private JLabel PsPok2;
    private JLabel labelgif2;
    private JLabel utente;
    private boolean cambioUtente;
    private int  vittorieUtente1;
    private int vittorieUtente2;
    private Pokemon[] squadraUtente1 = new Pokemon[6];

    private Pokemon[] squadraUtente2 = new Pokemon[6];
    private Utente utente1; // da implementare
    private Utente utente2; // da implementare, fare le squadre random ecc, è stata solo implementata la scrittura delle vincite su file di testo

    // viene passato dalla schermata precedente, in base al tasto cliccato viene deciso quale utente utilizzare e questo viene caricato da memoria 

    // non deve essere una nuova finestra ma una card che viene selezionata dopo lo start

    // la mossa 3 e 4 viene aggiunta successivamente se il pokemon le ha, in caso contrario il bottone non sarà cliccabile
    public Lotta(int numeroUtente) throws IOException {

        //COMMENTO OPZIONI VISUALIZZAZIONE A FINESTRA
        //super("JPokemon");
        //setLayout(null);
        //setSize(1920,1080); // dimensioni finestra
        //setLocationRelativeTo(null);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PARTE AGGIUNTA POKEMON

        Reader provaLettore = new Reader();
        //Random rnd = new Random();
        //utente1 = provaLettore.buildUtentebyString(provaLettore.getRigaByIndex("testo/utenti.txt", numeroUtente));
        //utente2 = provaLettore.buildUtentebyString(provaLettore.getRigaByIndex("testo/utenti.txt", rnd.nextInt(4)));


        Pokemon prova=provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",4));
        // dovrei inizializzare i due utenti (vengono creati con delle squadre random)
        //squadra2[0] = prova;
        //squadra2[1] = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));
        squadraUtente2[0] = prova;
        squadraUtente2[1] = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));
        //squadraUtente1 = Utente1.getSquadra();
        //squadraUtente2 = Utente2.getSquadra();
        // UTENTE1 DEVE ESSERE QUELLO SELEZIONATO DAL BOTTONE
        // UTENTE2 O VIENE CREATO RANDOM O VIENE SELEZIONATO RANDOM DAGLI UTENTI SALVATI SUL FILE (PIU SENSATO)

        Pokemon contro = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));
        Pokemon squad2 = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",0));
        //squadraUtente1 = utente1.getSquadra();
        //squadraUtente1[0] = contro;
        //squadraUtente1[1] = squad2;
        //squadra[0] = contro;
        //squadra[1] = squad2;

        // PROVA
        squadra = clonaSquadra(squadraUtente1);
        squadra2 = clonaSquadra(squadraUtente2);
        //squadra = squadraUtente1.clone();
        //squadra2 = squadraUtente2.clone();


        // quando finisco la lotta ripristino tutto da squadraUtente1 e 2 cosi riprendo da 0





        pannello = new JPanel(null);
        ImageIcon fondoLotta = new ImageIcon("img/lotta.png");
        Image img = fondoLotta.getImage().getScaledInstance(1280,400,Image.SCALE_SMOOTH);
        fondoLotta = new ImageIcon(img);
        JLabel label = new JLabel(fondoLotta);
        label.setSize(1280, 400); // parte per l'aggiunta delle gif
        label.setLocation(0, 0);
        pannello.add(label);
        //pannello.setLayout(new BorderLayout());

        // prova GIF -- carico la gif -->
        ImageIcon gif1 = new ImageIcon(squadra2[0].getSpriteFront());
        ImageIcon gif2 = new ImageIcon(squadra[0].getSpriteBack());  //ImageIcon gif2 = new ImageIcon("img/retro/charizard-retro.gif");

        Mossa[] test;
        test = squadra[0].getMosse();

        mossa1 = new JButton(test[0].getNome()); //un pokemon ha sempre due mosse minimo
        mossa2 = new JButton(test[1].getNome());
        if(test[2] == null)
            mossa3 = new JButton("vuoto"); //TODO: Implementare if per i bottoni senza mosse
        else
            mossa3 = new JButton(test[2].getNome());

        if(test[3]==null)
            mossa4 = new JButton("vuoto");
        else
            mossa4 = new JButton(test[3].getNome());



        pokemon1 = new JButton(squadra[0].getNome(),new ImageIcon(squadra[0].getSpriteMini()));
        pokemon2 = new JButton(squadra[1].getNome(), new ImageIcon(squadra[1].getSpriteMini()));
        // il resto lo prendo (anche questo in realtà) dall'array squadra
        // da qui posso scalare la dimensione --> (getScaledInstance(400,400,Image.SCALE_DEFAULT))
        Image img1 = gif1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        Image img2 = gif2.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        ImageIcon scaledGif1 = new ImageIcon(img1);
        ImageIcon scaledGif2 = new ImageIcon(img2);

        // creo un altro JLabel contenente la gif scalata --> DIMENSIONI MAGGIORATE

        labelgif1 = new JLabel(scaledGif1);
        labelgif2 = new JLabel(scaledGif2);

        // il label sarà grande quanto le gif stesse
        labelgif1.setSize(150,150);//(gif1.getIconWidth(),gif1.getIconHeight()); //SONO LE DIMENSIONI STANDARD
        labelgif2.setSize(150,150);//(gif2.getIconWidth(),gif2.getIconHeight());

        //posizionamento gif
        labelgif1.setLocation(880,100);
        labelgif2.setLocation(240,250);

        pannello.add(labelgif1); // posso specificare il borderlayout (area in cui va a posizionarsi)
        pannello.add(labelgif2); // aggiungo solo il label e successivamente lo posiziono
//SET SIZE
        attacca.setSize(200,100);
        pokemon.setSize(200,100);

        mossa1.setSize(200,100);
        mossa2.setSize(200,100);
        mossa3.setSize(200,100);
        mossa4.setSize(200,100);

        pokemon1.setSize(200,100);
        pokemon2.setSize(200,100);
        pokemon3.setSize(200,100);
        pokemon4.setSize(200,100);
        pokemon5.setSize(200,100);
        pokemon6.setSize(200,100);
// SET LOCATION
        attacca.setLocation(0,400);
        pokemon.setLocation(200,400);

        mossa1.setLocation(0,400);
        mossa2.setLocation(200,400);
        mossa3.setLocation(0,500);
        mossa4.setLocation(200,500);

        pokemon1.setLocation(0,400);
        pokemon2.setLocation(200,400);
        pokemon3.setLocation(0,500);
        pokemon4.setLocation(200,500);
        pokemon5.setLocation(0,600);
        pokemon6.setLocation(200,600);

        //BOTTONE INDIETRO -- DA SISTEMARE
        indietro.setSize(200,300);
        indietro.setVisible(true);
        indietro.setLocation(400,400);

        pannello.add(indietro);

        pannello.add(pokemon,BorderLayout.SOUTH);
        pannello.add(attacca,BorderLayout.SOUTH);

        pannello.add(mossa1,BorderLayout.SOUTH);
        pannello.add(mossa2,BorderLayout.SOUTH);
        pannello.add(mossa3,BorderLayout.SOUTH);
        pannello.add(mossa4,BorderLayout.SOUTH);

        pannello.add(pokemon1,BorderLayout.SOUTH);
        pannello.add(pokemon2,BorderLayout.SOUTH);
        pannello.add(pokemon3,BorderLayout.SOUTH);
        pannello.add(pokemon4,BorderLayout.SOUTH);
        pannello.add(pokemon5,BorderLayout.SOUTH);
        pannello.add(pokemon6,BorderLayout.SOUTH);

        vistaMain();


        attacca.addActionListener(e -> vistaMosse());
        pokemon.addActionListener(e ->vistaPokemon());
        indietro.addActionListener(x ->vistaMain());

        setFocusable(true);
        requestFocus();     // SENZA QUESTI I CONTROLLI NON PARTONO

        //LOCALE
        nomePok1 = new JLabel(squadra[0].getNome()); //sostituire con pokemon.getNome()
        nomePok1.setBounds(930,320,100,20);
        barraPSpok1 = new BarraPS(squadra[0].getPs()); //salute dovrà riferirsi ai PS del pokemon principale coinvolto nella lotta
        barraPSpok1.getBarraSalute().setLocation(930,335); //PS del pokemonLocale
        barraPSpok1.getBarraSalute().setSize(310,20); // DA LEVARE
        PsPok1 = new JLabel(squadra[0].getPs() + "/" + squadra[0].getPs()); // sostituisco con ps (della classe pokemon) e vita (che ottengo dalla barra)
        PsPok1.setBounds(930,355,100,20);
        //AVVERSARI
        nomePok2 = new JLabel(squadra2[0].getNome());
        nomePok2.setBounds(55,55,100,20);
        barraPSpok2 = new BarraPS(squadra2[0].getPs());
        barraPSpok2.getBarraSalute().setLocation(55,70); //PS del pokemonAvversario - potrei usare anche qui il setBounds
        barraPSpok2.getBarraSalute().setSize(310,20); // DA LEVARE
        PsPok2 = new JLabel(squadra2[0].getPs()+"/"+squadra2[0].getPs()); // devo aggiornare questo valore
        PsPok2.setBounds(55,90,100,20);
        utente = new JLabel("UTENTE 1 ");
        utente.setBounds(605,320,200,200);
        pannello.add(utente);
        pannello.add(barraPSpok1.getBarraSalute());
        pannello.add(barraPSpok2.getBarraSalute());
        pannello.add(nomePok1);
        pannello.add(nomePok2);
        pannello.add(PsPok1);
        pannello.add(PsPok2);

        pannello.add(label,BorderLayout.NORTH);
        setContentPane(pannello); // TODO

        //QUESTO VA CAMBIATO MA DOPO
        Reader AssegnaMosse = new Reader();

        Mossa[] finalTest = test; // mi serve final, poi lo tolgo --si riferisce solo all utente 1
        Mossa[] finalTest2 = squadra2[0].getMosse(); // provvisorio

        cambioUtente=false;
        //Mossa Mossapokemon1 = null; // SE LA MOSSA VALE "null" allora ha priorità sulle altre perché non fa danno al pokemon avversario (ad esempio cambiare pokemon ha la priorità)
        //Mossa Mossapokemon2 = null;
        mossa1.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(0);
                cambiaContesto();
            }
            else{
                // index -10 out of bounds for length 4 (da rivedere, sarebbe il valore restituito dal cambio pokemon)
                // implementare if in piu, se ho -10 restituisco null, se ho null dentro turno cambio pokemon e non faccio nulla
                setTurno(turno(finalTest[getMossa()], finalTest2[0]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        //squadra2[0].sconfitto(squadra[0]);
                        cambioUtente=false;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        //squadra[0].sconfitto(squadra2[0]);
                        cambioUtente=true;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

            //IL PRIMO getPs prende la salute attuale, il secondo prende la salute MASSIMA, va fatta la cosa del clone
        });                                                //PER TESTARE DICHIARO UNA FUNZIONE CHE A PRIORI PRENDE LA VITA MA NON LA FACCIAMO COSI
        mossa2.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(1);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[1]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        cambioUtente=true;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

        });

        mossa3.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(2);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[2]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex); // da rivedere, al massimo stampare un messaggio d'errore
                        }
                    }else{
                        cambioUtente=true;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

        });

        mossa4.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(3);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[3]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }else{
                        cambioUtente=true;
                        try {
                            PreCambiaPokemon();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

        });
        // cambio UI per il secondo utente
        pokemon2.addActionListener(e->cambiaPokemon(1));
        pokemon3.addActionListener(e->cambiaPokemon(2));
        pokemon4.addActionListener(e->cambiaPokemon(3));
        pokemon5.addActionListener(e->cambiaPokemon(4));
        pokemon6.addActionListener(e->cambiaPokemon(5));

    }

    public void setMossa(int numero){
        this.mossa = numero;
    }
    public int getMossa(){
        return mossa;
    }
    public void setTurno(int numero){
        this.turno = numero;
    }
    public int getTurno(){
        return turno;
    }

    public int turno(Mossa Mossapokemon1, Mossa Mossapokemon2){
        // la velocita e del pokemon non della mossa
        int danno, CondEffetto1, CondEffetto2,effetti; //effetti non viene mai usata, da togliere
        String effetto1, effetto2 = null; // mai usati
        Boolean turno1, turno2 = false; // mai usati

        //SE IL POKEMON VIENE CAMBIATO COL TASTO FACCIAMO CHE LA MOSSA VIENE ISTANZIATA NULL
        //QUINDI POSSIAMO GESTIRE IL CAMBIO POKEMON FUORI DAL TURNO

        CondEffetto1 = Effetti.Effetto(Mossapokemon1.getNome());
        CondEffetto2 = Effetti.Effetto(Mossapokemon2.getNome());

        //GESTIONE CASI CondEffetto = 1
        // ESSENDOCI SOLO ATTACCO RAPIDO EFFETTIVAMENTE COME EFFETTO 1 allora posso gestirla in poche righe
        // FARE IF IN CUI SE UNA DELLE DUE MOSSE E NULL (CASO IN CUI VIENE CAMBIATO POKEMON) CHI NON CAMBIA MA SEMPRE LA MOSSA (SOLO LUI)
        if(CondEffetto1==-10 && CondEffetto2!=-10){ // SIMO CONTROLLA STA ROBA
            danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
            barraPSpok1.diminuisci(danno);
            PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
            if (squadra[0].getSalute() <= 0){
                return -1;
            }
        }
        if(CondEffetto2==-10 && CondEffetto1!=-10){ // SIMO CONTROLLA STA ROBA
            barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
            PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
            barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
            if(squadra2[0].getSalute() <= 0){
                return -1;
            }

        }
        if(CondEffetto1 == 1 && CondEffetto2 != 1){
            // squadra poi squadra 2
            barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
            PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
            barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
            if(squadra2[0].getSalute() <= 0){
                return -1;
            }
            if (CondEffetto2 == 3){
                danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                barraPSpok1.diminuisci(danno);
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                Effetti.attivaEffettoDopo(squadra2[0],squadra[0],Mossapokemon2,danno);
                return 0;

            }else if (CondEffetto2 ==2) {

                danno = Effetti.attivaEffettoDurante(squadra2[0], squadra[0], Mossapokemon2);
                barraPSpok1.diminuisci(danno);
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }
        } else if (CondEffetto2 == 1 && CondEffetto1 != 1) {
            // squadra 2 poi squadra
            barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
            PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
            if (squadra[0].getSalute() <= 0){
                return -1;
            }
            if (CondEffetto1 == 3){
                danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                barraPSpok2.diminuisci(danno);
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                Effetti.attivaEffettoDopo(squadra[0],squadra2[0],Mossapokemon1,danno);
                return 0;
            }else if (CondEffetto1==2){
                danno = Effetti.attivaEffettoDurante(squadra[0],squadra2[0], Mossapokemon1);
                barraPSpok2.diminuisci(danno);
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }
        }else if(CondEffetto2 == 1 && CondEffetto1 == 1){
            if(squadra[0].getVelocita() > squadra2[0].getVelocita()){
                //attacca squadra poi squadra2
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                //Attacca Squadra2 poi squadra
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }
        } else if (CondEffetto2 == -1 && CondEffetto1 == -1) {
            if(squadra[0].getVelocita() > squadra2[0].getVelocita()){
                //attacca squadra poi squadra2
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                //Attacca Squadra2 poi squadra
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }
        }else{
            if(squadra[0].getVelocita() > squadra2[0].getVelocita()){
                if(CondEffetto1 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra[0],squadra2[0],Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra[0],squadra2[0],Mossapokemon1,danno);
                }
                if(CondEffetto2 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra2[0], squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra2[0],squadra[0],Mossapokemon2,danno);
                }
                return 0;
            }else{
                if(CondEffetto2 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra2[0], squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra2[0],squadra[0],Mossapokemon2,danno);
                }

                if(CondEffetto1 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra[0],squadra2[0],Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra[0],squadra2[0],Mossapokemon1,danno);
                }
                return 0;
            }

            //qua non puo arrivare
        }
    }

    // diamo per scontato che il pokemon numero 1 in squadra sia quello coinvolto in lotta
    public JPanel getPannello(){
        return pannello;
    }
    public void PreCambiaPokemon() throws IOException {
        int possibiliCambi = 0;
        int[] indiceCambi = new int[6];

        if(!cambioUtente){
            for(int i=0;i<6;i++){
                if(squadra[i] != null){
                    if(squadra[i].getSalute() > 0){
                        indiceCambi[possibiliCambi] = i;
                        possibiliCambi++;
                    }
                }
            }
        }else{
            for(int i=0;i<6;i++){
                if(squadra2[i] != null){
                    if(squadra2[i].getSalute() > 0){
                        indiceCambi[possibiliCambi] = i;
                        possibiliCambi++;
                    }
                }
            }
        }
        if(possibiliCambi == 0){
            if(!cambioUtente){
                pokemon1.setEnabled(false); // quindi anche le mosse
                vittorieUtente2++;
                // devo passarci il flag di vittoria

                Vittoria frameVittoria = new Vittoria(cambioUtente); // è una finestra in piu, non la stessa modificata
                //salvo i progressi sui file
                //utente1.scrittoreModifica();
                //utente2.scrittoreModifica();
                resettaLotta();
                checkBattaglia();


                // vuol dire che ha vinto il giocatore 2, perche 1 sta senza pokemon
            }
            else{
                pokemon1.setEnabled(false); // quindi anche le mosse
                vittorieUtente1++;
                Vittoria frameVittoria = new Vittoria(cambioUtente);
                //utente1.scrittoreModifica();
                //utente2.scrittoreModifica();
                resettaLotta();
                checkBattaglia();

            }

            //System.exit(1);
        }else{
            if(!cambioUtente){
                squadra[0].sconfitto(squadra2[0]);
                if(squadra[0].getLvl() == squadra[0].getLvlEvoluzione()){
                    squadra[0].evolvi();
                    aggiornaUI();
                }
                //cambiaPokemon(indiceCambi[0]);

            }
            else{
                squadra2[0].sconfitto(squadra[0]);
                if(squadra2[0].getLvl() == squadra2[0].getLvlEvoluzione()){
                    squadra2[0].evolvi();
                    aggiornaUI();
                }
                //cambiaPokemon(indiceCambi[0]);
            }

            cambiaPokemon(indiceCambi[0]);

            }

    }

    public void cambiaPokemon(int indice){// quello contenuto nel bottone, viene ritornato dall actionlistener
        if(!cambioUtente) {
            Pokemon cambio = squadra[indice]; // identifico il pokemon che subentrera nella lotta
            squadra[indice] = squadra[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra[0] = cambio;
            if (squadra[indice].getSalute() <= 0) {
                // rendo il bottone non cliccabile
                switch (indice) {
                    case 0: pokemon1.setEnabled(false); // devo bloccare anche le mosse
                    case 1: pokemon2.setEnabled(false);
                    case 2: pokemon3.setEnabled(false);
                    case 3: pokemon4.setEnabled(false);
                    case 4: pokemon5.setEnabled(false);
                    case 5: pokemon6.setEnabled(false);
                }
            }
        }
        else{
            Pokemon cambio = squadra2[indice]; // identifico il pokemon che subentrera nella lotta
            squadra2[indice] = squadra2[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra2[0]=cambio;
            if(squadra2[indice].getSalute()<=0){
                // rendo il bottone non cliccabile in caso di pokemon esausto
                switch (indice){
                    case 0: pokemon1.setEnabled(false);
                    case 1: pokemon2.setEnabled(false);
                    case 2: pokemon3.setEnabled(false);
                    case 3: pokemon4.setEnabled(false);
                    case 4: pokemon5.setEnabled(false);
                    case 5: pokemon6.setEnabled(false);

                }

            }
        }

        aggiornaUI();
    }


    private void aggiornaUI(){
        // devo usare il metodo anche per quando vengono effettuate delle evoluzioni
            nomePok1.setText(squadra[0].getNome());
            barraPSpok1.getBarraSalute().setMaximum(squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
            PsPok1.setText(squadra[0].getSalute() + "/" + squadra[0].getPs());
            // Aggiorna le GIF

            ImageIcon img = new ImageIcon(squadra[0].getSpriteBack());
            Image scaledGif = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            labelgif2.setIcon(new ImageIcon(scaledGif));

            nomePok2.setText(squadra2[0].getNome());
            barraPSpok2.getBarraSalute().setMaximum(squadra2[0].getPs());
            barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
            PsPok2.setText(squadra2[0].getSalute() + "/" + squadra2[0].getPs());

            // Aggiorna le GIF
            img = new ImageIcon(squadra2[0].getSpriteFront());
            scaledGif = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            labelgif1.setIcon(new ImageIcon(scaledGif));

        cambiaContesto();

    }


    private void vistaMosse(){
        attacca.setVisible(false);
        pokemon.setVisible(false);

        mossa1.setVisible(true);
        mossa2.setVisible(true);
        mossa3.setVisible(true);
        mossa4.setVisible(true);
        indietro.setVisible(true);
    }
    private void vistaPokemon(){
        attacca.setVisible(false);
        pokemon.setVisible(false);

        pokemon1.setVisible(true);
        pokemon2.setVisible(true);
        pokemon3.setVisible(true);
        pokemon4.setVisible(true);
        pokemon5.setVisible(true);
        pokemon6.setVisible(true);
        indietro.setVisible(true);
    }

    private void vistaMain(){
        attacca.setVisible(true);
        pokemon.setVisible(true);
        mossa1.setVisible(false);
        mossa2.setVisible(false);
        mossa3.setVisible(false);
        mossa4.setVisible(false);
        pokemon1.setVisible(false);
        pokemon2.setVisible(false);
        pokemon3.setVisible(false);
        pokemon4.setVisible(false);
        pokemon5.setVisible(false);
        pokemon6.setVisible(false);
        indietro.setVisible(false);
    }

    private void cambiaContesto(){
        // sarebbe la funz utile per lo scambio utente
        Mossa[] mosse;
        Pokemon[] squad;
        // Aggiorna le mosse
        if(!cambioUtente){
            mosse = squadra2[0].getMosse();
            squad = squadra2;
            utente.setText("UTENTE 2"); // utente.getNome()
        }

        else{
            mosse = squadra[0].getMosse();
            squad = squadra;
            utente.setText("UTENTE 1"); // utente.getNome()
        }


        mossa1.setText(mosse[0].getNome());
        mossa2.setText(mosse[1].getNome());
        mossa3.setText(mosse[2] != null ? mosse[2].getNome() : "vuoto");
        mossa4.setText(mosse[3] != null ? mosse[3].getNome() : "vuoto");
        pokemon1.setText(squad[0].getNome());
        pokemon1.setIcon(new ImageIcon(squad[0].getSpriteMini()));
        pokemon2.setText(squad[1].getNome());
        pokemon2.setIcon(new ImageIcon(squad[1].getSpriteMini()));
        // QUANDO AVRO LE SQUADRE COMPLETE CON GLI UTENTI POTRO TOGLIERE I COMMENTI ALLE ISTR. QUA SOTTO
        //pokemon3.setText(squad[2].getNome());
        //pokemon3.setIcon(new ImageIcon(squad[2].getSpriteMini()));
        //pokemon4.setText(squad[3].getNome());
        //pokemon4.setIcon(new ImageIcon(squad[3].getSpriteMini()));
        //pokemon5.setText(squad[4].getNome());
        //pokemon5.setIcon(new ImageIcon(squad[4].getSpriteMini()));
        //pokemon6.setText(squad[5].getNome());
        //pokemon6.setIcon(new ImageIcon(squad[5].getSpriteMini()));
        cambioUtente=!cambioUtente;

         // il contrario, quindi l'altro utente
        // il cambio contesto si ha quando viene cambiato pokemon
        // per il resto viene fatto un if sulla velocita delle mosse
        // quindi per i casi normali è da rivedere

    }
    public void checkBattaglia() throws IOException {


        if(vittorieUtente1-vittorieUtente2>1){
            // resetto le vittorie e poi restarto
            //JOptionPane.showMessageDialog(null, "Giocatore 1 ha vinto la serie di battaglie!");
            SchermataBattaglia battaglia = new SchermataBattaglia(true);
            // sovrascrivo i file
            //utente1.incrementaVittorie();
            //utente2.incrementaSconfitte();

            //non implemento la scrittura nei file perche sta nella funzione che richiama il metodo corrente

        }
        if(vittorieUtente2-vittorieUtente1>1) {
            //JOptionPane.showMessageDialog(null, "Giocatore 2 ha vinto la serie di battaglie!");
            SchermataBattaglia battaglia = new SchermataBattaglia(false);
            // scrivo sul file
            //utente2.incrementaVittorie();
            //utente1.incrementaSconfitte();
            //non implemento la scrittura nei file perche sta nella funzione che richiama il metodo corrente

        }

        else
            resettaLotta();
    }

    public void resettaLotta(){
        // sistemo hp e tutto il resto cosi
        // squadraUtente1 = new Utente(rd.getRigaByIndex("testo/utenti.txt",0); --> DEVO CONTROLLARE QUALE UTENTE DEVO PRENDERE
        squadra = clonaSquadra(squadraUtente1);
        // dovrei prendere i cloni dai file di testo (che salvo a fine lotta)
        // cosi tengo conto dei progressi dei pokemon (exp,lvl, ecc..)
        // per fare questo devo implementare l'utente e utilizzare scrittoremodifica()

        squadra2 = clonaSquadra(squadraUtente2);
        aggiornaUI();
        pokemon1.setEnabled(true);
        pokemon2.setEnabled(true);
        pokemon3.setEnabled(true);
        pokemon4.setEnabled(true);
        pokemon5.setEnabled(true);
        pokemon6.setEnabled(true);


        // -- PROBLEMI
        // se cambio pokemon e l'altro attacca non succede nulla
        // si blocca il tasto, non fa nulla e bisogna far cambiare pokemon necessariamente anche all'altro utente


        // quando cambio il primo pokemon deve fare la lotta il secondo indipendetemente

    }

    private Pokemon[] clonaSquadra(Pokemon[] squad){
        if (squad==null){
            return null;
        }
        Pokemon[] squadraClonata = new Pokemon[squad.length];
        for (int i = 0; i < squad.length; i++) {
            if (squad[i] == null)
                squadraClonata[i] = null;
            else
                squadraClonata[i] = squad[i].clone();
        }
        return squadraClonata;
    }

// TODO: SE CLICCO UNA MOSSA NON PRESENTE SI BLOCCA (NON DEVE FARE NULLA)

    /*
        public Pokemon trovaPokemon(String nomePokemon){ // non optional perche se in squadra è certamente presente
        for(Pokemon pokemon:squadra){
            if(nomePokemon.equals(pokemon.getSpriteMini())) // questo è contenuto nel bottone
                return pokemon; // pokemon trovato
        }
        return null; // condizione in cui non si arriva mai --> è sicuramente presente
    }
     */







}