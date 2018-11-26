package pl.put.poznan.transformer.logic;

import lombok.Data;

/**
 *
 * @author patrykdolata
 */
@Data
public class UserTransformModel {
    
    public UserTransformModel(){}
    
    public UserTransformModel(String name) {
        this.name = name;
    }

    private String name;
    private String[] transforms;
    
}
