package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

public class UpperDecorator extends TransformerDecorator {
    
    public UpperDecorator(Transformer transformer) {
        super(transformer);
    }
    
   @Override
   public String transform(String text) {
       return upper(transformer.transform(text));
   }
   
    /**
     * Metoda zmieniająca wszystkie litery w tekście na wielkie
     * @param text oryginalny tekst
     * @return tekst, który wszystkie litery ma wielkie
     */
    public String upper(String text) {
        return text.toUpperCase();
    }
}
