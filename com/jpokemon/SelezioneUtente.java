package com.jpokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SelezioneUtente extends JFrame {
    private JPanel pannello;
    private JButton bottone1;
    private JButton bottone2;
    private JButton bottone3;
    private JButton bottone4;
    private String nome1;
    private String nome2;
    private String nome3;
    private String nome4;



    public SelezioneUtente() throws FileNotFoundException {
        Reader rd = new Reader();
        ImageIcon img = new ImageIcon("img/selezioneutenti.jpg");
        Image wallpaper = img.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        img = new ImageIcon(wallpaper);
        JLabel label = new JLabel(img);
        pannello = new JPanel();
        pannello.setLayout(new BorderLayout());
        bottone1 = new JButton();
        bottone1.setBounds(200,200,300,100);
        bottone2 = new JButton();
        bottone2.setBounds(200,400,300,100);
        bottone3 = new JButton();
        bottone3.setBounds(800,200,300,100);
        bottone4 = new JButton();
        bottone4.setBounds(800,400,300,100);
        //al posto di nome metto un array di utenti e uso getnome
        // questi vengono tirati fuori dal file di testo
        // implementare controllo (non sempre sono presenti tutti i nomi)
        String testo = rd.getRigaByIndex("testo/utenti.txt",0);
        if(testo==null)
            nome1 = null;
        else
            nome1 = testo.split(":")[0];

        testo = rd.getRigaByIndex("testo/utenti.txt",1);

        if(testo==null)
            nome2 = null;
        else
            nome2 = testo.split(":")[0];

        testo = rd.getRigaByIndex("testo/utenti.txt",2);

        if(testo==null)
            nome3 = null;
        else
            nome3 = testo.split(":")[0];

        testo = rd.getRigaByIndex("testo/utenti.txt",3);

        if(testo==null)
            nome4 = null;
        else
            nome4 = testo.split(":")[0];

        bottone1.setText(nome1 != null ? nome1 : "NUOVO UTENTE");
        bottone2.setText(nome2 != null ? nome2 : "NUOVO UTENTE");
        bottone3.setText(nome3 != null ? nome3 : "NUOVO UTENTE");
        bottone4.setText(nome4 != null ? nome4 : "NUOVO UTENTE");
        JFrame casella = new JFrame("Inserisci Utente");
        casella.setSize(300,100);
        casella.setLayout(null);
        casella.setDefaultCloseOperation(EXIT_ON_CLOSE);

        if (bottone1.getText().equals("NUOVO UTENTE")){
            bottone1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = JOptionPane.showInputDialog(casella,"Inserisci il nome utente = ");
                    Utente utente1 = new Utente(input);
                    try {
                        utente1.scrittore(); // dovrà scrivere il contenuto dentro dell istanza dentro il .txt
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });
        }

        if (bottone2.getText().equals("NUOVO UTENTE")){
            bottone2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = JOptionPane.showInputDialog(casella,"Inserisci il nome utente = ");
                    Utente utente2 = new Utente(input);
                    try {
                        utente2.scrittore(); // dovrà scrivere il contenuto dentro dell istanza dentro il .txt
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });
        }

        if(bottone3.getText().equals("NUOVO UTENTE")){
            bottone3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String input = JOptionPane.showInputDialog(casella,"Inserisci il nome utente = ");
                    Utente utente3 = new Utente(input);
                    try {
                        utente3.scrittore(); // dovrà scrivere il contenuto dentro dell istanza dentro il .txt
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            });
        }
        if (bottone4.getText().equals("NUOVO UTENTE")){
            bottone4.addActionListener(e -> {
                String input = JOptionPane.showInputDialog(casella,"Inserisci il nome utente = ");
                Utente utente4 = new Utente(input);
                try {
                    utente4.scrittore(); // dovrà scrivere il contenuto dentro dell istanza dentro il .txt
                } catch (IOException exc) {
                    throw new RuntimeException(exc);
                }
            });
        }

        pannello.add(bottone1,BorderLayout.CENTER);
        pannello.add(bottone2,BorderLayout.CENTER);
        pannello.add(bottone3,BorderLayout.CENTER);
        pannello.add(bottone4,BorderLayout.CENTER);
        pannello.add(label,BorderLayout.CENTER);
        setContentPane(pannello);

        // nome = rd.legginomeutente();

    }
    public JPanel getPannello(){
        return pannello;
    }

    public JButton getBottone1() {
        return bottone1;
    }

    public JButton getBottone2() {
        return bottone2;
    }

    public JButton getBottone3() {
        return bottone3;
    }

    public JButton getBottone4() {
        return bottone4;
    }
}
