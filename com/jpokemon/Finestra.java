package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Classe che estende JFrame contenente un cardLayout
 * con all'interno tutti gli scenari del videogioco
 */
public class Finestra extends JFrame {
    private CardLayout cardLayout;
    private  JPanel pannelloCard;
    private Lotta pannelloLotta;
    private Utente utenteGenerato1;
    private Utente utenteGenerato2;
    private int indiceUtente1=-1;
    private JFrame casella = new JFrame("Inserisci Utente");
    private Reader rd = Reader.getInstance();


    /**
     * Inizializzazione e definizione di tutti i componenti grafici
     * da inserire dentro il card layout
     * @throws IOException
     */

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
        pannelloHome.getButton().addActionListener(e -> cardLayout.next(pannelloCard));
        //pannelloUtente.getBottone1().addActionListener(e-> cardLayout.next(pannelloCard)); //qua passo il numero della riga dove prendere l'utente da inizalizzare dentro lotta

        pannelloUtente.getBottone1().addActionListener(e -> {
            if(pannelloUtente.getBottone1().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(0);}
            // devo fare in modo che qua ottengo tutti e due gli indici
            if (indiceUtente1 == -1)
                indiceUtente1 = 0;
            else
                inizializzaLotta(indiceUtente1,0);
        });
        pannelloUtente.getBottone2().addActionListener(e -> {
            if(pannelloUtente.getBottone2().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(1);}
            if (indiceUtente1 == -1)
                indiceUtente1 = 1;
            else
                inizializzaLotta(indiceUtente1,1);
        });
        pannelloUtente.getBottone3().addActionListener(e -> {
            if(pannelloUtente.getBottone3().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(2);}
            if (indiceUtente1 == -1)
                indiceUtente1 = 2;
            else
                inizializzaLotta(indiceUtente1,2);
        });

        pannelloUtente.getBottone4().addActionListener(e -> {
            if(pannelloUtente.getBottone4().getText().equals("NUOVO UTENTE")){
                creaUtente();}
            else{caricaUtente(3);}
            if (indiceUtente1 == -1)
                indiceUtente1 = 3;
            else
                inizializzaLotta(indiceUtente1,3);
        });

        setResizable(false);
        setVisible(true);

    }

    /**
     * Inizializza la pagina successiva (Lotta)
     * indicando quale utente è stato selezionato nella schermata precedente
     * e che dovrà quindi partecipare alla lotta
     * @param indiceUtente1, indice dell'utente nel file di testo di salvataggio
     * @param indiceUtente2, indice dell'utente 2 nel file di testo di salvataggio
     */
    private void inizializzaLotta(int indiceUtente1,int indiceUtente2) {
        try {
            pannelloLotta = new Lotta(indiceUtente1,indiceUtente2); // UTENTE 1
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        JPanel pannello3 = pannelloLotta.getPannello();
        pannelloCard.add(pannello3, "CARD 3");
        pannelloCard.revalidate();  // Forza il ricalcolo del layout
        pannelloCard.repaint();     // Ridisegna il pannello
        cardLayout.show(pannelloCard, "CARD 3");
    }

    /**
     * Genera un nuovo utente, chiedendo di inserire il suo nome
     */

    private void creaUtente(){

        String input = JOptionPane.showInputDialog(casella, "Inserisci il nome utente");
        try{
            if (utenteGenerato1==null)
                utenteGenerato1 = new Utente(input);
            else
                utenteGenerato2 = new Utente(input);
        } catch (IOException ex) {
            System.err.println("Creazione utente non andato a buon fine");
            throw new RuntimeException(ex);
        }
        try{
            if (utenteGenerato2==null)
                utenteGenerato1.scrittore();
            else
                utenteGenerato2.scrittore();
        } catch (IOException exc){
            System.err.println("Salvataggio non andato a buon fine");
            throw new RuntimeException(exc);
        }
    }

    /**
     *  carica uno già definito nel file di salvataggio
     *  a seconda del bottone selezionato
     * @param indiceUtente indice della riga da analizzare nel file di salvataggio dei progressi
     */
    private void caricaUtente(int indiceUtente){
        try {
            if(utenteGenerato2==null)
                utenteGenerato1 = rd.buildUtentebyString(rd.getRigaByIndex("testo/utenti.txt",indiceUtente));
            else
                utenteGenerato2 = rd.buildUtentebyString(rd.getRigaByIndex("testo/utenti.txt",indiceUtente));
        } catch (IOException ex) {
            System.err.println("File non formattato correttamente ");

            throw new RuntimeException(ex);
        }
    }



}
