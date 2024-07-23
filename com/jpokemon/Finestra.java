package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Finestra extends JFrame {
    private CardLayout cardLayout;
    private  JPanel pannelloCard;



    public Finestra() throws IOException {
        setTitle("JPokemon");
        setSize(1280,720); // posso anche fare il set location
        setLocationRelativeTo(null);// per impostare la posizione della finestra
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // creo il card layout
        cardLayout = new CardLayout();
        pannelloCard = new JPanel(cardLayout);
        Home pannelloHome = new Home();
        SelezioneUtente pannelloUtente = new SelezioneUtente();

        //creazione pannelli
        JPanel pannello1 = pannelloHome.getPannello();
        JPanel pannello2 = pannelloUtente.getPannello();



        // aggiunta dei pannelli al pannelloCard
        pannelloCard.add(pannello1, "CARD 1");
        pannelloCard.add(pannello2,"CARD 2");



        //aggiungo il pannello al frame (finestra)
        add(pannelloCard);

        //creazione pulsanti di controllo
        pannelloHome.getStart().addActionListener(e -> cardLayout.next(pannelloCard));
        //pannelloUtente.getBottone1().addActionListener(e->cardLayout.next(pannelloCard)); //qua passo il numero della riga dove prendere l'utente da inizalizzare dentro lotta
        pannelloUtente.getBottone1().addActionListener(e-> {
            try {
                pannelloCard.add(new Lotta(0).getPannello(), "UT1");
                cardLayout.show(pannelloCard,"UT1");
            } catch (IOException ex) {
                System.err.println("Impossiible creare utente 1 in Lotta");
                throw new RuntimeException(ex);
            }
        });
        pannelloUtente.getBottone2().addActionListener(e-> {
            try {
                pannelloCard.add(new Lotta(1).getPannello(), "UT2");
                cardLayout.show(pannelloCard,"UT2");
            } catch (IOException ex) {
                System.err.println("Impossiible creare utente 2 in Lotta");
                throw new RuntimeException(ex);
            }
        });
        pannelloUtente.getBottone3().addActionListener(e-> {
            try {
                pannelloCard.add(new Lotta(2).getPannello(), "UT3");
                cardLayout.show(pannelloCard,"UT3");
            } catch (IOException ex) {
                System.err.println("Impossiible creare utente 3 in Lotta");
                throw new RuntimeException(ex);
            }
        });
        pannelloUtente.getBottone4().addActionListener(e-> {
            try {
                pannelloCard.add(new Lotta(3).getPannello(), "UT4");
                cardLayout.show(pannelloCard,"UT4");
            } catch (IOException ex) {
                System.err.println("Impossiible creare utente 4 in Lotta");
                throw new RuntimeException(ex);
            }
        });
        setResizable(false);
        setVisible(true);

    }   

}
