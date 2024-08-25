package com.jpokemon;

import javax.swing.*;

/**
 * Classe astratta desrivente una schermata statica con cui
 * non è permessa un'interazione dinamica
 */
public abstract class StaticScreen extends JFrame {
    protected JPanel pannello;
    protected boolean flag;
    protected JButton button;
    public StaticScreen(){}
}
