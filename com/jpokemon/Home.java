package com.jpokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra di avvio del videogioco
 */
public class Home extends JFrame {
    private JPanel pannello;
    private JButton start;
    public Home(){
        ImageIcon img = new ImageIcon("img/home.png");
        Image wallpaper = img.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        img = new ImageIcon(wallpaper);
        JLabel label = new JLabel(img);
        pannello = new JPanel();
        pannello.setLayout(new BorderLayout());

        // creo bottone
        start = new JButton("START");
        start.setSize(250,70);

        // aggiungo il bottone al pannello
        pannello.add(start);
        pannello.add(label,BorderLayout.CENTER);
        start.setLocation(540,570);

        setContentPane(pannello); // TODO


    }
    public JButton getStart(){
        return start;
    }
    public JPanel getPannello(){
        return pannello;
    }



}
