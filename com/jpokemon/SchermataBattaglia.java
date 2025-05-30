package com.jpokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra che viene  visualizzata alla fine della serie di lotte
 * per determinare il vincitore della battaglia
 */
public class SchermataBattaglia extends StaticScreen {

    public SchermataBattaglia(boolean flag){
        setTitle("JPokemon");
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.flag = flag;
        ImageIcon img = new ImageIcon("img/fine-game.jpg");
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


        testo.setBounds(350,20,1200,100);
        testo.setFont(new Font("Arial", Font.BOLD, 30));
        button = new JButton("Esci dal gioco");
        button.setSize(250,70);
        button.setLocation(540,590);

        pannello.add(testo, BorderLayout.CENTER);
        pannello.add(button);
        button.addActionListener(e -> System.exit(0));
        pannello.add(label,BorderLayout.CENTER);

        setContentPane(pannello);
    }
}
