package pl.put.poznan.transformer.logic;

import lombok.Data;
/**
 * Klasa reprezentująca model opcji dostępnej jako transformacja tekstu
 * @author patryk
 */
public @Data class OptionModel {
    
    /**
     * nazwa opcji
     */
    private String name;
    /**
     * opis opcji
     */
    private String description;
}
