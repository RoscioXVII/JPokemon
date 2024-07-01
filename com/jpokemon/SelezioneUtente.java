package com.jpokemon;

import javax.swing.*;
import java.awt.*;

public class SelezioneUtente extends JFrame {
    private JPanel pannello;
    private JButton bottone1;
    private JButton bottone2;
    private JButton bottone3;
    private JButton bottone4;
    private String nome;



    public SelezioneUtente(){
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
        bottone1.setText(nome != null ? nome : "NUOVO UTENTE");
        bottone2.setText(nome != null ? nome : "NUOVO UTENTE");
        bottone3.setText(nome != null ? nome : "NUOVO UTENTE");
        bottone4.setText(nome != null ? nome : "NUOVO UTENTE");
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
