package com.jpokemon;

import javax.swing.*;

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

    public void diminuisci(int danno){
        int salute = barraSalute.getValue();
        while(salute>=vita-danno){
            barraSalute.setValue(salute);
            try{
                Thread.sleep(150); // velocita decremento barra
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            salute--;
        }
        vita-=danno;
    }

    public JProgressBar getBarraSalute(){
        return barraSalute;
    }
    public int getVita(){
        return vita;
    }

}
