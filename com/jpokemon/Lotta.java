package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Lotta extends JFrame {
    private Pokemon[] squadra = new Pokemon[6]; // ha dimensione fissa = 6, posso usare anche un array semplice
    private Pokemon[] squadra2 = new Pokemon[6]; // squadra dell'avversario
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
        Pokemon prova=provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",0));
        squadra2[0] = prova;
        squadra2[1] = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));

        Pokemon contro = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));
        Pokemon squad2 = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",0));
        squadra[0] = contro;
        squadra[1] = squad2;

        pannello = new JPanel(null);
        ImageIcon fondoLotta = new ImageIcon("img/lotta.png");
        Image img = fondoLotta.getImage().getScaledInstance(1920,700,Image.SCALE_SMOOTH);
        fondoLotta = new ImageIcon(img);
        JLabel label = new JLabel(fondoLotta);
        label.setSize(1920, 700); // parte per l'aggiunta delle gif
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
        Image img1 = gif1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        Image img2 = gif2.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);

        ImageIcon scaledGif1 = new ImageIcon(img1);
        ImageIcon scaledGif2 = new ImageIcon(img2);

        // creo un altro JLabel contenente la gif scalata --> DIMENSIONI MAGGIORATE

        labelgif1 = new JLabel(scaledGif1);
        labelgif2 = new JLabel(scaledGif2);

        // il label sarà grande quanto le gif stesse
        labelgif1.setSize(300,300);//(gif1.getIconWidth(),gif1.getIconHeight()); //SONO LE DIMENSIONI STANDARD
        labelgif2.setSize(300,300);//(gif2.getIconWidth(),gif2.getIconHeight());

        //posizionamento gif
        labelgif1.setLocation(1200,150);
        labelgif2.setLocation(300,400);

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
        attacca.setLocation(0,700);
        pokemon.setLocation(200,700);

        mossa1.setLocation(0,700);
        mossa2.setLocation(200,700);
        mossa3.setLocation(0,800);
        mossa4.setLocation(200,800);

        pokemon1.setLocation(0,700);
        pokemon2.setLocation(200,700);
        pokemon3.setLocation(0,800);
        pokemon4.setLocation(200,800);
        pokemon5.setLocation(0,900);
        pokemon6.setLocation(200,900);

        //BOTTONE INDIETRO -- DA SISTEMARE
        indietro.setSize(200,300);
        indietro.setVisible(true);
        indietro.setLocation(400,700);

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
        nomePok1.setBounds(1380,570,100,20);
        barraPSpok1 = new BarraPS(squadra[0].getPs()); //salute dovrà riferirsi ai PS del pokemon principale coinvolto nella lotta
        barraPSpok1.getBarraSalute().setLocation(1380,590); //PS del pokemonLocale
        barraPSpok1.getBarraSalute().setSize(500,20); // DA LEVARE
        PsPok1 = new JLabel(squadra[0].getPs() + "/" + squadra[0].getPs()); // sostituisco con ps (della classe pokemon) e vita (che ottengo dalla barra)
        PsPok1.setBounds(1380,610,100,20);
        //AVVERSARI
        nomePok2 = new JLabel(squadra2[0].getNome());
        nomePok2.setBounds(85,105,100,20);
        barraPSpok2 = new BarraPS(squadra2[0].getPs());
        barraPSpok2.getBarraSalute().setLocation(85,123); //PS del pokemonAvversario - potrei usare anche qui il setBounds
        barraPSpok2.getBarraSalute().setSize(500,20); // DA LEVARE
        PsPok2 = new JLabel(squadra2[0].getPs()+"/"+squadra2[0].getPs()); // devo aggiornare questo valore
        PsPok2.setBounds(85,143,100,20);
        pannello.add(barraPSpok1.getBarraSalute());
        pannello.add(barraPSpok2.getBarraSalute());
        pannello.add(nomePok1);
        pannello.add(nomePok2);
        pannello.add(PsPok1);
        pannello.add(PsPok2);

        pannello.add(label,BorderLayout.NORTH);
        setContentPane(pannello); // TODO


        //QUESTO VA CAMBIATO MA DOPO
        Mossa[] finalTest = test; // mi serve final, poi lo tolgo --si riferisce solo all utente 1
        Mossa[] finalTest2 = squadra2[0].getMosse(); // provvisorio
        int a = squadra2[0].getPs();
        int b = squadra[0].getPs();
        cambioUtente=false;
        // tutte le mosse devo aggiungere un cambio contesto (dopo che fa un'azione utente 1 la mano passa a utente 2)
        mossa1.addActionListener(e -> {
            // implementare if con variabile booleana per capire chi dei due attacca (di chi è il turno)
            //posso anche dentro cambio contesto
            // devo considerare anche la velocita delle due mosse
            if (!cambioUtente){
                barraPSpok2.diminuisci(squadra[0].attacca(squadra2[0], finalTest[0]));
                PsPok2.setText(squadra2[0].getPs()+"/"+a);
                cambiaContesto();
            }
            else{
                barraPSpok1.diminuisci(squadra2[0].attacca(squadra[0], finalTest2[0]));
                PsPok1.setText(squadra[0].getPs()+"/"+b);
                cambiaContesto();
            }

            //IL PRIMO getPs prende la salute attuale, il secondo prende la salute MASSIMA, va fatta la cosa del clone
        });                                                //PER TESTARE DICHIARO UNA FUNZIONE CHE A PRIORI PRENDE LA VITA MA NON LA FACCIAMO COSI
        mossa2.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[1].getPotenza());
            squadra[0].attacca(prova, finalTest[1]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });

        mossa3.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[2].getPotenza());
            squadra[0].attacca(prova, finalTest[2]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });

        mossa4.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[3].getPotenza());
            squadra[0].attacca(prova, finalTest[3]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });
        // cambio UI per il secondo utente
        pokemon2.addActionListener(e->cambiaPokemon(1));
        pokemon3.addActionListener(e->cambiaPokemon(2));
        pokemon4.addActionListener(e->cambiaPokemon(3));
        pokemon5.addActionListener(e->cambiaPokemon(4));
        pokemon6.addActionListener(e->cambiaPokemon(5));




    }
    // diamo per scontato che il pokemon numero 1 in squadra sia quello coinvolto in lotta
    public JPanel getPannello(){
        return pannello;
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
        Mossa[] mosse;

        if(!cambioUtente){
            mosse=squadra[0].getMosse();

            nomePok1.setText(squadra[0].getNome());

            barraPSpok1.getBarraSalute().setMaximum(squadra[0].getPs());
            barraPSpok1.getBarraSalute().setValue(squadra[0].getPs());
            PsPok1.setText(squadra[0].getPs() + "/" + squadra[0].getPs());
            // Aggiorna le GIF

            ImageIcon img = new ImageIcon(squadra[0].getSpriteBack());
            Image scaledGif = img.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            labelgif2.setIcon(new ImageIcon(scaledGif));

            // devo aggiornare le gif anche nella schermata di selezione dei pokemon
            pokemon1.setText(squadra[0].getNome());
            pokemon1.setIcon(new ImageIcon(squadra[0].getSpriteMini()));
            pokemon2.setText(squadra[1].getNome());
            pokemon2.setIcon(new ImageIcon(squadra[1].getSpriteMini()));

        }
        else{
            mosse=squadra2[0].getMosse();
            nomePok2.setText(squadra2[0].getNome());

            barraPSpok2.getBarraSalute().setMaximum(squadra2[0].getPs());
            barraPSpok2.getBarraSalute().setValue(squadra2[0].getPs());
            PsPok2.setText(squadra2[0].getPs() + "/" + squadra2[0].getPs());
            // Aggiorna le GIF

            ImageIcon img = new ImageIcon(squadra2[0].getSpriteFront());
            Image scaledGif = img.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
            labelgif1.setIcon(new ImageIcon(scaledGif));

            // devo aggiornare le gif anche nella schermata di selezione dei pokemon
            pokemon1.setText(squadra2[0].getNome());
            pokemon1.setIcon(new ImageIcon(squadra2[0].getSpriteMini()));
            pokemon2.setText(squadra2[1].getNome());
            pokemon2.setIcon(new ImageIcon(squadra2[1].getSpriteMini()));
        }



        // Aggiorna le mosse

        mossa1.setText(mosse[0].getNome());
        mossa2.setText(mosse[1].getNome());
        mossa3.setText(mosse[2] != null ? mosse[2].getNome() : "vuoto");
        mossa4.setText(mosse[3] != null ? mosse[3].getNome() : "vuoto");
        //cambiaContesto();
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
        }

        else{
            mosse = squadra[0].getMosse();
            squad = squadra;
        }


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