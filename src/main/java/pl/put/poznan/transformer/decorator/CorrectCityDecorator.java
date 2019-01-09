package pl.put.poznan.transformer.decorator;

import java.util.HashMap;
import java.util.Map;
import pl.put.poznan.transformer.logic.MapKey;
import pl.put.poznan.transformer.logic.Transformer;

/**
 *
 * @author patrykdolata
 */
public class CorrectCityDecorator extends TransformerDecorator {

    public CorrectCityDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return correctCity(transformer.transform(text));
    }
    
    /**
     * Poprawia pisownie wybranych miast
     * @param text wejściowy tekst
     * @return tekst z poprawionym słowem miasta
     */
    public String correctCity(String text) {
        Map<MapKey, String> cityMap = new HashMap<>();
        cityMap.put(new MapKey("poznan", "poznań"), "Poznań");
        cityMap.put(new MapKey("warszawa"), "Warszawa");
        cityMap.put(new MapKey("krakow", "kraków", "krakuw"), "Kraków");
        cityMap.put(new MapKey("wroclaw", "wrocław"), "Wrocław");
        
        for(Map.Entry<MapKey, String> entry : cityMap.entrySet()) {
            for(String key : entry.getKey().getKeys()) {
                if(text.toLowerCase().contains(key))
                    text = text.replace(key, entry.getValue());
            }
        }
        
        return text;
    }

}
