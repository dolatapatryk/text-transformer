package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;

public class CapitalizeDecorator extends TransformerDecorator {
    
    public CapitalizeDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return capitalize(transformer.transform(text));
    }
    
    /**
     * Metoda zamieniająca pierwszą literę w każdym wyrazie na wielką
     * @param text - tekst wejsciowy
     * @return tekst, w którym każde słowo zaczyna się wielką literą
     */
    public static String capitalize(String text){
        String[] words = text.split(" ");
        text = "";
   
        for(int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
            
            if(i == 0)
                text = text + words[i];
            else
                text = text + " " + words[i];
        }
        
        return text;
    }
}
