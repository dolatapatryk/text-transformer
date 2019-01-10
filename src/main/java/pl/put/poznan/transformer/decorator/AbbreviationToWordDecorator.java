package pl.put.poznan.transformer.decorator;

import pl.put.poznan.transformer.logic.Transformer;
import static pl.put.poznan.transformer.utils.Transformation.expandShortcut;

public class AbbreviationToWordDecorator extends TransformerDecorator {
    
    public AbbreviationToWordDecorator(Transformer transformer) {
        super(transformer);
    }
    
    @Override
    public String transform(String text) {
        return abbreviationToWord(transformer.transform(text));
    }
    
    /***
     * Metoda znajdująca i rozwijająca skróty w tekście
     * @param src - tekst w którym należy rozwinąć skróty
     * @return tekst z rozwiniętymi skrótami
     */
    public String abbreviationToWord(String src){
        String[] words = src.split(" ");

        String newText = "";
        String newWord = "";
        for(int i = 0; i < words.length; i++){
            if(i != words.length - 1)
                newWord = expandShortcut(words[i], false);
            else
                newWord = expandShortcut(words[i], true);
            newText += newWord;
            if(newWord.equals(words[i])){
                 if(i != words.length - 1)
                    newText += " ";
            }
        }

        return newText;
    }
}
