package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

public class LowerDecorator extends TransformerDecorator {
    
    public LowerDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return lower(transformer.transform(text));
    }
    
    /**
     * Metoda zmieniająca wszystkie litery w tekście na małe
     * @param text oryginalny tekst
     * @return tekst, który wszystie litery ma małe
     */
    public String lower(String text) {
        return text.toLowerCase();
    }
}
