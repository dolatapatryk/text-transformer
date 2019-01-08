package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import static pl.put.poznan.transformer.utils.Transformation.reverse;

public class InverseDecorator extends TransformerDecorator {
    
    public InverseDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return inverse(transformer.transform(text));
    }
    
    /**
     * Metoda do odwracania tekstu z zachowaniem wielkości liter na odpowiednich pozycjach
     * @param text oryginalny tekst
     * @return odwrócony tekst z zachowanie wielkości liter na pozycjach
     */
    public static String inverse(String text) {
        String reverse = reverse(text);
        
        //String inverse = keepLettersSize(text, reverse);
        
        return reverse;
    }
}
