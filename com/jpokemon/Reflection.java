package com.jpokemon;

import java.lang.reflect.Method;

public class Reflection {

    /*
       Allora questo metodo prende una classe dichiarata e crea un array di metodi: poi cerchi il metodo che ti serve a te e lo usi invocandolo dalla classe
       dichiarata prima

       in un modo si può modificare per far partire un metodo che Dichiarato un pokemon, prende quello che sta scritto nel file della mossa e ci fa qualcosa
       tanto alla fine le modifiche sono fatte dalle mosse alle statistiche dei pokemon

       sul file mosse.txt ci sono degli esempi dopo il sesto campo (nella prima riga è 25) ci stanno gli effetti della mossa scritti in forma Effetto:quantità:cosaPunta

     */


    /*

        public static void main(String[] args) throws Exception {
        Mossa Mosse = new Mossa("Azione",Tipo.NORMALE,40,100,30);

        Method[] metodi = Mossa.class.getDeclaredMethods();

        for(Method method: metodi){
            if(method.getName().equals("ToString")){
                method.invoke(Mosse);
            }

        }
    }

     */


}
