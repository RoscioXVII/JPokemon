package com.jpokemon;

public class Formule {

    public Formule(){}

    public static int danno(int livello,String tipoPoke1_1, String tipoPoke1_2,String tipoPoke2_1,String tipoPoke2_2, String tipoMossa, int potenza, int attacco, int difesa){

        double stab = 1;
        double superefficace = 0; // da implementare
        int bruttoColpo = 0;        // DA IMPLEMENTARE INSIEME A SUPEREFFICACE

        if(tipoPoke1_1 == tipoMossa || tipoPoke1_2 == tipoMossa){
            stab = 1.5;
        }
        // if(tipoMossa is superefficace)

        double danno = ((((((((2*livello)/5) + 2) * (potenza) * (attacco/difesa) )/50) + 2)) * stab * bruttoColpo * superefficace);

        return (int)danno;
    }
    public static int exp(int baseExp, int livelloPokemon){

        double formula = ((1.5 * baseExp * livelloPokemon)/(7*2));

        return (int)formula;

    }


}
