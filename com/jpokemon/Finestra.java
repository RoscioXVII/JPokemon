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
        //Utente utente1 = pannelloUtente; --> praticamente faccio un return quando clicco il bottone dell utente creato e me lo ritrovo qui
        Lotta pannelloLotta  = new Lotta(0);
        //Lotta pannelloLotta = new Lotta(utenteRestituito); //devo gestire il numero utente, questo lo metto solo per testing, senno non si arriva alla schermata di lotta

        //creazione pannelli
        JPanel pannello1 = pannelloHome.getPannello();
        JPanel pannello2 = pannelloUtente.getPannello();
        JPanel pannello3 = pannelloLotta.getPannello();



        // aggiunta dei pannelli al pannelloCard
        pannelloCard.add(pannello1, "CARD 1");
        pannelloCard.add(pannello2,"CARD 2");
        pannelloCard.add(pannello3, "CARD 3");



        //aggiungo il pannello al frame (finestra)
        add(pannelloCard);

        //creazione pulsanti di controllo
        // da risolvere, devo capire quale utente passare a lotta
        pannelloHome.getStart().addActionListener(e -> cardLayout.next(pannelloCard));
        pannelloUtente.getBottone1().addActionListener(e->cardLayout.next(pannelloCard)); //qua passo il numero della riga dove prendere l'utente da inizalizzare dentro lotta
        pannelloUtente.getBottone2().addActionListener(e->cardLayout.next(pannelloCard));
        pannelloUtente.getBottone3().addActionListener(e->cardLayout.next(pannelloCard));
        pannelloUtente.getBottone4().addActionListener(e->cardLayout.next(pannelloCard));
        setResizable(false);
        setVisible(true);

    }   

}
