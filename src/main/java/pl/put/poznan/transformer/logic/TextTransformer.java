package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.utils.Transformation;

/**
 * Klasa odpowiadająca za transformacje na tekście
 * @author patryk, marcel, artur, dominik
 */
public class TextTransformer {

    /**
     * tablica transformacji
     */
    private final String[] transforms;

    /**
     * Konstruktor klasy, przyjmujący transformacje, których chce użytkownik
     * @param transforms transformacje użytkownika
     */
    public TextTransformer(String[] transforms){
        this.transforms = transforms;
    }
    /**
     * Głowna metoda transformująca, bierze każdą transformacje po kolei, więc kolejność
     * transformacji w requescie ma znaczenie!!
     * @param text tekst wprowadzony przez użytkownika
     * @return ztransformowany tekst
     */
    public String transform(String text){
        for(String transform : transforms) {
            if(transform.equals("numberToText"))
                text = Transformation.numberToText(text);
            if(transform.equals("inverse"))
                text = Transformation.inverse(text);
            if(transform.equals("upper"))
                text = text.toUpperCase();
            if(transform.equals("lower"))
                text = text.toLowerCase();
            //tu inne opcje
        }
        
        return text;
    }
    
}
