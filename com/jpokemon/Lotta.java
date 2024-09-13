package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Classe incaricata della visualizzazione grafica della lotta pokemon
 * contenente elementi grafici e bottone di interazione gestiti per analizzare
 * e generare le corrette dinamiche di gioco
 */
public class Lotta extends JFrame {
    private Pokemon[] squadra;
    private Pokemon[] squadra2;
    private int mossa = -10;
    private int turno = 0;

    private final JButton attacca = new JButton("Attacca");
    private final JButton pokemon = new JButton("Pokemon");

    private JButton mossa1;
    private JButton mossa2;
    private JButton mossa3;
    private JButton mossa4;

    private JButton pokemon1;
    private JButton pokemon2;
    private JButton pokemon3;
    private JButton pokemon4;
    private JButton pokemon5;
    private JButton pokemon6;

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
    private JLabel labelUtente;
    private boolean cambioUtente;
    private int  vittorieUtente1;
    private int vittorieUtente2;
    private Pokemon[] squadraUtente1 = new Pokemon[6];

    private Pokemon[] squadraUtente2 = new Pokemon[6];

    private Utente utente1;
    private Utente utente2;
    private Mossa Cambio = new Mossa("Cambio",Tipo.NORMALE,TipoMossa.SPECIALE,0,0,9999);

    // viene passato dalla schermata precedente, in base al tasto cliccato viene deciso quale utente utilizzare e questo viene caricato da memoria 


    // la mossa 3 e 4 viene aggiunta successivamente se il pokemon le ha, in caso contrario il bottone non sarà cliccabile
    public Lotta(int numeroUtente1, int numeroUtente2) throws IOException {

        // PARTE AGGIUNTA POKEMON
        Reader rd = Reader.getInstance();

        utente1 = rd.buildUtentebyString(rd.getRigaByIndex("testo/utenti.txt", numeroUtente1));// <--------
        utente2 = rd.buildUtentebyString(rd.getRigaByIndex("testo/utenti.txt", numeroUtente2));

        squadraUtente1 = utente1.getSquadra();
        squadraUtente2 = utente2.getSquadra();


        squadra = clonaSquadra(squadraUtente1);
        squadra2 = clonaSquadra(squadraUtente2);




        pannello = new JPanel(null);
        ImageIcon fondoLotta = new ImageIcon("img/lotta.png");
        Image img = fondoLotta.getImage().getScaledInstance(1280,400,Image.SCALE_SMOOTH);
        fondoLotta = new ImageIcon(img);
        JLabel label = new JLabel(fondoLotta);
        label.setSize(1280, 400); // parte per l'aggiunta delle gif
        label.setLocation(0, 0);
        pannello.add(label);

        ImageIcon gif1 = new ImageIcon(squadra2[0].getSpriteFront());
        ImageIcon gif2 = new ImageIcon(squadra[0].getSpriteBack());
        Mossa[] mosse;
        mosse = squadra[0].getMosse();

        mossa1 = new JButton(mosse[0].getNome()); //un pokemon ha sempre almeno due mosse
        if (mosse[1] == null){
            mossa2 = new JButton("vuoto");
        }
        else
            mossa2 = new JButton(mosse[1].getNome());
        if(mosse[2] == null)
            mossa3 = new JButton("vuoto");
        else
            mossa3 = new JButton(mosse[2].getNome());

        if(mosse[3]==null)
            mossa4 = new JButton("vuoto");
        else
            mossa4 = new JButton(mosse[3].getNome());


        pokemon1 = new JButton(squadra[0].getNome(),new ImageIcon(squadra[0].getSpriteMini()));
        pokemon2 = new JButton(squadra[1].getNome(), new ImageIcon(squadra[1].getSpriteMini()));
        pokemon3 = new JButton(squadra[2].getNome(), new ImageIcon(squadra[2].getSpriteMini()));
        pokemon4 = new JButton(squadra[3].getNome(), new ImageIcon(squadra[3].getSpriteMini()));
        pokemon5 = new JButton(squadra[4].getNome(), new ImageIcon(squadra[4].getSpriteMini()));
        pokemon6 = new JButton(squadra[5].getNome(), new ImageIcon(squadra[5].getSpriteMini()));

        Image img1 = gif1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        Image img2 = gif2.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);

        ImageIcon scaledGif1 = new ImageIcon(img1);
        ImageIcon scaledGif2 = new ImageIcon(img2);

        // creo un altro JLabel contenente la gif scalata --> DIMENSIONI MAGGIORATE

        labelgif1 = new JLabel(scaledGif1);
        labelgif2 = new JLabel(scaledGif2);

        // il label sarà grande quanto le gif stesse
        labelgif1.setSize(150,150);
        labelgif2.setSize(150,150);

        //posizionamento gif
        labelgif1.setLocation(880,100);
        labelgif2.setLocation(240,250);

        pannello.add(labelgif1);
        pannello.add(labelgif2);

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
        requestFocus();

        //LOCALE
        nomePok1 = new JLabel(squadra[0].getNome());
        nomePok1.setBounds(930,320,100,20);
        barraPSpok1 = new BarraPS(squadra[0].getPs());
        barraPSpok1.getBarraSalute().setLocation(930,335); //PS del pokemonLocale
        barraPSpok1.getBarraSalute().setSize(310,20);
        PsPok1 = new JLabel(squadra[0].getPs() + "/" + squadra[0].getPs());
        PsPok1.setBounds(930,355,100,20);

        //AVVERSARI
        nomePok2 = new JLabel(squadra2[0].getNome());
        nomePok2.setBounds(55,55,100,20);
        barraPSpok2 = new BarraPS(squadra2[0].getPs());
        barraPSpok2.getBarraSalute().setLocation(55,70); //PS del pokemonAvversario
        barraPSpok2.getBarraSalute().setSize(310,20);
        PsPok2 = new JLabel(squadra2[0].getPs()+"/"+squadra2[0].getPs());
        PsPok2.setBounds(55,90,100,20);
        labelUtente = new JLabel(utente1.getNome());
        labelUtente.setBounds(605,320,200,200);
        pannello.add(labelUtente);
        pannello.add(barraPSpok1.getBarraSalute());
        pannello.add(barraPSpok2.getBarraSalute());
        pannello.add(nomePok1);
        pannello.add(nomePok2);
        pannello.add(PsPok1);
        pannello.add(PsPok2);

        pannello.add(label,BorderLayout.NORTH);
        setContentPane(pannello);

        cambioUtente=false;

        mossa1.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(0);
                cambiaContesto();
            }
            else{
                // index -10 out of bounds for length 4 (da rivedere, sarebbe il valore restituito dal cambio pokemon)

                if(getMossa() == -10){
                    setTurno(turno(Cambio, squadra2[0].getMosse()[0]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){

                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{

                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }else{
                    setTurno(turno(squadra[0].getMosse()[getMossa()], squadra2[0].getMosse()[0]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){

                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{

                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
                cambioUtente=true;

                cambiaContesto();
            }

            //IL PRIMO getPs prende la salute attuale, il secondo prende la salute MASSIMA, va fatta la cosa del clone
        });
        mossa2.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(1);
                cambiaContesto();
            }
            else{
                if(getMossa() == -10){
                    setTurno(turno(Cambio, squadra2[0].getMosse()[1]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }else{
                    setTurno(turno(squadra[0].getMosse()[getMossa()], squadra2[0].getMosse()[1]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }

                cambiaContesto();
            }

        });

        mossa3.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(2);
                cambiaContesto();
            }
            else{
                if(getMossa() == -10){
                    setTurno(turno(Cambio, squadra2[0].getMosse()[2]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }else{
                    setTurno(turno(squadra[0].getMosse()[getMossa()], squadra2[0].getMosse()[2]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }

                cambioUtente=true;

                cambiaContesto();
            }

        });

        mossa4.addActionListener(e -> {
            if (!cambioUtente){
                setMossa(3);
                cambiaContesto();
            }
            else{
                if(getMossa() == -10){
                    setTurno(turno(Cambio, squadra2[0].getMosse()[3]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }else{
                    setTurno(turno(squadra2[0].getMosse()[getMossa()], squadra2[0].getMosse()[3]));
                    if(getTurno() == -1){
                        if(squadra[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
                cambioUtente=true;

                cambiaContesto();
            }

        });
        // cambio UI per il secondo utente
        pokemon2.addActionListener(e->cambiaPokemon(1,0));
        pokemon3.addActionListener(e->cambiaPokemon(2,0));
        pokemon4.addActionListener(e->cambiaPokemon(3,0));
        pokemon5.addActionListener(e->cambiaPokemon(4,0));
        pokemon6.addActionListener(e->cambiaPokemon(5,0));

    }

    private static void createDialog(Mossa[] mossePoke, Pokemon poke) {
        // Creazione del JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Dialog con 5 Bottoni");
        dialog.setSize(960, 540);
        dialog.setLocationRelativeTo(null); // Centra il dialog
        dialog.setModal(true); // Rende il dialog modale

        // Pannello per i bottoni
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5)); // 1 riga, 5 colonne

        // Creazione dei 5 bottoni

        JButton button1 = new JButton(mossePoke[1].getNome());
        JButton button2 = new JButton(mossePoke[2].getNome());
        JButton button3 = new JButton(mossePoke[3].getNome());
        JButton button4 = new JButton(mossePoke[4].getNome());
        JButton button5 = new JButton(poke.getMossaDaImparare());

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);

        button1.addActionListener(e -> {
            try {
                poke.imparaMossa(poke.getMossaDaImparare(),0);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dialog.setVisible(false);
        });
        button2.addActionListener(e -> {
            try {
                poke.imparaMossa(poke.getMossaDaImparare(),1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dialog.setVisible(false);
        });
        button3.addActionListener(e -> {
            try {
                poke.imparaMossa(poke.getMossaDaImparare(),2);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dialog.setVisible(false);
        });
        button4.addActionListener(e -> {
            try {
                poke.imparaMossa(poke.getMossaDaImparare(),3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            dialog.setVisible(false);
        });
        button5.addActionListener(e -> {
            dialog.setVisible(false);
        });

        dialog.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        dialog.setVisible(true); // Mostra il dialog
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

    /**
     * determina graficamente lo svolgimento di un turno durante la lotta
     * @param Mossapokemon1 mossa del primo pokemon
     * @param Mossapokemon2 mossa del secondo pokemon
     * @return flag che determina se il pokemon è stato sconfitto
     */
    public int turno(Mossa Mossapokemon1, Mossa Mossapokemon2){
        int danno, CondEffetto1, CondEffetto2;


        //SE IL POKEMON VIENE CAMBIATO COL TASTO FACCIAMO CHE LA MOSSA VIENE ISTANZIATA NULL


        CondEffetto1 = Effetti.Effetto(Mossapokemon1.getNome());
        CondEffetto2 = Effetti.Effetto(Mossapokemon2.getNome());

        //GESTIONE CASI CondEffetto = 1 (attacco rapido)

        if(CondEffetto1==-10 && CondEffetto2!=-10){
            danno = squadra2[0].attacca(squadra[0], Mossapokemon2);
            barraPSpok1.diminuisci(danno);
            PsPok1.setText(squadra[0].getSalute()+"/"+squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getSalute());
            if (squadra[0].getSalute() <= 0){
                return -1;
            }
        }
        if(CondEffetto2==-10 && CondEffetto1!=-10){
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

        }
    }

    public JPanel getPannello(){
        return pannello;
    }

    /**
     * funzione di controllo per verificare la possibilita di effetture un cambio pokemon
     * @throws IOException
     * @param index : serve per sapere se il cambio avviene prima della morte oppure si
     */
    public void PreCambiaPokemon(int index) throws IOException {
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
                Vittoria frameVittoria = new Vittoria(cambioUtente);

                //salvo i progressi sui file
                resettaLotta();
                checkBattaglia();
                utente1.scrittoreModifica();
                utente2.scrittoreModifica();
                // vuol dire che ha vinto il giocatore 2, perche 1 sta senza pokemon
            }
            else{
                pokemon1.setEnabled(false); // quindi anche le mosse
                vittorieUtente1++;
                Vittoria frameVittoria = new Vittoria(cambioUtente);

                resettaLotta();
                checkBattaglia();
                utente1.scrittoreModifica();
                utente2.scrittoreModifica();

            }

        }else{
            if(!cambioUtente){
                squadra[0].sconfitto(squadra2[0]);
                if(squadra[0].getLvl() == squadra[0].getLvlEvoluzione()){
                    squadra[0].evolvi();
                    aggiornaUI();

                } else if (squadra[0].getLvl() == (squadra[0].getIndiceDaImparare())){

                    Mossa[] mosse = squadra[0].getMosse();
                    int cont = 0;
                    for(int i=0;i<4;i++){
                        if(!Objects.equals(mosse[i].getNome(), "null")){
                            cont++;
                        }
                    }
                    if(cont == 4){
                        createDialog(mosse,squadra[0]);

                    }else{
                        squadra[0].imparaMossa(squadra[0].getMossaDaImparare(),0);
                    }

                }


            }
            else{
                squadra2[0].sconfitto(squadra[0]);
                if(squadra2[0].getLvl() == squadra2[0].getLvlEvoluzione()) {
                    squadra2[0].evolvi();
                    aggiornaUI();
                } else if (squadra2[0].getLvl() == (squadra2[0].getIndiceDaImparare())) {

                    Mossa[] mosse = squadra2[0].getMosse();
                    int cont = 0;
                    for(int i=0;i<4;i++){
                        if(!Objects.equals(mosse[i].getNome(), "null")){
                            cont++;
                        }
                    }
                    if(cont == 4){
                        createDialog(mosse,squadra2[0]);
                    }else{
                        squadra2[0].imparaMossa(squadra2[0].getMossaDaImparare(),0);
                    }
                }
            }

            cambiaPokemon(indiceCambi[0],index);

            }

    }

    /**
     * funzione per il cambio di un pokemon (tra uno in squadra e quello attualmente coinvolto in lotta)
     * @param indice
     */
    public void cambiaPokemon(int indice,int index){// quello contenuto nel bottone, viene ritornato dall actionlistener
        if(!cambioUtente) {
            Pokemon cambio = squadra[indice]; // identifico il pokemon che subentrera nella lotta
            squadra[indice] = squadra[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra[0] = cambio;
            if (squadra[indice].getSalute() <= 0) {
                // rendo il bottone non cliccabile
                switch (indice) {
                    case 0:
                        pokemon1.setEnabled(false); // devo bloccare anche le mosse
                        break;
                    case 1:
                        pokemon2.setEnabled(false);
                        break;
                    case 2:
                        pokemon3.setEnabled(false);
                        break;
                    case 3:
                        pokemon4.setEnabled(false);
                        break;
                    case 4:
                        pokemon5.setEnabled(false);
                        break;
                    case 5:
                        pokemon6.setEnabled(false);
                        break;
                }
            }
            setMossa(-10);
        }
        else{
            Pokemon cambio = squadra2[indice]; // identifico il pokemon che subentrera nella lotta
            squadra2[indice] = squadra2[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
            squadra2[0]=cambio;
            if(squadra2[indice].getSalute()<=0){
                // rendo il bottone non cliccabile in caso di pokemon esausto

                switch (indice){

                    case 0:
                        pokemon1.setEnabled(false);
                        break;
                    case 1:
                        pokemon2.setEnabled(false);
                        break;
                    case 2:
                        pokemon3.setEnabled(false);
                        break;
                    case 3:
                        pokemon4.setEnabled(false);
                        break;
                    case 4:
                        pokemon5.setEnabled(false);
                        break;
                    case 5:
                        pokemon6.setEnabled(false);
                        break;

                }

            }
            if(index != -1){
                if(getMossa() == -10){
                    setTurno(turno(Cambio, Cambio));
                }else{
                    Mossa mossa = squadra[0].getMosse()[getMossa()];
                    setTurno(turno(mossa,Cambio));
                    if(getTurno() == -1){
                        if(squadra2[0].getSalute() <= 0){
                            cambioUtente=false;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }else{
                            cambioUtente=true;
                            try {
                                PreCambiaPokemon(-1);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

                }
            }

        }

        aggiornaUI();
    }

    /**
     * metodo per l'aggiornamento degli elementi grafici visualizzati dopo una modifica apportata sui pokemon utilizzati
     */
    private void aggiornaUI(){

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

    /**
     * determina chi deve effettuare il proprio turno tra i due utenti personalizzando l'interfaccia grafica in funzione di questi
     */
    private void cambiaContesto(){
        Mossa[] mosse;
        Pokemon[] squad;

        // Aggiorna le mosse
        if(!cambioUtente){
            mosse = squadra2[0].getMosse();
            squad = squadra2;
            labelUtente.setText(utente2.getNome());
        }

        else{
            mosse = squadra[0].getMosse();
            squad = squadra;
            labelUtente.setText(utente1.getNome());
        }


        mossa1.setText(mosse[0] != null ? mosse[0].getNome() : "vuoto");
        mossa2.setText(mosse[1] != null ? mosse[1].getNome() : "vuoto");
        mossa3.setText(mosse[2] != null ? mosse[2].getNome() : "vuoto");
        mossa4.setText(mosse[3] != null ? mosse[3].getNome() : "vuoto");
        pokemon1.setText(squad[0].getNome());
        pokemon1.setIcon(new ImageIcon(squad[0].getSpriteMini()));
        pokemon2.setText(squad[1].getNome());
        pokemon2.setIcon(new ImageIcon(squad[1].getSpriteMini()));
        pokemon3.setText(squad[2].getNome());
        pokemon3.setIcon(new ImageIcon(squad[2].getSpriteMini()));
        pokemon4.setText(squad[3].getNome());
        pokemon4.setIcon(new ImageIcon(squad[3].getSpriteMini()));
        pokemon5.setText(squad[4].getNome());
        pokemon5.setIcon(new ImageIcon(squad[4].getSpriteMini()));
        pokemon6.setText(squad[5].getNome());
        pokemon6.setIcon(new ImageIcon(squad[5].getSpriteMini()));
        cambioUtente=!cambioUtente;


    }

    /**
     * controlla se uno dei due utenti ha vinto la battaglia generale (al meglio delle tre)
     * @throws IOException
     */
    public void checkBattaglia() throws IOException {


        if(vittorieUtente1-vittorieUtente2>1){
            // sovrascrivo i file
            utente1.incrementaVittorie();
            utente1.partiteGiocate();
            utente2.incrementaSconfitte();
            utente2.partiteGiocate();
            SchermataBattaglia battaglia = new SchermataBattaglia(true);




        }
        if(vittorieUtente2-vittorieUtente1>1) {
            // scrivo sul file
            utente2.incrementaVittorie();
            utente2.partiteGiocate();
            utente1.incrementaSconfitte();
            utente1.partiteGiocate();
            SchermataBattaglia battaglia = new SchermataBattaglia(false);



        }

        else
            resettaLotta();
    }

    /**
     * dopo la vittoria di una singola lotta ripristina lo stato iniziale della lotta
     */
    public void resettaLotta(){
        squadra = clonaSquadra(squadraUtente1);

        squadra2 = clonaSquadra(squadraUtente2);
        aggiornaUI();
        pokemon1.setEnabled(true);
        pokemon2.setEnabled(true);
        pokemon3.setEnabled(true);
        pokemon4.setEnabled(true);
        pokemon5.setEnabled(true);
        pokemon6.setEnabled(true);

    }

    /**
     * clona la squadra di un pokemon
     * @param squad : squadra pokemon
     * @return clone della squadra data come parametro
     */
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




}