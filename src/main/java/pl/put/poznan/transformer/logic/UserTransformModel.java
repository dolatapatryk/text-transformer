package pl.put.poznan.transformer.logic;

import lombok.Data;

/**
 *Klasa reprezentująca ciąg transformacji zdefiniowany przez użytkownika
 * @author patrykdolata
 */
@Data
public class UserTransformModel {
    
    /**
     * własna nazwa ciągu transformacji
     */
    private String name;
    
    /**
     * tablica transformacji
     */
    private String[] transforms;
    
    public UserTransformModel(){}
    
    /**
     * 
     * Konstruktor przyjmujący nazwe ciągu jako parametr
     * @param name nazwa własnego ciągu transformacji
     */
    public UserTransformModel(String name) {
        this.name = name;
    } 
}
