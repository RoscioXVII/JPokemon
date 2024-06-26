package com.jpokemon;

import java.util.Arrays;
import java.util.Random;
import java.io.IOException;

public class LottaProva extends Reader{

    public LottaProva(){}

    public static void main(String[] args) throws IOException {
        Reader a = new Reader();
        //Formule b = new Formule();  se dichiari static le funzioni le puoi usare quando vuoi

        String place = a.getRigaByIndex("testo/pokemon.txt",0);
        Pokemon squadra1 = a.buildPokemonByString(place);

        place = a.getRigaByIndex("testo/pokemon.txt",6);
        Pokemon squadra2 = a.buildPokemonByString(place);

        System.out.println(squadra1.getNome());
        System.out.println(squadra2.getNome());

        System.out.println(squadra1.mossaString());
        System.out.println(squadra2.mossaString());

        System.out.println(squadra1.getAttacco());
        System.out.println(squadra2.getDifesa());

        System.out.println(squadra1.getVelocita());

        int bruttoColpo = Formule.bruttoColpo(squadra1.getVelocita());
        if(bruttoColpo == 2){
            System.out.println("Brutto colpo");
        }
        int danno = Formule.danno(squadra1.getTipo1(),squadra1.getTipo2(),squadra2.getTipo1(),squadra2.getTipo2(),Tipo.ERBA,bruttoColpo,5,40,squadra1.getAttacco(),squadra2.getDifesa());

        System.out.println(danno);

    }
}
