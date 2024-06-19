package com.jpokemon;

import javax.swing.*;
import java.awt.*;

public class Lotta extends JFrame {
    // Al posto di MOSSA metterò il nome della vera mossa ricavato dall'istanza
    private JButton mossa1 = new JButton("MOSSA 1");
    private JButton mossa2 = new JButton("MOSSA 2");
    private JButton mossa3 = new JButton("MOSSA 3");
    private JButton mossa4 = new JButton("MOSSA 4");

    // non deve essere una nuova finestra ma una card che viene selezionata dopo lo start

    // la mossa 3 e 4 viene aggiunta successivamente se il pokemon le ha, in caso contrario il bottone non sarà cliccabile

    public Lotta(){
        super("JPokemon");
        setLayout(null);
        setSize(1920,1080); // dimensioni finestra
        setLocationRelativeTo(null);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel pannello = new JPanel(null);
        ImageIcon fondoLotta = new ImageIcon("img/lotta.png");
        Image img = fondoLotta.getImage().getScaledInstance(1920,700,Image.SCALE_SMOOTH);
        fondoLotta = new ImageIcon(img);
        JLabel label = new JLabel(fondoLotta);
        label.setSize(1920, 700); // parte per l'aggiunta delle gif
        label.setLocation(0, 0);
        pannello.add(label);
        //pannello.setLayout(new BorderLayout());

        // PARTE AGGIUNTA POKEMON
        // prova GIF -- carico la gif -->
        ImageIcon gif1 = new ImageIcon("img/blastoise-front.gif");
        ImageIcon gif2 = new ImageIcon("img/charizard-retro.gif");

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
        mossa1.setSize(200,100);
        mossa2.setSize(200,100);
        mossa3.setSize(200,100);
        mossa4.setSize(200,100);
        mossa1.setLocation(0,700);
        mossa2.setLocation(200,700);
        mossa3.setLocation(400,700);
        mossa4.setLocation(600,700);
        pannello.add(mossa1,BorderLayout.SOUTH);
        pannello.add(mossa2,BorderLayout.SOUTH);
        pannello.add(mossa3,BorderLayout.SOUTH);
        pannello.add(mossa4,BorderLayout.SOUTH);



        pannello.add(label,BorderLayout.NORTH);
        setContentPane(pannello); // TODO

        setVisible(true);
    }



}
