package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import pl.put.poznan.transformer.decorator.AbbreviationToWordDecorator;
import pl.put.poznan.transformer.decorator.AddDotDecorator;
import pl.put.poznan.transformer.decorator.CapitalizeDecorator;
import pl.put.poznan.transformer.decorator.CapitalizeSentencesDecorator;
import pl.put.poznan.transformer.decorator.EliminateDecorator;
import pl.put.poznan.transformer.decorator.InverseDecorator;
import pl.put.poznan.transformer.decorator.LowerDecorator;
import pl.put.poznan.transformer.decorator.NumberToTextDecorator;
import pl.put.poznan.transformer.decorator.UpperDecorator;
import pl.put.poznan.transformer.decorator.WordToAbbreviationDecorator;
import pl.put.poznan.transformer.utils.Utils;

/**
 * Klasa odpowiadająca za transformacje na tekście
 * @author patryk, marcel, artur, dominik
 */
public class TextTransformer {
    
    /**
     * Obiekt klasy Transformer, którego będziemy dekorować transformacjami
     */
    private Transformer transformer;

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
        this.transformer = new Text();
    }
    
    /**
     * Metoda dekorująca nasz obiekt klasy Transformer
     * @param transform nazwa transformacji
     * @return udekorowany obiekt
     */
    public Transformer decorate(String transform){
            if(transform.equals("numberToText"))
                transformer = new NumberToTextDecorator(transformer);
            if(transform.equals("inverse"))
                transformer = new InverseDecorator(transformer);
            if(transform.equals("capitalize"))
                transformer = new CapitalizeDecorator(transformer);
            if(transform.equals("capitalizeSentences"))
                transformer = new CapitalizeSentencesDecorator(transformer);
            if(transform.equals("upper"))
                transformer = new UpperDecorator(transformer);
            if(transform.equals("lower"))
                transformer = new LowerDecorator(transformer);
            if(transform.equals("abbreviationToWord"))
                transformer = new AbbreviationToWordDecorator(transformer);
            if(transform.equals("wordToAbbreviation"))
                transformer = new WordToAbbreviationDecorator(transformer);
            if(transform.equals("addDot"))
                transformer = new AddDotDecorator(transformer);
            if(transform.equals("eliminate"))
                transformer = new EliminateDecorator(transformer);
  
        return transformer;
    }
    
    /**
     * Głowna metoda transformująca, bierze każdą transformacje po kolei, więc kolejność
     * transformacji w requescie ma znaczenie!!
     * @param text tekst wprowadzony przez użytkownika
     * @return ztransformowany tekst użytkownika
     */
    public String transform(String text) {
        for(String transform : this.transforms) {
            if(Utils.checkUserTransforms(transform)) {
                UserTransformModel userTransform = Utils.getTransformWithGivenName(transform);
                transformer = transformWithUserTransforms(userTransform);
            } else 
                transformer = decorate(transform);
        }
        
        return transformer.transform(text);
    }
    
    /**
     * Metoda, która dekoruje obiekt na podstawie zdefiniowanego ciągu transformacji użytkownika
     * @param userTransforms model ciągu transformacji użytkownika
     * @return  obiekt udekorowany ciągiem transformacji użytkownika
     */
    public Transformer transformWithUserTransforms(UserTransformModel userTransforms) {
        for(String transform : userTransforms.getTransforms())
            transformer = decorate(transform);
        
        return transformer;
    }
    
}
