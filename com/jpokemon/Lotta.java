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

    private JButton mossa1;// = new JButton("MOSSA 1"); //pokemon.getmossa(1); --> pokemon ha array mosse[4]
    private JButton mossa2;// = new JButton("MOSSA 2");
    private JButton mossa3;// = new JButton("MOSSA 3");
    private JButton mossa4;// = new JButton("MOSSA 4");
    // FORMATO BOTTONE: NOME (pokemon.getNome()), IMAGEICON(pokemon.getSpriteMini);
    private JButton pokemon1; // = new JButton("Charizard",new ImageIcon("img/mini/charizard-mini.gif")); //con un get del pokemon prendo l img la faccio imageicon e creo il bottone
    private JButton pokemon2 = new JButton("Rapidash", new ImageIcon("img/mini/rapidash-mini.gif"));
    private JButton pokemon3 = new JButton("POKEMON 3");
    private JButton pokemon4 = new JButton("POKEMON 4");
    private JButton pokemon5 = new JButton("POKEMON 5");
    private JButton pokemon6 = new JButton("POKEMON 6");

    private JButton indietro = new JButton("INDIETRO");
    private JPanel pannello;
    // non deve essere una nuova finestra ma una card che viene selezionata dopo lo start

    // la mossa 3 e 4 viene aggiunta successivamente se il pokemon le ha, in caso contrario il bottone non sarà cliccabile

    public Lotta() throws IOException {
        // i bottoni li dichiaro solo ma li istanzio tutti qui dentro, in quanto devono fare riferimento a oggetti non statici
        //COMMENTO OPZIONI VISUALIZZAZIONE A FINESTRA
        //super("JPokemon");
        //setLayout(null);
        //setSize(1920,1080); // dimensioni finestra
        //setLocationRelativeTo(null);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PARTE AGGIUNTA POKEMON
        Pokemon prova;
        Reader provaLettore = new Reader();
        prova=provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",0));

        Pokemon contro;
        contro = provaLettore.buildPokemonByString(provaLettore.getRigaByIndex("testo/pokemon.txt",6));




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
        ImageIcon gif1 = new ImageIcon(prova.getSpriteFront());
        ImageIcon gif2 = new ImageIcon(contro.getSpriteBack());  //ImageIcon gif2 = new ImageIcon("img/retro/charizard-retro.gif");

        Mossa[] test = new Mossa[4];
        test = contro.getMosse();

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



        pokemon1 = new JButton(contro.getNome(),new ImageIcon(contro.getSpriteMini()));
        // il resto lo prendo (anche questo in realtà) dall'array squadra
        // da qui posso scalare la dimensione --> (getScaledInstance(400,400,Image.SCALE_DEFAULT))
        Image img1 = gif1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        Image img2 = gif2.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);

        ImageIcon scaledGif1 = new ImageIcon(img1);
        ImageIcon scaledGif2 = new ImageIcon(img2);

        // creo un altro JLabel contenente la gif scalata --> DIMENSIONI MAGGIORATE
        JLabel labelgif1 = new JLabel(scaledGif1);
        JLabel labelgif2 = new JLabel(scaledGif2);
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

        attacca.setVisible(true);
        pokemon.setVisible(true);   //non servono ma li metto per ricordarmi i nomi
        indietro.setVisible(false);

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


        attacca.addActionListener(e -> {
            attacca.setVisible(false);
            pokemon.setVisible(false);

            mossa1.setVisible(true);
            mossa2.setVisible(true);
            mossa3.setVisible(true);
            mossa4.setVisible(true);
            indietro.setVisible(true);
        });

        pokemon.addActionListener(e ->{
            attacca.setVisible(false);
            pokemon.setVisible(false);

            pokemon1.setVisible(true);
            pokemon2.setVisible(true);
            pokemon3.setVisible(true);
            pokemon4.setVisible(true);
            pokemon5.setVisible(true);
            pokemon6.setVisible(true);
            indietro.setVisible(true);

        });
        indietro.addActionListener(x ->{
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

        });



        setFocusable(true);
        requestFocus();     // SENZA QUESTI I CONTROLLI NON PARTONO

        //LOCALE
        JLabel nomePok1 = new JLabel(contro.getNome()); //sostituire con pokemon.getNome()
        nomePok1.setBounds(1380,570,100,20);
        JProgressBar barraPSpok1 = new BarraPS(contro.getPs()).getBarraSalute(); //salute dovrà riferirsi ai PS del pokemon principale coinvolto nella lotta
        barraPSpok1.setLocation(1380,590); //PS del pokemonLocale
        barraPSpok1.setSize(500,20); // DA LEVARE
        JLabel PsPok1 = new JLabel(contro.getPs() + "/" + contro.getPs()); // sostituisco con ps (della classe pokemon) e vita (che ottengo dalla barra)
        PsPok1.setBounds(1380,610,100,20);
        //AVVERSARI
        JLabel nomePok2 = new JLabel(prova.getNome());
        nomePok2.setBounds(85,105,100,20);
        BarraPS barraPSpok2 = new BarraPS(prova.getPs());
        barraPSpok2.getBarraSalute().setLocation(85,123); //PS del pokemonAvversario - potrei usare anche qui il setBounds
        barraPSpok2.getBarraSalute().setSize(500,20); // DA LEVARE
        JLabel PsPok2 = new JLabel(prova.getPs()+"/"+prova.getPs()); // devo aggiornare questo valore
        PsPok2.setBounds(85,143,100,20);
        pannello.add(barraPSpok1);
        pannello.add(barraPSpok2.getBarraSalute());
        pannello.add(nomePok1);
        pannello.add(nomePok2);
        pannello.add(PsPok1);
        pannello.add(PsPok2);

        pannello.add(label,BorderLayout.NORTH);
        setContentPane(pannello); // TODO


        //QUESTO VA CAMBIATO MA DOPO
        Mossa[] finalTest = test; // mi serve final, poi lo tolgo
        int a = prova.getPs();

        // tutte le mosse devo aggiungere un cambio contesto (dopo che fa un'azione utente 1 la mano passa a utente 2)
        mossa1.addActionListener(e -> {
            barraPSpok2.diminuisci(contro.attacca(prova, finalTest[0]));
            PsPok2.setText(prova.getPs()+"/"+a);  //IL PRIMO getPs prende la salute attuale, il secondo prende la salute MASSIMA, va fatta la cosa del clone
        });                                                //PER TESTARE DICHIARO UNA FUNZIONE CHE A PRIORI PRENDE LA VITA MA NON LA FACCIAMO COSI
        mossa2.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[1].getPotenza());
            contro.attacca(prova, finalTest[1]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });

        mossa3.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[2].getPotenza());
            contro.attacca(prova, finalTest[2]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });

        mossa4.addActionListener(e -> {
            barraPSpok2.diminuisci(finalTest[3].getPotenza());
            contro.attacca(prova, finalTest[3]);
            PsPok2.setText(prova.getPs()+"/"+a);
        });

        pokemon2.addActionListener(e->{
            // cambio pokemon con riferimenti

        });




    }
    // diamo per scontato che il pokemon numero 1 in squadra sia quello coinvolto in lotta
    public JPanel getPannello(){
        return pannello;
    }

    public void cambiaPokemon(int indice){ // quello contenuto nel bottone, viene ritornato dall actionlistener
        Pokemon cambio = squadra[indice]; // identifico il pokemon che subentrera nella lotta
        squadra[indice] = squadra[0];// senno clicco il bottone e dal numero bottone tiro fuori il pokemon
        squadra[0]=cambio;
    }

    // TODO: da implementare viene eseguita quando viene cambiato un pokemon dalla schermata principale
    //  oppure quando un pokemon viene sconfitto, e quindi viene sotituito con un altro
    public void cambiaPokemon(){}


    // devo cambiare tutti i riferimenti al pokemon vecchio con quello nuovo
    //utilizzo metodo per risalire dal nome all'istanza del pokemon vera e propria che sta dentro pokemon
    // va da se che i 6 pokemon che ho li devo contenere dentro una lista




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
