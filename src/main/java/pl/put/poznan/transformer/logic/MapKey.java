package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

/**
 *
 * @author patrykdolata
 */
public @Data class MapKey {
    
    private List<String> keys = new ArrayList<>();
    
    public MapKey(){};
    
    public MapKey(String... keys) {
        this.keys.addAll(Arrays.asList(keys));
    }

    public boolean contains(String key) {
        if(this.keys.contains(key))
            return true;
        else
            return false;
    }
    
    
    
}
