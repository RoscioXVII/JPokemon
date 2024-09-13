package com.jpokemon;

import javax.swing.*;
import java.awt.*;

/**
 * Finestra che determina il vincitore della singola lotta
 */
public class Vittoria extends StaticScreen {
    public Vittoria(boolean flag){
        setTitle("JPokemon");
        setSize(1280,720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.flag = flag;
        ImageIcon img = new ImageIcon("img/fine-lotta.jpeg");
        Image wallpaper = img.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        img = new ImageIcon(wallpaper);
        JLabel label = new JLabel(img);
        pannello = new JPanel();
        pannello.setLayout(new BorderLayout());
        JLabel testo;

        // creo bottone
        button = new JButton("Torna alla lotta");
        button.setSize(250,70);

        // aggiungo il bottone al pannello
        button.setLocation(540,570);
        if(!flag)
            testo = new JLabel("Il giocatore 2 ha vinto ");
        else
            testo = new JLabel("Il giocatore 1 ha vinto");


        testo.setBounds(390,0,1200,100);
        testo.setFont(new Font("Arial", Font.BOLD, 50));
        pannello.add(testo, BorderLayout.CENTER);
        pannello.add(button);
        pannello.add(label,BorderLayout.CENTER);
        setContentPane(pannello);
        button.addActionListener(e->dispose());

    }
}
