package com.jpokemon;

public enum Tipo {
    NORMALE, FUOCO, LOTTA,
    ACQUA, ERBA, ELETTRO,
    TERRA, ROCCIA, GHIACCIO,
    PSICO, SPETTRO, DRAGO,
    BUIO, VELENO, VOLANTE,
    COLEOTTERO,FOLLETTO,ACCIAIO;

    // per la tabella debolezze usare switch case

    public static Tipo getTipoByString(String tipo) {
        tipo = tipo.toLowerCase();

        return switch (tipo) {
            case "normale" -> NORMALE;
            case "fuoco" -> FUOCO;
            case "lotta" -> LOTTA;
            case "acqua" -> ACQUA;
            case "erba" -> ERBA;
            case "elettro" -> ELETTRO;
            case "terra" -> TERRA;
            case "roccia" -> ROCCIA;
            case "ghiaccio" -> GHIACCIO;
            case "psico" -> PSICO;
            case "spettro" -> SPETTRO;
            case "drago" -> DRAGO;
            case "buio" -> BUIO;
            case "veleno" -> VELENO;
            case "volante" -> VOLANTE;
            case "coleottero" -> COLEOTTERO;
            case "folletto" -> FOLLETTO;
            case "acciaio" -> ACCIAIO;
            default -> null;
        };
    }
    

}
