package com.jpokemon;

import javax.swing.*;
import java.awt.*;

public class Finestra extends JFrame {
    private JButton bottone;


    public Finestra(){
        super("JPokemon");


        setLayout(null); // prima era new border layout
        setSize(1920,1080); // posso anche fare il set location
        setLocationRelativeTo(null);// per impostare la posizione della finestra


        // prova GIF -- carico la gif -->
        ImageIcon gif1 = new ImageIcon("img/front/blastoise-front.gif");
        ImageIcon gif2 = new ImageIcon("img/retro/charizard-retro.gif");

       // da qui posso scalare la dimensione --> (getScaledInstance(400,400,Image.SCALE_DEFAULT))
        Image img1 = gif1.getImage().getScaledInstance(270, 270, Image.SCALE_DEFAULT);
        Image img2 = gif2.getImage().getScaledInstance(270, 270, Image.SCALE_DEFAULT);

        ImageIcon scaledGif1 = new ImageIcon(img1);
        ImageIcon scaledGif2 = new ImageIcon(img2);


        // creo un altro JLabel contenente la gif scalata --> DIMENSIONI MAGGIORATE
        JLabel labelgif1 = new JLabel(scaledGif1);
        JLabel labelgif2 = new JLabel(scaledGif2);


        // creo un altro jlabel contenente la gif --> DIMENSIONI STANDARD
        //JLabel labelgif1 = new JLabel(gif1);
        //JLabel labelgif2 = new JLabel(gif2);

        // il label sarà grande quanto le gif stesse
        labelgif1.setSize(270,270);//(gif1.getIconWidth(),gif1.getIconHeight()); //SONO LE DIMENSIONI STANDARD
        labelgif2.setSize(270,270);//(gif2.getIconWidth(),gif2.getIconHeight());

        //posizionamento gif
        labelgif1.setLocation(800,80);
        labelgif2.setLocation(300,270);


        add(labelgif1); // posso specificare il borderlayout (area in cui va a posizionarsi)
        add(labelgif2); // aggiungo solo il label e successivamente lo posiziono

        // creo bottone
        bottone = new JButton("START");
        bottone.setSize(200,100);


        // TODO: creo pannello - capire struttura base
        JPanel pannello = new JPanel(new FlowLayout());// creo un panello che contiene il bottone e aggiungo quello ,
        pannello.add(bottone);// cosi da controllare la dimensione del bottone stesso
        pannello.setLayout(null); // PROVA
        pannello.setSize(400,100);
        pannello.setLocation(850, 750);
        add(pannello);

        //add(pannello,BorderLayout.CENTER); // lo avessi fatto direttamente con il bottone questo avrebbe occupato tutta la schermata


        setResizable(false); // poi vediamo se abilitarlo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }   

}
