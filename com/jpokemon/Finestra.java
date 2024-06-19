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


        // prova GIF -- carico la gif
        ImageIcon gif1 = new ImageIcon("img/blastoise-front.gif");
        ImageIcon gif2 = new ImageIcon("img/charizard-retro.gif");
        // creo un altro jlabel contenente la gif
        JLabel labelgif1 = new JLabel(gif1);
        JLabel labelgif2 = new JLabel(gif2);

        // il label sarà grande quanto le gif stesse
        labelgif1.setSize(gif1.getIconWidth(),gif1.getIconHeight());
        labelgif2.setSize(gif2.getIconWidth(),gif2.getIconHeight());

        //posizionamento gif
        labelgif1.setLocation(920,150);
        labelgif2.setLocation(800,210);


        add(labelgif1); // posso specificare il borderlayout (area in cui va a posizionarsi)
        add(labelgif2); // aggiungo solo il label e successivamente lo posiziono

        // creo bottone
        bottone = new JButton("START!");
        bottone.setSize(200,100);

        // TODO: creo pannello - capire struttura base
        JPanel pannello = new JPanel(new FlowLayout());// creo un panello che contiene il bottone e aggiungo quello ,
        pannello.add(bottone);// cosi da controllare la dimensione del bottone stesso
        pannello.setLayout(null); // PROVA
        pannello.setSize(400,100);
        pannello.setLocation((getWidth()-pannello.getWidth())/2, (getHeight()-pannello.getHeight())/2);
        add(pannello);

        // lo aggiungo alla finestra
        //add(pannello,BorderLayout.CENTER); // lo avessi fatto direttamente con il bottone questo avrebbe occupato tutta la schermata
        setResizable(false); // poi vediamo se abilitarlo

        // per mettere il bottone nell'esatto centro della finestra devo usare il gridbaglayout e non il panel

        // specifiche finali finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }   

}
