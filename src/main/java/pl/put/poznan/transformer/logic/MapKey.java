package pl.put.poznan.transformer.logic;

import lombok.Data;

/**
 *
 * @author patrykdolata
 */
public @Data class MapKey {
    
    private String key1;
    private String key2;
    
    public boolean checkIfContains(String key) {
        if(key.equals(key1))
            return true;
        else if (key.equals(key2))
            return true;
        else
            return false;
    }
}
