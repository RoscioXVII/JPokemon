package com.jpokemon;

import javax.swing.*;
import java.awt.*;

public class SchermataBattaglia extends StaticScreen {

    public SchermataBattaglia(boolean flag){
        setTitle("JPokemon");
        setSize(1280,720); // posso anche fare il set location
        setLocationRelativeTo(null);// per impostare la posizione della finestra
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.flag = flag;
        ImageIcon img = new ImageIcon("img/schermo-vittoria.png");
        Image wallpaper = img.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        img = new ImageIcon(wallpaper);
        JLabel label = new JLabel(img);
        pannello = new JPanel();
        pannello.setLayout(new BorderLayout());
        JLabel testo;
        if(!flag)
            testo = new JLabel("Il giocatore 2 ha vinto la serie di battaglie ");
        else
            testo = new JLabel("Il giocatore 1 ha vinto la serie di battaglie");

        //testo.setSize(100,100)
        testo.setBounds(220,100,1200,100);
        testo.setFont(new Font("Arial", Font.BOLD, 50));
        //testo.setLocation(600,100);
        pannello.add(testo, BorderLayout.CENTER);
        pannello.add(label,BorderLayout.CENTER);
        setContentPane(pannello);
    }
}
