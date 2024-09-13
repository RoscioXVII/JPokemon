package com.jpokemon;

import javax.swing.*;

/**
 * Barra della salute legata ai pokemon coivnolti in lotta
 */
public class BarraPS extends JFrame {
    private int vita;
    private JProgressBar barraSalute;

    public BarraPS(int salute){
        this.vita=salute;
        barraSalute= new JProgressBar(0,vita);
        barraSalute.setValue(vita);
        barraSalute.setBounds(0,0,500,50);
        barraSalute.setStringPainted(false); // mostra la percentuale

    }

    /**
     * Diminuisce la salute del pokemon e di conseguenza anche la barra relativa ad essa
     * @param danno danno inflitto dal pokemon nemico
     */

    public void diminuisci(int danno){
        int salute = barraSalute.getValue();
        while(salute>=vita-danno){
            barraSalute.setValue(salute);
            salute--;
        }
        vita-=danno;
    }

    public JProgressBar getBarraSalute(){
        return barraSalute;
    }


}
