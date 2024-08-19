package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Finestra extends JFrame {
    private CardLayout cardLayout;
    private  JPanel pannelloCard;
    private Lotta pannelloLotta;
    private Utente utenteGenerato;
    private JFrame casella = new JFrame("Inserisci Utente");
    private Reader rd = Reader.getInstance();



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
        //Lotta pannelloLotta  = new Lotta(0);
        //Lotta pannelloLotta = new Lotta(utenteRestituito); //devo gestire il numero utente, questo lo metto solo per testing, senno non si arriva alla schermata di lotta

        //creazione pannelli
        JPanel pannello1 = pannelloHome.getPannello();
        JPanel pannello2 = pannelloUtente.getPannello();
        //JPanel pannello3 = pannelloLotta.getPannello();



        // aggiunta dei pannelli al pannelloCard
        pannelloCard.add(pannello1, "CARD 1");
        pannelloCard.add(pannello2,"CARD 2");
        //pannelloCard.add(pannello3, "CARD 3");



        //aggiungo il pannello al frame (finestra)
        add(pannelloCard); // non ha lo scenario dopo, da sistemare
        // PROVVISORIO

        casella.setSize(300,100);
        casella.setLayout(null);
        casella.setDefaultCloseOperation(EXIT_ON_CLOSE);


        //creazione pulsanti di controllo
        // da risolvere, devo capire quale utente passare a lotta
        pannelloHome.getStart().addActionListener(e -> cardLayout.next(pannelloCard));
        //pannelloUtente.getBottone1().addActionListener(e-> cardLayout.next(pannelloCard)); //qua passo il numero della riga dove prendere l'utente da inizalizzare dentro lotta

        pannelloUtente.getBottone1().addActionListener(e -> {
            if(pannelloUtente.getBottone1().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(0);}
            inizializzaLotta(0);
        });
        pannelloUtente.getBottone2().addActionListener(e -> {
            if(pannelloUtente.getBottone2().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(1);}
            inizializzaLotta(1);
        });
        pannelloUtente.getBottone3().addActionListener(e -> {
            if(pannelloUtente.getBottone3().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(2);}
            inizializzaLotta(2); // o vedo se passargli l utente diretto.
        });

        pannelloUtente.getBottone4().addActionListener(e -> {
            if(pannelloUtente.getBottone4().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(3);}
            inizializzaLotta(3);
        });



        //pannelloUtente.getBottone2().addActionListener(e->cardLayout.next(pannelloCard));
        //pannelloUtente.getBottone3().addActionListener(e->cardLayout.next(pannelloCard));
        //pannelloUtente.getBottone4().addActionListener(e->cardLayout.next(pannelloCard));
        // pannello lotta è l'ultimo quindi posso aggiungere la schermata alla fine appunto
        // non so se questo sia possibile utilizzando lambda
        setResizable(false);
        setVisible(true);

    }
    private void inizializzaLotta(int indiceUtente) {
        try {
            pannelloLotta = new Lotta(indiceUtente); // UTENTE 1
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JPanel pannello3 = pannelloLotta.getPannello();
        pannelloCard.add(pannello3, "CARD 3");
        pannelloCard.revalidate();  // Forza il ricalcolo del layout
        pannelloCard.repaint();     // Ridisegna il pannello
        cardLayout.show(pannelloCard, "CARD 3");
    }

    private void creaUtente(){

        String input = JOptionPane.showInputDialog(casella, "Inserisci il nome utente");
        try{
            utenteGenerato = new Utente(input);
        } catch (IOException ex) {
            System.err.println("Creazione utente non andato a buon fine");
            throw new RuntimeException(ex);
        }
        try{
            utenteGenerato.scrittore();
        } catch (IOException exc){
            System.err.println("Salvataggio non andato a buon fine");
            throw new RuntimeException(exc);
        }
    }
    private void caricaUtente(int indiceUtente){
        try {
            utenteGenerato = rd.buildUtentebyString(rd.getRigaByIndex("testo/utenti.txt",indiceUtente));
        } catch (IOException ex) {
            System.err.println("File non formattato correttamente ");

            throw new RuntimeException(ex);
        }
    }



}
