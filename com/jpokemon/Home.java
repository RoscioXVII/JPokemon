package com.jpokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra di avvio del videogioco
 */
public class Home extends StaticScreen {
    //private JPanel pannello;
    //private JButton start;
    public Home(){
        ImageIcon img = new ImageIcon("img/home.png");
        Image wallpaper = img.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        img = new ImageIcon(wallpaper);
        JLabel label = new JLabel(img);
        pannello = new JPanel();
        pannello.setLayout(new BorderLayout());

        // creo bottone
        button = new JButton("START");
        button.setSize(250,70);

        // aggiungo il bottone al pannello
        pannello.add(button);
        pannello.add(label,BorderLayout.CENTER);
        button.setLocation(540,570);

        setContentPane(pannello); // TODO


    }
    public JButton getButton(){
        return button;
    }
    public JPanel getPannello(){
        return pannello;
    }



}
