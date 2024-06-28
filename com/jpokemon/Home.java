package com.jpokemon;

import javax.swing.*;
import java.awt.*;

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
        //SCHERMO INTERO
      //  Dimension dimSchermo = Toolkit.getDefaultToolkit().getScreenSize();
     //   setSize(dimSchermo);
     //   setExtendedState(JFrame.MAXIMIZED_BOTH);
     //   setUndecorated(true);
        // SCHERMO INTERO - FINE

        // OPZIONI COMMENTATE RIGUARDANTI VISUALIZZAZIONE A FINESTRA

        //setSize(1920,1080); // dimensioni finestra
        //setLocationRelativeTo(null);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);

    }
    public JButton getStart(){
        return start;
    }
    public JPanel getPannello(){
        return pannello;
    }



}
