package pl.put.poznan.transformer.decorator;

import java.util.regex.Pattern;
import pl.put.poznan.transformer.logic.Transformer;
import static pl.put.poznan.transformer.utils.Transformation.expandShortcut;

public class CapitalizeSentencesDecorator extends TransformerDecorator {
    
    public CapitalizeSentencesDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return capitalizeSentences(transformer.transform(text));
    }
    
    /**
     * Metoda wstawiająca wielkie litery na początku zdań
     * @param text - tekst do przetworzenia
     * @return tekst z wielkimi literami na początku każdego zdania
     */

    public static String capitalizeSentences(String text){
        String[] sentences = text.split(Pattern.quote(". "));
        for(int i = 0; i < sentences.length-1; i++){
            String[] wordsInSentence = sentences[i].split(" ");
            String temp = wordsInSentence[wordsInSentence.length-1];
            if (temp == expandShortcut(temp, true)){
                int j = 0;
                while (sentences[i+1].charAt(j) == ' ')
                    j++;
                sentences[i+1] = sentences[i+1].substring(j,j+1).toUpperCase() + sentences[i+1].substring(j+1);
                sentences[i] = sentences[i]+". ";
            }
            else {
                sentences[i] += ". ";
            }
        }

        String output = "";
        for (int i = 0; i<sentences.length; i++){
            output+=sentences[i];
        }
        output = output.substring(0,1).toUpperCase() + output.substring(1);
        return output;
    }
}
