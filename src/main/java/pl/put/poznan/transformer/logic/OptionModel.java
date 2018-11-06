package pl.put.poznan.transformer.logic;

import lombok.Data;
/**
 * Klasa reprezentująca model opcji dostępnej jako transformacja tekstu
 * @author patryk
 */
public @Data class OptionModel {
    
    private String name;
    private String description;
}
