package pl.put.poznan.transformer.logic;

import lombok.Data;

/**
 * Klasa reprezentująca model json'a przesyłanego przez użytkownika
 * @author patryk
 */
public @Data class InputModel {
    
    /**
     * tekst do transformacji
     */
    private String text;
    
    /**
     * tablica transformacji
     */
    private String[] transforms;
}
