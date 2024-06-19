package com.jpokemon;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    public Home(){
        ImageIcon img = new ImageIcon("img/home.jpg");
        JLabel label = new JLabel(img);
        JPanel pannello = new JPanel();
        pannello.setLayout(new BorderLayout());

        // creo bottone
        JButton start = new JButton("START");
        start.setSize(450,100);

        // aggiungo il bottone al pannello
        pannello.add(start);
        pannello.add(label,BorderLayout.CENTER);
        start.setLocation(572,840);

        setContentPane(pannello); // TODO
        setSize(1920,1080); // dimensioni finestra
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }



}
