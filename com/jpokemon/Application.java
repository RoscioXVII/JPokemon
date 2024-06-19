package com.jpokemon;

import javax.swing.*;

public class Application {
    public static void main(String[] args){
        //JFrame frameProva = new JFrame("JPokemon ");
        new Finestra(); // bozza schermata lotta
        new Home(); // schermata iniziale

    }
}




/*
public class Pulsanti extends JFrame {

    JButton pulsanteUno = new JButton("Pulsante uno"); // Creazione dei pulsanti

    JButton pulsanteDue = new JButton("Pulsante due");

    String x;
    int y;

    public Pulsanti() {

        super("Prova pulsanti");

        setSize(800, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pulsanteUno.addActionListener(e -> {

                x = JOptionPane.showInputDialog(Pulsanti.this, "Inserire il tuo nome");
                if(x == null || x.length() == 0) {
                    JOptionPane.showMessageDialog(Pulsanti.this, "Nome non inserito");
                }else {
                    y = JOptionPane.showConfirmDialog(Pulsanti.this,"Sei sicuro del nome: " + x);
                    // OK = 0, NO = 1, Annulla = 2
                    if(y == 0) {
                        //vedemo se se po cambia
                    }
                }


            }
        );

        pulsanteDue.addActionListener(e -> {
                JOptionPane.showMessageDialog(Pulsanti.this, "tu sei "+ x);
        });


        JPanel pannello = new JPanel(); // Creazione del pannello che conterrà i pulsanti

        pannello.add(pulsanteUno); // Aggiunta dei pulsanti al pannello

        pannello.add(pulsanteDue);

        setContentPane(pannello); // Trasforma il pannello nel contenuto del frame

        setVisible(true);
    }


    public static void main(String args[]) {

        Pulsanti p = new Pulsanti();

    }

}
 */