package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Lotta extends JFrame {
    private Pokemon[] squadra = new Pokemon[6]; // ha dimensione fissa = 6, posso usare anche un array semplice
    private Pokemon[] squadra2 = new Pokemon[6]; // squadra dell'avversario
    private int mossa = -10;
    private int numTurno = 0;
    private int turno = 0;
    //devo fare un metodo grafico per selezionarli

    // Al posto di MOSSA metterò il nome della vera mossa ricavato dall'istanza
    // mettero tutto a static
    private JButton attacca = new JButton("Attacca");
    private JButton pokemon = new JButton("Pokemon");

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


    // non deve essere una nuova finestra ma una card che viene selezionata dopo lo start

    // la mossa 3 e 4 viene aggiunta successivamente se il pokemon le ha, in caso contrario il bottone non sarà cliccabile

    public Lotta() throws IOException {

        //COMMENTO OPZIONI VISUALIZZAZIONE A FINESTRA
        //super("JPokemon");
        //setLayout(null);
        //setSize(1920,1080); // dimensioni finestra
        //setLocationRelativeTo(null);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PARTE AGGIUNTA POKEMON
        Reader provaLettore = new Reader();
        Pokemon prova=provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",4));
        squadra2[0] = prova;
        squadra2[1] = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));

        Pokemon contro = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));
        Pokemon squad2 = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",0));
        squadra[0] = contro;
        squadra[1] = squad2;

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
        int a = squadra2[0].getSalute();
        int b = squadra[0].getSalute();
        cambioUtente=false;
        int danno;
        Mossa Mossapokemon1 = null; // SE LA MOSSA VALE "null" allora ha priorità sulle altre perché non fa danno al pokemon avversario (ad esempio cambiare pokemon ha la priorità)
        Mossa Mossapokemon2 = null;
        mossa1.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(0);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[0]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        PreCambiaPokemon();
                    }else{
                        cambioUtente=true;
                        PreCambiaPokemon();
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
                setMossa(0);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[0]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        PreCambiaPokemon();
                    }else{
                        cambioUtente=true;
                        PreCambiaPokemon();
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

        });

        mossa3.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(0);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[0]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        PreCambiaPokemon();
                    }else{
                        cambioUtente=true;
                        PreCambiaPokemon();
                    }
                }
                cambioUtente=true;
                setMossa(-10);
                cambiaContesto();
            }

        });

        mossa4.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(0);
                cambiaContesto();
            }
            else{
                setTurno(turno(finalTest[getMossa()], finalTest2[0]));
                if(getTurno() == -1){
                    if(squadra[0].getSalute() <= 0){
                        cambioUtente=false;
                        PreCambiaPokemon();
                    }else{
                        cambioUtente=true;
                        PreCambiaPokemon();
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
        int danno, CondEffetto1, CondEffetto2,effetti;
        String effetto1, effetto2 = null;
        Boolean turno1, turno2 = false;

        //SE IL POKEMON VIENE CAMBIATO COL TASTO FACCIAMO CHE LA MOSSA VIENE ISTANZIATA NULL
        //QUINDI POSSIAMO GESTIRE IL CAMBIO POKEMON FUORI DAL TURNO

        CondEffetto1 = Effetti.Effetto(Mossapokemon1.getNome());
        CondEffetto2 = Effetti.Effetto(Mossapokemon2.getNome());

        //GESTIONE CASI CondEffetto = 1
        // ESSENDOCI SOLO ATTACCO RAPIDO EFFETTIVAMENTE COME EFFETTO 1 allora posso gestirla in poche righe
        if(CondEffetto1 == 1 && CondEffetto2 != 1){
            // squadra poi squadra 2
            barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
            PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
            if(squadra2[0].getSalute() <= 0){
                return -1;
            }
            if (CondEffetto2 == 3){
                danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                barraPSpok1.diminuisci(danno);
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                Effetti.attivaEffettoDopo(squadra2[0],squadra[0],Mossapokemon2,danno);
                return 0;

            }else if (CondEffetto2 ==2) {

                danno = Effetti.attivaEffettoDurante(squadra2[0], squadra[0], Mossapokemon2);
                barraPSpok1.diminuisci(danno);
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }
        } else if (CondEffetto2 == 1 && CondEffetto1 != 1) {
            // squadra 2 poi squadra
            barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
            PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
            if (squadra[0].getSalute() <= 0){
                return -1;
            }
            if (CondEffetto1 == 3){
                danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                barraPSpok2.diminuisci(danno);
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                Effetti.attivaEffettoDopo(squadra[0],squadra2[0],Mossapokemon1,danno);
                return 0;
            }else if (CondEffetto1==2){
                danno = Effetti.attivaEffettoDurante(squadra[0],squadra2[0], Mossapokemon1);
                barraPSpok2.diminuisci(danno);
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
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
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                //Attacca Squadra2 poi squadra
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
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
                if(squadra2[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                return 0;
            }else{
                //Attacca Squadra2 poi squadra
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], Mossapokemon2));
                PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                if (squadra[0].getSalute() <= 0){
                    return -1;
                }
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], Mossapokemon1));
                PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
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
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra[0],squadra2[0],Mossapokemon1,danno);
                }
                if(CondEffetto2 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra2[0], squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
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
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
                    barraPSpok1.diminuisci(danno);
                    PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
                    if (squadra[0].getSalute() <= 0){
                        return -1;
                    }
                    Effetti.attivaEffettoDopo(squadra2[0],squadra[0],Mossapokemon2,danno);
                }

                if(CondEffetto1 == 2){
                    danno = Effetti.attivaEffettoDurante(squadra[0],squadra2[0],Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
                    if(squadra2[0].getSalute() <= 0){
                        return -1;
                    }
                }else{
                    danno = squadra[0].attacca(squadra2[0], Mossapokemon1);
                    barraPSpok2.diminuisci(danno);
                    PsPok2.setText(squadra2[0].getSalute()+"/"+squadra2[0].getPs());
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
    public void PreCambiaPokemon(){
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

            System.exit(1);
        }else{
            cambiaPokemon(indiceCambi[0]);
        }
    }
    public void cambiaPokemon(int indice){// quello contenuto nel bottone, viene ritornato dall actionlistener
        if(!cambioUtente){
            Pokemon cambio = squadra[indice]; // identifico il pokemon che subentrera nella lotta
            squadra[indice] = squadra[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra[0]=cambio;
        }
        else{
            Pokemon cambio = squadra2[indice]; // identifico il pokemon che subentrera nella lotta
            squadra2[indice] = squadra2[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra2[0]=cambio;
        }

        aggiornaUI();
    }

    // TODO: da implementare viene eseguita quando viene cambiato un pokemon dalla schermata principale
    //  oppure quando un pokemon viene sconfitto, e quindi viene sotituito con un altro

    private void aggiornaUI(){
        // devo usare il metodo anche per quando vengono effettuate delle evoluzioni

        if(!cambioUtente){
            nomePok1.setText(squadra[0].getNome());
            barraPSpok1.getBarraSalute().setMaximum(squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
            PsPok1.setText(squadra[0].getSalute() + "/" + squadra[0].getPs());
            // Aggiorna le GIF

            ImageIcon img = new ImageIcon(squadra[0].getSpriteBack());
            Image scaledGif = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            labelgif2.setIcon(new ImageIcon(scaledGif));


        }
        else{
            nomePok2.setText(squadra2[0].getNome());
            barraPSpok2.getBarraSalute().setMaximum(squadra2[0].getPs());
            barraPSpok2.getBarraSalute().setValue(squadra2[0].getSalute());
            PsPok2.setText(squadra2[0].getSalute() + "/" + squadra2[0].getPs()); // al posto di get ps dovrei mettere le variabili temporanee a e b
            // risolvo mettendo dentro pokemon salute e ps come prima
            // Aggiorna le GIF

            ImageIcon img = new ImageIcon(squadra2[0].getSpriteFront());
            Image scaledGif = img.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            labelgif1.setIcon(new ImageIcon(scaledGif));

        }
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
            utente.setText("UTENTE 2");
        }

        else{
            mosse = squadra[0].getMosse();
            squad = squadra;
            utente.setText("UTENTE 1");
        }

        // posso accorpare aggiornaUI e questa in una sola dato che le istruzioni solo duplicate
        mossa1.setText(mosse[0].getNome());
        mossa2.setText(mosse[1].getNome());
        mossa3.setText(mosse[2] != null ? mosse[2].getNome() : "vuoto");
        mossa4.setText(mosse[3] != null ? mosse[3].getNome() : "vuoto");
        pokemon1.setText(squad[0].getNome());
        pokemon1.setIcon(new ImageIcon(squad[0].getSpriteMini()));
        pokemon2.setText(squad[1].getNome());
        pokemon2.setIcon(new ImageIcon(squad[1].getSpriteMini()));
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