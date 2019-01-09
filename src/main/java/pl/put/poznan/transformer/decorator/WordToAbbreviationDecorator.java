package pl.put.poznan.transformer.decorator;

import java.util.ArrayList;
import java.util.List;
import pl.put.poznan.transformer.logic.Transformer;
import static pl.put.poznan.transformer.utils.Transformation.createShortcut;

public class WordToAbbreviationDecorator extends TransformerDecorator {
    
    public WordToAbbreviationDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return wordToAbbreviation(transformer.transform(text));
    }
    
    /***
     * Metoda znajdująca i zamieniająca słowa na skróty
     * @param src - tekst w którym należy znaleźć i zamienić wyrazy na skróty
     * @return tekst ze słowami zwiniętymi w skróty
     */
    public static String wordToAbbreviation(String src){
        List<String> sentences = new ArrayList<String>();
        sentences.add("między innymi");
        sentences.add("na przykład");
        sentences.add("i tym podobne");
        sentences.add("i tak dalej");

        for(String sentence: sentences){
            if(src.toLowerCase().contains(sentence)){
                src = src.replace(sentence, createShortcut(sentence));
            }
        }

        return src;
    }
}
