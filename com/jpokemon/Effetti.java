package com.jpokemon;

import java.util.Objects;

public class Effetti {
    public Effetti(){}

    public static int Effetto(String nome){
        String effetto = Formule.getEffettoFromTabella(nome);
        if(Objects.equals(effetto, "Nulla")){
            return -1;
        }
        String[] Condizioni = effetto.split(":");
        int x = Condizioni.length;
        int res;
        switch (Condizioni[x-1]){
            case "Prima" -> res = Prima();
            case "Durante" -> res = Durante();
            case "Dopo" -> res = Dopo();
            default -> res = -1;
        }
        return res;
    }
    public static int Prima(){
        return 1;
    }
    public static int Durante(){
        return 2;
    }
    public static int Dopo(){
        return 3;
    }

    public static void main(String[] args) {
        int risoluzioneEffetto1 = Effetto("Absorb");
        int risoluzioneEffetto2 = Effetto("Acid");
        int risoluzioneEffetto3 = Effetto("Acid Armor");
        int risoluzioneEffetto4 = Effetto("Wrap");
    }

}
