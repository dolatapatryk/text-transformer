package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import pl.put.poznan.transformer.utils.Transformation;
import static pl.put.poznan.transformer.app.TextTransformerApplication.userTransforms;
import pl.put.poznan.transformer.utils.Utils;

/**
 * Klasa odpowiadająca za transformacje na tekście
 * @author patryk, marcel, artur, dominik
 */
public class TextTransformer {

    /**
     * tablica transformacji
     */
    @Getter private final String[] transforms;
    

    /**
     * Konstruktor klasy, przyjmujący transformacje, których chce użytkownik
     * @param transforms transformacje użytkownika
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }
    
    /**
     * Metoda zmieniająca tekst na podstawie podanej transformacji
     * @param text tekst wejściowy
     * @param transform nazwa transformacji
     * @return  ztransformowany tekst
     */
    public String transform(String text, String transform){
            if(transform.equals("numberToText"))
                text = Transformation.numberToText(text);
            if(transform.equals("inverse"))
                text = Transformation.inverse(text);
            if(transform.equals("capitalize"))
                text = Transformation.capitalize(text);
            if(transform.equals("capitalizeSentences"))
                text = Transformation.capitalizeSentences(text);
            if(transform.equals("upper"))
                text = Transformation.upper(text);
            if(transform.equals("lower"))
                text = Transformation.lower(text);
            if(transform.equals("abbreviationToWord"))
                text = Transformation.abbreviationToWord(text);
            if(transform.equals("wordToAbbreviation"))
                text = Transformation.wordToAbbreviation(text);
            if(transform.equals("addDot"))
                text = Transformation.addDot(text);
            if(transform.equals("eliminate"))
                text = Transformation.eliminate(text);
            //tu inne opcje
        return text;
    }
    
    /**
     * Głowna metoda transformująca, bierze każdą transformacje po kolei, więc kolejność
     * transformacji w requescie ma znaczenie!!
     * @param text tekst wprowadzony przez użytkownika
     * @return ztransformowany tekst użytkownika
     */
    public String superTransform(String text) {
        for(String transform : this.transforms) {
            if(Utils.checkUserTransforms(transform)) {
                UserTransformModel userTransform = Utils.getTransformWithGivenName(transform);
                text = transformWithUserTransforms(text, userTransform);
            } else 
                text = transform(text, transform);
        }
        
        return text;
    }
    
    /**
     * Metoda, która transformuje tekst na podstawie zdefiniowanego ciągu transformacji użytkownika
     * @param text wejściowy tekst
     * @param userTransforms model ciągu transformacji użytkownika
     * @return  ztransformowany tekst
     */
    public String transformWithUserTransforms(String text, UserTransformModel userTransforms) {
        for(String transform : userTransforms.getTransforms())
            text = transform(text, transform);
        
        return text;
    }
    
}
