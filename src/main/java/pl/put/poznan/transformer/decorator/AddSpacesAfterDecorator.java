package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

public class AddSpacesAfterDecorator extends TransformerDecorator {
    
    private char symbol;
    
    public AddSpacesAfterDecorator(Transformer transformer, char symbol) {
        super(transformer); 
        this.symbol = symbol; 
    }

    @Override
    public String transform(String text) { 
        return addSpacesAfter(transformer.transform(text),symbol); 
    }
    
    /**
     * Metoda wstawiająca spacje po każdym wystąpieniu wyspecyfikowanego znaku, z wyłączeniem ostatniej litery
     * @param text - tekst do przetworzenia
     * @param character - znak, po którym należy wstawiać spacje
     * @return tekst ze spacjami po wyspecyfikowanych znakach
     */
    public String addSpacesAfter(String text, char character){
        String returnText = "";
        for (int i = 0; i < text.length()-1; i++){
            returnText += text.charAt(i);
            if (text.charAt(i) == character) {
                returnText += " ";
            }
        }
        returnText += text.charAt(text.length()-1);
        return returnText;
    }
}
